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

@file:JvmName(name = "ColorUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.graphics

import android.graphics.Color
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt

/**
 * @return Same as [Color.parseColor] but returns null on failure instead
 * of throwing an exception.
 */
@CheckResult
@ColorInt
inline fun String.toColorIntOrNull(): Int? {
    return try {
        Color.parseColor(this)
    } catch (_: Throwable) {
        null
    }
}

/**
 * Interprets the receiver as [ColorInt] and returns the hex string representation of the color.
 * @return The hex string representation of the color.
 */
@CheckResult
inline fun @receiver:ColorInt Int.toHexColor(): String {
    return "#%08X".format(0xFFFFFFFF and this.toLong())
}
