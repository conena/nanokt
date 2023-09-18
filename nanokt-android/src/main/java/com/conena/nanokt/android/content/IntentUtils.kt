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

@file:JvmName(name = "IntentUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.CheckResult
import com.conena.nanokt.android.net.MimeType
import com.conena.nanokt.annotations.ExperimentalNanoKtApi

/**
 * The IntentCompanion functions are used internally by NanoKt.
 * They are placed in this object because only very few developers need them directly.
 * The usage is experimental because the object solution is not optimal.
 * It will be resolved as soon as statics are available in Kotlin.
 */
@ExperimentalNanoKtApi
object IntentCompanion {

    /**
     * Create an [Intent] to share plain text and/or an attachment.
     * How the individual parameters are interpreted depends on the application that is started with the intent.
     * @param subject The subject to send.
     * @param text The text to send.
     * @param attachment A content URI holding a stream of data to send.
     * @return The created intent.
     */
    @CheckResult
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
    @CheckResult
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

}

/**
 * Wrap the current intent into a new [Intent.ACTION_CHOOSER] intent.
 * @param title Optional title that will be displayed in the chooser. This does not work with actions
 * [Intent.ACTION_SEND] and [Intent.ACTION_SEND_MULTIPLE].
 * @return The new [Intent.ACTION_CHOOSER] intent.
 */
@CheckResult
inline fun Intent.chooser(title: CharSequence? = null): Intent = Intent.createChooser(this, title)


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
 * Similar to [Context.startActivity].
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param context The context used to start the activity.
 * @param options Additional options for how the Activity should be started.
 * @see Context.startActivity
 */
inline fun Intent.startActivity(
    context: Context,
    options: Bundle? = null
) {
    if (context !is Activity && (context !is ContextWrapper || context.baseContext !is Activity)) {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(this, options)
}

/**
 * Similar to [Context.startActivity] but instead of throwing an exception,
 * the exception is encapsulated in the returned result.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param context The context used to start the activity.
 * @param options Additional options for how the Activity should be started.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see Context.startActivity
 * @see Context.startActivityCatching
 */
inline fun Intent.startActivityCatching(
    context: Context,
    options: Bundle? = null
): Result<Unit> {
    return context.startActivityCatching(this, options)
}