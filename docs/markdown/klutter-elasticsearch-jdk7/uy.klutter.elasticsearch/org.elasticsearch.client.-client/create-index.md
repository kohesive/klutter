[uy.klutter.elasticsearch](../index.md) / [org.elasticsearch.client.Client](index.md) / [createIndex](.)


# createIndex
<code>fun Client.createIndex(index: String, mappings: List<[IndexTypeMapping](../-index-type-mapping/index.md)>, shardCount: Int = EsConfig.indexShardCount, replicaCount: Int = EsConfig.indexReplicaCount, settingsInit: Builder.() -> Unit = {}): Promise<Unit, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code> [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/Client.kt#L138)<br/>

