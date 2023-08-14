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

import org.junit.Assert
import org.junit.Test

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
        Assert.assertFalse(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags or FLAG_1
        Assert.assertTrue(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags or FLAG_16
        Assert.assertTrue(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertTrue(flags.isFlagSet(FLAG_16))
    }


    @Suppress("KotlinConstantConditions")
    @Test
    fun addFlag() {
        var flags = 0L
        flags = flags.addFlag(FLAG_1)
        Assert.assertTrue(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags.addFlag(FLAG_2)
        Assert.assertTrue(flags.isFlagSet(FLAG_1))
        Assert.assertTrue(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertFalse(flags.isFlagSet(FLAG_16))
        flags = flags.addFlag(FLAG_16)
        Assert.assertTrue(flags.isFlagSet(FLAG_1))
        Assert.assertTrue(flags.isFlagSet(FLAG_2))
        Assert.assertFalse(flags.isFlagSet(FLAG_4))
        Assert.assertFalse(flags.isFlagSet(FLAG_8))
        Assert.assertTrue(flags.isFlagSet(FLAG_16))
    }

    @Test
    fun removeFlag() {
        var flags = 0b11111L
        flags = flags.removeFlag(FLAG_1)
        Assert.assertFalse(flags.isFlagSet(FLAG_1))
        Assert.assertTrue(flags.isFlagSet(FLAG_2))
        Assert.assertTrue(flags.isFlagSet(FLAG_4))
        Assert.assertTrue(flags.isFlagSet(FLAG_8))
        Assert.assertTrue(flags.isFlagSet(FLAG_16))
        flags = flags.removeFlag(FLAG_2)
        Assert.assertFalse(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertTrue(flags.isFlagSet(FLAG_4))
        Assert.assertTrue(flags.isFlagSet(FLAG_8))
        Assert.assertTrue(flags.isFlagSet(FLAG_16))
        flags = flags.removeFlag(FLAG_16)
        Assert.assertFalse(flags.isFlagSet(FLAG_1))
        Assert.assertFalse(flags.isFlagSet(FLAG_2))
        Assert.assertTrue(flags.isFlagSet(FLAG_4))
        Assert.assertTrue(flags.isFlagSet(FLAG_8))
        Assert.assertFalse(flags.isFlagSet(FLAG_16))
    }

    @Test
    fun getBitFlags() {
        val exceptedFlags = arrayOf(0b10L, 0b100L, 0b10000L, 0b100000L)
        val resolvedFlags = 0b110110L.getBitFlags()
        Assert.assertEquals(exceptedFlags.size, resolvedFlags.size)
        Assert.assertArrayEquals(exceptedFlags, resolvedFlags)
    }

    @Test
    fun zeroAsNull() {
        Assert.assertNull(0L.zeroAsNull())
        Assert.assertEquals(1L, 1L.zeroAsNull())
        Assert.assertEquals(-1L, (-1L).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Long? = null
        Assert.assertEquals(0L, nullValue.nullAsZero())
        Assert.assertEquals(0L, 0L.nullAsZero())
        Assert.assertEquals(1L, 1L.nullAsZero())
        Assert.assertEquals(-1L, (-1L).nullAsZero())
    }

    @Test
    fun negativeAsNull() {
        Assert.assertEquals(0L, 0L.negativeAsNull())
        Assert.assertEquals(1L, 1L.negativeAsNull())
        Assert.assertNull((-1L).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        Assert.assertEquals(0L, 0L.negativeAsZero())
        Assert.assertEquals(1L, 1L.negativeAsZero())
        Assert.assertEquals(0L, (-1L).negativeAsZero())
    }

    @Test
    fun positiveAsNull() {
        Assert.assertEquals(0L, 0L.positiveAsNull())
        Assert.assertNull(1L.positiveAsNull())
        Assert.assertEquals(-1L, (-1L).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        Assert.assertEquals(0L, 0L.positiveAsZero())
        Assert.assertEquals(0L, 1L.positiveAsZero())
        Assert.assertEquals(-1L, (-1L).positiveAsZero())
    }

    @Test
    fun isNegative() {
        Assert.assertFalse(0L.isNegative())
        Assert.assertFalse(1L.isNegative())
        Assert.assertTrue((-1L).isNegative())
    }

    @Test
    fun isPositive() {
        Assert.assertFalse(0L.isPositive())
        Assert.assertTrue(1L.isPositive())
        Assert.assertFalse((-1L).isPositive())
    }

    @Test
    fun isZero() {
        Assert.assertTrue(0L.isZero())
        Assert.assertFalse(1L.isZero())
        Assert.assertFalse((-1L).isZero())
    }

    @Test
    fun isNotNegative() {
        Assert.assertTrue(0L.isNotNegative())
        Assert.assertTrue(1L.isNotNegative())
        Assert.assertFalse((-1L).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        Assert.assertTrue(0L.isNotPositive())
        Assert.assertFalse(1L.isNotPositive())
        Assert.assertTrue((-1L).isNotPositive())
    }

    @Test
    fun isNotZero() {
        Assert.assertFalse(0L.isNotZero())
        Assert.assertTrue(1L.isNotZero())
        Assert.assertTrue((-1L).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Long? = null
        Assert.assertTrue(nullValue.isNullOrNegative())
        Assert.assertFalse(0L.isNullOrNegative())
        Assert.assertFalse(1L.isNullOrNegative())
        Assert.assertTrue((-1L).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Long? = null
        Assert.assertTrue(nullValue.isNullOrPositive())
        Assert.assertFalse(0L.isNullOrPositive())
        Assert.assertTrue(1L.isNullOrPositive())
        Assert.assertFalse((-1L).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Long? = null
        Assert.assertTrue(nullValue.isNullOrZero())
        Assert.assertTrue(0L.isNullOrZero())
        Assert.assertFalse(1L.isNullOrZero())
        Assert.assertFalse((-1L).isNullOrZero())
    }
}