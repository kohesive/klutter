package uy.klutter.core.jdk

public fun String.fromEnd(howManyFromEnd: Int): String = this.substring(this.length()-howManyFromEnd)
public fun String.fromStart(howManyFromStart: Int): String = this.substring(0, howManyFromStart)
public fun String.exceptEnding(allButThisMany: Int): String = this.substring(0, this.length()-allButThisMany)
public fun String.exceptLast(): String = this.substring(0, this.length()-1)
public fun String.exceptStarting(allAfterThisMany: Int): String = this.substring(allAfterThisMany)
public fun String.exceptFirst(): String = this.substring(1)

public fun String.mustStartWith(prefix: String): String {
    return if (this.startsWith(prefix)) {
        this
    }
    else {
        prefix + this
    }
}

public fun String.mustStartWith(prefix: Char): String {
    return if (this.startsWith(prefix)) {
        this
    }
    else {
        prefix + this
    }
}

public fun String.mustNotStartWith(prefix: String): String {
    return if (!this.startsWith(prefix)) {
        this
    }
    else {
        this.exceptStarting(prefix.length())
    }
}

public fun String.mustNotStartWith(prefix: Char): String {
    return if (!this.startsWith(prefix)) {
        this
    }
    else {
        this.exceptFirst()
    }
}

public fun String.mustNotEndWith(postfix: Char): String {
    return if (!this.endsWith(postfix)) {
        this
    }
    else {
        this.exceptLast()
    }
}

public fun String.mustNotEndWith(postfix: String): String {
    return if (!this.endsWith(postfix)) {
        this
    }
    else {
        this.exceptEnding(postfix.length())
    }
}

public fun String.mustEndWith(postfix: String): String {
    return if (this.endsWith(postfix)) {
        this
    } else {
        this + postfix
    }
}

public fun String.mustEndWith(postfix: Char): String {
    return if (this.endsWith(postfix)) {
        this
    } else {
        this + postfix
    }
}

inline public fun String.whenStartsWith(prefix: String, thenWithRest: (String)->Unit): Boolean {
    if (this.startsWith(prefix)) {
        thenWithRest(this.exceptStarting(prefix.length()))
        return true
    }
    return false
}


inline public fun String.whenStartsWith(prefixes: List<String>, thenWithRest: (String)->Unit): Boolean {
    prefixes.forEach { prefix ->
        if (this.startsWith(prefix)) {
            thenWithRest(this.exceptStarting(prefix.length()))
            return@whenStartsWith true
        }
    }
    return false
}

public fun String?.nullIfBlank(): String? = if (this.isNullOrBlank()) null else this
public fun String?.nullIfEmpty(): String? = if (this.isNullOrEmpty()) null else this

