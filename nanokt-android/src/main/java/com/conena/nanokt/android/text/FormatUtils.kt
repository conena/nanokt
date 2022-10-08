@file:JvmName(name = "FormatUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.text

import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import com.conena.nanokt.android.ExperimentalNanoKtAndroidApi
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
@ExperimentalNanoKtAndroidApi
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