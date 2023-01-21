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

@file:JvmName(name = "BundleUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os


import android.os.BaseBundle
import android.os.Build
import android.os.Bundle
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import com.conena.nanokt.android.ExperimentalNanoKtAndroidApi


/**
 * @param T The type of the value.
 * @param key The key or `null`.
 * @return The value associated with the given key.
 * Returns `null` if no value is associated with the given key,
 * a `null` value is associated with the given key or the value is not from type [T].
 */
@CheckResult
inline fun <reified T> Bundle.getOrNull(key: String?): T? {
    return this.get(key) as? T?
}

/**
 * @param T The type of the value.
 * @param key The key or `null`.
 * @param defaultValue The default value is returned when called on a `null` reference,
 * no value is associated with the given key, a `null` value is associated with the given key
 * or the value is not from type [T].
 * @return The value associated with the given key otherwise the [defaultValue].
 */
@CheckResult
@ExperimentalNanoKtAndroidApi
inline fun <reified T> Bundle?.get(key: String?, defaultValue: T): T {
    return (this?.get(key) as? T?) ?: defaultValue
}

/**
 * @param T The type of the value.
 * @param key The key or `null`.
 * @return The value associated with the given key.
 * Returns `null` if no value is associated with the given key,
 * a `null` value is associated with the given key or the value is not from type [T].
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@CheckResult
inline fun <reified T> BaseBundle.getOrNull(key: String?): T? {
    return this.get(key) as? T?
}

/**
 * @param T The type of the value.
 * @param key The key or `null`.
 * @param defaultValue The default value is returned when called on a `null` reference,
 * no value is associated with the given key, a `null` value is associated with the given key
 * or the value is not from type [T].
 * @return The value associated with the given key otherwise the [defaultValue].
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@CheckResult
@ExperimentalNanoKtAndroidApi
inline fun <reified T> BaseBundle?.get(key: String?, defaultValue: T): T {
    return (this?.get(key) as? T?) ?: defaultValue
}