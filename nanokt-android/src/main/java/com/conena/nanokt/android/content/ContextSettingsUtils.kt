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

@file:JvmName(name = "ContextSettingsUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi


/**
 * Helper class that provides getters for common values in [Settings.Global],[Settings.System] and [Settings.Secure].
 * Used to not bloat the autocomplete suggestions for context instances too much.
 * ### It is strongly discouraged to store instances of this class in variables.
 * If this is still desired, an ApplicationContext must have been used to obtain the instance.
 */
@JvmInline
value class NanoKtSettingsProvider @PublishedApi internal constructor(
    val context: Context
) {

    /**
     * `true` if USB-Debugging is enabled on the device.
     */
    @get:CheckResult
    inline val isAdbEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.ADB_ENABLED) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.ADB_ENABLED) == 1
            }
        }

    /**
     * `true` if airplane mode is enabled on the device.
     */
    @get:CheckResult
    inline val isAirplaneModeEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.AIRPLANE_MODE_ON) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.AIRPLANE_MODE_ON) == 1
            }
        }

    /**
     * `true` if "Always finish activities" is enabled on the device.
     * This means that the the activity manager will aggressively finish activities and processes
     * as soon as they are no longer needed.
     * It is a common source of weird behavior when users enable this option
     * without knowing what it actually does.
     */
    @get:CheckResult
    inline val isAlwaysFinishActivities: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.ALWAYS_FINISH_ACTIVITIES) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.ALWAYS_FINISH_ACTIVITIES) == 1
            }
        }

    /**
     * `true` if bluetooth is enabled on the device.
     */
    @get:CheckResult
    inline val isBluetoothEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.BLUETOOTH_ON) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.BLUETOOTH_ON) == 1
            }
        }

    /**
     * `true` if data roaming is enabled on the device.
     */
    @get:CheckResult
    inline val isDataRoamingEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.DATA_ROAMING) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.DATA_ROAMING) == 1
            }
        }

    /**
     * `true` if the developer options are enabled on the device.
     */
    @get:CheckResult
    inline val isDeveloperOptionsEnabled: Boolean
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        get() = getGlobalIntOrNull(Settings.Global.DEVELOPMENT_SETTINGS_ENABLED) == 1

    /**
     * The name of the device
     */
    @get:CheckResult
    inline val deviceName: String?
        @RequiresApi(Build.VERSION_CODES.N_MR1)
        get() = getGlobalStringOrNull(Settings.Global.DEVICE_NAME)

    /**
     * `true` if mobile data is enabled on the device.
     */
    @get:CheckResult
    inline val isMobileDataEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull("mobile_data") == 1
            } else {
                getSystemIntOrNull("mobile_data") == 1
            }
        }

    /**
     * `true` if wifi is enabled on the device.
     */
    @get:CheckResult
    inline val isWifiEnabled: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getGlobalIntOrNull(Settings.Global.WIFI_ON) == 1
            } else {
                @Suppress("DEPRECATION")
                getSystemIntOrNull(Settings.System.WIFI_ON) == 1
            }
        }

    /**
     * `true` if accessibility is enabled.
     */
    @get:CheckResult
    inline val isAccessibilityEnabled get() = getSecureIntOrNull(Settings.Secure.ACCESSIBILITY_ENABLED) == 1

    /**
     * The current screen brightness between 0 and 255.
     */
    @get:CheckResult
    inline val screenBrightness get() = getSystemIntOrNull(Settings.System.SCREEN_BRIGHTNESS)

    /**
     * Retrieve an integer settings value from [Settings.Global].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not an integer.
     */
    @CheckResult
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline fun getGlobalIntOrNull(name: String): Int? {
        return try {
            Settings.Global.getInt(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a long settings value from [Settings.Global].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a long.
     */
    @CheckResult
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline fun getGlobalLongOrNull(name: String): Long? {
        return try {
            Settings.Global.getLong(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a float settings value from [Settings.Global].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a float.
     */
    @CheckResult
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline fun getGlobalFloatOrNull(name: String): Float? {
        return try {
            Settings.Global.getFloat(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a settings value from [Settings.Global].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found.
     */
    @CheckResult
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline fun getGlobalStringOrNull(name: String): String? {
        return Settings.Global.getString(context.contentResolver, name)
    }

    /**
     * Retrieve an integer settings value from [Settings.System].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not an integer.
     */
    @CheckResult
    inline fun getSystemIntOrNull(name: String): Int? {
        return try {
            Settings.System.getInt(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a long settings value from [Settings.System].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a long.
     */
    @CheckResult
    inline fun getSystemLongOrNull(name: String): Long? {
        return try {
            Settings.System.getLong(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a float settings value from [Settings.System].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a float.
     */
    @CheckResult
    inline fun getSystemFloatOrNull(name: String): Float? {
        return try {
            Settings.System.getFloat(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a settings value from [Settings.System].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found.
     */
    @CheckResult
    inline fun getSystemStringOrNull(name: String): String? {
        return Settings.System.getString(context.contentResolver, name)
    }

    /**
     * Retrieve an integer settings value from [Settings.Secure].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not an integer.
     */
    @CheckResult
    inline fun getSecureIntOrNull(name: String): Int? {
        return try {
            Settings.Secure.getInt(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a long settings value from [Settings.Secure].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a long.
     */
    @CheckResult
    inline fun getSecureLongOrNull(name: String): Long? {
        return try {
            Settings.Secure.getLong(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a float settings value from [Settings.Secure].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found or the value is not a float.
     */
    @CheckResult
    inline fun getSecureFloatOrNull(name: String): Float? {
        return try {
            Settings.Secure.getFloat(context.contentResolver, name)
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * Retrieve a settings value from [Settings.Secure].
     * @param name The name of the setting.
     * @return The value of the setting. `null` if the setting was not found.
     */
    @CheckResult
    inline fun getSecureStringOrNull(name: String): String? {
        return Settings.Secure.getString(context.contentResolver, name)
    }

}

/**
 * Helper class that provides getters for common values in [Settings.Global],[Settings.System] and [Settings.Secure].
 */
@get:CheckResult
inline val Context.settings get() = NanoKtSettingsProvider(this)