@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.lang.ref.WeakReference

/**
 * @return A [WeakReference] of the receiver.
 */
inline fun <T> T.toWeakReference(): WeakReference<T> = WeakReference(this)
