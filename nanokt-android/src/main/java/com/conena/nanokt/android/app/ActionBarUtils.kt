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

@file:JvmName(name = "ActionBarUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.app

import androidx.appcompat.app.ActionBar

/**
 * Hide the ActionBar if it is currently showing.
 * Show the ActionBar if it is currently hidden.
 * @see ActionBar.isShowing
 * @see ActionBar.hide
 * @see ActionBar.show
 */
inline fun ActionBar.toggle() = if (isShowing) hide() else show()