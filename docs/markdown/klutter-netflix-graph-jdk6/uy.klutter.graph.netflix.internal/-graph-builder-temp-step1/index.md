[uy.klutter.graph.netflix.internal](../index.md) / [GraphBuilderTempStep1](.)


# GraphBuilderTempStep1
<code>class GraphBuilderTempStep1<N : Enum<N>, R : Enum<R>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Building.kt#L87)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphBuilderTempStep1(fromNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, completr: ([NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, R, [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>) -> Unit)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [completr](completr.md) | <code>val completr: ([NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, R, [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>) -> Unit</code><br/> |
| [fromNodeWithOrd](from-node-with-ord.md) | <code>val fromNodeWithOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N></code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [edge](edge.md) | <code>fun edge(relation: R): [GraphBuilderTempStep2](../-graph-builder-temp-step2/index.md)<N, R></code><br/> |
