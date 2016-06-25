[uy.klutter.vertx.json](.)


## Package uy.klutter.vertx.json

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [io.vertx.core.json.JsonArray](io.vertx.core.json.-json-array/index.md) |  |
| [io.vertx.core.json.JsonObject](io.vertx.core.json.-json-object/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [jsonArray](json-array.md) | <code>fun jsonArray(init: JsonArray.() -> Unit): JsonArray</code><br/>A builder function to start creating a JsonArray |
| [jsonArrayFromList](json-array-from-list.md) | <code>fun <T> jsonArrayFromList(list: List<T>): JsonArray</code><br/>Converts a List into a JsonArray |
| [jsonArrayFromString](json-array-from-string.md) | <code>fun jsonArrayFromString(json: String): JsonArray</code><br/>converts a JSON formatted string containing an array into an JsonArray |
| [jsonObject](json-object.md) | <code>fun jsonObject(init: JsonObject.() -> Unit): JsonObject</code><br/>A builder function to start creating a JsonObject |
| [jsonObjectFromMap](json-object-from-map.md) | <code>fun <V> jsonObjectFromMap(map: Map<String, V>): JsonObject</code><br/>Converts a Map&lt;String, T&gt; into a JsonObject |
| [jsonObjectFromPojo](json-object-from-pojo.md) | <code>fun jsonObjectFromPojo(something: Any): JsonObject</code><br/>Converts a POJO into a JsonObject |
| [jsonObjectFromString](json-object-from-string.md) | <code>fun jsonObjectFromString(json: String): JsonObject</code><br/>converts a JSON formatted string containing an object into a tree of JsonObject |
