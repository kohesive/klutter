[uy.klutter.vertx](index.md) / [promiseResult](.)


# promiseResult
`fun &lt;T&gt; promiseResult(deferred:&nbsp;Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;): (AsyncResult&lt;T&gt;)&nbsp;-&gt;&nbsp;Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L348)

Helper to convert an expectation of `AsyncResult` into a promise represented by `Deferred&lt;T,Exception&gt;`

for example:

```
​​​​​public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
​​​​​    val deferred = deferred<SomeType, Exception>()
​​​​​    vertx.someAsyncAction( promiseResult(deferred) )
​​​​​    return deferred.promise
​​​​​}
```

or a shorter version:

```
​​​​​public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
​​​​​    return withDeferred { vertx.someAsyncAction( promiseResult(it) ) }
​​​​​}
```




