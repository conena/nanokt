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

@file:JvmName(name = "AlertDialogUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.app

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

/**
 * Same as [AlertDialog.Builder.setPositiveButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setPositiveButton(
    @StringRes textId: Int
): T {
    return setPositiveButton(textId, null) as T
}

/**
 * Same as [AlertDialog.Builder.setPositiveButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setPositiveButton(
    text: CharSequence
): T {
    return setPositiveButton(text, null) as T
}

/**
 * Same as [AlertDialog.Builder.setNegativeButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setNegativeButton(
    @StringRes textId: Int
): T {
    return setNegativeButton(textId, null) as T
}

/**
 * Same as [AlertDialog.Builder.setNegativeButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setNegativeButton(
    text: CharSequence
): T {
    return setNegativeButton(text, null) as T
}

/**
 * Same as [AlertDialog.Builder.setNeutralButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setNeutralButton(
    @StringRes textId: Int
): T {
    return setNeutralButton(textId, null) as T
}

/**
 * Same as [AlertDialog.Builder.setNeutralButton] but with null as argument for the listener.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <reified T : AlertDialog.Builder> T.setNeutralButton(
    text: CharSequence
): T {
    return setNeutralButton(text, null) as T
}