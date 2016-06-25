[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.SharedData](index.md) / [getLockWithTimeout](.)


# getLockWithTimeout
`fun SharedData.getLockWithTimeout(name:&nbsp;String, timeout:&nbsp;Long): Promise&lt;Lock,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxSharedData.kt#L74)

Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)
This alias for [SharedData.promiseLockWithTimeout(name,timeout)](#) and might be harder
to locate due to code completion favoring the built-in method of the same name.

### Parameters
`name` - of the lock

`timeout` - length of time to wait in milliseconds for the lock

**Return**
Promise&lt;Lock, Exception&gt;


