@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

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