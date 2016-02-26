package uy.klutter.core.common

/**
 * Make initialization of a parameter more readable with its verification work done inline with the declaration
 * The instance is passed as a variable to the initialization lambda
 */
public infix inline fun <T> T.verifiedBy(verifyWith: (T) -> Unit): T {
    verifyWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its verification work done inline with the declaration
 * The receiver of the lambda is the variable to be initialized
 */
public infix inline fun <T> T.verifiedWith(verifyWith: T.() -> Unit): T {
    this.verifyWith()
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 * The instance is passed as a variable to the initialization lambda
 */
public infix inline fun <T> T.initializedBy(initWith: (T) -> Unit): T {
    initWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 * The receiver of the lambda is the variable to be initialized
 */
public infix inline fun <T> T.initializedWith(initWith: T.() -> Unit): T {
    this.initWith()
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 */
public infix inline fun <T> T.with(initWith: T.() -> Unit): T {
    this.initWith()
    return this
}

/**
 * When something isn't null do something, kinda the opposite of ?:
 */
public infix inline fun <T: Any, R: Any> T?.whenNotNull(thenDo: (T) -> R?): R? = if (this == null) null else thenDo(this)

/**
 * When something isn't null do something, kinda the opposite of ?:
 */
public infix inline fun <T: Any, R: Any> T?.withNotNull(thenDo: T.() -> R?): R? = if (this == null) null else this.thenDo()


fun <T: Any, R: Any> Collection<T?>.whenAllNotNull(block: (List<T>)->R) {
    if (this.all { it != null }) {
        block(this.filterNotNull())
    }
}

fun <T: Any, R: Any> Collection<T?>.whenAnyNotNull(block: (List<T>)->R) {
    if (this.any { it != null }) {
        block(this.filterNotNull())
    }
}
