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
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DoubleExtensionsKtTest {

    @Test
    fun zeroAsNull() {
        assertNull(0.0.zeroAsNull())
        assertEquals(0.1, 0.1.zeroAsNull())
        assertEquals(-0.1, (-0.1).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Double? = null
        assertEquals(0.0, nullValue.nullAsZero(), 0.0)
        assertEquals(0.0, 0.0.nullAsZero(), 0.0)
        assertEquals(0.1, 0.1.nullAsZero(), 0.0)
        assertEquals(-0.1, (-0.1).nullAsZero(), 0.0)
    }

    @Test
    fun negativeAsNull() {
        assertEquals(0.0, 0.0.negativeAsNull())
        assertEquals(0.1, 0.1.negativeAsNull())
        assertNull((-0.1).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        assertEquals(0.0, 0.0.negativeAsZero(), 0.0)
        assertEquals(0.1, 0.1.negativeAsZero(), 0.0)
        assertEquals(0.0, (-0.1).negativeAsZero(), 0.0)
    }

    @Test
    fun positiveAsNull() {
        assertEquals(0.0, 0.0.positiveAsNull())
        assertNull(0.1.positiveAsNull())
        assertEquals(-0.1, (-0.1).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        assertEquals(0.0, 0.0.positiveAsZero(), 0.0)
        assertEquals(0.0, 0.1.positiveAsZero(), 0.0)
        assertEquals(-0.1, (-0.1).positiveAsZero(), 0.0)
    }

    @Test
    fun isNegative() {
        assertFalse(0.0.isNegative())
        assertFalse(0.1.isNegative())
        assertTrue((-0.1).isNegative())
    }

    @Test
    fun isPositive() {
        assertFalse(0.0.isPositive())
        assertTrue(0.1.isPositive())
        assertFalse((-0.1).isPositive())
    }

    @Test
    fun isZero() {
        assertTrue(0.0.isZero())
        assertFalse(0.1.isZero())
        assertFalse((-0.1).isZero())
    }

    @Test
    fun isNotNegative() {
        assertTrue(0.0.isNotNegative())
        assertTrue(0.1.isNotNegative())
        assertFalse((-0.1).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        assertTrue(0.0.isNotPositive())
        assertFalse(0.1.isNotPositive())
        assertTrue((-0.1).isNotPositive())
    }

    @Test
    fun isNotZero() {
        assertFalse(0.0.isNotZero())
        assertTrue(0.1.isNotZero())
        assertTrue((-0.1).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Double? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0.0.isNullOrNegative())
        assertFalse(0.1.isNullOrNegative())
        assertTrue((-0.1).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Double? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0.0.isNullOrPositive())
        assertTrue(0.1.isNullOrPositive())
        assertFalse((-0.1).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Double? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0.0.isNullOrZero())
        assertFalse(0.1.isNullOrZero())
        assertFalse((-0.1).isNullOrZero())
    }

}