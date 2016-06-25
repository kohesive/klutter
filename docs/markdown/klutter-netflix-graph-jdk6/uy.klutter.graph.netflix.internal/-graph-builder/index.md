[uy.klutter.graph.netflix.internal](../index.md) / [GraphBuilder](.)


# GraphBuilder
`class GraphBuilder<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>&nbsp;:&nbsp;[GraphOrdinalContainer](../-graph-ordinal-container/index.md)<N>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Building.kt#L100)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphBuilder(schema:&nbsp;[CompiledGraphSchema](../-compiled-graph-schema/index.md)<N,&nbsp;R>)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [schema](schema.md) | `val schema: [CompiledGraphSchema](../-compiled-graph-schema/index.md)<N,&nbsp;R>` |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | `val ordinalsByType: MutableMap<N,&nbsp;OrdinalMap<String>>` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connect](connect.md) | `fun connect(fromNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>): [GraphBuilderTempStep1](../-graph-builder-temp-step1/index.md)<N,&nbsp;R>`
`fun connect(fromNodeWithId:&nbsp;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>, relation:&nbsp;R, toNodeWithId:&nbsp;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>): Unit`
`fun connect(fromNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R, toNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>): Unit` |
| [serialize](serialize.md) | `fun serialize(output:&nbsp;[OutputStream](http://docs.oracle.com/javase/6/docs/api/java/io/OutputStream.html)): Unit` |
