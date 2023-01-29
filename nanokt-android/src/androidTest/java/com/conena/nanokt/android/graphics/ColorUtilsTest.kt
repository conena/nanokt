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

package com.conena.nanokt.android.graphics

import android.graphics.Color
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ColorUtilsTest {

    @Test
    fun toHexColor() {
        @Suppress("SpellCheckingInspection")
        val colors = listOf(
            "#5C000000",
            "#FFBB86FC",
            "#6F00EEBE",
            "#FFFFFFFF",
            "#FF000000"
        )
        val map = colors.associateBy { Color.parseColor(it) }
        for (entry in map) {
            assertThat(entry.key.toHexColor()).isEqualTo(entry.value)
        }
    }

}