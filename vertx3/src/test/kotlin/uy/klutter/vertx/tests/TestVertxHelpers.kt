package uy.klutter.vertx.tests

import com.hazelcast.config.AwsConfig
import com.hazelcast.config.GroupConfig
import com.hazelcast.config.XmlConfigBuilder
import io.vertx.core.AbstractVerticle
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.combine.combine
import nl.komponents.kovenant.functional.bind
import nl.komponents.kovenant.then
import nl.komponents.kovenant.unwrap
import org.junit.Test
import uy.klutter.vertx.*
import java.net.InetAddress
import kotlin.test.assertEquals
import kotlin.test.assertNull


class TestVertxHelpers {
    @Test fun testStartupAndDeployPromises() {
        // local clustered without interfering with other clusters running at same time, on this or other machines

        val hazelcastConfig = XmlConfigBuilder().build().setGroupConfig(GroupConfig("Test${System.currentTimeMillis()}", "${System.currentTimeMillis()}"))
        val loopback = InetAddress.getLoopbackAddress().hostAddress
        hazelcastConfig.networkConfig.join.awsConfig = AwsConfig().setEnabled(false)
        hazelcastConfig.networkConfig.interfaces.interfaces = setOf(loopback)
        hazelcastConfig.networkConfig.join.multicastConfig.isEnabled = false
        hazelcastConfig.networkConfig.join.tcpIpConfig.setEnabled(false).members = listOf(loopback)
        hazelcastConfig.networkConfig.port = 10599
        hazelcastConfig.networkConfig.portCount = 100
        hazelcastConfig.networkConfig.isPortAutoIncrement = true
        val vertxOptions = VertxOptions().setWorkerPoolSize(16)
                .setClustered(true)
                .setClusterManager(HazelcastClusterManager(hazelcastConfig))

        // actual test...

        val vertxInstance = vertxCluster(vertxOptions).get()  // force synchronous
        val vertxDeployment = vertxInstance.promiseDeployVerticle(TestVertxVerticle::class.java).get()  // force synchronous

        vertxInstance.sharedData().promiseClusterWideMap<String, String>("testmap").bind { map ->
            combine(Promise.of(map), map.put("testKey", "testValue"))
        }.bind { tuple ->
            val (map, _) = tuple
            combine(Promise.of(map), map.get("testKey"))
        }.bind { tuple ->
            val (map, getResult) = tuple
            assertEquals("testValue", getResult)
            combine(Promise.of(map), map.clear())
        }.bind { tuple ->
            val (map, _) = tuple
            combine(Promise.of(map), map.get("testKey"))
        }.then { tuple ->
            val (_, getResult) = tuple
            assertNull(getResult)
        }.get() // force synchronous

        vertxInstance.promiseUndeploy(vertxDeployment).then {
            vertxInstance.promiseClose()
        }.unwrap().get()  // force synchronous

    }
}

class TestVertxVerticle : AbstractVerticle() {
    override fun start() {
        println("Started")
    }

}