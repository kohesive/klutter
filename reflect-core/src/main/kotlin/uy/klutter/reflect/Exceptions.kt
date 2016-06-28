package uy.klutter.reflect

import java.lang.reflect.InvocationTargetException

fun unwrapInvokeException(rawEx: Throwable): Throwable {
    return if (rawEx is InvocationTargetException) rawEx.cause!! else rawEx
}

fun unwrapInvokeException(rawEx: Exception): Throwable {
    return if (rawEx is InvocationTargetException) rawEx.cause!! else rawEx
}
