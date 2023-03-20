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

package com.conena.nanokt.jvm.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Test

class RegexExtensionsKtTest {

    @Test
    fun `toPatternOrNull - valid pattern`() {
        val pattern = "^test\$"
        assertEquals(pattern, pattern.toPatternOrNull()?.pattern())
    }

    @Test
    fun `toPatternOrNull - invalid pattern`() {
        val pattern = "("
        assertNull(pattern.toPatternOrNull())
    }

    @Test
    fun `toPatternOrNull - Invalid flag`() {
        assertThrows(IllegalArgumentException::class.java) {
            "^test\$".toPatternOrNull(flags = -1)
        }
    }

}