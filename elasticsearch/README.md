## klutter/elasticsearch

Helper functions for ElasticSearch.

## Kovenant Integration

Actions created for ElasticSearch normally executed synchronously with `execute().actionGet()` or async with `excute()`
can be called with extension methods `promise()` and `promiseNothing()` (for void methods) to return a [Kovenant](http://kovenant.komponents.nl)
promise.

## Client Helpers

Methods available to create ES Clients (each returns Kovenant promises):

|Method|Description|
|------|-----------|
|esNodeClient(clusterName,settingsMap)|Create Node client with provided settings|
|esNodeClient(clusterName,initFunc)|Create Node client with builder function on settings object|
|esTransportClient(clusterName,nodes,settingsMap)|Create Transport client for a given node list and provided settings|
|esTransportClient(clusterName,nodes,initFunc)|Create Transport client for a given node list with builder function on settings object|
|esEmbeddedClient(clusterName,baseDir,settingsMap)|Create embedded local ElasticSearch configured to use data, work and log dirs under baseDir|
|esEmbeddedClient(clusterName,baseDir,initFunc)|Create embedded local ElasticSearch configured to use data, work and log dirs under baseDir|

Extension functions on all ES clients:

|Method|Description|
|------|-----------|
|waitForGreenCluster()|Returns promise of waiting for green cluster state|
|waitForYellowCluster()|Returns promise of waiting at least for yellow cluster state (actual state returned)|
|waitForGreenIndex(indices)|Returns promise of waiting for green indices state|
|waitForYellowIndex(indices)|Returns promise of waiting at least for yellow indices state (actual state returned|
|indexExists(indices)|Returns promise Boolean of whether all indices provided exist or not|
|createIndex(index,mappings,shards,replicas,initFunc)|Create an index with provided mappings per type, number of shards and replicas, and with a builder function on the index settings object|
|updateIndexMappings(index,mappings)|Update an index for a list of type mappings|

Note that type mappings for `createIndex` and `updateIndexMappings` are a data class:

```kotlin
public data class IndexTypeMapping(val type: String, val json: XContentBuilder)
```

## Index Mapping And XContent Helpers

XContent when required, can be created using helper functions that start a JSON style builder:

`xsonObject(initFunc)` and `xsonArray(initFunc)` start JSON style builders to create XContent for any purpose.  If you use
`xsonObjectWithFields<enumType>(initFunc)` your builder can use the Enum as the field name for more "name safety".

For creating mappings, it is easier to start with `mappingsForType(typeName,allowDynamic,topLevelInitFunc,propertiesInitFunc)` or
the similar `mappingsForTypeWithEnum<enumType>(...)` which adds extra builder methods such as:

* `stringField(fieldName, indexed, stored, initFunc)`
* `dateField(fieldName, indexed, stored, initFunc)`
* `integerField(fieldName, indexed, stored, initFunc)`
* `longField(fieldName, indexed, stored, initFunc)`
* `booleanField(fieldName, indexed, stored, initFunc)`
* `ignoreField(fieldName)`

Where the initFunc allows extra properties to be set on the XContent such as custom analyzers and more.  `indexed` and `stored` are Enums.

## Other Helpers

When indexing, you can use the `IndexRequestBuilder.setSourceFromObject(pojo)` helper to databind using Jackson your object into JSON source.

And for search results, `SearchResponse.getHitsAsObjects(): Sequence<T>` and same extension on `SearchHits` returns databound objects from the JSON results.