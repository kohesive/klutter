package uy.klutter.config.typesafe.tests

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigObject
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import uy.klutter.config.typesafe.*
import kotlin.test.assertEquals

class TestConfigLoading {
    companion object {
        @BeforeClass @JvmStatic fun setupSystemProps() {
            System.setProperty("systemKey", "value System")
            System.setProperty("base.value4", "four-sys")
        }

        @AfterClass @JvmStatic fun removeSystemProps() {
            System.clearProperty("base.value4")
            System.clearProperty("systemKey")
            ConfigFactory.invalidateCaches()
        }
    }

    @Before fun clearCaching() {
        // system properties are cached in Typesafe Config and our tests might want to change them.
        // This method doesn't just clear cache, it reloads the values
        ConfigFactory.invalidateCaches()
    }

    @Test fun testConfigOverridingOrder() {
        val a = mapOf("common" to "value A", "unique A" to "value A", "systemKey" to "value A")
        val b = mapOf("common" to "value B", "unique B" to "value B", "systemKey" to "value B")

        val cfg = loadConfig(SystemPropertiesConfig(), MapAsConfig(a), MapAsConfig(b))

        assertEquals("value A", cfg.value("common").asString())
        assertEquals("value A", cfg.value("unique A").asString())
        assertEquals("value B", cfg.value("unique B").asString())
        assertEquals("value System", cfg.value("systemKey").asString())
    }

    @Test fun testAppConfLoading() {
        val cfg = loadApplicationConfig()

        assertEquals("one", cfg.value("base.value1").asString())
        assertEquals("two-app", cfg.value("base.value2").asString())
        assertEquals("three", cfg.value("base.value3").asString())
        assertEquals("four-sys", cfg.value("base.value4").asString())
    }

    // from http://stackoverflow.com/questions/37092808/reading-and-processing-hocon-in-kotlin
    @Test fun testFromSo37092808() {
        // === mocked configuration file

        val cfg = loadConfig(StringAsConfig("""
                products: {
                  columns: [
                    { item: { type: integer, key: true, null: false } }
                    { desc: { type: varchar, length: 64 } }
                    { quantity: { type: integer, null: false } }
                    { price: { type: decimal, precision: 14, scale: 3 } }
                  ]
                }
              """))

        // === function to find which columns are key columns

        fun findKeyColumns(cfg: Config, tableName: String): Map<String, ConfigObject> {
            return cfg.nested(tableName).value("columns").asObjectList()
                    .map { it.keys.single() to it.value(it.keys.single()).asObject() }
                    .filter {
                        it.second.value("key").asBoolean(false)
                    }
                    .toMap()
        }

        // === sample usage

        val productKeys = findKeyColumns(cfg, "products")

        // we only have 1 in the test data, so grab the name and the values
        val onlyColumnName = productKeys.entries.first().key
        val onlyColumnObj = productKeys.entries.first().value

        assertEquals ("item", onlyColumnName)
        assertEquals (true, onlyColumnObj.value("key").asBoolean())
        assertEquals ("integer", onlyColumnObj.value("type").asString())
        assertEquals (false, onlyColumnObj.value("null").asBoolean())
    }

}
