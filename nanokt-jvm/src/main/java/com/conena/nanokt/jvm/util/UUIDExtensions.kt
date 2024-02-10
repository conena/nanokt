/*
 * Copyright (C) 2024 Fabian Andera
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

@file:Suppress("NOTHING_TO_INLINE")

package com.conena.nanokt.jvm.util

import java.util.UUID

/**
 * @return true if the receiver string is a valid [UUID] as described in [UUID.toString].
 * @see UUID
 */
inline fun String.isUUID(): Boolean {
    return try {
        this == UUID.fromString(this).toString()
    } catch (_: Throwable) {
        false
    }
}

/**
 * Create a [UUID] from its string representation.
 * @return A [UUID] instance for the receiver string.
 * @throws IllegalArgumentException If the receiver string is not a valid [UUID]
 * as described in [UUID.toString].
 * @see UUID.fromString
 */
@Throws(IllegalArgumentException::class)
inline fun String.toUUID(): UUID {
    return UUID.fromString(this).also { uuid ->
        if (uuid.toString() != this) {
            throw IllegalArgumentException("$this is not a valid UUID.")
        }
    }
}

/**
 * Create a [UUID] from its string representation.
 * @return A [UUID] instance for the receiver string. Null in case the receiver string
 * is not a valid [UUID] as described in [UUID.toString]..
 * @see UUID.fromString
 */
inline fun String.toUUIDorNull(): UUID? {
    return try {
        UUID.fromString(this).let { uuid ->
            if (uuid.toString() == this) uuid else null
        }
    } catch (_: Throwable) {
        return null
    }
}