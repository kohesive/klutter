[uy.klutter.graph.netflix.internal](../index.md) / [GraphSchemaBuilder](.)


# GraphSchemaBuilder
`class GraphSchemaBuilder<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L11)



### Types

|&nbsp;|&nbsp;|
|---|---|
| [GraphScopeModel](-graph-scope-model/index.md) | `inner class GraphScopeModel<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>` |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphSchemaBuilder(nodeTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<N>, relationTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<R>, defaultStructure:&nbsp;[RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md)&nbsp;=&nbsp;RelationStructure.HASH)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [from](from.md) | `fun from(nodeType:&nbsp;N): [GraphRelationBuilder](../-graph-relation-builder/index.md)<N,&nbsp;R>` |
| [modelScope](model-scope.md) | `fun modelScope(init:&nbsp;[GraphScopeModel](-graph-scope-model/index.md)<N,&nbsp;R>.()&nbsp;->&nbsp;Unit): Unit` |
