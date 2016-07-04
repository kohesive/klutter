package uy.klutter.binder

import uy.klutter.core.collections.toImmutable
import java.lang.reflect.Type


class MapValueProvider(wrap: Map<String, Any>) : NamedValueProvider {
    val source = wrap.toImmutable()

    override fun existsByName(name: String, targetType: Type): Boolean {
        return source.containsKey(name)
    }

    @Suppress("UNCHECKED_CAST")
    override fun valueByName(name: String, targetType: Type): Any? {
        return source.get(name)
    }

    override fun entries(): List<Pair<String, Any?>> {
        return source.map { Pair(it.key, it.value) }.toList()
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                other is MapValueProvider &&
                other.source == this.source
    }

    override fun hashCode(): Int {
        return source.hashCode()
    }

    override val supportsDottedNames: Boolean = true
    override val knowsEntries: Boolean = true
}

fun emptyValueProvider() = MapValueProvider(emptyMap())