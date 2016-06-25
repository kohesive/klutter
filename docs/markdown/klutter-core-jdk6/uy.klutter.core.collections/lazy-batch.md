[uy.klutter.core.collections](index.md) / [lazyBatch](.)


# lazyBatch
`fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(&lt;ERROR CLASS&gt;&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L47)

A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
So, for purely lazy we only allow basically forEach when completely lazy


