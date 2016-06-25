[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.SharedData](index.md) / [promiseLock](.)


# promiseLock
<code>fun SharedData.promiseLock(name: String): Promise<Lock, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L38)<br/>
Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)

### Parameters
`name` - of the lock

**Return**
Promise&lt;Lock, Exception&gt;


