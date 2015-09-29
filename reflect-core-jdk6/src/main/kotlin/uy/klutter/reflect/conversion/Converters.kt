package uy.klutter.reflect.conversion

import uy.klutter.reflect.TypeReference
import uy.klutter.reflect.erasedType
import uy.klutter.reflect.fullType
import uy.klutter.reflect.isAssignableFrom
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

public @Volatile var DefaultTypeConverter: TypeConverters = TypeConverters()

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public class TypeConverters(val parent: TypeConverters? = null) {
    private val exactConvertersMap = ConcurrentHashMap<Pair<Type, Type>, ExactConverter>()

    public @Volatile var permiteEnumToEnum: Boolean = false

    @Suppress("UNCHECKED_CAST")
    public fun <T: Any, R: Any> register(fromType: Type, toType: Type, convertFunc: ExactConverter.(T)->R) {
        val ec = ExactConverter(fromType, toType, convertFunc as ExactConverter.(Any)->Any)
        exactConvertersMap.put(ec.key, ec)
    }

    public fun <T: X, X: Any, R: Any> register(fromType: TypeReference<T>, toType: TypeReference<R>, convertFunc: ExactConverter.(X)->R) {
        register(fromType.type, toType.type, convertFunc)
    }

    @Suppress("UNCHECKED_CAST")
    public inline fun <reified T : Any, reified R : Any> register(noinline converter: ExactConverter.(T) -> R) {
        register(fullType<T>(), fullType<R>(), converter)
    }


    public fun hasConverter(fromType: Type, toType: Type): Boolean {
        return (parent?.findConverter(fromType, toType) ?: findConverter(fromType, toType)) != null
    }

    public fun <T: Any, R: Any> hasConverter(fromType: TypeReference<T>, toType: TypeReference<R>): Boolean {
        return hasConverter(fromType.type, toType.type)
    }

    public fun findConverter(fromType: Type, toType: Type): ExactConverter? {
        return try {
            parent?.findConverter(fromType, toType) ?: exactConvertersMap.concurrentGetOrPut(Pair(fromType, toType)) {
                if (isPrimitiveConversion(fromType, toType)) {
                    ExactConverter(fromType, toType, primitiveConversion)
                }  else {
                    throw IllegalStateException()
                }
            }
        }
        catch (ex: IllegalStateException) {
            null
        }
    }

    public fun <T: Any, R: Any> findConverter(fromType: TypeReference<T>, toType: TypeReference<R>): ExactConverter? {
        return findConverter(fromType.type, toType.type)
    }

    @Suppress("UNCHECKED_CAST")
    public fun <T: Any, R: Any> convertValue(fromType: Type, toType: Type, value: T): R {
        val converter = findConverter(fromType, toType) ?: throw IllegalStateException("No converter registered from ${fromType} to ${toType} (value ${value})")
        return with (converter) { convertFunc(value) } as R
    }

    public fun <T: Any, R: Any> convertValue(fromType: TypeReference<T>, toType: TypeReference<R>, value: T): R {
        return convertValue(fromType.type, toType.type, value)
    }

    public inline fun <reified T: Any, reified R: Any> convertValue(value: T): R {
        return convertValue<T,R>(fullType<T>(), fullType<R>(), value)
    }

    private data class ExactConverter(val fromType: Type, val toType: Type, val convertFunc: ExactConverter.(Any) -> Any) {
        val key: Pair<Type,Type> = Pair(fromType, toType)
    }

    private val primitiveConversion = fun ExactConverter.(value: Any): Any {
        return when (value) {
            is String -> when (toType) {
                String::class.java -> value     // identity
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toShort().toByte()
                Int::class.java, java.lang.Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value)
                BigInteger::class.java -> BigDecimal(value)
                CharSequence::class.java -> value
                ByteArray::class.java -> value.toByteArray() // UTF-8 always
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().filter { it.name() == value }.first() ?:  IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}, no matching value ${value}")
                    } else {
                        throw IllegalStateException("Unknown String conversion from ${fromType} to ${toType}")
                    }
                }
            }
            is CharSequence -> when (toType) {
                String::class.java -> value.toString()
                else -> throw IllegalStateException("Unknown CharSequence conversion from ${fromType} to ${toType}")
            }
            is Number -> when (toType) {
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toByte()
                Int::class.java, java.lang.Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value.toDouble())
                BigInteger::class.java -> BigDecimal(value.toDouble())
                String::class.java -> value.toString()
                Char::class.java, java.lang.Character::class.java -> value.toChar()
                Boolean::class.java, java.lang.Boolean::class.java -> value != 0
                Date::class.java -> Date(value.toLong())
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().get(value.toInt())
                    } else {
                        throw IllegalStateException("Unknown number conversion from ${fromType} to ${toType}")
                    }
                }
            }
            is Char -> when (toType) {
                Char::class.java, java.lang.Character::class.java -> value
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toByte()
                Int::class.java, java.lang.Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value.toInt())
                BigInteger::class.java -> BigDecimal(value.toInt())
                String::class.java -> value.toString()
                Boolean::class.java, java.lang.Boolean::class.java -> value == '1' || value == 'T' || value == 't'
                else -> throw IllegalStateException("Unknown char conversion from ${fromType} to ${toType}")
            }
            is Date -> when (toType) {
                Date::class.java -> value   // identity
                Long::class.java, java.lang.Long::class.java -> value.time
                else -> throw IllegalStateException("Unknown date conversion from ${fromType} to ${toType}")
            }
            is ByteArray -> when (toType) {
                String::class.java -> String(value, "UTF-8") // always UTF-8
                else -> throw IllegalStateException("Unknown ByteArray conversion from ${fromType} to ${toType}")
            }
            is Enum<*> -> when (toType) {
                String::class.java -> value.name()
                Byte::class.java, java.lang.Byte::class.java -> value.ordinal().toByte()
                Short::class.java, java.lang.Short::class.java -> value.ordinal().toShort()
                Int::class.java, java.lang.Integer::class.java -> value.ordinal()
                Long::class.java, java.lang.Long::class.java -> value.ordinal().toLong()
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum && permiteEnumToEnum) {
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().filter { it.name() == value.name() }.firstOrNull() ?: IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}, no matching value ${value}")
                    } else {
                        throw IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}")
                    }
                }
            }
            else -> throw IllegalArgumentException("No primitive conversion for ${fromType} to ${toType} for value ${value}")
        }
    }

    private fun isPrimitiveConversion(fromType: Type, toType: Type): Boolean {
        val fromErased = fromType.erasedType()
        return when {
            String::class.isAssignableFrom(fromType) -> when (toType) {
                String::class.java,
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, java.lang.Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                CharSequence::class.java ,
                ByteArray::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                       true
                    } else {
                       false
                    }
                }
            }
            CharSequence::class.isAssignableFrom(fromType) -> when (toType) {
                String::class.java -> true
                else -> false
            }
            Number::class.isAssignableFrom(fromType) -> when (toType) {
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, java.lang.Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                String::class.java,
                Char::class.java, java.lang.Character::class.java,
                Boolean::class.java, java.lang.Boolean::class.java,
                Date::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                        true
                    } else {
                        false
                    }
                }
            }
            Char::class.isAssignableFrom(fromType),
            java.lang.Character::class.isAssignableFrom(fromType) -> when (toType) {
                Char::class.java, java.lang.Character::class.java,
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, java.lang.Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                String::class.java,
                Boolean::class.java, java.lang.Boolean::class.java -> true
                else -> false
            }
            Date::class.isAssignableFrom(fromType) -> when (toType) {
                Date::class.java,
                Long::class.java, java.lang.Long::class.java -> true
                else -> false
            }
            fromType == ByteArray::class.java -> when (toType) {
                String::class.java -> true
                else -> false
            }
            Enum::class.isAssignableFrom(fromType) -> when (toType) {
                String::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Short::class.java, java.lang.Short::class.java,
                Int::class.java, java.lang.Integer::class.java,
                Long::class.java, java.lang.Long::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum && permiteEnumToEnum) {
                       true
                    } else {
                       false
                    }
                }
            }
            else -> false
        }
    }


}
