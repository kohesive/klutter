[uy.klutter.core.collections](../index.md) / [kotlin.collections.Iterable](.)


### Extensions for kotlin.collections.Iterable

|&nbsp;|&nbsp;|
|---|---|
| [batch](batch.md) | `fun &lt;T&gt; Iterable&lt;T&gt;.batch(n:&nbsp;Int): &lt;ERROR CLASS&gt;&lt;List&lt;T&gt;&gt;`<p>Batch a sequence into a sequence of lists of max N size</p>`fun &lt;T&gt; Iterable&lt;T&gt;.batch(n:&nbsp;Int, forEachDo:&nbsp;(List&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`<p>Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group</p> |
| [lazyBatch](lazy-batch.md) | `fun &lt;T&gt; Iterable&lt;T&gt;.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(&lt;ERROR CLASS&gt;&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`<p>A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration<br/>So, for purely lazy we only allow basically forEach when completely lazy</p> |
