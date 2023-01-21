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

@file:JvmName(name = "EditTextUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget

import android.widget.EditText

/**
 * Set the cursor to the start of the text.
 * @param T The type of the EditText.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : EditText> T.setCursorToStart(): T {
    setSelection(0)
    return this
}

/**
 * Set the cursor to the end of the text.
 * @param T The type of the EditText.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : EditText> T.setCursorToEnd(): T {
    setSelection(text.length)
    return this
}