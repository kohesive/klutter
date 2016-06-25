[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationPredicateNoBackwards](.)


# GraphRelationPredicateNoBackwards
<code>class GraphRelationPredicateNoBackwards<N : Enum<N>, R : Enum<R>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L110)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphRelationPredicateNoBackwards(builder: [GraphRelationBuilder](../-graph-relation-builder/index.md)<N, R>)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [autoMirrorEdges](auto-mirror-edges.md) | <code>fun autoMirrorEdges(backRelation: R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)<N, R></code><br/> |
| [autoMirrorOneEdge](auto-mirror-one-edge.md) | <code>fun autoMirrorOneEdge(backRelation: R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)<N, R></code><br/> |
| [compact](compact.md) | <code>fun compact(): GraphRelationPredicateNoBackwards<N, R></code><br/> |
| [globalScope](global-scope.md) | <code>fun globalScope(): GraphRelationPredicateNoBackwards<N, R></code><br/> |
| [hashed](hashed.md) | <code>fun hashed(): GraphRelationPredicateNoBackwards<N, R></code><br/> |
| [modelScope](model-scope.md) | <code>fun modelScope(): GraphRelationPredicateNoBackwards<N, R></code><br/> |
