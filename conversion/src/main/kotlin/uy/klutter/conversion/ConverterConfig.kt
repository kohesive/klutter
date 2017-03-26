package uy.klutter.conversion

import java.lang.reflect.Type
import java.util.*


object TypeConversionConfig {
    @Volatile var permiteEnumToEnum: Boolean = false

    @Volatile var defaultConverter = TypeConverters().apply {
        register(PrimitiveConverters())
        RegisterJdk7Converters().registerInto(this)
        RegisterJdk8Converters().registerInto(this)
        val services = ServiceLoader.load(SelfRegisteringConverters::class.java)
        services.forEach {
            it.registerInto(this)
        }
    }
}

interface SelfRegisteringConverters {
    fun registerInto(conversion: TypeConverters)
}

interface ConverterSet {
    fun predicate(fromType: Type, toType: Type): Boolean
    fun convert(fromType: Type, toType: Type, value: Any): Any
}