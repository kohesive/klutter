[uy.klutter.graph.netflix](../index.md) / [GraphRelationOptions](.)


# GraphRelationOptions
`interface GraphRelationOptions` [(source)](https://github.com/kohesive/klutter/blob/master/netflix-graph-jdk6/src/main/kotlin/uy/klutter/graph/netflix/NetflixGraph.kt#L43)



### Properties

|&nbsp;|&nbsp;|
|---|---|
| [flags](flags.md) | `abstract val flags: Int` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [minus](../minus.md) | `operator fun GraphRelationOptions.minus(other:&nbsp;GraphRelationOptions): GraphRelationOptions` |
| [plus](../plus.md) | `operator fun GraphRelationOptions.plus(other:&nbsp;GraphRelationOptions): GraphRelationOptions` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [RelationCardinality](../-relation-cardinality/index.md) | `enum class RelationCardinality&nbsp;:&nbsp;GraphRelationOptions` |
| [RelationScope](../-relation-scope/index.md) | `enum class RelationScope&nbsp;:&nbsp;GraphRelationOptions` |
| [RelationStructure](../-relation-structure/index.md) | `enum class RelationStructure&nbsp;:&nbsp;GraphRelationOptions` |
| [TempGraphFlags](../-temp-graph-flags/index.md) | `class TempGraphFlags&nbsp;:&nbsp;GraphRelationOptions` |
