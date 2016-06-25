[uy.klutter.elasticsearch](../index.md) / [org.elasticsearch.client.Client](.)


### Extensions for org.elasticsearch.client.Client

|&nbsp;|&nbsp;|
|---|---|
| [createIndex](create-index.md) | `fun Client.createIndex(index:&nbsp;String, mappings:&nbsp;List&lt;[IndexTypeMapping](../-index-type-mapping/index.md)&gt;, shardCount:&nbsp;Int&nbsp;=&nbsp;EsConfig.indexShardCount, replicaCount:&nbsp;Int&nbsp;=&nbsp;EsConfig.indexReplicaCount, settingsInit:&nbsp;Builder.()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Promise&lt;Unit,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [indexExists](index-exists.md) | `fun Client.indexExists(vararg indices:&nbsp;String): Promise&lt;Boolean,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [updateIndexMappings](update-index-mappings.md) | `fun Client.updateIndexMappings(index:&nbsp;String, mappings:&nbsp;List&lt;[IndexTypeMapping](../-index-type-mapping/index.md)&gt;): Promise&lt;List&lt;Boolean&gt;,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [waitForGreenCluster](wait-for-green-cluster.md) | `fun Client.waitForGreenCluster(): Promise&lt;ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [waitForGreenIndex](wait-for-green-index.md) | `fun Client.waitForGreenIndex(vararg indices:&nbsp;String): Promise&lt;ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [waitForYellowCluster](wait-for-yellow-cluster.md) | `fun Client.waitForYellowCluster(): Promise&lt;ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
| [waitForYellowIndex](wait-for-yellow-index.md) | `fun Client.waitForYellowIndex(vararg indices:&nbsp;String): Promise&lt;ClusterHealthStatus,&nbsp;[Exception](http://docs.oracle.com/javase/6/docs/api/java/lang/Exception.html)&gt;` |
