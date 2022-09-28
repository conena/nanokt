package com.conena.nanokt

import org.junit.Assert.*
import org.junit.Test


class GlobalExtensionsKtTest {

    @Test
    fun `biLetNonNull - should invoke block`() {
        val firstVal = "Test"
        val secondVal = 2
        var invoked = false
        biLetNonNull(firstVal, secondVal) { first , second ->
            assertSame(firstVal, first)
            assertSame(secondVal, second)
            invoked = true
        }
        assertTrue(invoked)
    }

    @Test
    fun `biLetNonNull - should not invoke block`() {
        var invoked = false
        biLetNonNull("Test", null) { _,_ -> invoked = true }
        assertFalse(invoked)
        var invoked2 = false
        biLetNonNull(null, 1) { _,_ -> invoked2 = true }
        assertFalse(invoked2)
        var invoked3 = false
        biLetNonNull(null, null) { _,_ -> invoked3 = true }
        assertFalse(invoked3)
    }

    @Test
    fun `triLetNonNull - should invoke block`() {
        val firstVal = "Test"
        val secondVal = 2
        val thirdVal = 'T'
        var invoked = false
        triLetNonNull(firstVal, secondVal, thirdVal) { first , second, third ->
            assertSame(firstVal, first)
            assertSame(secondVal, second)
            assertSame(thirdVal, third)
            invoked = true
        }
        assertTrue(invoked)
    }

    @Test
    fun `triLetNonNull - should not invoke block`() {
        var invoked = false
        triLetNonNull("Test", 'S',null) { _,_,_ -> invoked = true }
        assertFalse(invoked)
        var invoked2 = false
        triLetNonNull(null, 'S',0) { _,_,_ -> invoked2 = true }
        assertFalse(invoked2)
        var invoked3 = false
        triLetNonNull(null, null,0) { _,_,_ -> invoked3 = true }
        assertFalse(invoked3)
        var invoked4 = false
        triLetNonNull(null, null,null) { _,_,_ -> invoked4 = true }
        assertFalse(invoked4)
    }

    @Test
    fun `cast - no exception`() {
        val value: CharSequence = "Test"
        assertSame(value, value.cast<String>())
    }

    @Test
    fun `cast - exception`() {
        val value: CharSequence = "Test"
        assertThrows(ClassCastException::class.java) {
            value.cast<Long>()
        }
    }

    @Test
    fun `castOrNull - cast works`() {
        val value: CharSequence = "Test"
        assertSame(value, value.castOrNull<String>())
    }

    @Test
    fun `castOrNull - cast does not work`() {
        val value: CharSequence = "Test"
        assertNull(value.castOrNull<Long>())
    }

    @Test
    fun `ifNull - value parameter`() {
        assertEquals("Hello", "Hello".ifNull("Test"))
        assertEquals("Test", null.ifNull("Test"))
    }

    @Test
    fun `ifNull - function parameter`() {
        var called = false
        assertEquals("Hello", "Hello".ifNull {
            called = true
            "Test"
        })
        assertFalse(called)
        assertEquals("Test", null.ifNull {
            called = true
            "Test"
        })
        assertTrue(called)
    }

    @Test
    fun `runSilent - complete without exception`() {
        var completed = false
        runSilent {
            completed = true
        }
        assertTrue(completed)
    }


    @Test
    fun `runSilent - complete with exception`() {
        var completed = false
        runSilent {
            throw Throwable()
            @Suppress("UNREACHABLE_CODE")
            completed = true
        }
        @Suppress("KotlinConstantConditions")
        assertFalse(completed)
    }

    @Test
    fun `runSilentAndGet - complete without exception`() {
        assertEquals(true, runSilentAndGet {
            true
        })
    }

    @Test
    fun `runSilentAndGet - complete with exception`() {
        assertNull(runSilentAndGet {
            throw Throwable()
            @Suppress("UNREACHABLE_CODE")
            true
        })
    }

    @Test
    fun applySilent() {
        val test = "test"
        val result = test.applySilent {
            assertSame(test, this)
            throw Throwable()
        }
        assertSame(test, result)
    }

    @Test
    fun alsoSilent() {
        val test = "test"
        val result = test.alsoSilent {
            assertSame(test, it)
            throw Throwable()
        }
        assertSame(test, result)
    }

}