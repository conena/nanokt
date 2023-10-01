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

@file:JvmName(name = "ClipDataUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.ClipData
import androidx.annotation.CheckResult
import com.conena.nanokt.annotations.ExperimentalNanoKtApi


/**
 * The items in this clip data.
 */
@ExperimentalNanoKtApi
inline val ClipData.items: Iterable<ClipData.Item>
    @CheckResult
    get() = object : Iterable<ClipData.Item> {
        override operator fun iterator(): Iterator<ClipData.Item> = object : Iterator<ClipData.Item> {
            private var index = 0
            override operator fun hasNext(): Boolean = index < itemCount
            override operator fun next(): ClipData.Item {
                if (hasNext()) return getItemAt(index++) else throw NoSuchElementException()
            }
        }
    }
