[uy.klutter.core.collections](.)


## Package uy.klutter.core.collections


### Extensions for External Classes


| [kotlin.collections.Iterable](kotlin.collections.-iterable/index.md) |  |


### Functions


| [batch](batch.md) | `fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.batch(n:&nbsp;Int): &lt;ERROR CLASS&gt;&lt;List&lt;T&gt;&gt;`
Batch a sequence into a sequence of lists of max N size

`fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.batch(n:&nbsp;Int, forEachDo:&nbsp;(List&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`
Batch a sequence into a sequence of lists of max N size, and execute a lambda for each group

 |
| [lazyBatch](lazy-batch.md) | `fun &lt;T&gt; &lt;ERROR CLASS&gt;&lt;T&gt;.lazyBatch(n:&nbsp;Int, forEachDo:&nbsp;(&lt;ERROR CLASS&gt;&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit): Unit`
A purely Lazy batch must have the source consumed to progress, but does not need to materialize a list per iteration
So, for purely lazy we only allow basically forEach when completely lazy

 |

