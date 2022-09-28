@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

/**
 * The enum entry with the given [name]. `null` if no enum with the given name exists in [T].
 */
inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? {
    return try {
        enumValueOf<T>(name = name)
    } catch (e: IllegalArgumentException) {
        null
    }
}