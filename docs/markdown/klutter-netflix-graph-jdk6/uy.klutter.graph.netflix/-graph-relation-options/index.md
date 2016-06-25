[uy.klutter.graph.netflix](../index.md) / [GraphRelationOptions](.)


# GraphRelationOptions
<code>interface GraphRelationOptions</code> [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L43)<br/>


### Properties

|&nbsp;|&nbsp;|
|---|---|
| [flags](flags.md) | <code>abstract val flags: Int</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [minus](../minus.md) | <code>operator fun GraphRelationOptions.minus(other: GraphRelationOptions): GraphRelationOptions</code><br/> |
| [plus](../plus.md) | <code>operator fun GraphRelationOptions.plus(other: GraphRelationOptions): GraphRelationOptions</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [RelationCardinality](../-relation-cardinality/index.md) | <code>enum class RelationCardinality : GraphRelationOptions</code><br/> |
| [RelationScope](../-relation-scope/index.md) | <code>enum class RelationScope : GraphRelationOptions</code><br/> |
| [RelationStructure](../-relation-structure/index.md) | <code>enum class RelationStructure : GraphRelationOptions</code><br/> |
| [TempGraphFlags](../-temp-graph-flags/index.md) | <code>class TempGraphFlags : GraphRelationOptions</code><br/> |
