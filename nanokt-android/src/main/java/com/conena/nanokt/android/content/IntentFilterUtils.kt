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

@file:JvmName(name = "IntentFilterUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.IntentFilter


/**
 * Add the given [actions] to the intent filter.
 * @param actions The actions to add.
 * @return The receiver object, for chaining multiple calls into a single statement.
 * @see IntentFilter.addAction
 */
inline fun IntentFilter.addActions(vararg actions: String): IntentFilter {
    for (action in actions) {
        addAction(action)
    }
    return this
}

/**
 * Add the given [categories] to the intent filter.
 * @param categories The categories to add.
 * @return The receiver object, for chaining multiple calls into a single statement.
 * @see IntentFilter.addCategory
 */
inline fun IntentFilter.addCategories(vararg categories: String): IntentFilter {
    for (category in categories) {
        addCategory(category)
    }
    return this
}

/**
 * Add the given [dataSchemes] to the intent filter.
 * @param dataSchemes The data scheme to add.
 * @return The receiver object, for chaining multiple calls into a single statement.
 * @see IntentFilter.addDataScheme
 */
inline fun IntentFilter.addDataSchemes(vararg dataSchemes: String): IntentFilter {
    for (dataScheme in dataSchemes) {
        addDataScheme(dataScheme)
    }
    return this
}

/**
 * Add the given [dataTypes] to the intent filter.
 * @param dataTypes The data types to add.
 * @return The receiver object, for chaining multiple calls into a single statement.
 * @throws IntentFilter.MalformedMimeTypeException If any of the given MIME types is
 * syntactically incorrect.
 * @see IntentFilter.addDataType
 */
@Throws(IntentFilter.MalformedMimeTypeException::class)
inline fun IntentFilter.addDataTypes(vararg dataTypes: String): IntentFilter {
    for (dataType in dataTypes) {
        addDataType(dataType)
    }
    return this
}