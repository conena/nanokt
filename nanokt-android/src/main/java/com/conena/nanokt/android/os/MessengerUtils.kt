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
