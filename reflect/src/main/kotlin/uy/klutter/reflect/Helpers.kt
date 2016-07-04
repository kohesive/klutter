package uy.klutter.reflect

import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

fun Method?.isPublic(): Boolean = this?.modifiers?.let { Modifier.isPublic(it) } ?: false
fun Method?.isStatic(): Boolean = this?.modifiers?.let { Modifier.isStatic(it) } ?: false
fun <T: Any> Constructor<T>?.isPublic(): Boolean = this?.modifiers?.let { Modifier.isPublic(it) } ?: false
fun <T: Any> Class<T>?.isPublic(): Boolean = this?.modifiers?.let { Modifier.isPublic(it) } ?: false
fun <T: Any> KClass<T>?.isPublic(): Boolean = this?.java?.modifiers?.let { Modifier.isPublic(it) } ?: false
