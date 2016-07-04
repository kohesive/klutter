package uy.klutter.binder

import uy.klutter.reflect.isAssignableFromOrSamePrimitive
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
                                   val nonmatchingProviderEntries: Set<String>) {
    val errorCount: Int = parameterErrors.groupBy { it.first }.size
    val warningCount: Int = parameterWarnings.groupBy { it.first }.size
    val hasErrors: Boolean = errorCount > 0
    val hasWarnings: Boolean = warningCount > 0

    fun execute(): R {
        if (hasErrors) throw IllegalStateException("Callable binding that ahs errors is not executable")

        useCallable.isAccessible = true
        val instance: R = if (useCallable.parameters.size == withParameters.size) {
            useCallable.call(*withParameters.map { it.second }.toTypedArray())
        } else {
            useCallable.callBy(withParameters.toMap())
        }
        return instance
    }

    companion object {
        val DEFAULT_treatMissingAsNullForNullableMethodParameters = true

        @Suppress("UNCHECKED_CAST")
        fun <DT, RT, R> from(usingCallable: KCallable<R>,
                             dispatchInstance: DT?,
                             receiverInstance: RT?,
                             valueProvider: NamedValueProvider,
                             treatMissingAsNullForNullableMethodParameters: Boolean = ConstructionBinding.DEFAULT_treatMissingAsNullForNullableConstructorParameters): MethodCallBinding<DT, RT, R> {

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
                                if (treatMissingAsNullForNullableMethodParameters) {
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

            return MethodCallBinding(usingCallable, dispatchInstance, receiverInstance,
                    orderedParamValues, paramErrors, paramWarnings,
                    satisfiedParameters, unusedEntriesFromProvider)

        }
    }
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