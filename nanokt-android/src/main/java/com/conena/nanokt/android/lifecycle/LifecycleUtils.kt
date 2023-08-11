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

@file:JvmName(name = "LifecycleUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.lifecycle

import androidx.annotation.CheckResult
import androidx.lifecycle.Lifecycle

/**
 * True if the lifecycles current state is at least [Lifecycle.State.CREATED].
 */
@get:CheckResult
inline val Lifecycle.isAtLeastCreated get() = currentState.isAtLeast(Lifecycle.State.CREATED)

/**
 * True if the lifecycles current state is at least [Lifecycle.State.STARTED].
 */
@get:CheckResult
inline val Lifecycle.isAtLeastStarted get() = currentState.isAtLeast(Lifecycle.State.STARTED)

/**
 * True if the lifecycles current state is [Lifecycle.State.RESUMED].
 */
@get:CheckResult
inline val Lifecycle.isResumed get() = currentState === Lifecycle.State.RESUMED