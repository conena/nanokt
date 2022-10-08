package com.conena.nanokt

import org.junit.Assert.*

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

}