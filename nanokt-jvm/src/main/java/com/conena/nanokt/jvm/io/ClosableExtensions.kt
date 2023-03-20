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

package com.conena.nanokt.jvm.io

import java.io.Closeable

/**
 * Same as [Closeable.close] but swallows all exceptions.
 * Consider using [Closeable.use] when possible.
 */
inline fun Closeable.closeSilent() {
    try {
        close()
    } catch (_: Throwable) {}
}

/**
 * Close the [closeable] and swallow all exceptions.
 * Consider using [Closeable.use] when possible.
 */
inline fun closeCloseablesSilent(closeable: Closeable?) {
    closeable?.closeSilent()
}

/**
 * Close the [closeables] and swallow all exceptions.
 */
inline fun closeCloseablesSilent(vararg closeables: Closeable?) {
    closeables.forEach { it?.closeSilent() }
}