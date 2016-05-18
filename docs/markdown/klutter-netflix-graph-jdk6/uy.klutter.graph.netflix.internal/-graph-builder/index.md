[uy.klutter.graph.netflix.internal](../index.md) / [GraphBuilder](.)


# GraphBuilder

`class GraphBuilder&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;&nbsp;:&nbsp;[GraphOrdinalContainer](../-graph-ordinal-container/index.md)&lt;N&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Building.kt#L100)



### Constructors


| [&lt;init&gt;](-init-.md) | `GraphBuilder(schema:&nbsp;[CompiledGraphSchema](../-compiled-graph-schema/index.md)&lt;N,&nbsp;R&gt;)` |


### Properties


| [schema](schema.md) | `val schema: [CompiledGraphSchema](../-compiled-graph-schema/index.md)&lt;N,&nbsp;R&gt;` |


### Inherited Properties


| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | `val ordinalsByType: MutableMap&lt;N,&nbsp;OrdinalMap&lt;String&gt;&gt;` |


### Functions


| [connect](connect.md) | `fun connect(fromNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;): [GraphBuilderTempStep1](../-graph-builder-temp-step1/index.md)&lt;N,&nbsp;R&gt;`
`fun connect(fromNodeWithId:&nbsp;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;, relation:&nbsp;R, toNodeWithId:&nbsp;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;): Unit`
`fun connect(fromNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R, toNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;): Unit` |
| [serialize](serialize.md) | `fun serialize(output:&nbsp;[OutputStream](http://docs.oracle.com/javase/6/docs/api/java/io/OutputStream.html)): Unit` |

