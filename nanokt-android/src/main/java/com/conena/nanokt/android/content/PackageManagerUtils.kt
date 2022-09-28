@file:JvmName(name = "PackageManagerUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.CheckResult

/**
 * Check if an app is installed on the device.
 * Please be aware of the [changes to package visibility in Android 11](https://developer.android.com/training/package-visibility)
 * and declare the necessary queries in your manifest.
 * @param packageName The package name of the application to check.
 * @return `true` if the app is installed. `false` if the current app has no permission to check
 * if the app is installed or if it is actually not installed.
 */
@CheckResult
inline fun PackageManager.isAppInstalled(packageName: String): Boolean {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            @Suppress("DEPRECATION")
            getPackageInfo(packageName, 0)
        }
        true
    } catch (_: PackageManager.NameNotFoundException) {
        false
    }
}

