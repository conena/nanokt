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

package com.conena.nanokt.jvm.util

import java.util.SortedMap

/**
 * @return The first element.
 * @throws NoSuchElementException If the map is empty.
 */
@Throws(NoSuchElementException::class)
inline fun <K, V> SortedMap<K, V>.first(): V {
    return get(firstKey()) ?: throw NoSuchElementException()
}

/**
 * @return The last element.
 * @throws NoSuchElementException If the map is empty.
 */
@Throws(NoSuchElementException::class)
inline fun <K, V> SortedMap<K, V>.last(): V {
    return get(lastKey()) ?: throw NoSuchElementException()
}

/**
 * @return The first element or null if the map is empty.
 */
inline fun <K, V> SortedMap<K, V>.firstOrNull(): V? {
    return try {
        get(firstKey())
    } catch (_: NoSuchElementException) {
        null
    }
}

/**
 * @return The last element or null if the map is empty.
 */
inline fun <K, V> SortedMap<K, V>.lastOrNull(): V? {
    return try {
        get(lastKey())
    } catch (_: NoSuchElementException) {
        null
    }
}

/**
 * @return The first (lowest) key in this map or null if the map is empty.
 */
inline fun <K, V> SortedMap<K, V>.firstKeyOrNull(): K? {
    return try {
        firstKey()
    } catch (_: NoSuchElementException) {
        null
    }
}

/**
 * @return The last (highest) key in this map or null if the map is empty.
 */
inline fun <K, V> SortedMap<K, V>.lastKeyOrNull(): K? {
    return try {
        lastKey()
    } catch (_: NoSuchElementException) {
        null
    }
}