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

package com.conena.nanokt.collections

/**
 * Same as [Map.forEach] but does also work below Java 8.
 * @param action The action to perform on each entry.
 */
inline fun <K, V> Map<K, V>.forEachCompat(action: (key: K, value: V) -> Unit) {
    for (mapEntry in this) {
        action(mapEntry.key, mapEntry.value)
    }
}