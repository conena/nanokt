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

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class IteratorExtensionsKtTest {

    @Test
    fun removeIf() {
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val expectedList = listOf(1, 3, 5, 7, 9)
        val iterator = list.iterator()
        while (iterator.hasNext()) iterator.removeIf(condition = iterator.next() % 2 == 0)
        assertArrayEquals(expectedList.toTypedArray(), list.toTypedArray())
    }

    @Test
    fun nextOrNull() {
        val iterator = listOf("Test").iterator()
        assertEquals("Test", iterator.nextOrNull())
        assertNull(iterator.nextOrNull())
    }

}