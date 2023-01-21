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