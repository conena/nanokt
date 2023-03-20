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

package com.conena.nanokt.collections

import org.junit.Assert.assertEquals
import org.junit.Test

class MapExtensionsKtTest {

    @Test
    fun forEachCompat() {
        val map = mapOf(1 to "1", 2 to "2", 3 to "3")
        val actionMap = mutableMapOf<Int, String>()
        map.forEachCompat { key, value ->
            actionMap[key] = value
        }
        assertEquals(map, actionMap)
    }
}