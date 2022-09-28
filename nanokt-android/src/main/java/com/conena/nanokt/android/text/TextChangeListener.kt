@file:Suppress("unused")

package com.conena.nanokt.android.text

import android.text.Editable
import android.text.TextWatcher

/**
 * Implementation of [TextWatcher] that does only require to override [onTextChanged]
 */
interface TextChangeListener : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun afterTextChanged(s: Editable?) = Unit

}