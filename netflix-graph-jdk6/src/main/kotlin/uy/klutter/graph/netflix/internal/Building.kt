package uy.klutter.graph.netflix.internal

import com.netflix.nfgraph.build.NFBuildGraph
import com.netflix.nfgraph.spec.NFGraphSpec
import com.netflix.nfgraph.spec.NFNodeSpec
import com.netflix.nfgraph.spec.NFPropertySpec
import uy.klutter.graph.netflix.*
import java.io.DataOutputStream
import java.io.OutputStream


public class CompiledGraphSchema<N : Enum<N>, R : Enum<R>>(val schema: GraphSchemaBuilder<N, R>) {
    internal val nodeTypes: Set<N> = schema.nodeTypes
    internal val relationTypes: Set<R> = schema.relationTypes
    internal val nodeTypeEnum: Class<N> = schema.nodeTypeEnum
    internal val relationTypeEnum: Class<R> = schema.relationTypeEnum

    // when have a relationship of a->R->b and a->R->c need to change to a->R.b->b and a->R.c->c but later find both if someone asks for R again during querying

    // ok, take the schema, render out both the NFGraphSpec and also our version of this that we can serialize
    // Ours includes:
    //      outbound relationships for a given node type
    //      relationship groups that disambiguate any collisions
    //      relationship mirrors that automatically apply the reverse relationship when the opposite is added

    internal val relationshipGroups: MutableMap<RelationshipPairKey<N, R>, MutableSet<RelationshipTrippleKey<N, R>>> = hashMapOf()
    internal val relationshipMirrors = hashMapOf<RelationshipTrippleKey<N, R>, RelationshipTrippleKey<N, R>>()

    internal val graphSpec = NFGraphSpec()

    init {
        val relationshipFlags = hashMapOf<RelationshipTrippleKey<N, R>, Int>()

        fun createRelation(fromNode: N, relationship: R, toNode: N, relationOptions: GraphRelationOptions, modelScope: String? = null): RelationshipTrippleKey<N, R> {
            val trippleKey = RelationshipTrippleKey(fromNode, relationship, toNode)
            val oldFlags = relationshipFlags.get(trippleKey)
            if (oldFlags != null && oldFlags != relationOptions.flags) {
                throw RuntimeException("Repeated definition of a relationship must have the same flags: ${trippleKey} with old ${oldFlags} new ${relationOptions.flags}")
            }
            val pairKey = RelationshipPairKey(fromNode, relationship)
            val relationGroup = relationshipGroups.getOrPut(pairKey, { hashSetOf() })
            relationGroup.add(trippleKey)
            relationshipFlags.put(trippleKey, relationOptions.flags)
            return trippleKey
        }

        schema.relations.forEach { r ->
            val modelScope = if (r.scopeAs == RelationScope.GLOBAL) null else r.modelScopeName
            val forward = createRelation(r.fromNode, r.forwardRelation, r.toNode, r.forwardFlags, modelScope)
            if (r.backwardRelation != null) {
                val backward = createRelation(r.toNode, r.backwardRelation!!, r.fromNode, r.backwardFlags, modelScope)

                val oldForwardMirror = relationshipMirrors.get(forward)
                val oldBackwardMirror = relationshipMirrors.get(backward)

                if (oldForwardMirror != null && oldBackwardMirror != null && oldForwardMirror != backward && oldBackwardMirror != forward) {
                    throw RuntimeException("Repeated definition of a relationship mirrors must have the same mirror relationships: ${forward} and ${backward} have differing mirrors ${oldBackwardMirror} and ${oldForwardMirror}")
                }

                relationshipMirrors.put(forward, backward)
                relationshipMirrors.put(backward, forward)
            }
        }

        val relationshipGroupsPerNodeType = relationshipGroups.keySet().groupBy { it.fromNode }
        nodeTypes.forEach { node ->
            val nodeRelationGroups = relationshipGroupsPerNodeType.get(node) ?: listOf()
            val propSpecs = arrayListOf<NFPropertySpec>()

            if (nodeRelationGroups.isNotEmpty()) {
                nodeRelationGroups.forEach { pairKey ->
                    val groupMembers = relationshipGroups.getOrElse(pairKey, { hashSetOf() })
                    if (groupMembers.isNotEmpty()) {
                        val fullyQualify = groupMembers.size() > 1
                        groupMembers.forEach { trippleKey ->
                            val relateName = if (fullyQualify) "${trippleKey.relationship.name()}.${trippleKey.toNode.name()}" else trippleKey.relationship.name()
                            propSpecs.add(NFPropertySpec(relateName, trippleKey.toNode.name(), relationshipFlags.get(trippleKey) ?: 0))
                        }
                    }
                }
                graphSpec.addNodeSpec(NFNodeSpec(node.name(), *(propSpecs.toTypedArray())))
            }
        }
    }
}

public class GraphBuilderTempStep1<N : Enum<N>, R : Enum<R>>(val fromNodeWithOrd: NodeAndOrd<N>, val completr: (NodeAndOrd<N>, R, NodeAndOrd<N>)->Unit) {
    public fun edge(relation: R): GraphBuilderTempStep2<N,R> {
        return GraphBuilderTempStep2<N, R>(fromNodeWithOrd, relation, completr)
    }
}

public class GraphBuilderTempStep2<N : Enum<N>, R : Enum<R>>(val fromNodeWithOrd: NodeAndOrd<N>, val relation: R, val completr: (NodeAndOrd<N>,R,NodeAndOrd<N>)->Unit) {
    public fun to(toNodeWithOrd: NodeAndOrd<N>): Unit {
        completr(fromNodeWithOrd, relation, toNodeWithOrd)
    }
}


public class GraphBuilder<N : Enum<N>, R : Enum<R>>(val schema: CompiledGraphSchema<N, R>) : GraphOrdinalContainer<N>(false) {
    private val graphBuilder = NFBuildGraph(schema.graphSpec)

    public fun connect(fromNodeWithOrd: NodeAndOrd<N>): GraphBuilderTempStep1<N,R> {
        return GraphBuilderTempStep1<N,R>(fromNodeWithOrd, { f,r,t -> connect(f,r,t) })
    }

    public fun connect(fromNodeWithId: NodeAndId<N>, relation: R, toNodeWithId: NodeAndId<N>) {
        connect(fromNodeWithId.toNord(), relation, toNodeWithId.toNord())
    }

    public fun connect(fromNodeWithOrd: NodeAndOrd<N>, relation: R, toNodeWithOrd: NodeAndOrd<N>) {
        val forward = connectInternal(fromNodeWithOrd, relation, toNodeWithOrd)

        val mirrorRelation = schema.relationshipMirrors.get(forward)
        if (mirrorRelation != null) {
            connectInternal(toNodeWithOrd, mirrorRelation.relationship, fromNodeWithOrd)
        }
    }

    // TODO: support new methods getNodes(), getOrCreateNote(), addConnection(node, spec, ...) which are more efficient
    //       also addConnectionModel returning int can be used to speed things up
    //       also getPropertySpec returning NFPropertySpec can be used in the new add methods
    //       ...  see: https://github.com/Netflix/netflix-graph/commit/2f297cf584bb83361d8ca7f1ecdfe84f11731031
    //       this may not be in release yet, could be 1.6 or later, have to check...

    private fun connectInternal(fromNodeWithOrd: NodeAndOrd<N>, relation: R, toNodeWithOrd: NodeAndOrd<N>): RelationshipTrippleKey<N,R> {
        val pairKey = RelationshipPairKey(fromNodeWithOrd.nodeType, relation)
        val trippleKey = RelationshipTrippleKey(fromNodeWithOrd.nodeType, relation, toNodeWithOrd.nodeType)

        val nodeRelations = schema.relationshipGroups.getOrElse(pairKey, { setOf<RelationshipTrippleKey<N,R>>() })
        if (nodeRelations.isNotEmpty()) {
            val fullyQualify = nodeRelations.size() > 1
            val matchingRelation = nodeRelations.filter { it == trippleKey }.firstOrNull()
            if (matchingRelation != null) {
                val relateName = if (fullyQualify) "${trippleKey.relationship.name()}.${trippleKey.toNode.name()}" else trippleKey.relationship.name()
                graphBuilder.addConnection(matchingRelation.fromNode.name(), fromNodeWithOrd.ord, relateName, toNodeWithOrd.ord)
                return trippleKey
            } else {
                throw RuntimeException("No relationship for ${trippleKey} exists, cannot connect these nodes!")
            }
        } else {
            throw RuntimeException("No relationship for ${pairKey} exists, cannot connect it to anything!")
        }
    }

    public fun serialize(output: OutputStream) {
        DataOutputStream(output).use { dataStream ->

            // Header:
            //   "**KOTLIN-NFGRAPH**"
            //   version as number
            // Partial Schema: (only unidirectional relations, no mirrors)
            //   "**SCHEMA**"
            //   number of nodeType
            //   [foreach nodeType]
            //      - name of nodeType
            //   number of relation groups
            //   [foreach relation group]
            //      - nodeType name
            //      - relation name
            //      - number of targets
            //      [foreach target]
            //         - target nodeType name
            //   number of mirrors
            //   [foreach mirror]
            //      - forward nodeType name
            //      - forward relation name
            //      - forward target nodeType name
            //      - backward nodeType name
            //      - backward relation name
            //      - backward target nodeType name
            //
            // Ordinals:
            //   "**ORDINALS**"
            //   number of ordinal maps
            //   [foreach ordinal map]
            //      - name of nodeType
            //      - count N of ordinals
            //      - 0..N of ordinals
            //
            // Graph:
            //   "**GRAPH**"
            //   compressed graph serialized

            // Header
            dataStream.writeUTF(GRAPH_MARKERS_HEADER)
            dataStream.writeInt(1)

            // Partial Schema:
            dataStream.writeUTF(GRAPH_MARKERS_SCHEMA_HEADER)
            val values = schema.nodeTypes
            dataStream.writeInt(values.size())
            values.forEach { nodeType ->
                dataStream.writeUTF(nodeType.name())
            }
            val groups = schema.relationshipGroups
            dataStream.writeInt(groups.size())
            groups.entrySet().forEach { groupEntry ->
                dataStream.writeUTF(groupEntry.getKey().fromNode.name())
                dataStream.writeUTF(groupEntry.getKey().relationship.name())
                dataStream.writeInt(groupEntry.getValue().size())
                groupEntry.getValue().forEach { target ->
                    dataStream.writeUTF(target.toNode.name())
                }
            }
            val mirrors = schema.relationshipMirrors
            dataStream.writeInt(mirrors.size())
            mirrors.entrySet().forEach { mirrorEntry ->
                dataStream.writeUTF(mirrorEntry.getKey().fromNode.name())
                dataStream.writeUTF(mirrorEntry.getKey().relationship.name())
                dataStream.writeUTF(mirrorEntry.getKey().toNode.name())
                dataStream.writeUTF(mirrorEntry.getValue().fromNode.name())
                dataStream.writeUTF(mirrorEntry.getValue().relationship.name())
                dataStream.writeUTF(mirrorEntry.getValue().toNode.name())
            }

            // Ordinals:
            dataStream.writeUTF(GRAPH_MARKERS_ORDINAL_HEADER)
            dataStream.writeInt(ordinalsByType.size())
            ordinalsByType.forEach { entry ->
                dataStream.writeUTF(entry.getKey().name())
                dataStream.writeInt(entry.getValue().size())
                entry.getValue().asSequence().forEach { ordValue ->
                    dataStream.writeUTF(ordValue)
                }
            }

            // Graph:
            dataStream.writeUTF(GRAPH_MARKERS_GRAPH_HEADER)
            val compressed = graphBuilder.compress()
            compressed.writeTo(dataStream)
        }
    }
}
