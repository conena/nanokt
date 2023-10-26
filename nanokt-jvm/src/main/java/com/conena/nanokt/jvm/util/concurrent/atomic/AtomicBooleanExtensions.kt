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

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm.util.concurrent.atomic

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.KProperty

/**
 * @param value The value to set.
 */
inline operator fun AtomicBoolean.setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
    set(value)
}

/**
 * @return The current value of the receiver.
 */
inline operator fun AtomicBoolean.getValue(thisRef: Any?, property: KProperty<*>): Boolean {
    return get()
}