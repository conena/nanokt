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

package com.conena.nanokt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TupleUtilsKtTest {

    @Test
    fun toOrNull() {
        val nn = null toOrNull null
        assertNull(nn)

        val nv = null toOrNull "Second"
        assertNull(nv)

        val vn = "First" toOrNull null
        assertNull(vn)

        val vv = "First" toOrNull "Second"
        assertNotNull(vv)
        assertEquals("First", vv.first)
        assertEquals("Second", vv.second)
    }
}