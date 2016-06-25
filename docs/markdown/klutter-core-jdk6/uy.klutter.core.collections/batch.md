[uy.klutter.core.collections](index.md) / [batch](.)


# batch
`fun <T> <ERROR CLASS><T>.batch(n:&nbsp;Int): <ERROR CLASS><List<T>>` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L7)

Batch a sequence into a sequence of lists of max N size


`fun <T> <ERROR CLASS><T>.batch(n:&nbsp;Int, forEachDo:&nbsp;(List<T>)&nbsp;->&nbsp;Unit): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L21)

Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group


