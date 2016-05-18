[uy.klutter.vertx](.)


## Package uy.klutter.vertx


### Types


| [VertxInit](-vertx-init/index.md) | `object VertxInit`
Setup Kovenant do dispatch via Vert.x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types.

 |
| [VertxInjektModule](-vertx-injekt-module/index.md) | `abstract class VertxInjektModule&nbsp;:&nbsp;InjektModule` |
| [VertxInjektables](-vertx-injektables/index.md) | `object VertxInjektables&nbsp;:&nbsp;[VertxInjektModule](-vertx-injekt-module/index.md)`
This Injekt Module does a few things:

 |
| [VertxWithSlf4jInjektables](-vertx-with-slf4j-injektables/index.md) | `object VertxWithSlf4jInjektables&nbsp;:&nbsp;[VertxInjektModule](-vertx-injekt-module/index.md)`
Same as VertxInjektables but routes Vert.x and Hazelcast logging facades to use Slf4j, and app directly to Slf4j

 |


### Exceptions


| [WrappedThrowableException](-wrapped-throwable-exception/index.md) | `class WrappedThrowableException&nbsp;:&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)` |


### Extensions for External Classes


| [io.vertx.core.Vertx](io.vertx.core.-vertx/index.md) |  |
| [io.vertx.core.shareddata.AsyncMap](io.vertx.core.shareddata.-async-map/index.md) |  |
| [io.vertx.core.shareddata.SharedData](io.vertx.core.shareddata.-shared-data/index.md) |  |
| [io.vertx.ext.web.Route](io.vertx.ext.web.-route/index.md) |  |
| [io.vertx.ext.web.RoutingContext](io.vertx.ext.web.-routing-context/index.md) |  |
| [io.vertx.ext.web.Session](io.vertx.ext.web.-session/index.md) |  |


### Functions


| [promiseResult](promise-result.md) | `fun &lt;T&gt; promiseResult(deferred:&nbsp;Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;): (AsyncResult&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit`
Helper to convert an expectation of `AsyncResult` into a promise represented by `Deferred&lt;T,Exception&gt;`

 |
| [promiseVertx](promise-vertx.md) | `fun promiseVertx(): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Start vert.x (alias for [`vertx()`](vertx.md) function)

`fun promiseVertx(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Start vert.x (alias for [`vertx(options)`](vertx.md) function)

 |
| [promiseVertxCluster](promise-vertx-cluster.md) | `fun promiseVertxCluster(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Start clustered vert.x (alias for [`vertxCluster(options)`](vertx-cluster.md) function)

 |
| [promiseVoidResult](promise-void-result.md) | `fun promiseVoidResult(deferred:&nbsp;Deferred&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;): (AsyncResult&lt;[Void](http://docs.oracle.com/javase/6/docs/api/java/lang/Void.html)&gt;)&nbsp;-&gt;&nbsp;Unit`
Same as [promiseResult](promise-result.md) but to be used when the `AsyncResult` type is `Void`

 |
| [setupVertxJsonForKotlin](setup-vertx-json-for-kotlin.md) | `fun setupVertxJsonForKotlin(): Unit`
Setup the Vert.x singleton for Jackson ObjectMapper to support Kotlin and JDK 8 types.  This should be called very early
in your application lifecycle.

 |
| [setupVertxLoggingToSlf4j](setup-vertx-logging-to-slf4j.md) | `fun setupVertxLoggingToSlf4j(): Unit`
Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application
before the logging systems are activated.

 |
| [vertx](vertx.md) | `fun vertx(): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun vertx(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Start vert.x

 |
| [vertxCluster](vertx-cluster.md) | `fun vertxCluster(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Start clustered vert.x

 |
| [vertxContext](vertx-context.md) | `fun vertxContext(): Context?`
retrieve the current vert.x context if one is attached to the current thread

 |
| [withDeferred](with-deferred.md) | `fun &lt;T&gt; withDeferred(codeBlock:&nbsp;(Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;)&nbsp;-&gt;&nbsp;Unit): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Helper function that creates a deferred for a block of code and returns the promise associated with the deferred

 |

