[uy.klutter.core.collections](.)


## Package uy.klutter.core.collections

### Types

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnly](-read-only.md) | `interface ReadOnly` |
| [ReadOnlyCollection](-read-only-collection/index.md) | `class ReadOnlyCollection<T>&nbsp;:&nbsp;Collection<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyIterator](-read-only-iterator/index.md) | `class ReadOnlyIterator<T>&nbsp;:&nbsp;Iterator<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyList](-read-only-list/index.md) | `open class ReadOnlyList<T>&nbsp;:&nbsp;List<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | `class ReadOnlyListIterator<T>&nbsp;:&nbsp;ListIterator<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyMap](-read-only-map/index.md) | `class ReadOnlyMap<K&nbsp;:&nbsp;Any, V>&nbsp;:&nbsp;Map<K,&nbsp;V>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | `class ReadOnlyRandomAccessList<T>&nbsp;:&nbsp;[ReadOnlyList](-read-only-list/index.md)<T>, List<T>, [ReadOnly](-read-only.md), [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type</p> |
| [ReadOnlySet](-read-only-set/index.md) | `class ReadOnlySet<T>&nbsp;:&nbsp;Set<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type</p> |

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
| [batch](batch.md) | `fun <T> <ERROR CLASS><T>.batch(n:&nbsp;Int): <ERROR CLASS><List<T>>`<p>Batch a sequence into a sequence of lists of max N size</p>`fun <T> <ERROR CLASS><T>.batch(n:&nbsp;Int, forEachDo:&nbsp;(List<T>)&nbsp;->&nbsp;Unit): Unit`<p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p> |
| [lazyBatch](lazy-batch.md) | `fun <T> <ERROR CLASS><T>.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(<ERROR CLASS><T>)&nbsp;->&nbsp;Unit): Unit`<p>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy</p> |
