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

package com.conena.nanokt

/**
 * Same as [Iterable.indexOf] but returns `null` if the [element] does not exist in the collection.
 * @param element The element to search for.
 * @return The first index of [element]. `null` if the element does not exist in the collection.
 */
inline fun <T> Iterable<T>.indexOfOrNull(element: T): Int? {
    val result = indexOf(element)
    return if (result == -1) null else result
}

/**
 * Same as [Iterable.indexOfFirst] but returns `null` if the [predicate] does not match any element in the collection.
 * @param predicate The predicate to match the elements against.
 * @return The index of the first element matching the [predicate],
 * or `null` if the collection does not contain such an element.
 */
inline fun <T> Iterable<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    val result = indexOfFirst(predicate)
    return if (result == -1) null else result
}

/**
 * Same as [Collection.lastIndexOf] but returns `null` if the [element] does not exist in the collection.
 * @param element The element to search for.
 * @return The last index of [element]. `null` if the element does not exist in the collection.
 */
inline fun <T> Iterable<T>.lastIndexOfOrNull(element: T): Int? {
    val result = lastIndexOf(element)
    return if (result == -1) null else result
}

/**
 * Same as [Iterable.indexOfLast] but returns `null` if the [predicate] does not match any element in the collection.
 * @param predicate The predicate to match the elements against.
 * @return The index of the last element matching the [predicate],
 * or `null` if the collection does not contain such an element.
 */
inline fun <T> Iterable<T>.indexOfLastOrNull(predicate: (T) -> Boolean): Int? {
    val result = indexOfLast(predicate)
    return if (result == -1) null else result
}

/**
 * Same as [MutableIterable.removeAll] but does return a list containing the removed elements.
 * @param predicate Predicate that returns `true` for entries that should be removed.
 * @return A [List] containing the removed elements.
 */
inline fun <T> MutableIterable<T>.removeAllAndGet(predicate: (T) -> Boolean): List<T> {
    val removed = mutableListOf<T>()
    val each = iterator()
    while (each.hasNext()) {
        val next = each.next()
        if (predicate.invoke(next)) {
            each.remove()
            removed.add(next)
        }
    }
    return removed
}