package uy.klutter.config.typesafe.tests

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.singleton
import org.junit.Test
import uy.klutter.config.typesafe.*
import uy.klutter.config.typesafe.kodein.KodeinTypesafeConfig
import uy.klutter.config.typesafe.kodein.importConfig
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
        val matchHttp = HttpConfig(8080,16)
        val matchData = DataConfig("com.test.bucket", "us-east")

        assertEquals(matchHttp, kodein.instance<HttpConfig>())
        assertEquals(matchData, kodein.instance<DataConfig>())
    }

    @Test fun testFactoryUsingConfigWorks() {
        val matchHttp = HttpConfig(8080,16)
        val matchData = DataConfig("com.test.bucket", "us-east")

        val thing =  kodein.instance<ConfiguredThing>()
        assertEquals(matchHttp, thing.httpCfg)
        assertEquals(matchData, thing.dataCfg)
    }

    @Test fun testWithModules() {
        val thing = kodein.instance<OtherThingWantingConfig>()
        assertEquals("frisbee", thing.cfg.name)
    }

    @Test fun testConstant() {
        assertEquals(8080, kodein.instance("serverPort"))
    }


    data class HttpConfig(val httpPort: Int, val workerThreads: Int)
    data class DataConfig(val bucket: String, val region: String)
    data class ConfiguredThing(val httpCfg: HttpConfig, val dataCfg: DataConfig)
}


data class OtherConfig(val name: String)
data class OtherThingWantingConfig(val cfg: OtherConfig)

object OtherModule {
    val configModule = KodeinTypesafeConfig.Module {
        bind<OtherConfig>() fromConfig(it)
    }

    val kodeinModule = Kodein.Module {
        bind() from singleton { OtherThingWantingConfig(instance()) }
    }
}




