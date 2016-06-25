[uy.klutter.graph.netflix.internal](../index.md) / [GraphBuilder](.)


# GraphBuilder
<code>class GraphBuilder<N : Enum<N>, R : Enum<R>> : [GraphOrdinalContainer](../-graph-ordinal-container/index.md)<N></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Building.kt#L100)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphBuilder(schema: [CompiledGraphSchema](../-compiled-graph-schema/index.md)<N, R>)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [schema](schema.md) | <code>val schema: [CompiledGraphSchema](../-compiled-graph-schema/index.md)<N, R></code><br/> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | <code>val ordinalsByType: MutableMap<N, OrdinalMap<String>></code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connect](connect.md) | <code>fun connect(fromNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>): [GraphBuilderTempStep1](../-graph-builder-temp-step1/index.md)<N, R></code><br/><code>fun connect(fromNodeWithId: [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>, relation: R, toNodeWithId: [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>): Unit</code><br/><code>fun connect(fromNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R, toNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>): Unit</code><br/> |
| [serialize](serialize.md) | <code>fun serialize(output: [OutputStream](http://docs.oracle.com/javase/6/docs/api/java/io/OutputStream.html)): Unit</code><br/> |
