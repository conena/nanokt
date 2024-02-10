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
import androidx.annotation.CheckResult

/**
 * Write a message with level [Log.VERBOSE] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logVerbose(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    if (throwable == null) Log.v(tag, message) else Log.v(tag, message, throwable)
}

/**
 * Write a message with level [Log.DEBUG] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logDebug(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    if (throwable == null) Log.d(tag, message) else Log.d(tag, message, throwable)
}

/**
 * Write a message with level [Log.INFO] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logInfo(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    if (throwable == null) Log.i(tag, message) else Log.i(tag, message, throwable)
}

/**
 * Write a message with level [Log.WARN] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logWarn(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    if (throwable == null) Log.w(tag, message) else Log.w(tag, message, throwable)
}

/**
 * Write a message with level [Log.ERROR] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logError(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    if (throwable == null) Log.e(tag, message) else Log.e(tag, message, throwable)
}

/**
 * Write a message with level [Log.ASSERT] to the logcat's main buffer.
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the line number determined via [generateLogTag] is incorrect
 * if the calling function is an inline function.
 * @param message The log message.
 * @param throwable Optionally a [Throwable] to log, otherwise `null`.
 * @param tag The tag of the message.
 */
fun logFatal(message: String, throwable: Throwable? = null, tag: String = generateLogTag()) {
    Log.println(
        Log.ASSERT,
        tag,
        if (throwable == null) message else "$message\n${Log.getStackTraceString(throwable)}"
    )
}

/**
 * Note: This method can not be inlined because [KT-8628](https://youtrack.jetbrains.com/issue/KT-8628)
 * In addition, the determined line number is incorrect if called from an inline function.
 * @return A string suitable as tag for logcat entries.
 * The string consists of the file name and the line in which this method was called.
 */
@CheckResult
@PublishedApi
internal fun generateLogTag(): String {
    // We have to ignore the first four elements, the first two are internal,
    // the third is this method and the fourth is the log method that called this method.
    val st = Thread.currentThread().stackTrace[4]
    val line = ":${st.lineNumber}"
    return if (st.fileName.length > (23 - line.length)) {
        st.fileName.take(21 - line.length) + ".." + line
    } else st.fileName + line
}

