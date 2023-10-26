package com.conena.nanokt.jvm.util.concurrent.atomic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.atomic.AtomicLong

class AtomicLongExtensionsKtTest {

    @Test
    fun setValue() {
        val atomicLong = AtomicLong(0L)
        var delegatedLong by atomicLong
        delegatedLong = 1L
        assertEquals(1L, atomicLong.get())
    }

    @Test
    fun getValue() {
        val atomicLong = AtomicLong(0L)
        val delegatedLong by atomicLong
        assertEquals(0L, delegatedLong)
        atomicLong.set(1L)
        assertEquals(1L, delegatedLong)
    }

}