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

class FloatExtensionsKtTest {

    @Test
    fun zeroAsNull() {
        assertNull(0.0f.zeroAsNull())
        assertEquals(0.1f, 0.1f.zeroAsNull())
        assertEquals(-0.1f, (-0.1f).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Float? = null
        assertEquals(0.0f, nullValue.nullAsZero())
        assertEquals(0.0f, 0.0f.nullAsZero())
        assertEquals(0.1f, 0.1f.nullAsZero())
        assertEquals(-0.1f, (-0.1f).nullAsZero())
    }

    @Test
    fun negativeAsNull() {
        assertEquals(0.0f, 0.0f.negativeAsNull())
        assertEquals(0.1f, 0.1f.negativeAsNull())
        assertNull((-0.1f).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        assertEquals(0.0f, 0.0f.negativeAsZero())
        assertEquals(0.1f, 0.1f.negativeAsZero())
        assertEquals(0.0f, (-0.1f).negativeAsZero())
    }

    @Test
    fun positiveAsNull() {
        assertEquals(0.0f, 0.0f.positiveAsNull())
        assertNull(0.1f.positiveAsNull())
        assertEquals(-0.1f, (-0.1f).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        assertEquals(0.0f, 0.0f.positiveAsZero())
        assertEquals(0.0f, 0.1f.positiveAsZero())
        assertEquals(-0.1f, (-0.1f).positiveAsZero())
    }

    @Test
    fun isNegative() {
        assertFalse(0.0f.isNegative())
        assertFalse(0.1f.isNegative())
        assertTrue((-0.1f).isNegative())
    }

    @Test
    fun isPositive() {
        assertFalse(0.0f.isPositive())
        assertTrue(0.1f.isPositive())
        assertFalse((-0.1f).isPositive())
    }

    @Test
    fun isZero() {
        assertTrue(0.0f.isZero())
        assertFalse(0.1f.isZero())
        assertFalse((-0.1f).isZero())
    }

    @Test
    fun isNotNegative() {
        assertTrue(0.0f.isNotNegative())
        assertTrue(0.1f.isNotNegative())
        assertFalse((-0.1f).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        assertTrue(0.0f.isNotPositive())
        assertFalse(0.1f.isNotPositive())
        assertTrue((-0.1f).isNotPositive())
    }

    @Test
    fun isNotZero() {
        assertFalse(0.0f.isNotZero())
        assertTrue(0.1f.isNotZero())
        assertTrue((-0.1f).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Float? = null
        assertTrue(nullValue.isNullOrNegative())
        assertFalse(0.0f.isNullOrNegative())
        assertFalse(0.1f.isNullOrNegative())
        assertTrue((-0.1f).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Float? = null
        assertTrue(nullValue.isNullOrPositive())
        assertFalse(0.0f.isNullOrPositive())
        assertTrue(0.1f.isNullOrPositive())
        assertFalse((-0.1f).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Float? = null
        assertTrue(nullValue.isNullOrZero())
        assertTrue(0.0f.isNullOrZero())
        assertFalse(0.1f.isNullOrZero())
        assertFalse((-0.1f).isNullOrZero())
    }

}