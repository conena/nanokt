@file:JvmName(name = "DocumentFileUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.provider

import androidx.documentfile.provider.DocumentFile

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