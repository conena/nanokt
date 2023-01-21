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