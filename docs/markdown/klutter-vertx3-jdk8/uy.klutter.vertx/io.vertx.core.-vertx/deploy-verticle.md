[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [deployVerticle](.)


# deployVerticle
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.deployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L186)

Deploy a verticle async without waiting for it to complete or tracking it in any way

### Parameters
`verticleClass` - reference to Verticle class (Kotlin `KClass`) to deploy


`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.deployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L196)

Deploy a verticle async without waiting for it to complete or tracking it in any way

### Parameters
`verticleClass` - reference to Verticle class (Java `Class&lt;*&gt;`) to deploy


