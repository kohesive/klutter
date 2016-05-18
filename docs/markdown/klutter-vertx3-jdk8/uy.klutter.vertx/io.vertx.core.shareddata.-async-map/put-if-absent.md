[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.AsyncMap](index.md) / [putIfAbsent](.)


# putIfAbsent

`fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.putIfAbsent(key:&nbsp;K, value:&nbsp;V): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L152)

Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)



`fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.putIfAbsent(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L166)

Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)



