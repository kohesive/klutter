package uy.klutter.reflect.conversion.jdk8

import uy.klutter.reflect.conversion.SelfRegisteringConverters
import uy.klutter.reflect.conversion.TypeConverters
import uy.klutter.reflect.isAssignableFrom
import java.lang.reflect.Type
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Instant
import java.time.temporal.Temporal

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public class RegisterJdk8Converters: SelfRegisteringConverters {
    override fun registerInto(conversion: TypeConverters) {
        conversion.register(fun (fromType: Type, toType: Type):Boolean {
            return when {
                fromType == Long::class.java, fromType == java.lang.Long::class.java -> when (toType) {
                    Instant::class.java -> true
                    else -> false
                }
                Instant::class.isAssignableFrom(fromType) -> when (toType) {
                    Int::class.java, java.lang.Integer::class.java,
                    Long::class.java, java.lang.Long::class.java,
                    Double::class.java, java.lang.Double::class.java-> true
                    else -> false
                }
                else -> false
            }
        }, fun TypeConverters.ExactConverter.(value: Any): Any {
            return  when (value) {
                is Long -> when (toType) {
                    Instant::class.java -> Instant.ofEpochMilli(value.toLong())
                    else ->  throw IllegalStateException("Invalid Number conversion for ${fromType} to ${toType}")
                }
                is Instant -> when (toType) {
                    Long::class.java, java.lang.Long::class.java -> value.toEpochMilli()
                    Double::class.java, java.lang.Double::class.java -> value.toEpochMilli().toDouble()
                    else ->  throw IllegalStateException("Invalid Instant conversion for ${fromType} to ${toType}")
                }
                else -> throw IllegalStateException("Cannot convert ${fromType} to ${toType}")
            }
        })
    }
}
