package uy.klutter.binder

import java.lang.reflect.Type

interface NamedValueProvider {
    // name "a" = parameter "a"
    // name "a.b" = nested value "b" within parameter "a"
    fun valueByName(name: String, targetType: Type): Any?

    fun existsByName(name: String, targetType: Type): Boolean

    fun entries(): List<Pair<String, Any?>>

    override fun hashCode(): Int
    override fun equals(other: Any?): Boolean

    val supportsDottedNames: Boolean
    val knowsEntries: Boolean // if true the provider knows the complete list of entries it wants to provide, otherwise it has none or a partial list
}

interface IndexedValueProvider {
    fun valueByIndex(idx: Int, targetType: Type? = null): Any?
    fun existsByIndex(idx: Int, targetType: Type?): Boolean
}