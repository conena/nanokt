@file:JvmName(name = "UriUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.net

import android.net.Uri
import androidx.annotation.CheckResult
import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException
import java.net.URL

/**
 * Builds an [Uri] to show the application with [packageName] in the Google Play Store.
 * @param packageName The unique application id for the desired application.
 * @param referrer Value for the referrer parameter in the URI. The value is encoded by the function.
 * If null, no referrer is added.
 * @param webLink `true` to get a web uri (play.google.com) instead of an uri to open the Play Store App.
 * @return The created [Uri].
 */
@CheckResult
inline fun getPlayStoreUriForApp(
    packageName: String,
    referrer: String? = null,
    webLink: Boolean = false
): Uri {
    return Uri.Builder()
        .scheme(if (webLink) "https" else "market")
        .authority(if (webLink) "play.google.com" else "details")
        .path(if (webLink) "store/apps/details" else null)
        .appendQueryParameter("id", packageName)
        .apply {
            if (referrer != null) appendQueryParameter("referrer", referrer)
        }
        .build()
}

/**
 * Builds an [Uri] to show the developer with [developerName] in the Google Play Store.
 * @param developerName The unique developer id for the desired developer account.
 * @param webLink `true` to get a web uri (play.google.com) instead of an uri to open the Play Store App.
 * @return The created [Uri].
 */
@CheckResult
inline fun getPlayStoreUriForDeveloper(
    developerName: String,
    webLink: Boolean = false
): Uri {
    return Uri.Builder()
        .scheme(if (webLink) "https" else "market")
        .authority(if (webLink) "play.google.com" else "developer")
        .path(if (webLink) "store/apps/developer" else null)
        .appendQueryParameter("id", developerName)
        .build()
}

/**
 * Builds an [Uri] to show the Google Play test-track join page for the application with [packageName].
 * @param packageName The unique application id for the desired application.
 * @return The created [Uri].
 */
inline fun getTestTrackWebsiteUriForApp(packageName: String): Uri {
    return Uri.Builder()
        .scheme("https")
        .authority("play.google.com")
        .path("apps/testing/$packageName")
        .build()
}

/**
 * Convert an android [Uri] to a java [URI].
 * @return A java [URI] identical to the android [Uri].
 * @throws URISyntaxException If the given [Uri] violates RFC 2396.
 * @see URI
 * @see toJavaUriOrNull
 */
@Throws(URISyntaxException::class)
inline fun Uri.toJavaUri(): URI = URI(toString())

/**
 * Convert an android [Uri] to a java [URI].
 * @return A java [URI] identical to the android [Uri].
 * If the [Uri] violates RFC 2396 null is returned.
 * @see URI
 * @see toJavaUri
 */
@CheckResult
inline fun Uri.toJavaUriOrNull(): URI? {
    return try {
        URI(toString())
    } catch (_: Throwable) {
        null
    }
}

/**
 * Convert an android [Uri] to a java [URL].
 * @return A java [URL] identical to the android [Uri].
 * @throws MalformedURLException If the [Uri]'s protocol is not supported.
 * @see URI
 * @see toJavaUrlOrNull
 */
@Throws(MalformedURLException::class)
inline fun Uri.toJavaUrl(): URL = URL(toString())

/**
 * Convert an android [Uri] to a java [URL].
 * @return A java [URL] identical to the android [Uri].
 * If the [Uri] violates RFC 2396 null is returned.
 * @see URI
 * @see toJavaUrl
 */
@CheckResult
inline fun Uri.toJavaUrlOrNull(): URL? {
    return try {
        URL(toString())
    } catch (_: Throwable) {
        null
    }
}

/**
 * Convert a java [URI] to an android [Uri].
 * @return An android [Uri] identical to the java [URI].
 * @see URI
 */
@CheckResult
inline fun URI.toAndroidUri(): Uri = Uri.parse(toString())

/**
 * Convert a java [URL] to an android [Uri].
 * @return An android [Uri] identical to the java [URL].
 * @see URL
 */
@CheckResult
inline fun URL.toAndroidUri(): Uri = Uri.parse(toString())