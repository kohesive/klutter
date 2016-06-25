[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [batch](.)


# batch
`fun <T> Iterable<T>.batch(n:&nbsp;Int): <ERROR CLASS><List<T>>` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L14)

Batch a sequence into a sequence of lists of max N size


`fun <T> Iterable<T>.batch(n:&nbsp;Int, forEachDo:&nbsp;(List<T>)&nbsp;->&nbsp;Unit): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L28)

Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group


