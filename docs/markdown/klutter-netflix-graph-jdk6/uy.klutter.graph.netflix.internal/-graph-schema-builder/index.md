[uy.klutter.graph.netflix.internal](../index.md) / [GraphSchemaBuilder](.)


# GraphSchemaBuilder

`class GraphSchemaBuilder&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L11)



### Types


| [GraphScopeModel](-graph-scope-model/index.md) | `inner class GraphScopeModel&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;` |


### Constructors


| [&lt;init&gt;](-init-.md) | `GraphSchemaBuilder(nodeTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;N&gt;, relationTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;R&gt;, defaultStructure:&nbsp;[RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md)&nbsp;=&nbsp;RelationStructure.HASH)` |


### Functions


| [from](from.md) | `fun from(nodeType:&nbsp;N): [GraphRelationBuilder](../-graph-relation-builder/index.md)&lt;N,&nbsp;R&gt;` |
| [modelScope](model-scope.md) | `fun modelScope(init:&nbsp;[GraphScopeModel](-graph-scope-model/index.md)&lt;N,&nbsp;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |

