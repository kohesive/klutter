[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationPredicateNoBackwards](.)


# GraphRelationPredicateNoBackwards
`class GraphRelationPredicateNoBackwards<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L110)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphRelationPredicateNoBackwards(builder:&nbsp;[GraphRelationBuilder](../-graph-relation-builder/index.md)<N,&nbsp;R>)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [autoMirrorEdges](auto-mirror-edges.md) | `fun autoMirrorEdges(backRelation:&nbsp;R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)<N,&nbsp;R>` |
| [autoMirrorOneEdge](auto-mirror-one-edge.md) | `fun autoMirrorOneEdge(backRelation:&nbsp;R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)<N,&nbsp;R>` |
| [compact](compact.md) | `fun compact(): GraphRelationPredicateNoBackwards<N,&nbsp;R>` |
| [globalScope](global-scope.md) | `fun globalScope(): GraphRelationPredicateNoBackwards<N,&nbsp;R>` |
| [hashed](hashed.md) | `fun hashed(): GraphRelationPredicateNoBackwards<N,&nbsp;R>` |
| [modelScope](model-scope.md) | `fun modelScope(): GraphRelationPredicateNoBackwards<N,&nbsp;R>` |
