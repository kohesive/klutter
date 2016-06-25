[uy.klutter.graph.netflix.internal](../index.md) / [GraphOrdinalContainer](.)


# GraphOrdinalContainer
`abstract class GraphOrdinalContainer&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Ordinals.kt#L8)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `GraphOrdinalContainer(readOnlyOrdinals:&nbsp;Boolean)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](ordinals-by-type.md) | `val ordinalsByType: MutableMap&lt;N,&nbsp;OrdinalMap&lt;String&gt;&gt;` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [get](get.md) | `operator fun N.get(id:&nbsp;String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;` |
| [invoke](invoke.md) | `operator fun N.invoke(id:&nbsp;String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;` |
| [nodeOrdMap](node-ord-map.md) | `fun nodeOrdMap(nodeType:&nbsp;N): OrdinalMap&lt;String&gt;` |
| [toId](to-id.md) | `fun toId(nodeType:&nbsp;N, ordinal:&nbsp;Int): String` |
| [toNid](to-nid.md) | `fun N.toNid(id:&nbsp;String): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;`<br/>`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;.toNid(): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;` |
| [toNord](to-nord.md) | `fun N.toNord(id:&nbsp;String): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;`<br/>`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;.toNord(): [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;` |
| [toOrd](to-ord.md) | `fun toOrd(nodeType:&nbsp;N, primaryKey:&nbsp;String): Int` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [GraphBuilder](../-graph-builder/index.md) | `class GraphBuilder&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;&nbsp;:&nbsp;GraphOrdinalContainer&lt;N&gt;` |
| [ReadOnlyGraph](../-read-only-graph/index.md) | `class ReadOnlyGraph&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;&nbsp;:&nbsp;GraphOrdinalContainer&lt;N&gt;` |
