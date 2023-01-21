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

@file:JvmName(name = "LogUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.util

import android.util.Log

/**
 * Write a message with level [Log.VERBOSE] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logVerbose(message: String, tag: String = generateLogTag()) {
    Log.v(tag, message)
}

/**
 * Write a message with level [Log.DEBUG] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logDebug(message: String, tag: String = generateLogTag()) {
    Log.d(tag, message)
}

/**
 * Write a message with level [Log.INFO] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logInfo(message: String, tag: String = generateLogTag()) {
    Log.i(tag, message)
}

/**
 * Write a message with level [Log.WARN] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logWarn(message: String, tag: String = generateLogTag()) {
    Log.w(tag, message)
}

/**
 * Write a message with level [Log.ERROR] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logError(message: String, tag: String = generateLogTag()) {
    Log.e(tag, message)
}

/**
 * Write a message with level [Log.ASSERT] to the logcat's main buffer.
 * @param message The log message.
 * @param tag The tag of the message.
 */
inline fun logFatal(message: String, tag: String = generateLogTag()) {
    Log.println(Log.ASSERT, tag, message)
}

/**
 * @return A string suitable as tag for logcat entries.
 * The string consists of the file name and the line in which this method was called.
 */
inline fun generateLogTag(): String {
    val st = Thread.currentThread().stackTrace[2]
    val line = ":${st.lineNumber}"
    return if (st.fileName.length > (23 - line.length)) {
        st.fileName.take(21 - line.length) + ".." + line
    } else st.fileName + line
}

