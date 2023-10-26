package com.conena.nanokt.jvm.util.concurrent.atomic

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class AtomicIntegerExtensionsKtTest {

    @Test
    fun setValue() {
        val atomicInteger = AtomicInteger(0)
        var delegatedInteger by atomicInteger
        delegatedInteger = 1
        assertEquals(1, atomicInteger.get())
    }

    @Test
    fun getValue() {
        val atomicInteger = AtomicInteger(0)
        val delegatedInteger by atomicInteger
        assertEquals(0, delegatedInteger)
        atomicInteger.set(1)
        assertEquals(1, delegatedInteger)
    }

}