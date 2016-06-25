[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](.)


### Extensions for io.vertx.core.Vertx

|&nbsp;|&nbsp;|
|---|---|
| [deployVerticle](deploy-verticle.md) | `fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.deployVerticle(verticleClass:&nbsp;KClass<T>): Unit`
`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.deployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit`<p>Deploy a verticle async without waiting for it to complete or tracking it in any way</p> |
| [executeBlocking](execute-blocking.md) | `fun <T&nbsp;:&nbsp;Any> Vertx.executeBlocking(blockingCode:&nbsp;()&nbsp;->&nbsp;T): Promise<T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Execute blocking code using vert.x dispatcher returning a `Promise<T,Exception>`.  Since Kovenant and<br/>vert.x dispatching are united, this is the same as doing `task{...}` in Kovenant except that no<br/>vert.x context will be in thread local storage if you do not use this method.</p> |
| [promiseClose](promise-close.md) | `fun Vertx.promiseClose(): Promise<Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Close vert.x</p> |
| [promiseDeployVerticle](promise-deploy-verticle.md) | `fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass<T>): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass<T>, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun <T&nbsp;:&nbsp;AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun Vertx.promiseDeployVerticle(name:&nbsp;String): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`
`fun Vertx.promiseDeployVerticle(name:&nbsp;String, options:&nbsp;DeploymentOptions): Promise<String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Deploy a verticle</p> |
| [promiseExecuteBlocking](promise-execute-blocking.md) | `fun <T&nbsp;:&nbsp;Any> Vertx.promiseExecuteBlocking(blockingCode:&nbsp;()&nbsp;->&nbsp;T): Promise<T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Execute blocking code using vert.x dispatcher returning a `Promise<T,Exception>`.  Since Kovenant and<br/>vert.x dispatching are united, this is the same as doing `task{...}` in Kovenant except that no<br/>vert.x context will be in thread local storage if you do not use this method.</p> |
| [promiseReply](promise-reply.md) | `fun <T&nbsp;:&nbsp;Any> Vertx.promiseReply(address:&nbsp;String, message:&nbsp;Any): Promise<T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Sends the message and returns a promise of a reply to that message.</p> |
| [promiseUndeploy](promise-undeploy.md) | `fun Vertx.promiseUndeploy(deploymentId:&nbsp;String): Promise<Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>`<p>Undeploy a verticle</p> |
