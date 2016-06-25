[uy.klutter.core.collections](.)


## Package uy.klutter.core.collections

### Types

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnly](-read-only.md) | <code>interface ReadOnly</code><br/> |
| [ReadOnlyCollection](-read-only-collection/index.md) | <code>class ReadOnlyCollection<T> : Collection<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyIterator](-read-only-iterator/index.md) | <code>class ReadOnlyIterator<T> : Iterator<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyList](-read-only-list/index.md) | <code>open class ReadOnlyList<T> : List<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a List with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | <code>class ReadOnlyListIterator<T> : ListIterator<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyMap](-read-only-map/index.md) | <code>class ReadOnlyMap<K : Any, V> : Map<K, V>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | <code>class ReadOnlyRandomAccessList<T> : [ReadOnlyList](-read-only-list/index.md)<T>, List<T>, [ReadOnly](-read-only.md), [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type |
| [ReadOnlySet](-read-only-set/index.md) | <code>class ReadOnlySet<T> : Set<T>, [ReadOnly](-read-only.md), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type |

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
| [batch](batch.md) | <code>fun <T> <ERROR CLASS><T>.batch(n: Int): <ERROR CLASS><List<T>></code><br/>Batch a sequence into a sequence of lists of max N size<code>fun <T> <ERROR CLASS><T>.batch(n: Int, forEachDo: (List<T>) -> Unit): Unit</code><br/>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group |
| [lazyBatch](lazy-batch.md) | <code>fun <T> <ERROR CLASS><T>.lazyBatch(n: Int, forEachDo: (<ERROR CLASS><T>) -> Unit): Unit</code><br/>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy |
