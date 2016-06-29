package uy.klutter.binder

import uy.klutter.core.collections.toImmutable
import java.lang.reflect.Type
import uy.klutter.reflect.*
import kotlin.reflect.*
import kotlin.reflect.jvm.javaType

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

// search constructors, static methods, methods of companion, or someone provides a lambda that returns a callable
//     find the best fit of:
//          use parameters during use of callable
//          any settable parameters after constructed
//
//     so all mandatory things must be satisfied in whatever combinations are considered
//
//     in a tie, the one with more set during callable is preferred



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

enum class ConstructionError {
    WRONG_TYPE, COERCION_ERROR, NULL_VALUE_NON_NULLABLE_TYPE, MISSING_PROPERTY, MISSING_VALUE, NON_SETTABLE_PROPERTY
}

enum class ConstructionWarning {
    MISSING_VALUE, DEFAULT_VALUE_USED_FOR_DATATYPE
}

class ConstructionPlan <T: Any, C: T> (val constructClass: KClass<T>,
                                 val constructType: Type,
                                 val useCallable: KCallable<C>,
                                 val withParameters: List<Pair<KParameter, Any?>>,
                                 val thenSetProperties: List<Pair<KMutableProperty1<T, *>, Any?>>,
                                 val parameterErrors: List<Pair<KParameter, ConstructionError>>,
                                 val parameterWarnings: List<Pair<KParameter, ConstructionWarning>>,
                                 val propertyErrors: List<Pair<KProperty1<T, *>, ConstructionError>>,
                                 val propertyWarnings: List<Pair<KProperty1<T, *>, ConstructionWarning>>,
                                 val nonmatchingProviderEntries: Set<String>) {
    val errorCount: Int = parameterErrors.groupBy { it.first }.size +
            propertyErrors.groupBy { it.first }.size
    val warningCount: Int = parameterWarnings.groupBy { it.first }.size +
            propertyWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount  > 0

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <T: Any, C: T> from(constructClass: KClass<T>, constructType: Type, usingCallable: KCallable<C>, valueProvider: NamedValueProvider, nullableValuesAcceptMissingAsNull: Boolean = true): ConstructionPlan<T,C> {
            val isConstructorCall = constructClass.constructors.any { it == usingCallable }
            val isCompanionCall = constructClass.companionObject?.declaredFunctions?.any { it == usingCallable } ?: false

            if (!isConstructorCall && !isCompanionCall) {
                throw IllegalStateException("callable is not from $constructClass nor its companion object")
            }

            val propertiesOfClass = constructClass.declaredMemberProperties.map { it.name to it }.toMap()
            val entriesFromProvider = valueProvider.entries().map { it.first.substringBefore('.') }.toSet()

            val usedEntriesFromProvider = hashSetOf<String>()
            val usedPropertiesOfClass = hashSetOf<KProperty1<T, *>>()
            val paramErrors = arrayListOf<Pair<KParameter, ConstructionError>>()
            val paramWarnings = arrayListOf<Pair<KParameter, ConstructionWarning>>()
            val propertyErrors = arrayListOf<Pair<KProperty1<T, *>, ConstructionError>>()
            val propertyWarnings = arrayListOf<Pair<KProperty1<T, *>, ConstructionWarning>>()
            val orderedParamValues = arrayListOf<Pair<KParameter, Any?>>()
            val propertyValues = arrayListOf<Pair<KMutableProperty1<T, *>, Any?>>()

            fun markValueMatchedSomething(key: String) {
                usedEntriesFromProvider.add(key)
            }

            fun consumeProperty(propertyName: String) {
                propertiesOfClass.get(propertyName)?.let {
                    usedPropertiesOfClass.add(it)
                }
            }

            fun errorProperty(property: KProperty1<T, *>, error: ConstructionError) {
               propertyErrors.add(Pair(property, error))
            }

            fun errorProperty(propertyName: String, error: ConstructionError) {
                propertiesOfClass.get(propertyName)?.let {
                    errorProperty(it, error)
                }
            }

            fun warnProperty(property: KProperty1<T, *>, warning: ConstructionWarning) {
                propertyWarnings.add(Pair(property, warning))
            }

            fun warnProperty(propertyName: String, warning: ConstructionWarning) {
                propertiesOfClass.get(propertyName)?.let {
                    warnProperty(it, warning)
                }
            }

            fun errorParam(param: KParameter, error: ConstructionError) {
                paramErrors.add(Pair(param, error))
            }

            fun warnParam(param: KParameter, warning: ConstructionWarning) {
                paramWarnings.add(Pair(param, warning))
            }

            fun useParam(param: KParameter, rawValue: Any?) {
                if (rawValue != null && !param.type.isAssignableFromOrSamePrimitive(rawValue.javaClass)) {
                    errorParam(param, ConstructionError.WRONG_TYPE)
                } else {
                    orderedParamValues.add(Pair(param, rawValue))
                }
            }

            fun useProperty(propDef: KMutableProperty1<T, *>, rawValue: Any?) {
                if (rawValue != null && !propDef.returnType.isAssignableFromOrSamePrimitive(rawValue.javaClass)) {
                    errorProperty(propDef, ConstructionError.WRONG_TYPE)
                } else {
                    propertyValues.add(Pair(propDef, rawValue))
                }
            }

            usingCallable.parameters.forEach { paramDef ->
                when (paramDef.kind) {
                    KParameter.Kind.INSTANCE -> {
                        if (isCompanionCall)  {
                            orderedParamValues.add(Pair(paramDef, constructClass.companionObjectInstance))
                        } else {
                            throw IllegalStateException("non companion object callable wasn't expecting an instance parameter")
                        }
                    }
                    KParameter.Kind.EXTENSION_RECEIVER -> throw IllegalStateException("callable requires a receiver object cannot be used to construct a class")
                    KParameter.Kind.VALUE -> {
                        val paramName = paramDef.name ?: throw IllegalStateException("callable has parameter with unknown name")
                        val isMissing = !valueProvider.existsByName(paramName, paramDef.type.javaType)

                        if (!isMissing) {
                            markValueMatchedSomething(paramName)
                        }

                        if (isMissing) {
                            if (paramDef.isOptional) {
                                // this is ok, optional parameter not resolved will have default parameter value of method
                                consumeProperty(paramName)
                            } else if (paramDef.type.isMarkedNullable && nullableValuesAcceptMissingAsNull) {
                                // default value for datatype is ok if null and nullable, or is non null and matches type
                                useParam(paramDef, null)
                                consumeProperty(paramName)
                                warnParam(paramDef, ConstructionWarning.DEFAULT_VALUE_USED_FOR_DATATYPE)
                            } else {
                                errorParam(paramDef, ConstructionError.MISSING_VALUE)
                                errorParam(paramDef, ConstructionError.NULL_VALUE_NON_NULLABLE_TYPE)
                            }
                        } else {
                            val paramVal = valueProvider.valueByName(paramName, paramDef.type.javaType)

                            if (paramVal == null && !paramDef.type.isMarkedNullable) {
                                // value coming in as null for non-nullable type
                                errorParam(paramDef, ConstructionError.NULL_VALUE_NON_NULLABLE_TYPE)
                            } else {
                                // value present, and can be set
                                useParam(paramDef, paramVal)
                                consumeProperty(paramName)
                            }
                        }
                    }
                }
                Unit
            }

            val unsetProperties = propertiesOfClass.entries.map { it.value }.toSet() - usedPropertiesOfClass
            unsetProperties.forEach { propDefx ->
                if (propDefx is KMutableProperty1<*, *>) {
                    val propDef = propDefx as KMutableProperty1<T, *>
                    val propName = propDef.name
                    val isMissing = !valueProvider.existsByName(propName, propDef.returnType.javaType)
                    val propVal = valueProvider.valueByName(propName, propDef.returnType.javaType)

                    if (!isMissing) {
                        markValueMatchedSomething(propName)
                    }

                    if (isMissing) {
                        // no value for property, which is ok, we just want to know about it
                        warnProperty(propDef, ConstructionWarning.MISSING_VALUE)
                    } else {
                        if (propVal == null && !propDef.returnType.isMarkedNullable) {
                            // value coming in as null for non-nullable type
                            errorProperty(propDef, ConstructionError.NULL_VALUE_NON_NULLABLE_TYPE)
                        } else {
                            // value present, and can be set
                            useProperty(propDef, propVal)
                            consumeProperty(propName)
                        }
                    }
                } else {
                    markValueMatchedSomething(propDefx.name) // we can't set it, but it does aline
                    errorProperty(propDefx, ConstructionError.NON_SETTABLE_PROPERTY)
                }
            }

            val unusedEntriesFromProvider = entriesFromProvider - usedEntriesFromProvider

            return ConstructionPlan(constructClass, constructType, usingCallable,
                    orderedParamValues, propertyValues,
                    paramErrors, paramWarnings, propertyErrors, propertyWarnings, unusedEntriesFromProvider)
        }
    }
}

// TODO: do not allow construction for callables that have a receiver type
// TODO: plans should be cacheable for a given constructable class and the same provider of values
/*
inline fun <reified T: Any> constructObject(valueProvider: NamedValueProvider): T {
    val typeOf = T::class

// find constructors, catalog which are possible to call given the values available
    // after each constructor, what remaining properties must be set
    // and can they be set (wrong type, nullability mistmatches recorded).
    // those values which cannot be used, those parameters or properties that cannot be set
    constructClass.constructors

}

inline fun <reified T: Any> constructObject(usingCallable: KCallable<T>, valueProvider: NamedValueProvider): T {
    val typeOf = T::class


}

inline fun <reified T: Any> constructObject(usingCallable: KCallable<T>, valueProvider: NamedValueProvider, withPlan: ConstructionPlan): T {
    val typeOf = T::class


}
*/

