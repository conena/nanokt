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

package com.conena.nanokt.jvm

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 * `true` if the [Field] is `public`.
 */
inline val Field.isPublic: Boolean get() {
    return Modifier.isPublic(modifiers)
}

/**
 * `true` if the [Field] is `private`.
 */
inline val Field.isPrivate: Boolean get() {
    return Modifier.isPrivate(modifiers)
}

/**
 * `true` if the [Field] is `protected`.
 */
inline val Field.isProtected: Boolean get() {
    return Modifier.isProtected(modifiers)
}

/**
 * `true` if the [Field] is `static`.
 */
inline val Field.isStatic: Boolean get() {
    return Modifier.isStatic(modifiers)
}

/**
 * `true` if the [Field] is `final`.
 */
inline val Field.isFinal: Boolean get() {
    return Modifier.isFinal(modifiers)
}

/**
 * `true` if the [Field] is `synchronized`.
 */
inline val Field.isSynchronized: Boolean get() {
    return Modifier.isSynchronized(modifiers)
}

/**
 * `true` if the [Field] is `volatile`.
 */
inline val Field.isVolatile: Boolean get() {
    return Modifier.isVolatile(modifiers)
}

/**
 * `true` if the [Field] is `transient`.
 */
inline val Field.isTransient: Boolean get() {
    return Modifier.isTransient(modifiers)
}