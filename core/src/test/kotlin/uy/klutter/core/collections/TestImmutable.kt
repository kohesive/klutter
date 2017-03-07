@file:Suppress("UNUSED_VARIABLE")

package uy.klutter.core.collections

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestReadOnlyAndImmutableCollections {
    @Test(expected = ClassCastException::class) fun testReadOnlyListCannotCastAsMutable() {
        val l = arrayListOf("a", "b", "c").asReadOnly()
        val x = l as ArrayList<String>
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test fun testNonReadOnlyListCanBeModifiedWithCasting() {
        run {
            val l: List<String> = arrayListOf("a", "b", "c")
            val mutable = l as ArrayList<String>
            mutable.add("bad")
            // BAD:  no failure, then you are not protected
        }

        run {
            val l: List<String> = arrayListOf("1", "2", " 3")
            val mutable = l.iterator() as java.util.Iterator<String>
            mutable.next()
            mutable.remove()
            // BAD:  no failure, then you are not protected
        }

        run {
            val l: List<String> = arrayListOf("a", "b", "c")
            val mutable = l.subList(1, 2) as AbstractList<String>
            mutable.add("bad")
            // BAD:  no failure, then you are not protected
        }
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testReadOnlyListMakesImmutableIterator() {
        val l = arrayListOf("a", "b", "c").asReadOnly()
        val x = l.iterator() as java.util.Iterator<String>
        x.next()
        x.remove()
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testReadOnlyListMakesImmutableListIteratorRemove() {
        val l = arrayListOf("a", "b", "c").asReadOnly()
        val x = l.listIterator() as java.util.ListIterator<String>
        x.next()
        x.remove()
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testReadOnlyListMakesImmutableListIteratorAdd() {
        val l = arrayListOf("a", "b", "c").asReadOnly()
        val x = l.listIterator() as java.util.ListIterator<String>
        x.add("fish")
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testReadOnlyListMakesImmutableSublist() {
        val l = arrayListOf("a", "b", "c").asReadOnly()
        val x = l.subList(1, 2) as java.util.List<String>
        x.add("fish")
    }

    @Test(expected = ClassCastException::class) fun testReadOnlyMapCannotBeCastToMutableMap() {
        val m = hashMapOf("one" to 1, "two" to 2, "three" to 3).asReadOnly()
        val x = m as HashMap<String, Int> // nope, protected
    }

    @Test(expected = ClassCastException::class) fun testReadOnlyMapMakesImmutableEntrySet() {
        val m = mutableMapOf("one" to 1, "two" to 2, "three" to 3).asReadOnly()
        val x = m.entries as AbstractSet<Map.Entry<String, Int>> // nope, protected
    }

    @Test fun testGithub35_RandomAccessMarkerIsRetained() {
        assertTrue(arrayListOf(1,2,3) is RandomAccess)
        assertTrue(arrayListOf(1,2,3) is RandomAccess)
        assertFalse(LinkedList(listOf(1,2,3)) is RandomAccess)
        assertFalse(LinkedList(listOf(1,2,3)).asReadOnly() is RandomAccess)
    }

    @Test fun testEqualsWorks() {
        assertEquals(listOf(1,2,3), listOf(1,2,3).asReadOnly())
        assertEquals(listOf(1,2,3), listOf(1,2,3).toImmutable())
        assertTrue(listOf(1,2,3) == listOf(1,2,3).asReadOnly())
        assertTrue(listOf(1,2,3) == listOf(1,2,3).toImmutable())

        assertTrue(listOf(1,2,3) == arrayListOf(1,2,3).asReadOnly())
        assertTrue(listOf(1,2,3) == arrayListOf(1,2,3).toImmutable())

        assertEquals(mapOf("one" to 1, "two" to 2), mapOf("one" to 1, "two" to 2).asReadOnly())
        assertEquals(mapOf("one" to 1, "two" to 2), mapOf("one" to 1, "two" to 2).toImmutable())
        assertTrue(mapOf("one" to 1, "two" to 2) == mapOf("one" to 1, "two" to 2).asReadOnly())
        assertTrue(mapOf("one" to 1, "two" to 2) == mapOf("one" to 1, "two" to 2).toImmutable())

        assertEquals(setOf(1,2,3), setOf(1,2,3).asReadOnly())
        assertEquals(setOf(1,2,3), setOf(1,2,3).toImmutable())
        assertTrue(setOf(1,2,3) == setOf(1,2,3).asReadOnly())
        assertTrue(setOf(1,2,3) == setOf(1,2,3).toImmutable())

        assertEquals(sortedSetOf(1,2,3), sortedSetOf(1,2,3).asReadOnly())
        assertEquals(sortedSetOf(1,2,3), sortedSetOf(1,2,3).toImmutable())
        assertTrue(sortedSetOf(1,2,3) == sortedSetOf(1,2,3).asReadOnly())
        assertTrue(sortedSetOf(1,2,3) == sortedSetOf(1,2,3).toImmutable())

    }

    @Test fun testImmutableListCannotBeModifiedFromOriginalList() {
        val l = arrayListOf(1, 2, 3)
        val i = l.toImmutable()
        val r = l.asReadOnly()

        l.add(4)
        l.remove(element = 1)

        assertEquals(listOf(2,3,4), l) // original list changed
        assertEquals(listOf(1,2,3).asReadOnly(), i)
        assertEquals(listOf(2,3,4), r) // read only was modified by changing original
    }

}