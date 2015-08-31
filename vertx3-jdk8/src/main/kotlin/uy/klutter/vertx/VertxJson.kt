package uy.klutter.vertx

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import uy.klutter.core.jdk8.toIsoString
import java.time.temporal.Temporal


@suppress("NOTHING_TO_INLINE")
public inline fun jsonObjectFromString(json: String): JsonObject {
    return JsonObject(json)
}

@suppress("NOTHING_TO_INLINE")
public inline fun jsonArrayFromString(json: String): JsonArray {
    return JsonArray(json)
}

@suppress("NOTHING_TO_INLINE")
public inline fun <V> jsonObjectFromMap(map: Map<String, V>): JsonObject {
    return JsonObject(map)
}

@suppress("NOTHING_TO_INLINE")
public inline fun <T> jsonArrayFromList(list: List<T>): JsonArray {
    return JsonArray(list)
}

@suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN")
public inline fun jsonObjectFromPojo(something: Any): JsonObject {
    return jsonObjectFromMap<Any?>(Json.mapper.convertValue(something, javaClass<java.util.Map<*,*>>()) as Map<String, Any?>)
}

public inline fun jsonObject(init: JsonObject.() -> Unit): JsonObject {
    val jsonObject = JsonObject()
    jsonObject.init()
    return jsonObject
}

public inline fun jsonArray(init: JsonArray.() -> Unit): JsonArray {
    val jsonArray = JsonArray()
    jsonArray.init()
    return jsonArray
}

public inline fun JsonObject.putObject(name: String, init: JsonObject.() -> Unit): JsonObject {
    put(name, jsonObject(init))
    return this
}

public inline fun JsonObject.putArray(name: String, init: JsonArray.() -> Unit): JsonObject {
    put(name, jsonArray(init))
    return this
}

public inline fun JsonArray.addObject(init: JsonObject.() -> Unit): JsonArray {
    add(jsonObject(init))
    return this
}

public inline fun JsonArray.addArray(init: JsonArray.() -> Unit): JsonArray {
    add(jsonArray(init))
    return this
}

@suppress("NOTHING_TO_INLINE")
public inline fun JsonObject.putDateIsoString(name: String, value: Temporal): JsonObject = put(name, value.toIsoString())

@suppress("NOTHING_TO_INLINE")
public inline fun JsonArray.addDateIsoString(value: Temporal): JsonArray = add(value.toIsoString())


