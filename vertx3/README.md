## klutter/vertx3

Helper functions for working with Vert.x-3 including converting async/executeBlocking calls into Kovenant Promises.  This module requires JDK 8 since Vert.x 3 requires the JDK 8 or newer.

## Vertx and Hazelcast Logging Facades

There is a utility functions to configure Vert.x and Hazelcast (when clustered) logging facades to log through Slf4j, this must be called before creating Vert.x or Hazelcast instances.

```
setupVertxLoggingToSlf4j()
```

When using [Injekt](https://github.com/kohesive/injekt) dependency injection, you can setup logging by importing either `VertxInjektables` module which will add a Vert.x facade based
logger to be injected by Injekt.  Or `VertxWithSlf4jInjektables` to setup an injectable direct to SLf4j logger.  These modules also setup Vert.x embedded JSON (as noted below).

## Vertx JSON with Kotlin and JDK 8

To make sure the embedded Jackson used by Vert.x is Kotlin capable, you can use a top level function to add Kotlin module, and JDK 8 types to Jackson including Temporal, Option, and more:

```
setupVertxJsonForKotlin()
```

When using [Injekt](https://github.com/kohesive/injekt) dependency injection, both the `VertxInjektables` and `VertxWithSlf4jInjektables` modules automatically setup
Jackson by calling this utility method and adding a singleton factory for `ObjectMapper` that is the same instance Vert.x uses for data binding.  More JSON helpers are
provided, see JSON Builders below.

## Vertx Core + Kovenant

Vert.x can be started using top-level functions that also ensure that Kovenant is setup correctly to dispatch using Vert.x threads, and maintaining the Vert.x context
across thread dispatches (as Vert.x does when you `executeBlocking()` or `runOnContext()`) and using the correct thread at the right time. The top-level functions also
wrap Throwables that are not Exceptions with a wrapped exception so that they play nicely with Kovenant Promise failures.

The following top-level functions work with Promises all returning a `Promise<Vertx, Exception>``:

* `vertx()`
* `vertx(options: VertxOptions)`
* `vertxCluster(options: VertxOptions)` (requires vert-hazelcast module)

Other top level functions:

* `vertxContext()` returning the context from the current thread if one exists, works across promise threads since context is copied to dispatched threads

In addition there are extension methods on the `Vertx` class that provide promise based versions of deployment, shutdown and close.

* `Vertx.promiseDeployVerticle(verticle: Verticle)`
* `Vertx.promiseDeployVerticle(verticle: Verticle, options: DeploymentOptions)`
* `Vertx.promiseDeployVerticle(verticleClass: KClass<T>)`
* `Vertx.promiseDeployVerticle(verticleClass: KClass<T>, options: DeploymentOptions)`
* `Vertx.promiseDeployVerticle(verticleClass: Class<T>)`
* `Vertx.promiseDeployVerticle(verticleClass: Class<T>, options: DeploymentOptions)`
* `Vertx.promiseDeployVerticle(name: String)`
* `Vertx.promiseDeployVerticle(name: String, options: DeploymentOptions)`
* `Vertx.promiseUndeploy(deploymentId: String)`
* `Vertx.promiseClose()`

And some additional extensions, not promise based but adding additional variants of existing methods:

* `Vertx.deployVerticle(verticleClass: KClass<T>)`
* `Vertx.deployVerticle(verticleClass: Class<T>)`

For async code execution, instead of `Vertx.executeBlocking` method, use one of:

* `Vertx.promiseExecuteBlocking(blockingCode: () -> T): Promise<T, Exception>`
* `Vertx.executeBlocking(blockingCode: () -> T): Promise<T, Exception>`

There is also a helper function `promiseResult(deferred)` to help turn a `Deferred<T, Exception>` into an `AsyncResult<T>` for methods that require one, for example:

```kotlin
public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
    val deferred = deferred<SomeType, Exception>()
    vertx.someAsyncAction( promiseResult(deferred) )
    return deferred.promise
}
```

If you call the top-level functions to create Vert.x, the intialization of Kovenant is done for you.  If not you need to call a function before using Kovenant promises:

```
VertxInit.ensure()
```

## Vert.x JSON Builders

Vert.x provides its own `JsonObject` and `JsonArray` that it configures to work well with Jackson.  It expects these objects in various API's.  Klutter-vertx3 adds
JSON Builders to make it easier to create these, and also helper functions to convert Kotlin objects into these objects.

* `jsonObjectFromString(json: String): JsonObject`
* `jsonArrayFromString(json: String): JsonArray`
* `jsonObjectFromMap(map: Map<String, V>): JsonObject`
* `jsonArrayFromList(list: List<T>): JsonArray`
* `jsonObjectFromPojo(something: Any): JsonObject`

And for the builders:

* `jsonObject(init: JsonObject.() -> Unit): JsonObject` - an object builder
* `jsonArray(init: JsonArray.() -> Unit): JsonArray` - an array builder

Along with extension functions for `JsonObject` and `JsonArray` to continue the builder pattern:

* `JsonObject.putObject(name: String, init: JsonObject.() -> Unit): JsonObject` (fluent)
* `JsonObject.putArray(name: String, init: JsonArray.() -> Unit): JsonObject` (fluent)
* `JsonArray.addObject(init: JsonObject.() -> Unit): JsonArray` (fluent)
* `JsonArray.addArray(init: JsonArray.() -> Unit): JsonArray` (fluent)

And extensions to take a Temporal and add it to JSON as an ISO formatted date string:

* `JsonObject.putDateIsoString(name: String, value: Temporal): JsonObject` (fluent)
* `JsonArray.addDateIsoString(value: Temporal): JsonArray = add(value.toIsoString())` (fluent)

## Vert.x Web

A few helper functions are provided for Vert.x Web:

* `Session.putSafely(key: String, value: Any?)` - if the value is null, removes item from session, otherwise adds it. (some clustered session stores cannot handle null values)
* `Session.removeSafely(key: String): Any?` - same as `Session.remove` but counterpart to `putSafely()`
* `RoutingContext.externalizeUrl(): String` - make current URI of request into external URL using scheme, host and port of proxy or load balancer (headers `X-Forwarded-Proto`, `X-Forwarded-Host` and `X-Forwarded-Port`), and if those are not available the actual Vert.x listener host, scheme and port.
* `RoutingContext.externalizeUrl(url: String)` - convert a URL to externalized form, unless it is already absolute will process the same as the method above.  See JavaDoc for more information.

There is another related library that extends `Klutter/vertx3` called [Kovert](https://github.com/kohesive/kovert) which allows you to bind a Kotlin class to a route and have it act as a REST controller.

## Other Libraries

* [Injekt](https://github.com/kohesive/injekt) - dependency injection for Kotlin
* [Kovenant](http://kovenant.komponents.nl) - promises for Kotlin
* [Klutter](https://github.com/klutter/klutter) - the other modules in this library
* [Kovert](https://github.com/kohesive/kovert) - invisible REST framework for Kotlin + Vert.x
* [Cy6erGn0m/vertx3-lang-kotlin](https://github.com/cy6erGn0m/vertx3-lang-kotlin) - an alternative library for Vert.x-3 with Kotlin

## Roadmap (in random order)

* helpers for other areas that could use promises, such as HTTP Client, File system access via Vert.x
* looking at any async pattern in Vert.x that causes nested handlers, and promise-ize
