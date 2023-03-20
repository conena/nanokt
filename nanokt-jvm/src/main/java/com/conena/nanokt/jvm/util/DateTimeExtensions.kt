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

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Same as [DateFormat.format] but returns null if [date] is null.
 * @param date The date to format.
 * @return The formatted date time string or null if [date] was null.
 */
inline fun DateFormat.formatOrNull(date: Date?): String? {
   return format(date ?: return null)
}

/**
 * Creates a new [Date] instance with the receiver as epoch timestamp.
 */
inline fun Long.toDate() = Date(this)

/**
 * @param format The [DateFormat] used to format the receiver.
 * @return The formatted string.
 */
inline fun Date.format(format: DateFormat): String {
   return format.format(this)
}

/**
 * This method creates a new [SimpleDateFormat] instance with the given pattern and formats the receiver with it.
 * You should not use this method if you need the same pattern repeatedly (e.g. in a loop).
 * In this case it is more effective to store the [SimpleDateFormat] as variable and call [format] with it.
 * @param pattern The pattern used to format the receiver. Internally a [SimpleDateFormat] is used.
 * @param locale The locale whose symbols should be used for the date format.
 * @return The formatted string.
 */
inline fun Date.format(pattern: String, locale: Locale = Locale.getDefault()): String {
   return SimpleDateFormat(pattern, locale).format(this)
}