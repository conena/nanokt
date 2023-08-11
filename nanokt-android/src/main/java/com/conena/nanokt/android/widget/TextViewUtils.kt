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

@file:JvmName(name = "WidgetUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.conena.nanokt.android.content.getDrawableCompatOrNull

/**
 * The style of the current [Typeface].
 * @see Typeface.NORMAL
 * @see Typeface.BOLD
 * @see Typeface.ITALIC
 * @see Typeface.BOLD_ITALIC
 */
@get:CheckResult
inline var TextView.textStyle: Int
    get() = typeface.style
    set(value) = setTypeface(typeface, value)

/**
 * Same as [TextView.getText] but returning [String] instead instead of [CharSequence].
 */
@get:CheckResult
inline var TextView.textString: String
    get() = text.toString()
    set(value) {
        text = value
    }

/**
 * Clear the text in the TextView.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.clear(): T {
    text = ""
    return this
}

/**
 * Clear the error message in the TextView.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.clearError(): T {
    setError(null, null)
    return this
}

/**
 * Same as [TextView.setError] but accepts a string resource.
 * @param resId The string resource id of the error message.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.setError(@StringRes resId: Int): T {
    error = context.getString(resId)
    return this
}

/**
 * Same as [TextView.setError] but accepts a string resource.
 * @param resId The string resource id of the error message.
 * @param icon The right-hand compound drawable of the TextView.
 * The drawable must already have had [Drawable.setBounds] set on it.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.setError(@StringRes resId: Int, icon: Drawable?): T {
    setError(context.getString(resId), icon)
    return this
}

/**
 * Same as [TextView.setError] but accepts a string resource.
 * @param resId The string resource id of the error message.
 * @param iconResId The resource for the right-hand compound drawable of the TextView.
 * [Drawable.setBounds] will be called with the [Drawable.getIntrinsicWidth] and [Drawable.getIntrinsicHeight].
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.setError(@StringRes resId: Int, @DrawableRes iconResId: Int): T {
    setError(context.getString(resId), context.getDrawableCompatOrNull(iconResId)?.apply {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    })
    return this
}

/**
 * Same as [TextView.setError] but accepts a string resource.
 * @param error The error message.
 * @param iconResId The resource for the right-hand compound drawable of the TextView.
 * [Drawable.setBounds] will be called with the [Drawable.getIntrinsicWidth] and [Drawable.getIntrinsicHeight].
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : TextView> T.setError(error: CharSequence, @DrawableRes iconResId: Int): T {
    setError(error, context.getDrawableCompatOrNull(iconResId)?.apply {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    })
    return this
}