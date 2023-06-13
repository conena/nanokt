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

package com.conena.nanokt

/**
 * Same as [to] but returns null if either the receiver or the parameter is null.
 * @param that The second element of the pair.
 * @return A [Pair] if the receiver and [that] are not null.
 */
infix fun <A, B> A?.toOrNull(that: B?): Pair<A, B>? {
    return if (this == null || that == null) {
        return null
    } else Pair(first = this, second = that)
}