package uy.klutter.config.typesafe.tests

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.singleton
import org.junit.Test
import org.kodein.di.direct
import org.kodein.di.generic.instance
import uy.klutter.config.typesafe.MapAsConfig
import uy.klutter.config.typesafe.kodein.ConfigModule
import uy.klutter.config.typesafe.kodein.importConfig
import uy.klutter.config.typesafe.loadConfig
import kotlin.test.assertEquals

class TestConfigInjection {
    val fakeConfig = mapOf(
            "http" to mapOf("httpPort" to 8080, "workerThreads" to 16),
            "data" to mapOf("bucket" to "com.test.bucket", "region" to "us-east"),
            "other" to mapOf("name" to "frisbee"))
    val kodein = Kodein {
        importConfig(loadConfig(MapAsConfig(fakeConfig))) {
            bind<HttpConfig>() fromConfig "http"
            bind<DataConfig>() fromConfig "data"
            constant<Int>("serverPort") fromConfig "http.httpPort"
            import("other", OtherModule.configModule)
        }

        bind() from singleton { ConfiguredThing(instance(), instance()) }
        import(OtherModule.kodeinModule)
    }

    @Test fun testConfigSingletonsExist() {
        val matchHttp = HttpConfig(8080, 16)
        val matchData = DataConfig("com.test.bucket", "us-east")

        assertEquals(matchHttp, kodein.direct.instance<HttpConfig>())
        assertEquals(matchData, kodein.direct.instance<DataConfig>())
    }

    @Test fun testFactoryUsingConfigWorks() {
        val matchHttp = HttpConfig(8080, 16)
        val matchData = DataConfig("com.test.bucket", "us-east")

        val thing = kodein.direct.instance<ConfiguredThing>()
        assertEquals(matchHttp, thing.httpCfg)
        assertEquals(matchData, thing.dataCfg)
    }

    @Test fun testWithModules() {
        val thing = kodein.direct.instance<OtherThingWantingConfig>()
        assertEquals("frisbee", thing.cfg.name)
    }

    @Test fun testConstant() {
        assertEquals(8080, kodein.direct.instance("serverPort"))
    }


    data class HttpConfig(val httpPort: Int, val workerThreads: Int)
    data class DataConfig(val bucket: String, val region: String)
    data class ConfiguredThing(val httpCfg: HttpConfig, val dataCfg: DataConfig)
}


data class OtherConfig(val name: String)
data class OtherThingWantingConfig(val cfg: OtherConfig)

object OtherModule {
    val configModule = Kodein.ConfigModule {
        bind<OtherConfig>() fromConfig(it)
    }

    val kodeinModule = Kodein.Module("klutter-test-other-module-${System.currentTimeMillis()}") {
        bind() from singleton { OtherThingWantingConfig(instance()) }
    }
}


