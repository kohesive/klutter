[uy.klutter.core.collections](index.md) / [ReadOnly](.)


# ReadOnly
`interface ReadOnly` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L8)



### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnlyCollection](-read-only-collection/index.md) | `class ReadOnlyCollection<T>&nbsp;:&nbsp;Collection<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyIterator](-read-only-iterator/index.md) | `class ReadOnlyIterator<T>&nbsp;:&nbsp;Iterator<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyList](-read-only-list/index.md) | `open class ReadOnlyList<T>&nbsp;:&nbsp;List<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | `class ReadOnlyListIterator<T>&nbsp;:&nbsp;ListIterator<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyMap](-read-only-map/index.md) | `class ReadOnlyMap<K&nbsp;:&nbsp;Any, V>&nbsp;:&nbsp;Map<K,&nbsp;V>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | `class ReadOnlyRandomAccessList<T>&nbsp;:&nbsp;[ReadOnlyList](-read-only-list/index.md)<T>, List<T>, ReadOnly, [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type</p> |
| [ReadOnlySet](-read-only-set/index.md) | `class ReadOnlySet<T>&nbsp;:&nbsp;Set<T>, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type</p> |
