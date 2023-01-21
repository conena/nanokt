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

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.jvm

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