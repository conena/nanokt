@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.text.DateFormat
import java.util.Date

/**
 * Same as [DateFormat.format] but returns null if [date] is null.
 * @param date The date to format.
 * @return The formatted date time string or null if [date] was null.
 */
inline fun DateFormat.formatOrNull(date: Date?): String? {
   return format(date ?: return null)
}