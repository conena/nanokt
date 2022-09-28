@file:JvmName(name = "BuildUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os

import android.os.Build
import androidx.annotation.CheckResult
import androidx.annotation.ChecksSdkIntAtLeast

/**
 * @param sdkVersion The sdk version to check
 * @return `true` if the [Build.VERSION.SDK_INT] is [sdkVersion] or higher.
 * @see Build.VERSION.SDK_INT
 * @see Build.VERSION_CODES
 */
@ChecksSdkIntAtLeast(parameter = 0)
@CheckResult
inline fun isMinSdk(sdkVersion: Int): Boolean {
    return Build.VERSION.SDK_INT >= sdkVersion
}

