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

@file:JvmName(name = "LongSparseArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.LongSparseArray
import androidx.annotation.CheckResult

/**
 * @param T The type used for the [Long].
 * @param pair Pair with the initial element and key.
 * @return A [LongSparseArray], mapping the specified key to the specified element.
 */
@CheckResult
inline fun <T> longSparseArrayOf(pair: Pair<Long, T>): LongSparseArray<T> {
    val r = LongSparseArray<T>()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param T The type used for the [LongSparseArray].
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as elements.
 * If multiple pairs have the same key, the resulting [LongSparseArray] will contain the value from the last of those pairs.
 * @return A [LongSparseArray], mapping the specified keys to the specified values.
 */
@CheckResult
inline fun <T> longSparseArrayOf(vararg pairs: Pair<Long, T>): LongSparseArray<T> {
    val r = LongSparseArray<T>()
    for (pair in pairs) {
        r.put(pair.first, pair.second)
    }
    return r
}

/**
 * @param T The type used for the [LongSparseArray].
 * @param element The initial element for the [LongSparseArray]. 0 will is used as key.
 * @return A [LongSparseArray], mapping 0 to the specified element.
 */
@CheckResult
inline fun <T> longSparseArrayOf(element: T): LongSparseArray<T> {
    val r = LongSparseArray<T>()
    r.put(0L, element)
    return r
}

/**
 * @param T The type used for the [LongSparseArray].
 * @param elements The elements for the [LongSparseArray]. The first element will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [LongSparseArray], mapping the indexes of [elements] to the elements.
 */
@CheckResult
inline fun <T> longSparseArrayOf(vararg elements: T): LongSparseArray<T> {
    val r = LongSparseArray<T>()
    for (i in 0..elements.lastIndex) {
        r.put(i.toLong(), elements[i])
    }
    return r
}

/**
 * Create an empty [LongSparseArray] with type [T].
 */
@CheckResult
inline fun <T> emptyLongSparseArray() = LongSparseArray<T>()

/**
 * @return A new [HashMap] containing all key-value pairs from the [LongSparseArray].
 */
@CheckResult
inline fun <T> LongSparseArray<T>.toHashMap(): HashMap<Long, T> {
    val map = HashMap<Long, T>(size())
    for (i in 0 ..< size()) {
        map[i.toLong()] = valueAt(i)
    }
    return map
}

/**
 * @return A new [ArrayList] containing all elements from the [LongSparseArray]. The original sorting is retained.
 * If the indexes contain gaps, all following elements are shifted forward by the size of the gaps.
 */
@CheckResult
inline fun <T> LongSparseArray<T>.toArrayList(): ArrayList<T> {
    val list = ArrayList<T>(size())
    for (i in 0 ..< size()) {
        list.add(valueAt(i))
    }
    return list
}

