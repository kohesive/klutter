[uy.klutter.graph.netflix.internal](../index.md) / [GraphSchemaBuilder](.)


# GraphSchemaBuilder
<code>class GraphSchemaBuilder<N : Enum<N>, R : Enum<R>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L11)<br/>


### Types

|&nbsp;|&nbsp;|
|---|---|
| [GraphScopeModel](-graph-scope-model/index.md) | <code>inner class GraphScopeModel<N : Enum<N>, R : Enum<R>></code><br/> |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphSchemaBuilder(nodeTypeEnum: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<N>, relationTypeEnum: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<R>, defaultStructure: [RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md) = RelationStructure.HASH)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [from](from.md) | <code>fun from(nodeType: N): [GraphRelationBuilder](../-graph-relation-builder/index.md)<N, R></code><br/> |
| [modelScope](model-scope.md) | <code>fun modelScope(init: [GraphScopeModel](-graph-scope-model/index.md)<N, R>.() -> Unit): Unit</code><br/> |
