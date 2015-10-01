package uy.klutter.reflect.conversion.jdk8.tests

import org.junit.Test
import uy.klutter.reflect.conversion.TypeConversionConfig
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue

public class TestJdk8Converters {
   @Test public fun testInstantConversion() {
       val now = Instant.now()
       val nowAsLong = now.toEpochMilli()
       val converter = TypeConversionConfig.defaultConverter

       val convertedToLong: Long = converter.convertValue(now)
       val convertedToInstant: Instant = converter.convertValue(nowAsLong)

       assertEquals(nowAsLong, convertedToLong)
       assertEquals(now, convertedToInstant)
   }
}
