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
@get:CheckResult
@get:Px
inline val Int.dpInPx get() = this.times(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as pixels and converted to density-independent pixels.
 */
@get:CheckResult
inline val Int.pxInDp get() = this.div(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as scale-independent pixels and converted to pixels.
 */
@get:CheckResult
@get:Px
inline val Int.spInPx get() = this.times(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * The current value interpreted as pixels and converted to scale-independent pixels.
 */
@get:CheckResult
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

/**
 * The current value interpreted as density-independent pixels and converted to pixels.
 */
@get:CheckResult
@get:Px
inline val Float.dpInPx get() = this.times(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as pixels and converted to density-independent pixels.
 */
@get:CheckResult
inline val Float.pxInDp get() = this.div(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * The current value interpreted as scale-independent pixels and converted to pixels.
 */
@get:CheckResult
@get:Px
inline val Float.spInPx get() = this.times(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * The current value interpreted as pixels and converted to scale-independent pixels.
 */
@get:CheckResult
inline val Float.pxInSp get() = this.div(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * @return Convert the current value from density-independent pixels to pixels.
 */
@CheckResult
@Px
inline fun Float.dpToPx() = this.times(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * @return Convert the current value from pixels to density-independent pixels.
 */
@CheckResult
inline fun Float.pxToDp() = this.div(Resources.getSystem().displayMetrics.density).roundToInt()

/**
 * @return Convert the current value from scale-independent pixels to pixels.
 */
@CheckResult
@Px
inline fun Float.spToPx() = this.times(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

/**
 * @return Convert the current value from pixels to scale-independent pixels.
 */
@CheckResult
inline fun Float.pxToSp() = this.div(Resources.getSystem().displayMetrics.scaledDensity).roundToInt()

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
inline fun Float.toPx(unit: Int): Int {
    return TypedValue.applyDimension(unit, this, Resources.getSystem().displayMetrics).roundToInt()
}