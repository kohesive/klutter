package uy.klutter.reflect.conversion.jdk8.tests

import org.junit.Test
import uy.klutter.conversion.TypeConversionConfig
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestJdk8Converters {
   @Test fun testInstantConversion() {
       val now = Instant.now()
       val nowAsLong = now.toEpochMilli()
       val converter = TypeConversionConfig.defaultConverter

       val convertedToLong: Long = converter.convertValue(now)
       val convertedToInstant: Instant = converter.convertValue(nowAsLong)

       assertEquals(nowAsLong, convertedToLong)
       assertEquals(now, convertedToInstant)
   }
}
