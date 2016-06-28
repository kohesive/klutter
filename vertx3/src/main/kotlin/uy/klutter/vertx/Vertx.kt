package uy.klutter.vertx

import io.vertx.core.*
import io.vertx.core.eventbus.Message
import nl.komponents.kovenant.Deferred
import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred
import kotlin.reflect.KClass


/*
 *  VertxInit.ensure() is called from methods that startup vertx to be sure Kovenant and Vertx are configured to work
 *  together.  If you do not startup vertx using these methods, you should directly call VertxInit.ensure() at the start
 *  of your application.
 */

class WrappedThrowableException(cause: Throwable) : Exception(cause.message, cause)

/**
 * Start vert.x
 *
 * @return Promise<Vertx, Exception>
 */
fun vertx(): Promise<Vertx, Exception> {
    VertxInit.ensure()

    val deferred = deferred<Vertx, Exception>()
    try {
        deferred.resolve(Vertx.vertx())
    } catch (ex: Exception) {
        deferred.reject(ex)
    } catch (ex: Throwable) {
        deferred.reject(WrappedThrowableException(ex))
    }
    return deferred.promise
}

/**
 * Start vert.x (alias for [`vertx()`][vertx] function)
 *
 * @return Promise<Vertx, Exception>
 */
fun promiseVertx(): Promise<Vertx, Exception> = vertx()

/**
 * Start vert.x
 *
 * @param options configuration options for vert.x
 * @return Promise<Vertx, Exception>
 */
fun vertx(options: VertxOptions): Promise<Vertx, Exception> {
    VertxInit.ensure()

    val deferred = deferred<Vertx, Exception>()
    try {
        deferred.resolve(Vertx.vertx(options))
    } catch (ex: Exception) {
        deferred.reject(ex)
    } catch (ex: Throwable) {
        deferred.reject(WrappedThrowableException(ex))
    }
    return deferred.promise
}

/**
 * Start vert.x (alias for [`vertx(options)`][vertx] function)
 *
 * @param options configuration options for vert.x
 * @return Promise<Vertx, Exception>
 */
fun promiseVertx(options: VertxOptions): Promise<Vertx, Exception> = vertx(options)

/**
 * Start clustered vert.x
 *
 * @param options configuration options for vert.x
 * @return Promise<Vertx, Exception>
 */
fun vertxCluster(options: VertxOptions): Promise<Vertx, Exception> {
    return withDeferred { Vertx.clusteredVertx(options, promiseResult(it)) }
}

/**
 * Start clustered vert.x (alias for [`vertxCluster(options)`][vertxCluster] function)
 *
 * @param options configuration options for vert.x
 * @return Promise<Vertx, Exception>
 */
fun promiseVertxCluster(options: VertxOptions): Promise<Vertx, Exception> = vertxCluster(options)

/**
 * retrieve the current vert.x context if one is attached to the current thread
 */
fun vertxContext(): Context? = Vertx.currentContext()

/**
 * Deploy a verticle
 *
 * @param verticle to deploy
 * @return Promise<deploymentId as String, Exception>
 */
fun Vertx.promiseDeployVerticle(verticle: Verticle): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticle, promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param verticle to deploy
 * @param options deployment options
 * @return Promise<deploymentId as String, Exception>
 */
fun Vertx.promiseDeployVerticle(verticle: Verticle, options: DeploymentOptions): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticle, options, promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param verticleClass reference to Verticle class (Kotlin `KClass`) to deploy
 * @return Promise<deploymentId as String, Exception>
 */
fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: KClass<T>): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticleClass.java.getName(), promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param verticleClass reference to Verticle class (Kotlin `KClass`) to deploy
 * @param options deployment options
 * @return Promise<deploymentId as String, Exception>
 */
fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: KClass<T>, options: DeploymentOptions): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticleClass.java.getName(), options, promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param verticleClass reference to Verticle class (Java `Class<*>`) to deploy
 * @return Promise<deploymentId as String, Exception>
 */
fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: Class<T>): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticleClass.getName(), promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param verticleClass reference to Verticle class (Java `Class<*>`) to deploy
 * @param options deployment options
 * @return Promise<deploymentId as String, Exception>
 */
fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: Class<T>, options: DeploymentOptions): Promise<String, Exception> {
    return withDeferred { deployVerticle(verticleClass.getName(), options, promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param name verticle by identifier
 * @return Promise<deploymentId as String, Exception>
 */
fun Vertx.promiseDeployVerticle(name: String): Promise<String, Exception> {
    return withDeferred { deployVerticle(name, promiseResult(it)) }
}

/**
 * Deploy a verticle
 *
 * @param name verticle by identifier
 * @param options deployment options
 * @return Promise<deploymentId as String, Exception>
 */
fun Vertx.promiseDeployVerticle(name: String, options: DeploymentOptions): Promise<String, Exception> {
    return withDeferred { deployVerticle(name, options, promiseResult(it)) }
}

/**
 * Deploy a verticle async without waiting for it to complete or tracking it in any way
 *
 * @param verticleClass reference to Verticle class (Kotlin `KClass`) to deploy
 */
fun <T : AbstractVerticle> Vertx.deployVerticle(verticleClass: KClass<T>): Unit {
    VertxInit.ensure()
    deployVerticle(verticleClass.java.getName())
}

/**
 * Deploy a verticle async without waiting for it to complete or tracking it in any way
 *
 * @param verticleClass reference to Verticle class (Java `Class<*>`) to deploy
 */
fun <T : AbstractVerticle> Vertx.deployVerticle(verticleClass: Class<T>): Unit {
    VertxInit.ensure()
    deployVerticle(verticleClass.getName())
}

/**
 * Undeploy a verticle
 *
 * @return Promise<Unit, Exception> an empty promise that is successful when undeploy completes
 */
fun Vertx.promiseUndeploy(deploymentId: String): Promise<Unit, Exception> {
    return withDeferred { this.undeploy(deploymentId, promiseVoidResult(it)) }
}

/**
 * Close vert.x
 *
 * @return Promise<Unit, Exception> an empty promise that is successful when closing completes
 */
fun Vertx.promiseClose(): Promise<Unit, Exception> {
    return withDeferred { this.close(promiseVoidResult(it)) }
}

/**
 * Execute blocking code using vert.x dispatcher returning a `Promise<T, Exception>`.  Since Kovenant and
 * vert.x dispatching are united, this is the same as doing `task { ... }` in Kovenant except that no
 * vert.x context will be in thread local storage if you do not use this method.
 *
 * @param blockingCode code to execute
 * @return Promise<T, Exception> where T is the return type of blockingCode
 */
fun <T : Any> Vertx.promiseExecuteBlocking(blockingCode: () -> T): Promise<T, Exception> {
    return withDeferred { deferred ->
        this.executeBlocking({ response ->
            try {
                response.complete(blockingCode())
            } catch (ex: Throwable) {
                response.fail(ex)
            }
        }, promiseResult(deferred))
    }
}

/**
 * Execute blocking code using vert.x dispatcher returning a `Promise<T, Exception>`.  Since Kovenant and
 * vert.x dispatching are united, this is the same as doing `task { ... }` in Kovenant except that no
 * vert.x context will be in thread local storage if you do not use this method.
 *
 * @param blockingCode code to execute
 * @return Promise<T, Exception> where T is the return type of blockingCode
 */
fun <T : Any> Vertx.executeBlocking(blockingCode: () -> T): Promise<T, Exception> {
    return withDeferred { deferred ->
        this.executeBlocking({ response ->
            try {
                response.complete(blockingCode())
            } catch (ex: Throwable) {
                response.fail(ex)
            }
        }, promiseResult(deferred))
    }
}

/**
 * Sends the message and returns a promise of a reply to that message.
 *
 * @param address Message-bus address to send to
 * @param message The message to send on the message-bus
 * @return Promise<T, Exception>
 */
fun <T : Any> Vertx.promiseReply(address: String, message: Any): Promise<T, Exception> {
    return withDeferred { deferred ->
        eventBus().send(address, message, Handler { ar: AsyncResult<Message<T>> ->
            if (ar.succeeded()) {
                deferred.resolve(ar.result().body())
            } else {
                deferred.reject(Exception(ar.cause()))
            }
        })
    }
}

/**
 * Setup Kovenant do dispatch via Vert.x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types.
 *
 * All promise verison of startup methods ensure this object is initialized before continuing.  If you are starting Vert.x
 * in some other way, you should call [`VertxInit.ensure()`][VertxInit.ensure] before doing anything with Vert.x + Kovenant
 * together.  Calling the method more than once does nothing, and is not harmful (it only causes the class to load if not loaded).
 */
object VertxInit {
    val fallbackContext = Kovenant.createContext {
        workerContext.dispatcher {
            name = "worker-fallback"
            concurrentTasks = Runtime.getRuntime().availableProcessors()
            pollStrategy {
                //Some intermediate strategies
                yielding(numberOfPolls = 1000)

                //Make sure to block to keep the threads alive
                blocking()
            }
        }


        callbackContext.dispatcher {
            name = "callback-fallback" //that has a nice ring to it too
            concurrentTasks = 1
            pollStrategy {
                //Again, make sure to block to keep the threads alive
                blocking()
            }
        }
    }

    init {
        Kovenant.context = VertxKovenantContext(fallbackContext)
        setupVertxJsonForKotlin()
    }

    /**
     * Be sure that Kovenant and Vert.x are initialized in a way that they cooperate and work together.
     */
    @Suppress("NOTHING_TO_INLINE")
    inline fun ensure() {
        // TODO: here to be sure we have intiailized anything related before using,
        //       although this function may remain empty it causes initializers on the
        //       object to run.
    }
}

/**
 * Helper to convert an expectation of `AsyncResult<T>` into a promise represented by `Deferred<T, Exception>`
 *
 *     for example:
 *
 *       ```
 *       public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
 *           val deferred = deferred<SomeType, Exception>()
 *           vertx.someAsyncAction( promiseResult(deferred) )
 *           return deferred.promise
 *       }
 *       ```
 *
 *     or a shorter version:
 *
 *       ```
 *       public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
 *           return withDeferred { vertx.someAsyncAction( promiseResult(it) ) }
 *       }
 *       ```
 */
fun <T : Any?> promiseResult(deferred: Deferred<T, Exception>): (AsyncResult<T>) -> Unit {
    VertxInit.ensure()

    return { completion ->
        if (completion.succeeded()) {
            deferred.resolve(completion.result())
        } else {
            if (completion.cause() is Exception) {
                deferred.reject(completion.cause() as Exception)
            } else {
                deferred.reject(WrappedThrowableException(completion.cause()))
            }
        }
    }
}

/**
 * Same as [promiseResult] but to be used when the `AsyncResult` type is `Void`
 */
fun promiseVoidResult(deferred: Deferred<Unit, Exception>): (AsyncResult<Void>) -> Unit {
    VertxInit.ensure()

    return { completion ->
        if (completion.succeeded()) {
            deferred.resolve(Unit)
        } else {
            if (completion.cause() is Exception) {
                deferred.reject(completion.cause() as Exception)
            } else {
                deferred.reject(WrappedThrowableException(completion.cause()))
            }
        }
    }
}

/**
 * Helper function that creates a deferred for a block of code and returns the promise associated with the deferred
 *
 *     for example:
 *
 *       ```
 *       public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
 *           return withDeferred { vertx.someAsyncAction( promiseResult(it) ) }
 *       }
 *       ```
 *
 * @param codeBlock the block of code that is executed and will resolve or reject the `deferred`
 * @return Promise<T, Exception> where `T` is the return type of the codeBlock
 */
fun <T : Any?> withDeferred(codeBlock: (deferred: Deferred<T, Exception>) -> Unit): Promise<T, Exception> {
    VertxInit.ensure()

    val deferred = deferred<T, Exception>()
    codeBlock(deferred)
    return deferred.promise
}

