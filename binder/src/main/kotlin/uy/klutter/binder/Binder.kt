@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package uy.klutter.binder

import uy.klutter.core.collections.asReadOnly
import uy.klutter.reflect.*
import java.lang.reflect.GenericArrayType
import java.lang.reflect.Type
import java.util.*
import kotlin.reflect.KCallable
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType


interface DeferredExecutable<out T> {
    val executor: () -> T
    val returnType: EitherType
}

inline fun <reified T: Any> eitherTypeOf() = EitherType.ofUnchecked(reifiedType<T>())

data class EitherType constructor(val type: Type?, val ktype: KType?) {
    init {
        if (type == null && ktype == null) throw IllegalArgumentException("Either type or ktype must not be null")
    }

    val asJava: Type get() = type ?: ktype?.javaType ?: throw IllegalStateException("Should have one of Java Type or Ktype")
    val asKotlin: KType? get() = ktype

    @Suppress("UNCHECKED_CAST")
    val asErased: Class<*> get() = asJava.erasedType()

    val isNullable: Boolean get() = asKotlin?.let { it.isMarkedNullable } ?: true // if we can't tell, we have to assume nullable for now

    companion object {
        inline fun <reified T : Any> of(type: Type): EitherType {
            return if (T::class.isAssignableFromOrSamePrimitive(type)) {
                EitherType(type, null)
            } else {
                throw IllegalArgumentException("type is not matching the reified type paramter")
            }
        }

        fun ofUnchecked(type: Type): EitherType = EitherType(type, null)

        inline fun <reified T : Any> of(ktype: KType): EitherType {
            return if (T::class.isAssignableFromOrSamePrimitive(ktype)) {
                EitherType(null, ktype)
            } else {
                throw IllegalArgumentException("ktype is not matching the reified type paramter")
            }
        }

        fun ofUnchecked(ktype: KType): EitherType = EitherType(null, ktype)
    }
}

data class BindingMessage(val target: String, val message: String)
data class BindingPlan<out T>(val deferred: DeferredExecutable<T>, val errors: List<BindingMessage>, val warnings: List<BindingMessage>, val score: BindingScore)
interface BindingScore : Comparable<BindingScore>

private fun fakeAnyType(): Any? = TODO()

class Binder {
    companion object {
        object Defaults {
            val considerCompanionObjectFactories = true
            val treatUnusedValuesFromProviderAsErrors = true
        }

        internal fun <T> illegalDeferred(): () -> T {
            return { throw IllegalStateException("Cannot execute a binding that had errors") }
        }

        internal val anyNullableType: EitherType = run {

            EitherType.ofUnchecked(::fakeAnyType.returnType)
        }

        internal fun arrayComponentTypeFromArrayType(arrayType: EitherType): EitherType {
            return if (arrayType.asJava is GenericArrayType) {
                EitherType.ofUnchecked((arrayType.asJava as GenericArrayType).genericComponentType)
            } else if ((arrayType.asJava as? Class<*>)?.isArray ?: false) {
                EitherType.ofUnchecked((arrayType.asJava as Class<*>).componentType)
            } else {
                throw IllegalStateException("arrayType is not really an Array type, but rather ${arrayType.asJava}")
            }
        }
    }

    @Volatile var considerCompanionObjectFactories = Defaults.considerCompanionObjectFactories
    @Volatile var treatUnusedValuesFromProviderAsErrors = Defaults.treatUnusedValuesFromProviderAsErrors

    fun allowCompanionObjectFactories(): Binder {
        considerCompanionObjectFactories = true
        return this
    }

    fun disallowCompanionObjectFactories(): Binder {
        considerCompanionObjectFactories = false
        return this
    }

    fun considerUnusedValuesFromProvidersAsErrors(): Binder {
        treatUnusedValuesFromProviderAsErrors = true
        return this
    }

    fun ignoreUnusedValuesFromProviders(): Binder {
        treatUnusedValuesFromProviderAsErrors = false
        return this
    }

    fun <T : Any> executeUsingCallable(instance: Any?, receiver: Any?, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> executeUsingCompanionFunction(companion: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> executeUsingConstructor(callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> executeUsingReceiverFunction(receiver: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> executeUsingInstanceReceiverFunction(instance: Any, receiver: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> constructUsingCallable(instance: Any?, receiver: Any?, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> constructUsingCompanionFunction(companion: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> constructUsingConstructor(callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> constructUsingReceiverFunction(receiver: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T : Any> constructUsingInstanceReceiverFunction(instance: Any, receiver: Any, callable: KCallable<T>, valueProvider: NamedValueProvider): BindingPlan<T> {
        return TODO()
    }

    fun <T, R : Collection<T>> constructCollection(collectionType: EitherType, valueProvider: OrderedValueProvider): BindingPlan<R> {
        return TODO()
    }

    // currently we only can construct maps that have string keys
    fun <K: String, V, R : Map<K, V>> constructMap(mapType: EitherType, valueProvider: NamedValueProvider): BindingPlan<R> {
        val deferredFunc = fun(): R {
            if (!Map::class.isAssignableFrom(mapType.asJava)) {
                throw IllegalArgumentException("mapType is not of Map interface, instead was ${mapType.asJava}")
            }

            if (Map::class == mapType.asKotlin || Map::class.java == mapType.asJava || java.util.Map::class.java == mapType.asJava) {
                // if Map interface, create HashMap
                val result: MutableMap<K, V> = HashMap()
                // TODO: try to determine value type parameter instead of Any
                val valueType = anyNullableType

                valueProvider.entryNames().map { it to valueProvider.valueByName(it, valueType, ValueProviderTargetScope.CONSTRUCTOR)}

                @Suppress("UNCHECKED_CAST")
                return if (mapType.asJava.isMutableMap()) {
                    result
                } else {
                    result.asReadOnly()
                } as R
            } else {
                // create the map type they want
                // TODO: try to determine value type parameter instead of Any
                // TODO: if known Map type then do normal things, if unknown Map type we could have unknown problems
                return HashMap<K,V>() as R
            }
        }

        val deferredExec = object : DeferredExecutable<R> {
            override val executor: () -> R
                get() = deferredFunc
            override val returnType: EitherType
                get() = mapType
        }

        return BindingPlan(deferredExec, emptyList(), emptyList(), MapConstructBindingScore())
    }

    private class MapConstructBindingScore : BindingScore {
        override fun compareTo(other: BindingScore): Int {
            return 0
        }
    }

    private class ArrayConstructBindingScore : BindingScore {
        override fun compareTo(other: BindingScore): Int {
            return 0
        }
    }

    fun <CT> constructArrayByType(arrayType: EitherType, valueProvider: OrderedValueProvider): BindingPlan<Array<CT>> {
        return constructArrayByComponentType(arrayComponentTypeFromArrayType(arrayType), valueProvider)
    }

    fun <CT> constructArrayByComponentType(arrayComponentType: EitherType, valueProvider: OrderedValueProvider): BindingPlan<Array<CT>> {
        val deferredFunc = fun(): Array<CT> {
            val temp = arrayListOf<Any?>()
            val ct = arrayComponentType.asErased
            valueProvider.valueSequence(arrayComponentType).forEach {
                val value = if (!arrayComponentType.isNullable) it.value!! else it.value
                if (!ct.isInstance(value)) throw IllegalStateException("Value for array is not of component type")
                temp.add(value)
            }
            val target = java.lang.reflect.Array.newInstance(ct, temp.size)
            @Suppress("UNCHECKED_CAST")
            temp.toArray(target as Array<CT>)
            return target
        }

        val arrayType = java.lang.reflect.Array.newInstance(arrayComponentType.asErased, 0).javaClass

        val deferredExec = object : DeferredExecutable<Array<CT>> {
            override val executor: () -> Array<CT>
                get() = deferredFunc
            override val returnType: EitherType
                get() = EitherType.ofUnchecked(arrayType)
        }

        return BindingPlan(deferredExec, emptyList(), emptyList(), ArrayConstructBindingScore())
    }
}