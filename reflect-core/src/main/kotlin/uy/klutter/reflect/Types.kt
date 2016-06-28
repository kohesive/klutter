package uy.klutter.reflect

import java.lang.reflect.Type
import kotlin.reflect.KClass

fun <T: Any> KClass<T>.isAssignableFrom(other: Type): Boolean {
    if (this.java == other) return true
    return this.java.isAssignableFrom(other.erasedType())
}

fun Class<*>.isAssignableFrom(other: Type): Boolean {
    if (this == other) return true
    return this.isAssignableFrom(other.erasedType())
}

fun <T: Any> Class<*>.isAssignableFrom(other: KClass<T>): Boolean {
    if (this == other.java) return true
    return this.isAssignableFrom(other.java)
}

fun <T: Any> KClass<T>.isAssignableFrom(other: Class<*>): Boolean {
    if (this.java == other) return true
    return this.java.isAssignableFrom(other)
}

fun <T: Any, O: Any> KClass<T>.isAssignableFrom(other: KClass<O>): Boolean {
    if (this.java == other.java) return true
    return this.java.isAssignableFrom(other.java)
}

fun Type.isAssignableFrom(other: Type): Boolean {
    if (this == other) return true
    return (this.erasedType()).isAssignableFrom(other.erasedType())
}

fun Type.isAssignableFrom(other: Class<*>): Boolean {
    if (this == other) return true
    return (this.erasedType()).isAssignableFrom(other)
}

fun Type.isAssignableFrom(other: KClass<*>): Boolean {
    return (this.erasedType()).isAssignableFrom(other.java)
}
