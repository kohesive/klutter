package uy.klutter.binder

import uy.klutter.reflect.isAssignableFrom
import java.lang.reflect.Type
import java.time.Instant

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN") class RegisterJdk8Converters : SelfRegisteringConverters, ConverterSet {
    override fun registerInto(conversion: TypeConverters) {
        conversion.register(this)
    }

    override fun predicate(fromType: Type, toType: Type): Boolean {
        return when {
            fromType == Long::class.java || fromType == java.lang.Long::class.java -> when (toType) {
                Instant::class.java -> true
                else -> false
            }
            Instant::class.isAssignableFrom(fromType) -> when (toType) {
                Int::class.java, Integer::class.java,
                Long::class.java, java.lang.Long::class.java,
                Double::class.java, java.lang.Double::class.java -> true
                else -> false
            }
            else -> false
        }
    }

    override fun convert(fromType: Type, toType: Type, value: Any): Any {
        return when (value) {
            is Long -> when (toType) {
                Instant::class.java -> Instant.ofEpochMilli(value.toLong())
                else -> throw IllegalStateException("Invalid Number conversion for ${fromType} to ${toType}")
            }
            is Instant -> when (toType) {
                Long::class.java, java.lang.Long::class.java -> value.toEpochMilli()
                Double::class.java, java.lang.Double::class.java -> value.toEpochMilli().toDouble()
                else -> throw IllegalStateException("Invalid Instant conversion for ${fromType} to ${toType}")
            }
            else -> throw IllegalStateException("Cannot convert ${fromType} to ${toType}")
        }
    }

}
