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

package com.conena.nanokt.text

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TextExtensionsKtTest {

    @Test
    fun emptyAsNull() {
        assertNull("".emptyAsNull())
        assertEquals("  ", "  ".emptyAsNull())
        assertEquals("Test", "Test".emptyAsNull())
    }

    @Test
    fun blankAsNull() {
        assertNull("".blankAsNull())
        assertNull("  ".blankAsNull())
        assertEquals("Test", "Test".blankAsNull())
    }

    @Test
    fun toStringBuilder() {
        assertEquals("Test", "Test".toStringBuilder().toString())
    }

    @Test
    fun addPrefix() {
        assertEquals("<!>Test", "Test".addPrefix("<!>"))
    }

    @Test
    fun addPrefix_char() {
        assertEquals("-Test", "Test".addPrefix('-'))
    }

    @Test
    fun addSuffix() {
        assertEquals("Test<!>", "Test".addSuffix("<!>"))
    }

    @Test
    fun addSuffix_char() {
        assertEquals("Test-", "Test".addSuffix('-'))
    }

    @Test
    fun addSurrounding() {
        assertEquals("``Test``", "Test".addSurrounding("``"))
    }

    @Test
    fun addSurrounding_char() {
        assertEquals("\"Test\"", "Test".addSurrounding('"'))
    }

    @Test
    fun indexOfOrNull_char() {
        assertEquals(2, "123456789".indexOfOrNull('3'))
        assertEquals(null, "123456789".indexOfOrNull('0'))
    }

    @Test
    fun lastIndexOfOrNull_char() {
        assertEquals(11, "123456789123456789".lastIndexOfOrNull('3'))
        assertEquals(null, "123456789123456789".lastIndexOfOrNull('0'))
    }

    @Test
    fun indexOfOrNull_string() {
        assertEquals(2, "123456789".indexOfOrNull("34"))
        assertEquals(null, "123456789".indexOfOrNull("90"))
    }

    @Test
    fun lastIndexOfOrNull_string() {
        assertEquals(11, "123456789123456789".lastIndexOfOrNull("34"))
        assertEquals(null, "123456789123456789".lastIndexOfOrNull("90"))
    }

    @Test
    fun isEmptyOrBlank() {
        assertTrue("".isEmptyOrBlank())
        assertTrue(" ".isEmptyOrBlank())
        assertTrue(System.lineSeparator().isEmptyOrBlank())
        assertFalse(",".isEmptyOrBlank())
    }

    @Test
    fun isNotEmptyOrBlank() {
        assertFalse("".isNotEmptyOrBlank())
        assertFalse(" ".isNotEmptyOrBlank())
        assertFalse(System.lineSeparator().isNotEmptyOrBlank())
        assertTrue(",".isNotEmptyOrBlank())
    }

}