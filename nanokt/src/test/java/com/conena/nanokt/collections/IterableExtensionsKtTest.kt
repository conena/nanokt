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
import kotlin.test.assertTrue

class IterableExtensionsKtTest {

    private val testList = listOf(
        "String1",
        "String2",
        "String3",
        "String4",
        "String1",
        "String2",
        "String3",
        "String4"
    )

    @Test
    fun indexOfOrNull() {
        assertEquals(1, testList.indexOfOrNull(testList[1]))
        assertEquals(null, testList.indexOfOrNull("String0"))
    }

    @Test
    fun indexOfFirstOrNull() {
        assertEquals(3, testList.indexOfFirstOrNull { it.endsWith("4") })
        assertEquals(null, testList.indexOfFirstOrNull { it.endsWith("5") })
    }

    @Test
    fun lastIndexOfOrNull() {
        assertEquals(5, testList.lastIndexOfOrNull(testList[5]))
        assertEquals(null, testList.lastIndexOfOrNull("String0"))
    }

    @Test
    fun indexOfLastOrNull() {
        assertEquals(7, testList.indexOfLastOrNull { it.endsWith("4") })
        assertEquals(null, testList.indexOfLastOrNull { it.endsWith("5") })
    }

    @Test
    fun removeAllAndGet() {
        // Remove from list
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedRemovedList = listOf(6, 7, 8, 9, 10)
        val expectedList = listOf(1, 2, 3, 4, 5)
        assertContentEquals(expectedRemovedList, list.removeAllAndGet { it > 5 })
        assertContentEquals(expectedList, list)

        // Remove from set
        val set = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedRemovedSet = mutableSetOf("CDE", "DEF", "EFG")
        val expectedSet = mutableSetOf("ABC", "BCD", "FGH")
        assertContentEquals(expectedRemovedSet, set.removeAllAndGet { it.contains('E') })
        assertContentEquals(expectedSet.asIterable(), set.asIterable())

        // Do not remove from list
        val list2 = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertTrue(list2.removeAllAndGet { it > 10 }.isEmpty())
        assertContentEquals(expectedList2, list2)

        // Do not remove from set
        val set2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        val expectedSet2 = mutableSetOf("ABC", "BCD", "CDE", "DEF", "EFG", "FGH")
        assertTrue(set2.removeAllAndGet { it.contains('I') }.isEmpty())
        assertContentEquals(expectedSet2.asIterable(), set2.asIterable())
    }

    @Test
    fun toArrayList() {
        val arrayList = testList.toArrayList()
        assertContentEquals(testList, arrayList)
        // Do not modify original list
        arrayList.clear()
        assertTrue(arrayList.isEmpty())
        assertFalse(testList.isEmpty())
    }

}