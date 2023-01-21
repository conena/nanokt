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

package com.conena.nanokt.jvm

import org.junit.Assert.*

import org.junit.Test
import java.io.Closeable
import java.io.IOException

class ClosableExtensionsKtTest {

    class MockClosable : Closeable {

        var closed: Boolean = false

        override fun close() {
            closed = true
            throw IOException()
        }

    }

    @Test
    fun closeSilent() {
        val closable = MockClosable()
        assertFalse(closable.closed)
        closable.closeSilent()
        assertTrue(closable.closed)
    }

    @Test
    fun closeCloseablesSilent() {
        val closable = MockClosable()
        assertFalse(closable.closed)
        closeCloseablesSilent(closable)
        assertTrue(closable.closed)
    }

    @Test
    fun testCloseCloseablesSilent() {
        val closable = MockClosable()
        val closable2 = MockClosable()
        val closable3 = MockClosable()
        assertFalse(closable.closed)
        assertFalse(closable2.closed)
        assertFalse(closable3.closed)
        closeCloseablesSilent(closable, closable2, null, closable3)
        assertTrue(closable.closed)
        assertTrue(closable2.closed)
        assertTrue(closable3.closed)
    }

}