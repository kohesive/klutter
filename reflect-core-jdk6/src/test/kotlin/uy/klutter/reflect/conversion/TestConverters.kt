package uy.klutter.reflect.conversion.tests

import org.junit.Test
import uy.klutter.reflect.conversion.TypeConversionConfig
import uy.klutter.reflect.fullType
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class TestConverters {

    val converter = TypeConversionConfig.defaultConverter

    @Test public fun testBasicConversionRegistration() {
        assertTrue(converter.hasConverter(fullType<String>(), fullType<Int>()))
        assertFalse(converter.hasConverter(fullType<Goo>(), fullType<String>()))
    }

    @Test public fun testBasicConversions() {
        val x: Int = converter.convertValue("10")
        val s: String = converter.convertValue(10)
        assertEquals(10, x)
        assertEquals("10", s)
    }

    @Test public fun testStringEnumConversions() {
        val x1: EnumThings = converter.convertValue("two")
        assertEquals(EnumThings.two, x1)
        val s1: String = converter.convertValue(EnumThings.three)
        assertEquals("three", s1)
    }

    @Test public fun testIntEnumConversions() {
        val x2: EnumThings = converter.convertValue(1)
        assertEquals(EnumThings.two, x2)
        val s2: Int = converter.convertValue(EnumThings.three)
        assertEquals(2, s2)
    }

    public enum class EnumThings {
        one, two, three
    }

    data class Foo(val name: String)
    data class Goo(val name: String)
}

