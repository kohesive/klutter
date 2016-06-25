[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [promiseReply](.)


# promiseReply
`fun <T&nbsp;:&nbsp;Any> Vertx.promiseReply(address:&nbsp;String, message:&nbsp;Any): Promise<T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L266)

Sends the message and returns a promise of a reply to that message.

### Parameters
`address` - Message-bus address to send to

`message` - The message to send on the message-bus

**Return**
Promise&lt;T, Exception&gt;


