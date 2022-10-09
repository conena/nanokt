@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

/**
 * @return A [WeakReference] of the receiver.
 */
inline fun <T> T.toWeakReference(): WeakReference<T> = WeakReference(this)

/**
 * @return A [SoftReference] of the receiver.
 */
inline fun <T> T.toSoftReference(): SoftReference<T> = SoftReference(this)
