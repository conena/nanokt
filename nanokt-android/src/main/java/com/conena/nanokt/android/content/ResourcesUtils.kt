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

@file:JvmName(name = "ResourcesUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import com.conena.nanokt.annotations.ExperimentalNanoKtApi


/**
 * Same as [ResourcesCompat.getColor].
 * @param id The resource identifier of the color.
 * @param theme The theme used to style the color attributes.
 * @return A single color value in the form 0xAARRGGBB.
 * @throws NotFoundException If [id] does not exist.
 */
@Throws(NotFoundException::class)
@ColorInt
inline fun Resources.getColorCompat(@ColorRes id: Int, theme: Theme? = null): Int {
    return ResourcesCompat.getColor(this, id, theme)
}

/**
 * Same as [ResourcesCompat.getColor] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the color.
 * @param theme The theme used to style the color attributes.
 * @return A single color value in the form 0xAARRGGBB.
 */
@ColorInt
inline fun Resources.getColorCompatOrNull(@ColorRes id: Int?, theme: Theme? = null): Int? {
    return try {
        ResourcesCompat.getColor(this, id ?: return null, theme)
    } catch (_: NotFoundException) {
        null
    }
}

/**
 * Same as [ResourcesCompat.getColorStateList].
 * @param id The resource identifier of the [ColorStateList].
 * @param theme The theme used to style the color attributes.
 * @return A [ColorStateList], or `null` if the resource could not be resolved.
 * @throws NotFoundException If [id] does not exist.
 * @experimental Consider using [Resources.getColorStateListCompatOrNull].
 */
@ExperimentalNanoKtApi
@Throws(NotFoundException::class)
inline fun Resources.getColorStateListCompat(@ColorRes id: Int, theme: Theme? = null): ColorStateList? {
    return ResourcesCompat.getColorStateList(this, id, theme)
}

/**
 * Same as [ResourcesCompat.getColorStateList] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [ColorStateList].
 * @param theme The theme used to style the color attributes.
 * @return A [ColorStateList], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Resources.getColorStateListCompatOrNull(@ColorRes id: Int?, theme: Theme? = null): ColorStateList? {
    return try {
        ResourcesCompat.getColorStateList(this, id ?: return null, theme)
    } catch (_: NotFoundException) {
        null
    }
}

/**
 * Same as [ResourcesCompat.getDrawable].
 * @param id The resource identifier of the [Drawable].
 * @param theme The theme used to style the drawable attributes.
 * @return A [Drawable], or `null` if the resource could not be resolved.
 * @throws NotFoundException If [id] does not exist.
 * @experimental Consider using [Resources.getDrawableCompatOrNull].
 */
@ExperimentalNanoKtApi
@Throws(NotFoundException::class)
inline fun Resources.getDrawableCompat(@DrawableRes id: Int, theme: Theme? = null): Drawable? {
    return ResourcesCompat.getDrawable(this, id, theme)
}

/**
 * Same as [ResourcesCompat.getDrawable] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [Drawable].
 * @param theme The theme used to style the drawable attributes.
 * @return A [Drawable], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Resources.getDrawableCompatOrNull(@DrawableRes id: Int?, theme: Theme? = null): Drawable? {
    return try {
        ResourcesCompat.getDrawable(this, id ?: return null, theme)
    } catch (_: NotFoundException) {
        null
    }
}

/**
 * Get a drawable resource identifier for the given resource name in the given [packageName].
 * @param name The resource name.
 * @param packageName The package of the resource.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@DrawableRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Resources.getDrawableIdByNameOrNull(name: String, packageName: String): Int? {
    val resId = getIdentifier(name, "drawable", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}

/**
 * Get a string resource identifier for the given resource name in the given [packageName].
 * @param name The resource name.
 * @param packageName The package of the resource.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@StringRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Resources.getStringIdByNameOrNull(name: String, packageName: String): Int? {
    val resId = getIdentifier(name, "string", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}

/**
 * Get a layout resource identifier for the given resource name in the given [packageName].
 * @param name The resource name.
 * @param packageName The package of the resource.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@LayoutRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Resources.getLayoutIdByNameOrNull(name: String, packageName: String): Int? {
    val resId = getIdentifier(name, "layout", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}