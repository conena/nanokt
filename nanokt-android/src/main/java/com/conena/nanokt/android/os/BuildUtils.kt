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

