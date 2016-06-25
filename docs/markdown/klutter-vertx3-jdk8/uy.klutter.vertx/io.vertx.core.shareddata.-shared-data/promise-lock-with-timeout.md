[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.SharedData](index.md) / [promiseLockWithTimeout](.)


# promiseLockWithTimeout
`fun SharedData.promiseLockWithTimeout(name:&nbsp;String, timeout:&nbsp;Long): Promise<Lock,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L61)

Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)

### Parameters
`name` - of the lock

`timeout` - length of time to wait in milliseconds for the lock

**Return**
Promise&lt;Lock, Exception&gt;


