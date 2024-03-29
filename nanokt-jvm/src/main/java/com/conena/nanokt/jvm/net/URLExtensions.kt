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

package com.conena.nanokt.jvm.net

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * @return The current string encoded in application/x-www-form-urlencoded format using UTF-8.
 */
inline fun String.encodeUrl(): String = URLEncoder.encode(this, Charsets.UTF_8.name())

/**
 * @return Decodes the current string from application/x-www-form-urlencoded format using UTF-8.
 */
inline fun String.decodeUrl(): String = URLDecoder.decode(this, Charsets.UTF_8.name())