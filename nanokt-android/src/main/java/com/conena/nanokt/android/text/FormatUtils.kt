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

@file:JvmName(name = "FormatUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.text

import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import com.conena.nanokt.annotations.ExperimentalNanoKtApi
import java.text.SimpleDateFormat
import java.util.*

/**
 * @param skeleton A skeleton similar to, and uses the same format characters as, a Unicode
 * [UTS#35 Pattern](http://www.unicode.org/reports/tr35/#Date_Format_Patterns).
 * @param locale The locale into which the skeleton should be localized.
 * @return The best possible localized [SimpleDateFormat] of the given skeleton for the given locale.
 * @see DateFormat.getBestDateTimePattern
 * @see Locale.getSuitableDateTimeFormat
 */
@ExperimentalNanoKtApi
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun getSuitableDateTimeFormat(
    skeleton: String,
    locale: Locale = Locale.getDefault()
): SimpleDateFormat {
    return SimpleDateFormat(DateFormat.getBestDateTimePattern(locale, skeleton), locale)
}

/**
 * @param skeleton A skeleton similar to, and uses the same format characters as, a Unicode
 * [UTS#35 Pattern](http://www.unicode.org/reports/tr35/#Date_Format_Patterns).
 * @return The best possible localized [SimpleDateFormat] of the given skeleton for the receiver.
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun Locale.getSuitableDateTimeFormat(skeleton: String): SimpleDateFormat {
    return SimpleDateFormat(DateFormat.getBestDateTimePattern(this, skeleton), this)
}

/**
 * This method creates a new [SimpleDateFormat] instance with a pattern created from the given skeleton
 * and locale and formats the receiver with it.
 * You should not use this method if you need the same pattern repeatedly (e.g. in a loop).
 * In this case it is more effective to call [getSuitableDateTimeFormat], store the [SimpleDateFormat]
 * as variable and call [format] with it.
 * @param skeleton A skeleton similar to, and uses the same format characters as, a Unicode
 * [UTS#35 Pattern](http://www.unicode.org/reports/tr35/#Date_Format_Patterns).
 * @param locale The locale into which the skeleton should be localized.
 * @return The date formatted with the best possible localized format of the given skeleton for the given locale.
 * @see Locale.getSuitableDateTimeFormat
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@CheckResult
inline fun Date.formatSuitable(skeleton: String, locale: Locale = Locale.getDefault()): String {
    return locale.getSuitableDateTimeFormat(skeleton = skeleton).format(this)
}