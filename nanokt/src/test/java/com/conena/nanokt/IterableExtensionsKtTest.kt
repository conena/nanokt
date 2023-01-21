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

import org.junit.Assert.*

import org.junit.Test

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

}