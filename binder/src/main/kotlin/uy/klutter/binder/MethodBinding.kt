package uy.klutter.binder

import uy.klutter.reflect.isAssignableFromOrSamePrimitive
import java.lang.reflect.Type
import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaType

class MethodCallBinding<DT, RT, out R>(val useCallable: KCallable<R>,
                                       val dispatchInstance: DT?,
                                       val receiverInstance: RT?,
                                       val withParameters: List<Pair<KParameter, Any?>>,
                                       val parameterErrors: List<Pair<KParameter, CallableError>>,
                                       val parameterWarnings: List<Pair<KParameter, CallableWarning>>,
                                       val satisfiedParameters: List<KParameter>,
                                       val nonmatchingProviderEntries: Set<String>): DeferredExecutable<R> {
    val errorCount: Int = parameterErrors.groupBy { it.first }.size
    val warningCount: Int = parameterWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount > 0

    override val returnType: EitherType = EitherType.ofUnchecked(useCallable.returnType)
    override val executor = {
        if (hasErrors) throw IllegalStateException("Callable binding that has errors is not executable")

        useCallable.isAccessible = true

        val finalParameters = withParameters.map {
            val value = it.second
            it.first to when (value) {
                is DeferredExecutable<*> -> value.executor()
                else -> value
            }
        }

        val instance: R = if (useCallable.parameters.size == finalParameters.size) {
            useCallable.call(*finalParameters.map { it.second }.toTypedArray())
        } else {
            useCallable.callBy(finalParameters.toMap())
        }
        instance
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <DT, RT, R> from(usingCallable: KCallable<R>,
                             dispatchInstance: DT?,
                             receiverInstance: RT?,
                             valueProvider: NamedValueProvider,
                             overrideScope: ValueProviderTargetScope = ValueProviderTargetScope.METHOD): MethodCallBinding<DT, RT, R> {

            val entriesFromProvider = valueProvider.entryNames().map { it.substringBefore('.') }.toSet()
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

            fun useParam(param: KParameter, maybe: ProvidedValue<Any?>) {
                when (maybe) {
                    is ProvidedValue.NestedNamedValueProvider -> {
                        // whatever parameter is expecting, try to construct
                        val constructable = ConstructionBinding.findBestBinding<Any, Any>(param.type.javaType, maybe.value,
                                ConstructionBinding.DEFAULT_considerCompanionObjectFactories,  // TODO: this needs to come from settings or this instance of settings, not defaults
                                ConstructionBinding.DEFAULT_treatUnusedValuesFromProviderAsErrors)   // TODO: this needs to come from settings or this instance of settings, not defaults
                        if (constructable == null || constructable.hasErrors) {
                            errorParam(param, CallableError.SUB_CONSTRUCTION_ERROR)
                        } else {
                            orderedParamValues.add(Pair(param, constructable))
                        }
                    }
                    is ProvidedValue.NestedOrderedValueProvider -> {
                        // whatever parameter is expecting, try to construct
                        val constructable = ConstructionBinding.findBestBinding<Any, Any>(param.type.javaType, maybe.value)
                        if (constructable == null || constructable.hasErrors) {
                            errorParam(param, CallableError.SUB_CONSTRUCTION_ERROR)
                        } else {
                            orderedParamValues.add(Pair(param, constructable))
                        }
                    }
                    is ProvidedValue.Present -> {
                        val testValue = maybe.value

                        val testType = if (testValue is DeferredExecutable<*>) {
                            testValue.returnType.asJava // construction or method call to happen later
                        } else if (testValue != null) {
                            testValue.javaClass
                        } else {
                            Any::class.java
                        }

                        if (testValue != null && !param.type.isAssignableFromOrSamePrimitive(testType)) {
                            errorParam(param, CallableError.WRONG_TYPE)
                        } else {
                            if (maybe is ProvidedValue.Coerced<*, *>) {
                                warnParam(param, CallableWarning.TYPE_CONVERTED)
                            }
                            orderedParamValues.add(Pair(param, testValue))
                        }
                    }
                    is ProvidedValue.Absent -> {
                        // should never be able to get here
                        throw IllegalStateException("Trying to use absent value as parameter value")
                    }
                }
            }

            usingCallable.parameters.forEach { paramDef ->
                when (paramDef.kind) {
                    KParameter.Kind.INSTANCE -> orderedParamValues.add(Pair(paramDef, dispatchInstance))
                    KParameter.Kind.EXTENSION_RECEIVER -> orderedParamValues.add(Pair(paramDef, receiverInstance))
                    KParameter.Kind.VALUE -> {
                        val paramName = paramDef.name ?: throw IllegalStateException("callable has parameter with unknown name")
                        val maybe = valueProvider.valueByName(paramName, EitherType.ofUnchecked(paramDef.type), ValueProviderTargetScope.METHOD)

                        when (maybe) {
                            is ProvidedValue.Absent -> {
                                if (paramDef.isOptional) {
                                    // this is ok, optional parameter not resolved will have default parameter value of method
                                    consumeParameter(paramDef)
                                } else {
                                    errorParam(paramDef, CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER)
                                }
                            }
                            is ProvidedValue.Present,
                            is ProvidedValue.NestedNamedValueProvider,
                            is ProvidedValue.NestedOrderedValueProvider -> {
                                markValueMatchedSomething(paramName)
                                if (maybe.value == null && !paramDef.type.isMarkedNullable) {
                                    errorParam(paramDef, CallableError.NULL_VALUE_NON_NULLABLE_TYPE)
                                } else {
                                    useParam(paramDef, maybe)
                                    consumeParameter(paramDef)
                                }
                            }
                        }
                    }
                }
                Unit
            }

            val unusedEntriesFromProvider = entriesFromProvider - usedEntriesFromProvider

            return MethodCallBinding(usingCallable, dispatchInstance, receiverInstance,
                    orderedParamValues, paramErrors, paramWarnings,
                    satisfiedParameters, unusedEntriesFromProvider)

        }
    }
}

enum class CallableError {
    WRONG_TYPE,
    NULL_VALUE_NON_NULLABLE_TYPE,
    MISSING_VALUE_FOR_REQUIRED_PARAMETER,
    SUB_CONSTRUCTION_ERROR
}

enum class CallableWarning {
    TYPE_CONVERTED
}