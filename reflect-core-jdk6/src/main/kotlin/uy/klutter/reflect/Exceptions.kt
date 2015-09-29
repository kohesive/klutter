package uy.klutter.reflect

import java.lang.reflect.InvocationTargetException

public fun unwrapInvokeException(rawEx: Throwable): Throwable {
    return if (rawEx is InvocationTargetException) rawEx.getCause()!! else rawEx
}

public fun unwrapInvokeException(rawEx: Exception): Throwable {
    return if (rawEx is InvocationTargetException) rawEx.getCause()!! else rawEx
}
