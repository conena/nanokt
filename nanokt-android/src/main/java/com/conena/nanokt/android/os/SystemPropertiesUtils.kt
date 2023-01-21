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