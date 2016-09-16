package uy.klutter.binder

import uy.klutter.core.collections.asReadOnly
import uy.klutter.core.collections.toImmutable
import uy.klutter.reflect.erasedType
import uy.klutter.reflect.isAssignableFrom
import uy.klutter.reflect.isAssignableFromOrSamePrimitive
import java.lang.reflect.Type
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType


interface NamedValueProvider {
    fun valueByName(name: String, targetType: EitherType, scope: ValueProviderTargetScope): ProvidedValue<Any?>

    // TODO: optimize getting entries as pair of name/value with or without type coersion

    fun entryNames(): List<String>

    override fun hashCode(): Int
    override fun equals(other: Any?): Boolean

    val knowsEntries: Boolean // if true the provider knows the complete list of entries it wants to provide, otherwise it has none or a partial list
}

interface OrderedValueProvider {
    // fun valueByIndex(idx: Int, targetType: KType): ProvidedValue<Any>
    fun valueSequence(targetType: EitherType): Sequence<ProvidedValue.Present<Any?>>

    override fun hashCode(): Int
    override fun equals(other: Any?): Boolean
}

enum class ValueProviderTargetScope {
    CONSTRUCTOR, METHOD, PROPERTY, UNKNOWN
}

fun NamedValueProvider.withDefaults() = this.withTypeConversion().withMissingInParameterValuesAsNullable()

fun NamedValueProvider.withMissingInParameterValuesAsNullable() = MissingConstructorAndMethodParametersTreatedAsNullableNamedValueProviderDelegate(this)
fun NamedValueProvider.withTypeConversion() = TypeConversionNamedValueProviderDelegate(this)

class TypeConversionNamedValueProviderDelegate(private val delegate: NamedValueProvider, private val conversion: TypeConverters = TypeConversionConfig.defaultConverter) : NamedValueProvider by delegate {
    override fun valueByName(name: String, targetType: EitherType, scope: ValueProviderTargetScope): ProvidedValue<Any?> {
        val maybe = delegate.valueByName(name, targetType, scope)
        return when (maybe) {
            is ProvidedValue.Present -> {
                val rawValue = maybe.value
                if (rawValue != null &&
                        !targetType.asJava.isAssignableFromOrSamePrimitive(rawValue.javaClass) &&
                        conversion.hasConverter(rawValue.javaClass, targetType.asJava)) {
                    ProvidedValue.coerced<Any?, Any>(maybe, conversion.convertValue(rawValue.javaClass, targetType.asJava, rawValue))
                } else {
                    maybe
                }
            }
            is ProvidedValue.NestedNamedValueProvider -> maybe
            is ProvidedValue.NestedOrderedValueProvider -> maybe
            is ProvidedValue.Absent -> maybe
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                other.javaClass == other.javaClass &&
                other is TypeConversionNamedValueProviderDelegate &&
                other.delegate == this.delegate
    }

    override fun hashCode(): Int {
        return 34 * delegate.hashCode()
    }
}

class TypeConversionOrderedValueProviderDelegate(private val delegate: OrderedValueProvider, private val conversion: TypeConverters = TypeConversionConfig.defaultConverter) : OrderedValueProvider by delegate {
    override fun valueSequence(targetType: EitherType): Sequence<ProvidedValue.Present<Any?>> {
        return delegate.valueSequence(targetType).map { likely ->
            val rawValue = likely.value
            if (rawValue != null &&
                    !targetType.asJava.isAssignableFromOrSamePrimitive(rawValue.javaClass) &&
                    conversion.hasConverter(rawValue.javaClass, targetType.asJava)) {
                ProvidedValue.coerced<Any?, Any?>(likely, conversion.convertValue(rawValue.javaClass, targetType.asJava, rawValue)) as ProvidedValue.Present<Any?>
            } else {
                likely
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                other.javaClass == other.javaClass &&
                other is TypeConversionOrderedValueProviderDelegate &&
                other.delegate == this.delegate
    }

    override fun hashCode(): Int {
        return 37 * delegate.hashCode()
    }
}

class MissingConstructorAndMethodParametersTreatedAsNullableNamedValueProviderDelegate(private val delegate: NamedValueProvider) : NamedValueProvider by delegate {
    override fun valueByName(name: String, targetType: EitherType, scope: ValueProviderTargetScope): ProvidedValue<Any?> {
        val maybe = delegate.valueByName(name, targetType, scope)
        return when (maybe) {
            is ProvidedValue.Present -> maybe
            is ProvidedValue.NestedNamedValueProvider -> maybe
            is ProvidedValue.NestedOrderedValueProvider -> maybe
            is ProvidedValue.Absent -> {
                if (targetType.isNullable && (scope == ValueProviderTargetScope.CONSTRUCTOR || scope == ValueProviderTargetScope.METHOD)) {
                    ProvidedValue.coerced<Any?, Any?>(maybe, null)
                } else {
                    maybe
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                other.javaClass == other.javaClass &&
                other is MissingConstructorAndMethodParametersTreatedAsNullableNamedValueProviderDelegate &&
                other.delegate == this.delegate
    }

    override fun hashCode(): Int {
        return 41 * delegate.hashCode()
    }
}

class MapValueProvider(wrap: Map<String, Any?>) : NamedValueProvider {
    val source = wrap.asReadOnly().let { original ->
        // turn dotted property names into nested providers
        original.entries.groupBy { it.key.substringBefore('.') }.map {
            if (it.value.size == 1) {
                val first = it.value.first()
                if (first is Map<*, *>) {
                    @Suppress("UNCHECKED_CAST")
                    Pair(it.key, MapValueProvider(first as Map<String, Any?>))
                } else {
                    Pair(it.key, it.value.first().value)
                }
            } else {
                Pair(it.key, MapValueProvider(it.value.map { it.key.substringAfter('.') to it.value }.toMap()))
            }
        }.toMap()
    }

    @Suppress("UNCHECKED_CAST")
    override fun valueByName(name: String, targetType: EitherType, scope: ValueProviderTargetScope): ProvidedValue<Any?> {
        return if (!source.containsKey(name)) ProvidedValue.absent()
        else source.get(name)?.let { value ->
            when {
                value is NamedValueProvider -> ProvidedValue.nested(value)
                value is OrderedValueProvider -> ProvidedValue.nested(value)
                value is Map<*, *> && !targetType.asErased.isAssignableFrom(Map::class) -> ProvidedValue.nested(MapValueProvider(value as Map<String, Any>))
                value is Collection<*> && !targetType.asErased.isAssignableFrom(Collection::class) -> ProvidedValue.nested(seqValueProviderOf(value))
                value is Array<*> && !targetType.asErased.isArray() -> ProvidedValue.nested(seqValueProviderOf(value))
                value is Sequence<*> && !targetType.asErased.isAssignableFrom(Sequence::class) -> ProvidedValue.nested(seqValueProviderOf(value))
                value is Iterator<*> && !targetType.asErased.isAssignableFrom(Iterator::class) -> ProvidedValue.nested(seqValueProviderOf(value))
                value is Iterable<*> && !targetType.asErased.isAssignableFrom(Iterable::class) -> ProvidedValue.nested(seqValueProviderOf(value))
                else -> ProvidedValue.of(value)
            }
        } ?: ProvidedValue.of(null)
    }

    override fun entryNames(): List<String> {
        return source.map { it.key }
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

    override val knowsEntries: Boolean = true
}

fun emptyValueProvider() = MapValueProvider(emptyMap())

fun <K: String, V> mapValueProviderOf(vararg keyValues: Pair<K, V>): MapValueProvider = MapValueProvider(mapOf(*keyValues))
@Suppress("UNCHECKED_CAST")
fun <K: String, V> mapValueProviderOf(original: Map<K, V>): MapValueProvider = MapValueProvider(original as Map<String, Any?>)

fun <T> seqValueProviderOf(collection: Collection<T>): SequenceValueProvider<T> = SequenceValueProvider(collection.asSequence())
fun <T> seqValueProviderOf(iterate: Iterator<T>): SequenceValueProvider<T> = SequenceValueProvider(iterate.asSequence())
fun <T> seqValueProviderOf(iterable: Iterable<T>): SequenceValueProvider<T> = SequenceValueProvider(iterable.asSequence())
fun <T> seqValueProviderOf(sequence: Sequence<T>): SequenceValueProvider<T> = SequenceValueProvider(sequence.asSequence())
fun <T> seqValueProviderOf(array: Array<T>): SequenceValueProvider<T> = SequenceValueProvider(array.asSequence())

@JvmName("seqValueProviderOfVararg")
fun <T> seqValueProviderOf(vararg values: T): SequenceValueProvider<T> = SequenceValueProvider(values.asSequence())

class SequenceValueProvider<out ST>(wrap: Sequence<ST>): OrderedValueProvider {
    val source = wrap

    override fun valueSequence(targetType: EitherType): Sequence<ProvidedValue.Present<Any?>> {
        return source.map {
            if (!targetType.isNullable) it!!
            ProvidedValue.Present.of(it)
        }
    }

    override fun hashCode(): Int {
        return source.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                this.javaClass == other.javaClass &&
                other is SequenceValueProvider<*> &&
                other.source == this.source
    }
}