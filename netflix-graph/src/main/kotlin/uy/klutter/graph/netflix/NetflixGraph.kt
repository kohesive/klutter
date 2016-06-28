package uy.klutter.graph.netflix

import uy.klutter.graph.netflix.internal.*
import java.io.InputStream


inline fun <reified N : Enum<N>, reified R : Enum<R>> defineGraphSchema(defaultStructure: RelationStructure, init: GraphSchemaBuilder<N, R>.() -> Unit): CompiledGraphSchema<N, R> {
    val schema = GraphSchemaBuilder(N::class.java, R::class.java, defaultStructure)
    schema.init()
    return CompiledGraphSchema(schema)
}

fun <N : Enum<N>, R : Enum<R>> constructGraph(schema: CompiledGraphSchema<N, R>, init: GraphBuilder<N, R>.() -> Unit): GraphBuilder<N,R> {
    val builder = GraphBuilder(schema)
    builder.init()
    return builder
}

inline fun <reified N : Enum<N>, reified R : Enum<R>> useGraph(inputStream: InputStream, run: ReadOnlyGraph<N, R>.() -> Unit): ReadOnlyGraph<N, R> {
    val graph = ReadOnlyGraph<N,R>(N::class.java, R::class.java, inputStream)
    graph.run()
    return graph
}

data class NodeAndOrd<N : Enum<N>>(val nodeType: N, val ord: Int)
data class NodeAndId<N : Enum<N>>(val nodeType: N, val id: String)

enum class RelationCardinality(override val flags: Int) : GraphRelationOptions {
    SINGLE(com.netflix.nfgraph.spec.NFPropertySpec.SINGLE),
    MULTIPLE(com.netflix.nfgraph.spec.NFPropertySpec.MULTIPLE)
}

enum class RelationScope(override val flags: Int) : GraphRelationOptions {
    GLOBAL(com.netflix.nfgraph.spec.NFPropertySpec.GLOBAL),
    MODEL(com.netflix.nfgraph.spec.NFPropertySpec.MODEL_SPECIFIC)
}

enum class RelationStructure(override val flags: Int) : GraphRelationOptions {
    COMPACT(com.netflix.nfgraph.spec.NFPropertySpec.COMPACT),
    HASH(com.netflix.nfgraph.spec.NFPropertySpec.HASH)
}

interface GraphRelationOptions {
    val flags: Int
}

operator fun GraphRelationOptions.plus(other: GraphRelationOptions): GraphRelationOptions = TempGraphFlags(this.flags or other.flags)
operator fun GraphRelationOptions.minus(other: GraphRelationOptions): GraphRelationOptions = TempGraphFlags(this.flags and other.flags.inv())
class TempGraphFlags(override val flags: Int) : GraphRelationOptions

