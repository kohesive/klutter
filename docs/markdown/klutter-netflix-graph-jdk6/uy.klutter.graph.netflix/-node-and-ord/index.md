[uy.klutter.graph.netflix](../index.md) / [NodeAndOrd](.)


# NodeAndOrd
`data class NodeAndOrd<N&nbsp;:&nbsp;Enum<N>>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L25)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `NodeAndOrd(nodeType:&nbsp;N, ord:&nbsp;Int)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [nodeType](node-type.md) | `val nodeType: N` |
| [ord](ord.md) | `val ord: Int` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | `fun NodeAndOrd<N>.getConnections(relation:&nbsp;R): Set<[NodeAndId](../-node-and-id/index.md)<N>>`
`fun NodeAndOrd<N>.getConnections(model:&nbsp;String, relation:&nbsp;R): Set<[NodeAndId](../-node-and-id/index.md)<N>>` |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | `fun NodeAndOrd<N>.getSingleConnection(relation:&nbsp;R): [NodeAndId](../-node-and-id/index.md)<N>?`
`fun NodeAndOrd<N>.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../-node-and-id/index.md)<N>?` |
| [toNid](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nid.md) | `fun NodeAndOrd<N>.toNid(): [NodeAndId](../-node-and-id/index.md)<N>` |
