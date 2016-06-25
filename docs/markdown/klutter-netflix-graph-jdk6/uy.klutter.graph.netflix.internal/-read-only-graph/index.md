[uy.klutter.graph.netflix.internal](../index.md) / [ReadOnlyGraph](.)


# ReadOnlyGraph
`class ReadOnlyGraph<N&nbsp;:&nbsp;Enum<N>, R&nbsp;:&nbsp;Enum<R>>&nbsp;:&nbsp;[GraphOrdinalContainer](../-graph-ordinal-container/index.md)<N>` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Graph.kt#L20)



### Types

|&nbsp;|&nbsp;|
|---|---|
| [OrdinalIterable](-ordinal-iterable/index.md) | `class OrdinalIterable&nbsp;:&nbsp;Iterable<Int>` |
| [OrdinalIteration](-ordinal-iteration/index.md) | `class OrdinalIteration` |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyGraph(nodeTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<N>, relationTypeEnum:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<R>, input:&nbsp;[InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html))` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [graph](graph.md) | `val graph: NFGraph` |
| [relationshipGroups](relationship-groups.md) | `val relationshipGroups: MutableMap<RelationshipPairKey<N,&nbsp;R>,&nbsp;MutableSet<RelationshipTrippleKey<N,&nbsp;R>>>` |
| [relationshipMirrors](relationship-mirrors.md) | `val relationshipMirrors: MutableMap<RelationshipTrippleKey<N,&nbsp;R>,&nbsp;RelationshipTrippleKey<N,&nbsp;R>>` |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | `val ordinalsByType: MutableMap<N,&nbsp;OrdinalMap<String>>` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connectionsAsIterable](connections-as-iterable.md) | `fun NFGraph.connectionsAsIterable(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [OrdinalIterable](-ordinal-iterable/index.md)`
`fun NFGraph.connectionsAsIterable(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [OrdinalIterable](-ordinal-iterable/index.md)`
`fun NFGraph.connectionsAsIterable(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relationName:&nbsp;String): [OrdinalIterable](-ordinal-iterable/index.md)`
`fun NFGraph.connectionsAsIterable(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relationName:&nbsp;String): [OrdinalIterable](-ordinal-iterable/index.md)` |
| [connectionsAsIterator](connections-as-iterator.md) | `fun NFGraph.connectionsAsIterator(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [OrdinalIteration](-ordinal-iteration/index.md)`
`fun NFGraph.connectionsAsIterator(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [OrdinalIteration](-ordinal-iteration/index.md)` |
| [connectionsAsSet](connections-as-set.md) | `fun NFGraph.connectionsAsSet(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): Set<Int>`
`fun NFGraph.connectionsAsSet(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): Set<Int>` |
| [getConnection](get-connection.md) | `fun NFGraph.getConnection(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?`
`fun NFGraph.getConnection(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?` |
| [getConnectionSet](get-connection-set.md) | `fun NFGraph.getConnectionSet(nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>`
`fun NFGraph.getConnectionSet(model:&nbsp;String, nodeAndOrd:&nbsp;[NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>` |
| [getConnections](get-connections.md) | `fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getConnections(relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>`
`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getConnections(model:&nbsp;String, relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>`
`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getConnections(relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>`
`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getConnections(model:&nbsp;String, relation:&nbsp;R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>>` |
| [getRelationTargets](get-relation-targets.md) | `fun getRelationTargets(nodeType:&nbsp;N, relation:&nbsp;R): List<N>` |
| [getSingleConnection](get-single-connection.md) | `fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getSingleConnection(relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?`
`fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?`
`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getSingleConnection(relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?`
`fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getSingleConnection(model:&nbsp;String, relation:&nbsp;R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?` |
| [makeRelationshipName](make-relationship-name.md) | `fun makeRelationshipName(relation:&nbsp;R, toNodeType:&nbsp;N): String` |
