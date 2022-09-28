@file:JvmName(name = "IntentUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import com.conena.nanokt.android.net.MimeType

/**
 * Wrap the current intent into a new [Intent.ACTION_CHOOSER] intent.
 * @param title Optional title that will be displayed in the chooser. This does not work with actions
 * [Intent.ACTION_SEND] and [Intent.ACTION_SEND_MULTIPLE].
 * @return The new [Intent.ACTION_CHOOSER] intent.
 */
inline fun Intent.chooser(title: CharSequence? = null): Intent = Intent.createChooser(this, title)

/**
 * Create a new intent filter with multiple actions
 * @param actions The intent actions to match against.
 * @return the created intent filter.
 */
inline fun createIntentFilter(vararg actions: String): IntentFilter {
    val filter = IntentFilter()
    for (action in actions) {
        filter.addAction(action)
    }
    return filter
}

/**
 * Add the given [actions] to the intent filter.
 * @param actions The actions to add.
 * @return The receiver object, for chaining multiple calls.
 * into a single statement.
 * @see IntentFilter.addAction
 */
inline fun IntentFilter.addActions(vararg actions: String): IntentFilter {
    for (action in actions) {
        addAction(action)
    }
    return this
}

/**
 * Set the data of the intent to an uri with the scheme "package"
 * and the [packageName] string as scheme-specific-part.
 * @param packageName The package name to use as scheme-specific-part.
 * @return The receiver object, for chaining multiple calls.
 * into a single statement.
 * @see Intent.setData
 */
inline fun Intent.setPackageUri(packageName: String): Intent {
    data = Uri.fromParts("package", packageName, null)
    return this
}

/**
 * Create an [Intent] to share plain text and/or an attachment.
 * How the individual parameters are interpreted depends on the application that is started with the intent.
 * @param subject The subject to send.
 * @param text The text to send.
 * @param attachment A content URI holding a stream of data to send.
 * @return The created intent.
 */
inline fun createSendIntent(
    subject: String? = null,
    text: String? = null,
    attachment: Uri? = null
): Intent {
    val intent = Intent(Intent.ACTION_SEND).setType(MimeType.PLAIN_TEXT)
    if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (text != null) intent.putExtra(Intent.EXTRA_TEXT, text)
    if (attachment != null) intent.putExtra(Intent.EXTRA_STREAM, attachment)
    return intent
}

/**
 * Create an [Intent] to start a mail application.
 * @param subject The subject of the mail.
 * @param body The body of the mail.
 * @param recipient The recipient's mail address.
 * @param attachment A content URI holding a stream of data to send.
 * @return The created intent.
 */
inline fun createMailSendIntent(
    subject: String? = null,
    body: String? = null,
    recipient: String? = null,
    attachment: Uri? = null
): Intent {
    val intent = Intent(Intent.ACTION_SEND).setType(MimeType.MAIL_RFC822)
    if (recipient != null) intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
    if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (body != null) intent.putExtra(Intent.EXTRA_TEXT, body)
    if (attachment != null) intent.putExtra(Intent.EXTRA_STREAM, attachment)
    return intent
}
