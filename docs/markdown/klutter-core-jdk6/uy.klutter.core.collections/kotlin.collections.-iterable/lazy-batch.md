[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [lazyBatch](.)


# lazyBatch
<code>fun <T> Iterable<T>.lazyBatch(n: Int, forEachDo: (<ERROR CLASS><T>) -> Unit): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L57)<br/>
A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
So, for purely lazy we only allow basically forEach when completely lazy


