package uy.klutter.core.collections

import org.junit.Test
import java.util.*

class TestImmutableCollections {
    @Test(expected = ClassCastException::class) fun testImmumtableListCannotCastAsMutable() {
        val l = arrayListOf("a", "b", "c").asImmutable()
        val x = l as ArrayList<String>
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test fun testNonImmutableListCanBeModifiedWithCasting() {
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
    @Test(expected = UnsupportedOperationException::class) fun testImmutableListMakesImmutableIterator() {
        val l = arrayListOf("a", "b", "c").asImmutable()
        val x = l.iterator() as java.util.Iterator<String>
        x.next()
        x.remove()
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testImmutableListMakesImmutableListIteratorRemove() {
        val l = arrayListOf("a", "b", "c").asImmutable()
        val x = l.listIterator() as java.util.ListIterator<String>
        x.next()
        x.remove()
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testImmutableListMakesImmutableListIteratorAdd() {
        val l = arrayListOf("a", "b", "c").asImmutable()
        val x = l.listIterator() as java.util.ListIterator<String>
        x.add("fish")
    }

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
    @Test(expected = UnsupportedOperationException::class) fun testImmutableListMakesImmutableSublist() {
        val l = arrayListOf("a", "b", "c").asImmutable()
        val x = l.subList(1, 2) as java.util.List<String>
        x.add("fish")
    }

    @Test(expected = ClassCastException::class) fun testImmutableMapCannotBeCastToMutableMap() {
        val m = hashMapOf("one" to 1, "two" to 2, "three" to 3).asImmutable()
        val x = m as HashMap<String, Int> // nope, protected
    }

    @Test(expected = ClassCastException::class) fun testImmutableMapMakesImmutableEntrySet() {
        val m = mutableMapOf("one" to 1, "two" to 2, "three" to 3).asImmutable()
        val x = m.entries as AbstractSet<Map.Entry<String, Int>> // nope, protected
    }
}