@file:JvmName(name = "DocumentFileUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.provider

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.DocumentsProvider
import androidx.annotation.CheckResult
import androidx.documentfile.provider.DocumentFile
import java.io.File

/**
 * True if the receiver represents a file and the current context is allowed to write to it.
 * @see DocumentFile.isFile
 * @see DocumentFile.canWrite
 */
inline val DocumentFile.isWritableFile get() = isFile && canWrite()

/**
 * True if the receiver represents a directory and the current context is allowed to write to it.
 * @see DocumentFile.isDirectory
 * @see DocumentFile.canWrite
 */
inline val DocumentFile.isWritableDirectory get() = isDirectory && canWrite()

/**
 * True if the receiver represents a file and the current context is allowed to read from it.
 * @see DocumentFile.isFile
 * @see DocumentFile.canRead
 */
inline val DocumentFile.isReadableFile get() = isFile && canRead()

/**
 * True if the receiver represents a directory and the current context is allowed to read from it.
 * @see DocumentFile.isDirectory
 * @see DocumentFile.canRead
 */
inline val DocumentFile.isReadableDirectory get() = isDirectory && canRead()

/**
 * True if the current context is allowed to write to this [DocumentFile].
 * @see DocumentFile.canWrite
 */
inline val DocumentFile.isWritable get() = canWrite()

/**
 * True if the current context is allowed to read from this [DocumentFile].
 * @see DocumentFile.canRead
 */
inline val DocumentFile.isReadable get() = canRead()

/**
 * @param context Used internal to query the document providers.
 * @return `true` if given Uri is backed by a [DocumentsProvider].
 */
@CheckResult
inline fun Uri.isDocumentUri(context: Context) = DocumentFile.isDocumentUri(context, this)

/**
 * @param context Used internal to query the document providers.
 * @return A [DocumentFile] representing the single document at the [Uri]. Returns `null` if the [Uri]
 * is not backed by a [DocumentsProvider] or if the method is called on devices running versions prior to
 * [Build.VERSION_CODES.KITKAT].
 */
@CheckResult
inline fun Uri.toDocumentFileOrNull(context: Context): DocumentFile? {
    return try {
        if (DocumentFile.isDocumentUri(context, this)) {
            DocumentFile.fromSingleUri(context, this)
        } else {
            null
        }
    } catch (_: Throwable) {
        null
    }
}

/**
 * @param context Used internal to query the document providers.
 * @return A [DocumentFile] representing the document tree at the [Uri]. Returns `null` if the [Uri]
 * is not backed by a [DocumentsProvider] or if the method is called on devices running versions prior to
 * [Build.VERSION_CODES.KITKAT].
 */
@CheckResult
inline fun Uri.toDocumentTreeOrNull(context: Context): DocumentFile? {
    return try {
        DocumentFile.fromTreeUri(context, this)
    } catch (_: Throwable) {
        null
    }
}

/**
 * Same as [DocumentFile.fromFile].
 * @return A [DocumentFile] representing the filesystem tree rooted at the receiver [File].
 */
@CheckResult
inline fun File.toDocumentFile(): DocumentFile = DocumentFile.fromFile(this)