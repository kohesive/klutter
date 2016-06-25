[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.AsyncMap](index.md) / [promisePutIfAbsent](.)


# promisePutIfAbsent
`fun <K&nbsp;:&nbsp;Any, V> AsyncMap<K,&nbsp;V>.promisePutIfAbsent(key:&nbsp;K, value:&nbsp;V): Promise<V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L145)

Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)


`fun <K&nbsp;:&nbsp;Any, V> AsyncMap<K,&nbsp;V>.promisePutIfAbsent(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise<V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L159)

Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)


