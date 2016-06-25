[uy.klutter.core.collections](index.md) / [batch](.)


# batch
<code>fun <T> <ERROR CLASS><T>.batch(n: Int): <ERROR CLASS><List<T>></code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L7)<br/><p>Batch a sequence into a sequence of lists of max N size</p><br/><br/><code>fun <T> <ERROR CLASS><T>.batch(n: Int, forEachDo: (List<T>) -> Unit): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L21)<br/><p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p><br/><br/>