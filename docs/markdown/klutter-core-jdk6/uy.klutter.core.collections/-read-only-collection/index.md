[uy.klutter.core.collections](../index.md) / [ReadOnlyCollection](.)


# ReadOnlyCollection
`class ReadOnlyCollection<T>&nbsp;:&nbsp;Collection<T>, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L34)

Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyCollection(delegate:&nbsp;Collection<T>)`<p>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [equals](equals.md) | `fun equals(other:&nbsp;Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [iterator](iterator.md) | `fun iterator(): Iterator<T>` |
| [toString](to-string.md) | `fun toString(): String` |

### Companion Object Properties

|&nbsp;|&nbsp;|
|---|---|
| [serialVersionUID](serial-version-u-i-d.md) | `val serialVersionUID: Long` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](../kotlin.collections.-collection/as-read-only.md) | `fun <T> Collection<T>.asReadOnly(): Collection<T>`<p>Wraps the Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [batch](../kotlin.collections.-iterable/batch.md) | `fun <T> Iterable<T>.batch(n:&nbsp;Int): <ERROR CLASS><List<T>>`<p>Batch a sequence into a sequence of lists of max N size</p>`fun <T> Iterable<T>.batch(n:&nbsp;Int, forEachDo:&nbsp;(List<T>)&nbsp;->&nbsp;Unit): Unit`<p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p> |
| [lazyBatch](../kotlin.collections.-iterable/lazy-batch.md) | `fun <T> Iterable<T>.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(<ERROR CLASS><T>)&nbsp;->&nbsp;Unit): Unit`<p>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy</p> |
| [whenAllNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-all-not-null.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> Collection<T?>.whenAllNotNull(block:&nbsp;(List<T>)&nbsp;->&nbsp;R): Unit` |
| [whenAnyNotNull](../../uy.klutter.core.common/kotlin.collections.-collection/when-any-not-null.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> Collection<T?>.whenAnyNotNull(block:&nbsp;(List<T>)&nbsp;->&nbsp;R): Unit` |
