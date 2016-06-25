[uy.klutter.vertx](.)


## Package uy.klutter.vertx

### Types

|&nbsp;|&nbsp;|
|---|---|
| [VertxInit](-vertx-init/index.md) | `object VertxInit`<p>Setup Kovenant do dispatch via Vert.x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types.</p> |
| [VertxInjektModule](-vertx-injekt-module/index.md) | `abstract class VertxInjektModule&nbsp;:&nbsp;InjektModule` |
| [VertxInjektables](-vertx-injektables/index.md) | `object VertxInjektables&nbsp;:&nbsp;[VertxInjektModule](-vertx-injekt-module/index.md)`<p>This Injekt Module does a few things:</p> |
| [VertxWithSlf4jInjektables](-vertx-with-slf4j-injektables/index.md) | `object VertxWithSlf4jInjektables&nbsp;:&nbsp;[VertxInjektModule](-vertx-injekt-module/index.md)`<p>Same as VertxInjektables but routes Vert.x and Hazelcast logging facades to use Slf4j, and app directly to Slf4j</p> |

### Exceptions

|&nbsp;|&nbsp;|
|---|---|
| [WrappedThrowableException](-wrapped-throwable-exception/index.md) | `class WrappedThrowableException&nbsp;:&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)` |

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [io.vertx.core.Vertx](io.vertx.core.-vertx/index.md) |  |
| [io.vertx.core.shareddata.AsyncMap](io.vertx.core.shareddata.-async-map/index.md) |  |
| [io.vertx.core.shareddata.SharedData](io.vertx.core.shareddata.-shared-data/index.md) |  |
| [io.vertx.ext.web.Route](io.vertx.ext.web.-route/index.md) |  |
| [io.vertx.ext.web.RoutingContext](io.vertx.ext.web.-routing-context/index.md) |  |
| [io.vertx.ext.web.Session](io.vertx.ext.web.-session/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [promiseResult](promise-result.md) | `fun &lt;T&gt; promiseResult(deferred:&nbsp;Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;): (AsyncResult&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit`<p>Helper to convert an expectation of `AsyncResult` into a promise represented by `Deferred&lt;T,Exception&gt;`</p> |
| [promiseVertx](promise-vertx.md) | `fun promiseVertx(): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Start vert.x (alias for [`vertx()`](vertx.md) function)</p>`fun promiseVertx(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Start vert.x (alias for [`vertx(options)`](vertx.md) function)</p> |
| [promiseVertxCluster](promise-vertx-cluster.md) | `fun promiseVertxCluster(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Start clustered vert.x (alias for [`vertxCluster(options)`](vertx-cluster.md) function)</p> |
| [promiseVoidResult](promise-void-result.md) | `fun promiseVoidResult(deferred:&nbsp;Deferred&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;): (AsyncResult&lt;[Void](http://docs.oracle.com/javase/6/docs/api/java/lang/Void.html)&gt;)&nbsp;-&gt;&nbsp;Unit`<p>Same as [promiseResult](promise-result.md) but to be used when the `AsyncResult` type is `Void`</p> |
| [setupVertxJsonForKotlin](setup-vertx-json-for-kotlin.md) | `fun setupVertxJsonForKotlin(): Unit`<p>Setup the Vert.x singleton for Jackson ObjectMapper to support Kotlin and JDK 8 types.  This should be called very early<br/>in your application lifecycle.</p> |
| [setupVertxLoggingToSlf4j](setup-vertx-logging-to-slf4j.md) | `fun setupVertxLoggingToSlf4j(): Unit`<p>Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application<br/>before the logging systems are activated.</p> |
| [vertx](vertx.md) | `fun vertx(): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<br/>`fun vertx(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Start vert.x</p> |
| [vertxCluster](vertx-cluster.md) | `fun vertxCluster(options:&nbsp;VertxOptions): Promise&lt;Vertx,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Start clustered vert.x</p> |
| [vertxContext](vertx-context.md) | `fun vertxContext(): Context?`<p>retrieve the current vert.x context if one is attached to the current thread</p> |
| [withDeferred](with-deferred.md) | `fun &lt;T&gt; withDeferred(codeBlock:&nbsp;(Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;)&nbsp;-&gt;&nbsp;Unit): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`<p>Helper function that creates a deferred for a block of code and returns the promise associated with the deferred</p> |
