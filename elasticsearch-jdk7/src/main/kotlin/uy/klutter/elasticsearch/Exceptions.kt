package uy.klutter.elasticsearch

public class WrappedThrowableException(cause: Throwable): Exception(cause.message, cause)

public fun wrapThrowable(rawEx: Throwable): Exception = if (rawEx is Exception) rawEx else WrappedThrowableException(rawEx)
