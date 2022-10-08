@file:JvmName(name = "LifecycleUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.lifecycle

import androidx.lifecycle.Lifecycle

/**
 * True if the lifecycles current state is at least [Lifecycle.State.CREATED].
 */
inline val Lifecycle.isAtLeastCreated get() = currentState.isAtLeast(Lifecycle.State.CREATED)

/**
 * True if the lifecycles current state is at least [Lifecycle.State.STARTED].
 */
inline val Lifecycle.isAtLeastStarted get() = currentState.isAtLeast(Lifecycle.State.STARTED)

/**
 * True if the lifecycles current state is [Lifecycle.State.RESUMED].
 */
inline val Lifecycle.isResumed get() = currentState === Lifecycle.State.RESUMED