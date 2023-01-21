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

package com.conena.nanokt.android.content

import android.app.Instrumentation
import android.content.Context
import android.os.Build
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ContextServiceUtilsTest {

    private lateinit var context: Context
    private lateinit var instrumentation: Instrumentation

    @Before
    fun setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation()
        context = instrumentation.context
    }

    @Test
    fun accessibilityManager() {
        assertThat(context.systemServices.accessibilityManager).isNotNull()
    }

    @Test
    fun accountManager() {
        assertThat(context.systemServices.accountManager).isNotNull()
    }

    @Test
    fun activityManager() {
        assertThat(context.systemServices.activityManager).isNotNull()
    }

    @Test
    fun alarmManager() {
        assertThat(context.systemServices.alarmManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
    @Test
    fun appOpsManager() {
        assertThat(context.systemServices.appOpsManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun appWidgetManager() {
        assertThat(context.systemServices.appWidgetManager).isNotNull()
    }

    @Test
    fun audioManager() {
        assertThat(context.systemServices.audioManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun batteryManager() {
        assertThat(context.systemServices.batteryManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.Q)
    @Test
    fun biometricManager() {
        assertThat(context.systemServices.biometricManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    fun bluetoothManager() {
        assertThat(context.systemServices.bluetoothManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun cameraManager() {
        assertThat(context.systemServices.cameraManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
    @Test
    fun captioningManager() {
        assertThat(context.systemServices.captioningManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.M)
    @Test
    fun carrierConfigManager() {
        assertThat(context.systemServices.carrierConfigManager).isNotNull()
    }

    @Test
    fun clipboardManager() {
        var called = false
        instrumentation.runOnMainSync {
            assertThat(context.systemServices.clipboardManager).isNotNull()
            called = true
        }
        assertThat(called).isTrue()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
    @Test
    fun companionDeviceManager() {
        assertThat(context.systemServices.companionDeviceManager).isNotNull()
    }

    @Test
    fun connectivityManager() {
        assertThat(context.systemServices.connectivityManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
    @Test
    fun consumerIrManager() {
        assertThat(context.systemServices.consumerIrManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun crossProfileApps() {
        assertThat(context.systemServices.crossProfileApps).isNotNull()
    }

    @Test
    fun devicePolicyManager() {
        assertThat(context.systemServices.devicePolicyManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.S)
    @Test
    fun displayHashManager() {
        assertThat(context.systemServices.displayHashManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    fun displayManager() {
        assertThat(context.systemServices.displayManager).isNotNull()
    }

    @Test
    fun downloadManager() {
        assertThat(context.systemServices.downloadManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun euiccManager() {
        assertThat(context.systemServices.euiccManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.N)
    @Test
    fun hardwarePropertiesManager() {
        assertThat(context.systemServices.hardwarePropertiesManager).isNotNull()
    }

    @Test
    fun inputManager() {
        assertThat(context.systemServices.inputManager).isNotNull()
    }

    @Test
    fun inputMethodManager() {
        assertThat(context.systemServices.inputMethodManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun ipSecManager() {
        assertThat(context.systemServices.ipSecManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun jobScheduler() {
        assertThat(context.systemServices.jobScheduler).isNotNull()
    }

    @Test
    fun keyguardManager() {
        assertThat(context.systemServices.keyguardManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun launcherApps() {
        assertThat(context.systemServices.launcherApps).isNotNull()
    }

    @Test
    fun layoutInflater() {
        assertThat(context.systemServices.layoutInflater).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.TIRAMISU)
    @Test
    fun localeManager() {
        assertThat(context.systemServices.localeManager).isNotNull()
    }

    @Test
    fun locationManager() {
        assertThat(context.systemServices.locationManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.S)
    @Test
    fun mediaCommunicationManager() {
        assertThat(context.systemServices.mediaCommunicationManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.S)
    @Test
    fun mediaMetricsManager() {
        assertThat(context.systemServices.mediaMetricsManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun mediaProjectionManager() {
        assertThat(context.systemServices.mediaProjectionManager).isNotNull()
    }

    @Test
    fun mediaRouter() {
        assertThat(context.systemServices.mediaRouter).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun mediaSessionManager() {
        assertThat(context.systemServices.mediaSessionManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.M)
    @Test
    fun midiManager() {
        assertThat(context.systemServices.midiManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.M)
    @Test
    fun networkStatsManager() {
        assertThat(context.systemServices.networkStatsManager).isNotNull()
    }

    @Test
    fun nfcManager() {
        assertThat(context.systemServices.nfcManager).isNotNull()
    }

    @Test
    fun notificationManager() {
        assertThat(context.systemServices.notificationManager).isNotNull()
    }

    @Test
    fun nsdManager() {
        assertThat(context.systemServices.nsdManager).isNotNull()
    }

    @Test
    fun powerManager() {
        assertThat(context.systemServices.powerManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
    @Test
    fun printManager() {
        assertThat(context.systemServices.printManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun restrictionsManager() {
        assertThat(context.systemServices.restrictionsManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.Q)
    @Test
    fun roleManager() {
        assertThat(context.systemServices.roleManager).isNotNull()
    }

    @Test
    fun searchManager() {
        assertThat(context.systemServices.searchManager).isNotNull()
    }

    @Test
    fun sensorManager() {
        assertThat(context.systemServices.sensorManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.N_MR1)
    @Test
    fun shortcutManager() {
        assertThat(context.systemServices.shortcutManager).isNotNull()
    }

    @Test
    fun storageManager() {
        assertThat(context.systemServices.storageManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
    @Test
    fun storageStatsManager() {
        assertThat(context.systemServices.storageStatsManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Test
    fun subscriptionManager() {
        assertThat(context.systemServices.subscriptionManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.N)
    @Test
    fun systemHealthManager() {
        assertThat(context.systemServices.systemHealthManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun telecomManager() {
        assertThat(context.systemServices.telecomManager).isNotNull()
    }

    @Test
    fun telephonyManager() {
        assertThat(context.systemServices.telephonyManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
    @Test
    fun textClassificationManager() {
        assertThat(context.systemServices.textClassificationManager).isNotNull()
    }

    @Test
    fun textServicesManager() {
        assertThat(context.systemServices.textServicesManager).isNotNull()
    }

    @Test
    fun uiModeManager() {
        assertThat(context.systemServices.uiModeManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Test
    fun usageStatsManager() {
        assertThat(context.systemServices.usageStatsManager).isNotNull()
    }

    @Test
    fun usbManager() {
        assertThat(context.systemServices.usbManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    fun userManager() {
        assertThat(context.systemServices.userManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.S)
    @Test
    fun vibratorManager() {
        assertThat(context.systemServices.vibratorManager).isNotNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.R)
    @Test
    fun vpnManager() {
        assertThat(context.systemServices.vpnManager).isNotNull()
    }

    @Test
    fun wallpaperManager() {
        assertThat(context.systemServices.wallpaperManager).isNotNull()
    }

    @Test
    fun wifiManager() {
        assertThat(context.systemServices.wifiManager).isNotNull()
    }

    @Test
    fun wifiP2pManager() {
        assertThat(context.systemServices.wifiP2pManager).isNotNull()
    }

    @Test
    fun windowManager() {
        assertThat(context.systemServices.windowManager).isNotNull()
    }

}