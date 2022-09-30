@file:JvmName(name = "TypedValueUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.TypedValue

/**
 * True if the value is a color.
 * @see TypedValue.isColorType
 */
inline val TypedValue.isColorTypeCompat
get() = type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT

/**
 * The complex unit type. Does only work for values with type [TypedValue.TYPE_DIMENSION].
 * @see TypedValue.getComplexUnit
 */
inline val TypedValue.complexUnitTypeCompat
get() = TypedValue.COMPLEX_UNIT_MASK and (data shr TypedValue.COMPLEX_UNIT_SHIFT)