[uy.klutter.graph.netflix](.)


## Package uy.klutter.graph.netflix

### Types

|&nbsp;|&nbsp;|
|---|---|
| [GraphRelationOptions](-graph-relation-options/index.md) | `interface GraphRelationOptions` |
| [NodeAndId](-node-and-id/index.md) | `data class NodeAndId<N&nbsp;:&nbsp;Enum<N>>` |
| [NodeAndOrd](-node-and-ord/index.md) | `data class NodeAndOrd<N&nbsp;:&nbsp;Enum<N>>` |
| [RelationCardinality](-relation-cardinality/index.md) | `enum class RelationCardinality&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [RelationScope](-relation-scope/index.md) | `enum class RelationScope&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [RelationStructure](-relation-structure/index.md) | `enum class RelationStructure&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [TempGraphFlags](-temp-graph-flags/index.md) | `class TempGraphFlags&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [constructGraph](construct-graph.md) | `fun <N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>> constructGraph(schema:&nbsp;[CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)<N,&nbsp;R>, init:&nbsp;[GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)<N,&nbsp;R>.()&nbsp;->&nbsp;Unit): [GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)<N,&nbsp;R>` |
| [defineGraphSchema](define-graph-schema.md) | `fun <N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>> defineGraphSchema(defaultStructure:&nbsp;[RelationStructure](-relation-structure/index.md), init:&nbsp;[GraphSchemaBuilder](../uy.klutter.graph.netflix.internal/-graph-schema-builder/index.md)<N,&nbsp;R>.()&nbsp;->&nbsp;Unit): [CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)<N,&nbsp;R>` |
| [minus](minus.md) | `operator fun [GraphRelationOptions](-graph-relation-options/index.md).minus(other:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)` |
| [plus](plus.md) | `operator fun [GraphRelationOptions](-graph-relation-options/index.md).plus(other:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)` |
| [useGraph](use-graph.md) | `fun <N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>> useGraph(inputStream:&nbsp;[InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html), run:&nbsp;[ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)<N,&nbsp;R>.()&nbsp;->&nbsp;Unit): [ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)<N,&nbsp;R>` |
