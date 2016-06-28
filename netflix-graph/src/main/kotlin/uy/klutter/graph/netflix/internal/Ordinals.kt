package uy.klutter.graph.netflix.internal


import com.netflix.nfgraph.util.OrdinalMap
import uy.klutter.graph.netflix.NodeAndId
import uy.klutter.graph.netflix.NodeAndOrd

abstract class GraphOrdinalContainer<N : Enum<N>>(private val readOnlyOrdinals: Boolean) {
    protected val ordinalsByType: MutableMap<N, OrdinalMap<String>> = hashMapOf()
    /*
    protected val typeNameToEnum: Map<String, N> = nodeTypeEnum.getEnumConstants().map { Pair(it.name(), it) }.toMap()

    [deprecated("Try to always use enum instead of strings")]
    protected fun nodeOrdMap(nodeTypeName: String): OrdinalMap<String> {
        val nodeType = typeNameToEnum.get(nodeTypeName)
        if (nodeType == null) {
            throw RuntimeException("NodeType name ${nodeTypeName} does not exist in the enumeration for node types")
        }
        return nodeOrdMap(nodeType)
    }
    */

    protected fun nodeOrdMap(nodeType: N): OrdinalMap<String> {
        return ordinalsByType.getOrPut(nodeType, { OrdinalMap<String>() })
    }

    fun toOrd(nodeType: N, primaryKey: String): Int {
        val nodeOrdinals = nodeOrdMap(nodeType)
        return if (readOnlyOrdinals) nodeOrdinals.get(primaryKey)
        else nodeOrdinals.add(primaryKey)
    }
    fun toId(nodeType: N, ordinal: Int): String {
        val nodeOrdinals = nodeOrdMap(nodeType)
        return nodeOrdinals.get(ordinal)
    }

    fun N.toNord(id: String): NodeAndOrd<N> = NodeAndOrd(this, toOrd(this, id))
    operator fun N.get(id: String): NodeAndOrd<N> = NodeAndOrd(this, toOrd(this, id))
    operator fun N.invoke(id: String): NodeAndId<N> = NodeAndId(this, id)
    fun N.toNid(id: String): NodeAndId<N> = NodeAndId(this, id)

    fun NodeAndId<N>.toNord(): NodeAndOrd<N> = nodeType.get(id)
    fun NodeAndOrd<N>.toNid(): NodeAndId<N> = NodeAndId(nodeType, toId(nodeType, ord))
}

