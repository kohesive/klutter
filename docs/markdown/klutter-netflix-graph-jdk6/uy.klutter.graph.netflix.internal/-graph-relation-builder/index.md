[uy.klutter.graph.netflix.internal](../index.md) / [GraphRelationBuilder](.)


# GraphRelationBuilder

`class GraphRelationBuilder&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Schema.kt#L75)



### Constructors


| [&lt;init&gt;](-init-.md) | `GraphRelationBuilder(relations:&nbsp;MutableList&lt;GraphRelationBuilder&lt;N,&nbsp;R&gt;&gt;, defaultStructure:&nbsp;[RelationStructure](../../uy.klutter.graph.netflix/-relation-structure/index.md), fromNode:&nbsp;N, scopeAs:&nbsp;[RelationScope](../../uy.klutter.graph.netflix/-relation-scope/index.md), modelScopeName:&nbsp;String?&nbsp;=&nbsp;null)` |


### Functions


| [connectEdges](connect-edges.md) | `fun connectEdges(relation:&nbsp;R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)&lt;N,&nbsp;R&gt;` |
| [connectOneEdge](connect-one-edge.md) | `fun connectOneEdge(relation:&nbsp;R): [GraphRelationPredicateEdge](../-graph-relation-predicate-edge/index.md)&lt;N,&nbsp;R&gt;` |

