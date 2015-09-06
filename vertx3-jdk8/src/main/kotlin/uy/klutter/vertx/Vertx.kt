package uy.klutter.vertx

import io.vertx.core.*
import nl.komponents.kovenant.Deferred
import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.temporal.Temporal
import kotlin.reflect.KClass
import kotlin.reflect.jvm.java


/**
 *  VertxInit.ensure() is called from methods that startup vertx to be sure Kovenant and Vertx are configured to work
 *  together.  If you do not startup vertx using these methods, you should directly call VertxInit.ensure() at the start
 *  of your application.  Not all vertx+Kovenant methods call VertxInit.ensure, although those that will not affect performance
 *  might.
 */

public class WrappedThrowableException(cause: Throwable): Exception(cause.getMessage(), cause)

/**
 * Start vert.x returning a Kovenant Promise<Vertx, Exception>
 */
public fun vertx(): Promise<Vertx, Exception> {
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
 * Start vert.x returning a Kovenant Promise<Vertx, Exception>
 */
public fun vertx(options: VertxOptions): Promise<Vertx, Exception> {
    VertxInit.ensure()

    val deferred = deferred<Vertx, Exception>()
    try {
        deferred.resolve(Vertx.vertx(options))
    } catch (ex: Exception) {
        deferred.reject(ex)
    }  catch (ex: Throwable) {
        deferred.reject(WrappedThrowableException(ex))
    }
    return deferred.promise
}

/**
 * Start clustered vert.x returning a Kovenant Promise<Vertx, Exception>
 */
public fun vertxCluster(options: VertxOptions): Promise<Vertx, Exception> {
    VertxInit.ensure()

    val deferred = deferred<Vertx, Exception>()
    Vertx.clusteredVertx(options, promiseResult(deferred))
    return deferred.promise
}

/**
 * retrieve the current vert.x context if one is attached to the current thread
 */
public fun vertxContext(): Context? = Vertx.currentContext()

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun Vertx.promiseDeployVerticle(verticle: Verticle): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticle, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun Vertx.promiseDeployVerticle(verticle: Verticle, options: DeploymentOptions): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticle, options, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: KClass<T>): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticleClass.java.getName(), promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: KClass<T>, options: DeploymentOptions): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticleClass.java.getName(), options, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: Class<T>): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticleClass.getName(), promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun  <T : AbstractVerticle> Vertx.promiseDeployVerticle(verticleClass: Class<T>, options: DeploymentOptions): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(verticleClass.getName(), options, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun Vertx.promiseDeployVerticle(name: String): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(name, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle, returning a Promise<deploymentId as String, Exception>
 */
public fun Vertx.promiseDeployVerticle(name: String, options: DeploymentOptions): Promise<String, Exception> {
    VertxInit.ensure()

    val deferred = deferred<String, Exception>()
    deployVerticle(name, options, promiseResult(deferred))
    return deferred.promise
}

/**
 * Deploy a verticle async without waiting for it to complete
 */
public fun <T : AbstractVerticle> Vertx.deployVerticle(verticleClass: KClass<T>): Unit {
    deployVerticle(verticleClass.java.getName())
}

/**
 * Deploy a verticle async without waiting for it to complete
 */
public fun <T : AbstractVerticle> Vertx.deployVerticle(verticleClass: Class<T>): Unit {
    deployVerticle(verticleClass.getName())
}

public class NADA {} // we can't deal with Void return type Vert.x likes to define but really means NULL but isn't nullable
val nada = NADA()

/**
 * Undeploy a verticle, returning a Promise<NADA, Exception>
 */
public fun Vertx.promiseUndeploy(deploymentId: String): Promise<NADA, Exception> {
    VertxInit.ensure()

    val deferred = deferred<NADA, Exception>()
    this.undeploy(deploymentId, { completion ->
        if (completion.succeeded()) {
            deferred.resolve(nada)
        } else {
            if (completion.cause() is Exception) {
                deferred.reject(completion.cause() as Exception)
            } else {
                deferred.reject(WrappedThrowableException(completion.cause()))
            }
        }
    })
    return deferred.promise
}

/**
 * Close vertx, returning a Promise<Void, Exception>
 */
public fun Vertx.promiseClose(): Promise<NADA, Exception> {
    val deferred = deferred<NADA, Exception>()
    this.close({ completion ->
        if (completion.succeeded()) {
            deferred.resolve(nada)
        } else {
            if (completion.cause() is Exception) {
                deferred.reject(completion.cause() as Exception)
            } else {
                deferred.reject(WrappedThrowableException(completion.cause()))
            }
        }
    })
    return deferred.promise
}

/**
 * Execute blocking code using vertx dispatcher returning a Promise<T, Exception>.  Since Kovenant and
 * vertx dispatching are united, this is the same as doing async { ... } in Kovenant, no need to call on a
 * vertx instance.
 */
public fun <T> Vertx.promiseExecuteBlocking(blockingCode: () -> T): Promise<T, Exception> {
    VertxInit.ensure()

    val deferred = deferred<T, Exception>()
    this.executeBlocking({ response ->
        try {
            response.complete(blockingCode())
        } catch (ex: Throwable) {
            response.fail(ex)
        }
    }, promiseResult(deferred))
    return deferred.promise
}

/**
 * Execute blocking code using vertx dispatcher returning a Promise<T, Exception>.  Since Kovenant and
 * vertx dispatching are united, this is the same as doing async { ... } in Kovenant, no need to call on a
 * vertx instance.
 */
public fun <T> Vertx.executeBlocking(blockingCode: () -> T): Promise<T, Exception> {
    VertxInit.ensure()

    val deferred = deferred<T, Exception>()
    this.executeBlocking({ response ->
        try {
            response.complete(blockingCode())
        } catch (ex: Throwable) {
            response.fail(ex)
        }
    }, promiseResult(deferred))
    return deferred.promise
}


/**
 * Setup Kovenant do dispatch via Vert-x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types
 */

private object VertxInit {
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

    @suppress("NOTHING_TO_INLINE")
    public inline fun ensure() {
        // TODO: here to be sure we have intiailized anything related before using,
        //       although this function may remain empty it causes initializers on the
        //       object to run.
    }
}

/**
 * Helper to convert an expectation of AsyncResult<T> into a promise represented by Deferred<T, Throwable>
 *
 *     i.e.
 *       public fun someAsynActionAsPromise(): Promise<SomeType, Exception> {
 *           val deferred = deferred<SomeType, Exception>()
 *           vertx.someAsyncAction( promiseResult(deferred) )
 *           return deferred.promise
 *       }
 */
public fun <T> promiseResult(deferred: Deferred<T, Exception>): (AsyncResult<T>) -> Unit {
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


