[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](index.md) / [promiseDeployVerticle](.)


# promiseDeployVerticle
`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L103)

Deploy a verticle

### Parameters
`verticle` - to deploy

**Return**
Promise<deploymentId as String, Exception>


`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L114)

Deploy a verticle

### Parameters
`verticle` - to deploy

`options` - deployment options

**Return**
Promise<deploymentId as String, Exception>


`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass<T>): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L124)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Kotlin `KClass`) to deploy

**Return**
Promise<deploymentId as String, Exception>


`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass<T>, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L135)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Kotlin `KClass`) to deploy

`options` - deployment options

**Return**
Promise<deploymentId as String, Exception>


`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L145)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Java `Class<*>`) to deploy

**Return**
Promise<deploymentId as String, Exception>


`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L156)

Deploy a verticle

### Parameters
`verticleClass` - reference to Verticle class (Java `Class<*>`) to deploy

`options` - deployment options

**Return**
Promise<deploymentId as String, Exception>


`fun Vertx.promiseDeployVerticle(name:&nbsp;String): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L166)

Deploy a verticle

### Parameters
`name` - verticle by identifier

**Return**
Promise<deploymentId as String, Exception>


`fun Vertx.promiseDeployVerticle(name:&nbsp;String, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L177)

Deploy a verticle

### Parameters
`name` - verticle by identifier

`options` - deployment options

**Return**
Promise<deploymentId as String, Exception>


