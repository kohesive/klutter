[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.SharedData](index.md) / [getClusterWideMap](.)


# getClusterWideMap
<code>fun <K : Any, V : Any> SharedData.getClusterWideMap(name: String): Promise<AsyncMap<K, V>, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L28)<br/>
Retrieve a vert.x cluster wide map (see Vert.x SharedData class for more documentation)
This alias for [SharedData.promiseClusterWideMap(name)](#) and might be harder
to locate due to code completion favoring the built-in method of the same name.

### Parameters
`name` - of the map

**Return**
Promise&lt;AsyncMap&lt;K, V&gt;, Exception&gt;


