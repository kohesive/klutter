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

    @Test public fun testBooleanConversions() {
        val bT: Boolean = converter.convertValue("true")
        val bF: Boolean = converter.convertValue("False")
        assertTrue(bT)
        assertFalse(bF)

        val bT1: Boolean = converter.convertValue(1)
        val bF0: Boolean = converter.convertValue(0)
        assertTrue(bT1)
        assertFalse(bF0)

        val bTCT: Boolean = converter.convertValue('T')
        val bFCF: Boolean = converter.convertValue('F')
        assertTrue(bTCT)
        assertFalse(bFCF)

        val bsTrue: String = converter.convertValue(true)
        val bsFalse: String = converter.convertValue(false)
        val biTrue: Int = converter.convertValue(true)
        val biFalse: Int = converter.convertValue(false)
        val bcTrue: Char = converter.convertValue(true)
        val bcFalse: Char = converter.convertValue(false)

        assertEquals("true", bsTrue)
        assertEquals("false", bsFalse)
        assertEquals(1, biTrue)
        assertEquals(0, biFalse)
        assertEquals('T', bcTrue)
        assertEquals('F', bcFalse)
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

