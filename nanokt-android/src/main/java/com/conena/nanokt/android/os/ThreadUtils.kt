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

@file:JvmName(name = "ThreadUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os


import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.annotation.RestrictTo
import com.conena.nanokt.android.ExperimentalNanoKtAndroidApi
import java.util.concurrent.TimeUnit

/**
 * ThreadUtils companion object to avoid creating new [Handler] instances again and again.
 */
@ExperimentalNanoKtAndroidApi
@RestrictTo(RestrictTo.Scope.LIBRARY)
object NanoKtThreadUtilsCompanion {
    val mainHandler: Handler by lazy { Handler(Looper.getMainLooper()) }
}

/**
 * Invokes [block] on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun runOnMainThread(@MainThread block: Runnable) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block.run()
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post(block)
    }
}

/**
 * Invokes [block] on the main thread after the specified [delay] in the specified [unit].
 */
@ExperimentalNanoKtAndroidApi
inline fun runOnMainThread(
    delay: Long,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    @MainThread block: Runnable
) {
    NanoKtThreadUtilsCompanion.mainHandler.postDelayed(block, unit.toMillis(delay))
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun Function0<Unit>.invokeOnMainThread() {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke()
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post(this)
    }
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun <P1> Function1<P1, Unit>.invokeOnMainThread(p1: P1) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke(p1)
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post {
            invoke(p1)
        }
    }
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun <P1, P2> Function2<P1, P2, Unit>.invokeOnMainThread(p1: P1, p2: P2) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke(p1, p2)
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post {
            invoke(p1, p2)
        }
    }
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun <P1, P2, P3> Function3<P1, P2, P3, Unit>.invokeOnMainThread(p1: P1, p2: P2, p3: P3) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke(p1, p2, p3)
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post {
            invoke(p1, p2, p3)
        }
    }
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun <P1, P2, P3, P4> Function4<P1, P2, P3, P4, Unit>.invokeOnMainThread(p1: P1, p2: P2, p3: P3, p4: P4) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke(p1, p2, p3, p4)
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post {
            invoke(p1, p2, p3, p4)
        }
    }
}

/**
 * Invokes the receiver on the main thread.
 */
@ExperimentalNanoKtAndroidApi
inline fun <P1, P2, P3, P4, P5> Function5<P1, P2, P3, P4, P5, Unit>.invokeOnMainThread(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        invoke(p1, p2, p3, p4, p5)
    } else {
        NanoKtThreadUtilsCompanion.mainHandler.post {
            invoke(p1, p2, p3, p4, p5)
        }
    }
}