[uy.klutter.core.collections](.)


## Package uy.klutter.core.collections

### Types

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnly](-read-only.md) | `interface ReadOnly` |
| [ReadOnlyCollection](-read-only-collection/index.md) | `class ReadOnlyCollection&lt;T&gt;&nbsp;:&nbsp;Collection&lt;T&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyIterator](-read-only-iterator/index.md) | `class ReadOnlyIterator&lt;T&gt;&nbsp;:&nbsp;Iterator&lt;T&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyList](-read-only-list/index.md) | `open class ReadOnlyList&lt;T&gt;&nbsp;:&nbsp;List&lt;T&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | `class ReadOnlyListIterator&lt;T&gt;&nbsp;:&nbsp;ListIterator&lt;T&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyMap](-read-only-map/index.md) | `class ReadOnlyMap&lt;K&nbsp;:&nbsp;Any, V&gt;&nbsp;:&nbsp;Map&lt;K,&nbsp;V&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | `class ReadOnlyRandomAccessList&lt;T&gt;&nbsp;:&nbsp;[ReadOnlyList](-read-only-list/index.md)&lt;T&gt;, List&lt;T&gt;, [ReadOnly](-read-only.md), [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type</p> |
| [ReadOnlySet](-read-only-set/index.md) | `class ReadOnlySet&lt;T&gt;&nbsp;:&nbsp;Set&lt;T&gt;, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type</p> |

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [kotlin.collections.Collection](kotlin.collections.-collection/index.md) |  |
| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |
| [kotlin.collections.Iterator](kotlin.collections.-iterator/index.md) |  |
| [kotlin.collections.List](kotlin.collections.-list/index.md) |  |
| [kotlin.collections.ListIterator](kotlin.collections.-list-iterator/index.md) |  |
| [kotlin.collections.Map](kotlin.collections.-map/index.md) |  |
| [kotlin.collections.Set](kotlin.collections.-set/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [batch](batch.md) | `fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.batch(n:&nbsp;Int): &lt;ERROR CLASS&gt;&lt;List&lt;T&gt;&gt;`<p>Batch a sequence into a sequence of lists of max N size</p>`fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.batch(n:&nbsp;Int, forEachDo:&nbsp;(List&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`<p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p> |
| [lazyBatch](lazy-batch.md) | `fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(&lt;ERROR CLASS&gt;&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`<p>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy</p> |
