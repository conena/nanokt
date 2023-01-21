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

@file:JvmName(name = "SparseBooleanArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.SparseBooleanArray
import androidx.annotation.CheckResult

/**
 * @param pair Pair with the initial value and key.
 * @return A [SparseBooleanArray], mapping the specified key to the specified value.
 */
@CheckResult
inline fun <T> sparseBooleanArrayOf(pair: Pair<Int, Boolean>): SparseBooleanArray {
    val r = SparseBooleanArray()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as values.
 * If multiple pairs have the same key, the resulting [SparseBooleanArray] will contain the value from the last of those pairs.
 * @return A [SparseBooleanArray], mapping the specified keys to the specified values.
 */
@CheckResult
inline fun <T> sparseBooleanArrayOf(vararg pairs: Pair<Int, Boolean>): SparseBooleanArray {
    val r = SparseBooleanArray()
    for (pair in pairs) {
        r.put(pair.first, pair.second)
    }
    return r
}

/**
 * @param value The initial value for the [SparseBooleanArray]. 0 will is used as key.
 * @return A [SparseBooleanArray], mapping 0 to the specified value.
 */
@CheckResult
inline fun sparseBooleanArrayOf(value: Boolean): SparseBooleanArray {
    val r = SparseBooleanArray()
    r.put(0, value)
    return r
}

/**
 * @param values The values for the [SparseBooleanArray]. The first value will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [SparseBooleanArray], mapping the indexes of [values] to the values.
 */
@CheckResult
inline fun <T> sparseBooleanArrayOf(vararg values: Boolean): SparseBooleanArray {
    val r = SparseBooleanArray()
    for (i in 0 .. values.lastIndex) {
        r.put(i, values[i])
    }
    return r
}

/**
 * Create an empty [SparseBooleanArray].
 */
@CheckResult
inline fun <T> emptySparseBooleanArray() = SparseBooleanArray()

/**
 * @return A new [HashMap] containing all key-value pairs from the [SparseBooleanArray].
 */
@CheckResult
inline fun SparseBooleanArray.toHashMap(): HashMap<Int, Boolean> {
    val map = HashMap<Int, Boolean>(size())
    for (i in 0 until size()) {
        map[i] = valueAt(i)
    }
    return map
}

/**
 * @return A new [ArrayList] containing all values from the [SparseBooleanArray]. The original sorting is retained.
 * If the indexes contain gaps, all following entries are shifted forward by the size of the gaps.
 */
@CheckResult
inline fun SparseBooleanArray.toArrayList(): ArrayList<Boolean> {
    val list = ArrayList<Boolean>(size())
    for (i in 0 until size()) {
        list.add(valueAt(i))
    }
    return list
}