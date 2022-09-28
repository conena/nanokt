@file:JvmName(name = "ActivityUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.conena.nanokt.android.app

import android.app.Activity
import android.os.Build
import android.view.View

/**
 * `true` if the activity is currently in multi-window mode.
 * @see Activity.isInMultiWindowMode
 */
inline val Activity.isMultiWindowModeEnabled: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && isInMultiWindowMode

/**
 * The root view of the Activity. `null` if the root view could not be obtained.
 * @see android.R.id.content
 */
inline val Activity.contentView: View? get() = window?.decorView?.findViewById(android.R.id.content)

