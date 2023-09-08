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

@file:JvmName(name = "IntentFilterUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.IntentFilter
import androidx.annotation.CheckResult
import com.conena.nanokt.annotations.ExperimentalNanoKtApi

/**
 * Create a new intent filter with multiple actions.
 * @param actions The intent actions to match against.
 * @return the created intent filter.
 */
@ExperimentalNanoKtApi
@CheckResult
inline fun createIntentFilter(vararg actions: String): IntentFilter {
    val filter = IntentFilter()
    for (action in actions) {
        filter.addAction(action)
    }
    return filter
}

/**
 * Add the given [actions] to the intent filter.
 * @param actions The actions to add.
 * @return The receiver object, for chaining multiple calls into a single statement.
 * @see IntentFilter.addAction
 */
inline fun IntentFilter.addActions(vararg actions: String): IntentFilter {
    for (action in actions) {
        addAction(action)
    }
    return this
}