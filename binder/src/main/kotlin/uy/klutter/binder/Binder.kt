@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package uy.klutter.binder

import uy.klutter.core.collections.asReadOnly
import uy.klutter.reflect.*
import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*
import kotlin.reflect.KCallable
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType


interface DeferredExecutable<out T> {
    val executor: () -> T
    val returnType: EitherType
}

inline fun <reified T> eitherTypeOf() = EitherType.ofUnchecked(reifiedType<T>())

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
                throw IllegalArgumentException("type is not matching the reified type paramteer")
            }
        }

        fun ofUnchecked(type: Type): EitherType = EitherType(type, null)

        inline fun <reified T : Any> of(ktype: KType): EitherType {
            return if (T::class.isAssignableFromOrSamePrimitive(ktype)) {
                EitherType(null, ktype)
            } else {
                throw IllegalArgumentException("ktype is not matching the reified type parameter")
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
    fun <K: String, V> constructMap(mapType: EitherType, valueProvider: NamedValueProvider): BindingPlan<Map<K,V>> {
        val deferredFunc = fun(): Map<K,V> {
            if (!Map::class.isAssignableFrom(mapType.asJava)) {
                throw IllegalArgumentException("mapType is not of Map interface, instead was ${mapType.asJava}")
            }

            val mapTypeJava = mapType.asJava
            val (keyType, valueType) = if (mapTypeJava is ParameterizedType && mapTypeJava.actualTypeArguments.size == 2) {
                EitherType.ofUnchecked(mapTypeJava.actualTypeArguments[0]) to
                        EitherType.ofUnchecked(mapTypeJava.actualTypeArguments[1])
            } else EitherType.ofUnchecked(String::class.java) to anyNullableType

            @Suppress("UNCHECKED_CAST")
            fun makeCheckedMutableMap(wrapMap: MutableMap<K,V> = HashMap<K, V>()): MutableMap<K, V> =
                 Collections.checkedMap<K, V>(wrapMap, keyType.asErased as Class<K>, valueType.asErased as Class<V>)

            fun fillMap(mapToFill: MutableMap<K, V>  = makeCheckedMutableMap()): MutableMap<K, V> {
                @Suppress("UNCHECKED_CAST")
                valueProvider.entryNames().forEach {
                    // TODO: nested named value providers?  or sequence value providers?
                    // for the following we need to have the correctly known value type or
                    // assume a generic type if we have no guidance:
                    //   if the value is a list convert to sequence value provider or shove into map?
                    //   if the value is a sequence value provider, convert to a list or array?
                    //   if the value is a map convert to a named value provider or shove into map?
                    //   if the value is a named value provider, convert to a nested map or object?
                    val value = valueProvider.valueByName(it, valueType, ValueProviderTargetScope.CONSTRUCTOR).let {
                        if (!valueType.isNullable) it.value!! else it.value
                    }
                    mapToFill.put(it as K, value as V)
                }
                return mapToFill
            }

            fun isMapInterface(checkType: EitherType): Boolean =
                    Map::class == checkType.asKotlin || Map::class.java == checkType.asErased || java.util.Map::class.java == checkType.asErased

            // if Map interface, create HashMap and return as MutableMap o Map
            if (isMapInterface(mapType)) {
                val result = fillMap()

                return if (mapType.asJava.isMutableMap()) {
                    result
                } else {
                    result.asReadOnly()
                }
            } else { // otherwise they want a specific map type
                // order of constructor call (to avoid problems with some immutable maps we cannot modify later):

                // 1. constructor that accepts a Map in the constructor with same generics
                // 2. static that accepts Map in the constructor with same generics and returns this type
                // 3. default constructor, then add all after
                if (mapTypeJava is ParameterizedType && mapTypeJava.actualTypeArguments.size == 2) {
                    val constructType = mapTypeJava.rawType as Class<*>
                    val constructorTakingAMap = constructType.declaredConstructors.firstOrNull {
                        it.parameterCount == 1 &&
                                it.genericParameterTypes[0] is ParameterizedType &&
                                isMapInterface(EitherType.ofUnchecked((it.genericParameterTypes[0] as ParameterizedType).rawType)) &&
                                (it.genericParameterTypes[0] as ParameterizedType).actualTypeArguments.size == 2
                    }
                    if (constructorTakingAMap != null) {
                        val constructorMap = fillMap()
                        @Suppress("UNCHECKED_CAST")
                        return constructorTakingAMap.newInstance(constructorMap) as MutableMap<K, V>
                    } else {
                        // TODO: scan statics for factory (#2 above)
                        @Suppress("UNCHECKED_CAST")
                        return fillMap(makeCheckedMutableMap(constructType.newInstance() as MutableMap<K, V>))
                    }
                }

                // TODO: feature switch for the static factory in construction selection, or some way to pick for this class

                // TODO: other map constructor types (i.e. Guava)

                // TODO: allow untyped maps?!?
                throw IllegalStateException("untyped map construction is dangerous, maybe later")

            }
        }

        val deferredExec = object : DeferredExecutable<Map<K,V>> {
            override val executor: () -> Map<K,V>
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
                if (value != null && !ct.isInstance(value)) throw IllegalStateException("Value for array is not of component type")
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