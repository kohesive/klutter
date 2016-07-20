package uy.klutter.elasticsearch

class WrappedThrowableException(cause: Throwable): Exception(cause.message, cause)

fun wrapThrowable(rawEx: Throwable): Exception = if (rawEx is Exception) rawEx else WrappedThrowableException(rawEx)
