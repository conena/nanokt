@file:JvmName(name = "TextUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.text

import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.Spanned
import androidx.annotation.CheckResult

/**
 * @return An [Editable] with the content of the current char-sequence.
 */
@CheckResult
inline fun CharSequence.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

/**
 * Check if the receiver is a valid filename on Android and Linux systems using ext2/ext3/ext4 or F2FS.
 * More precisely, this method checks if the receiver is not empty,
 * does not contain a reserved character ('/' or 'NUL') and is not larger than 255 bytes (UTF-8).
 * @return True if the name is valid, false otherwise.
 */
fun String.isValidFileName(): Boolean {
    return this != "" && !contains('/') && !contains('\u0000') && encodeToByteArray().size <= 255
}

/**
 * Interprets the receiver as HTML string and returns displayable styled text.
 * @param imageGetter Any <img> tags in the HTML will use this [Html.ImageGetter]
 * to request a representation of the image.
 * @param tagHandler To handle unknown tags.
 * @param legacy `true` to use [Html.FROM_HTML_MODE_LEGACY] instead of [Html.FROM_HTML_MODE_COMPACT]
 * starting from [Build.VERSION_CODES.N]. On older versions [Html.FROM_HTML_MODE_LEGACY] is always used.
 * @return The displayable styled text.
 */
@CheckResult
inline fun String.toHtml(
    imageGetter: Html.ImageGetter? = null,
    tagHandler: Html.TagHandler? = null,
    legacy: Boolean = false,
): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(
            this,
            if (legacy) Html.FROM_HTML_MODE_LEGACY else Html.FROM_HTML_MODE_COMPACT,
            imageGetter,
            tagHandler
        )
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this, imageGetter, tagHandler)
    }
}
