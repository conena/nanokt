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

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

private const val FLAG_1 = 1L
private const val FLAG_2 = 2L
private const val FLAG_4 = 4L
private const val FLAG_8 = 8L
private const val FLAG_16 = 16L

class LongExtensionsKtTest {

    @Suppress("KotlinConstantConditions")
    @Test
    fun isFlagSet() {
        var flags = 0L
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
        var flags = 0L
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
        var flags = 0b11111L
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
        val exceptedFlags = arrayOf(0b10L, 0b100L, 0b10000L, 0b100000L)
        val resolvedFlags = 0b110110L.getBitFlags()
        assertEquals(exceptedFlags.size, resolvedFlags.size)
        assertContentEquals(exceptedFlags, resolvedFlags)
    }

    @Test
    fun zeroAsNull() {
        assertNull(0L.zeroAsNull())
        assertEquals(1L, 1L.zeroAsNull())
        assertEquals(-1L, (-1L).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Long? = null
        assertEquals(0L, nullValue.nullAsZero())
        assertEquals(0L, 0L.nullAsZero())
        assertEquals(1L, 1L.nullAsZero())
        assertEquals(-1L, (-1L).nullAsZero())
    }

    @Test
    fun negativeAsNull() {
        assertEquals(0L, 0L.negativeAsNull())
        assertEquals(1L, 1L.negativeAsNull())
        assertNull((-1L).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        assertEquals(0L, 0L.negativeAsZero())
        assertEquals(1L, 1L.negativeAsZero())
        assertEquals(0L, (-1L).negativeAsZero())
    }

    @Test
    fun positiveAsNull() {
        assertEquals(0L, 0L.positiveAsNull())
        assertNull(1L.positiveAsNull())
        assertEquals(-1L, (-1L).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        assertEquals(0L, 0L.positiveAsZero())
        assertEquals(0L, 1L.positiveAsZero())
        assertEquals(-1L, (-1L).positiveAsZero())
    }

    @Test
    fun isNegative() {
        assertFalse(0L.isNegative())
        assertFalse(1L.isNegative())
        assertTrue((-1L).isNegative())
    }

    @Test
    fun isPositive() {
        assertFalse(0L.isPositive())
        assertTrue(1L.isPositive())
        assertFalse((-1L).isPositive())
    }

    @Test
    fun isZero() {
        assertTrue(0L.isZero())
        assertFalse(1L.isZero())
        assertFalse((-1L).isZero())
    }

    @Test
    fun isNotNegative() {
        assertTrue(0L.isNotNegative())
        assertTrue(1L.isNotNegative())
        assertFalse((-1L).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        assertTrue(0L.isNotPositive())
        assertFalse(1L.isNotPositive())
        assertTrue((-1L).isNotPositive())
    }

    @Test
    fun isNotZero() {
        assertFalse(0L.isNotZero())
        assertTrue(1L.isNotZero())
        assertTrue((-1L).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0L.isNullOrNegative())
        assertFalse(1L.isNullOrNegative())
        assertTrue((-1L).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0L.isNullOrPositive())
        assertTrue(1L.isNullOrPositive())
        assertFalse((-1L).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Long? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0L.isNullOrZero())
        assertFalse(1L.isNullOrZero())
        assertFalse((-1L).isNullOrZero())
    }
}