[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](.)


### Extensions for kotlin.collections.Iterable

|&nbsp;|&nbsp;|
|---|---|
| [batch](batch.md) | <code>fun <T> Iterable<T>.batch(n: Int): <ERROR CLASS><List<T>></code><br/>Batch a sequence into a sequence of lists of max N size<code>fun <T> Iterable<T>.batch(n: Int, forEachDo: (List<T>) -> Unit): Unit</code><br/>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group |
| [lazyBatch](lazy-batch.md) | <code>fun <T> Iterable<T>.lazyBatch(n: Int, forEachDo: (<ERROR CLASS><T>) -> Unit): Unit</code><br/>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy |
