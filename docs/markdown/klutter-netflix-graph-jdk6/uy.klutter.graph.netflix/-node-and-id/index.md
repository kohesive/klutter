[uy.klutter.graph.netflix](../index.md) / [NodeAndId](.)


# NodeAndId
<code>data class NodeAndId<N : Enum<N>></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L26)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>NodeAndId(nodeType: N, id: String)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [id](id.md) | <code>val id: String</code><br/> |
| [nodeType](node-type.md) | <code>val nodeType: N</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | <code>fun NodeAndId<N>.getConnections(relation: R): Set<NodeAndId<N>></code><br/><code>fun NodeAndId<N>.getConnections(model: String, relation: R): Set<NodeAndId<N>></code><br/> |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | <code>fun NodeAndId<N>.getSingleConnection(relation: R): NodeAndId<N>?</code><br/><code>fun NodeAndId<N>.getSingleConnection(model: String, relation: R): NodeAndId<N>?</code><br/> |
| [toNord](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nord.md) | <code>fun NodeAndId<N>.toNord(): [NodeAndOrd](../-node-and-ord/index.md)<N></code><br/> |
