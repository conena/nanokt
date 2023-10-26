package com.conena.nanokt.jvm.util.concurrent.atomic

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean

class AtomicBooleanExtensionsKtTest {

    @Test
    fun setValue() {
        val atomicBoolean = AtomicBoolean(false)
        var delegatedBoolean by atomicBoolean
        delegatedBoolean = false
        assertFalse(atomicBoolean.get())
    }

    @Test
    fun getValue() {
        val atomicBoolean = AtomicBoolean(false)
        val delegatedBoolean by atomicBoolean
        assertFalse(delegatedBoolean)
        atomicBoolean.set(true)
        assertTrue(delegatedBoolean)
    }

}