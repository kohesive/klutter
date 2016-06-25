[uy.klutter.graph.netflix.internal](../index.md) / [GraphBuilderTempStep1](.)


# GraphBuilderTempStep1
`class GraphBuilderTempStep1<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Building.kt#L87)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphBuilderTempStep1(fromNodeWithOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, completr:&nbsp;([NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>,&nbsp;R,&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>)&nbsp;->&nbsp;Unit)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [completr](completr.md) | `val completr: ([NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>,&nbsp;R,&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>)&nbsp;->&nbsp;Unit` |
| [fromNodeWithOrd](from-node-with-ord.md) | `val fromNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [edge](edge.md) | `fun edge(relation:&nbsp;R): [GraphBuilderTempStep2](../-graph-builder-temp-step2/index.md)<N,&nbsp;R>` |
