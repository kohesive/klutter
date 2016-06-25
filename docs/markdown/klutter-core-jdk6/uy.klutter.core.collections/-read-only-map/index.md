[uy.klutter.core.collections](../index.md) / [ReadOnlyMap](.)


# ReadOnlyMap
<code>class ReadOnlyMap<K : Any, V> : Map<K, V>, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L163)<br/>
Wraps a Map with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ReadOnlyMap(delegate: Map<K, V>)</code><br/>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [entries](entries.md) | <code>val entries: Set<Entry<K, V>></code><br/> |
| [keys](keys.md) | <code>val keys: Set<K></code><br/> |
| [values](values.md) | <code>val values: Collection<V></code><br/> |

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

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](../kotlin.collections.-map/as-read-only.md) | <code>fun <K : Any, V> Map<K, V>.asReadOnly(): Map<K, V></code><br/>Wraps the Map with a lightweight delegating class that prevents casting back to mutable type |
| [toImmutable](../kotlin.collections.-map/to-immutable.md) | <code>fun <K : Any, V> Map<K, V>.toImmutable(): Map<K, V></code><br/>Copies the Map and then wraps with a lightweight delegating class that prevents casting back to mutable type |
