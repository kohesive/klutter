[uy.klutter.core.collections](../index.md) / [ReadOnlyMap](.)


# ReadOnlyMap
`class ReadOnlyMap&lt;K&nbsp;:&nbsp;Any, V&gt;&nbsp;:&nbsp;Map&lt;K,&nbsp;V&gt;, [ReadOnly](../-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L163)

Wraps a Map with a lightweight delegating class that prevents casting back to mutable type



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ReadOnlyMap(delegate:&nbsp;Map&lt;K,&nbsp;V&gt;)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [entries](entries.md) | `val entries: Set&lt;Entry&lt;K,&nbsp;V&gt;&gt;` |
| [keys](keys.md) | `val keys: Set&lt;K&gt;` |
| [values](values.md) | `val values: Collection&lt;V&gt;` |

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
| [asReadOnly](../kotlin.collections.-map/as-read-only.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; Map&lt;K,&nbsp;V&gt;.asReadOnly(): Map&lt;K,&nbsp;V&gt;`<p>Wraps the Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [toImmutable](../kotlin.collections.-map/to-immutable.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; Map&lt;K,&nbsp;V&gt;.toImmutable(): Map&lt;K,&nbsp;V&gt;`<p>Copies the Map and then wraps with a lightweight delegating class that prevents casting back to mutable type</p> |
