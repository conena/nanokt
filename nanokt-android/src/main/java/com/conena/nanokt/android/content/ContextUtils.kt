@file:JvmName(name = "ContextUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.*
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.IconCompat
import com.conena.nanokt.android.ExperimentalNanoKtAndroidApi
import com.conena.nanokt.android.net.getPlayStoreUriForApp
import com.conena.nanokt.android.net.getPlayStoreUriForDeveloper
import com.conena.nanokt.android.net.getTestTrackWebsiteUriForApp
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Throwable
import kotlin.Throws
import kotlin.Unit
import kotlin.system.exitProcess

/**
 * The content of the clipboard's primary clip as text.
 * `null` if the application is not the default input method editor or does not have input focus.
 * @see ClipData.Item.coerceToText
 * @see ClipboardManager.getPrimaryClipDataItem
 */
inline var Context.clipboardTextContent: String?
get() = clipboardManager.getPrimaryClipDataItem()?.coerceToText(this)?.toString()
set(value) = clipboardManager.setPrimaryClip(text = value ?: "")

/**
 * `true` if the app is currently at the top of the screen that the user is interacting with.
 * @see RunningAppProcessInfo.IMPORTANCE_FOREGROUND
 */
inline val Context.isAppInForeground: Boolean get() {
    return activityManager.runningAppProcesses.orEmpty().any { process ->
        process.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && process.uid == Process.myUid()
    }
}

/**
 * The current configuration for the application's package resources instance.
 */
inline val Context.configuration: Configuration get() = resources.configuration

/**
 * Opens various settings screens for the current application.
 * @param action One of the actions below to get to a specific settings screen.
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
inline fun Context.openAppSettings(
    action: String = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
): Result<Unit> {
    return startActivityCatching(
        intent = Intent(action)
            .setPackageUri(packageName)
            .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            .putExtra(Intent.EXTRA_PACKAGE_NAME, packageName)
    )
}

/**
 * Open the app notification channel settings for a specific channel
 * @param channelId The ID of the channel for which the settings are to be displayed.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
@RequiresApi(Build.VERSION_CODES.O)
inline fun Context.openAppNotificationChannelSettings(channelId: String): Result<Unit> {
    return startActivityCatching(
        intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
            .putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            .putExtra(Settings.EXTRA_CHANNEL_ID, channelId)
    )
}

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

/**
 * Launch a new activity.
 * If invoked on a non-activity context [Intent.FLAG_ACTIVITY_NEW_TASK] is added automatically.
 * @param T The Activity to start.
 * @param options Additional options for how the Activity should be started.
 * @see Context.startActivity
 */
@Throws(ActivityNotFoundException::class)
inline fun <reified T : Activity> Context.startActivity(options: Bundle? = null) {
    if (this is Activity) {
        startActivity(Intent(this, T::class.java), options)
    } else {
        startActivity(Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), options)
    }
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
 * Opens the Play Store overview page for the given app.
 * In the first step, it tries to start the Play Store app on the device.
 * If this is not successful, an attempt is made to open the Play Store in the browser.
 * If this also fails, the exception is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showAppInPlayStoreApp
 * @see showAppInPlayStoreWebsite
 */
inline fun Context.showAppInPlayStore(
    packageName: String = this.packageName,
    referrer: String? = null
): Result<Unit> {
    return showAppInPlayStoreApp(
        packageName = packageName,
        referrer = referrer
    ).onFailure {
        return showAppInPlayStoreWebsite(
            packageName = packageName,
            referrer = referrer
        )
    }
}

/**
 * Opens the Play Store overview page for the given app in the user's default web browser.
 * If this fails, the error is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showAppInPlayStoreApp
 * @see showAppInPlayStore
 */
inline fun Context.showAppInPlayStoreWebsite(
    packageName: String = this.packageName,
    referrer: String? = null
): Result<Unit> {
    return showAppInPlayStoreInternal(
        packageName = packageName,
        referrer = referrer,
        openWeb = true
    )
}

/**
 * Opens the Play Store overview page for the given app in the Play Store app.
 * If this fails, the exception is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showAppInPlayStoreWebsite
 * @see showAppInPlayStore
 */
inline fun Context.showAppInPlayStoreApp(
    packageName: String = this.packageName,
    referrer: String? = null
): Result<Unit> {
    return showAppInPlayStoreInternal(
        packageName = packageName,
        referrer = referrer
    )
}

/**
 * Opens the Play Store overview page for the given developer.
 * In the first step, it tries to start the Play Store app on the device.
 * If this is not successful, an attempt is made to open the Play Store in the browser.
 * If this also fails, the exception is encapsulated in the returned [Result].
 * @param developerName The unique developer id.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showDeveloperInPlayStoreApp
 * @see showDeveloperInPlayStoreWebsite
 */
inline fun Context.showDeveloperInPlayStore(developerName: String): Result<Unit> {
    return showDeveloperInPlayStoreApp(developerName = developerName).onFailure {
        return showDeveloperInPlayStoreWebsite(developerName = developerName)
    }
}

/**
 * Displays the test track for an app in Google Play. In the first step,
 * an attempt is made to open the details page on the Play Store app.
 * If this fails, the testing page is opened in the browser.
 * If this also fails, the exception is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see getTestTrackWebsiteUriForApp
 */
inline fun Context.showTestTrackInPlayStore(packageName: String = this.packageName): Result<Unit> {
    return showAppInPlayStoreApp(packageName = packageName).onFailure {
        return openWebsite(url = getTestTrackWebsiteUriForApp(packageName = packageName))
    }
}

/**
 * Opens the Play Store testing page for the given [packageName] in the user's default web browser.
 * If this fails, the exception is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see getTestTrackWebsiteUriForApp
 */
inline fun Context.showTestTrackInPlayStoreWebsite(
    packageName: String = this.packageName
): Result<Unit> {
    return openWebsite(url = getTestTrackWebsiteUriForApp(packageName = packageName))
}

/**
 * Opens the Play Store overview page for the given developer in the user's default web browser.
 * If this fails, the exception is encapsulated in the [Result].
 * @param developerName The unique developer id.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showDeveloperInPlayStoreApp
 * @see showDeveloperInPlayStore
 */
inline fun Context.showDeveloperInPlayStoreWebsite(
    developerName: String
): Result<Unit> {
    return showDeveloperInPlayStoreInternal(
        developerName = developerName,
        openWeb = true
    )
}

/**
 * Opens the Play Store overview page for the given developer in the Play Store app.
 * If this fails, the exception is encapsulated in the returned [Result].
 * @param developerName The unique developer id.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showDeveloperInPlayStoreWebsite
 * @see showDeveloperInPlayStore
 */
inline fun Context.showDeveloperInPlayStoreApp(
    developerName: String
): Result<Unit> {
    return showDeveloperInPlayStoreInternal(developerName = developerName)
}

/**
 * Opens the Play Store overview page for the given app.
 * If this fails, the exception is encapsulated in the returned [Result].
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @param openWeb `true` to open the user's default browser instead of the Play Store app.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showAppInPlayStore
 * @see showAppInPlayStoreApp
 * @see showAppInPlayStoreWebsite
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
inline fun Context.showAppInPlayStoreInternal(
    packageName: String,
    referrer: String?,
    openWeb: Boolean = false
): Result<Unit> {
    return startActivityCatching(
        intent = Intent(
            Intent.ACTION_VIEW,
            getPlayStoreUriForApp(
                packageName = packageName,
                referrer = referrer,
                webLink = openWeb
            )
        )
    )
}

/**
 * Opens the Play Store overview page for the given developer.
 * If this fails, the exception is encapsulated in the returned [Result].
 * @param developerName  The unique developer id.
 * @param openWeb `true` to open the user's default browser instead of the Play Store app.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 * @see showAppInPlayStore
 * @see showAppInPlayStoreApp
 * @see showAppInPlayStoreWebsite
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
inline fun Context.showDeveloperInPlayStoreInternal(
    developerName: String,
    openWeb: Boolean = false
): Result<Unit> {
    return startActivityCatching(
        intent = Intent(
            Intent.ACTION_VIEW,
            getPlayStoreUriForDeveloper(
                developerName = developerName,
                webLink = openWeb
            )
        )
    )
}

/**
 * Open a website in the user's default browser.
 * @param url The url of the website to open.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.openWebsite(url: Uri): Result<Unit> {
    return startActivityCatching(intent = Intent(Intent.ACTION_VIEW, url))
}

/**
 * Share plain text and/or an attachment.
 * How the individual parameters are interpreted depends on the application that the user
 * selects to handle the intent.
 * @param subject The subject to send.
 * @param text The text to send.
 * @param attachment A content URI holding a stream of data to send.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.share(
    subject: String? = null,
    text: String? = null,
    attachment: Uri? = null
): Result<Unit> {
    return startActivityCatching(
        intent = createSendIntent(
            subject = subject,
            text = text,
            attachment = attachment
        )
    )
}

/**
 * Start a mail application.
 * @param subject The subject of the mail.
 * @param body The body of the mail.
 * @param recipient The recipient's mail address.
 * @param attachment A content URI holding a stream of data to send.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Context.startMailApplication(
    subject: String? = null,
    body: String? = null,
    recipient: String? = null,
    attachment: Uri? = null
): Result<Unit> {
    return startActivityCatching(
        intent = createMailSendIntent(
            subject = subject,
            body = body,
            recipient = recipient,
            attachment = attachment
        )
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
 * Exit and restart the application.
 */
inline fun Context.restartApplication() {
    val pendingIntent = createImmutableActivityPendingIntent(
        intent = packageManager.getLaunchIntentForPackage(packageName)!!
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
        flags = PendingIntent.FLAG_CANCEL_CURRENT
    )
    alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
    exitProcess(status = 0)
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
 * Same as [ContextCompat.getColorStateList].
 * @param id The resource identifier of the [ColorStateList].
 * @return A [ColorStateList], or `null` if the resource could not be resolved.
 * @throws Resources.NotFoundException If [id] does not exist.
 * @experimental Consider using [Context.getColorStateListCompatOrNull].
 */
@ExperimentalNanoKtAndroidApi
@Throws(Resources.NotFoundException::class)
inline fun Context.getColorStateListCompat(@ColorRes id: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, id)
}

/**
 * Same as [ContextCompat.getColorStateList] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [ColorStateList].
 * @return A [ColorStateList], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Context.getColorStateListCompatOrNull(@ColorRes id: Int?): ColorStateList? {
    return try {
        ContextCompat.getColorStateList(this, id ?: return null)
    } catch (_: Resources.NotFoundException) {
        null
    }
}

/**
 * Same as [ContextCompat.getDrawable].
 * @param id The resource identifier of the [Drawable].
 * @return A [Drawable], or `null` if the resource could not be resolved.
 * @throws Resources.NotFoundException If [id] does not exist.
 * @experimental Consider using [Context.getDrawableCompatOrNull].
 */
@ExperimentalNanoKtAndroidApi
@Throws(Resources.NotFoundException::class)
inline fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

/**
 * Same as [ContextCompat.getDrawable] but returns null if [id] is null or not valid.
 * @param id The resource identifier of the [Drawable].
 * @return A [Drawable], or `null` if the resource could not be resolved or was invalid.
 */
inline fun Context.getDrawableCompatOrNull(@DrawableRes id: Int?): Drawable? {
    return try {
        ContextCompat.getDrawable(this, id ?: return null)
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
 * Calls [Context.startForegroundService] on [Build.VERSION_CODES.O] and above and
 * [Context.startService] on lower versions.
 * @param service Identifies the service to be started.
 * The Intent must be fully explicit (supplying a component name).
 * @throws SecurityException  If the caller does not have permission to access the service or
 * the service can not be found.
 * @throws IllegalStateException If the application is in a state where the service can not be started.
 */
@Throws(SecurityException::class, IllegalStateException::class)
inline fun Context.startForegroundServiceCompat(service: Intent) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(service)
    } else {
        startService(service)
    }
}