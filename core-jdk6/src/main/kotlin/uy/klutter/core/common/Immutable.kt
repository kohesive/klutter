package uy.klutter.core.collections

import java.io.Serializable
import java.util.*

// based off of the answer from @miensol in this Stackoverflow answer http://stackoverflow.com/a/37936456/3679676

interface ReadOnly

/**
 * Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type
*/
class ReadOnlyIterator <T> (private val delegate: Iterator<T>) : Iterator<T> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type
 */
class ReadOnlyCollection <T> (private val delegate: Collection<T>) : Collection<T> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun iterator(): Iterator<T> {
        return delegate.iterator().asReadOnly()
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type
 */
class ReadOnlyListIterator <T> (private val delegate: ListIterator<T>): ListIterator<T> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a List with a lightweight delegating class that prevents casting back to mutable type
 */
open class ReadOnlyList <T>(protected val delegate: List<T>) : List<T> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun iterator(): Iterator<T> {
        return delegate.iterator().asReadOnly()
    }

    override fun listIterator(): ListIterator<T> {
        return delegate.listIterator().asReadOnly()
    }

    override fun listIterator(index: Int): ListIterator<T> {
        return delegate.listIterator(index).asReadOnly()
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        return delegate.subList(fromIndex, toIndex).asReadOnly()
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type
 */
class ReadOnlyRandomAccessList <T> (delegate: List<T>): ReadOnlyList<T>(delegate), List<T>, ReadOnly, RandomAccess, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a Set with a lightweight delegating class that prevents casting back to mutable type
 */
class ReadOnlySet <T>(private val delegate: Set<T>) : Set<T> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override fun iterator(): Iterator<T> {
        return delegate.iterator().asReadOnly()
    }

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps a Map with a lightweight delegating class that prevents casting back to mutable type
 */
class ReadOnlyMap <K : Any, V>(private val delegate: Map<K, V>) : Map<K, V> by delegate, ReadOnly, Serializable {
    companion object {
        @JvmField val serialVersionUID = 1L
    }

    override val keys: Set<K>
        get() = delegate.keys.asReadOnly()
    override val values: Collection<V>
        get() = delegate.values.asReadOnly()
    override val entries: kotlin.collections.Set<kotlin.collections.Map.Entry<K, V>>
        get() = delegate.entries.asReadOnly()

    override fun toString(): String {
        return "ReadOnly: ${super.toString()}"
    }

    override fun equals(other: Any?): Boolean {
        return delegate.equals(other)
    }

    override fun hashCode(): Int {
        return delegate.hashCode()
    }
}

/**
 * Wraps the Iterator with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> Iterator<T>.asReadOnly(): Iterator<T> = this.whenNotAlreadyReadOnly { ReadOnlyIterator(it) }


/**
 * Wraps the ListIterator with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> ListIterator<T>.asReadOnly(): ListIterator<T> = this.whenNotAlreadyReadOnly { ReadOnlyListIterator(it) }

/**
 * Wraps the Collection with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> Collection<T>.asReadOnly(): Collection<T> = this.whenNotAlreadyReadOnly { ReadOnlyCollection(it) }

/**
 * Wraps the List with a lightweight delegating class that prevents casting back to mutable type,
 * specializing for the case of the RandomAccess marker interface being retained if it was there originally
 */
fun <T> List<T>.asReadOnly(): List<T> {
    return this.whenNotAlreadyReadOnly {
        when (it) {
            is RandomAccess -> ReadOnlyRandomAccessList(it)
            else -> ReadOnlyList(it)
        }
    }
}

/**
 * Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,
 * specializing for the case of the RandomAccess marker interface being retained if it was there originally
 */
@Suppress("UNCHECKED_CAST")
fun <T> List<T>.toImmutable(): List<T> {
    val copy = when (this) {
        is ArrayList -> this.clone() as ArrayList<T>
        else -> this.toList()
    }
    return when (this) {
        is RandomAccess ->  ReadOnlyRandomAccessList(copy)
        else -> ReadOnlyList(copy)
    }
}

/**
 * Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> List<T>.asReadOnlyCollection(): Collection<T> = this.whenNotAlreadyReadOnly { ReadOnlyCollection(it) }

/**
 * Wraps the Set with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> Set<T>.asReadOnly(): Set<T> = this.whenNotAlreadyReadOnly { ReadOnlySet(it) }

/**
 * Copies the Set and then wraps with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> Set<T>.toImmutable(): Set<T> = ReadOnlySet(this.toSet())

/**
 * Wraps the Set as a Collection with a lightweight delegating class that prevents casting back to mutable type
 */
fun <T> Set<T>.asReadOnlyCollection(): Collection<T> = this.whenNotAlreadyReadOnly { ReadOnlyCollection(it) }

/**
 * Wraps the Map with a lightweight delegating class that prevents casting back to mutable type
 */
fun <K : Any, V> Map<K, V>.asReadOnly(): Map<K, V> = this.whenNotAlreadyReadOnly { ReadOnlyMap(it) }

/**
 * Copies the Map and then wraps with a lightweight delegating class that prevents casting back to mutable type
 */
fun <K : Any, V> Map<K, V>.toImmutable(): Map<K, V> = ReadOnlyMap(this.asSequence().map { it.key to it.value }.toMap())


private inline fun <T: R, R: Any> T.whenNotAlreadyReadOnly(makeReadOnly: (T)->R): R = if (this is ReadOnly) this else makeReadOnly(this)