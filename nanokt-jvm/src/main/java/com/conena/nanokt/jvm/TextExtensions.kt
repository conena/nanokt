@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * @return The current string encoded in application/x-www-form-urlencoded format using UTF-8.
 */
inline fun String.encodeUrl(): String = URLEncoder.encode(this, Charsets.UTF_8.name())

/**
 * @return Decodes the current string from application/x-www-form-urlencoded format using UTF-8.
 */
inline fun String.decodeUrl(): String = URLDecoder.decode(this, Charsets.UTF_8.name())