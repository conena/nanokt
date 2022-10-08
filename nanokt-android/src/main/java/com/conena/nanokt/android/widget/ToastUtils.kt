@file:JvmName(name = "ToastUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.StringRes

/**
 * Show a standard android toast with the given [text] for the given [duration].
 * @param text The text to display
 * @param duration Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG].
 */
@MainThread
inline fun Context.toast(text: String, duration: Int) = Toast.makeText(this, text, duration).show()

/**
 * Show a standard android toast with the given [text] for the given [duration].
 * @param text The text to display
 * @param duration Either [Toast.LENGTH_SHORT] or [Toast.LENGTH_LONG].
 * @throws Resources.NotFoundException If the resource can't be found.
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toast(@StringRes text: Int, duration: Int) = Toast.makeText(this, text, duration).show()

/**
 * Show a standard android toast with the given [text] for a short period of time.
 * @param text The text to display
 */
@MainThread
inline fun Context.toastShort(text: String) = toast(text = text, duration = Toast.LENGTH_SHORT)

/**
 * Show a standard android toast with the given [text] for a longer period of time.
 * @param text The text to display
 */
@MainThread
inline fun Context.toastLong(text: String) = toast(text = text, duration = Toast.LENGTH_LONG)

/**
 * Show a standard android toast with the given [text] for a short period of time.
 * @param text The text to display
 * @throws Resources.NotFoundException If the resource can't be found
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toastShort(@StringRes text: Int) = toast(text = text, duration = Toast.LENGTH_SHORT)

/**
 * Show a standard android toast with the given [text] for a longer period of time.
 * @param text The text to display
 * @throws Resources.NotFoundException If the resource can't be found
 */
@MainThread
@Throws(Resources.NotFoundException::class)
inline fun Context.toastLong(@StringRes text: Int) = toast(text = text, duration = Toast.LENGTH_LONG)