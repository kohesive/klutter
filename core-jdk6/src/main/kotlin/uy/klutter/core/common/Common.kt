package uy.klutter.core.common

/**
 * Make initialization of a parameter more readable with its verification work done inline with the declaration
 */
public inline fun <T> T.verifiedBy(verifyWith: (T) -> Unit): T {
    verifyWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 */
public inline fun <T> T.initializedBy(initWith: (T) -> Unit): T {
    initWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 */
public inline fun <T> T.with(initWith: T.() -> Unit): T {
    this.initWith().let{}
    return this
}

/**
 * When something isn't null do something, kinda the opposite of ?:
 */
public inline fun <T: Any, R: Any> T?.whenNotNull(thenDo: (T) -> R?): R? = if (this == null) null else thenDo(this)
