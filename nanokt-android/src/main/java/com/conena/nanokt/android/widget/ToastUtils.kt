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

@file:JvmName(name = "ToastUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import java.lang.ref.WeakReference

/**
 * Helper object to prevent toasts from stacking.
 */
object NanoKtToastCompanion {

    /**
     * A [WeakReference] to the last set [Toast].
     * @see NanoKtToastCompanion.cancelAndSet
     */
    @PublishedApi
    internal var toastReference = WeakReference<Toast>(null)

    /**
     * Cancel the last toast and set a new toast.
     * This method does not call [Toast.show]!
     * @param toast The new toast to set.
     * @return The new toast.
     */
    inline fun cancelAndSet(toast: Toast): Toast {
        toastReference.get()?.cancel()
        toastReference = WeakReference(toast)
        return toast
    }

}

/**
 * Show a standard android toast with the given [text] for the given [duration].
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 * @param duration Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG].
 */
@MainThread
inline fun Context.toast(text: CharSequence, duration: Int) {
    NanoKtToastCompanion.cancelAndSet(Toast.makeText(this, text, duration)).show()
}

/**
 * Show a standard android toast with the given [text] for the given [duration].
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 * @param duration Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG].
 * @throws Resources.NotFoundException If the resource can't be found.
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toast(@StringRes text: Int, duration: Int) = toast(getText(text), duration)

/**
 * Show a standard android toast with the given [text] for a short period of time.
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 */
@MainThread
inline fun Context.toastShort(text: CharSequence) = toast(text = text, duration = Toast.LENGTH_SHORT)

/**
 * Show a standard android toast with the given [text] for a longer period of time.
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 */
@MainThread
inline fun Context.toastLong(text: CharSequence) = toast(text = text, duration = Toast.LENGTH_LONG)

/**
 * Show a standard android toast with the given [text] for a short period of time.
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 * @throws Resources.NotFoundException If the resource can't be found
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toastShort(@StringRes text: Int) = toast(text = text, duration = Toast.LENGTH_SHORT)

/**
 * Show a standard android toast with the given [text] for a longer period of time.
 * Uses [NanoKtToastCompanion] to prevent toasts from stacking.
 * @param text The text to display.
 * @throws Resources.NotFoundException If the resource can't be found
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toastLong(@StringRes text: Int) = toast(text = text, duration = Toast.LENGTH_LONG)