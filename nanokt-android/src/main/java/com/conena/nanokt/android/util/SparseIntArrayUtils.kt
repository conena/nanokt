@file:JvmName(name = "SparseIntArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.SparseIntArray
import androidx.annotation.CheckResult

/**
 * @param pair Pair with the initial value and key.
 * @return A [SparseIntArray], mapping the specified key to the specified value.
 */
@CheckResult
inline fun <T> sparseIntArrayOf(pair: Pair<Int, Int>): SparseIntArray {
    val r = SparseIntArray()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as values.
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
 * @param value The initial value for the [SparseIntArray]. 0 will is used as key.
 * @return A [SparseIntArray], mapping 0 to the specified value.
 */
@CheckResult
inline fun sparseIntArrayOf(value: Int): SparseIntArray {
    val r = SparseIntArray()
    r.put(0, value)
    return r
}

/**
 * @param values The values for the [SparseIntArray]. The first value will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [SparseIntArray], mapping the indexes of [values] to the values.
 */
@CheckResult
inline fun <T> sparseIntArrayOf(vararg values: Int): SparseIntArray {
    val r = SparseIntArray()
    for (i in 0 .. values.lastIndex) {
        r.put(i, values[i])
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
 * @return A new [ArrayList] containing all values from the [SparseIntArray]. The original sorting is retained.
 * If the indexes contain gaps, all following entries are shifted forward by the size of the gaps.
 */
@CheckResult
inline fun SparseIntArray.toArrayList(): ArrayList<Int> {
    val list = ArrayList<Int>(size())
    for (i in 0 until size()) {
        list.add(valueAt(i))
    }
    return list
}