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

package com.conena.nanokt.text

/**
 * @return `null` if the current CharSequence is empty. Otherwise the CharSequence is returned.
 */
inline fun <T : CharSequence> T.emptyAsNull() = ifEmpty { null }

/**
 * @return `null` if the current CharSequence is blank. Otherwise the CharSequence is returned.
 */
inline fun <T : CharSequence> T.blankAsNull() = ifBlank { null }

/**
 * @return A new [StringBuilder] with the current CharSequence.
 */
inline fun CharSequence.toStringBuilder() = StringBuilder(this)

/**
 * @return A newly created string with [delimiter] added as prefix.
 */
inline fun String.addPrefix(delimiter: Char) = delimiter + this

/**
 * @return A newly created string with [delimiter] added as prefix.
 */
inline fun String.addPrefix(delimiter: String) = delimiter + this

/**
 * @return A newly created string with [delimiter] added as suffix.
 */
inline fun String.addSuffix(delimiter: Char) = this + delimiter

/**
 * @return A newly created string with [delimiter] added as suffix.
 */
inline fun String.addSuffix(delimiter: String) = this + delimiter

/**
 * @return A newly created string with [delimiter] added as suffix and prefix.
 */
inline fun String.addSurrounding(delimiter: Char) = delimiter + this + delimiter

/**
 * @return A newly created string with [delimiter] added as suffix and prefix.
 */
inline fun String.addSurrounding(delimiter: String) = delimiter + this + delimiter

/**
 * Same as [CharSequence.indexOf] but returns `null` if the [char] was not found.
 * @param char The character to look for.
 * @param startIndex The index (including) to start searching from.
 * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
 * @return The index of the first occurrence of [char] or `null` if none is found.
 */
inline fun CharSequence.indexOfOrNull(
    char: Char,
    startIndex: Int = 0,
    ignoreCase: Boolean = false
): Int? {
    val result = indexOf(char = char, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (result == -1) null else result
}

/**
 * Same as [CharSequence.indexOf] but returns `null` if the [string] was not found.
 * @param string The [String] to look for.
 * @param startIndex The index (including) to start searching from.
 * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
 * @return The index of the first occurrence of [string] or `null` if none is found.
 */
inline fun CharSequence.indexOfOrNull(
    string: String,
    startIndex: Int = 0,
    ignoreCase: Boolean = false
): Int? {
    val result = indexOf(string = string, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (result == -1) null else result
}

/**
 * Same as [CharSequence.lastIndexOf] but returns `null` if the [char] was not found.
 * @param char The character to look for.
 * @param startIndex The index of character to start searching at.
 * The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
 * @return The index of the last occurrence of [char] or `null` if none is found.
 */
inline fun CharSequence.lastIndexOfOrNull(
    char: Char,
    startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): Int? {
    val result = lastIndexOf(char = char, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (result == -1) null else result
}

/**
 * Same as [CharSequence.lastIndexOf] but returns `null` if the [string] was not found.
 * @param string The [String] to look for.
 * @param startIndex The index of character to start searching at.
 * The search proceeds backward toward the beginning of the string.
 * @param ignoreCase `true` to ignore character case when matching a character. By default `false`.
 * @return The index of the last occurrence of [string] or `null` if none is found.
 */
inline fun CharSequence.lastIndexOfOrNull(
    string: String,
    startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
): Int? {
    val result = lastIndexOf(string = string, startIndex = startIndex, ignoreCase = ignoreCase)
    return if (result == -1) null else result
}