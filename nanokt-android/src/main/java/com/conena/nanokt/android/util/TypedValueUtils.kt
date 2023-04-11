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