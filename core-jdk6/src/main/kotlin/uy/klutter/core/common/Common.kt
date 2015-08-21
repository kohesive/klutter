package uy.klutter.core.common

/**
 * Make initialization of a parameter more readable with its verification work done inline with the declaration
 */
public fun <T> T.verifiedBy(verifyWith: (T) -> Unit): T {
    verifyWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 */
public fun <T> T.initializedBy(initWith: (T) -> Unit): T {
    initWith(this)
    return this
}

/**
 * Make initialization of a parameter more readable with its initialization work done inline with the declaration
 */
public fun <T> T.with(initWith: T.() -> Unit): T {
    this.initWith()
    return this
}
