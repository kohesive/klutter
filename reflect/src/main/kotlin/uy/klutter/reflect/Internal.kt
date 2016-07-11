@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package uy.klutter.reflect

import java.lang.reflect.Type
import kotlin.jvm.internal.markers.*
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

/** Dangerous things from Kotlin internal type information **/

fun Type.isMutableList(): Boolean {
    val erased = this.erasedType()
    return java.util.List::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableList::class.isAssignableFrom(erased))
}

fun KType.isMutableList(): Boolean = this.javaType.isMutableList()

fun Type.isMutableSet(): Boolean {
    val erased = this.erasedType()
    return java.util.Set::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableSet::class.isAssignableFrom(erased))
}

fun KType.isMutableSet(): Boolean = this.javaType.isMutableSet()

fun Type.isMutableMap(): Boolean {
    val erased = this.erasedType()
    return java.util.Map::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableMap::class.isAssignableFrom(erased))
}

fun KType.isMutableMap(): Boolean = this.javaType.isMutableMap()

fun Type.isMutableMapEntry(): Boolean {
    val erased = this.erasedType()
    return java.util.Map::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableMap.Entry::class.isAssignableFrom(erased))
}

fun KType.isMutableMapEntry(): Boolean = this.javaType.isMutableMapEntry()


fun Type.isMutableCollection(): Boolean {
    val erased = this.erasedType()
    return java.util.Collection::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableCollection::class.isAssignableFrom(erased))
}

fun KType.isMutableCollection(): Boolean = this.javaType.isMutableCollection()

fun Type.isMutableIterator(): Boolean {
    val erased = this.erasedType()
    return java.util.Iterator::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableIterator::class.isAssignableFrom(erased))
}

fun KType.isMutableIterator(): Boolean = this.javaType.isMutableIterator()

fun Type.isMutableListIterator(): Boolean {
    val erased = this.erasedType()
    return java.util.ListIterator::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableListIterator::class.isAssignableFrom(erased))
}

fun KType.isMutableListIterator(): Boolean = this.javaType.isMutableListIterator()

fun Type.isMutableIterable(): Boolean {
    val erased = this.erasedType()
    return java.lang.Iterable::class.isAssignableFrom(erased) &&
            (erased.isNotMarkMapped() || KMutableIterable::class.isAssignableFrom(erased))
}

fun KType.isMutableIterable(): Boolean = this.javaType.isMutableIterable()


fun Type.isNotMarkMapped(): Boolean = !KMappedMarker::class.isAssignableFrom(this)


