[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [batch](.)


# batch
<code>fun <T> Iterable<T>.batch(n: Int): <ERROR CLASS><List<T>></code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L14)<br/><p>Batch a sequence into a sequence of lists of max N size</p><br/><br/><code>fun <T> Iterable<T>.batch(n: Int, forEachDo: (List<T>) -> Unit): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L28)<br/><p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p><br/><br/>