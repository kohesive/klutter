package uy.klutter.binder

import uy.klutter.core.collections.toImmutable
import kotlin.reflect.KType


class MapValueProvider(wrap: Map<String, Any?>) : NamedValueProvider {
    val source = wrap.toImmutable()

    @Suppress("UNCHECKED_CAST")
    override fun valueByName(name: String, targetType: KType, scope: ValueProviderTargetScope): ProvidedValue<Any> {
        return if (!source.containsKey(name)) ProvidedValue.absent()
               else ProvidedValue.of(source.get(name))
    }

    override fun entries(): List<Pair<String, Any?>> {
        return source.map { Pair(it.key, it.value) }.toList()
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                this.javaClass == other.javaClass &&
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
fun mapValueProviderOf(vararg keyValues: Pair<String, Any?>): MapValueProvider = MapValueProvider(mapOf(*keyValues))