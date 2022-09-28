@file:JvmName(name = "ThemeUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult

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