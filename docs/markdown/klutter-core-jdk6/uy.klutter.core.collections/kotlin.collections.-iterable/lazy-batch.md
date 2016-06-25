[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [lazyBatch](.)


# lazyBatch
`fun <T> Iterable<T>.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(<ERROR CLASS><T>)&nbsp;->&nbsp;Unit): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L57)

A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
So, for purely lazy we only allow basically forEach when completely lazy


