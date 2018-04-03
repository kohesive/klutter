package uy.klutter.reflect

import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

fun <T : Any> KClass<T>.isAssignableFrom(other: Type): Boolean {
    if (this.java == other) return true
    return this.java.isAssignableFrom(other.erasedType())
}

fun Class<*>.isAssignableFrom(other: Type): Boolean {
    if (this == other) return true
    return this.isAssignableFrom(other.erasedType())
}

fun <T : Any> Class<*>.isAssignableFrom(other: KClass<T>): Boolean {
    if (this == other.java) return true
    return this.isAssignableFrom(other.java)
}

fun <T : Any> KClass<T>.isAssignableFrom(other: Class<*>): Boolean {
    if (this.java == other) return true
    return this.java.isAssignableFrom(other)
}

fun <T : Any, O : Any> KClass<T>.isAssignableFrom(other: KClass<O>): Boolean {
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


fun KType.isAssignableFrom(other: KType): Boolean {
    if (this == other || this.javaType == other.javaType) return true
    return (this.erasedType()).isAssignableFrom(other.erasedType())
}

fun KType.isAssignableFrom(other: Type): Boolean {
    if (this == other || this.javaType == other) return true
    return (this.erasedType()).isAssignableFrom(other.erasedType())
}

fun KType.isAssignableFrom(other: Class<*>): Boolean {
    if (this.javaType == other) return true
    return (this.erasedType()).isAssignableFrom(other)
}

fun KType.isAssignableFrom(other: KClass<*>): Boolean {
    return (this.erasedType()).isAssignableFrom(other.java)
}

fun Type.isAssignableFrom(other: KType): Boolean {
    if (this == other || this == other.javaType) return true
    return (this.erasedType()).isAssignableFrom(other.erasedType())
}

fun Class<*>.isAssignableFrom(other: KType): Boolean {
    if (this == other.javaType) return true
    return this.isAssignableFrom(other.erasedType())
}

fun <T : Any> KClass<T>.isAssignableFrom(other: KType): Boolean {
    if (this.java == other.javaType) return true
    return this.java.isAssignableFrom(other.erasedType())
}

fun Type.isAssignableFromOrSamePrimitive(other: Type): Boolean {
    if (this == other) return true
    if (this is Class<*>) {
        if (other is Class<*>) {
            return this == other.kotlin.javaObjectType || this == other.kotlin.javaPrimitiveType ||
                    this.isAssignableFrom(other)
        }
        return this.isAssignableFrom(other.erasedType())
    }
    return this.erasedType().isAssignableFrom(other.erasedType())
}

fun Type.isAssignableFromOrSamePrimitive(other: KClass<*>): Boolean {
    return this.isAssignableFromOrSamePrimitive(other.java)
}

fun Type.isAssignableFromOrSamePrimitive(other: KType): Boolean {
    return this.isAssignableFromOrSamePrimitive(other.javaType)
}

fun KType.isAssignableFromOrSamePrimitive(other: KType): Boolean {
    return this.javaType.isAssignableFromOrSamePrimitive(other.javaType)
}

fun KType.isAssignableFromOrSamePrimitive(other: Class<*>): Boolean {
    return this.javaType.isAssignableFromOrSamePrimitive(other as Type)
}

fun KType.isAssignableFromOrSamePrimitive(other: Type): Boolean {
    return this.javaType.isAssignableFromOrSamePrimitive(other)
}

fun KType.isAssignableFromOrSamePrimitive(other: KClass<*>): Boolean {
    return this.javaType.isAssignableFromOrSamePrimitive(other.java as Type)
}


fun KClass<*>.isAssignableFromOrSamePrimitive(other: KClass<*>): Boolean {
    return (this.java as Type).isAssignableFromOrSamePrimitive(other.java as Type)
}

fun KClass<*>.isAssignableFromOrSamePrimitive(other: Class<*>): Boolean {
    return (this.java as Type).isAssignableFromOrSamePrimitive(other as Type)
}

fun KClass<*>.isAssignableFromOrSamePrimitive(other: KType): Boolean {
    return (this.java as Type).isAssignableFromOrSamePrimitive(other.javaType)
}

fun KClass<*>.isAssignableFromOrSamePrimitive(other: Type): Boolean {
    return (this.java as Type).isAssignableFromOrSamePrimitive(other)
}

fun Class<*>.isAssignableFromOrSamePrimitive(other: Class<*>): Boolean {
    return (this as Type).isAssignableFromOrSamePrimitive(other as Type)
}

fun Class<*>.isAssignableFromOrSamePrimitive(other: KClass<*>): Boolean {
    return (this as Type).isAssignableFromOrSamePrimitive(other.java as Type)
}

fun Class<*>.isAssignableFromOrSamePrimitive(other: KType): Boolean {
    return (this as Type).isAssignableFromOrSamePrimitive(other.javaType)
}


