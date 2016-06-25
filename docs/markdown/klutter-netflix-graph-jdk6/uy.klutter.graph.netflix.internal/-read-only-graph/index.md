[uy.klutter.graph.netflix.internal](../index.md) / [ReadOnlyGraph](.)


# ReadOnlyGraph
`class ReadOnlyGraph&lt;N&nbsp;:&nbsp;Enum&lt;N&gt;, R&nbsp;:&nbsp;Enum&lt;R&gt;&gt;&nbsp;:&nbsp;[GraphOrdinalContainer](../-graph-ordinal-container/index.md)&lt;N&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Graph.kt#L20)



### Types

|&nbsp;|&nbsp;|
|---|---|
| [OrdinalIterable](-ordinal-iterable/index.md) | `class OrdinalIterable&nbsp;:&nbsp;Iterable&lt;Int&gt;` |
| [OrdinalIteration](-ordinal-iteration/index.md) | `class OrdinalIteration` |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyGraph(nodeTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;N&gt;, relationTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;R&gt;, input:&nbsp;[InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html))` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [graph](graph.md) | `val graph: NFGraph` |
| [relationshipGroups](relationship-groups.md) | `val relationshipGroups: MutableMap&lt;RelationshipPairKey&lt;N,&nbsp;R&gt;,&nbsp;MutableSet&lt;RelationshipTrippleKey&lt;N,&nbsp;R&gt;&gt;&gt;` |
| [relationshipMirrors](relationship-mirrors.md) | `val relationshipMirrors: MutableMap&lt;RelationshipTrippleKey&lt;N,&nbsp;R&gt;,&nbsp;RelationshipTrippleKey&lt;N,&nbsp;R&gt;&gt;` |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | `val ordinalsByType: MutableMap&lt;N,&nbsp;OrdinalMap&lt;String&gt;&gt;` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connectionsAsIterable](connections-as-iterable.md) | `fun NFGraph.connectionsAsIterable(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [OrdinalIterable](-ordinal-iterable/index.md)`<br/>`fun NFGraph.connectionsAsIterable(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [OrdinalIterable](-ordinal-iterable/index.md)`<br/>`fun NFGraph.connectionsAsIterable(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relationName:&nbsp;String): [OrdinalIterable](-ordinal-iterable/index.md)`<br/>`fun NFGraph.connectionsAsIterable(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relationName:&nbsp;String): [OrdinalIterable](-ordinal-iterable/index.md)` |
| [connectionsAsIterator](connections-as-iterator.md) | `fun NFGraph.connectionsAsIterator(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [OrdinalIteration](-ordinal-iteration/index.md)`<br/>`fun NFGraph.connectionsAsIterator(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [OrdinalIteration](-ordinal-iteration/index.md)` |
| [connectionsAsSet](connections-as-set.md) | `fun NFGraph.connectionsAsSet(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): Set&lt;Int&gt;`<br/>`fun NFGraph.connectionsAsSet(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): Set&lt;Int&gt;` |
| [getConnection](get-connection.md) | `fun NFGraph.getConnection(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?`<br/>`fun NFGraph.getConnection(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?` |
| [getConnectionSet](get-connection-set.md) | `fun NFGraph.getConnectionSet(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;`<br/>`fun NFGraph.getConnectionSet(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;, relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;` |
| [getConnections](get-connections.md) | `fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;.getConnections(relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;`<br/>`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;.getConnections(model:&nbsp;String, relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;`<br/>`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;.getConnections(relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;`<br/>`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;.getConnections(model:&nbsp;String, relation:&nbsp;R): Set&lt;[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;&gt;` |
| [getRelationTargets](get-relation-targets.md) | `fun getRelationTargets(nodeType:&nbsp;N, relation:&nbsp;R): List&lt;N&gt;` |
| [getSingleConnection](get-single-connection.md) | `fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;.getSingleConnection(relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?`<br/>`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)&lt;N&gt;.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?`<br/>`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;.getSingleConnection(relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?`<br/>`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)&lt;N&gt;?` |
| [makeRelationshipName](make-relationship-name.md) | `fun makeRelationshipName(relation:&nbsp;R, toNodeType:&nbsp;N): String` |
