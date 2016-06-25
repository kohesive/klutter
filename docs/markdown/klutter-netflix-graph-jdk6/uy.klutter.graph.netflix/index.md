[uy.klutter.graph.netflix](.)


## Package uy.klutter.graph.netflix

### Types

|&nbsp;|&nbsp;|
|---|---|
| [GraphRelationOptions](-graph-relation-options/index.md) | <code>interface GraphRelationOptions</code><br/> |
| [NodeAndId](-node-and-id/index.md) | <code>data class NodeAndId<N : Enum<N>></code><br/> |
| [NodeAndOrd](-node-and-ord/index.md) | <code>data class NodeAndOrd<N : Enum<N>></code><br/> |
| [RelationCardinality](-relation-cardinality/index.md) | <code>enum class RelationCardinality : [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |
| [RelationScope](-relation-scope/index.md) | <code>enum class RelationScope : [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |
| [RelationStructure](-relation-structure/index.md) | <code>enum class RelationStructure : [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |
| [TempGraphFlags](-temp-graph-flags/index.md) | <code>class TempGraphFlags : [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [constructGraph](construct-graph.md) | <code>fun <N : Enum<N>, R : Enum<R>> constructGraph(schema: [CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)<N, R>, init: [GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)<N, R>.() -> Unit): [GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)<N, R></code><br/> |
| [defineGraphSchema](define-graph-schema.md) | <code>fun <N : Enum<N>, R : Enum<R>> defineGraphSchema(defaultStructure: [RelationStructure](-relation-structure/index.md), init: [GraphSchemaBuilder](../uy.klutter.graph.netflix.internal/-graph-schema-builder/index.md)<N, R>.() -> Unit): [CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)<N, R></code><br/> |
| [minus](minus.md) | <code>operator fun [GraphRelationOptions](-graph-relation-options/index.md).minus(other: [GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |
| [plus](plus.md) | <code>operator fun [GraphRelationOptions](-graph-relation-options/index.md).plus(other: [GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)</code><br/> |
| [useGraph](use-graph.md) | <code>fun <N : Enum<N>, R : Enum<R>> useGraph(inputStream: [InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html), run: [ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)<N, R>.() -> Unit): [ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)<N, R></code><br/> |
