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

@file:JvmName(name = "SparseIntArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.SparseIntArray
import androidx.annotation.CheckResult

/**
 * @param pair Pair with the initial element and key.
 * @return A [SparseIntArray], mapping the specified key to the specified element.
 */
@CheckResult
inline fun <T> sparseIntArrayOf(pair: Pair<Int, Int>): SparseIntArray {
    val r = SparseIntArray()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as elements.
 * If multiple pairs have the same key, the resulting [SparseIntArray] will contain the value from the last of those pairs.
 * @return A [SparseIntArray], mapping the specified keys to the specified values.
 */
@CheckResult
inline fun <T> sparseIntArrayOf(vararg pairs: Pair<Int, Int>): SparseIntArray {
    val r = SparseIntArray()
    for (pair in pairs) {
        r.put(pair.first, pair.second)
    }
    return r
}

/**
 * @param element The initial element for the [SparseIntArray]. 0 will is used as key.
 * @return A [SparseIntArray], mapping 0 to the specified element.
 */
@CheckResult
inline fun sparseIntArrayOf(element: Int): SparseIntArray {
    val r = SparseIntArray()
    r.put(0, element)
    return r
}

/**
 * @param elements The elements for the [SparseIntArray]. The first element will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [SparseIntArray], mapping the indexes of [elements] to the elements.
 */
@CheckResult
inline fun <T> sparseIntArrayOf(vararg elements: Int): SparseIntArray {
    val r = SparseIntArray()
    for (i in 0..elements.lastIndex) {
        r.put(i, elements[i])
    }
    return r
}

/**
 * Create an empty [SparseIntArray].
 */
@CheckResult
inline fun <T> emptySparseIntArray() = SparseIntArray()

/**
 * @return A new [HashMap] containing all key-value pairs from the [SparseIntArray].
 */
@CheckResult
inline fun SparseIntArray.toHashMap(): HashMap<Int, Int> {
    val map = HashMap<Int, Int>(size())
    for (i in 0 until size()) {
        map[i] = valueAt(i)
    }
    return map
}

/**
 * @return A new [ArrayList] containing all elements from the [SparseIntArray]. The original sorting is retained.
 * If the indexes contain gaps, all following elements are shifted forward by the size of the gaps.
 */
@CheckResult
inline fun SparseIntArray.toArrayList(): ArrayList<Int> {
    val list = ArrayList<Int>(size())
    for (i in 0 until size()) {
        list.add(valueAt(i))
    }
    return list
}