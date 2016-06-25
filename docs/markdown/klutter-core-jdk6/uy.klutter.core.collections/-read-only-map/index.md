[uy.klutter.core.collections](../index.md) / [ReadOnlyMap](.)


# ReadOnlyMap
`class ReadOnlyMap<K&nbsp;:&nbsp;Any, V>&nbsp;:&nbsp;Map<K,&nbsp;V>, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L163)

Wraps a Map with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyMap(delegate:&nbsp;Map<K,&nbsp;V>)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [entries](entries.md) | `val entries: Set<Entry<K,&nbsp;V>>` |
| [keys](keys.md) | `val keys: Set<K>` |
| [values](values.md) | `val values: Collection<V>` |

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
| [asReadOnly](../kotlin.collections.-map/as-read-only.md) | `fun <K&nbsp;:&nbsp;Any, V> Map<K,&nbsp;V>.asReadOnly(): Map<K,&nbsp;V>`<p>Wraps the Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [toImmutable](../kotlin.collections.-map/to-immutable.md) | `fun <K&nbsp;:&nbsp;Any, V> Map<K,&nbsp;V>.toImmutable(): Map<K,&nbsp;V>`<p>Copies the Map and then wraps with a lightweight delegating class that prevents casting back to mutable type</p> |
