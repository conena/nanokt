package com.conena.nanokt

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