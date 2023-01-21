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

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Invokes [block] with parameters [first] and [second] if both are non `null`.
 * @param F The type of the first [block] parameter.
 * @param S The type of the second [block] parameter.
 * @param R The type of the [block] result.
 * @param first First [block] parameter.
 * @param second Second [block] parameter.
 * @param block Invoked if [first] and [second] are non `null`.
 * @return `null` if either [first] or [second] is `null`. Otherwise the result of [block] is returned.
 */
inline fun <F, S, R> biLetNonNull(first: F?, second: S?, block: (F, S) -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return if (first == null || second == null) null else block(first, second)
}

/**
 * Invokes [block] with parameters [first], [second] and [third] if all are non `null`.
 * @param F The type of the first [block] parameter.
 * @param S The type of the second [block] parameter.
 * @param T The type of the third [block] parameter.
 * @param R The type of the [block] result.
 * @param first First [block] parameter.
 * @param second Second [block] parameter.
 * @param third Third [block] parameter.
 * @param block Invoked if [first], [second] and [third] are non `null`.
 * @return `null` if either [first], [second] or [third] is `null`. Otherwise the result of [block] is returned.
 */
inline fun <F, S, T, R> triLetNonNull(first: F?, second: S?, third: T?, block: (F, S, T) -> R): R? {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return if (first == null || second == null || third == null) null else block(first, second, third)
}

/**
 * Cast the current value to [T].
 * @param T The type to cast to.
 * @throws ClassCastException If the cast failed
 */
@Throws(ClassCastException::class)
inline fun <reified T> Any?.cast() = this as T

/**
 * Cast the current value to [T]. Return `null` on failure.
 * @param T The type to cast to.
 */
inline fun <reified T> Any?.castOrNull() = this as? T

/**
 * @param then The value returned if the current instance is `null`.
 * @return The current instance if not `null` else [then].
 */
inline fun <T : Any> T?.ifNull(then: T): T {
    return this ?: then
}

/**
 * @param block Called when the current instance is `null`.
 * @return The current instance if not `null` else the result of [block].
 */
inline fun <T : Any> T?.ifNull(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    return this ?: block()
}

/**
 * Invokes [block] and swallows any [Throwable].
 */
inline fun <T> runSilent(block: () -> T) {
    try { block() } catch (_: Throwable) {}
}

/**
 * Invokes [block] and returns the result or `null` if any [Throwable] was thrown.
 */
inline fun <T> runSilentAndGet(block: () -> T): T? {
    return try {
        block()
    } catch (_: Throwable) {
        null
    }
}

/**
 * Same as [apply] but swallows all throwables thrown in [block].
 */
inline fun <T> T.applySilent(block: T.() -> Unit): T {
    try {
        block()
    } catch (_: Throwable) {}
    return this
}

/**
 * Same as [also] but swallows all throwables thrown in [block].
 */
inline fun <T> T.alsoSilent(block: (T) -> Unit): T {
    try {
        block(this)
    } catch (_: Throwable) {}
    return this
}