@file:JvmName(name = "MessengerUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os

import android.os.DeadObjectException
import android.os.Message
import android.os.Messenger

/**
 * Same as [Messenger.send] but returns a [Result] instead of throwing an [DeadObjectException]
 * if the target handler is dead.
 * @param message The [Message] to send.
 * @return A [Result] object that indicates the result of the action.
 * In case of an error the exception is encapsulated in the [Result].
 * You can use [Result.onFailure] for error handling.
 */
inline fun Messenger.sendCatching(message: Message): Result<Unit> {
    return kotlin.runCatching { send(message) }
}
