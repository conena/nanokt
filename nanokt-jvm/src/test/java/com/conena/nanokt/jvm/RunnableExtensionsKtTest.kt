package com.conena.nanokt.jvm

import org.junit.Assert.*

import org.junit.Test

class RunnableExtensionsKtTest {

    @Test
    operator fun invoke() {
        var called = false
        val runnable = Runnable { called = true }
        assertFalse(called)
        runnable()
        assertTrue(called)
        called = false
        assertFalse(called)
        runnable.invoke()
        assertTrue(called)
    }
}