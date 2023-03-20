/*
 * Copyright (C) 2023 Fabian Andera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.conena.nanokt

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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
    fun removeIfAndGet() {
        // Remove from list
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedRemovedList = listOf(6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5)
        assertArrayEquals(expectedRemovedList.toTypedArray(), list.removeIfAndGet { it > 5 }.toTypedArray())
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Remove from set
        val set = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedRemovedSet = mutableSetOf("CDE", "DEF", "EFG")
        val expectedSet = mutableSetOf("ABC", "BCD", "FGH")
        assertArrayEquals(expectedRemovedSet.toTypedArray(), set.removeIfAndGet { it.contains('E') }.toTypedArray())
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())

        // Do not remove from list
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertTrue(list2.removeIfAndGet { it > 10 }.isEmpty())
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not remove from set
        val set2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedSet2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        assertTrue(set2.removeIfAndGet { it.contains('I') }.isEmpty())
        assertArrayEquals(expectedSet2.toTypedArray(), set2.toTypedArray())
    }

    @Test
    fun addOrRemove() {
        // Remove
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 4, 5, 6, 7, 8, 9, 10)
        assertTrue(list.addOrRemove(add = false, element = 3))
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Add
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addOrRemove(add = true, element = 18))
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not Remove
        val list3 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList3 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list3.addOrRemove(add = false, element = 11))
        assertArrayEquals(expectedList3.toTypedArray(), list3.toTypedArray())

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addOrRemove(add = true, element = 5))
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())
    }

    @Test
    fun addNotNull() {
        // Add null
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list.addNotNull(element = null))
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Add not null
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addNotNull(element = 18))
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addNotNull(element = 5))
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())
    }

    @Test
    fun addAllNotNull() {
        // Add null
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list.addAllNotNull(elements = listOf(null, null)))
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())

        // Add not null
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addAllNotNull(elements = listOf(null, 18, null)))
        assertArrayEquals(expectedList2.toTypedArray(), list2.toTypedArray())

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addAllNotNull(elements  = listOf(5, null, 10, null)))
        assertArrayEquals(expectedSet.toTypedArray(), set.toTypedArray())
    }

}