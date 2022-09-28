@file:JvmName(name = "ActionBarUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.conena.nanokt.android.app

import androidx.appcompat.app.ActionBar

/**
 * Hide the ActionBar if it is currently showing.
 * Show the ActionBar if it is currently hidden.
 * @see ActionBar.isShowing
 * @see ActionBar.hide
 * @see ActionBar.show
 */
inline fun ActionBar.toggle() = if (isShowing) hide() else show()