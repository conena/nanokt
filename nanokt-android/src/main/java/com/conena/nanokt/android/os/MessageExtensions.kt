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

@file:JvmName(name = "MessageUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.os

import android.os.Message
import android.os.Messenger

/**
 * Chainable setter for [Message.replyTo].
 */
inline fun Message.setReplyTo(replyTo: Messenger?): Message {
    this.replyTo = replyTo
    return this
}

/**
 * Chainable setter for [Message.what].
 */
inline fun Message.setWhat(what: Int): Message {
    this.what = what
    return this
}

/**
 * Chainable setter for [Message.arg1].
 */
inline fun Message.setArg1(arg1: Int): Message {
    this.arg1 = arg1
    return this
}

/**
 * Chainable setter for [Message.arg2].
 */
inline fun Message.setArg2(arg2: Int): Message {
    this.arg2 = arg2
    return this
}