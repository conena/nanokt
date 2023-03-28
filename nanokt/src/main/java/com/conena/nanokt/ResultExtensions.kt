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
 * Prints the stack trace of the exception if the result is [Result.isFailure] to the standard
 * error output.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T> Result<T>.printStackTraceOnFailure(): Result<T> {
    exceptionOrNull()?.printStackTrace()
    return this
}

/**
 * @param transform The method to transform the encapsulated [Throwable].
 * @return Transforms the encapsulated throwable of the current Result into a new Result
 * using the [transform] function if it is [failure][Result.isFailure],
 * otherwise it returns the current Result unchanged.
 */
inline fun <T> Result<T>.mapFailure(transform: (Throwable) -> Throwable): Result<T> {
    return Result.failure(transform(exceptionOrNull() ?: return this))
}

/**
 * @return Same as [Result.getOrNull].
 */
inline operator fun <T> Result<T>.component1(): T? = getOrNull()

/**
 * @return Same as [Result.exceptionOrNull].
 */
inline operator fun <T> Result<T>.component2(): Throwable? = exceptionOrNull()