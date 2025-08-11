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

package com.conena.nanokt.collections

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

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
        val list1 = listOfInstanceOf<String>(
            "String",
            1L,
            1,
            1.0,
            0..1,
            "String2"
        )
        assertEquals(2, list1.size)
        val list2 = listOfInstanceOf<String>(1L, null, 2L, null, 3L)
        assertEquals(0, list2.size)
        val list3 =
            listOfInstanceOf<Long>(1L, null, 2L, null, 3L, null, 4)
        assertEquals(3, list3.size)
        val list4 = listOfInstanceOf<Long>(1L)
        assertEquals(1, list4.size)
        val list5 = listOfInstanceOf<Long>(1.0)
        assertEquals(0, list5.size)
    }

    @Test
    fun addOrRemove() {
        // Remove
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 4, 5, 6, 7, 8, 9, 10)
        assertTrue(list.addOrRemove(add = false, element = 3))
        assertContentEquals(expectedList, list)

        // Add
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addOrRemove(add = true, element = 18))
        assertContentEquals(expectedList2, list2)

        // Do not Remove
        val list3 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList3 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list3.addOrRemove(add = false, element = 11))
        assertContentEquals(expectedList3, list3)

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addOrRemove(add = true, element = 5))
        assertContentEquals(expectedSet.asIterable(), set.asIterable())
    }

    @Test
    fun addNotNull() {
        // Add null
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list.addNotNull(element = null))
        assertContentEquals(expectedList, list)

        // Add not null
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addNotNull(element = 18))
        assertContentEquals(expectedList2, list2)

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addNotNull(element = 5))
        assertContentEquals(expectedSet.asIterable(), set.asIterable())
    }

    @Test
    fun addAllNotNull() {
        // Add null
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(list.addAllNotNull(elements = listOf(null, null)))
        assertContentEquals(expectedList, list)

        // Add not null
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18)
        assertTrue(list2.addAllNotNull(elements = listOf(null, 18, null)))
        assertContentEquals(expectedList2, list2)

        // Do not add
        val set = mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedSet = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertFalse(set.addAllNotNull(elements  = listOf(5, null, 10, null)))
        assertContentEquals(expectedSet.asIterable(), set.asIterable())
    }

    @Test
    fun `removeFirstCompat - successfully removes and returns first element`() {
        val list = mutableListOf("a", "b", "c")
        val removedElement = list.removeFirstCompat()

        assertEquals("a", removedElement)
        assertEquals(listOf("b", "c"), list)
    }

    @Test
    fun `removeFirstCompat - list with one element becomes empty`() {
        val list = mutableListOf(2)
        val removedElement = list.removeFirstCompat()

        assertEquals(2, removedElement)
        assertTrue(list.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun `removeFirstCompat - throws exception on empty list`() {
        val emptyList = mutableListOf<Int>()
        emptyList.removeFirstCompat()
    }

    @Test
    fun `removeLastCompat - successfully removes and returns last element`() {
        val list = mutableListOf("a", "b", "c")
        val removedElement = list.removeLastCompat()

        assertEquals("c", removedElement)
        assertEquals(listOf("a", "b"), list)
    }

    @Test
    fun `removeLastCompat - list with one element becomes empty`() {
        val list = mutableListOf(2)
        val removedElement = list.removeLastCompat()

        assertEquals(2, removedElement)
        assertTrue(list.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun `removeLastCompat - throws exception on empty list`() {
        val emptyList = mutableListOf<Int>()
        emptyList.removeLastCompat()
    }

}