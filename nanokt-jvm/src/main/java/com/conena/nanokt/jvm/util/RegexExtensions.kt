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

import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * Compile the given pattern to a [Pattern] instance or return `null` if the pattern is invalid.
 * @param flags Match flags. A bit mask that can include
 * [Pattern.CASE_INSENSITIVE], [Pattern.MULTILINE], [Pattern.DOTALL],
 * [Pattern.UNICODE_CASE], [Pattern.CANON_EQ], [Pattern.UNIX_LINES],
 * [Pattern.LITERAL], [Pattern.UNICODE_CHARACTER_CLASS] and [Pattern.COMMENTS]
 * @return The compiled pattern or `null` if the string is not a valid [Pattern]
 * @throws IllegalArgumentException If unsupported bit values are set in [flags]
 */
inline fun String.toPatternOrNull(flags: Int = 0): Pattern? {
    return try {
        Pattern.compile(this, flags)
    } catch (e: PatternSyntaxException){
        null
    }
}