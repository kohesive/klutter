[uy.klutter.graph.netflix](../index.md) / [NodeAndOrd](.)


# NodeAndOrd
<code>data class NodeAndOrd<N : Enum<N>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L25)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>NodeAndOrd(nodeType: N, ord: Int)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [nodeType](node-type.md) | <code>val nodeType: N</code><br/> |
| [ord](ord.md) | <code>val ord: Int</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | <code>fun NodeAndOrd<N>.getConnections(relation: R): Set<[NodeAndId](../-node-and-id/index.md)<N>></code><br/><code>fun NodeAndOrd<N>.getConnections(model: String, relation: R): Set<[NodeAndId](../-node-and-id/index.md)<N>></code><br/> |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | <code>fun NodeAndOrd<N>.getSingleConnection(relation: R): [NodeAndId](../-node-and-id/index.md)<N>?</code><br/><code>fun NodeAndOrd<N>.getSingleConnection(model: String, relation: R): [NodeAndId](../-node-and-id/index.md)<N>?</code><br/> |
| [toNid](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nid.md) | <code>fun NodeAndOrd<N>.toNid(): [NodeAndId](../-node-and-id/index.md)<N></code><br/> |
