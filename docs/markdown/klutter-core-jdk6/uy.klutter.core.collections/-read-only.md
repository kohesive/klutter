[uy.klutter.core.collections](index.md) / [ReadOnly](.)


# ReadOnly
`interface ReadOnly` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L8)



### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [ReadOnlyCollection](-read-only-collection/index.md) | `class ReadOnlyCollection&lt;T&gt;&nbsp;:&nbsp;Collection&lt;T&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyIterator](-read-only-iterator/index.md) | `class ReadOnlyIterator&lt;T&gt;&nbsp;:&nbsp;Iterator&lt;T&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps an Iterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyList](-read-only-list/index.md) | `open class ReadOnlyList&lt;T&gt;&nbsp;:&nbsp;List&lt;T&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyListIterator](-read-only-list-iterator/index.md) | `class ReadOnlyListIterator&lt;T&gt;&nbsp;:&nbsp;ListIterator&lt;T&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a ListIterator with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyMap](-read-only-map/index.md) | `class ReadOnlyMap&lt;K&nbsp;:&nbsp;Any, V&gt;&nbsp;:&nbsp;Map&lt;K,&nbsp;V&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Map with a lightweight delegating class that prevents casting back to mutable type</p> |
| [ReadOnlyRandomAccessList](-read-only-random-access-list/index.md) | `class ReadOnlyRandomAccessList&lt;T&gt;&nbsp;:&nbsp;[ReadOnlyList](-read-only-list/index.md)&lt;T&gt;, List&lt;T&gt;, ReadOnly, [RandomAccess](http://docs.oracle.com/javase/6/docs/api/java/util/RandomAccess.html), [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a List that is also RandomAccess with a delegating class that prevents casting back to mutable type</p> |
| [ReadOnlySet](-read-only-set/index.md) | `class ReadOnlySet&lt;T&gt;&nbsp;:&nbsp;Set&lt;T&gt;, ReadOnly, [Serializable](http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html)`<p>Wraps a Set with a lightweight delegating class that prevents casting back to mutable type</p> |
