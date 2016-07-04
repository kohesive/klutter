package uy.klutter.binder

import java.lang.reflect.Type

object BindingUtils {
    fun getDottedFromProvider(dottedName: String, targetType: Type, provider: NamedValueProvider): Any? {
        return _getDottedFromMapInternal(dottedName, emptyList(), targetType, provider)
    }

    @Suppress("UNCHECKED_CAST")
    private fun _getDottedFromMapInternal(dottedName: String, nameStack: List<String>, targetType: Type, provider: NamedValueProvider): Any? {
        val allSegments = dottedName.split('.')
        val startIndex = if (provider.supportsDottedNames) allSegments.size else 1
        (startIndex..1).forEach { howMany ->
            val currentSegments = allSegments.take(howMany)
            val currentName = currentSegments.joinToString(".")
            val newStack = nameStack + currentSegments

            if (provider.existsByName(currentName, targetType)) {
                val attemptValue = provider.valueByName(currentName, targetType)
                if (attemptValue != null) {
                    if (howMany < allSegments.size) {
                        val nestedProvider = when (attemptValue) {
                            is Map<*, *> -> MapValueProvider(attemptValue as Map<String, Any>)
                            else -> throw IllegalStateException("dotted variable ${nameStack.joinToString(".")} has type without a known value provider")
                        }
                        return _getDottedFromMapInternal(allSegments.drop(howMany).joinToString("."), newStack, targetType, nestedProvider)
                    } else {
                        return attemptValue
                    }
                }
            }
        }
        return null
    }
}