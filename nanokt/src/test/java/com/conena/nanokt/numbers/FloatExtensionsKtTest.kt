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

class FloatExtensionsKtTest {

    @Test
    fun zeroAsNull() {
        Assert.assertNull(0.0f.zeroAsNull())
        Assert.assertEquals(0.1f, 0.1f.zeroAsNull())
        Assert.assertEquals(-0.1f, (-0.1f).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Float? = null
        Assert.assertEquals(0.0f, nullValue.nullAsZero())
        Assert.assertEquals(0.0f, 0.0f.nullAsZero())
        Assert.assertEquals(0.1f, 0.1f.nullAsZero())
        Assert.assertEquals(-0.1f, (-0.1f).nullAsZero())
    }

    @Test
    fun negativeAsNull() {
        Assert.assertEquals(0.0f, 0.0f.negativeAsNull())
        Assert.assertEquals(0.1f, 0.1f.negativeAsNull())
        Assert.assertNull((-0.1f).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        Assert.assertEquals(0.0f, 0.0f.negativeAsZero())
        Assert.assertEquals(0.1f, 0.1f.negativeAsZero())
        Assert.assertEquals(0.0f, (-0.1f).negativeAsZero())
    }

    @Test
    fun positiveAsNull() {
        Assert.assertEquals(0.0f, 0.0f.positiveAsNull())
        Assert.assertNull(0.1f.positiveAsNull())
        Assert.assertEquals(-0.1f, (-0.1f).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        Assert.assertEquals(0.0f, 0.0f.positiveAsZero())
        Assert.assertEquals(0.0f, 0.1f.positiveAsZero())
        Assert.assertEquals(-0.1f, (-0.1f).positiveAsZero())
    }

    @Test
    fun isNegative() {
        Assert.assertFalse(0.0f.isNegative())
        Assert.assertFalse(0.1f.isNegative())
        Assert.assertTrue((-0.1f).isNegative())
    }

    @Test
    fun isPositive() {
        Assert.assertFalse(0.0f.isPositive())
        Assert.assertTrue(0.1f.isPositive())
        Assert.assertFalse((-0.1f).isPositive())
    }

    @Test
    fun isZero() {
        Assert.assertTrue(0.0f.isZero())
        Assert.assertFalse(0.1f.isZero())
        Assert.assertFalse((-0.1f).isZero())
    }

    @Test
    fun isNotNegative() {
        Assert.assertTrue(0.0f.isNotNegative())
        Assert.assertTrue(0.1f.isNotNegative())
        Assert.assertFalse((-0.1f).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        Assert.assertTrue(0.0f.isNotPositive())
        Assert.assertFalse(0.1f.isNotPositive())
        Assert.assertTrue((-0.1f).isNotPositive())
    }

    @Test
    fun isNotZero() {
        Assert.assertFalse(0.0f.isNotZero())
        Assert.assertTrue(0.1f.isNotZero())
        Assert.assertTrue((-0.1f).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Float? = null
        Assert.assertTrue(nullValue.isNullOrNegative())
        Assert.assertFalse(0.0f.isNullOrNegative())
        Assert.assertFalse(0.1f.isNullOrNegative())
        Assert.assertTrue((-0.1f).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Float? = null
        Assert.assertTrue(nullValue.isNullOrPositive())
        Assert.assertFalse(0.0f.isNullOrPositive())
        Assert.assertTrue(0.1f.isNullOrPositive())
        Assert.assertFalse((-0.1f).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Float? = null
        Assert.assertTrue(nullValue.isNullOrZero())
        Assert.assertTrue(0.0f.isNullOrZero())
        Assert.assertFalse(0.1f.isNullOrZero())
        Assert.assertFalse((-0.1f).isNullOrZero())
    }

}