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

    @Suppress("SpellCheckingInspection")
    @Test
    fun encodeUrl() {
        val encoded = "%21%22%23%24%25%26%27%28%29*%2B%2C-.%2F0123456789%3A%3B%3C%3D%3E%3F%40ABCDEFGHIJKLMOPQRSTUVWXYZ%5B%5C%5D%5E_%60abcdefghijklmnopqrstuvwxyz%7B%7C%7D%7E%C2%A1%C2%A2%C2%A3%C2%A4%C2%A5%C2%A6%C2%A7%C2%A8%C2%A9%C2%AA%C2%AB%C2%AC%C2%AE%C2%AF%C2%B0%C2%B1%C2%B2%C2%B3%C2%B4%C2%B5%C2%B6%C2%B7%C2%B8%C2%B9"
        val decoded = "!\"#\$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹"
        assertEquals(encoded, decoded.encodeUrl())
    }

    @Suppress("SpellCheckingInspection")
    @Test
    fun decodeUrl() {
        val encoded = "%21%22%23%24%25%26%27%28%29*%2B%2C-.%2F0123456789%3A%3B%3C%3D%3E%3F%40ABCDEFGHIJKLMOPQRSTUVWXYZ%5B%5C%5D%5E_%60abcdefghijklmnopqrstuvwxyz%7B%7C%7D%7E%C2%A1%C2%A2%C2%A3%C2%A4%C2%A5%C2%A6%C2%A7%C2%A8%C2%A9%C2%AA%C2%AB%C2%AC%C2%AE%C2%AF%C2%B0%C2%B1%C2%B2%C2%B3%C2%B4%C2%B5%C2%B6%C2%B7%C2%B8%C2%B9"
        val decoded = "!\"#\$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹"
        assertEquals(decoded, encoded.decodeUrl())
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