package uy.klutter.core.common

fun String.fromEnd(howManyFromEnd: Int): String = this.substring(this.length - howManyFromEnd)
fun String.fromStart(howManyFromStart: Int): String = this.substring(0, howManyFromStart)
fun String.exceptEnding(allButThisMany: Int): String = this.substring(0, this.length - allButThisMany)
fun String.exceptLast(): String = this.substring(0, this.length - 1)
fun String.exceptStarting(allAfterThisMany: Int): String = this.substring(allAfterThisMany)
fun String.exceptFirst(): String = this.substring(1)

fun String.mustStartWith(prefix: String): String {
    return if (this.startsWith(prefix)) {
        this
    } else {
        prefix + this
    }
}

fun String.mustStartWith(prefix: Char): String {
    return if (this.startsWith(prefix)) {
        this
    } else {
        prefix + this
    }
}

fun String.mustNotStartWith(prefix: String): String {
    return if (!this.startsWith(prefix)) {
        this
    } else {
        this.exceptStarting(prefix.length)
    }
}

fun String.mustNotStartWith(prefix: Char): String {
    return if (!this.startsWith(prefix)) {
        this
    } else {
        this.exceptFirst()
    }
}

fun String.mustNotEndWith(postfix: Char): String {
    return if (!this.endsWith(postfix)) {
        this
    } else {
        this.exceptLast()
    }
}

fun String.mustNotEndWith(postfix: String): String {
    return if (!this.endsWith(postfix)) {
        this
    } else {
        this.exceptEnding(postfix.length)
    }
}

fun String.mustEndWith(postfix: String): String {
    return if (this.endsWith(postfix)) {
        this
    } else {
        this + postfix
    }
}

fun String.mustEndWith(postfix: Char): String {
    return if (this.endsWith(postfix)) {
        this
    } else {
        this + postfix
    }
}

inline fun String.whenStartsWith(prefix: String, thenWithRest: (String) -> Unit): Boolean {
    if (this.startsWith(prefix)) {
        thenWithRest(this.exceptStarting(prefix.length))
        return true
    }
    return false
}

inline fun String.whenStartsWith(prefixes: List<String>, thenWithRest: (String) -> Unit): Boolean {
    prefixes.forEach { prefix ->
        if (this.startsWith(prefix)) {
            thenWithRest(this.exceptStarting(prefix.length))
            return@whenStartsWith true
        }
    }
    return false
}

fun String?.nullIfBlank(): String? = if (this.isNullOrBlank()) null else this
fun String?.nullIfEmpty(): String? = if (this.isNullOrEmpty()) null else this

