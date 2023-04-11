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

@file:JvmName(name = "CompoundButtonUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget


import android.view.View
import android.widget.CompoundButton

/**
 * Sets the current instance as [View.OnLongClickListener] for the [view].
 * @param T The Type of the [CompoundButton.OnCheckedChangeListener].
 * @param view The view to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : CompoundButton.OnCheckedChangeListener> T.addAsCheckedChangeListenerFor(
    view: CompoundButton
): T {
    view.setOnCheckedChangeListener(this)
    return this
}

/**
 * Sets the current instance as [CompoundButton.OnCheckedChangeListener] for all of the given [views].
 * @param T The Type of the [CompoundButton.OnCheckedChangeListener].
 * @param views The views to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : CompoundButton.OnCheckedChangeListener> T.addAsCheckedChangeListenerFor(
    vararg views: CompoundButton
): T {
    for (v in views) {
        v.setOnCheckedChangeListener(this)
    }
    return this
}