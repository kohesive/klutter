package uy.klutter.reflect.full

import java.lang.reflect.Method
import java.util.*
import kotlin.reflect.*
import kotlin.reflect.jvm.reflect


class KCallableFuncRefOrLambda<T : Function<R>, out R : Any?> private constructor(private val _functionClass: Class<T>, private val _kfunc: KFunction<R>, private val _name: String? = null, private val _annotations: List<Annotation>?) : KCallable<R> {
    companion object {
        public fun <T : Function<R>, R : Any?> fromInstance(functionInstance: T): KCallableFuncRefOrLambda<T, R> {
            val kfunc = functionInstance.reflect() ?: throw IllegalStateException("The function instance isn't reflect-able")
            return KCallableFuncRefOrLambda(functionInstance.javaClass, kfunc, null, null)
        }

        public fun <T : Function<R>, R : Any?> fromInstance(functionInstance: T, name: String, annotations: List<Annotation>): KCallableFuncRefOrLambda<T, R> {
            val kfunc = functionInstance.reflect() ?: throw IllegalStateException("The function instance isn't reflect-able")
            return KCallableFuncRefOrLambda(functionInstance.javaClass, kfunc, name, annotations)
        }
    }

    private val _invokeMethod: Method = _functionClass.getMethods().filter { method ->
        method.getName() == "invoke" &&
                !method.isBridge &&
                method.parameterCount == _kfunc.parameters.size
    }.first().apply { isAccessible = true }

    override val name: String = _name ?: _kfunc.name
    override val returnType: KType = _invokeMethod.returnType.kotlin.defaultType
    override val annotations: List<Annotation> = _annotations ?: emptyList<Annotation>() + _kfunc.annotations

    override val parameters: List<KParameter> by lazy {
        val receiver = listOf(object : KParameter {
            override val index: Int = 0
            override val isOptional: Boolean = false
            override val kind: KParameter.Kind = KParameter.Kind.INSTANCE
            override val name: String? = null
            override val type: KType = object : KType {    // TODO: this is fragile if anyone looks at this type and tries to do much with it (not resolved!)
                override val isMarkedNullable: Boolean
                    get() = false
            }
            override val annotations: List<Annotation> = _annotations ?: emptyList<Annotation>() + _kfunc.annotations
        })
        val realParams = _invokeMethod.parameters.withIndex().zip(_kfunc.parameters).map {
                    object : KParameter {
                        override val index: Int = it.first.index + 1
                        override val isOptional: Boolean = it.second.isOptional
                        override val kind: KParameter.Kind = it.second.kind
                        override val name: String? = it.second.name
                        override val type: KType = it.first.value.getType().kotlin.defaultType
                        override val annotations: List<Annotation> = _annotations ?: emptyList<Annotation>() + _kfunc.annotations
                    }
                }
        receiver + realParams
    }

    @Suppress("UNCHECKED_CAST")
    override fun call(vararg args: Any?): R {
        return _invokeMethod.invoke(args[0], *args.drop(1).toTypedArray()) as R
    }

    @Suppress("UNCHECKED_CAST")
    override fun callBy(args: Map<KParameter, Any?>): R {
        val parameters = parameters
        val arguments = ArrayList<Any?>(parameters.size)

        for (parameter in parameters) {
            when {
                args.containsKey(parameter) -> {
                    arguments.add(args[parameter])
                }
                else -> {
                    throw IllegalArgumentException("No argument provided for a required parameter: $parameter")
                }
            }
        }

        return call(*arguments.toTypedArray())
    }


}

