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
inline fun Double.zeroAsNull() = if (this == 0.0) null else this

/**
 * @return 0 if the receiver is `null`. Otherwise the receiver is returned.
 */
inline fun Double?.nullAsZero() = this ?: 0.0

/**
 * @return `null` if the receiver is negative. Otherwise the receiver is returned.
 */
inline fun Double.negativeAsNull() = if (this < 0.0) null else this

/**
 * @return 0 if the receiver is negative. Otherwise the receiver is returned.
 */
inline fun Double.negativeAsZero() = if (this < 0.0) 0.0 else this

/**
 * @return `null` if the receiver is positive. Otherwise the receiver is returned.
 */
inline fun Double.positiveAsNull() = if (this > 0.0) null else this

/**
 * @return 0 if the receiver is positive. Otherwise the receiver is returned.
 */
inline fun Double.positiveAsZero() = if (this > 0.0) 0.0 else this

/**
 * @return `true` if the receiver is not `null` and negative.
 */
inline fun Double?.isNegative() = this != null && this < 0.0

/**
 * @return `true` if the receiver is not `null` and positive.
 */
inline fun Double?.isPositive() = this != null && this > 0.0

/**
 * @return `true` it the receiver is zero.
 */
inline fun Double?.isZero() = this == 0.0

/**
 * @return `true` if the receiver is not negative.
 */
inline fun Double?.isNotNegative() = this == null || this >= 0.0

/**
 * @return `true` if the receiver is not positive.
 */
inline fun Double?.isNotPositive() = this == null || this <= 0.0

/**
 * @return `true` it the receiver is not zero.
 */
inline fun Double?.isNotZero() = this != 0.0

/**
 * @return `true` if the receiver is `null` or negative.
 */
inline fun Double?.isNullOrNegative() = this == null || this < 0.0

/**
 * @return `true` if the receiver is `null` or positive.
 */
inline fun Double?.isNullOrPositive() = this == null || this > 0.0

/**
 * @return `true` it the receiver is `null` or zero.
 */
inline fun Double?.isNullOrZero() = this == null || this == 0.0