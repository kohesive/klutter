package uy.klutter.binder

import kotlinx.support.jdk8.collections.computeIfAbsent
import uy.klutter.reflect.*
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.*
import kotlin.reflect.jvm.*

// TODO: type conversions

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

enum class ConstructionError {
    COERCION_ERROR,
    WRONG_TYPE,
    NULL_VALUE_NON_NULLABLE_TYPE,
    HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY,
    HAVE_VALUE_NOT_USED,
    SUB_CONSTRUCTION_ERROR,
    WRONG_TYPE_FOR_COLLECTION_VALUE
}

enum class ConstructionWarning {
    MISSING_VALUE_FOR_SETTABLE_PROPERTY,
    HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY,
    HAVE_VALUE_NOT_USED,
    TYPE_CONVERTED
}

interface DeferredExecutable<out T> {
    fun execute(): T
    val returnType: Type
}

class ConstructionBinding<T : Any, out R : T>(val constructClass: KClass<T>,
                                              val constructType: Type,
                                              val callableBinding: MethodCallBinding<Any, Nothing, R>,
                                              val thenSetProperties: List<Pair<KMutableProperty1<T, Any?>, Any?>>,
                                              val propertyErrors: List<Pair<String, ConstructionError>>,
                                              val propertyWarnings: List<Pair<String, ConstructionWarning>>,
                                              val thenFillValues: Sequence<Any?> = emptySequence(),
                                              val fillFunc: T.(Any?) -> Unit = {}) : DeferredExecutable<T> {
    val parameterErrors: List<Pair<KParameter, CallableError>> get() = callableBinding.parameterErrors
    val parameterWarnings: List<Pair<KParameter, CallableWarning>> get() = callableBinding.parameterWarnings
    val errorCount: Int = callableBinding.errorCount + propertyErrors.groupBy { it.first }.size
    val warningCount: Int = callableBinding.warningCount + propertyWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount > 0
    val withParameters: List<Pair<KParameter, Any?>> get() = callableBinding.withParameters

    override val returnType: Type = constructType
    override fun execute(): T {
        if (hasErrors) throw IllegalStateException("Constructor binding that has errors is not executable")
        val instance: T = callableBinding.execute()
        thenSetProperties.forEach {
            val value = it.second
            val finalValue = when (value) {
                is DeferredExecutable<*> -> value.execute()
                else -> value
            }
            it.first.set(instance, finalValue)
        }
        thenFillValues.forEach {
            instance.fillFunc(it)
        }
        return instance
    }

    companion object {
        val DEFAULT_considerCompanionObjectFactories = true
        val DEFAULT_treatUnusedValuesFromProviderAsErrors = true

        // TODO: cache is only holding strong references to Class or Companion objects, but if a class reloader is used this could be a problem
        data class CacheKey(val constructClass: KClass<*>, val constructType: Type, val usingCallable: KCallable<*>,
                            val valueProvider: NamedValueProvider,
                            val treatUnusedValuesFromProviderAsErrors: Boolean)

        private val planCache: MutableMap<CacheKey, ConstructionBinding<*, *>> = ConcurrentHashMap()

        inline fun <reified T : Any> from(usingCallable: KCallable<T>,
                                          valueProvider: NamedValueProvider,
                                          treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors
        ): ConstructionBinding<T, T> {
            return from(reifiedType<T>(), usingCallable, valueProvider, treatUnusedValuesFromProviderAsErrors)
        }

        @Suppress("UNCHECKED_CAST")
        fun <T : Any, INSTANCE : T> from(constructType: Type,
                                         usingCallable: KCallable<INSTANCE>,
                                         valueProvider: NamedValueProvider,
                                         treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors
        ): ConstructionBinding<T, INSTANCE> {

            @Suppress("UNCHECKED_CAST")
            val constructClass = when (constructType) {
                is Class<*> -> constructType as Class<T>
                is ParameterizedType -> constructType.rawType as Class<T>
                else -> throw IllegalArgumentException("Other types such as ${constructType} not yet supported")
            }.kotlin

            val key = CacheKey(constructClass, constructType, usingCallable, valueProvider, treatUnusedValuesFromProviderAsErrors)

            val constructionPlan = planCache.computeIfAbsent(key) {
                val isConstructorCall = constructClass.constructors.any { it == usingCallable }
                val isCompanionCall = constructClass.companionObject?.declaredFunctions?.any { it == usingCallable } ?: false

                if (!isConstructorCall && !isCompanionCall) {
                    throw IllegalStateException("callable is not from $constructClass nor its companion object")
                }

                val dispatchInstance = if (isCompanionCall || (usingCallable.parameters.size > 0 && usingCallable.parameters[0].kind == KParameter.Kind.INSTANCE && usingCallable.parameters[0].type == constructClass.companionObject?.defaultType)) {
                    constructClass.companionObjectInstance
                    // TODO: what if class is private, we can't get to companion instance at all in a good way

                    /* this doesn't solve the exception:
                     * java.lang.IllegalAccessException: Class kotlin.reflect.jvm.internal.KClassImpl$objectInstance_$1 can not access a member of class uy.klutter.binder.TestConstruction$TestConstructWithCompanionCallables with modifiers "public static final"
                     *
                     *
                    constructClass.companionObject?.let { companion ->
                        val name = companion.simpleName
                        val field = companion.java.enclosingClass.getDeclaredField(name)
                        field.isAccessible = true
                        constructClass.companionObjectInstance
                    }
                    */
                } else {
                    null
                }

                if (!constructType.isAssignableFromOrSamePrimitive(usingCallable.returnType)) {
                    throw IllegalArgumentException("the callable does not return the expected type being constructed")
                }

                // create plan for constructor/creator call, and copy out a few of the results
                val callablePlan = MethodCallBinding.from(usingCallable, dispatchInstance, null, valueProvider, overrideScope = ValueProviderTargetScope.CONSTRUCTOR)
                val entriesFromProvider = callablePlan.nonmatchingProviderEntries
                val previouslyUsedProperties = callablePlan.satisfiedParameters.map { it.name }.filterNotNull()

                // based on what hasn't yet been satisfied, set properties
                val propertiesOfClass = constructClass.declaredMemberProperties
                        .filter { it.javaGetter.isPublic() }
                        .map { it.name to it }.toMap()

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

                fun useProperty(propDef: KMutableProperty1<T, Any?>, maybe: ProvidedValue<Any?>) {
                    when (maybe) {
                        is ProvidedValue.Nested -> {
                            // whatever parameter is expecting, try to construct
                            val constructable = ConstructionBinding.findBestBinding<Any, Any>(propDef.returnType.javaType, maybe.value,
                                    ConstructionBinding.DEFAULT_considerCompanionObjectFactories, // TODO: this needs to come from settings or this instance of settings, not defaults
                                    ConstructionBinding.DEFAULT_treatUnusedValuesFromProviderAsErrors)   // TODO: this needs to come from settings or this instance of settings, not defaults
                            if (constructable == null || constructable.hasErrors) {
                                errorProperty(propDef, ConstructionError.SUB_CONSTRUCTION_ERROR)
                            } else {
                                propertyValues.add(Pair(propDef, constructable))
                            }
                        }
                        is ProvidedValue.Present -> {
                            val testValue = maybe.value

                            val testType = if (testValue is DeferredExecutable<*>) {
                                testValue.returnType // construction or method call to happen later
                            } else if (testValue != null) {
                                testValue.javaClass
                            } else {
                                Any::class.java
                            }

                            if (testValue != null && !propDef.returnType.isAssignableFromOrSamePrimitive(testType)) {
                                errorProperty(propDef, ConstructionError.WRONG_TYPE)
                            } else {
                                if (maybe is ProvidedValue.Coerced<*, *>) {
                                    warnProperty(propDef, ConstructionWarning.TYPE_CONVERTED)
                                }
                                propertyValues.add(Pair(propDef, testValue))
                            }
                        }
                        is ProvidedValue.Absent -> {
                            // should never be able to get here
                            throw IllegalStateException("Trying to use absent value as proeprty value")
                        }
                    }
                }

                previouslyUsedProperties.forEach { consumeProperty(it) }

                val unsetProperties = propertiesOfClass.entries.map { it.value }.toSet() - usedPropertiesOfClass
                unsetProperties.forEach { propReadOnly ->
                    val propName = propReadOnly.name
                    val maybe = valueProvider.valueByName(propName, propReadOnly.returnType, ValueProviderTargetScope.PROPERTY)

                    if (propReadOnly is KMutableProperty1<*, *> && propReadOnly.javaSetter.isPublic()) {
                        val propWriteable = propReadOnly as KMutableProperty1<T, Any?>

                        when (maybe) {
                            is ProvidedValue.Absent -> {
                                // no value for property, which is ok, we just want to know about it
                                warnProperty(propWriteable, ConstructionWarning.MISSING_VALUE_FOR_SETTABLE_PROPERTY)
                            }
                            is ProvidedValue.Present,
                            is ProvidedValue.Nested -> {
                                markValueMatchedSomething(propName)
                                if (maybe.value == null && !propWriteable.returnType.isMarkedNullable) {
                                    // value coming in as null for non-nullable type
                                    errorProperty(propWriteable, ConstructionError.NULL_VALUE_NON_NULLABLE_TYPE)
                                } else {
                                    // value present, and can be set
                                    useProperty(propWriteable, maybe)
                                    consumeProperty(propName)
                                }
                            }
                        }
                    } else {
                        markValueMatchedSomething(propReadOnly.name)
                        when (maybe) {
                            is ProvidedValue.Absent -> {
                                // we can't set it, but it doesn't exist so all good (we hope, we can't really say for sure)
                            }
                            is ProvidedValue.Present,
                            is ProvidedValue.Nested -> {
                                // we can't set it, but it does align and we have a value for it
                                if (treatUnusedValuesFromProviderAsErrors) {
                                    errorProperty(propReadOnly, ConstructionError.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY)
                                } else {
                                    warnProperty(propReadOnly, ConstructionWarning.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY)
                                }
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

                ConstructionBinding<T, INSTANCE>(constructClass, constructType, callablePlan,
                        propertyValues, propertyErrors, propertyWarnings)
            }
            return constructionPlan as ConstructionBinding<T, INSTANCE>
        }

        // TODO: collection type construction
        // TODO: array type construction
        // TODO: all defaults configurable

        inline fun <reified T : Any> findBestBinding(valueProvider: OrderedValueProvider,
                                                     considerCompanionObjectFactories: Boolean = DEFAULT_considerCompanionObjectFactories,
                                                     treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors): ConstructionBinding<T, T>? {
            return findBestBinding(reifiedType<T>(), valueProvider, considerCompanionObjectFactories, treatUnusedValuesFromProviderAsErrors)
        }

        fun <T : Any, C : T> findBestBinding(constructType: Type,
                                             valueProvider: OrderedValueProvider,
                                             considerCompanionObjectFactories: Boolean = DEFAULT_considerCompanionObjectFactories,
                                             treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors): ConstructionBinding<T, C>? {
            // val constructClass = constructType.erasedType().kotlin

            // special case:  Array, Iterator, Collection, List (w y w/o randomaccess), ListIterator
            // special case:  Iterable
            // special case:  Set

            // the actual type is a List vs. MutableList vs. ArrayList vs. LinkedList vs. SomeJaysonList vs ...

            return when {
                constructType is GenericArrayType -> {
                    // easy, construct an array
                    // TODO: array construction
                    throw NotImplementedError("Need to handle special case of Array construction")
                }
                Set::class.isAssignableFrom(constructType) -> {
                    // TODO: Set construction
                    throw NotImplementedError("Need to handle special case of Set construction")
                }
                List::class.isAssignableFrom(constructType) ||
                        Collection::class.isAssignableFrom(constructType) ||
                        ListIterator::class.isAssignableFrom(constructType) ||
                        Iterator::class.isAssignableFrom(constructType) -> {
                    // TODO:  List,Collection,ListIterator,Iterator construction
                    throw NotImplementedError("Need to handle special case of List,Collection,ListIterator,Iterator construction")
                }
                Iterable::class.isAssignableFrom(constructType) -> {
                    // TODO: Iterable construction
                    throw NotImplementedError("Need to handle special case of Iterable construction")
                }
                else -> throw IllegalStateException("Cannot develop a construction plan for type $constructType from an OrderedValueProvider")
            }
        }

        inline fun <reified T : Any> findBestBinding(valueProvider: NamedValueProvider,
                                                     considerCompanionObjectFactories: Boolean = DEFAULT_considerCompanionObjectFactories,
                                                     treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors): ConstructionBinding<T, T>? {
            return findBestBinding(reifiedType<T>(), valueProvider, considerCompanionObjectFactories, treatUnusedValuesFromProviderAsErrors)
        }

        fun <T : Any, C : T> findBestBinding(constructType: Type,
                                             valueProvider: NamedValueProvider,
                                             considerCompanionObjectFactories: Boolean = DEFAULT_considerCompanionObjectFactories,
                                             treatUnusedValuesFromProviderAsErrors: Boolean = DEFAULT_treatUnusedValuesFromProviderAsErrors): ConstructionBinding<T, C>? {

            if (Map::class.isAssignableFrom(constructType)) {
                // TODO: Map construction
                throw NotImplementedError("Need to handle special case of Map construction")
            }

            val constructClass = constructType.erasedType().kotlin

            val fromCompanion = if (considerCompanionObjectFactories) {
                constructClass.companionObject?.declaredMemberFunctions?.filter { constructClass.isAssignableFrom(it.returnType) } ?: emptyList()
            } else {
                emptyList()
            }

            val callables = constructClass.constructors.filter { it.javaConstructor.isPublic() } +
                    fromCompanion.filter { it.javaMethod.isPublic() }

            @Suppress("UNCHECKED_CAST")
            val rawPlans = callables.map { callable ->
                ConstructionBinding.from<T, C>(constructType, callable as KCallable<C>,
                        valueProvider, treatUnusedValuesFromProviderAsErrors)
            }
            val plans = rawPlans.filterNot { it.hasErrors }
                    .sortedWith(Comparator<uy.klutter.binder.ConstructionBinding<T, C>> { o1, o2 ->
                        val isO1Constructor = o1.callableBinding.dispatchInstance == null
                        val isO2Constructor = o1.callableBinding.dispatchInstance == null

                        // calc value params
                        val o1ParamCount = o1.withParameters.count { it.first.kind == KParameter.Kind.VALUE }
                        val o2ParamCount = o2.withParameters.count { it.first.kind == KParameter.Kind.VALUE }

                        // more used constructor parameters wins
                        val o1ActiveParms = o1ParamCount - o1.callableBinding.warningCount // parameters minus suspect parameters
                        val o2ActiveParms = o2ParamCount - o2.callableBinding.warningCount // parameters minus suspect parameters
                        val diff = o1ActiveParms - o2ActiveParms
                        if (diff != 0) return@Comparator diff

                        // less warnings wins
                        val paramDiff = o2.parameterWarnings.size - o1.parameterWarnings.size // reversed positions, higher number is bad
                        val propDiff = o2.propertyWarnings.size - o1.propertyWarnings.size // reversed positions, higher number is bad
                        val warnDiff = if (paramDiff != 0) paramDiff else propDiff
                        if (warnDiff != 0) return@Comparator warnDiff

                        // primary constructor wins over others
                        val o1IsPrimary = if (constructClass.primaryConstructor == o1.callableBinding.useCallable) 1 else 0
                        val o2IsPrimary = if (constructClass.primaryConstructor == o2.callableBinding.useCallable) 1 else 0
                        val primarDiff = o1IsPrimary - o2IsPrimary
                        if (primarDiff != 0) return@Comparator primarDiff

                        // constructor wins over creator
                        val o1IsConstructor = if (isO1Constructor) 1 else 0
                        val o2IsConstructor = if (isO2Constructor) 1 else 0
                        val typeDiff = o1IsConstructor - o2IsConstructor
                        if (typeDiff != 0) return@Comparator typeDiff

                        // ok raw parameter count this time ignoring warnings
                        val allParmDiff = o1ParamCount - o2ParamCount
                        if (allParmDiff != 0) return@Comparator allParmDiff

                        // ok, we just need to be deterministic at this point, lowest toString value wins.
                        // order is reversed here on purpose since Comparator is reversed later
                        o2.callableBinding.useCallable.toString().compareTo(o1.callableBinding.useCallable.toString())
                    }.reversed())

            return plans.firstOrNull()
        }

    }
}

inline fun <reified T : Any> constructFromValues(valueProvider: NamedValueProvider,
                                                 considerCompanionObjectFactories: Boolean = ConstructionBinding.DEFAULT_considerCompanionObjectFactories,
                                                 treatUnusedValuesFromProviderAsErrors: Boolean = ConstructionBinding.DEFAULT_treatUnusedValuesFromProviderAsErrors): T {
    return ConstructionBinding.findBestBinding<T>(valueProvider, considerCompanionObjectFactories, treatUnusedValuesFromProviderAsErrors)?.execute() ?: throw IllegalStateException("No clear construction binding can be found for ${T::class}")
}


