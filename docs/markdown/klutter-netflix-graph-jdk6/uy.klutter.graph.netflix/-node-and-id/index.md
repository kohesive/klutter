[uy.klutter.graph.netflix](../index.md) / [NodeAndId](.)


# NodeAndId

`data class NodeAndId&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L26)



### Constructors


| [&lt;init&gt;](-init-.md) | `NodeAndId(nodeType:&nbsp;N, id:&nbsp;String)` |


### Properties


| [id](id.md) | `val id: String` |
| [nodeType](node-type.md) | `val nodeType: N` |


### Extension Functions


| [getConnections](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-connections.md) | `fun NodeAndId&lt;N&gt;.getConnections(relation:&nbsp;R): Set&lt;NodeAndId&lt;N&gt;&gt;`
`fun NodeAndId&lt;N&gt;.getConnections(model:&nbsp;String, relation:&nbsp;R): Set&lt;NodeAndId&lt;N&gt;&gt;` |
| [getSingleConnection](../../uy.klutter.graph.netflix.internal/-read-only-graph/get-single-connection.md) | `fun NodeAndId&lt;N&gt;.getSingleConnection(relation:&nbsp;R): NodeAndId&lt;N&gt;?`
`fun NodeAndId&lt;N&gt;.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): NodeAndId&lt;N&gt;?` |
| [toNord](../../uy.klutter.graph.netflix.internal/-graph-ordinal-container/to-nord.md) | `fun NodeAndId&lt;N&gt;.toNord(): [NodeAndOrd](../-node-and-ord/index.md)&lt;N&gt;` |

