@file:JvmName(name = "TileUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.service

import android.os.Build
import android.service.quicksettings.Tile
import androidx.annotation.RequiresApi
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * `true` if the tile is in [Tile.STATE_ACTIVE].
 */
inline val Tile.isActive: Boolean @RequiresApi(Build.VERSION_CODES.N)
get() = state == Tile.STATE_ACTIVE

/**
 * `true` if the tile is in [Tile.STATE_INACTIVE].
 */
inline val Tile.isInactive: Boolean @RequiresApi(Build.VERSION_CODES.N)
get() = state == Tile.STATE_INACTIVE

/**
 * `true` if the tile is not in state [Tile.STATE_UNAVAILABLE].
 */
inline val Tile.isAvailable: Boolean @RequiresApi(Build.VERSION_CODES.N)
get() = state != Tile.STATE_UNAVAILABLE

/**
 * Invokes [Tile.updateTile] on the receiver after [action] was invoked.
 */
@RequiresApi(Build.VERSION_CODES.N)
inline fun Tile.update(
    action: Tile.() -> Unit
) {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    updateTile()
}

