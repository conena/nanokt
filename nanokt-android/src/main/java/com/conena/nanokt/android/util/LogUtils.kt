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

