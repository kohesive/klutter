package uy.klutter.binder

import uy.klutter.reflect.isAssignableFromOrSamePrimitive
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType


interface NamedValueProvider {
    // name "a" = parameter "a"
    // name "a.b" = nested value "b" within parameter "a"
    fun valueByName(name: String, targetType: KType, scope: ValueProviderTargetScope): ProvidedValue<Any>

    fun entries(): List<Pair<String, Any?>>

    override fun hashCode(): Int
    override fun equals(other: Any?): Boolean

    val supportsDottedNames: Boolean
    val knowsEntries: Boolean // if true the provider knows the complete list of entries it wants to provide, otherwise it has none or a partial list
}

enum class ValueProviderTargetScope {
    CONSTRUCTOR, METHOD, PROPERTY, UNKNOWN
}

fun NamedValueProvider.withDefaults() = this.withTypeConversion().withMissingInParameterValuesAsNullable()

fun NamedValueProvider.withMissingInParameterValuesAsNullable() = MissingConstructorAndMethodParametersTreatedAsNullableNamedValueProviderDelegate(this)
fun NamedValueProvider.withTypeConversion() = TypeConversionNamedValueProviderDelegate(this)

class TypeConversionNamedValueProviderDelegate(private val delegate: NamedValueProvider, private val conversion: TypeConverters = TypeConversionConfig.defaultConverter) : NamedValueProvider by delegate {
    override fun valueByName(name: String, targetType: KType, scope: ValueProviderTargetScope): ProvidedValue<Any> {
        val maybe = delegate.valueByName(name, targetType, scope)
        return when (maybe) {
            is ProvidedValue.Present -> {
                val rawValue = maybe.value
                if (rawValue != null &&
                        !targetType.isAssignableFromOrSamePrimitive(rawValue.javaClass) &&
                        conversion.hasConverter(rawValue.javaClass, targetType.javaType)) {
                    ProvidedValue.coerced<Any, Any>(maybe, conversion.convertValue(rawValue.javaClass, targetType.javaType, rawValue))
                } else {
                    maybe
                }
            }
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

class MissingConstructorAndMethodParametersTreatedAsNullableNamedValueProviderDelegate(private val delegate: NamedValueProvider) : NamedValueProvider by delegate {
    override fun valueByName(name: String, targetType: KType, scope: ValueProviderTargetScope): ProvidedValue<Any> {
        val maybe = delegate.valueByName(name, targetType, scope)
        return when (maybe) {
            is ProvidedValue.Present -> maybe
            is ProvidedValue.Absent -> {
                if (targetType.isMarkedNullable && (scope == ValueProviderTargetScope.CONSTRUCTOR || scope == ValueProviderTargetScope.METHOD)) {
                    ProvidedValue.coerced<Any, Any>(maybe, null)
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
