package uy.klutter.binder

import uy.klutter.reflect.isAssignableFromOrSamePrimitive
import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.isAccessible

class MethodCallBinding<DT, RT, out R>(val useCallable: KCallable<R>,
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
        if (hasErrors) throw IllegalStateException("Callable binding that has errors is not executable")

        useCallable.isAccessible = true
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
                             overrideScope: ValueProviderTargetScope = ValueProviderTargetScope.METHOD): MethodCallBinding<DT, RT, R> {

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

            fun useParam(param: KParameter, maybe: ProvidedValue<Any>) {
                when (maybe) {
                    is ProvidedValue.Present -> {
                        val rawValue = maybe.value
                        if (rawValue != null && !param.type.isAssignableFromOrSamePrimitive(rawValue.javaClass)) {
                            errorParam(param, CallableError.WRONG_TYPE)
                        } else {
                            if (maybe is ProvidedValue.Coerced<*, *>) {
                                warnParam(param, CallableWarning.TYPE_CONVERTED)
                            }
                            orderedParamValues.add(Pair(param, rawValue))
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
                        val maybe = valueProvider.valueByName(paramName, paramDef.type, ValueProviderTargetScope.METHOD)

                        when (maybe) {
                            is ProvidedValue.Absent -> {
                                if (paramDef.isOptional) {
                                    // this is ok, optional parameter not resolved will have default parameter value of method
                                    consumeParameter(paramDef)
                                } else {
                                    errorParam(paramDef, CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER)
                                }
                            }
                            is ProvidedValue.Present -> {
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
    MISSING_VALUE_FOR_REQUIRED_PARAMETER
}

enum class CallableWarning {
    TYPE_CONVERTED
}