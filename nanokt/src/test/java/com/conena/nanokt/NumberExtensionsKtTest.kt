package com.conena.nanokt

import org.junit.Assert.*

import org.junit.Test

class NumberExtensionsKtTest {

    @Test
    fun `zeroAsNull - Int`() {
        val nullValue: Int? = null
        assertNull(nullValue.zeroAsNull())
        assertNull(0.zeroAsNull())
        assertEquals(1, 1.zeroAsNull())
        assertEquals(-1, (-1).zeroAsNull())
    }

    @Test
    fun `nullAsZero - Int`() {
        val nullValue: Int? = null
        assertEquals(0, nullValue.nullAsZero())
        assertEquals(0, 0.nullAsZero())
        assertEquals(1, 1.nullAsZero())
        assertEquals(-1, (-1).nullAsZero())
    }

    @Test
    fun `negativeAsNull - Int`() {
        val nullValue: Int? = null
        assertNull(nullValue.negativeAsNull())
        assertEquals(0, 0.negativeAsNull())
        assertEquals(1, 1.negativeAsNull())
        assertNull((-1).negativeAsNull())
    }

    @Test
    fun `negativeAsZero - Int`() {
        val nullValue: Int? = null
        assertNull(nullValue.negativeAsZero())
        assertEquals(0, 0.negativeAsZero())
        assertEquals(1, 1.negativeAsZero())
        assertEquals(0, (-1).negativeAsZero())
    }

    @Test
    fun `positiveAsNull - Int`() {
        val nullValue: Int? = null
        assertNull(nullValue.positiveAsNull())
        assertEquals(0, 0.positiveAsNull())
        assertNull(1.positiveAsNull())
        assertEquals(-1, (-1).positiveAsNull())
    }

    @Test
    fun `positiveAsZero - Int`() {
        val nullValue: Int? = null
        assertNull(nullValue.positiveAsZero())
        assertEquals(0, 0.positiveAsZero())
        assertEquals(0, 1.positiveAsZero())
        assertEquals(-1, (-1).positiveAsZero())
    }

    @Test
    fun `isNegative - Int`() {
        val nullValue: Int? = null
        assertFalse(nullValue.isNegative())
        assertFalse(0.isNegative())
        assertFalse(1.isNegative())
        assertTrue((-1).isNegative())
    }

    @Test
    fun `isPositive - Int`() {
        val nullValue: Int? = null
        assertFalse(nullValue.isPositive())
        assertFalse(0.isPositive())
        assertTrue(1.isPositive())
        assertFalse((-1).isPositive())
    }

    @Test
    fun `isZero - Int`() {
        val nullValue: Int? = null
        assertFalse(nullValue.isZero())
        assertTrue(0.isZero())
        assertFalse(1.isZero())
        assertFalse((-1).isZero())
    }

    @Test
    fun `zeroAsNull - Long`() {
        val nullValue: Long? = null
        assertNull(nullValue.zeroAsNull())
        assertNull(0L.zeroAsNull())
        assertEquals(1L, 1L.zeroAsNull())
        assertEquals(-1L, (-1L).zeroAsNull())
    }

    @Test
    fun `nullAsZero - Long`() {
        val nullValue: Long? = null
        assertEquals(0L, nullValue.nullAsZero())
        assertEquals(0L, 0L.nullAsZero())
        assertEquals(1L, 1L.nullAsZero())
        assertEquals(-1L, (-1L).nullAsZero())
    }

    @Test
    fun `negativeAsNull - Long`() {
        val nullValue: Long? = null
        assertNull(nullValue.negativeAsNull())
        assertEquals(0L, 0L.negativeAsNull())
        assertEquals(1L, 1L.negativeAsNull())
        assertNull((-1L).negativeAsNull())
    }

    @Test
    fun `negativeAsZero - Long`() {
        val nullValue: Long? = null
        assertNull(nullValue.negativeAsZero())
        assertEquals(0L, 0L.negativeAsZero())
        assertEquals(1L, 1L.negativeAsZero())
        assertEquals(0L, (-1L).negativeAsZero())
    }

    @Test
    fun `positiveAsNull - Long`() {
        val nullValue: Long? = null
        assertNull(nullValue.positiveAsNull())
        assertEquals(0L, 0L.positiveAsNull())
        assertNull(1L.positiveAsNull())
        assertEquals(-1L, (-1L).positiveAsNull())
    }

    @Test
    fun `positiveAsZero - Long`() {
        val nullValue: Long? = null
        assertNull(nullValue.positiveAsZero())
        assertEquals(0L, 0L.positiveAsZero())
        assertEquals(0L, 1L.positiveAsZero())
        assertEquals(-1L, (-1L).positiveAsZero())
    }

    @Test
    fun `isNegative - Long`() {
        val nullValue: Long? = null
        assertFalse(nullValue.isNegative())
        assertFalse(0L.isNegative())
        assertFalse(1L.isNegative())
        assertTrue((-1L).isNegative())
    }

    @Test
    fun `isPositive - Long`() {
        val nullValue: Long? = null
        assertFalse(nullValue.isPositive())
        assertFalse(0L.isPositive())
        assertTrue(1L.isPositive())
        assertFalse((-1L).isPositive())
    }

    @Test
    fun `isZero - Long`() {
        val nullValue: Long? = null
        assertFalse(nullValue.isZero())
        assertTrue(0L.isZero())
        assertFalse(1L.isZero())
        assertFalse((-1L).isZero())
    }
}