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

class DoubleExtensionsKtTest {

    @Test
    fun zeroAsNull() {
        Assert.assertNull(0.0.zeroAsNull())
        Assert.assertEquals(0.1, 0.1.zeroAsNull())
        Assert.assertEquals(-0.1, (-0.1).zeroAsNull())
    }

    @Test
    fun nullAsZero() {
        val nullValue: Double? = null
        Assert.assertEquals(0.0, nullValue.nullAsZero(), 0.0)
        Assert.assertEquals(0.0, 0.0.nullAsZero(), 0.0)
        Assert.assertEquals(0.1, 0.1.nullAsZero(), 0.0)
        Assert.assertEquals(-0.1, (-0.1).nullAsZero(), 0.0)
    }

    @Test
    fun negativeAsNull() {
        Assert.assertEquals(0.0, 0.0.negativeAsNull())
        Assert.assertEquals(0.1, 0.1.negativeAsNull())
        Assert.assertNull((-0.1).negativeAsNull())
    }

    @Test
    fun negativeAsZero() {
        Assert.assertEquals(0.0, 0.0.negativeAsZero(), 0.0)
        Assert.assertEquals(0.1, 0.1.negativeAsZero(), 0.0)
        Assert.assertEquals(0.0, (-0.1).negativeAsZero(), 0.0)
    }

    @Test
    fun positiveAsNull() {
        Assert.assertEquals(0.0, 0.0.positiveAsNull())
        Assert.assertNull(0.1.positiveAsNull())
        Assert.assertEquals(-0.1, (-0.1).positiveAsNull())
    }

    @Test
    fun positiveAsZero() {
        Assert.assertEquals(0.0, 0.0.positiveAsZero(), 0.0)
        Assert.assertEquals(0.0, 0.1.positiveAsZero(), 0.0)
        Assert.assertEquals(-0.1, (-0.1).positiveAsZero(), 0.0)
    }

    @Test
    fun isNegative() {
        Assert.assertFalse(0.0.isNegative())
        Assert.assertFalse(0.1.isNegative())
        Assert.assertTrue((-0.1).isNegative())
    }

    @Test
    fun isPositive() {
        Assert.assertFalse(0.0.isPositive())
        Assert.assertTrue(0.1.isPositive())
        Assert.assertFalse((-0.1).isPositive())
    }

    @Test
    fun isZero() {
        Assert.assertTrue(0.0.isZero())
        Assert.assertFalse(0.1.isZero())
        Assert.assertFalse((-0.1).isZero())
    }

    @Test
    fun isNotNegative() {
        Assert.assertTrue(0.0.isNotNegative())
        Assert.assertTrue(0.1.isNotNegative())
        Assert.assertFalse((-0.1).isNotNegative())
    }

    @Test
    fun isNotPositive() {
        Assert.assertTrue(0.0.isNotPositive())
        Assert.assertFalse(0.1.isNotPositive())
        Assert.assertTrue((-0.1).isNotPositive())
    }

    @Test
    fun isNotZero() {
        Assert.assertFalse(0.0.isNotZero())
        Assert.assertTrue(0.1.isNotZero())
        Assert.assertTrue((-0.1).isNotZero())
    }

    @Test
    fun isNullOrNegative() {
        val nullValue: Double? = null
        Assert.assertTrue(nullValue.isNullOrNegative())
        Assert.assertFalse(0.0.isNullOrNegative())
        Assert.assertFalse(0.1.isNullOrNegative())
        Assert.assertTrue((-0.1).isNullOrNegative())
    }

    @Test
    fun isNullOrPositive() {
        val nullValue: Double? = null
        Assert.assertTrue(nullValue.isNullOrPositive())
        Assert.assertFalse(0.0.isNullOrPositive())
        Assert.assertTrue(0.1.isNullOrPositive())
        Assert.assertFalse((-0.1).isNullOrPositive())
    }

    @Test
    fun isNullOrZero() {
        val nullValue: Double? = null
        Assert.assertTrue(nullValue.isNullOrZero())
        Assert.assertTrue(0.0.isNullOrZero())
        Assert.assertFalse(0.1.isNullOrZero())
        Assert.assertFalse((-0.1).isNullOrZero())
    }

}