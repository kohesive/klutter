package uy.klutter.reflect.conversion

import org.junit.Test
import uy.klutter.reflect.reifiedKType
import uy.klutter.reflect.reifiedType
import kotlin.reflect.full.starProjectedType
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestTypeInfo {

    @Test
    fun testReifyKtypeNullable() {
        val nullableString = reifiedKType<String?>()
        assertTrue(nullableString.isMarkedNullable)

        val nonNullableString = reifiedKType<String>()
        assertFalse(nonNullableString.isMarkedNullable)
    }

    @Test
    fun testReifyKtypeNullableWithGenericType() {
        val nullableMap = reifiedKType<Map<String, String>?>()
        assertTrue(nullableMap.isMarkedNullable)
        assertEquals(String::class.starProjectedType, nullableMap.arguments[0].type)
        assertEquals(String::class.starProjectedType, nullableMap.arguments[1].type)

        val nonNullableMap = reifiedKType<Map<String, String>>()
        assertFalse(nonNullableMap.isMarkedNullable)
        assertEquals(String::class.starProjectedType, nonNullableMap.arguments[0].type)
        assertEquals(String::class.starProjectedType, nonNullableMap.arguments[1].type)
    }
}