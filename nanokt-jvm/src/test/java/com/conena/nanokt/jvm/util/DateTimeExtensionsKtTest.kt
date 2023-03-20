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

package com.conena.nanokt.jvm.util


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class DateTimeExtensionsKtTest {

    @Test
    fun formatOrNull() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        assertEquals("2022-10-08", formatter.formatOrNull(Date(1665239501000L)))
        assertNull(formatter.formatOrNull(null))
    }

    @Test
    fun toDate() {
        val timeStamp = 1665239501000L
        assertEquals(Date(timeStamp), timeStamp.toDate())
    }

    @Test
    fun formatWithDateFormat() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        assertEquals("2022-10-08", Date(1665239501000L).format(formatter))
    }

    @Test
    fun formatWithPattern() {
        assertEquals("2022-10-08", Date(1665239501000L).format(pattern = "yyyy-MM-dd"))
    }

}