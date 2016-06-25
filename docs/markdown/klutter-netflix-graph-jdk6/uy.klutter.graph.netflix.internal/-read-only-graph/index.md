[uy.klutter.graph.netflix.internal](../index.md) / [ReadOnlyGraph](.)


# ReadOnlyGraph
<code>class ReadOnlyGraph<N : Enum<N>, R : Enum<R>> : [GraphOrdinalContainer](../-graph-ordinal-container/index.md)<N></code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/internal/Graph.kt#L20)<br/>


### Types

|&nbsp;|&nbsp;|
|---|---|
| [OrdinalIterable](-ordinal-iterable/index.md) | <code>class OrdinalIterable : Iterable<Int></code><br/> |
| [OrdinalIteration](-ordinal-iteration/index.md) | <code>class OrdinalIteration</code><br/> |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ReadOnlyGraph(nodeTypeEnum: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<N>, relationTypeEnum: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<R>, input: [InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html))</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [graph](graph.md) | <code>val graph: NFGraph</code><br/> |
| [relationshipGroups](relationship-groups.md) | <code>val relationshipGroups: MutableMap<RelationshipPairKey<N, R>, MutableSet<RelationshipTrippleKey<N, R>>></code><br/> |
| [relationshipMirrors](relationship-mirrors.md) | <code>val relationshipMirrors: MutableMap<RelationshipTrippleKey<N, R>, RelationshipTrippleKey<N, R>></code><br/> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [ordinalsByType](../-graph-ordinal-container/ordinals-by-type.md) | <code>val ordinalsByType: MutableMap<N, OrdinalMap<String>></code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [connectionsAsIterable](connections-as-iterable.md) | <code>fun NFGraph.connectionsAsIterable(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [OrdinalIterable](-ordinal-iterable/index.md)</code><br/><code>fun NFGraph.connectionsAsIterable(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [OrdinalIterable](-ordinal-iterable/index.md)</code><br/><code>fun NFGraph.connectionsAsIterable(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relationName: String): [OrdinalIterable](-ordinal-iterable/index.md)</code><br/><code>fun NFGraph.connectionsAsIterable(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relationName: String): [OrdinalIterable](-ordinal-iterable/index.md)</code><br/> |
| [connectionsAsIterator](connections-as-iterator.md) | <code>fun NFGraph.connectionsAsIterator(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [OrdinalIteration](-ordinal-iteration/index.md)</code><br/><code>fun NFGraph.connectionsAsIterator(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [OrdinalIteration](-ordinal-iteration/index.md)</code><br/> |
| [connectionsAsSet](connections-as-set.md) | <code>fun NFGraph.connectionsAsSet(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): Set<Int></code><br/><code>fun NFGraph.connectionsAsSet(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): Set<Int></code><br/> |
| [getConnection](get-connection.md) | <code>fun NFGraph.getConnection(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/><code>fun NFGraph.getConnection(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/> |
| [getConnectionSet](get-connection-set.md) | <code>fun NFGraph.getConnectionSet(nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/><code>fun NFGraph.getConnectionSet(model: String, nodeAndOrd: [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>, relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/> |
| [getConnections](get-connections.md) | <code>fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getConnections(relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/><code>fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getConnections(model: String, relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/><code>fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getConnections(relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/><code>fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getConnections(model: String, relation: R): Set<[NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>></code><br/> |
| [getRelationTargets](get-relation-targets.md) | <code>fun getRelationTargets(nodeType: N, relation: R): List<N></code><br/> |
| [getSingleConnection](get-single-connection.md) | <code>fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getSingleConnection(relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/><code>fun [NodeAndOrd](../../uy.klutter.graph.netflix/-node-and-ord/index.md)<N>.getSingleConnection(model: String, relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/><code>fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getSingleConnection(relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/><code>fun [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>.getSingleConnection(model: String, relation: R): [NodeAndId](../../uy.klutter.graph.netflix/-node-and-id/index.md)<N>?</code><br/> |
| [makeRelationshipName](make-relationship-name.md) | <code>fun makeRelationshipName(relation: R, toNodeType: N): String</code><br/> |
