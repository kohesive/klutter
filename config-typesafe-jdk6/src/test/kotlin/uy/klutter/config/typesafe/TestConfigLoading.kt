package uy.klutter.config.typesafe.tests

import com.typesafe.config.ConfigFactory
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import uy.klutter.config.typesafe.*
import kotlin.test.assertEquals

public class TestConfigLoading {
    companion object {
        @BeforeClass @JvmStatic public fun setupSystemProps() {
            System.setProperty("systemKey", "value System")
            System.setProperty("base.value4", "four-sys")
        }

        @AfterClass @JvmStatic public fun removeSystemProps() {
            System.clearProperty("base.value4")
            System.clearProperty("systemKey")
            ConfigFactory.invalidateCaches()
        }
    }

    @Before public fun clearCaching() {
        // system properties are cached in Typesafe Config and our tests might want to change them.
        // This method doesn't just clear cache, it reloads the values
        ConfigFactory.invalidateCaches()
    }

    @Test public fun testConfigOverridingOrder() {
        val a = mapOf("common" to "value A", "unique A" to "value A", "systemKey" to "value A")
        val b = mapOf("common" to "value B", "unique B" to "value B", "systemKey" to "value B")

        val cfg = loadConfig(SystemPropertiesConfig(), MapAsConfig(a), MapAsConfig(b))

        assertEquals("value A", cfg.value("common").asString())
        assertEquals("value A", cfg.value("unique A").asString())
        assertEquals("value B", cfg.value("unique B").asString())
        assertEquals("value System", cfg.value("systemKey").asString())
    }

    @Test public fun testAppConfLoading() {
        val cfg = loadApplicationConfig()

        assertEquals("one", cfg.value("base.value1").asString())
        assertEquals("two-app", cfg.value("base.value2").asString())
        assertEquals("three", cfg.value("base.value3").asString())
        assertEquals("four-sys", cfg.value("base.value4").asString())
    }
}
