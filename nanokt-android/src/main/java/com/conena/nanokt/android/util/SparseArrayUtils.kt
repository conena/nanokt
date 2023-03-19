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

@file:JvmName(name = "SparseArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.SparseArray
import androidx.annotation.CheckResult

/**
 * @param T The type used for the [SparseArray].
 * @param pair Pair with the initial element and key.
 * @return A [SparseArray], mapping the specified key to the specified element.
 */
@CheckResult
inline fun <T> sparseArrayOf(pair: Pair<Int, T>): SparseArray<T> {
    val r = SparseArray<T>()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param T The type used for the [SparseArray].
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as elements.
 * If multiple pairs have the same key, the resulting [SparseArray] will contain the value from the last of those pairs.
 * @return A [SparseArray], mapping the specified keys to the specified values.
 */
@CheckResult
inline fun <T> sparseArrayOf(vararg pairs: Pair<Int, T>): SparseArray<T> {
    val r = SparseArray<T>()
    for (pair in pairs) {
        r.put(pair.first, pair.second)
    }
    return r
}

/**
 * @param T The type used for the [SparseArray].
 * @param element The initial element for the [SparseArray]. 0 will is used as key.
 * @return A [SparseArray], mapping 0 to the specified element.
 */
@CheckResult
inline fun <T> sparseArrayOf(element: T): SparseArray<T> {
    val r = SparseArray<T>()
    r.put(0, element)
    return r
}

/**
 * @param T The type used for the [SparseArray].
 * @param elements The elements for the [SparseArray]. The first element will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [SparseArray], mapping the indexes of [elements] to the elements.
 */
@CheckResult
inline fun <T> sparseArrayOf(vararg elements: T): SparseArray<T> {
    val r = SparseArray<T>()
    for (i in 0 .. elements.lastIndex) {
        r.put(i, elements[i])
    }
    return r
}

/**
 * Create an empty [SparseArray] with type [T].
 */
@CheckResult
inline fun <T> emptySparseArray() = SparseArray<T>()

/**
 * @return A new [HashMap] containing all key-value pairs from the [SparseArray].
 */
@CheckResult
inline fun <T> SparseArray<T>.toHashMap(): HashMap<Int, T> {
    val map = HashMap<Int, T>(size())
    for (i in 0 until size()) {
        map[i] = valueAt(i)
    }
    return map
}

/**
 * @return A new [ArrayList] containing all elements from the [SparseArray]. The original sorting is retained.
 * If the indexes contain gaps, all following elements are shifted forward by the size of the gaps.
 */
@CheckResult
inline fun <T> SparseArray<T>.toArrayList(): ArrayList<T> {
    val list = ArrayList<T>(size())
    for (i in 0 until size()) {
        list.add(valueAt(i))
    }
    return list
}
