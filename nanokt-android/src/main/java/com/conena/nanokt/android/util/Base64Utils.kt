@file:JvmName(name = "Base64Utils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.Base64
import androidx.annotation.CheckResult
import java.nio.charset.Charset

/**
 * Base64-encode the current string.
 * @param charset The [Charset] used to encode the current string.
 * @param urlSafe `true` to use the URL and filename safe alphabet of Base64 as specified in RFC 3548 section 4
 * where - and _ are used in place of + and /.
 * @param wrap `true` to use line breaks as specified in RFC 2045.
 * @param padding `false` to omit the padding '=' characters at the end of the output.
 * @param crlf `true` to terminate lines with a CRLF pair instead of just an LF.
 * @return The string result.
 */
@CheckResult
inline fun String.encodeBase64(
    charset: Charset = Charsets.UTF_8,
    urlSafe: Boolean = false,
    wrap: Boolean = false,
    padding: Boolean = true,
    crlf: Boolean = false
): String {
    var flags = if (urlSafe) Base64.URL_SAFE else Base64.DEFAULT
    if (!wrap) flags = flags or Base64.NO_WRAP
    if (!padding) flags = flags or Base64.NO_PADDING
    if (crlf) flags = flags or Base64.CRLF
    return String(
        bytes = Base64.encode(toByteArray(charset), flags),
        charset = Charsets.US_ASCII
    )
}


/**
 * Base64-decode the current string. Invalid characters are ignored.
 * The padding '=' characters at the end are optional, but if any are present,
 * there must be the correct number of them.
 * @param charset The [Charset] used to create the result string.
 * @param urlSafe `true` to use the URL and filename safe alphabet of Base64 as specified in RFC 3548 section 4
 * where - and _ are used in place of + and /.
 * @return The string result.
 * @throws IllegalArgumentException If the current string contains incorrect Base64 padding.
 * @see Base64.decode
 */
@CheckResult
@Throws(IllegalArgumentException::class)
inline fun String.decodeBase64(charset: Charset = Charsets.UTF_8, urlSafe: Boolean = false): String {
    return String(
        bytes = Base64.decode(this, if (urlSafe) Base64.URL_SAFE else Base64.DEFAULT),
        charset = charset
    )
}