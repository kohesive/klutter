[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [promiseReply](.)


# promiseReply
<code>fun <T : Any> Vertx.promiseReply(address: String, message: Any): Promise<T, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L266)<br/>
Sends the message and returns a promise of a reply to that message.

### Parameters
`address` - Message-bus address to send to

`message` - The message to send on the message-bus

**Return**
Promise&lt;T, Exception&gt;


