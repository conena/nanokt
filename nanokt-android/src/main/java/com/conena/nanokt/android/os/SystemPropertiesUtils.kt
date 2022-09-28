@file:JvmName(name = "SystemPropertiesUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os

import android.annotation.SuppressLint

/**
 * Retrieve values from the system properties store.
 * @param key The key or name of the property.
 * @return The value of the property as string or `null` if no value was found for the given property.
 */
@SuppressLint("PrivateApi")
inline fun getSystemProperty(key: String): String? {
    return try {
        val value = Class.forName("android.os.SystemProperties")
            .getMethod("get", String::class.java)
            .invoke(null, key) as? String
        if (value.isNullOrEmpty()) null else value
    } catch (_: Throwable) {
        null
    }
}

/**
 * `true` if the current device is running [MIUI](https://global.miui.com)
 */
inline fun isMiui() = getSystemProperty("ro.miui.ui.version.code") != null