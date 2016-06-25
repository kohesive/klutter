[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [promiseDeployVerticle](.)


# promiseDeployVerticle
`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L103)

Deploy a verticle

### Parameters
`verticle` - to deploy

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L114)

Deploy a verticle

### Parameters
`verticle` - to deploy

`options` - deployment options

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L124)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Kotlin `KClass`) to deploy

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L135)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Kotlin `KClass`) to deploy

`options` - deployment options

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L145)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Java `Class&lt;*&gt;`) to deploy

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L156)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Java `Class&lt;*&gt;`) to deploy

`options` - deployment options

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun Vertx.promiseDeployVerticle(name:&nbsp;String): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L166)

Deploy a verticle

### Parameters
`name` - verticle by identifier

**Return**
Promise&lt;deploymentId as String, Exception&gt;


`fun Vertx.promiseDeployVerticle(name:&nbsp;String, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L177)

Deploy a verticle

### Parameters
`name` - verticle by identifier

`options` - deployment options

**Return**
Promise&lt;deploymentId as String, Exception&gt;


