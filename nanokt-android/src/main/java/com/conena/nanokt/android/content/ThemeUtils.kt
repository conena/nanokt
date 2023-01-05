@file:JvmName(name = "ThemeUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import com.conena.nanokt.android.util.isColorTypeCompat

/**
 * Get a [TypedValue] from the theme. Resource references will be resolved.
 * @param id Resource attribute id.
 * @return Obtained TypedValue or `null` if the attribute was not found.
 */
@CheckResult
inline fun Resources.Theme.getAttributeOrNull(@AttrRes id: Int): TypedValue? {
    val v = TypedValue()
    return if (resolveAttribute(id, v, true)) v else null
}

/**
 * Get a [TypedValue] from the theme. Resource references will be resolved.
 * @param id Resource attribute id.
 * @return Obtained TypedValue or `null` if the attribute was not found.
 * @throws Resources.NotFoundException If [id] does not exist in the theme.
 */
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getAttribute(@AttrRes id: Int): TypedValue {
    return getAttributeOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get a boolean value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the boolean.
 * @return The boolean if the attribute was found, `null` otherwise.
 */
@CheckResult
inline fun Resources.Theme.getBooleanOrNull(@AttrRes id: Int): Boolean? {
    // Should return 1 if true according to docs but returns -1 if true on my devices, so we check for false
    return getAttributeOrNull(id)?.data?.equals(0)?.not()
}

/**
 * Get a boolean value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the boolean.
 * @return The boolean.
 * @throws Resources.NotFoundException If [id] does not exist in the theme.
 */
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getBoolean(@AttrRes id: Int): Boolean {
    return getBooleanOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get an integer value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the integer.
 * @return The integer if the attribute was found, `null` otherwise.
 */
@CheckResult
inline fun Resources.Theme.getIntegerOrNull(@AttrRes id: Int): Int? {
    return getAttributeOrNull(id)?.data
}

/**
 * Get an integer value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the integer.
 * @return The integer.
 * @throws Resources.NotFoundException If [id] does not exist in the theme.
 */
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getInteger(@AttrRes id: Int): Int {
    return getIntegerOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get a color from the theme. Resource references will be resolved.
 * Use [Context.getThemeColorOrNull] if you need support below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The color if the attribute was found, `null` otherwise.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@ColorInt
@CheckResult
inline fun Resources.Theme.getColorOrNull(@AttrRes id: Int): Int? {
    val attr = getAttributeOrNull(id) ?: return null
    return if (attr.isColorTypeCompat) {
        attr.data
    } else resources.getColorCompatOrNull(id = attr.resourceId, theme = this)
}

/**
 * Get a color from the theme. Resource references will be resolved.
 * Use [Context.getThemeColor] if you need support below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The color.
 * @throws Resources.NotFoundException If [id] does not exist in the theme or is no color.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@ColorInt
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getColor(@AttrRes id: Int): Int {
    return getColorOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get a [ColorStateList] from the theme. Resource references will be resolved.
 * Use [Context.getThemeColorStateListOrNull] if you need support below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The [ColorStateList] if the attribute was found, `null` otherwise.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@CheckResult
inline fun Resources.Theme.getColorStateListOrNull(@AttrRes id: Int): ColorStateList? {
    return resources.getColorStateListCompatOrNull(
        id = getAttributeOrNull(id)?.resourceId,
        theme = this
    )
}

/**
 * Get a [ColorStateList] from the theme. Resource references will be resolved.
 * Use [Context.getThemeColorStateList] if you need support below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The [ColorStateList].
 * @throws Resources.NotFoundException If [id] does not exist in the theme or is no [ColorStateList].
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getColorStateList(@AttrRes id: Int): ColorStateList {
    return getColorStateListOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get an string value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the string.
 * @return The string if the attribute was found, `null` otherwise.
 */
@CheckResult
inline fun Resources.Theme.getStringOrNull(@AttrRes id: Int): String? {
    return getAttributeOrNull(id)?.string?.toString()
}

/**
 * Get an string value from the theme. Resource references will be resolved.
 * @param id Resource attribute id for the string.
 * @return The string.
 * @throws Resources.NotFoundException If [id] does not exist in the theme.
 */
@Throws(Resources.NotFoundException::class)
inline fun Resources.Theme.getString(@AttrRes id: Int): String {
    return getStringOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}