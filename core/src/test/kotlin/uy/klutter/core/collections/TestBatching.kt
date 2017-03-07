@file:Suppress("UNUSED_VARIABLE")

package uy.klutter.core.collections.tests

import org.junit.Test
import uy.klutter.core.collections.batch
import uy.klutter.core.collections.lazyBatch
import java.io.Reader
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.fail


class TestGroupingStream {

    @Test fun testConvertToListOfGroupsWithoutConsumingGroup() {
        run {
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asSequence().batch(2).toList()
            assertEquals(5, listOfGroups.size)
            assertEquals(listOf(1, 2), listOfGroups[0].toList())
            assertEquals(listOf(3, 4), listOfGroups[1].toList())
            assertEquals(listOf(5, 6), listOfGroups[2].toList())
            assertEquals(listOf(7, 8), listOfGroups[3].toList())
            assertEquals(listOf(9, 10), listOfGroups[4].toList())
        }

        run {
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).batch(2).toList()
            assertEquals(5, listOfGroups.size)
            assertEquals(listOf(1, 2), listOfGroups[0].toList())
            assertEquals(listOf(3, 4), listOfGroups[1].toList())
            assertEquals(listOf(5, 6), listOfGroups[2].toList())
            assertEquals(listOf(7, 8), listOfGroups[3].toList())
            assertEquals(listOf(9, 10), listOfGroups[4].toList())
        }
    }

    @Test fun testLazyBatchingWithoutConsumingGroup() {
        run {
            var count = 0
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asSequence().lazyBatch(2) { group ->
                count++

                if (count == 5) {
                    assertEquals(listOf(9, 10), group.toList())
                }
            }

            assertEquals(5, count)
        }

        run {
            var count = 0
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).lazyBatch(2) { group ->
                count++

                if (count == 5) {
                    assertEquals(listOf(9, 10), group.toList())
                }
            }

            assertEquals(5, count)
        }
    }

    @Test fun testLazyBatchingValidatingGroups() {
        run {
            var count = 0
            val expectedGroups = listOf(listOf(1,2), listOf(3,4), listOf(5,6), listOf(7,8), listOf(9,10))
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).asSequence().lazyBatch(2) { group ->
                val groupAsList = group.toList()
                assertEquals(expectedGroups[count], groupAsList)
                count++
            }

            assertEquals(5, count)
        }

        run {
            var count = 0
            val expectedGroups = listOf(listOf(1,2), listOf(3,4), listOf(5,6), listOf(7,8), listOf(9,10))
            val listOfGroups = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).lazyBatch(2) { group ->
                assertEquals(expectedGroups[count], group.toList())
                count++
            }

            assertEquals(5, count)
        }
    }

    @Test fun testSpecificCase() {
        run {
            val originalStream = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            val results = originalStream.asSequence().batch(3).map { group ->
                group.toList()
            }.toList()

            assertEquals(listOf(1, 2, 3), results[0])
            assertEquals(listOf(4, 5, 6), results[1])
            assertEquals(listOf(7, 8, 9), results[2])
            assertEquals(listOf(10), results[3])
        }

        run {
            val originalStream = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

            val results = originalStream.batch(3).map { group ->
                group.toList()
            }.toList()

            assertEquals(listOf(1, 2, 3), results[0])
            assertEquals(listOf(4, 5, 6), results[1])
            assertEquals(listOf(7, 8, 9), results[2])
            assertEquals(listOf(10), results[3])
        }
    }


    fun testStream(testList: List<Int>, batchSize: Int, expectedGroups: Int) {
        run {
            var groupSeenCount = 0
            var itemsSeen = ArrayList<Int>()

            testList.asSequence().batch(batchSize).forEach { groupStream ->
                groupSeenCount++
                groupStream.forEach { item ->
                    itemsSeen.add(item)
                }
            }

            assertEquals(testList, itemsSeen)
            assertEquals(groupSeenCount, expectedGroups)
        }

        run {
            var groupSeenCount = 0
            var itemsSeen = ArrayList<Int>()

            testList.batch(batchSize).forEach { groupStream ->
                groupSeenCount++
                groupStream.forEach { item ->
                    itemsSeen.add(item)
                }
            }

            assertEquals(testList, itemsSeen)
            assertEquals(groupSeenCount, expectedGroups)
        }
    }

    @Test fun groupsOfExactSize() {
        testStream(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), 5, 3)
    }

    @Test fun groupsOfOddSize() {
        testStream(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18), 5, 4)
        testStream(listOf(1, 2, 3, 4), 3, 2)
    }

    @Test fun groupsOfLessThanBatchSize() {
        testStream(listOf(1, 2, 3), 5, 1)
        testStream(listOf(1), 5, 1)
    }

    @Test fun groupsOfSize1() {
        testStream(listOf(1, 2, 3), 1, 3)
    }

    @Test fun groupsOfSize0() {
        val testList = listOf(1, 2, 3)

        val groupCountZero = testList.asSequence().batch(0).toList().size
        assertEquals(0, groupCountZero)

        val groupCountZeroOtraVez = testList.batch(0).toList().size
        assertEquals(0, groupCountZeroOtraVez)

        val groupCountNeg = testList.asSequence().batch(-1).toList().size
        assertEquals(0, groupCountNeg)

        val groupCountNegOtraVez = testList.batch(-1).toList().size
        assertEquals(0, groupCountNeg)

    }

    @Test fun emptySource() {
        listOf<Int>().asSequence().batch(1).forEach { _ ->
            fail()
        }

        listOf<Int>().batch(1).forEach { _ ->
            fail()
        }

    }
}
