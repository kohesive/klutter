[uy.klutter.graph.netflix](../index.md) / [NodeAndOrd](.)


# NodeAndOrd
`data class NodeAndOrd&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L25)



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
| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | `fun NodeAndOrd&lt;N&gt;.getConnections(relation:&nbsp;R): Set&lt;[NodeAndId](../-node-and-id/index.md)&lt;N&gt;&gt;`<br/>`fun NodeAndOrd&lt;N&gt;.getConnections(model:&nbsp;String, relation:&nbsp;R): Set&lt;[NodeAndId](../-node-and-id/index.md)&lt;N&gt;&gt;` |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | `fun NodeAndOrd&lt;N&gt;.getSingleConnection(relation:&nbsp;R): [NodeAndId](../-node-and-id/index.md)&lt;N&gt;?`<br/>`fun NodeAndOrd&lt;N&gt;.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../-node-and-id/index.md)&lt;N&gt;?` |
| [toNid](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nid.md) | `fun NodeAndOrd&lt;N&gt;.toNid(): [NodeAndId](../-node-and-id/index.md)&lt;N&gt;` |
