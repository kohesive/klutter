package uy.klutter.binder

import org.junit.Ignore
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestBinder {

    @Test
    fun testArrayConstruction() {
        val provider = seqValueProviderOf(1, 2, 3)
        run {
            val check = Binder().constructArrayByType<Int>(eitherTypeOf<Array<Int>>(), provider).deferred.executor()
            assertTrue(Arrays.equals(arrayOf(1, 2, 3), check))
        }

        run {
            val check = Binder().constructArrayByComponentType<Int>(eitherTypeOf<Int>(), provider).deferred.executor()
            assertTrue(Arrays.equals(arrayOf(1, 2, 3), check))
        }
    }

    @Test
    fun testArrayConstruction_With_Allowed_Nulls() {
        val provider = seqValueProviderOf(1, null, 3)
        run {
            val check = Binder().constructArrayByType<Int?>(eitherTypeOf<Array<Int?>>(), provider).deferred.executor()
            assertTrue(Arrays.equals(arrayOf(1, null, 3), check))
        }

        run {
            val check = Binder().constructArrayByComponentType<Int?>(eitherTypeOf<Int?>(), provider).deferred.executor()
            assertTrue(Arrays.equals(arrayOf(1, null, 3), check))
        }
    }

    @Test(expected = NullPointerException::class)
    @Ignore("Kotlin cannot yet reify a KType which would allow checking nulls from reified parameters")
    fun testArrayConstruction_With_Disallowed_Nulls_1() {
        val provider = seqValueProviderOf(1, null, 3)
        Binder().constructArrayByType<Int>(eitherTypeOf<Array<Int>>(), provider).deferred.executor()
    }

    @Test(expected = NullPointerException::class)
    @Ignore("Kotlin cannot yet reify a KType which would allow checking nulls from reified parameters")
    fun testArrayConstruction_With_Disallowed_Nulls_2() {
        val provider = seqValueProviderOf(1, null, 3)
        Binder().constructArrayByComponentType<Int>(eitherTypeOf<Int>(), provider).deferred.executor()
    }

    @Test
    fun testMapConstruction_SingleLevel_ToMapInterface() {
        val originalMap = mapOf("a" to 1, "b" to 2, "c" to 3)
        val provider = mapValueProviderOf(originalMap)
        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int>(eitherTypeOf<Map<String, Int>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }
    }

    @Test
    fun testMapConstruction_Allowed_Nullable_Values() {
        val originalMap = mapOf("a" to 1, "b" to null, "c" to 3)
        val provider = mapValueProviderOf(originalMap)
        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int?>(eitherTypeOf<Map<String, Int?>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }
    }

    @Test(expected = NullPointerException::class)
    @Ignore("Kotlin cannot yet reify a KType which would allow checking nulls from reified parameters")
    fun testMapConstruction_Disallowed_Nullable_Values() {
        val originalMap = mapOf("a" to 1, "b" to null, "c" to 3)
        val provider = mapValueProviderOf(originalMap)
        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int>(eitherTypeOf<Map<String, Int>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }
    }

    @Test
    fun testMapConstruction_SingleLevel_ToMapType() {
        val originalMap = mapOf("a" to 1, "b" to 2, "c" to 3)
        val provider = mapValueProviderOf(originalMap)
        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int>(eitherTypeOf<HashMap<String, Int>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }

        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int>(eitherTypeOf<LinkedHashMap<String, Int>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }

        run {
            // as interface target, exact types
            val check = Binder().constructMap<String, Int>(eitherTypeOf<TreeMap<String, Int>>(), provider).deferred.executor()
            assertEquals(originalMap, check)
        }
    }
}