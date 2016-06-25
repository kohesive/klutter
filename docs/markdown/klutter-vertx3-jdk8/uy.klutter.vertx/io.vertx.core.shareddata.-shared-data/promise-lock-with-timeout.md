[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.SharedData](index.md) / [promiseLockWithTimeout](.)


# promiseLockWithTimeout
<code>fun SharedData.promiseLockWithTimeout(name: String, timeout: Long): Promise<Lock, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L61)<br/>
Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)

### Parameters
`name` - of the lock

`timeout` - length of time to wait in milliseconds for the lock

**Return**
Promise&lt;Lock, Exception&gt;


