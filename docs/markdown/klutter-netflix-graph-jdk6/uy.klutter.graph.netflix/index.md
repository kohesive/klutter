[uy.klutter.graph.netflix](.)


## Package uy.klutter.graph.netflix

### Types

|&nbsp;|&nbsp;|
|---|---|
| [GraphRelationOptions](-graph-relation-options/index.md) | `interface GraphRelationOptions` |
| [NodeAndId](-node-and-id/index.md) | `data class NodeAndId&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;&gt;` |
| [NodeAndOrd](-node-and-ord/index.md) | `data class NodeAndOrd&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;&gt;` |
| [RelationCardinality](-relation-cardinality/index.md) | `enum class RelationCardinality&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [RelationScope](-relation-scope/index.md) | `enum class RelationScope&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [RelationStructure](-relation-structure/index.md) | `enum class RelationStructure&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |
| [TempGraphFlags](-temp-graph-flags/index.md) | `class TempGraphFlags&nbsp;:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [constructGraph](construct-graph.md) | `fun &lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; constructGraph(schema:&nbsp;[CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)&lt;N,&nbsp;R&gt;, init:&nbsp;[GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)&lt;N,&nbsp;R&gt;.()&nbsp;-&gt;&nbsp;Unit): [GraphBuilder](../uy.klutter.graph.netflix.internal/-graph-builder/index.md)&lt;N,&nbsp;R&gt;` |
| [defineGraphSchema](define-graph-schema.md) | `fun &lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; defineGraphSchema(defaultStructure:&nbsp;[RelationStructure](-relation-structure/index.md), init:&nbsp;[GraphSchemaBuilder](../uy.klutter.graph.netflix.internal/-graph-schema-builder/index.md)&lt;N,&nbsp;R&gt;.()&nbsp;-&gt;&nbsp;Unit): [CompiledGraphSchema](../uy.klutter.graph.netflix.internal/-compiled-graph-schema/index.md)&lt;N,&nbsp;R&gt;` |
| [minus](minus.md) | `operator fun [GraphRelationOptions](-graph-relation-options/index.md).minus(other:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)` |
| [plus](plus.md) | `operator fun [GraphRelationOptions](-graph-relation-options/index.md).plus(other:&nbsp;[GraphRelationOptions](-graph-relation-options/index.md)): [GraphRelationOptions](-graph-relation-options/index.md)` |
| [useGraph](use-graph.md) | `fun &lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; useGraph(inputStream:&nbsp;[InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html), run:&nbsp;[ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)&lt;N,&nbsp;R&gt;.()&nbsp;-&gt;&nbsp;Unit): [ReadOnlyGraph](../uy.klutter.graph.netflix.internal/-read-only-graph/index.md)&lt;N,&nbsp;R&gt;` |
