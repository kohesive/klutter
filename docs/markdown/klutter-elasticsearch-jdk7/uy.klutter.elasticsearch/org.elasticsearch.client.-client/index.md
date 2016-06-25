[uy.klutter.elasticsearch](../index.md) / [org.elasticsearch.client.Client](.)


### Extensions for org.elasticsearch.client.Client

|&nbsp;|&nbsp;|
|---|---|
| [createIndex](create-index.md) | `fun Client.createIndex(index:&nbsp;String, mappings:&nbsp;List<[IndexTypeMapping](../-index-type-mapping/index.md)>, shardCount:&nbsp;Int&nbsp;=&nbsp;EsConfig.indexShardCount, replicaCount:&nbsp;Int&nbsp;=&nbsp;EsConfig.indexReplicaCount, settingsInit:&nbsp;Builder.()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Promise<Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [indexExists](index-exists.md) | `fun Client.indexExists(vararg indices:&nbsp;String): Promise<Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [updateIndexMappings](update-index-mappings.md) | `fun Client.updateIndexMappings(index:&nbsp;String, mappings:&nbsp;List<[IndexTypeMapping](../-index-type-mapping/index.md)>): Promise<List<Boolean>,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [waitForGreenCluster](wait-for-green-cluster.md) | `fun Client.waitForGreenCluster(): Promise<ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [waitForGreenIndex](wait-for-green-index.md) | `fun Client.waitForGreenIndex(vararg indices:&nbsp;String): Promise<ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [waitForYellowCluster](wait-for-yellow-cluster.md) | `fun Client.waitForYellowCluster(): Promise<ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
| [waitForYellowIndex](wait-for-yellow-index.md) | `fun Client.waitForYellowIndex(vararg indices:&nbsp;String): Promise<ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)>` |
