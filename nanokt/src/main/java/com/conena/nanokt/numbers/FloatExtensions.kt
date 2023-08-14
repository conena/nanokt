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
 * @return `null` if the receiver is zero. Otherwise the receiver is returned.
 */
inline fun Float.zeroAsNull() = if (this == 0f) null else this

/**
 * @return 0 if the receiver is `null`. Otherwise the receiver is returned.
 */
inline fun Float?.nullAsZero() = this ?: 0f

/**
 * @return `null` if the receiver is negative. Otherwise the receiver is returned.
 */
inline fun Float.negativeAsNull() = if (this < 0f) null else this

/**
 * @return 0 if the receiver is negative. Otherwise the receiver is returned.
 */
inline fun Float.negativeAsZero() = if (this < 0f) 0f else this

/**
 * @return `null` if the receiver is positive. Otherwise the receiver is returned.
 */
inline fun Float.positiveAsNull() = if (this > 0f) null else this

/**
 * @return 0 if the receiver is positive. Otherwise the receiver is returned.
 */
inline fun Float.positiveAsZero() = if (this > 0f) 0f else this

/**
 * @return `true` if the receiver is negative.
 */
inline fun Float.isNegative() = this < 0f

/**
 * @return `true` if the receiver is positive.
 */
inline fun Float.isPositive() = this > 0f

/**
 * @return `true` it the receiver is zero.
 */
inline fun Float.isZero() = this == 0f

/**
 * @return `true` if the receiver is not negative.
 */
inline fun Float.isNotNegative() = this >= 0f

/**
 * @return `true` if the receiver is not positive.
 */
inline fun Float.isNotPositive() = this <= 0f

/**
 * @return `true` it the receiver is not zero.
 */
inline fun Float.isNotZero() = this != 0f

/**
 * @return `true` if the receiver is `null` or negative.
 */
inline fun Float?.isNullOrNegative() = this == null || this < 0f

/**
 * @return `true` if the receiver is `null` or positive.
 */
inline fun Float?.isNullOrPositive() = this == null || this > 0f

/**
 * @return `true` it the receiver is `null` or zero.
 */
inline fun Float?.isNullOrZero() = this == null || this == 0f