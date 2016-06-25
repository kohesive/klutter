[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](index.md) / [batch](.)


# batch
`fun &lt;T&gt; Iterable&lt;T&gt;.batch(n:&nbsp;Int): &lt;ERROR CLASS&gt;&lt;List&lt;T&gt;&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L14)

Batch a sequence into a sequence of lists of max N size


`fun &lt;T&gt; Iterable&lt;T&gt;.batch(n:&nbsp;Int, forEachDo:&nbsp;(List&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/CollectionsBatching.kt#L28)

Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group


