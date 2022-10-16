@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

/**
 * Same as calling [Runnable.run].
 */
inline operator fun Runnable.invoke() {
    run()
}