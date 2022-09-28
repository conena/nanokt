@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.conena.nanokt

/**
 * Function that is supposed to mimic Java's beloved ternary operator.
 * Unlike the ternary operator in java, this function does not perform short-circuit evaluation.
 * Both [ifTrue] and [ifFalse] will always be evaluated.
 * @param T The type of the two parameters and the return value.
 * @param ifTrue Returned if the boolean is `true`.
 * @param ifFalse Returned if the boolean is `false`.
 * @return [ifTrue] or [ifFalse] based on the value of the boolean.
 */
inline fun <reified T> Boolean.ternary(ifTrue: T, ifFalse: T): T {
    return if (this) ifTrue else ifFalse
}