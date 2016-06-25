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
| [jsonArray](json-array.md) | `fun jsonArray(init:&nbsp;JsonArray.()&nbsp;->&nbsp;Unit): JsonArray`<p>A builder function to start creating a JsonArray</p> |
| [jsonArrayFromList](json-array-from-list.md) | `fun <T> jsonArrayFromList(list:&nbsp;List<T>): JsonArray`<p>Converts a List into a JsonArray</p> |
| [jsonArrayFromString](json-array-from-string.md) | `fun jsonArrayFromString(json:&nbsp;String): JsonArray`<p>converts a JSON formatted string containing an array into an JsonArray</p> |
| [jsonObject](json-object.md) | `fun jsonObject(init:&nbsp;JsonObject.()&nbsp;->&nbsp;Unit): JsonObject`<p>A builder function to start creating a JsonObject</p> |
| [jsonObjectFromMap](json-object-from-map.md) | `fun <V> jsonObjectFromMap(map:&nbsp;Map<String,&nbsp;V>): JsonObject`<p>Converts a Map&lt;String, T&gt; into a JsonObject</p> |
| [jsonObjectFromPojo](json-object-from-pojo.md) | `fun jsonObjectFromPojo(something:&nbsp;Any): JsonObject`<p>Converts a POJO into a JsonObject</p> |
| [jsonObjectFromString](json-object-from-string.md) | `fun jsonObjectFromString(json:&nbsp;String): JsonObject`<p>converts a JSON formatted string containing an object into a tree of JsonObject</p> |
