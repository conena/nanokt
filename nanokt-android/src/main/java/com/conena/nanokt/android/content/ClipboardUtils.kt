@file:JvmName(name = "ClipboardUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.os.Build
import android.os.PersistableBundle
import androidx.annotation.CheckResult

/**
 * Set the given [text] as primary clip on the clipboard.
 * This is the clip that is involved in normal cut and paste operations.
 * @param text The text to set as primary clip.
 * @param label User-visible label for the clip data.
 * @param isSensitive If `true` [ClipDescription.EXTRA_IS_SENSITIVE] is added to the [ClipData].
 * This will prevent that the [text] is shown in the clipboard preview in Android 13 and above.
 * [isSensitive] has no effect on android versions below 13!
 * @param onNotifyUser [The documentation recommends informing the user about copying to the clipboard.](https://developer.android.com/develop/ui/views/touch-and-input/copy-paste#duplicate-notifications)
 * As of Android 13, the system takes care of this.
 * [onNotifyUser] is only invoked when a hint is supposed to be given by the app (before Android 13).
 * @see ClipboardManager.setPrimaryClip
 */
inline fun ClipboardManager.setPrimaryClip(
    text: String,
    label: String? = null,
    isSensitive: Boolean = false,
    onNotifyUser: () -> Unit = {}
) {
    val clipData = ClipData.newPlainText(label, text)
    if (isSensitive && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        clipData.description.extras = PersistableBundle().apply {
            putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
        }
    }
    setPrimaryClip(clipData)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        onNotifyUser()
    }
}

/**
 * @return The current primary [ClipData.Item] on the clipboard.
 * If the application is not the default input method editor or does not have input focus `null` is returned.
 * @see ClipboardManager.getPrimaryClip
 */
@CheckResult
inline fun ClipboardManager.getPrimaryClipDataItem(): ClipData.Item? {
    return primaryClip?.getItemAt(0)
}

/**
 * @return `true` if the current primary clip on the clipboard has the mime type [ClipDescription.MIMETYPE_TEXT_PLAIN].
 * If the application is not the default input method editor or does not have input focus `false` is returned.
 */
@CheckResult
inline fun ClipboardManager.isPrimaryClipContentPlainText(): Boolean {
    return primaryClipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) ?: false
}

/**
 * @return `true` if the current primary clip on the clipboard is marked as sensitive.
 * If the application is not the default input method editor or does not have input focus `false` is returned.
 * Does always return `false` on android versions below android 13.
 * @see ClipDescription.EXTRA_IS_SENSITIVE
 */
@CheckResult
inline fun ClipboardManager.isPrimaryClipContentSensitive(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            primaryClipDescription?.extras?.getBoolean(
                ClipDescription.EXTRA_IS_SENSITIVE, false
            ) ?: false
}

/**
 * Clears the current primary clip on the clipboard.
 * @see ClipboardManager.clearPrimaryClip
 */
inline fun ClipboardManager.clearPrimaryClipCompat() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        clearPrimaryClip()
    }
    setPrimaryClip(text = "")
}