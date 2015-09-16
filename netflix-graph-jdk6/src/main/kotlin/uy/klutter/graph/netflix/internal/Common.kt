package uy.klutter.graph.netflix.internal


internal data class RelationshipPairKey<N : Enum<N>, R : Enum<R>>(val fromNode: N, val relationship: R)
internal data class RelationshipTrippleKey<N : Enum<N>, R : Enum<R>>(val fromNode: N, val relationship: R, val toNode: N)

internal val GRAPH_MARKERS_HEADER = "**KOTLIN-NFGRAPH**"
internal val GRAPH_MARKERS_SCHEMA_HEADER = "**SCHEMA**"
internal val GRAPH_MARKERS_ORDINAL_HEADER = "**ORDINALS**"
internal val GRAPH_MARKERS_GRAPH_HEADER = "**GRAPH**"


