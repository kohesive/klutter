[uy.klutter.core.collections](../index.md) / [ReadOnlyListIterator](.)


# ReadOnlyListIterator
<code>class ReadOnlyListIterator<T> : ListIterator<T>, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L59)<br/>
Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ReadOnlyListIterator(delegate: ListIterator<T>)</code><br/>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [equals](equals.md) | <code>fun equals(other: Any?): Boolean</code><br/> |
| [hashCode](hash-code.md) | <code>fun hashCode(): Int</code><br/> |
| [toString](to-string.md) | <code>fun toString(): String</code><br/> |

### Companion Object Properties

|&nbsp;|&nbsp;|
|---|---|
| [serialVersionUID](serial-version-u-i-d.md) | <code>val serialVersionUID: Long</code><br/> |
