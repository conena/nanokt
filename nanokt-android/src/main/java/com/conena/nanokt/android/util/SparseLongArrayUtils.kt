@file:JvmName(name = "SparseLongArrayUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.os.Build
import android.util.SparseLongArray
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi

/**
 * @param pair Pair with the initial value and key.
 * @return A [SparseLongArray], mapping the specified key to the specified value.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun <T> sparseLongArrayOf(pair: Pair<Int, Long>): SparseLongArray {
    val r = SparseLongArray()
    r.put(pair.first, pair.second)
    return r
}

/**
 * @param pairs The values in [Pair.first] will be used as keys. The values in [Pair.second] as values.
 * If multiple pairs have the same key, the resulting [SparseLongArray] will contain the value from the last of those pairs.
 * @return A [SparseLongArray], mapping the specified keys to the specified values.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun <T> sparseLongArrayOf(vararg pairs: Pair<Int, Long>): SparseLongArray {
    val r = SparseLongArray()
    for (pair in pairs) {
        r.put(pair.first, pair.second)
    }
    return r
}

/**
 * @param value The initial value for the [SparseLongArray]. 0 will is used as key.
 * @return A [SparseLongArray], mapping 0 to the specified value.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun sparseLongArrayOf(value: Long): SparseLongArray {
    val r = SparseLongArray()
    r.put(0, value)
    return r
}

/**
 * @param values The values for the [SparseLongArray]. The first value will be mapped to the key 0.
 * The second to the key 1 and so on.
 * @return A [SparseLongArray], mapping the indexes of [values] to the values.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun <T> sparseLongArrayOf(vararg values: Long): SparseLongArray {
    val r = SparseLongArray()
    for (i in 0 .. values.lastIndex) {
        r.put(i, values[i])
    }
    return r
}

/**
 * Create an empty [SparseLongArray].
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun <T> emptySparseLongArray() = SparseLongArray()

/**
 * @return A new [HashMap] containing all key-value pairs from the [SparseLongArray].
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun SparseLongArray.toHashMap(): HashMap<Int, Long> {
    val map = HashMap<Int, Long>(size())
    for (i in 0 until size()) {
        map[i] = valueAt(i)
    }
    return map
}

/**
 * @return A new [ArrayList] containing all values from the [SparseLongArray]. The original sorting is retained.
 * If the indexes contain gaps, all following entries are shifted forward by the size of the gaps.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun SparseLongArray.toArrayList(): ArrayList<Long> {
    val list = ArrayList<Long>(size())
    for (i in 0 until size()) {
        list.add(valueAt(i))
    }
    return list
}