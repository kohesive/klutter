[uy.klutter.elasticsearch](../index.md) / [org.elasticsearch.client.Client](.)


### Extensions for org.elasticsearch.client.Client

|&nbsp;|&nbsp;|
|---|---|
| [createIndex](create-index.md) | <code>fun Client.createIndex(index: String, mappings: List<[IndexTypeMapping](../-index-type-mapping/index.md)>, shardCount: Int = EsConfig.indexShardCount, replicaCount: Int = EsConfig.indexReplicaCount, settingsInit: Builder.() -> Unit = {}): Promise<Unit, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [indexExists](index-exists.md) | <code>fun Client.indexExists(vararg indices: String): Promise<Boolean, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [updateIndexMappings](update-index-mappings.md) | <code>fun Client.updateIndexMappings(index: String, mappings: List<[IndexTypeMapping](../-index-type-mapping/index.md)>): Promise<List<Boolean>, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [waitForGreenCluster](wait-for-green-cluster.md) | <code>fun Client.waitForGreenCluster(): Promise<ClusterHealthStatus, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [waitForGreenIndex](wait-for-green-index.md) | <code>fun Client.waitForGreenIndex(vararg indices: String): Promise<ClusterHealthStatus, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [waitForYellowCluster](wait-for-yellow-cluster.md) | <code>fun Client.waitForYellowCluster(): Promise<ClusterHealthStatus, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
| [waitForYellowIndex](wait-for-yellow-index.md) | <code>fun Client.waitForYellowIndex(vararg indices: String): Promise<ClusterHealthStatus, [Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)></code><br/> |
