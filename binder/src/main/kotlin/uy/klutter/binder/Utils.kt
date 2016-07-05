package uy.klutter.binder

import java.lang.reflect.Type
import kotlin.reflect.KType

object BindingUtils {
    fun getDottedFromProvider(dottedName: String, targetType: KType, provider: NamedValueProvider, scope: ValueProviderTargetScope = ValueProviderTargetScope.UNKNOWN): Any? {
        return _getDottedFromMapInternal(dottedName, emptyList(), targetType, provider, scope)
    }

    @Suppress("UNCHECKED_CAST")
    private fun _getDottedFromMapInternal(dottedName: String, nameStack: List<String>, targetType: KType, provider: NamedValueProvider, scope: ValueProviderTargetScope): Any? {
        val allSegments = dottedName.split('.')
        val startIndex = if (provider.supportsDottedNames) allSegments.size else 1
        (startIndex..1).forEach { howMany ->
            val currentSegments = allSegments.take(howMany)
            val currentName = currentSegments.joinToString(".")
            val newStack = nameStack + currentSegments

            val maybe = provider.valueByName(currentName, targetType, scope)
            if (maybe is ProvidedValue.Present) {
                val rawValue = maybe.value
                if (rawValue != null) {
                    if (howMany < allSegments.size) {
                        val nestedProvider = when (rawValue) {
                            is Map<*, *> -> MapValueProvider(rawValue as Map<String, Any>)
                            else -> throw IllegalStateException("dotted variable ${nameStack.joinToString(".")} has type without a known value provider")
                        }
                        return _getDottedFromMapInternal(allSegments.drop(howMany).joinToString("."), newStack, targetType, nestedProvider, scope)
                    } else {
                        return rawValue
                    }
                }
            }
        }
        return null
    }
}