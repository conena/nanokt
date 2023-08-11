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
@get:CheckResult
inline val DocumentFile.isWritableFile get() = isFile && canWrite()

/**
 * True if the receiver represents a directory and the current context is allowed to write to it.
 * @see DocumentFile.isDirectory
 * @see DocumentFile.canWrite
 */
@get:CheckResult
inline val DocumentFile.isWritableDirectory get() = isDirectory && canWrite()

/**
 * True if the receiver represents a file and the current context is allowed to read from it.
 * @see DocumentFile.isFile
 * @see DocumentFile.canRead
 */
@get:CheckResult
inline val DocumentFile.isReadableFile get() = isFile && canRead()

/**
 * True if the receiver represents a directory and the current context is allowed to read from it.
 * @see DocumentFile.isDirectory
 * @see DocumentFile.canRead
 */
@get:CheckResult
inline val DocumentFile.isReadableDirectory get() = isDirectory && canRead()

/**
 * True if the current context is allowed to write to this [DocumentFile].
 * @see DocumentFile.canWrite
 */
@get:CheckResult
inline val DocumentFile.isWritable get() = canWrite()

/**
 * True if the current context is allowed to read from this [DocumentFile].
 * @see DocumentFile.canRead
 */
@get:CheckResult
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