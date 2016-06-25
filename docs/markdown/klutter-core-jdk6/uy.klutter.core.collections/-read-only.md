[uy.klutter.core.collections](index.md) / [ReadOnly](.)


# ReadOnly
<code>interface ReadOnly</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L8)<br/>


### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnlyCollection](-read-only-collection/index.md) | <code>class ReadOnlyCollection<T> : Collection<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyIterator](-read-only-iterator/index.md) | <code>class ReadOnlyIterator<T> : Iterator<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyList](-read-only-list/index.md) | <code>open class ReadOnlyList<T> : List<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a List with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | <code>class ReadOnlyListIterator<T> : ListIterator<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyMap](-read-only-map/index.md) | <code>class ReadOnlyMap<K : Any, V> : Map<K, V>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | <code>class ReadOnlyRandomAccessList<T> : [ReadOnlyList](-read-only-list/index.md)<T>, List<T>, ReadOnly, [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type |
| [ReadOnlySet](-read-only-set/index.md) | <code>class ReadOnlySet<T> : Set<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)</code><br/>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type |
