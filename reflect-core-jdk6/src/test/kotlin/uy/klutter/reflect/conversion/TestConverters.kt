package uy.klutter.reflect.conversion.tests

import org.junit.Test
import uy.klutter.reflect.conversion.*
import uy.klutter.reflect.fullType
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import uy.klutter.reflect.*

public class TestConverters {

    @Test public fun testBasicConversionRegistration() {
        assertTrue(DefaultTypeConverter.hasConverter(fullType<String>(), fullType<Int>()))
        assertFalse(DefaultTypeConverter.hasConverter(fullType<Goo>(), fullType<String>()))
    }

    @Test public fun testBasicConversions() {
        val x: Int = DefaultTypeConverter.convertValue("10")
        val s: String = DefaultTypeConverter.convertValue(10)
        assertEquals(10, x)
        assertEquals("10", s)
    }

    @Test public fun testStringEnumConversions() {
        val x1: EnumThings = DefaultTypeConverter.convertValue("two")
        assertEquals(EnumThings.two, x1)
        val s1: String = DefaultTypeConverter.convertValue(EnumThings.three)
        assertEquals("three", s1)
    }

    @Test public fun testIntEnumConversions() {
        val x2: EnumThings = DefaultTypeConverter.convertValue(1)
        assertEquals(EnumThings.two, x2)
        val s2: Int =  DefaultTypeConverter.convertValue(EnumThings.three)
        assertEquals(2, s2)
    }

    public enum class EnumThings {
        one, two, three
    }

    data class Foo(val name: String)
    data class Goo(val name: String)
}

