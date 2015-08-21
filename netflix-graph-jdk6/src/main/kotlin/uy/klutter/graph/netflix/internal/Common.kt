package uy.klutter.graph.netflix.internal


private data class RelationshipPairKey<N : Enum<N>, R : Enum<R>>(val fromNode: N, val relationship: R)
private data class RelationshipTrippleKey<N : Enum<N>, R : Enum<R>>(val fromNode: N, val relationship: R, val toNode: N)

private val GRAPH_MARKERS_HEADER = "**KOTLIN-NFGRAPH**"
private val GRAPH_MARKERS_SCHEMA_HEADER = "**SCHEMA**"
private val GRAPH_MARKERS_ORDINAL_HEADER = "**ORDINALS**"
private val GRAPH_MARKERS_GRAPH_HEADER = "**GRAPH**"


