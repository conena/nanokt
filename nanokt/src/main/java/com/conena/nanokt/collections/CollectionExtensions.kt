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
 * @return `null` if the collection is empty or `null`. Otherwise the collection is returned.
 */
inline fun <T> Collection<T>.emptyAsNull(): Collection<T>? = ifEmpty { null }

/**
 * @return A list of all entries in [elements] that are of type [T].
 */
inline fun <reified T : Any> listOfInstanceOf(vararg elements: Any?): List<T> {
    return elements.filterIsInstance(T::class.java)
}

/**
 * @return A list containing [element] it it is of type [T]. Otherwise an empty list is returned.
 */
inline fun <reified T : Any> listOfInstanceOf(element: Any?): List<T> {
    return if (element is T) listOf(element) else emptyList()
}

/**
 * Remove or add an element based on the value of [add].
 * @param add `true` to add the element, `false` to remove the element.
 * @param element The element.
 * @return `true` if the collection was changed, `false` if the collection is unchanged.
 */
inline fun <T> MutableCollection<T>.addOrRemove(add: Boolean, element: T): Boolean {
    return if (add) add(element) else remove(element)
}

/**
 * Adds the element to the collection if it is not null.
 * @param element The element to add.
 * @return `true` if the element has been added, `false` if the element was null or the
 * collection does not support duplicates and the element is already contained in the collection.
 */
inline fun <T> MutableCollection<T>.addNotNull(element: T?): Boolean {
    return add(element = element ?: return false)
}

/**
 * Adds all elements to the collection that are not null.
 * @param elements The elements to add.
 * @return `true` if any of the specified elements was added to the collection,
 * `false` if the collection was not modified.
 */
inline fun <T> MutableCollection<T>.addAllNotNull(elements: Iterable<T?>): Boolean {
    var added = false
    for (item in elements)
        if (item != null && add(item)) added = true
    return added
}