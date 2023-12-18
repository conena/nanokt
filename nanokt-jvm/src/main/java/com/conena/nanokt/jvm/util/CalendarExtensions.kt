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

@file:Suppress("unused")

package com.conena.nanokt.jvm.util

import java.util.Calendar
import java.util.GregorianCalendar

/**
 * The value for [Calendar.ERA].
 * @see Calendar.set
 * @see Calendar.get
 * @see GregorianCalendar.AD
 * @see GregorianCalendar.BC
 */
inline var Calendar.era
    get() = get(Calendar.ERA)
    set(value) = set(Calendar.ERA, value)

/**
 * The value for [Calendar.YEAR].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.year
    get() = get(Calendar.YEAR)
    set(value) = set(Calendar.YEAR, value)

/**
 * The value for [Calendar.MONTH].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.month
    get() = get(Calendar.MONTH)
    set(value) = set(Calendar.MONTH, value)

/**
 * The value for [Calendar.WEEK_OF_YEAR].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.weekOfYear
    get() = get(Calendar.WEEK_OF_YEAR)
    set(value) = set(Calendar.WEEK_OF_YEAR, value)

/**
 * The value for [Calendar.WEEK_OF_MONTH].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.weekOfMonth
    get() = get(Calendar.WEEK_OF_MONTH)
    set(value) = set(Calendar.WEEK_OF_MONTH, value)

/**
 * The value for [Calendar.DAY_OF_MONTH].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.dayOfMonth
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) = set(Calendar.DAY_OF_MONTH, value)

/**
 * The value for [Calendar.DAY_OF_YEAR].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.dayOfYear
    get() = get(Calendar.DAY_OF_YEAR)
    set(value) = set(Calendar.DAY_OF_YEAR, value)

/**
 * The value for [Calendar.DAY_OF_WEEK].
 * @see Calendar.set
 * @see Calendar.get
 * @see Calendar.MONDAY
 * @see Calendar.TUESDAY
 * @see Calendar.WEDNESDAY
 * @see Calendar.THURSDAY
 * @see Calendar.FRIDAY
 * @see Calendar.SATURDAY
 * @see Calendar.SUNDAY
 */
inline var Calendar.dayOfWeek
    get() = get(Calendar.DAY_OF_WEEK)
    set(value) = set(Calendar.DAY_OF_WEEK, value)

/**
 * The value for [Calendar.DAY_OF_WEEK_IN_MONTH].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.dayOfWeekInMonth
    get() = get(Calendar.DAY_OF_WEEK_IN_MONTH)
    set(value) = set(Calendar.DAY_OF_WEEK_IN_MONTH, value)

/**
 * The value for [Calendar.HOUR] (12-hour clock).
 * Noon and midnight are represented by 0, not by 12.
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.hour
    get() = get(Calendar.HOUR)
    set(value) = set(Calendar.HOUR, value)

/**
 * The value for [Calendar.HOUR_OF_DAY] (24-hour clock).
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.hourOfDay
    get() = get(Calendar.HOUR_OF_DAY)
    set(value) = set(Calendar.HOUR_OF_DAY, value)

/**
 * The value for [Calendar.MINUTE].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.minute
    get() = get(Calendar.MINUTE)
    set(value) = set(Calendar.MINUTE, value)

/**
 * The value for [Calendar.SECOND].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.second
    get() = get(Calendar.SECOND)
    set(value) = set(Calendar.SECOND, value)

/**
 * The value for [Calendar.MILLISECOND].
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.millisecond
    get() = get(Calendar.MILLISECOND)
    set(value) = set(Calendar.MILLISECOND, value)

/**
 * True if the hour is before noon.
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.isAM
    get() = get(Calendar.AM_PM) == Calendar.AM
    set(value) = set(Calendar.AM_PM, if (value) Calendar.AM else Calendar.PM)

/**
 * True if the hour is after noon.
 * @see Calendar.set
 * @see Calendar.get
 */
inline var Calendar.isPM
    get() = get(Calendar.AM_PM) == Calendar.PM
    set(value) = set(Calendar.AM_PM, if (value) Calendar.PM else Calendar.AM)