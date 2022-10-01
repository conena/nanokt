@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException

/**
 * Version of [BufferedReader.readLine] that is non blocking and
 * returns null if the underlying stream is not ready or the buffer is empty.
 * @throws IOException If an I/O error occurs.
 */
@Throws(IOException::class)
inline fun BufferedReader.readLineIfReady() = if (ready()) readLine() else null

/**
 * Calls [BufferedWriter.newLine] directly after [BufferedWriter.write].
 * @param str The string to be written.
 * @param offset offset from which to start writing characters.
 * @param length Number of characters to write.
 * @throws IOException If an I/O error occurs.
 */
@Throws(IOException::class)
inline fun BufferedWriter.writeLine(str: String, offset: Int = 0, length: Int = str.length) {
    write(str, offset, length)
    newLine()
}

/**
 * Calls [BufferedWriter.flush] directly after [BufferedWriter.write].
 * @param str The string to be written.
 * @param offset offset from which to start writing characters.
 * @param length Number of characters to write.
 * @throws IOException If an I/O error occurs.
 */
@Throws(IOException::class)
inline fun BufferedWriter.writeFlushing(str: String, offset: Int = 0, length: Int = str.length) {
    write(str, offset, length)
    flush()
}

/**
 * Calls [BufferedWriter.flush] directly after [BufferedWriter.write] and [BufferedWriter.newLine].
 * @param str The string to be written.
 * @param offset offset from which to start writing characters.
 * @param length Number of characters to write.
 * @throws IOException If an I/O error occurs.
 */
@Throws(IOException::class)
inline fun BufferedWriter.writeLineFlushing(str: String, offset: Int = 0, length: Int = str.length) {
    write(str, offset, length)
    newLine()
    flush()
}