[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationPredicateNoBackwards](.)


# GraphRelationPredicateNoBackwards
`class GraphRelationPredicateNoBackwards&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L110)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphRelationPredicateNoBackwards(builder:&nbsp;[GraphRelationBuilder](../-graph-relation-builder/index.md)&lt;N,&nbsp;R&gt;)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [autoMirrorEdges](auto-mirror-edges.md) | `fun autoMirrorEdges(backRelation:&nbsp;R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)&lt;N,&nbsp;R&gt;` |
| [autoMirrorOneEdge](auto-mirror-one-edge.md) | `fun autoMirrorOneEdge(backRelation:&nbsp;R): [GraphRelationPredicateWithBackEdge](../-graph-relation-predicate-with-back-edge/index.md)&lt;N,&nbsp;R&gt;` |
| [compact](compact.md) | `fun compact(): GraphRelationPredicateNoBackwards&lt;N,&nbsp;R&gt;` |
| [globalScope](global-scope.md) | `fun globalScope(): GraphRelationPredicateNoBackwards&lt;N,&nbsp;R&gt;` |
| [hashed](hashed.md) | `fun hashed(): GraphRelationPredicateNoBackwards&lt;N,&nbsp;R&gt;` |
| [modelScope](model-scope.md) | `fun modelScope(): GraphRelationPredicateNoBackwards&lt;N,&nbsp;R&gt;` |
