package uy.klutter.binder

import kotlinx.support.jdk8.collections.computeIfAbsent
import uy.klutter.core.collections.toImmutable
import uy.klutter.reflect.isAssignableFrom
import uy.klutter.reflect.isAssignableFromOrSamePrimitive
import java.lang.reflect.Type
import java.util.concurrent.ConcurrentHashMap
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
    COERCION_ERROR,
    WRONG_TYPE,
    NULL_VALUE_NON_NULLABLE_TYPE,
    HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY,
    HAVE_VALUE_NOT_USED
}

enum class ConstructionWarning {
    MISSING_VALUE_FOR_SETTABLE_PROPERTY,
    HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY,
    HAVE_VALUE_NOT_USED
}

enum class CallableError {
    COERCION_ERROR,
    WRONG_TYPE,
    NULL_VALUE_NON_NULLABLE_TYPE,
    MISSING_VALUE_FOR_REQUIRED_PARAMETER
}

enum class CallableWarning {
    DEFAULT_VALUE_USED_FOR_DATATYPE
}

// TODO: recursive construction, for example if the provider is nested levels of properties, it could provide enough
//       to construct instances required.

// TODO: if doing recursive construction, or even construction of things like List<Person> we need to change value provider
//       to return array of things, or object of things.  So Value provider will have to be like the JSON API's but with
//       difference of having Type and exists calls.  So maybe:
//           ValueProvider is like JsonObject, can return nested ValueProviders
//           List<ValueProvider> is like JsonArray, can return list of providers
//       while still allowing dotted property name access from a value provider "a.b.c.d" to skip down into the provider
//       but not sure if we need array indexed dotted access, since dotted access is mostly used as optimization from things
//       like TypeSafeConfig or Map of Maps.

// TODO: for injection of parameters, allow `injectOnlyAnnotated` flag to annotations ending in `.Inject` .. do we pay attention to
//       @Provider and @Singleton then?  or are we then becoming full injection and not really what we meant to do?  This would go
//       into the Kodein module anyway.  A @Qualifier and @Named would be like an Kodein tag.  @Scope?

// TODO: flags for using properties or not after construction

// TODO: flags for preference of constructor FULL vs. Default

class CallablePlan <DT, RT, R>(val useCallable: KCallable<R>,
                               val dispatchInstance: DT?,
                               val receiverInstance: RT?,
                               val withParameters: List<Pair<KParameter, Any?>>,
                               val parameterErrors: List<Pair<KParameter, CallableError>>,
                               val parameterWarnings: List<Pair<KParameter, CallableWarning>>,
                               val satisfiedParameters: List<KParameter>,
                               val nonmatchingProviderEntries: Set<String>) {
    val errorCount: Int = parameterErrors.groupBy { it.first }.size
    val warningCount: Int = parameterWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount > 0

    fun execute(): R {
        if (hasErrors) throw IllegalStateException("Callable binding that ahs errors is not executable")

        val instance: R = if (useCallable.parameters.size == withParameters.size) {
            useCallable.call(*withParameters.map { it.second }.toTypedArray())
        } else {
            useCallable.callBy(withParameters.toMap())
        }
        return instance
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <DT, RT, R> from(usingCallable: KCallable<R>,
                             dispatchInstance: DT?,
                             receiverInstance: RT?,
                             valueProvider: NamedValueProvider,
                             treatMissingAsNullForNullableConstructorParameters: Boolean = true): CallablePlan<DT, RT, R> {

            val entriesFromProvider = valueProvider.entries().map { it.first.substringBefore('.') }.toSet()
            val usedEntriesFromProvider = hashSetOf<String>()
            val satisfiedParameters = arrayListOf<KParameter>()

            val paramErrors = arrayListOf<Pair<KParameter, CallableError>>()
            val paramWarnings = arrayListOf<Pair<KParameter, CallableWarning>>()

            val orderedParamValues = arrayListOf<Pair<KParameter, Any?>>()

            fun consumeParameter(prop: KParameter) {
                satisfiedParameters.add(prop)
            }

            fun markValueMatchedSomething(key: String) {
                usedEntriesFromProvider.add(key)
            }

            fun errorParam(param: KParameter, error: CallableError) {
                paramErrors.add(Pair(param, error))
            }

            fun warnParam(param: KParameter, warning: CallableWarning) {
                paramWarnings.add(Pair(param, warning))
            }

            fun useParam(param: KParameter, rawValue: Any?) {
                if (rawValue != null && !param.type.isAssignableFromOrSamePrimitive(rawValue.javaClass)) {
                    errorParam(param, CallableError.WRONG_TYPE)
                } else {
                    orderedParamValues.add(Pair(param, rawValue))
                }
            }

            usingCallable.parameters.forEach { paramDef ->
                when (paramDef.kind) {
                    KParameter.Kind.INSTANCE -> orderedParamValues.add(Pair(paramDef, dispatchInstance))
                    KParameter.Kind.EXTENSION_RECEIVER -> orderedParamValues.add(Pair(paramDef, receiverInstance))
                    KParameter.Kind.VALUE -> {
                        val paramName = paramDef.name ?: throw IllegalStateException("callable has parameter with unknown name")
                        val isMissing = !valueProvider.existsByName(paramName, paramDef.type.javaType)

                        if (!isMissing) {
                            markValueMatchedSomething(paramName)
                        }

                        if (isMissing) {
                            if (paramDef.isOptional) {
                                // this is ok, optional parameter not resolved will have default parameter value of method
                                consumeParameter(paramDef)
                            } else if (paramDef.type.isMarkedNullable) {
                                if (treatMissingAsNullForNullableConstructorParameters) {
                                    // default value for datatype is ok if null and nullable, or is non null and matches type
                                    useParam(paramDef, null)
                                    consumeParameter(paramDef)
                                    warnParam(paramDef, CallableWarning.DEFAULT_VALUE_USED_FOR_DATATYPE)
                                } else {
                                    errorParam(paramDef, CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER)
                                }
                            } else {
                                errorParam(paramDef, CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER)
                                errorParam(paramDef, CallableError.NULL_VALUE_NON_NULLABLE_TYPE)
                            }
                        } else {
                            val paramVal = valueProvider.valueByName(paramName, paramDef.type.javaType)

                            if (paramVal == null && !paramDef.type.isMarkedNullable) {
                                // value coming in as null for non-nullable type
                                errorParam(paramDef, CallableError.NULL_VALUE_NON_NULLABLE_TYPE)
                            } else {
                                // value present, and can be set
                                useParam(paramDef, paramVal)
                                consumeParameter(paramDef)
                            }
                        }
                    }
                }
                Unit
            }

            val unusedEntriesFromProvider = entriesFromProvider - usedEntriesFromProvider

            return CallablePlan(usingCallable, dispatchInstance, receiverInstance,
                    orderedParamValues, paramErrors, paramWarnings,
                    satisfiedParameters, unusedEntriesFromProvider)

        }
    }
}

class ConstructionPlan <T : Any, R : T>(val constructClass: KClass<T>,
                                        val constructType: Type,
                                        val callablePlan: CallablePlan<Any, Nothing, R>,
                                        val thenSetProperties: List<Pair<KMutableProperty1<T, Any?>, Any?>>,
                                        val propertyErrors: List<Pair<String, ConstructionError>>,
                                        val propertyWarnings: List<Pair<String, ConstructionWarning>>) {
    val parameterErrors: List<Pair<KParameter, CallableError>> get() = callablePlan.parameterErrors
    val parameterWarnings: List<Pair<KParameter, CallableWarning>> get() = callablePlan.parameterWarnings
    val errorCount: Int = callablePlan.errorCount + propertyErrors.groupBy { it.first }.size
    val warningCount: Int = callablePlan.warningCount + propertyWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount > 0
    val withParameters: List<Pair<KParameter, Any?>> get() = callablePlan.withParameters

    fun execute(): T {
        if (hasErrors) throw IllegalStateException("Constructor binding that has errors is not executable")

        val instance: T = callablePlan.execute()
        thenSetProperties.forEach {
            it.first.set(instance, it.second)
        }
        return instance
    }

    companion object {
        // TODO: cache is only holding strong references to Class or Companion objects, but if a class reloader is used this could be a problem
        data class CacheKey(val constructClass: KClass<*>, val constructType: Type, val usingCallable: KCallable<*>,
                            val valueProvider: NamedValueProvider,
                            val treatMissingAsNullForNullableConstructorParameters: Boolean,
                            val treatUnusedValuesFromProviderAsErrors: Boolean)

        private val planCache: MutableMap<CacheKey, ConstructionPlan<*, *>> = ConcurrentHashMap()

        @Suppress("UNCHECKED_CAST")
        fun <T : Any, INSTANCE : T> from(constructClass: KClass<T>,
                                         constructType: Type,
                                         usingCallable: KCallable<INSTANCE>,
                                         valueProvider: NamedValueProvider,
                                         treatMissingAsNullForNullableConstructorParameters: Boolean = true,
                                         treatUnusedValuesFromProviderAsErrors: Boolean = true): ConstructionPlan<T, INSTANCE> {

            val key = CacheKey(constructClass, constructType, usingCallable, valueProvider,
                    treatMissingAsNullForNullableConstructorParameters, treatUnusedValuesFromProviderAsErrors)
            val constructionPlan = planCache.computeIfAbsent(key) {
                val isConstructorCall = constructClass.constructors.any { it == usingCallable }
                val isCompanionCall = constructClass.companionObject?.declaredFunctions?.any { it == usingCallable } ?: false

                if (!isConstructorCall && !isCompanionCall) {
                    throw IllegalStateException("callable is not from $constructClass nor its companion object")
                }

                val dispatchInstance = if (isCompanionCall || (usingCallable.parameters.size > 0 && usingCallable.parameters[0].kind == KParameter.Kind.INSTANCE && usingCallable.parameters[0].type == constructClass.companionObject?.defaultType)) {
                    constructClass.companionObjectInstance
                } else {
                    null
                }

                if (!constructType.isAssignableFromOrSamePrimitive(usingCallable.returnType)) {
                    throw IllegalArgumentException("the callable does not return the expected type being constructed")
                }

                // create plan for constructor/creator call, and copy out a few of the results
                val callablePlan = CallablePlan.from(usingCallable, dispatchInstance, null, valueProvider, treatMissingAsNullForNullableConstructorParameters)
                val entriesFromProvider = callablePlan.nonmatchingProviderEntries
                val previouslyUsedProperties = callablePlan.satisfiedParameters.map { it.name }.filterNotNull()
                val temp = callablePlan.useCallable.parameters
                        .filter { it.kind == KParameter.Kind.VALUE }
                        .map { it.name }
                        .filterNotNull()
                        .toSet() - callablePlan.parameterErrors.map { it.first.name }.filterNotNull()

                // based on what hasn't yet been satisfied, set properties
                val propertiesOfClass = constructClass.declaredMemberProperties.map { it.name to it }.toMap()

                val usedEntriesFromProvider = hashSetOf<String>()
                val usedPropertiesOfClass = hashSetOf<KProperty1<T, Any?>>()

                val propertyErrors = arrayListOf<Pair<String, ConstructionError>>()
                val propertyWarnings = arrayListOf<Pair<String, ConstructionWarning>>()
                val propertyValues = arrayListOf<Pair<KMutableProperty1<T, Any?>, Any?>>()

                fun markValueMatchedSomething(key: String) {
                    usedEntriesFromProvider.add(key)
                }

                fun consumeProperty(propertyName: String) {
                    propertiesOfClass.get(propertyName)?.let {
                        usedPropertiesOfClass.add(it)
                    }
                }

                fun errorProperty(property: KProperty1<T, *>, error: ConstructionError) {
                    propertyErrors.add(Pair(property.name, error))
                }

                fun errorProperty(propertyName: String, error: ConstructionError) {
                    propertyErrors.add(Pair(propertyName, error))
                }

                fun warnProperty(property: KProperty1<T, *>, warning: ConstructionWarning) {
                    propertyWarnings.add(Pair(property.name, warning))
                }

                fun warnProperty(propertyName: String, warning: ConstructionWarning) {
                    propertyWarnings.add(Pair(propertyName, warning))
                }

                fun useProperty(propDef: KMutableProperty1<T, Any?>, rawValue: Any?) {
                    if (rawValue != null && !propDef.returnType.isAssignableFromOrSamePrimitive(rawValue.javaClass)) {
                        errorProperty(propDef, ConstructionError.WRONG_TYPE)
                    } else {
                        propertyValues.add(Pair(propDef, rawValue))
                    }
                }

                previouslyUsedProperties.forEach { consumeProperty(it) }

                val unsetProperties = propertiesOfClass.entries.map { it.value }.toSet() - usedPropertiesOfClass
                unsetProperties.forEach { propReadOnly ->
                    val propName = propReadOnly.name
                    val isMissing = !valueProvider.existsByName(propName, propReadOnly.returnType.javaType)

                    if (propReadOnly is KMutableProperty1<*, *>) {
                        val propWriteable = propReadOnly as KMutableProperty1<T, Any?>

                        val propVal = valueProvider.valueByName(propName, propWriteable.returnType.javaType)

                        if (!isMissing) {
                            markValueMatchedSomething(propName)
                        }

                        if (isMissing) {
                            // no value for property, which is ok, we just want to know about it
                            warnProperty(propWriteable, ConstructionWarning.MISSING_VALUE_FOR_SETTABLE_PROPERTY)
                        } else {
                            if (propVal == null && !propWriteable.returnType.isMarkedNullable) {
                                // value coming in as null for non-nullable type
                                errorProperty(propWriteable, ConstructionError.NULL_VALUE_NON_NULLABLE_TYPE)
                            } else {
                                // value present, and can be set
                                useProperty(propWriteable, propVal)
                                consumeProperty(propName)
                            }
                        }
                    } else {
                        markValueMatchedSomething(propReadOnly.name)
                        if (isMissing) {
                            // we can't set it, but it doesn't exist so all good (we hope, we can't really say for sure)
                        } else {
                            // we can't set it, but it does align and we have a value for it
                            if (treatUnusedValuesFromProviderAsErrors) {
                                errorProperty(propReadOnly, ConstructionError.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY)
                            } else {
                                warnProperty(propReadOnly, ConstructionWarning.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY)
                            }
                        }
                    }
                }

                val unusedEntriesFromProvider = entriesFromProvider - usedEntriesFromProvider

                unusedEntriesFromProvider.forEach { unusedProp ->
                    if (treatUnusedValuesFromProviderAsErrors) {
                        errorProperty(unusedProp, ConstructionError.HAVE_VALUE_NOT_USED)
                    } else {
                        warnProperty(unusedProp, ConstructionWarning.HAVE_VALUE_NOT_USED)
                    }
                }

                ConstructionPlan<T, INSTANCE>(constructClass, constructType, callablePlan,
                        propertyValues, propertyErrors, propertyWarnings)
            }
            return constructionPlan as ConstructionPlan<T, INSTANCE>
        }

        // TODO: collection type construction
        // TODO: array type construction

        fun <T : Any, C : T> bestPlanFor(constructClass: KClass<T>,
                                         constructType: Type,
                                         valueProvider: NamedValueProvider,
                                         considerCompanionObjectFactories: Boolean = true,
                                         treatMissingAsNullForNullableConstructorParameters: Boolean = true): ConstructionPlan<T, C>? {
            val fromCompanion = if (considerCompanionObjectFactories) {
                constructClass.companionObject?.declaredMemberFunctions?.filter { constructClass.isAssignableFrom(it.returnType) } ?: emptyList()
            } else {
                emptyList()
            }

            val callables = constructClass.constructors + fromCompanion

            @Suppress("UNCHECKED_CAST")
            val plans = callables.map { callable ->
                ConstructionPlan.from<T, C>(constructClass,  constructType, callable as KCallable<C>, valueProvider, treatMissingAsNullForNullableConstructorParameters)
            }.filterNot { it.hasErrors }

            return null
        }
    }
}




