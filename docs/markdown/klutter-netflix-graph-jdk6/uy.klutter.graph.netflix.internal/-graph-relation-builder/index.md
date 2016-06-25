[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationBuilder](.)


# GraphRelationBuilder
`class GraphRelationBuilder<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L75)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphRelationBuilder(relations:&nbsp;MutableList<GraphRelationBuilder<N,&nbsp;R>>, defaultStructure:&nbsp;[RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md), fromNode:&nbsp;N, scopeAs:&nbsp;[RelationScope](../../uy.klutter.graph.netflix/-relation-scope/index.md), modelScopeName:&nbsp;String?&nbsp;=&nbsp;null)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connectEdges](connect-edges.md) | `fun connectEdges(relation:&nbsp;R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)<N,&nbsp;R>` |
| [connectOneEdge](connect-one-edge.md) | `fun connectOneEdge(relation:&nbsp;R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)<N,&nbsp;R>` |
