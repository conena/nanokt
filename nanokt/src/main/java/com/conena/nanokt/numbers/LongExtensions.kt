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

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.numbers

/**
 * @param flag The flag to check.
 * @return True if the receiver has all flag bits in [flag] set.
 */
inline fun Long.isFlagSet(flag: Long): Boolean {
    return this.and(flag) == flag
}

/**
 * @param flag The bit flag to add to the receiver.
 * @return The receiver with all flag bits from [flag] added.
 */
inline fun Long.addFlag(flag: Long): Long {
    return this.or(flag)
}

/**
 * @param flag The bit flag to remove from the receiver.
 * @return The receiver with all flag bits from [flag] removed.
 */
inline fun Long.removeFlag(flag: Long): Long {
    return this.and(flag.inv())
}

/**
 * @return An array with the set bit flags.
 */
inline fun Long.getBitFlags(): Array<Long> {
    val found = ArrayList<Long>(countOneBits())
    for (i in 0..31) {
        val nextFlag = 1L.shl(i)
        if (this.and(nextFlag) == nextFlag) {
            found.add(nextFlag)
        }
    }
    return found.toTypedArray()
}

/**
 * @return `null` if the Long is zero. Otherwise the Long is returned.
 */
inline fun Long.zeroAsNull() = if (this == 0L) null else this

/**
 * @return 0 if the Long is `null`. Otherwise the Long is returned.
 */
inline fun Long?.nullAsZero() = this ?: 0L

/**
 * @return `null` if the Long is negative. Otherwise the Long is returned.
 */
inline fun Long.negativeAsNull() = if (this < 0L) null else this

/**
 * @return 0 if the Long is negative. Otherwise the Long is returned.
 */
inline fun Long.negativeAsZero() = if (this < 0L) 0L else this

/**
 * @return `null` if the Long is positive. Otherwise the Long is returned.
 */
inline fun Long.positiveAsNull() = if (this > 0L) null else this

/**
 * @return 0 if the Long is positive. Otherwise the Long is returned.
 */
inline fun Long.positiveAsZero() = if (this > 0L) 0L else this

/**
 * @return `true` if the Long is not `null` and negative.
 */
inline fun Long?.isNegative() = this != null && this < 0L

/**
 * @return `true` if the Long is not `null` and positive.
 */
inline fun Long?.isPositive() = this != null && this > 0L

/**
 * @return `true` it the Long is zero.
 */
inline fun Long?.isZero() = this == 0L

/**
 * @return `true` if the Long is not negative.
 */
inline fun Long?.isNotNegative() = this == null || this >= 0L

/**
 * @return `true` if the Long is not positive.
 */
inline fun Long?.isNotPositive() = this == null || this <= 0L

/**
 * @return `true` it the Long is not zero.
 */
inline fun Long?.isNotZero() = this != 0L

/**
 * @return `true` if the Long is `null` or negative.
 */
inline fun Long?.isNullOrNegative() = this == null || this < 0L

/**
 * @return `true` if the Long is `null` or positive.
 */
inline fun Long?.isNullOrPositive() = this == null || this > 0L

/**
 * @return `true` it the Long is `null` or zero.
 */
inline fun Long?.isNullOrZero() = this == null || this == 0L