@file:JvmName(name = "EditTextUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.widget

import android.widget.EditText

/**
 * Set the cursor to the start of the text.
 * @param T The type of the EditText.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : EditText> T.setCursorToStart(): T {
    setSelection(0)
    return this
}

/**
 * Set the cursor to the end of the text.
 * @param T The type of the EditText.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : EditText> T.setCursorToEnd(): T {
    setSelection(text.length)
    return this
}