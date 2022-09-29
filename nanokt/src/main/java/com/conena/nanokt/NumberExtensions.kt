@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt


/**
 * @return `null` if the Int is zero. Otherwise the Int is returned.
 */
inline fun Int.zeroAsNull() = if (this == 0) null else this

/**
 * @return 0 if the Int is `null`. Otherwise the Int is returned.
 */
inline fun Int?.nullAsZero() = this ?: 0

/**
 * @return `null` if the Int is negative. Otherwise the Int is returned.
 */
inline fun Int.negativeAsNull() = if (this < 0) null else this

/**
 * @return 0 if the Int is negative. Otherwise the Int is returned.
 */
inline fun Int.negativeAsZero() = if (this < 0) 0 else this

/**
 * @return `null` if the Int is positive. Otherwise the Int is returned.
 */
inline fun Int.positiveAsNull() = if (this > 0) null else this

/**
 * @return 0 if the Int is positive. Otherwise the Int is returned.
 */
inline fun Int.positiveAsZero() = if (this > 0) 0 else this

/**
 * @return `true` if the Int is not `null` and negative.
 */
inline fun Int?.isNegative() = this != null && this < 0

/**
 * @return `true` if the Int is not `null` and positive.
 */
inline fun Int?.isPositive() = this != null && this > 0

/**
 * @return `true` it the Int is zero.
 */
inline fun Int?.isZero() = this == 0

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