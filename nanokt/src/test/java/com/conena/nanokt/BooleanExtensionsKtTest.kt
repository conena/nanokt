package com.conena.nanokt

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BooleanExtensionsKtTest {

    @Test
    fun ternary() {
        assertEquals("One", true.ternary("One", "Two"))
        assertEquals("Two", false.ternary("One", "Two"))
        assertEquals("One", true.ternary("One", null))
        assertNull(false.ternary("One", null))
    }

}