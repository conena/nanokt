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

@file:JvmName(name = "ContextUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Application
import android.app.PendingIntent
import android.app.Service
import android.content.*
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.net.toUri
import com.conena.nanokt.android.net.UriCompanion
import com.conena.nanokt.android.util.isColorTypeCompat
import com.conena.nanokt.annotations.ExperimentalNanoKtApi
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * The application label of this context's package.
 */
@get:CheckResult
inline val Context.applicationLabel: String get() = applicationInfo.loadLabel(packageManager).toString()

/**
 * The [PackageInfo] of this context's package.
 * @throws IllegalStateException In case the [PackageInfo] is not found.
 * This should never happen, but for the sake of completeness it is documented.
 */
@get:CheckResult
@get:Throws(IllegalStateException::class)
inline val Context.packageInfo: PackageInfo
    get() {
        return try {
            packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            throw IllegalStateException("Failed to find own package '$packageName'.", e)
        }
    }

/**
 * The content of the clipboard's primary clip as text.
 * `null` if the application is not the default input method editor or does not have input focus.
 * @see ClipData.Item.coerceToText
 * @see ClipboardManager.getPrimaryClipDataItem
 */
@get:CheckResult
inline var Context.clipboardTextContent: String?
    get() = clipboardManager.getPrimaryClipDataItem()?.coerceToText(this)?.toString()
    set(value) = clipboardManager.setPrimaryClip(text = value ?: "")

/**
 * `true` if the app is currently at the top of the screen that the user is interacting with.
 * @see RunningAppProcessInfo.IMPORTANCE_FOREGROUND
 */
@get:CheckResult
inline val Context.isAppInForeground: Boolean
    get() {
        return activityManager.runningAppProcesses.orEmpty().any { process ->
            process.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && process.uid == Process.myUid()
        }
    }

/**
 * The current configuration for the application's package resources instance.
 */
@get:CheckResult
inline val Context.configuration: Configuration get() = resources.configuration

/**
 * A [SharedPreferences] instance that points to the default file that is used by
 * the preference framework in the given context.
 * The returned instance points to the same preferences file as the
 * PreferenceManager.getDefaultSharedPreferences(receiver) method from the framework and the
 * AndroidX preferences library.
 * @see Context.getSharedPreferences
 */
@get:CheckResult
inline val Context.defaultSharedPreferences: SharedPreferences
    get() = getSharedPreferences("${packageName}_preferences", Context.MODE_PRIVATE)

/**
 * Opens various settings activities.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param action An action to open a settings screen. Some examples are listed below.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see Settings.ACTION_SETTINGS
 * @see Settings.ACTION_WIRELESS_SETTINGS
 * @see Settings.ACTION_AIRPLANE_MODE_SETTINGS
 * @see Settings.ACTION_ACCESSIBILITY_SETTINGS
 * @see Settings.ACTION_USAGE_ACCESS_SETTINGS
 * @see Settings.ACTION_SECURITY_SETTINGS
 * @see Settings.ACTION_PRIVACY_SETTINGS
 * @see Settings.ACTION_VPN_SETTINGS
 * @see Settings.ACTION_WIFI_SETTINGS
 * @see Settings.ACTION_BLUETOOTH_SETTINGS
 * @see Settings.ACTION_DATE_SETTINGS
 * @see Settings.ACTION_SOUND_SETTINGS
 * @see Settings.ACTION_DISPLAY_SETTINGS
 * @see Settings.ACTION_AUTO_ROTATE_SETTINGS
 * @see Settings.ACTION_APPLICATION_SETTINGS
 * @see Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
 * @see Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
 * @see Settings.ACTION_INTERNAL_STORAGE_SETTINGS
 */
inline fun Context.startSettings(
    action: String = Settings.ACTION_SETTINGS,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = Intent(action),
        intentEditor = intentEditor
    )
}

/**
 * Opens various settings activities for an application.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param action One of the actions below to get to a specific settings screen.
 * @param packageName The package name for which the settings page should be displayed.
 * By default, the package name is determined from the calling context.
 * @param setPackageUri For some actions like [Settings.ACTION_APPLICATION_DETAILS_SETTINGS]
 * it is not enough to specify the package name in the Intent Extras.
 * It must be supplied as a data Uri. Set this parameter to true to do this.
 * See the documentation for each action to find out if the Uri is required or not.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see Settings.ACTION_APP_LOCALE_SETTINGS
 * @see Settings.ACTION_APP_NOTIFICATION_SETTINGS
 * @see Settings.ACTION_APP_NOTIFICATION_BUBBLE_SETTINGS
 * @see Settings.ACTION_APP_OPEN_BY_DEFAULT_SETTINGS
 * @see Settings.ACTION_APP_USAGE_SETTINGS
 * @see Settings.ACTION_APPLICATION_DETAILS_SETTINGS
 * @see Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS
 * @see Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
 * @see Settings.ACTION_MANAGE_OVERLAY_PERMISSION
 * @see Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
 * @see Settings.ACTION_MANAGE_WRITE_SETTINGS
 * @see Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
 * @see Settings.ACTION_REQUEST_MANAGE_MEDIA
 * @see Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
 * @see Settings.ACTION_REQUEST_SET_AUTOFILL_SERVICE
 */
@SuppressLint("InlinedApi")
inline fun Context.startAppSettings(
    action: String = Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
    packageName: String = getPackageName(),
    setPackageUri: Boolean = action == Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = Intent(action)
            .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            .putExtra(Intent.EXTRA_PACKAGE_NAME, packageName)
            .apply {
                if (setPackageUri) setPackageUri(packageName)
            },
        intentEditor = intentEditor
    )
}

/**
 * Open the app notification channel settings for a specific channel.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param channelId The ID of the channel for which the settings are to be displayed.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
@RequiresApi(Build.VERSION_CODES.O)
inline fun Context.startAppNotificationChannelSettings(
    channelId: String,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
            .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            .putExtra(Settings.EXTRA_CHANNEL_ID, channelId),
        intentEditor = intentEditor
    )
}

/**
 * Launch a new activity.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param T The Activity to start.
 * @param options Additional options for how the Activity should be started.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * @see Context.startActivity
 */
@Throws(ActivityNotFoundException::class)
inline fun <reified T : Activity> Context.startActivity(
    options: Bundle? = null,
    intentEditor: Intent.() -> Unit = {}
) {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    val intent = Intent(this, T::class.java)
    if (this !is Activity) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent.also(intentEditor), options)
}

/**
 * Similar to [Context.startActivity] but instead of throwing an exception,
 * the exception is encapsulated in the returned result.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param intent The description of the activity to start.
 * @param options Additional options for how the Activity should be started.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see Context.startActivity
 */
inline fun Context.startActivityCatching(
    intent: Intent,
    options: Bundle? = null
): Result<Unit> {
    if (this !is Activity && (this !is ContextWrapper || this.baseContext !is Activity)) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    return kotlin.runCatching {
        startActivity(intent, options)
    }
}

/**
 * Internal version of [startActivityCatching] that takes an [intentEditor] lambda as last parameter.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param intent The description of the activity to start.
 * @param options Additional options for how the Activity should be started.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see Context.startActivity
 * @see Context.startActivityCatching
 */
@PublishedApi
internal inline fun Context.startActivityCatchingWithIntentEditor(
    intent: Intent,
    options: Bundle? = null,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    if (this !is Activity && (this !is ContextWrapper || this.baseContext !is Activity)) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    intent.also(intentEditor)
    return kotlin.runCatching {
        startActivity(intent, options)
    }
}

/**
 * Opens the Play Store overview page for the given app.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @param browserAsFallback `true` to try opening the Play Store in the browser (or any other app
 * that can handle the intent) if opening the Play Store app was unsuccessful.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startPlayStoreForApp(
    packageName: String = this.packageName,
    referrer: String? = null,
    browserAsFallback: Boolean = true,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.AT_LEAST_ONCE)
    }
    return startPlayStoreInternal(
        uri = UriCompanion.createPlayStoreUriForApp(packageName = packageName, referrer = referrer),
        browserAsFallback = browserAsFallback,
        intentEditor = intentEditor
    )
}

/**
 * Opens the Play Store overview page for the given developer.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param developerName The unique developer id.
 * @param browserAsFallback `true` to try opening the Play Store in the browser (or any other app
 * that can handle the intent) if opening the Play Store app was unsuccessful.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startPlayStoreForDeveloper(
    developerName: String,
    browserAsFallback: Boolean = true,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.AT_LEAST_ONCE)
    }
    return startPlayStoreInternal(
        uri = UriCompanion.createPlayStoreUriForDeveloper(developerName = developerName),
        browserAsFallback = browserAsFallback,
        intentEditor = intentEditor
    )
}

/**
 * Internal method to start the Google Play Store.
 * If this fails, the exception is encapsulated in the returned [Result].
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param uri The [Uri] for the launch intent.
 * @param browserAsFallback `true` to start the intent without the specified Play Store package
 * if the start with the specified package fails.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
@PublishedApi
internal inline fun Context.startPlayStoreInternal(
    uri: Uri,
    browserAsFallback: Boolean = true,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.AT_LEAST_ONCE)
    }
    val intent = Intent(Intent.ACTION_VIEW, uri).setPackage("com.android.vending")
    return startActivityCatchingWithIntentEditor(
        intent = intent,
        intentEditor = intentEditor
    ).onFailure {
        if (browserAsFallback) {
            return startActivityCatchingWithIntentEditor(
                intent = intent.setPackage(null),
                intentEditor = intentEditor
            )
        }
    }
}

/**
 * Displays the test track for an app in Google Play.
 * Note: For opening the Play Store app, this is currently not different from displaying the app
 * overview page.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param packageName The unique application id for the desired application.
 * @param startBrowser `true` to open the browser instead of the app. Open Play Store app (`false`)
 * is default.
 * @param useFallback `true` to also try the other method (browser or app) if the primary method
 * was not successful. If startBrowser is `true`, the app is opened as fallback and vice versa.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see UriCompanion.createPlayStoreTestTrackUriForApp
 */
inline fun Context.startPlayStoreForTestTrack(
    packageName: String = this.packageName,
    startBrowser: Boolean = false,
    useFallback: Boolean = true,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.AT_LEAST_ONCE)
    }
    if (startBrowser) {
        return startBrowser(
            url = UriCompanion.createPlayStoreTestTrackUriForApp(packageName = packageName),
            intentEditor = intentEditor
        ).onFailure {
            if (useFallback) {
                return startPlayStoreForApp(
                    packageName = packageName,
                    browserAsFallback = false,
                    intentEditor = intentEditor
                )
            }
        }
    } else {
        return startPlayStoreForApp(
            packageName = packageName,
            browserAsFallback = false,
            intentEditor = intentEditor
        ).onFailure {
            if (useFallback) {
                return startBrowser(
                    url = UriCompanion.createPlayStoreTestTrackUriForApp(packageName = packageName),
                    intentEditor = intentEditor
                )
            }
        }
    }
}

/**
 * Open a website in the user's default browser.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param url The url of the website to open.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startBrowser(url: Uri, intentEditor: Intent.() -> Unit = {}): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = Intent(Intent.ACTION_VIEW, url),
        intentEditor = intentEditor
    )
}

/**
 * Open a website in the user's default browser.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param url The url of the website to open.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startBrowser(url: String, intentEditor: Intent.() -> Unit = {}): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = Intent(Intent.ACTION_VIEW, url.toUri()),
        intentEditor = intentEditor
    )
}

/**
 * Share plain text and/or an attachment.
 * How the individual parameters are interpreted depends on the application that the user
 * selects to handle the intent.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param subject The subject to send.
 * @param text The text to send.
 * @param attachment A content URI holding a stream of data to send.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.share(
    subject: String? = null,
    text: String? = null,
    attachment: Uri? = null,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = IntentCompanion.createSendIntent(
            subject = subject,
            text = text,
            attachment = attachment
        ),
        intentEditor = intentEditor
    )
}

/**
 * Share plain text and/or an attachment.
 * How the individual parameters are interpreted depends on the application that the user
 * selects to handle the intent.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param subject The string resource of the subject to send.
 * @param text The string resource of the text to send.
 * @param attachment A content URI holding a stream of data to send.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.share(
    @StringRes subject: Int? = null,
    @StringRes text: Int? = null,
    attachment: Uri? = null,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = IntentCompanion.createSendIntent(
            subject = getStringOrNull(subject),
            text = getStringOrNull(text),
            attachment = attachment
        ),
        intentEditor = intentEditor
    )
}

/**
 * Start a mail application.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param subject The subject of the mail.
 * @param body The body of the mail.
 * @param recipient The recipient's mail address.
 * @param attachment A content URI holding a stream of data to send.
 * @param intentEditor Edit the created [Intent] before it is used to start the activity.
 * Errors occurring in the [intentEditor] are thrown further and not encapsulated in the [Result].
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startMailApplication(
    subject: String? = null,
    body: String? = null,
    recipient: String? = null,
    attachment: Uri? = null,
    intentEditor: Intent.() -> Unit = {}
): Result<Unit> {
    contract {
        callsInPlace(intentEditor, InvocationKind.EXACTLY_ONCE)
    }
    return startActivityCatchingWithIntentEditor(
        intent = IntentCompanion.createMailSendIntent(
            subject = subject,
            body = body,
            recipient = recipient,
            attachment = attachment
        ),
        intentEditor = intentEditor
    )
}

/**
 * Same as [Context.unregisterReceiver] but swallows all exceptions that may occur.
 */
inline fun Context.unregisterReceiverSilent(receiver: BroadcastReceiver) {
    try {
        unregisterReceiver(receiver)
    } catch (_: Throwable) {}
}

/**
 * Check whether the given permission is granted for the given process and user id.
 * @param permission The permission to check.
 * @param pid The process id being checked against.
 * @param uid The user id being checked against.
 * @return `true` if the permission is granted.
 * @see Context.checkPermission
 */
@CheckResult
inline fun Context.isPermissionGranted(
    permission: String,
    pid: Int = Process.myPid(),
    uid: Int = Process.myUid()
): Boolean {
    return checkPermission(permission, pid, uid) == PackageManager.PERMISSION_GRANTED
}

/**
 * @return The [Application] instance if the current context is an application context or a
 * wrapper for the application context.
 */
@CheckResult
inline fun Context.asApplicationOrNull(): Application? {
    return when (this) {
        is Application -> this
        is ContextWrapper -> baseContext as? Application
        else -> null
    }
}

/**
 * @return The [Activity] instance if the current context is an activity context or a
 * wrapper for an activity context.
 */
@CheckResult
inline fun Context.asActivityOrNull(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext as? Activity
        else -> null
    }
}

/**
 * Same as [PendingIntent.getActivity] but will set the flag [PendingIntent.FLAG_MUTABLE] and
 * remove the flag [PendingIntent.FLAG_IMMUTABLE].
 * @param intent Intent of the activity to be launched.
 * @param requestCode Request code for the sender.
 * @param flags May be [PendingIntent.FLAG_ONE_SHOT], [PendingIntent.FLAG_NO_CREATE],
 * [PendingIntent.FLAG_CANCEL_CURRENT], [PendingIntent.FLAG_UPDATE_CURRENT] or any of the flags
 * as supported by [Intent.fillIn] to control which unspecified parts of the intent that can be supplied
 * when the actual send happens.
 * @return Returns an existing or new PendingIntent matching the given parameters.
 * May return `null` only if [PendingIntent.FLAG_NO_CREATE] has been set.
 */
@RequiresApi(Build.VERSION_CODES.S)
inline fun Context.createMutableActivityPendingIntent(
    intent: Intent,
    requestCode: Int = 0,
    flags: Int = 0
): PendingIntent? {
    return PendingIntent.getActivity(
        this,
        requestCode,
        intent,
        flags.and(PendingIntent.FLAG_IMMUTABLE.inv()).or(PendingIntent.FLAG_MUTABLE)
    )
}

/**
 * Same as [PendingIntent.getActivity] but will set the flag [PendingIntent.FLAG_IMMUTABLE] and
 * remove the flag [PendingIntent.FLAG_MUTABLE].
 * @param intent Intent of the activity to be launched.
 * @param requestCode Request code for the sender.
 * @param flags May be [PendingIntent.FLAG_ONE_SHOT], [PendingIntent.FLAG_NO_CREATE],
 * [PendingIntent.FLAG_CANCEL_CURRENT], [PendingIntent.FLAG_UPDATE_CURRENT] or any of the flags
 * as supported by [Intent.fillIn] to control which unspecified parts of the intent that can be supplied
 * when the actual send happens.
 * @return Returns an existing or new PendingIntent matching the given parameters.
 * May return `null` only if [PendingIntent.FLAG_NO_CREATE] has been set.
 */
@SuppressLint("InlinedApi")
inline fun Context.createImmutableActivityPendingIntent(
    intent: Intent,
    requestCode: Int = 0,
    flags: Int = 0
): PendingIntent? {
    return PendingIntent.getActivity(
        this,
        requestCode,
        intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags.and(PendingIntent.FLAG_MUTABLE.inv()).or(PendingIntent.FLAG_IMMUTABLE) else flags
    )
}

/**
 * Get a drawable resource identifier for the given resource name in the current package.
 * @param name The resource name.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@DrawableRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Context.getDrawableIdByNameOrNull(name: String): Int? {
    val resId = resources.getIdentifier(name, "drawable", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}

/**
 * Get a string resource identifier for the given resource name in the current package.
 * @param name The resource name.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@StringRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Context.getStringIdByNameOrNull(name: String): Int? {
    val resId = resources.getIdentifier(name, "string", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}

/**
 * Get a layout resource identifier for the given resource name in the current package.
 * @param name The resource name.
 * @return The associated resource identifier. Returns `null` if no matching resource was found.
 */
@SuppressLint("DiscouragedApi")
@LayoutRes
@Discouraged(message = "See Resources.getIdentifier for details.")
inline fun Context.getLayoutIdByNameOrNull(name: String): Int? {
    val resId = resources.getIdentifier(name, "layout", packageName)
    return if (resId == ResourcesCompat.ID_NULL) null else resId
}

/**
 * Same as [ContextCompat.getColor].
 * @param id The resource identifier of the color.
 * @return A single color value in the form 0xAARRGGBB.
 * @throws Resources.NotFoundException If [id] does not exist.
 */
@Throws(Resources.NotFoundException::class)
@ColorInt
inline fun Context.getColorCompat(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

/**
 * Same as [ContextCompat.getColor] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the color.
 * @return A single color value in the form 0xAARRGGBB.
 */
@ColorInt
inline fun Context.getColorCompatOrNull(@ColorRes id: Int?): Int? {
    return try {
        ContextCompat.getColor(this, id ?: return null)
    } catch (_: Resources.NotFoundException) {
        null
    }
}

/**
 * Same as [AppCompatResources.getColorStateList].
 * @param id The resource identifier of the [ColorStateList].
 * @return A [ColorStateList], or `null` if the resource could not be resolved.
 * @throws Resources.NotFoundException If [id] does not exist.
 * @experimental Consider using [Context.getColorStateListCompatOrNull].
 */
@ExperimentalNanoKtApi
@Throws(Resources.NotFoundException::class)
inline fun Context.getColorStateListCompat(@ColorRes id: Int): ColorStateList? {
    return AppCompatResources.getColorStateList(this, id)
}

/**
 * Same as [AppCompatResources.getColorStateList] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [ColorStateList].
 * @return A [ColorStateList], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Context.getColorStateListCompatOrNull(@ColorRes id: Int?): ColorStateList? {
    return try {
        AppCompatResources.getColorStateList(this, id ?: return null)
    } catch (_: Resources.NotFoundException) {
        null
    }
}

/**
 * Same as [AppCompatResources.getDrawable].
 * @param id The resource identifier of the [Drawable].
 * @return A [Drawable], or `null` if the resource could not be resolved.
 * @throws Resources.NotFoundException If [id] does not exist.
 * @experimental Consider using [Context.getDrawableCompatOrNull].
 */
@ExperimentalNanoKtApi
@Throws(Resources.NotFoundException::class)
inline fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? {
    return AppCompatResources.getDrawable(this, id)
}

/**
 * Same as [AppCompatResources.getDrawable] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [Drawable].
 * @return A [Drawable], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Context.getDrawableCompatOrNull(@DrawableRes id: Int?): Drawable? {
    return try {
        AppCompatResources.getDrawable(this, id ?: return null)
    } catch (_: Resources.NotFoundException) {
        null
    }
}

/**
 * @param id The resource id of the icon.
 * @return An [Icon] pointing to a drawable resource.
 */
@RequiresApi(Build.VERSION_CODES.M)
inline fun Context.getIcon(@DrawableRes id: Int): Icon {
    return Icon.createWithResource(this, id)
}

/**
 * @param id The resource id of the icon.
 * @return An [IconCompat] pointing to a drawable resource.
 */
inline fun Context.getIconCompat(@DrawableRes id: Int): IconCompat {
    return IconCompat.createWithResource(this, id)
}

/**
 * Same as [Context.getString] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [String].
 * @return The [String], or `null` if the resource was invalid.
 */
inline fun Context.getStringOrNull(@StringRes id: Int?): String? {
    return try {
        getString(id ?: return null)
    } catch (_: Throwable) {
        null
    }
}

/**
 * Calls [Context.startForegroundService] on [Build.VERSION_CODES.O] and above and
 * [Context.startService] on lower versions.
 * @param service Identifies the service to be started.
 * The Intent must be fully explicit (supplying a component name).
 * @return The [ComponentName] of the service that is started or already running. Null if the
 * service does not exist.
 * @throws SecurityException  If the caller does not have permission to access the service or
 * the service can not be found.
 * @throws IllegalStateException If the application is in a state where the service can not be started.
 */
@Throws(SecurityException::class, IllegalStateException::class)
inline fun Context.startForegroundServiceCompat(service: Intent): ComponentName? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(service)
    } else {
        startService(service)
    }
}

/**
 * Calls [Context.startForegroundService] on [Build.VERSION_CODES.O] and above and
 * [Context.startService] on lower versions.
 * @param T The class of service to start.
 * @return The [ComponentName] of the service that is started or already running. Null if the
 * service does not exist.
 * @throws SecurityException If the caller does not have permission to access the service or
 * the service can not be found.
 * @throws IllegalStateException If the application is in a state where the service can not be started.
 */
@Throws(SecurityException::class, IllegalStateException::class)
inline fun <reified T : Service> Context.startForegroundServiceCompat(): ComponentName? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(Intent(this, T::class.java))
    } else {
        startService(Intent(this, T::class.java))
    }
}

/**
 * Same as Context.startService(Intent(this, T::class.java)).
 * @param T The class of service to start.
 * @return The [ComponentName] of the service that is started or already running. Null if the
 * service does not exist.
 * @throws SecurityException  If the caller does not have permission to access the service or
 * the service can not be found.
 * @throws IllegalStateException If the application is in a state where the service can not be started.
 * @see Context.startService
 */
@Throws(SecurityException::class, IllegalStateException::class)
inline fun <reified T : Service> Context.startService(): ComponentName? {
    return startService(Intent(this, T::class.java))
}

/**
 * Same as calling Context.bindService(Intent(this, T::class.java), connection, flags).
 * @param T The class of service to bind to.
 * @param connection Receives information when the service is started or stopped.
 * @param flags See [Context.bindService] for available flags.
 * @return True if the system is binding to the service. False otherwise.
 * @throws SecurityException If you do not have permission to access the service or the service cannot be found.
 * @see Context.bindService
 */
@Throws(SecurityException::class)
inline fun <reified T : Service> Context.bindService(
    connection: ServiceConnection,
    flags: Int = 0
): Boolean {
    return bindService(Intent(this, T::class.java), connection, flags)
}

/**
 * Get a color from the current theme. Resource references will be resolved.
 * Use [Resources.Theme.getColorOrNull] if you do not need support for android versions
 * below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The color if the attribute was found, `null` otherwise.
 */
@ColorInt
@CheckResult
inline fun Context.getThemeColorOrNull(@AttrRes id: Int): Int? {
    val attr = theme.getAttributeOrNull(id) ?: return null
    return if (attr.isColorTypeCompat) {
        attr.data
    } else resources.getColorCompat(id = attr.resourceId, theme = theme)
}

/**
 * Get a color from the current theme. Resource references will be resolved.
 * Use [Resources.Theme.getColor] if you do not need support for android versions
 * below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The color.
 * @throws Resources.NotFoundException If [id] does not exist in the theme or is no color.
 */
@ColorInt
@Throws(Resources.NotFoundException::class)
inline fun Context.getThemeColor(@AttrRes id: Int): Int {
    return getThemeColorOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}

/**
 * Get a [ColorStateList] from the current theme. Resource references will be resolved.
 * Use [Theme.getColorStateListOrNull] if you do not need support for android versions
 * below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The [ColorStateList] if the attribute was found, `null` otherwise.
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@CheckResult
inline fun Context.getThemeColorStateListOrNull(@AttrRes id: Int): ColorStateList? {
    return resources.getColorStateListCompatOrNull(
        id = theme.getAttributeOrNull(id)?.resourceId,
        theme = theme
    )
}

/**
 * Get a [ColorStateList] from the current theme. Resource references will be resolved.
 * Use [Theme.getColorStateList] if you do not need support for android versions
 * below [Build.VERSION_CODES.LOLLIPOP].
 * @param id Resource attribute id for the color.
 * @return The [ColorStateList].
 * @throws Resources.NotFoundException If [id] does not exist in the theme or is no [ColorStateList].
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Throws(Resources.NotFoundException::class)
inline fun Context.getThemeColorStateList(@AttrRes id: Int): ColorStateList {
    return getThemeColorStateListOrNull(id) ?: throw Resources.NotFoundException("Attribute with id $id not found")
}