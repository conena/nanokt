package com.conena.nanokt.jvm

import org.junit.Assert.*

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