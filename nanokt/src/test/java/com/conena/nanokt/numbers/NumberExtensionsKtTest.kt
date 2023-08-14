/*
 * Copyright (C) 2023 Fabian Andera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.conena.nanokt.numbers

import org.junit.Assert.*

import org.junit.Test

private const val FLAG_1 = 1
private const val FLAG_2 = 2
private const val FLAG_4 = 4
private const val FLAG_8 = 8
private const val FLAG_16 = 16

class NumberExtensionsKtTest {

    @Suppress("KotlinConstantConditions")
    @Test
    fun `isFlagSet - Int`() {
        var flags = 0
        assertFalse(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags or FLAG_1
        assertTrue(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags or FLAG_16
        assertTrue(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertTrue(flags.isFlagSet(FLAG_16))
    }

    @Suppress("KotlinConstantConditions")
    @Test
    fun `addFlag - Int`() {
        var flags = 0
        flags = flags.addFlag(FLAG_1)
        assertTrue(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags.addFlag(FLAG_2)
        assertTrue(flags.isFlagSet(FLAG_1))
        assertTrue(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags.addFlag(FLAG_16)
        assertTrue(flags.isFlagSet(FLAG_1))
        assertTrue(flags.isFlagSet(FLAG_2))
        assertFalse(flags.isFlagSet(FLAG_4))
        assertFalse(flags.isFlagSet(FLAG_8))
        assertTrue(flags.isFlagSet(FLAG_16))
    }

    @Test
    fun `removeFlag - Int`() {
        var flags = 0b11111
        flags = flags.removeFlag(FLAG_1)
        assertFalse(flags.isFlagSet(FLAG_1))
        assertTrue(flags.isFlagSet(FLAG_2))
        assertTrue(flags.isFlagSet(FLAG_4))
        assertTrue(flags.isFlagSet(FLAG_8))
        assertTrue(flags.isFlagSet(FLAG_16))
        flags = flags.removeFlag(FLAG_2)
        assertFalse(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertTrue(flags.isFlagSet(FLAG_4))
        assertTrue(flags.isFlagSet(FLAG_8))
        assertTrue(flags.isFlagSet(FLAG_16))
        flags = flags.removeFlag(FLAG_16)
        assertFalse(flags.isFlagSet(FLAG_1))
        assertFalse(flags.isFlagSet(FLAG_2))
        assertTrue(flags.isFlagSet(FLAG_4))
        assertTrue(flags.isFlagSet(FLAG_8))
        assertFalse(flags.isFlagSet(FLAG_16))
    }

    @Test
    fun `getBitFlags - Int`() {
        val exceptedFlags = arrayOf(0b10, 0b100, 0b10000, 0b100000)
        val resolvedFlags = 0b110110.getBitFlags()
        assertEquals(exceptedFlags.size, resolvedFlags.size)
        assertArrayEquals(exceptedFlags, resolvedFlags)
    }

    @Suppress("KotlinConstantConditions")
    @Test
    fun `isFlagSet - Long`() {
        var flags = 0L
        assertFalse(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertFalse(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags or FLAG_1.toLong()
        assertTrue(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertFalse(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags or FLAG_16.toLong()
        assertTrue(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertTrue(flags.isFlagSet(FLAG_16.toLong()))
    }


    @Suppress("KotlinConstantConditions")
    @Test
    fun `addFlag - Long`() {
        var flags = 0L
        flags = flags.addFlag(FLAG_1.toLong())
        assertTrue(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertFalse(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags.addFlag(FLAG_2.toLong())
        assertTrue(flags.isFlagSet(FLAG_1.toLong()))
        assertTrue(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertFalse(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags.addFlag(FLAG_16.toLong())
        assertTrue(flags.isFlagSet(FLAG_1.toLong()))
        assertTrue(flags.isFlagSet(FLAG_2.toLong()))
        assertFalse(flags.isFlagSet(FLAG_4.toLong()))
        assertFalse(flags.isFlagSet(FLAG_8.toLong()))
        assertTrue(flags.isFlagSet(FLAG_16.toLong()))
    }

    @Test
    fun `removeFlag - Long`() {
        var flags = 0b11111L
        flags = flags.removeFlag(FLAG_1.toLong())
        assertFalse(flags.isFlagSet(FLAG_1.toLong()))
        assertTrue(flags.isFlagSet(FLAG_2.toLong()))
        assertTrue(flags.isFlagSet(FLAG_4.toLong()))
        assertTrue(flags.isFlagSet(FLAG_8.toLong()))
        assertTrue(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags.removeFlag(FLAG_2.toLong())
        assertFalse(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertTrue(flags.isFlagSet(FLAG_4.toLong()))
        assertTrue(flags.isFlagSet(FLAG_8.toLong()))
        assertTrue(flags.isFlagSet(FLAG_16.toLong()))
        flags = flags.removeFlag(FLAG_16.toLong())
        assertFalse(flags.isFlagSet(FLAG_1.toLong()))
        assertFalse(flags.isFlagSet(FLAG_2.toLong()))
        assertTrue(flags.isFlagSet(FLAG_4.toLong()))
        assertTrue(flags.isFlagSet(FLAG_8.toLong()))
        assertFalse(flags.isFlagSet(FLAG_16.toLong()))
    }

    @Test
    fun `getBitFlags - Long`() {
        val exceptedFlags = arrayOf(0b10L, 0b100L, 0b10000L, 0b100000L)
        val resolvedFlags = 0b110110L.getBitFlags()
        assertEquals(exceptedFlags.size, resolvedFlags.size)
        assertArrayEquals(exceptedFlags, resolvedFlags)
    }

    @Test
    fun `zeroAsNull - Int`() {
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
        assertEquals(0, 0.negativeAsNull())
        assertEquals(1, 1.negativeAsNull())
        assertNull((-1).negativeAsNull())
    }

    @Test
    fun `negativeAsZero - Int`() {
        assertEquals(0, 0.negativeAsZero())
        assertEquals(1, 1.negativeAsZero())
        assertEquals(0, (-1).negativeAsZero())
    }

    @Test
    fun `positiveAsNull - Int`() {
        assertEquals(0, 0.positiveAsNull())
        assertNull(1.positiveAsNull())
        assertEquals(-1, (-1).positiveAsNull())
    }

    @Test
    fun `positiveAsZero - Int`() {
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
    fun `isNotNegative - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNotNegative())
        assertTrue(0.isNotNegative())
        assertTrue(1.isNotNegative())
        assertFalse((-1).isNotNegative())
    }

    @Test
    fun `isNotPositive - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNotPositive())
        assertTrue(0.isNotPositive())
        assertFalse(1.isNotPositive())
        assertTrue((-1).isNotPositive())
    }

    @Test
    fun `isNotZero - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNotZero())
        assertFalse(0.isNotZero())
        assertTrue(1.isNotZero())
        assertTrue((-1).isNotZero())
    }

    @Test
    fun `isNullOrNegative - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0.isNullOrNegative())
        assertFalse(1.isNullOrNegative())
        assertTrue((-1).isNullOrNegative())
    }

    @Test
    fun `isNullOrPositive - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0.isNullOrPositive())
        assertTrue(1.isNullOrPositive())
        assertFalse((-1).isNullOrPositive())
    }

    @Test
    fun `isNullOrZero - Int`() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0.isNullOrZero())
        assertFalse(1.isNullOrZero())
        assertFalse((-1).isNullOrZero())
    }

    @Test
    fun `zeroAsNull - Long`() {
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
        assertEquals(0L, 0L.negativeAsNull())
        assertEquals(1L, 1L.negativeAsNull())
        assertNull((-1L).negativeAsNull())
    }

    @Test
    fun `negativeAsZero - Long`() {
        assertEquals(0L, 0L.negativeAsZero())
        assertEquals(1L, 1L.negativeAsZero())
        assertEquals(0L, (-1L).negativeAsZero())
    }

    @Test
    fun `positiveAsNull - Long`() {
        assertEquals(0L, 0L.positiveAsNull())
        assertNull(1L.positiveAsNull())
        assertEquals(-1L, (-1L).positiveAsNull())
    }

    @Test
    fun `positiveAsZero - Long`() {
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

    @Test
    fun `isNotNegative - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNotNegative())
        assertTrue(0L.isNotNegative())
        assertTrue(1L.isNotNegative())
        assertFalse((-1L).isNotNegative())
    }

    @Test
    fun `isNotPositive - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNotPositive())
        assertTrue(0L.isNotPositive())
        assertFalse(1L.isNotPositive())
        assertTrue((-1L).isNotPositive())
    }

    @Test
    fun `isNotZero - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNotZero())
        assertFalse(0L.isNotZero())
        assertTrue(1L.isNotZero())
        assertTrue((-1L).isNotZero())
    }

    @Test
    fun `isNullOrNegative - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0L.isNullOrNegative())
        assertFalse(1L.isNullOrNegative())
        assertTrue((-1L).isNullOrNegative())
    }

    @Test
    fun `isNullOrPositive - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0L.isNullOrPositive())
        assertTrue(1L.isNullOrPositive())
        assertFalse((-1L).isNullOrPositive())
    }

    @Test
    fun `isNullOrZero - Long`() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0L.isNullOrZero())
        assertFalse(1L.isNullOrZero())
        assertFalse((-1L).isNullOrZero())
    }
}