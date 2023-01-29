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

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.text.NumberFormat


/**
 * @param format The format in which the long is to be formatted.
 * @return The formatted long.
 */
inline fun Long.format(format: NumberFormat = NumberFormat.getInstance()): String {
    return format.format(this)
}

/**
 * @param format The format in which the integer is to be formatted.
 * @return The formatted integer.
 */
inline fun Int.format(format: NumberFormat = NumberFormat.getInstance()): String {
    return format.format(this.toLong())
}