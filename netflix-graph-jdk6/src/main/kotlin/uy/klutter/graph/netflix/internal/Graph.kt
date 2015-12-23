package uy.klutter.graph.netflix.internal

import com.netflix.nfgraph.NFGraph
import com.netflix.nfgraph.OrdinalIterator
import com.netflix.nfgraph.compressed.NFCompressedGraph
import uy.klutter.graph.netflix.*
import java.io.DataInputStream
import java.io.InputStream
import kotlin.collections.AbstractIterator


private fun checkHeaderValue(input: DataInputStream, testValue: String) {
    val actualValue = input.readUTF()
    if (actualValue != testValue) {
        throw RuntimeException("Check header value is not correct: ${testValue} vs. actual ${actualValue}")
    }
}

public class ReadOnlyGraph<N : Enum<N>, R : Enum<R>>(private val nodeTypeEnum: Class<N>, private val relationTypeEnum: Class<R>, private val input: InputStream) : GraphOrdinalContainer<N>(true) {
    @Suppress("EXPOSED_PROPERTY_TYPE")
    protected val relationshipGroups: MutableMap<RelationshipPairKey<N, R>, MutableSet<RelationshipTrippleKey<N, R>>> = hashMapOf()
    @Suppress("EXPOSED_PROPERTY_TYPE")
    protected val relationshipMirrors: MutableMap<RelationshipTrippleKey<N, R>, RelationshipTrippleKey<N, R>> = hashMapOf()
    internal val nodeTypes: Map<String,N> = nodeTypeEnum.getEnumConstants().map { Pair(it.name,it) }.toMap()
    internal val relationTypes: Map<String,R> = relationTypeEnum.getEnumConstants().map { Pair(it.name,it) }.toMap()

    protected val graph: NFGraph = run {
        DataInputStream(input).use { dataStream ->
            // Header:
            checkHeaderValue(dataStream, GRAPH_MARKERS_HEADER)
            val version = dataStream.readInt() // TODO: if we need to check it for differences later

            // Partial Schema:
            checkHeaderValue(dataStream, GRAPH_MARKERS_SCHEMA_HEADER)
            val tempRelations = hashMapOf<Pair<String, String>, String>()
            val tempNodeTypes = hashSetOf<String>()

            val numberOfNodeTypes = dataStream.readInt()
            for (i in 1..numberOfNodeTypes) {
                val nodeTypeName = dataStream.readUTF()
            }

            val numberOfGroups = dataStream.readInt()
            for (i in 1..numberOfGroups) {
                val fromNodeType = dataStream.readUTF()
                val relationType = dataStream.readUTF()
                val numberOfTargets = dataStream.readInt()
                val pairKey = RelationshipPairKey(nodeTypes.get(fromNodeType)!!, relationTypes.get(relationType)!!)     // TODO: could fail, better error message?
                val group = relationshipGroups.getOrPut(pairKey, { hashSetOf() })
                for (x in 1..numberOfTargets) {
                    val toNodeType = dataStream.readUTF()
                    val trippleKey = RelationshipTrippleKey(pairKey.fromNode, pairKey.relationship, nodeTypes.get(toNodeType)!!)
                    group.add(trippleKey)
                }
            }

            val numberOfMirrors = dataStream.readInt()
            for (i in 1..numberOfMirrors) {
                val forward_fromNodeType = dataStream.readUTF()
                val forward_relationType = dataStream.readUTF()
                val forward_toNodeType = dataStream.readUTF()
                val backward_fromNodeType = dataStream.readUTF()
                val backward_relationType = dataStream.readUTF()
                val backward_toNodeType = dataStream.readUTF()
                val forwardTrippleKey = RelationshipTrippleKey(nodeTypes.get(forward_fromNodeType)!!, relationTypes.get(forward_relationType)!!, nodeTypes.get(forward_toNodeType)!!)
                val backwardTrippleKey = RelationshipTrippleKey(nodeTypes.get(backward_fromNodeType)!!, relationTypes.get(backward_relationType)!!, nodeTypes.get(backward_toNodeType)!!)
                relationshipMirrors.put(forwardTrippleKey, backwardTrippleKey)
            }

            // Ordinals:
            checkHeaderValue(dataStream, GRAPH_MARKERS_ORDINAL_HEADER)
            val numberOfOrdinalMaps = dataStream.readInt()
            for (i in 1..numberOfOrdinalMaps) {
                val nodeTypeName = dataStream.readUTF()
                val nodeOrdinalCount = dataStream.readInt()
                val nodeOrdMap = nodeOrdMap(nodeTypes.get(nodeTypeName)!!)
                for (x in 1..nodeOrdinalCount) {
                    val oneValue = dataStream.readUTF()
                    nodeOrdMap.add(oneValue)
                }
            }

            // Graph:
            checkHeaderValue(dataStream, GRAPH_MARKERS_GRAPH_HEADER)
            NFCompressedGraph.readFrom(dataStream)
        }
    }



    public class OrdinalIterable(private val iterator: OrdinalIterator) : Iterable<Int> {
        override fun iterator(): Iterator<Int> {
            return OrdinalIteration(iterator)
        }
    }

    public class OrdinalIteration(private val iterator: OrdinalIterator) : AbstractIterator<Int>() {
        override fun computeNext() {
            val nextVal = iterator.nextOrdinal()
            if (nextVal != OrdinalIterator.NO_MORE_ORDINALS) {
                this.setNext(nextVal)
            } else {
                this.done()
            }
        }
    }

    // get connections from node A to all others
    // get connections from node A of relationship R
    // get connections from node A to B
    // get connectinos from node A to B of relationship R
    // does A have a connection type R
    // does A have a connection type R to B?
    // does A connect to B in any way

    // TODO: all for connection model again :-(

    protected fun getRelationTargets(nodeType: N, relation: R): List<N> {
        return relationshipGroups.getOrElse(RelationshipPairKey(nodeType,relation), { setOf<RelationshipTrippleKey<N,R>>() }).map { it.toNode }
    }

    protected fun makeRelationshipName(relation: R, toNodeType: N): String {
        return "${relation.name}.${toNodeType.name}"
    }

    protected fun NFGraph.getConnection(nodeAndOrd: NodeAndOrd<N>, relation: R): NodeAndId<N>? {
        return getConnection("", nodeAndOrd, relation)
    }

    protected fun NFGraph.getConnection(model: String, nodeAndOrd: NodeAndOrd<N>, relation: R): NodeAndId<N>? {
        val targets = getRelationTargets(nodeAndOrd.nodeType, relation)
        val qualifyFully = targets.size > 1
        val results = linkedListOf<NodeAndId<N>>()
        targets.forEach { toNodeType ->
            val ordsForTarget = nodeOrdMap(toNodeType)
            val relate = if (qualifyFully) makeRelationshipName(relation, toNodeType) else relation.name
            val ord = if (model.isNotEmpty()) {
                getConnection(nodeAndOrd.nodeType.name, nodeAndOrd.ord, relate)
            } else {
                getConnection(model, nodeAndOrd.nodeType.name, nodeAndOrd.ord, relate)
            }
            if (ord >= 0) {
                results.add(NodeAndId(toNodeType, ordsForTarget.get(ord)))
            }
        }
        if (results.size > 1) {
            throw RuntimeException("Tried to get connections from graph node as single, but multiple existed instead: from ${nodeAndOrd} via ${relation} resulted in ${results.joinToString(",")}")
        }
        return results.firstOrNull()
    }

    protected fun NFGraph.getConnectionSet(nodeAndOrd: NodeAndOrd<N>, relation: R): Set<NodeAndId<N>> {
        return getConnectionSet("", nodeAndOrd, relation)
    }

    protected fun NFGraph.getConnectionSet(model: String, nodeAndOrd: NodeAndOrd<N>, relation: R): Set<NodeAndId<N>> {
        val targets = getRelationTargets(nodeAndOrd.nodeType, relation)
        val qualifyFully = targets.size > 1
        val results = linkedListOf<NodeAndId<N>>()
        targets.forEach { toNodeType ->
            val ordsForTarget = nodeOrdMap(toNodeType)
            val relate = if (qualifyFully) makeRelationshipName(relation, toNodeType) else relation.name
            val ordIterable = if (model.isEmpty()) {
                connectionsAsIterable(nodeAndOrd, relate)
            } else {
                connectionsAsIterable(model, nodeAndOrd, relate)
            }
            ordIterable.forEach { ord ->
                results.add(NodeAndId(toNodeType, ordsForTarget.get(ord)))
            }
        }
        return results.toSet()
    }


    public fun NodeAndOrd<N>.getSingleConnection(relation: R): NodeAndId<N>? {
        return graph.getConnection(this, relation)
    }

    public fun NodeAndOrd<N>.getSingleConnection(model: String, relation: R): NodeAndId<N>? {
        return graph.getConnection(model, this, relation)
    }

    public fun NodeAndOrd<N>.getConnections(relation: R): Set<NodeAndId<N>> {
        return graph.getConnectionSet(this, relation)
    }

    public fun NodeAndOrd<N>.getConnections(model: String, relation: R): Set<NodeAndId<N>>  {
        return graph.getConnectionSet(model, this, relation)
    }

    public fun NodeAndId<N>.getSingleConnection(relation: R): NodeAndId<N>? {
        return graph.getConnection(this.toNord(), relation)
    }

    public fun NodeAndId<N>.getSingleConnection(model: String, relation: R): NodeAndId<N>? {
        return graph.getConnection(model, this.toNord(), relation)
    }

    public fun NodeAndId<N>.getConnections(relation: R): Set<NodeAndId<N>> {
        return graph.getConnectionSet(this.toNord(), relation)
    }

    public fun NodeAndId<N>.getConnections(model: String, relation: R): Set<NodeAndId<N>>  {
        return graph.getConnectionSet(model, this.toNord(), relation)
    }

    // TODO: for this as well public fun NodeAndOrdSet<N>.

    // TODO: for this as well public fun NodeAndOrdList<N>.

    protected fun NFGraph.connectionsAsIterable(nodeAndOrd: NodeAndOrd<N>, relation: R): OrdinalIterable = OrdinalIterable(this.getConnectionIterator(nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name))
    protected fun NFGraph.connectionsAsIterable(model: String, nodeAndOrd: NodeAndOrd<N>, relation: R): OrdinalIterable = OrdinalIterable(this.getConnectionIterator(model, nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name))
    protected fun NFGraph.connectionsAsIterable(nodeAndOrd: NodeAndOrd<N>, relationName: String): OrdinalIterable {
        return OrdinalIterable(this.getConnectionIterator(nodeAndOrd.nodeType.name, nodeAndOrd.ord, relationName))
    }
    protected fun NFGraph.connectionsAsIterable(model: String, nodeAndOrd: NodeAndOrd<N>, relationName: String): OrdinalIterable = OrdinalIterable(this.getConnectionIterator(model, nodeAndOrd.nodeType.name, nodeAndOrd.ord, relationName))


    protected fun NFGraph.connectionsAsIterator(nodeAndOrd: NodeAndOrd<N>, relation: R): OrdinalIteration = OrdinalIteration(this.getConnectionIterator(nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name))
    protected fun NFGraph.connectionsAsIterator(model: String, nodeAndOrd: NodeAndOrd<N>, relation: R): OrdinalIteration = OrdinalIteration(this.getConnectionIterator(model, nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name))

    protected fun NFGraph.connectionsAsSet(nodeAndOrd: NodeAndOrd<N>, relation: R): Set<Int> = OrdinalIterable(this.getConnectionIterator(nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name)).toSet()
    protected fun NFGraph.connectionsAsSet(model: String, nodeAndOrd: NodeAndOrd<N>, relation: R): Set<Int> = OrdinalIterable(this.getConnectionIterator(model, nodeAndOrd.nodeType.name, nodeAndOrd.ord, relation.name)).toSet()

}

