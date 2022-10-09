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