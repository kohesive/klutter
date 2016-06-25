[uy.klutter.graph.netflix.internal](../index.md) / [GraphOrdinalContainer](.)


# GraphOrdinalContainer
<code>abstract class GraphOrdinalContainer<N : Enum<N>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Ordinals.kt#L8)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>GraphOrdinalContainer(readOnlyOrdinals: Boolean)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](ordinals-by-type.md) | <code>val ordinalsByType: MutableMap<N, OrdinalMap<String>></code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [get](get.md) | <code>operator fun N.get(id: String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N></code><br/> |
| [invoke](invoke.md) | <code>operator fun N.invoke(id: String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N></code><br/> |
| [nodeOrdMap](node-ord-map.md) | <code>fun nodeOrdMap(nodeType: N): OrdinalMap<String></code><br/> |
| [toId](to-id.md) | <code>fun toId(nodeType: N, ordinal: Int): String</code><br/> |
| [toNid](to-nid.md) | <code>fun N.toNid(id: String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N></code><br/><code>fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.toNid(): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N></code><br/> |
| [toNord](to-nord.md) | <code>fun N.toNord(id: String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N></code><br/><code>fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.toNord(): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N></code><br/> |
| [toOrd](to-ord.md) | <code>fun toOrd(nodeType: N, primaryKey: String): Int</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [GraphBuilder](../-graph-builder/index.md) | <code>class GraphBuilder<N : Enum<N>, R : Enum<R>> : GraphOrdinalContainer<N></code><br/> |
| [ReadOnlyGraph](../-read-only-graph/index.md) | <code>class ReadOnlyGraph<N : Enum<N>, R : Enum<R>> : GraphOrdinalContainer<N></code><br/> |
