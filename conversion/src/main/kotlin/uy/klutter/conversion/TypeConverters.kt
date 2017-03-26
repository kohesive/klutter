package uy.klutter.conversion

import uy.klutter.reflect.TypeReference
import uy.klutter.reflect.reifiedType
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") class TypeConverters(val parent: TypeConverters? = null) {
    private val specialConverters = ArrayList<ConverterSet>()
    private val exactConvertersMap = ConcurrentHashMap<Pair<Type, Type>, ExactConverter>()

    @Suppress("UNCHECKED_CAST") fun <T : Any, R : Any> register(fromType: Type, toType: Type, convertFunc: ExactConverter.(T) -> R) {
        val ec = ExactConverter(fromType, toType, convertFunc as ExactConverter.(Any) -> Any)
        exactConvertersMap.put(ec.key, ec)
    }

    fun <T : X, X : Any, R : Any> register(fromType: TypeReference<T>, toType: TypeReference<R>, convertFunc: ExactConverter.(X) -> R) {
        register(fromType.type, toType.type, convertFunc)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any, reified R : Any> register(noinline converter: ExactConverter.(T) -> R) {
        register(reifiedType<T>(), reifiedType<R>(), converter)
    }

    fun register(converterSet: ConverterSet) {
        specialConverters.add(converterSet)
    }

    fun hasConverter(fromType: Type, toType: Type): Boolean {
        val tempParent = parent ?: if (this != TypeConversionConfig.defaultConverter) TypeConversionConfig.defaultConverter else null
        return (tempParent?.findConverter(fromType, toType) ?: findConverter(fromType, toType)) != null
    }

    fun <T : Any, R : Any> hasConverter(fromType: TypeReference<T>, toType: TypeReference<R>): Boolean {
        return hasConverter(fromType.type, toType.type)
    }

    fun findConverter(fromType: Type, toType: Type): ExactConverter? {
        return try {
            val tempParent = parent ?: if (this != TypeConversionConfig.defaultConverter) TypeConversionConfig.defaultConverter else null
            tempParent?.findConverter(fromType, toType) ?: exactConvertersMap.getOrPut(Pair(fromType, toType)) {
                val acceptingConverter = specialConverters.firstOrNull { it.predicate(fromType, toType) } ?: throw IllegalStateException()
                ExactConverter(fromType, toType, { value -> acceptingConverter.convert(fromType, toType, value) })
            }
        } catch (ex: IllegalStateException) {
            null
        }
    }

    fun <T : Any, R : Any> findConverter(fromType: TypeReference<T>, toType: TypeReference<R>): ExactConverter? {
        return findConverter(fromType.type, toType.type)
    }

    @Suppress("UNCHECKED_CAST") fun <T : Any, R : Any> convertValue(fromType: Type, toType: Type, value: T): R {
        val converter = findConverter(fromType, toType) ?: throw IllegalStateException("No converter registered from ${fromType} to ${toType} (value ${value})")
        return with(converter) { convertFunc(value) } as R
    }

    fun <T : Any, R : Any> convertValue(fromType: TypeReference<T>, toType: TypeReference<R>, value: T): R {
        return convertValue(fromType.type, toType.type, value)
    }

    inline fun <reified T : Any, reified R : Any> convertValue(value: T): R {
        return convertValue<T, R>(reifiedType<T>(), reifiedType<R>(), value)
    }

    data class ExactConverter(val fromType: Type, val toType: Type, val convertFunc: ExactConverter.(Any) -> Any) {
        val key: Pair<Type, Type> = Pair(fromType, toType)
    }
}


