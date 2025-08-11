package com.conena.nanokt.jvm.util.concurrent.atomic

import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals

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