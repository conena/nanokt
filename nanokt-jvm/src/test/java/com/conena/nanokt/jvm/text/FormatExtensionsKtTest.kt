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

package com.conena.nanokt.jvm.text

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat
import java.util.Locale

class FormatExtensionsKtTest {

    @Test
    fun formatInt() {
        assertEquals("100", 100.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("1,000", 1000.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("10,000", 10000.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("1,000,000", 1000000.format(format = NumberFormat.getInstance(Locale.US)))

        assertEquals("100", 100.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("1.000", 1000.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("10.000", 10000.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("1.000.000", 1000000.format(format = NumberFormat.getInstance(Locale.GERMANY)))
    }

    @Test
    fun formatLong() {
        assertEquals("100", 100L.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("1,000", 1000L.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("10,000", 10000L.format(format = NumberFormat.getInstance(Locale.US)))
        assertEquals("1,000,000", 1000000L.format(format = NumberFormat.getInstance(Locale.US)))

        assertEquals("100", 100L.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("1.000", 1000L.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("10.000", 10000L.format(format = NumberFormat.getInstance(Locale.GERMANY)))
        assertEquals("1.000.000", 1000000L.format(format = NumberFormat.getInstance(Locale.GERMANY)))
    }

}