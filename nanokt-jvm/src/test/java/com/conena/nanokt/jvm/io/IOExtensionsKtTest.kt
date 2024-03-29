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

package com.conena.nanokt.jvm.io

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.io.BufferedReader
import java.io.IOException

class IOExtensionsKtTest {

    private class MockBufferedReader(
        val ready: Boolean,
        val line: String?
    ) : BufferedReader("".reader()) {

        override fun ready() = ready

        override fun readLine(): String? {
            if (!ready) {
                throw IOException("No ready!")
            }
            return line
        }

    }

    @Test
    fun readLineIfReady() {
        assertEquals(
            "Test",
            MockBufferedReader(ready = true, line = "Test").readLineIfReady()
        )
        assertNull(MockBufferedReader(ready = true, line = null).readLineIfReady())
        assertNull(MockBufferedReader(ready = false, line = "Test").readLineIfReady())
        assertNull(MockBufferedReader(ready = false, line = null).readLineIfReady())
    }
}