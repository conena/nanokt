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

@file:JvmName(name = "ServiceUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.app

import android.app.Service
import android.os.Build


/**
 * Compatibility method for [Service.stopForeground]. On versions below [Build.VERSION_CODES.N]
 * this methods calls the old [Service.stopForeground] method and passes true as parameter
 * when the [notificationBehavior] is [Service.STOP_FOREGROUND_REMOVE]. Otherwise false is passed.
 * On newer android versions this just calls the new [Service.stopForeground] method.
 * @param notificationBehavior One of the below listed constants.
 * @see Service.STOP_FOREGROUND_REMOVE
 * @see Service.STOP_FOREGROUND_DETACH
 * @see Service.STOP_FOREGROUND_LEGACY
 */
inline fun Service.stopForegroundCompat(
    notificationBehavior: Int
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        stopForeground(notificationBehavior)
    } else {
        @Suppress("DEPRECATION", "InlinedApi")
        stopForeground(notificationBehavior == Service.STOP_FOREGROUND_REMOVE)
    }
}

/**
 * Compatibility method for [Service.stopForeground]. On versions below [Build.VERSION_CODES.N]
 * this methods calls the old [Service.stopForeground] method and passes true as parameter
 * to remove the notification.
 * On newer android versions this just calls the new [Service.stopForeground] method
 * with [Service.STOP_FOREGROUND_REMOVE] as parameter.
 */
inline fun Service.stopForegroundAndRemoveNotification() {
    // noinspection InlinedApi
    stopForegroundCompat(notificationBehavior = Service.STOP_FOREGROUND_REMOVE)
}

/**
 * Compatibility method for [Service.stopForeground]. On versions below [Build.VERSION_CODES.N]
 * this methods calls the old [Service.stopForeground] method and passes false as parameter
 * to not remove the notification.
 * On newer android versions this just calls the new [Service.stopForeground] method
 * with [Service.STOP_FOREGROUND_DETACH] as parameter.
 */
inline fun Service.stopForegroundAndDetachNotification() {
    // noinspection InlinedApi
    stopForegroundCompat(notificationBehavior = Service.STOP_FOREGROUND_DETACH)
}