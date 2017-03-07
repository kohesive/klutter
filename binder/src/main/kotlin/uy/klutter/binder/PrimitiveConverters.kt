package uy.klutter.binder

import uy.klutter.reflect.erasedType
import uy.klutter.reflect.isAssignableFrom
import java.io.File
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL
import java.util.*

class PrimitiveConverters: ConverterSet {
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    override fun predicate(fromType: Type, toType: Type): Boolean {
        val fromErased = fromType.erasedType()
        return when {
            String::class.isAssignableFrom(fromType) -> when (toType) {
                String::class.java,
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                CharSequence::class.java,
                ByteArray::class.java,
                Boolean::class.java, java.lang.Boolean::class.java,
                File::class.java,
                URL::class.java,
                URI::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    toErased.isEnum
                }
            }
            CharSequence::class.isAssignableFrom(fromType) -> when (toType) {
                CharSequence::class.java,
                String::class.java,
                ByteArray::class.java -> true
                else -> false
            }
            Number::class.isAssignableFrom(fromType) -> when (toType) {
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                String::class.java,
                Char::class.java, Character::class.java,
                Boolean::class.java, java.lang.Boolean::class.java,
                Date::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    toErased.isEnum
                }
            }
            Char::class.isAssignableFrom(fromType) || Character::class.isAssignableFrom(fromType) -> when (toType) {
                Char::class.java, Character::class.java,
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                String::class.java,
                Boolean::class.java, java.lang.Boolean::class.java -> true
                else -> false
            }
            Boolean::class.isAssignableFrom(fromType) || java.lang.Boolean::class.isAssignableFrom(fromType) -> when (toType) {
                Boolean::class.java, java.lang.Boolean::class.java,
                Char::class.java, Character::class.java,
                Short::class.java, java.lang.Short::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java,
                Float::class.java, java.lang.Float::class.java,
                BigDecimal::class.java,
                BigInteger::class.java,
                String::class.java -> true
                else -> false
            }
            Date::class.isAssignableFrom(fromType) -> when (toType) {
                Date::class.java,
                Long::class.java, java.lang.Long::class.java -> true
                else -> false
            }
            fromType == ByteArray::class.java -> when (toType) {
                ByteArray::class.java,
                String::class.java -> true
                else -> false
            }
            Enum::class.isAssignableFrom(fromType) -> when (toType) {
                String::class.java,
                Byte::class.java, java.lang.Byte::class.java,
                Short::class.java, java.lang.Short::class.java,
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java -> true
                else -> {
                    val toErased = toType.erasedType()
                    toErased.isEnum && TypeConversionConfig.permiteEnumToEnum
                }
            }
            fromType == File::class.java -> when (toType) {
                File::class.java,
                String::class.java -> true
                else -> false
            }
            fromType == URI::class.java -> when (toType) {
                URI::class.java,
                String::class.java,
                URL::class.java -> true
                else -> false
            }
            fromType == URL::class.java -> when (toType) {
                URL::class.java,
                String::class.java,
                URI::class.java -> true
                else -> false
            }
            else -> false
        }
    }


    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "CAST_NEVER_SUCCEEDS")
    override fun convert(fromType: Type, toType: Type, value: Any): Any {
        return when (value) {
            is String -> when (toType) {
                String::class.java -> value     // identity
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toShort().toByte()
                Int::class.java, Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value)
                BigInteger::class.java -> BigDecimal(value)
                CharSequence::class.java -> value
                Boolean::class.java, java.lang.Boolean::class.java -> value.toBoolean()
                ByteArray::class.java -> value.toByteArray() // UTF-8 always
                File::class.java -> File(value)
                URL::class.java -> URL(value)
                URI::class.java -> URI(value)
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                        @Suppress("UNCHECKED_CAST")
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().filter { it.name == value }.first() ?: IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}, no matching value ${value}")
                    } else {
                        throw IllegalStateException("Unknown String conversion from ${fromType} to ${toType}")
                    }
                }
            }
            is CharSequence -> when (toType) {
                CharSequence::class.java -> value // identity
                String::class.java -> value.toString()
                ByteArray::class.java -> value.toString().toByteArray() // UTF-8 always
                else -> throw IllegalStateException("Unknown CharSequence conversion from ${fromType} to ${toType}")
            }
            is Number -> when (toType) {
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toByte()
                Int::class.java, Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value.toDouble())
                BigInteger::class.java -> BigDecimal(value.toDouble())
                String::class.java -> value.toString()
                Char::class.java, Character::class.java -> value.toChar()
                Boolean::class.java, java.lang.Boolean::class.java -> value != 0
                Date::class.java -> Date(value.toLong())
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum) {
                        @Suppress("UNCHECKED_CAST")
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().get(value.toInt())
                    } else {
                        throw IllegalStateException("Unknown number conversion from ${fromType} to ${toType}")
                    }
                }
            }
            is Char -> when (toType) {
                Char::class.java, Character::class.java -> value     // identity
                Short::class.java, java.lang.Short::class.java -> value.toShort()
                Byte::class.java, java.lang.Byte::class.java -> value.toByte()
                Int::class.java, Integer::class.java -> value.toInt()
                Long::class.java, java.lang.Long::class.java -> value.toLong()
                Double::class.java, java.lang.Double::class.java -> value.toDouble()
                Float::class.java, java.lang.Float::class.java -> value.toFloat()
                BigDecimal::class.java -> BigDecimal(value.toInt())
                BigInteger::class.java -> BigDecimal(value.toInt())
                String::class.java -> value.toString()
                Boolean::class.java, java.lang.Boolean::class.java -> value == '1' || value == 'T' || value == 't'
                else -> throw IllegalStateException("Unknown char conversion from ${fromType} to ${toType}")
            }
            is Boolean -> when (toType) {
                Boolean::class.java, java.lang.Boolean::class.java -> value      // identity
                Char::class.java, Character::class.java -> if (value) 'T' else 'F'
                Short::class.java, java.lang.Short::class.java -> (if (value) 1 else 0).toShort()
                Byte::class.java, java.lang.Byte::class.java -> (if (value) 1 else 0).toByte()
                Int::class.java, Integer::class.java -> (if (value) 1 else 0).toInt()
                Long::class.java, java.lang.Long::class.java -> (if (value) 1 else 0).toLong()
                Double::class.java, java.lang.Double::class.java -> (if (value) 1 else 0).toDouble()
                Float::class.java, java.lang.Float::class.java -> (if (value) 1 else 0).toFloat()
                BigDecimal::class.java -> BigDecimal(if (value) 1 else 0)
                BigInteger::class.java -> BigDecimal(if (value) 1 else 0)
                String::class.java -> value.toString()
                else -> throw IllegalStateException("Unknown boolean conversion from ${fromType} to ${toType}")
            }
            is Date -> when (toType) {
                Date::class.java -> value   // identity
                Long::class.java, java.lang.Long::class.java -> value.time
                else -> throw IllegalStateException("Unknown date conversion from ${fromType} to ${toType}")
            }
            is ByteArray -> when (toType) {
                ByteArray::class.java -> value // identity
                String::class.java -> value.toString(Charsets.UTF_8) // always UTF-8
                else -> throw IllegalStateException("Unknown ByteArray conversion from ${fromType} to ${toType}")
            }
            is Enum<*> -> when (toType) {
                String::class.java -> value.name
                Byte::class.java, java.lang.Byte::class.java -> value.ordinal.toByte()
                Short::class.java, java.lang.Short::class.java -> value.ordinal.toShort()
                Int::class.java, Integer::class.java -> value.ordinal
                Long::class.java, java.lang.Long::class.java -> value.ordinal.toLong()
                else -> {
                    val toErased = toType.erasedType()
                    if (toErased.isEnum && TypeConversionConfig.permiteEnumToEnum) {
                        @Suppress("UNCHECKED_CAST")
                        val ecls = toErased as Class<Enum<*>>
                        ecls.getEnumConstants().filter { it.name == value.name }.firstOrNull() ?: IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}, no matching value ${value}")
                    } else {
                        throw IllegalStateException("Unknown Enum conversion from ${fromType} to ${toType}")
                    }
                }
            }
            is File -> when (toType) {
                File::class.java -> value // identity
                String::class.java -> value.absolutePath
                else -> throw IllegalStateException("Unknown File conversion from ${fromType} to ${toType}")
            }
            is URL -> when (toType) {
                URL::class.java -> value // identity
                String::class.java -> value.toString()
                URI::class.java -> value.toURI()
                else -> throw IllegalStateException("Unknown URL conversion from ${fromType} to ${toType}")
            }
            is URI -> when (toType) {
                URI::class.java -> value // identity
                String::class.java -> value.toString()
                URL::class.java -> value.toURL()
                else -> throw IllegalStateException("Unknown URI conversion from ${fromType} to ${toType}")
            }
            else -> throw IllegalArgumentException("No primitive conversion for ${fromType} to ${toType} for value ${value}")
        }
    }
}