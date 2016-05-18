package uy.klutter.vertx

import io.vertx.core.shareddata.AsyncMap
import io.vertx.core.shareddata.Counter
import io.vertx.core.shareddata.Lock
import io.vertx.core.shareddata.SharedData
import nl.komponents.kovenant.Promise


/**
 * Retrieve a vert.x cluster wide map (see Vert.x SharedData class for more documentation)
 *
 * @param name of the map
 * @return Promise<AsyncMap<K, V>, Exception>
 */
fun <K : Any, V : Any> SharedData.promiseClusterWideMap(name: String): Promise<AsyncMap<K, V>, Exception> {
    return withDeferred { getClusterWideMap(name, promiseResult(it)) }
}

/**
 * Retrieve a vert.x cluster wide map (see Vert.x SharedData class for more documentation)
 * This alias for [SharedData.promiseClusterWideMap(name)][SharedData.promiseClusterWideMap] and might be harder
 * to locate due to code completion favoring the built-in method of the same name.
 *
 * @param name of the map
 * @return Promise<AsyncMap<K, V>, Exception>
 */
fun <K : Any, V : Any> SharedData.getClusterWideMap(name: String): Promise<AsyncMap<K, V>, Exception> {
    return promiseClusterWideMap(name)
}

/**
 * Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)
 *
 * @param name of the lock
 * @return Promise<Lock, Exception>
 */
fun SharedData.promiseLock(name: String): Promise<Lock, Exception> {
    return withDeferred { getLock(name, promiseResult(it)) }
}

/**
 * Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)
 * This alias for [SharedData.promiseLock(name)][SharedData.promiseLock] and might be harder
 * to locate due to code completion favoring the built-in method of the same name.
 *
 * @param name of the lock
 * @return Promise<Lock, Exception>
 */
fun SharedData.getLock(name: String): Promise<Lock, Exception> {
    return promiseLock(name)
}

/**
 * Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)
 *
 * @param name of the lock
 * @param timeout length of time to wait in milliseconds for the lock
 * @return Promise<Lock, Exception>
 */
fun SharedData.promiseLockWithTimeout(name: String, timeout: Long): Promise<Lock, Exception> {
    return withDeferred { getLockWithTimeout(name, timeout, promiseResult(it)) }
}

/**
 * Retrieve a vert.x cluster wide lock (see Vert.x SharedData class for more documentation)
 * This alias for [SharedData.promiseLockWithTimeout(name, timeout)][SharedData.promiseLockWithTimeout] and might be harder
 * to locate due to code completion favoring the built-in method of the same name.
 *
 * @param name of the lock
 * @param timeout length of time to wait in milliseconds for the lock
 * @return Promise<Lock, Exception>
 */
fun SharedData.getLockWithTimeout(name: String, timeout: Long): Promise<Lock, Exception> {
    return promiseLockWithTimeout(name, timeout)
}

/**
 * Retrieve a vert.x cluster wide counter (see Vert.x SharedData class for more documentation)
 *
 * @param name of the counter
 * @return Promise<Counter, Exception>
 */
fun SharedData.promiseCounter(name: String): Promise<Counter, Exception> {
    return withDeferred { getCounter(name, promiseResult(it)) }
}

/**
 * Retrieve a vert.x cluster wide counter (see Vert.x SharedData class for more documentation)
 * This alias for [SharedData.promiseCounter(name)][SharedData.promiseCounter] and might be harder
 * to locate due to code completion favoring the built-in method of the same name.
 *
 * @param name of the counter
 * @return Promise<Counter, Exception>
 */
fun SharedData.getCounter(name: String): Promise<Counter, Exception> {
    return promiseCounter(name)
}

/**
 * Get a value from the async map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.promiseGet(key: K): Promise<V, Exception> {
    return withDeferred { get(key, promiseResult(it)) }
}

/**
 * Get a value from the async map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.get(key: K): Promise<V, Exception> {
    return promiseGet(key)
}

/**
 * Put a value into the async map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promisePut(key: K, value: V): Promise<Unit, Exception> {
    return withDeferred { put(key, value, promiseVoidResult(it)) }
}

/**
 * Put a value into the async map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.put(key: K, value: V): Promise<Unit, Exception> {
    return promisePut(key, value)
}

/**
 * Put a value into the async map with TTL value (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promisePut(key: K, value: V, ttl: Long): Promise<Unit, Exception> {
    return withDeferred { put(key, value, ttl, promiseVoidResult(it)) }
}

/**
 * Put a value into the async map with TTL value (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.put(key: K, value: V, ttl: Long): Promise<Unit, Exception> {
    return promisePut(key, value, ttl)
}

/**
 * Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V: Any?> AsyncMap<K, V>.promisePutIfAbsent(key: K, value: V): Promise<V, Exception> {
    return withDeferred { putIfAbsent(key, value, promiseResult(it)) }
}

/**
 * Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.putIfAbsent(key: K, value: V): Promise<V, Exception> {
    return promisePutIfAbsent(key, value)
}

/**
 * Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.promisePutIfAbsent(key: K, value: V, ttl: Long): Promise<V, Exception> {
    return withDeferred { putIfAbsent(key, value, ttl, promiseResult(it)) }
}

/**
 * Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.putIfAbsent(key: K, value: V, ttl: Long): Promise<V, Exception> {
    return promisePutIfAbsent(key, value, ttl)
}

/**
 * Remove a value from the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.promiseRemove(key: K): Promise<V, Exception> {
    return withDeferred { remove(key, promiseResult(it)) }
}

/**
 * Remove a value from the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.remove(key: K): Promise<V, Exception> {
    return promiseRemove(key)
}

/**
 * Remove a value from the map if value is present (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promiseRemoveIfPresent(key: K, value: V): Promise<Boolean, Exception> {
    return withDeferred { removeIfPresent(key, value, promiseResult(it)) }
}

/**
 * Remove a value from the map if value is present (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.removeIfPresent(key: K, value: V): Promise<Boolean, Exception> {
    return promiseRemoveIfPresent(key, value)
}

/**
 * Replace a value in the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.promiseReplace(key: K, value: V): Promise<V, Exception> {
    return withDeferred { replace(key, value, promiseResult(it)) }
}

/**
 * Replace a value in the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any?> AsyncMap<K, V>.replace(key: K, value: V): Promise<V, Exception> {
    return promiseReplace(key, value)
}

/**
 * Replace a value in the map if old value is present (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promiseReplaceIfPresent(key: K, oldValue: V, newValue: V): Promise<Boolean, Exception> {
    return withDeferred { replaceIfPresent(key, oldValue, newValue, promiseResult(it)) }
}

/**
 * Replace a value in the map if old value is present (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.replaceIfPresent(key: K, oldValue: V, newValue: V): Promise<Boolean, Exception> {
    return promiseReplaceIfPresent(key, oldValue, newValue)
}

/**
 * Clear the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promiseClear(): Promise<Unit, Exception> {
    return withDeferred { clear(promiseVoidResult(it)) }
}

/**
 * Clear the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.clear(): Promise<Unit, Exception> {
    return promiseClear()
}

/**
 * Get the size of the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.promiseSize(): Promise<Int, Exception> {
    return withDeferred { size(promiseResult(it)) }
}

/**
 * Get the size of the map (see Vert.x AsncMap class for more full documentation)
 */
fun <K : Any, V : Any> AsyncMap<K, V>.size(): Promise<Int, Exception> {
    return promiseSize()
}