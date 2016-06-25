[uy.klutter.graph.netflix](../index.md) / [NodeAndId](.)


# NodeAndId
`data class NodeAndId<N&nbsp;:&nbsp;Enum<N>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L26)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `NodeAndId(nodeType:&nbsp;N, id:&nbsp;String)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [id](id.md) | `val id: String` |
| [nodeType](node-type.md) | `val nodeType: N` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | `fun NodeAndId<N>.getConnections(relation:&nbsp;R): Set<NodeAndId<N>>`
`fun NodeAndId<N>.getConnections(model:&nbsp;String, relation:&nbsp;R): Set<NodeAndId<N>>` |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | `fun NodeAndId<N>.getSingleConnection(relation:&nbsp;R): NodeAndId<N>?`
`fun NodeAndId<N>.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): NodeAndId<N>?` |
| [toNord](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nord.md) | `fun NodeAndId<N>.toNord(): [NodeAndOrd](../-node-and-ord/index.md)<N>` |
