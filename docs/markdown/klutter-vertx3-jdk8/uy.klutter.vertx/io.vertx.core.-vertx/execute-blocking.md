[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [executeBlocking](.)


# executeBlocking
`fun &lt;T&nbsp;:&nbsp;Any&gt; Vertx.executeBlocking(blockingCode:&nbsp;()&nbsp;-&gt;&nbsp;T): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L247)

Execute blocking code using vert.x dispatcher returning a `Promise&lt;T,Exception&gt;`.  Since Kovenant and
vert.x dispatching are united, this is the same as doing `task{...}` in Kovenant except that no
vert.x context will be in thread local storage if you do not use this method.

### Parameters
`blockingCode` - code to execute

**Return**
Promise&lt;T, Exception&gt; where T is the return type of blockingCode


