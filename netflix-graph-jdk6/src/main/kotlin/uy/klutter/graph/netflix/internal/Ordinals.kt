package uy.klutter.graph.netflix.internal


import com.netflix.nfgraph.util.OrdinalMap
import uy.klutter.graph.netflix.NodeAndId
import uy.klutter.graph.netflix.NodeAndOrd

public abstract class GraphOrdinalContainer<N : Enum<N>>(private val readOnlyOrdinals: Boolean) {
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

    public fun toOrd(nodeType: N, primaryKey: String): Int {
        val nodeOrdinals = nodeOrdMap(nodeType)
        return if (readOnlyOrdinals) nodeOrdinals.get(primaryKey)
        else nodeOrdinals.add(primaryKey)
    }
    public fun toId(nodeType: N, ordinal: Int): String {
        val nodeOrdinals = nodeOrdMap(nodeType)
        return nodeOrdinals.get(ordinal)
    }

    public fun N.toNord(id: String): NodeAndOrd<N> = NodeAndOrd(this, toOrd(this, id))
    public operator fun N.get(id: String): NodeAndOrd<N> = NodeAndOrd(this, toOrd(this, id))
    public operator fun N.invoke(id: String): NodeAndId<N> = NodeAndId(this, id)
    public fun N.toNid(id: String): NodeAndId<N> = NodeAndId(this, id)

    public fun NodeAndId<N>.toNord(): NodeAndOrd<N> = nodeType.get(id)
    public fun NodeAndOrd<N>.toNid(): NodeAndId<N> = NodeAndId(nodeType, toId(nodeType, ord))
}

