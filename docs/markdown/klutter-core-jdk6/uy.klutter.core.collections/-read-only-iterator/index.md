[uy.klutter.core.collections](../index.md) / [ReadOnlyIterator](.)


# ReadOnlyIterator
`class ReadOnlyIterator<T>&nbsp;:&nbsp;Iterator<T>, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L13)

Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyIterator(delegate:&nbsp;Iterator<T>)`<p>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [equals](equals.md) | `fun equals(other:&nbsp;Any?): Boolean` |
| [hashCode](hash-code.md) | `fun hashCode(): Int` |
| [toString](to-string.md) | `fun toString(): String` |

### Companion Object Properties

|&nbsp;|&nbsp;|
|---|---|
| [serialVersionUID](serial-version-u-i-d.md) | `val serialVersionUID: Long` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](../kotlin.collections.-iterator/as-read-only.md) | `fun <T> Iterator<T>.asReadOnly(): Iterator<T>`<p>Wraps the Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |
