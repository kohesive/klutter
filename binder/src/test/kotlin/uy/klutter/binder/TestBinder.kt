package uy.klutter.binder

import org.junit.Test
import uy.klutter.reflect.reifiedType
import java.util.*
import kotlin.test.assertTrue

class TestBinder {
    @Test fun testArrayConstruction() {
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
}