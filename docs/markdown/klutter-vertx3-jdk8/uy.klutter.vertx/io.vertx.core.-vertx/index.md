[uy.klutter.vertx](../index.md) / [io.vertx.core.Vertx](.)


### Extensions for io.vertx.core.Vertx


| [deployVerticle](deploy-verticle.md) | `fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.deployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;): Unit`
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.deployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit`
Deploy a verticle async without waiting for it to complete or tracking it in any way

 |
| [executeBlocking](execute-blocking.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; Vertx.executeBlocking(blockingCode:&nbsp;()&nbsp;-&gt;&nbsp;T): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Execute blocking code using vert.x dispatcher returning a `Promise&lt;T,Exception&gt;`.  Since Kovenant and
vert.x dispatching are united, this is the same as doing `task{...}` in Kovenant except that no
vert.x context will be in thread local storage if you do not use this method.

 |
| [promiseClose](promise-close.md) | `fun Vertx.promiseClose(): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Close vert.x

 |
| [promiseDeployVerticle](promise-deploy-verticle.md) | `fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun Vertx.promiseDeployVerticle(verticle:&nbsp;Verticle, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;KClass&lt;T&gt;, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun &lt;T&nbsp;:&nbsp;AbstractVerticle&gt; Vertx.promiseDeployVerticle(verticleClass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun Vertx.promiseDeployVerticle(name:&nbsp;String): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
`fun Vertx.promiseDeployVerticle(name:&nbsp;String, options:&nbsp;DeploymentOptions): Promise&lt;String,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Deploy a verticle

 |
| [promiseExecuteBlocking](promise-execute-blocking.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; Vertx.promiseExecuteBlocking(blockingCode:&nbsp;()&nbsp;-&gt;&nbsp;T): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Execute blocking code using vert.x dispatcher returning a `Promise&lt;T,Exception&gt;`.  Since Kovenant and
vert.x dispatching are united, this is the same as doing `task{...}` in Kovenant except that no
vert.x context will be in thread local storage if you do not use this method.

 |
| [promiseReply](promise-reply.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; Vertx.promiseReply(address:&nbsp;String, message:&nbsp;Any): Promise&lt;T,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Sends the message and returns a promise of a reply to that message.

 |
| [promiseUndeploy](promise-undeploy.md) | `fun Vertx.promiseUndeploy(deploymentId:&nbsp;String): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Undeploy a verticle

 |

