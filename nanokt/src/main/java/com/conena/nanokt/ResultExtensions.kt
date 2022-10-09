@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

/**
 * Prints the stack trace of the exception if the result is [Result.isFailure] to the standard
 * error output.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T> Result<T>.printStackTraceOnFailure(): Result<T> {
    exceptionOrNull()?.printStackTrace()
    return this
}