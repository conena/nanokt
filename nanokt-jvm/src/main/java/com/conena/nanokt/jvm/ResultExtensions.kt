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