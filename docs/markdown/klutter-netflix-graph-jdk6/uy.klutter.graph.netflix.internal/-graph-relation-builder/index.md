[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationBuilder](.)


# GraphRelationBuilder
<code>class GraphRelationBuilder<N : Enum<N>, R : Enum<R>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L75)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphRelationBuilder(relations: MutableList<GraphRelationBuilder<N, R>>, defaultStructure: [RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md), fromNode: N, scopeAs: [RelationScope](../../uy.klutter.graph.netflix/-relation-scope/index.md), modelScopeName: String? = null)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connectEdges](connect-edges.md) | <code>fun connectEdges(relation: R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)<N, R></code><br/> |
| [connectOneEdge](connect-one-edge.md) | <code>fun connectOneEdge(relation: R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)<N, R></code><br/> |
