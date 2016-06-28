package uy.klutter.elasticsearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.all
import nl.komponents.kovenant.deferred
import nl.komponents.kovenant.functional.bind
import nl.komponents.kovenant.functional.map
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.ActionRequest
import org.elasticsearch.action.ActionRequestBuilder
import org.elasticsearch.action.ActionResponse
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse
import org.elasticsearch.action.admin.cluster.health.ClusterHealthStatus
import org.elasticsearch.client.Client
import org.elasticsearch.client.ElasticsearchClient
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.common.transport.TransportAddress
import org.elasticsearch.common.unit.TimeValue
import org.elasticsearch.node.NodeBuilder
import uy.klutter.core.common.with
import java.nio.file.Path
import java.util.*

object EsConfig {
    @Volatile var adminActionTimeoutInSeconds: Long = 30
    @Volatile var indexReplicaCount: Int = 1
    @Volatile var indexShardCount: Int = 4

    @Volatile var objectMapper: ObjectMapper = jacksonObjectMapper()
}


fun esNodeClient(clusterName: String, settings: Map<String, String>): Client {
    return esNodeClient(clusterName) {
        settings.entries.forEach {
            put(it.key, it.value)
        }
    }
}

fun esNodeClient(clusterName: String, init: ImmutableSettings.Builder.()->Unit): Client {
    val settings = ImmutableSettings.settingsBuilder()
            .put("cluster.name", clusterName)
            .put("client.transport.sniff", false)
            .put("node.name", "nodeClient-" + System.currentTimeMillis())
            .put("http.enabled", false)
            .put("node.data", false)
            .put("node.master", false)
            .with { init() }
            .build()
    return NodeBuilder.nodeBuilder().settings(settings).node().client()
}

fun esTransportClient(clusterName: String, nodes: List<TransportAddress>, settings: Map<String, String>): Client {
    return esTransportClient(clusterName, nodes) {
        settings.entries.forEach {
            put(it.key, it.value)
        }
    }
}

fun esTransportClient(clusterName: String, nodes: List<TransportAddress>, init: ImmutableSettings.Builder.()->Unit): Client {
    val settings = ImmutableSettings.settingsBuilder()
            .put("cluster.name", clusterName)
            .put("client.transport.sniff", false)
            .with { init() }
            .build()
    val client = TransportClient(settings)
    nodes.forEach {
        client.addTransportAddress(it)
    }
    return client
}


fun esEmbeddedClient(clusterName: String, baseDir: Path, settings: Map<String, String>): Promise<Client, Exception>  {
    return esEmbeddedClient(clusterName, baseDir) {
        settings.entries.forEach {
            put(it.key, it.value)
        }
    }
}

fun esEmbeddedClient(clusterName: String, baseDir: Path,  init: ImmutableSettings.Builder.()->Unit): Promise<Client, Exception> {
    val deferred = deferred<Client, Exception>()
    try {
        val esRoot = baseDir.toAbsolutePath()
        val settings = ImmutableSettings.settingsBuilder()
                .put("path.data", "$esRoot/data")
                .put("path.work", "$esRoot/work")
                .put("path.logs", "$esRoot/logs")
                .put("http.enabled", false)
                .put("index.number_of_shards", "2")
                .put("index.number_of_replicas", "0")
                .put("cluster.routing.schedule", "50ms")
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", false)
                .put("node.name", "nodeEmbedded-" + System.currentTimeMillis())
                .put("node.data", true)
                .put("cluster.routing.allocation.disk.threshold_enabled", true)
                .put("cluster.routing.allocation.disk.watermark.low", "10gb")
                .with { init() }
                .build()
        val tempNode = NodeBuilder.nodeBuilder().local(true).data(true).settings(settings).node()
        val tempClient = tempNode.client()
        return tempClient.waitForYellowCluster().bind { deferred.promise }
    }
    catch (ex: Throwable) {
        deferred.reject(wrapThrowable(ex))
    }
    return deferred.promise
}

fun Client.waitForGreenCluster(): Promise<ClusterHealthStatus, Exception> {
    return admin().cluster().prepareHealth().setWaitForGreenStatus().setTimeout(TimeValue.timeValueSeconds(EsConfig.adminActionTimeoutInSeconds)).promise { it.getStatus() }
}

fun Client.waitForYellowCluster(): Promise<ClusterHealthStatus, Exception> {
    return admin().cluster().prepareHealth().setWaitForYellowStatus().setTimeout(TimeValue.timeValueSeconds(EsConfig.adminActionTimeoutInSeconds)).promise { it.getStatus() }
}

fun Client.waitForGreenIndex(vararg indices: String): Promise<ClusterHealthStatus, Exception> {
    return admin().cluster().prepareHealth(*indices).setWaitForGreenStatus().setTimeout(TimeValue.timeValueSeconds(EsConfig.adminActionTimeoutInSeconds)).promise { it.getStatus() }
}

fun Client.waitForYellowIndex(vararg indices: String): Promise<ClusterHealthStatus, Exception> {
    return admin().cluster().prepareHealth(*indices).setWaitForYellowStatus().setTimeout(TimeValue.timeValueSeconds(EsConfig.adminActionTimeoutInSeconds)).promise { it.getStatus() }
}

fun Client.indexExists(vararg indices: String): Promise<Boolean, Exception> {
    return admin().indices().prepareExists(*indices).promise { it.isExists() }
}

fun Client.createIndex(index: String, mappings: List<IndexTypeMapping>, shardCount: Int = EsConfig.indexShardCount, replicaCount: Int = EsConfig.indexReplicaCount, settingsInit: ImmutableSettings.Builder.()->Unit = {}): Promise<Unit, Exception> {
    val indexSettings = ImmutableSettings.settingsBuilder()
            .put("number_of_shards", shardCount)
            .put("number_of_replicas", replicaCount)
            .with { settingsInit() }
            .build()
    return admin().indices().prepareCreate(index).setSettings(indexSettings).with { mappings.forEach { addMapping(it.type, it.json) } }.promiseNothing()
}

fun Client.updateIndexMappings(index: String, mappings: List<IndexTypeMapping>): Promise<List<Boolean>, Exception> {
    val actions = LinkedList<Promise<Boolean, Exception>>()
    mappings.forEach {
        actions.add(admin().indices().preparePutMapping(index).setType(it.type).setSource(it.json).promise { it.isAcknowledged() })
    }
    return all<Boolean>(actions)
}

