package com.conena.nanokt

import org.junit.Assert.*

import org.junit.Test

class CollectionExtensionsKtTest {

    @Test
    fun `emptyAsNull - return empty collection as null`() {
        val emptyList = emptyList<Any>()
        val emptyList2 = listOf<Any>()
        assertNull(emptyList.emptyAsNull())
        assertNull(emptyList2.emptyAsNull())
    }

    @Test
    fun `emptyAsNull - return non-empty collection`() {
        val list1 = listOf(Any())
        val list2 = listOf<Any?>(null)
        assertEquals(list1, list1.emptyAsNull())
        assertEquals(list2, list2.emptyAsNull())
    }

    @Test
    fun listOfInstanceOf() {
        val list1 = listOfInstanceOf<String>("String", 1L, 1, 1.0, 0 .. 1, "String2")
        assertEquals(2, list1.size)
        val list2 = listOfInstanceOf<String>(1L, null, 2L, null, 3L)
        assertEquals(0, list2.size)
        val list3 = listOfInstanceOf<Long>(1L, null, 2L, null, 3L, null, 4)
        assertEquals(3, list3.size)
        val list4 = listOfInstanceOf<Long>(1L)
        assertEquals(1, list4.size)
        val list5 = listOfInstanceOf<Long>(1.0)
        assertEquals(0, list5.size)
    }

    @Test
    fun removeIfCompat() {
        // Remove from list
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5)
        assertTrue(list.removeIfCompat { it > 5 })
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Remove from set
        val set = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedSet = mutableSetOf("ABC", "BCD", "FGH")
        assertTrue(set.removeIfCompat { it.contains('E') })
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())

        // Do not remove from list
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list2.removeIfCompat { it > 10 })
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not remove from set
        val set2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedSet2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        assertFalse(set2.removeIfCompat { it.contains('I') })
        assertArrayEquals(expectedSet2.toTypedArray(), set2.toTypedArray())
    }

    @Test
    fun addOrRemove() {
        // Remove
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 4, 5, 6, 7, 8, 9, 10)
        assertTrue(list.addOrRemove(add = false, value = 3))
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Add
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addOrRemove(add = true, value = 18))
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not Remove
        val list3 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList3 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list3.addOrRemove(add = false, value = 11))
        assertArrayEquals(expectedList3.toTypedArray(), list3.toTypedArray())

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addOrRemove(add = true, value = 5))
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())
    }

}