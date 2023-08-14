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

class IntExtensionsKtTest {

    @Suppress("KotlinConstantConditions")
    @Test
    fun isFlagSet() {
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
    fun addFlag() {
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
    fun removeFlag() {
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
    fun getBitFlags() {
        val exceptedFlags = arrayOf(0b10, 0b100, 0b10000, 0b100000)
        val resolvedFlags = 0b110110.getBitFlags()
        assertEquals(exceptedFlags.size, resolvedFlags.size)
        assertArrayEquals(exceptedFlags, resolvedFlags)
    }

    @Test
    fun zeroAsNull() {
        assertNull(0.zeroAsNull())
        assertEquals(1, 1.zeroAsNull())
        assertEquals(-1, (-1).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Int? = null
        assertEquals(0, nullValue.nullAsZero())
        assertEquals(0, 0.nullAsZero())
        assertEquals(1, 1.nullAsZero())
        assertEquals(-1, (-1).nullAsZero())
    }

    @Test
    fun negativeAsNull() {
        assertEquals(0, 0.negativeAsNull())
        assertEquals(1, 1.negativeAsNull())
        assertNull((-1).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        assertEquals(0, 0.negativeAsZero())
        assertEquals(1, 1.negativeAsZero())
        assertEquals(0, (-1).negativeAsZero())
    }

    @Test
    fun positiveAsNull() {
        assertEquals(0, 0.positiveAsNull())
        assertNull(1.positiveAsNull())
        assertEquals(-1, (-1).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        assertEquals(0, 0.positiveAsZero())
        assertEquals(0, 1.positiveAsZero())
        assertEquals(-1, (-1).positiveAsZero())
    }

    @Test
    fun isNegative() {
        assertFalse(0.isNegative())
        assertFalse(1.isNegative())
        assertTrue((-1).isNegative())
    }

    @Test
    fun isPositive() {
        assertFalse(0.isPositive())
        assertTrue(1.isPositive())
        assertFalse((-1).isPositive())
    }

    @Test
    fun isZero() {
        assertTrue(0.isZero())
        assertFalse(1.isZero())
        assertFalse((-1).isZero())
    }

    @Test
    fun isNotNegative() {
        assertTrue(0.isNotNegative())
        assertTrue(1.isNotNegative())
        assertFalse((-1).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        assertTrue(0.isNotPositive())
        assertFalse(1.isNotPositive())
        assertTrue((-1).isNotPositive())
    }

    @Test
    fun isNotZero() {
        assertFalse(0.isNotZero())
        assertTrue(1.isNotZero())
        assertTrue((-1).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0.isNullOrNegative())
        assertFalse(1.isNullOrNegative())
        assertTrue((-1).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0.isNullOrPositive())
        assertTrue(1.isNullOrPositive())
        assertFalse((-1).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Int? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0.isNullOrZero())
        assertFalse(1.isNullOrZero())
        assertFalse((-1).isNullOrZero())
    }

}