@file:JvmName(name = "ContentResolverUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Take a persistable Uri read permission grant that has been offered.
 * Same as calling [ContentResolver.takePersistableUriPermission] with flag
 * [Intent.FLAG_GRANT_READ_URI_PERMISSION].
 * @param uri The [Uri] to take permission for.
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
inline fun ContentResolver.takePersistableReadUriPermission(uri: Uri) {
    takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
}

/**
 * Take a persistable Uri write permission grant that has been offered.
 * Same as calling [ContentResolver.takePersistableUriPermission] with flag
 * [Intent.FLAG_GRANT_WRITE_URI_PERMISSION].
 * @param uri The [Uri] to take permission for.
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
inline fun ContentResolver.takePersistableWriteUriPermission(uri: Uri) {
    takePersistableUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
}

/**
 * Take a persistable Uri read and write permission grant that has been offered.
 * Same as calling [ContentResolver.takePersistableUriPermission] with flags
 * [Intent.FLAG_GRANT_READ_URI_PERMISSION] and [Intent.FLAG_GRANT_WRITE_URI_PERMISSION].
 * @param uri The [Uri] to take permission for.
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
inline fun ContentResolver.takePersistableReadWriteUriPermission(uri: Uri) {
    takePersistableUriPermission(
        uri,
        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    )
}