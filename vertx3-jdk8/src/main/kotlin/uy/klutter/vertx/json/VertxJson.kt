@file:Suppress("NOTHING_TO_INLINE")

package uy.klutter.vertx.json

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import uy.klutter.core.jdk8.toIsoString
import java.time.temporal.Temporal

/**
 * converts a JSON formatted string containing an object into a tree of JsonObject
 */
inline fun jsonObjectFromString(json: String): JsonObject {
    return JsonObject(json)
}

/**
 * converts a JSON formatted string containing an array into an JsonArray
 */
inline fun jsonArrayFromString(json: String): JsonArray {
    return JsonArray(json)
}

/**
 * Converts a Map<String, T> into a JsonObject
 */
inline fun <V> jsonObjectFromMap(map: Map<String, V>): JsonObject {
    return JsonObject(map)
}

/**
 * Converts a List<T> into a JsonArray
 */
inline fun <T> jsonArrayFromList(list: List<T>): JsonArray {
    return JsonArray(list)
}

/**
 * Converts a POJO into a JsonObject
 */
@Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
inline fun jsonObjectFromPojo(something: Any): JsonObject {
    return jsonObjectFromMap<Any?>(Json.mapper.convertValue(something, java.util.Map::class.java) as Map<String, Any?>)
}

/**
 * A builder function to start creating a JsonObject
 *
 * ```
 * jsonObject {
 *     put("parm", "value")
 *     put("field2", 123)
 * }
 * ```
 */
inline fun jsonObject(init: JsonObject.() -> Unit): JsonObject {
    val jsonObject = JsonObject()
    jsonObject.init()
    return jsonObject
}

/**
 * A builder function to start creating a JsonArray
 *
 * ```
 * jsonArray {
 *     add("value1")
 *     add("value2")
 * }
 * ```
 */
inline fun jsonArray(init: JsonArray.() -> Unit): JsonArray {
    val jsonArray = JsonArray()
    jsonArray.init()
    return jsonArray
}

/**
 * A builder function to nest JSON objects within other objects
 */
inline fun JsonObject.putObject(name: String, init: JsonObject.() -> Unit): JsonObject {
    put(name, jsonObject(init))
    return this
}

/**
 * A builder function to nest JSON arrays within other objects
 */
inline fun JsonObject.putArray(name: String, init: JsonArray.() -> Unit): JsonObject {
    put(name, jsonArray(init))
    return this
}

/**
 * A builder function to nest JSON objects within an array
 */
inline fun JsonArray.addObject(init: JsonObject.() -> Unit): JsonArray {
    add(jsonObject(init))
    return this
}

/**
 * A builder function to nest JSON arrays within an array
 */
inline fun JsonArray.addArray(init: JsonArray.() -> Unit): JsonArray {
    add(jsonArray(init))
    return this
}

/**
 * Put a date into a JsonObject as an ISO formated string
 */
inline fun JsonObject.putDateIsoString(name: String, value: Temporal): JsonObject = put(name, value.toIsoString())

/**
 * Put a date into a JsonArray as an ISO formated string
 */
inline fun JsonArray.addDateIsoString(value: Temporal): JsonArray = add(value.toIsoString())


