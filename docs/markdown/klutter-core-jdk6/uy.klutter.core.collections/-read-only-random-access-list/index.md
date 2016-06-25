[uy.klutter.core.collections](../index.md) / [ReadOnlyRandomAccessList](.)


# ReadOnlyRandomAccessList
`class ReadOnlyRandomAccessList<T>&nbsp;:&nbsp;[ReadOnlyList](../-read-only-list/index.md)<T>, List<T>, [ReadOnly](../-read-only.md), [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L117)

Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyRandomAccessList(delegate:&nbsp;List<T>)`<p>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type</p> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [delegate](../-read-only-list/delegate.md) | `val delegate: List<T>` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [equals](equals.md) | `fun equals(other:&nbsp;Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [iterator](../-read-only-list/iterator.md) | `open fun iterator(): Iterator<T>` |
| [listIterator](../-read-only-list/list-iterator.md) | `open fun listIterator(): ListIterator<T>`
`open fun listIterator(index:&nbsp;Int): ListIterator<T>` |
| [subList](../-read-only-list/sub-list.md) | `open fun subList(fromIndex:&nbsp;Int, toIndex:&nbsp;Int): List<T>` |

### Companion Object Properties

|&nbsp;|&nbsp;|
|---|---|
| [serialVersionUID](serial-version-u-i-d.md) | `val serialVersionUID: Long` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnlyCollection](../kotlin.collections.-list/as-read-only-collection.md) | `fun <T> List<T>.asReadOnlyCollection(): Collection<T>`<p>Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [batch](../kotlin.collections.-iterable/batch.md) | `fun <T> Iterable<T>.batch(n:&nbsp;Int): <ERROR CLASS><List<T>>`<p>Batch a sequence into a sequence of lists of max N size</p>`fun <T> Iterable<T>.batch(n:&nbsp;Int, forEachDo:&nbsp;(List<T>)&nbsp;->&nbsp;Unit): Unit`<p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p> |
| [lazyBatch](../kotlin.collections.-iterable/lazy-batch.md) | `fun <T> Iterable<T>.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(<ERROR CLASS><T>)&nbsp;->&nbsp;Unit): Unit`<p>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy</p> |
| [toImmutable](../kotlin.collections.-list/to-immutable.md) | `fun <T> List<T>.toImmutable(): List<T>`<p>Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally</p> |
| [whenAllNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-all-not-null.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> Collection<T?>.whenAllNotNull(block:&nbsp;(List<T>)&nbsp;->&nbsp;R): Unit` |
| [whenAnyNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-any-not-null.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> Collection<T?>.whenAnyNotNull(block:&nbsp;(List<T>)&nbsp;->&nbsp;R): Unit` |
