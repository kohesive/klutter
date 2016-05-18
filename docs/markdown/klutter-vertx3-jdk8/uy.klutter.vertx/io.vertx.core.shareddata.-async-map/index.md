[uy.klutter.vertx](../index.md) / [io.vertx.core.shareddata.AsyncMap](.)


### Extensions for io.vertx.core.shareddata.AsyncMap


| [clear](clear.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.clear(): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Clear the map (see Vert.x AsncMap class for more full documentation)

 |
| [get](get.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.get(key:&nbsp;K): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Get a value from the async map (see Vert.x AsncMap class for more full documentation)

 |
| [promiseClear](promise-clear.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseClear(): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Clear the map (see Vert.x AsncMap class for more full documentation)

 |
| [promiseGet](promise-get.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseGet(key:&nbsp;K): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Get a value from the async map (see Vert.x AsncMap class for more full documentation)

 |
| [promisePut](promise-put.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promisePut(key:&nbsp;K, value:&nbsp;V): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map (see Vert.x AsncMap class for more full documentation)

`fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promisePut(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map with TTL value (see Vert.x AsncMap class for more full documentation)

 |
| [promisePutIfAbsent](promise-put-if-absent.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.promisePutIfAbsent(key:&nbsp;K, value:&nbsp;V): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)

`fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.promisePutIfAbsent(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)

 |
| [promiseRemove](promise-remove.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseRemove(key:&nbsp;K): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Remove a value from the map (see Vert.x AsncMap class for more full documentation)

 |
| [promiseRemoveIfPresent](promise-remove-if-present.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseRemoveIfPresent(key:&nbsp;K, value:&nbsp;V): Promise&lt;Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Remove a value from the map if value is present (see Vert.x AsncMap class for more full documentation)

 |
| [promiseReplace](promise-replace.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseReplace(key:&nbsp;K, value:&nbsp;V): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Replace a value in the map (see Vert.x AsncMap class for more full documentation)

 |
| [promiseReplaceIfPresent](promise-replace-if-present.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseReplaceIfPresent(key:&nbsp;K, oldValue:&nbsp;V, newValue:&nbsp;V): Promise&lt;Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Replace a value in the map if old value is present (see Vert.x AsncMap class for more full documentation)

 |
| [promiseSize](promise-size.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.promiseSize(): Promise&lt;Int,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Get the size of the map (see Vert.x AsncMap class for more full documentation)

 |
| [put](put.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.put(key:&nbsp;K, value:&nbsp;V): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map (see Vert.x AsncMap class for more full documentation)

`fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.put(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map with TTL value (see Vert.x AsncMap class for more full documentation)

 |
| [putIfAbsent](put-if-absent.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.putIfAbsent(key:&nbsp;K, value:&nbsp;V): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map if absent (see Vert.x AsncMap class for more full documentation)

`fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.putIfAbsent(key:&nbsp;K, value:&nbsp;V, ttl:&nbsp;Long): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Put a value into the async map if absent with TTL value (see Vert.x AsncMap class for more full documentation)

 |
| [remove](remove.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.remove(key:&nbsp;K): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Remove a value from the map (see Vert.x AsncMap class for more full documentation)

 |
| [removeIfPresent](remove-if-present.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.removeIfPresent(key:&nbsp;K, value:&nbsp;V): Promise&lt;Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Remove a value from the map if value is present (see Vert.x AsncMap class for more full documentation)

 |
| [replace](replace.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&gt; AsyncMap&lt;K,&nbsp;V&gt;.replace(key:&nbsp;K, value:&nbsp;V): Promise&lt;V,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Replace a value in the map (see Vert.x AsncMap class for more full documentation)

 |
| [replaceIfPresent](replace-if-present.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.replaceIfPresent(key:&nbsp;K, oldValue:&nbsp;V, newValue:&nbsp;V): Promise&lt;Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Replace a value in the map if old value is present (see Vert.x AsncMap class for more full documentation)

 |
| [size](size.md) | `fun &lt;K&nbsp;:&nbsp;Any, V&nbsp;:&nbsp;Any&gt; AsyncMap&lt;K,&nbsp;V&gt;.size(): Promise&lt;Int,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;`
Get the size of the map (see Vert.x AsncMap class for more full documentation)

 |

