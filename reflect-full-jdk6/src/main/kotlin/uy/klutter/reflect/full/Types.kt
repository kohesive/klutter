package uy.klutter.reflect.full

import uy.klutter.reflect.TypeReference
import uy.klutter.reflect.erasedType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

fun KType.isAssignableFrom(other: KType): Boolean {
    if (this == other || this.javaType == other.javaType) return true
    return (this.javaType.erasedType()).isAssignableFrom(other.javaType.erasedType())
}

fun KType.isAssignableFrom(other: Type): Boolean {
    if (this == other || this.javaType == other) return true
    return (this.javaType.erasedType()).isAssignableFrom(other.erasedType())
}

fun KType.isAssignableFrom(other: Class<*>): Boolean {
    if (this.javaType == other) return true
    return (this.javaType.erasedType()).isAssignableFrom(other)
}

fun KType.isAssignableFrom(other: KClass<*>): Boolean {
    return (this.javaType.erasedType()).isAssignableFrom(other.java)
}

fun Type.isAssignableFrom(other: KType): Boolean {
    if (this == other || this == other.javaType) return true
    return (this.erasedType()).isAssignableFrom(other.javaType.erasedType())
}

fun Class<*>.isAssignableFrom(other: KType): Boolean {
    if (this == other.javaType) return true
    return this.isAssignableFrom(other.erasedType())
}

fun <T: Any> KClass<T>.isAssignableFrom(other: KType): Boolean {
    if (this.java == other.javaType) return true
    return this.java.isAssignableFrom(other.javaType.erasedType())
}

fun KType.erasedType(): Class<Any> = this.javaType.erasedType()

