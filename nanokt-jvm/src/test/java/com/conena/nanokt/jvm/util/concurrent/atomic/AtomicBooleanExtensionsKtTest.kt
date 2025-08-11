package com.conena.nanokt.jvm.util.concurrent.atomic

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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