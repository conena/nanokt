@file:JvmName(name = "DimensionsUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.CheckResult
import androidx.annotation.Px
import kotlin.math.roundToInt

/**
 * The current value interpreted as density-independent pixels and converted to pixels.
 */
inline val Int.dpInPx get() = this.times(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as pixels and converted to density-independent pixels.
 */
inline val Int.pxInDp get() = this.div(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as scale-independent pixels and converted to pixels.
 */
inline val Int.spInPx get() = this.times(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * The current value interpreted as pixels and converted to scale-independent pixels.
 */
inline val Int.pxInSp get() = this.div(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * @return Convert the current value from density-independent pixels to pixels.
 */
@CheckResult
@Px
inline fun Int.dpToPx() = this.times(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * @return Convert the current value from pixels to density-independent pixels.
 */
@CheckResult
inline fun Int.pxToDp() = this.div(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * @return Convert the current value from scale-independent pixels to pixels.
 */
@CheckResult
@Px
inline fun Int.spToPx() = this.times(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * @return Convert the current value from pixels to scale-independent pixels.
 */
@CheckResult
inline fun Int.pxToSp() = this.div(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * @param unit The unit to convert the value from. The possible values are listed below.
 * @return The current value treated as [unit] converted to pixels.
 * @see TypedValue.COMPLEX_UNIT_PX
 * @see TypedValue.COMPLEX_UNIT_DIP
 * @see TypedValue.COMPLEX_UNIT_SP
 * @see TypedValue.COMPLEX_UNIT_PT
 * @see TypedValue.COMPLEX_UNIT_IN
 * @see TypedValue.COMPLEX_UNIT_MM
 */
@CheckResult
@Px
inline fun Int.toPx(unit: Int): Int {
    return TypedValue.applyDimension(unit, toFloat(), Resources.getSystem().displayMetrics).roundToInt()
}