[uy.klutter.vertx](index.md) / [withDeferred](.)


# withDeferred

`fun &lt;T&gt; withDeferred(codeBlock:&nbsp;(Deferred&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;)&nbsp;-&gt;&nbsp;Unit): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L397)

Helper function that creates a deferred for a block of code and returns the promise associated with the deferred


for example:


```
public fun someAsynActionAsPromise(): Promise&lt;SomeType, Exception&gt; {
    return withDeferred { vertx.someAsyncAction( promiseResult(it) ) }
}
```




### Parameters

`codeBlock` - the block of code that is executed and will resolve or reject the `deferred`

**Return**
Promise&lt;T, Exception&gt; where `T` is the return type of the codeBlock



