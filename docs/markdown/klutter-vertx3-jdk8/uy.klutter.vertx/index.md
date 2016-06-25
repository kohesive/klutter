[uy.klutter.vertx](.)


## Package uy.klutter.vertx

### Types

|&nbsp;|&nbsp;|
|---|---|
| [VertxInit](-vertx-init/index.md) | <code>object VertxInit</code><br/>Setup Kovenant do dispatch via Vert.x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types. |
| [VertxInjektModule](-vertx-injekt-module/index.md) | <code>abstract class VertxInjektModule : InjektModule</code><br/> |
| [VertxInjektables](-vertx-injektables/index.md) | <code>object VertxInjektables : [VertxInjektModule](-vertx-injekt-module/index.md)</code><br/>This Injekt Module does a few things: |
| [VertxWithSlf4jInjektables](-vertx-with-slf4j-injektables/index.md) | <code>object VertxWithSlf4jInjektables : [VertxInjektModule](-vertx-injekt-module/index.md)</code><br/>Same as VertxInjektables but routes Vert.x and Hazelcast logging facades to use Slf4j, and app directly to Slf4j |

### Exceptions

|&nbsp;|&nbsp;|
|---|---|
| [WrappedThrowableException](-wrapped-throwable-exception/index.md) | <code>class WrappedThrowableException : [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)</code><br/> |

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
| [promiseResult](promise-result.md) | <code>fun <T> promiseResult(deferred: Deferred<T, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>): (AsyncResult<T>) -> Unit</code><br/>Helper to convert an expectation of `AsyncResult` into a promise represented by `Deferred&lt;T,Exception&gt;` |
| [promiseVertx](promise-vertx.md) | <code>fun promiseVertx(): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Start vert.x (alias for [`vertx()`](vertx.md) function)<code>fun promiseVertx(options: VertxOptions): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Start vert.x (alias for [`vertx(options)`](vertx.md) function) |
| [promiseVertxCluster](promise-vertx-cluster.md) | <code>fun promiseVertxCluster(options: VertxOptions): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Start clustered vert.x (alias for [`vertxCluster(options)`](vertx-cluster.md) function) |
| [promiseVoidResult](promise-void-result.md) | <code>fun promiseVoidResult(deferred: Deferred<Unit, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>): (AsyncResult<[Void](http://docs.oracle.com/javase/6/docs/api/java/lang/Void.html)>) -> Unit</code><br/>Same as [promiseResult](promise-result.md) but to be used when the `AsyncResult` type is `Void` |
| [setupVertxJsonForKotlin](setup-vertx-json-for-kotlin.md) | <code>fun setupVertxJsonForKotlin(): Unit</code><br/>Setup the Vert.x singleton for Jackson ObjectMapper to support Kotlin and JDK 8 types.  This should be called very early<br/>in your application lifecycle. |
| [setupVertxLoggingToSlf4j](setup-vertx-logging-to-slf4j.md) | <code>fun setupVertxLoggingToSlf4j(): Unit</code><br/>Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application<br/>before the logging systems are activated. |
| [vertx](vertx.md) | <code>fun vertx(): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/><code>fun vertx(options: VertxOptions): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Start vert.x |
| [vertxCluster](vertx-cluster.md) | <code>fun vertxCluster(options: VertxOptions): Promise<Vertx, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Start clustered vert.x |
| [vertxContext](vertx-context.md) | <code>fun vertxContext(): Context?</code><br/>retrieve the current vert.x context if one is attached to the current thread |
| [withDeferred](with-deferred.md) | <code>fun <T> withDeferred(codeBlock: (Deferred<T, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>) -> Unit): Promise<T, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/>Helper function that creates a deferred for a block of code and returns the promise associated with the deferred |
