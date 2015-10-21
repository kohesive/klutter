package uy.klutter.reflect.conversion

import uy.klutter.core.common.with
import uy.klutter.reflect.TypeReference
import uy.klutter.reflect.erasedType
import uy.klutter.reflect.fullType
import uy.klutter.reflect.isAssignableFrom
import java.io.File
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL
import java.util.*
import java.util.concurrent.ConcurrentHashMap

public object TypeConversionConfig {
    public @Volatile var permiteEnumToEnum: Boolean = false

    public @Volatile var defaultConverter = TypeConverters().with {
        register(primitiveConversionPredicate, primitiveConversion)
        val services = ServiceLoader.load(SelfRegisteringConverters::class.java)
        services.forEach {
            it.registerInto(this)
        }
    }
}

public interface SelfRegisteringConverters {
    public fun registerInto(conversion: TypeConverters)
}

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public class TypeConverters(val parent: TypeConverters? = null) {
    private val specialConverters = ArrayList<AskToConverter>()
    private val exactConvertersMap = ConcurrentHashMap<Pair<Type, Type>, ExactConverter>()

    @Suppress("UNCHECKED_CAST")
    public fun <T : Any, R : Any> register(fromType: Type, toType: Type, convertFunc: ExactConverter.(T) -> R) {
        val ec = ExactConverter(fromType, toType, convertFunc as ExactConverter.(Any) -> Any)
        exactConvertersMap.put(ec.key, ec)
    }

    public fun <T : X, X : Any, R : Any> register(fromType: TypeReference<T>, toType: TypeReference<R>, convertFunc: ExactConverter.(X) -> R) {
        register(fromType.type, toType.type, convertFunc)
    }

    @Suppress("UNCHECKED_CAST")
    public inline fun <reified T : Any, reified R : Any> register(noinline converter: ExactConverter.(T) -> R) {
        register(fullType<T>(), fullType<R>(), converter)
    }

    public fun register(askFunc: (fromType: Type, toType: Type) -> Boolean, convertFunc: ExactConverter.(Any) -> Any) {
        specialConverters.add(AskToConverter(askFunc, convertFunc))
    }

    public fun hasConverter(fromType: Type, toType: Type): Boolean {
        val tempParent = parent ?: if (this != TypeConversionConfig.defaultConverter) TypeConversionConfig.defaultConverter else null
        return (tempParent?.findConverter(fromType, toType) ?: findConverter(fromType, toType)) != null
    }

    public fun <T : Any, R : Any> hasConverter(fromType: TypeReference<T>, toType: TypeReference<R>): Boolean {
        return hasConverter(fromType.type, toType.type)
    }

    public fun findConverter(fromType: Type, toType: Type): ExactConverter? {
        return try {
            val tempParent = parent ?: if (this != TypeConversionConfig.defaultConverter) TypeConversionConfig.defaultConverter else null
            tempParent?.findConverter(fromType, toType) ?: exactConvertersMap.concurrentGetOrPut(Pair(fromType, toType)) {
                val askConverter = specialConverters.firstOrNull { it.askFunc(fromType, toType) } ?: throw IllegalStateException()
                ExactConverter(fromType, toType, askConverter.convertFunc)
            }
        } catch (ex: IllegalStateException) {
            null
        }
    }

    public fun <T : Any, R : Any> findConverter(fromType: TypeReference<T>, toType: TypeReference<R>): ExactConverter? {
        return findConverter(fromType.type, toType.type)
    }

    @Suppress("UNCHECKED_CAST")
    public fun <T : Any, R : Any> convertValue(fromType: Type, toType: Type, value: T): R {
        val converter = findConverter(fromType, toType) ?: throw IllegalStateException("No converter registered from ${fromType} to ${toType} (value ${value})")
        return with (converter) { convertFunc(value) } as R
    }

    public fun <T : Any, R : Any> convertValue(fromType: TypeReference<T>, toType: TypeReference<R>, value: T): R {
        return convertValue(fromType.type, toType.type, value)
    }

    public inline fun <reified T : Any, reified R : Any> convertValue(value: T): R {
        return convertValue<T, R>(fullType<T>(), fullType<R>(), value)
    }

    data class ExactConverter(val fromType: Type, val toType: Type, val convertFunc: ExactConverter.(Any) -> Any) {
        val key: Pair<Type, Type> = Pair(fromType, toType)
    }

    data class AskToConverter(val askFunc: (fromType: Type, toType: Type) -> Boolean, val convertFunc: ExactConverter.(Any) -> Any)
}

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "CAST_NEVER_SUCCEEDS")
private val primitiveConversion = fun TypeConverters.ExactConverter.(value: Any): Any {
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
            Boolean::class.java, java.lang.Boolean::class.java -> value.toBoolean()
            ByteArray::class.java -> value.toByteArray() // UTF-8 always
            File::class.java -> File(value)
            URL::class.java -> URL(value)
            URI::class.java -> URI(value)
            else -> {
                val toErased = toType.erasedType()
                if (toErased.isEnum) {
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
            Char::class.java, java.lang.Character::class.java -> value     // identity
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
        is Boolean -> when (toType) {
            Boolean::class.java, java.lang.Boolean::class.java -> value      // identity
            Char::class.java, java.lang.Character::class.java -> if (value) 'T' else 'F'
            Short::class.java, java.lang.Short::class.java -> (if (value) 1 else 0).toShort()
            Byte::class.java, java.lang.Byte::class.java ->  (if (value) 1 else 0).toByte()
            Int::class.java, java.lang.Integer::class.java -> (if (value) 1 else 0).toInt()
            Long::class.java, java.lang.Long::class.java ->  (if (value) 1 else 0).toLong()
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
            String::class.java -> kotlin.String(value, "UTF-8") // always UTF-8
            else -> throw IllegalStateException("Unknown ByteArray conversion from ${fromType} to ${toType}")
        }
        is Enum<*> -> when (toType) {
            String::class.java -> value.name
            Byte::class.java, java.lang.Byte::class.java -> value.ordinal.toByte()
            Short::class.java, java.lang.Short::class.java -> value.ordinal.toShort()
            Int::class.java, java.lang.Integer::class.java -> value.ordinal
            Long::class.java, java.lang.Long::class.java -> value.ordinal.toLong()
            else -> {
                val toErased = toType.erasedType()
                if (toErased.isEnum && TypeConversionConfig.permiteEnumToEnum) {
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

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
private val primitiveConversionPredicate = fun(fromType: Type, toType: Type): Boolean {
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
            CharSequence::class.java,
            ByteArray::class.java,
            Boolean::class.java, java.lang.Boolean::class.java,
            File::class.java,
            URL::class.java,
            URI::class.java -> true
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
            CharSequence::class.java,
            String::class.java,
            ByteArray::class.java -> true
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
        Boolean::class.isAssignableFrom(fromType),
        java.lang.Boolean::class.isAssignableFrom(fromType)-> when (toType) {
            Boolean::class.java, java.lang.Boolean::class.java,
            Char::class.java, java.lang.Character::class.java,
            Short::class.java, java.lang.Short::class.java,
            Byte::class.java, java.lang.Byte::class.java,
            Int::class.java, java.lang.Integer::class.java,
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
            Int::class.java, java.lang.Integer::class.java,
            Long::class.java, java.lang.Long::class.java -> true
            else -> {
                val toErased = toType.erasedType()
                if (toErased.isEnum && TypeConversionConfig.permiteEnumToEnum) {
                    true
                } else {
                    false
                }
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


