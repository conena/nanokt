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

@file:JvmName(name = "BitmapUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.graphics

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Invokes [block] with the receiver as argument, returns the result and calls [Bitmap.recycle]
 * even when an exception is thrown. Exceptions are passed through.
 * @return The result of [block].
 */
inline fun <R> Bitmap.use(block: (Bitmap) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    try {
        return block(this)
    } finally {
        recycle()
    }
}

/**
 * Decode a byte array of compressed image data to a [Bitmap].
 * @param offset Offset in the receiver to start parsing.
 * @param length The number of bytes to parse.
 * @param options Options that control down sampling and whether the image should be completely decoded.
 * @return The decoded bitmap, or null if the receiver could not be decoded or the option
 * [BitmapFactory.Options.inJustDecodeBounds] was set.
 * @throws IllegalArgumentException In case of incompatible options.
 * See [BitmapFactory.decodeByteArray] for details.
 * @throws IndexOutOfBoundsException If offset or length are negative
 * or more bytes are to be read than the receiver is long (taking into account the offset)
 */
@Throws(IllegalArgumentException::class, ArrayIndexOutOfBoundsException::class)
inline fun ByteArray.decodeToBitmap(
    offset: Int = 0,
    length: Int = size,
    options: BitmapFactory.Options? = null
): Bitmap? {
    return BitmapFactory.decodeByteArray(this, offset, length, options)
}
