@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

import java.io.BufferedReader
import java.io.IOException


/**
 * Version of [BufferedReader.readLine] that is non blocking and
 * returns null if the underlying stream is not ready or the buffer is empty.
 * @throws IOException If an I/O error occurs.
 */
@Throws(IOException::class)
inline fun BufferedReader.readLineIfReady() = if (ready()) readLine() else null