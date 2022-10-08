package com.conena.nanokt.jvm

import org.junit.Assert.*

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