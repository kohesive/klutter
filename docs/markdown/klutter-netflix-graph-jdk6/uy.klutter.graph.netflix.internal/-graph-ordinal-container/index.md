[uy.klutter.graph.netflix.internal](../index.md) / [GraphOrdinalContainer](.)


# GraphOrdinalContainer
`abstract class GraphOrdinalContainer<N&nbsp;:&nbsp;Enum<N>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Ordinals.kt#L8)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphOrdinalContainer(readOnlyOrdinals:&nbsp;Boolean)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](ordinals-by-type.md) | `val ordinalsByType: MutableMap<N,&nbsp;OrdinalMap<String>>` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [get](get.md) | `operator fun N.get(id:&nbsp;String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>` |
| [invoke](invoke.md) | `operator fun N.invoke(id:&nbsp;String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>` |
| [nodeOrdMap](node-ord-map.md) | `fun nodeOrdMap(nodeType:&nbsp;N): OrdinalMap<String>` |
| [toId](to-id.md) | `fun toId(nodeType:&nbsp;N, ordinal:&nbsp;Int): String` |
| [toNid](to-nid.md) | `fun N.toNid(id:&nbsp;String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>`
`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.toNid(): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>` |
| [toNord](to-nord.md) | `fun N.toNord(id:&nbsp;String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>`
`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.toNord(): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>` |
| [toOrd](to-ord.md) | `fun toOrd(nodeType:&nbsp;N, primaryKey:&nbsp;String): Int` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [GraphBuilder](../-graph-builder/index.md) | `class GraphBuilder<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>&nbsp;:&nbsp;GraphOrdinalContainer<N>` |
| [ReadOnlyGraph](../-read-only-graph/index.md) | `class ReadOnlyGraph<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>&nbsp;:&nbsp;GraphOrdinalContainer<N>` |
