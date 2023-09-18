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

@file:JvmName(name = "TileServiceUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.service

import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Get the tile from this service and update it.
 * [Tile.updateTile] is invoked in the tile after [action].
 * If the [TileService.getQsTile] returns null (usually when the service is not bound)
 * [action] is not invoked and the tile is not updated.
 */
@RequiresApi(Build.VERSION_CODES.N)
inline fun TileService.updateTile(action: Tile.() -> Unit) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    qsTile?.update(action = action)
}