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

@file:JvmName(name = "ActivityUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.conena.nanokt.android.app

import android.app.Activity
import android.os.Build
import android.view.View

/**
 * `true` if the activity is currently in multi-window mode.
 * @see Activity.isInMultiWindowMode
 */
inline val Activity.isMultiWindowModeEnabled: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && isInMultiWindowMode

/**
 * The root view of the Activity. `null` if the root view could not be obtained.
 * @see android.R.id.content
 */
inline val Activity.contentView: View? get() = window?.decorView?.findViewById(android.R.id.content)

