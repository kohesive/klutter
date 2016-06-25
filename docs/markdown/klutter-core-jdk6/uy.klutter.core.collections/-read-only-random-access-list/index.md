[uy.klutter.core.collections](../index.md) / [ReadOnlyRandomAccessList](.)


# ReadOnlyRandomAccessList
<code>class ReadOnlyRandomAccessList<T> : [ReadOnlyList](../-read-only-list/index.md)<T>, List<T>, [ReadOnly](../-read-only.md), [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L117)<br/>
Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ReadOnlyRandomAccessList(delegate: List<T>)</code><br/>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [delegate](../-read-only-list/delegate.md) | <code>val delegate: List<T></code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [equals](equals.md) | <code>fun equals(other: Any?): Boolean</code><br/> |
| [hashCode](hash-code.md) | <code>fun hashCode(): Int</code><br/> |
| [toString](to-string.md) | <code>fun toString(): String</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [iterator](../-read-only-list/iterator.md) | <code>open fun iterator(): Iterator<T></code><br/> |
| [listIterator](../-read-only-list/list-iterator.md) | <code>open fun listIterator(): ListIterator<T></code><br/><code>open fun listIterator(index: Int): ListIterator<T></code><br/> |
| [subList](../-read-only-list/sub-list.md) | <code>open fun subList(fromIndex: Int, toIndex: Int): List<T></code><br/> |

### Companion Object Properties

|&nbsp;|&nbsp;|
|---|---|
| [serialVersionUID](serial-version-u-i-d.md) | <code>val serialVersionUID: Long</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnlyCollection](../kotlin.collections.-list/as-read-only-collection.md) | <code>fun <T> List<T>.asReadOnlyCollection(): Collection<T></code><br/>Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type |
| [batch](../kotlin.collections.-iterable/batch.md) | <code>fun <T> Iterable<T>.batch(n: Int): <ERROR CLASS><List<T>></code><br/>Batch a sequence into a sequence of lists of max N size<code>fun <T> Iterable<T>.batch(n: Int, forEachDo: (List<T>) -> Unit): Unit</code><br/>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group |
| [lazyBatch](../kotlin.collections.-iterable/lazy-batch.md) | <code>fun <T> Iterable<T>.lazyBatch(n: Int, forEachDo: (<ERROR CLASS><T>) -> Unit): Unit</code><br/>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy |
| [toImmutable](../kotlin.collections.-list/to-immutable.md) | <code>fun <T> List<T>.toImmutable(): List<T></code><br/>Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally |
| [whenAllNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-all-not-null.md) | <code>fun <T : Any, R : Any> Collection<T?>.whenAllNotNull(block: (List<T>) -> R): Unit</code><br/> |
| [whenAnyNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-any-not-null.md) | <code>fun <T : Any, R : Any> Collection<T?>.whenAnyNotNull(block: (List<T>) -> R): Unit</code><br/> |
