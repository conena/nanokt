package com.conena.nanokt.jvm

import org.junit.Assert.*

import org.junit.Test

class GlobalExtensionsKtTest {

    @Test
    fun toWeakReference() {
        val pair = "One" to 2
        assertSame(pair, pair.toWeakReference().get())
    }
}