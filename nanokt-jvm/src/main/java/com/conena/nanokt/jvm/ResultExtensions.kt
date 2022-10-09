@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.io.PrintStream
import java.io.PrintWriter

/**
 * Prints the stack trace of the exception if the result is [Result.isFailure] to the given [stream].
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T> Result<T>.printStackTraceOnFailure(stream: PrintStream): Result<T> {
    exceptionOrNull()?.printStackTrace(stream = stream)
    return this
}

/**
 * Prints the stack trace of the exception if the result is [Result.isFailure] to the given [writer].
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T> Result<T>.printStackTraceOnFailure(writer: PrintWriter): Result<T> {
    exceptionOrNull()?.printStackTrace(writer = writer)
    return this
}