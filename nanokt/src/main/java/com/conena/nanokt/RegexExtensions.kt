@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

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
inline fun String?.toPatternOrNull(flags: Int = 0): Pattern? {
    return try {
        Pattern.compile(this ?: return null, flags)
    } catch (e: PatternSyntaxException){
        null
    }
}