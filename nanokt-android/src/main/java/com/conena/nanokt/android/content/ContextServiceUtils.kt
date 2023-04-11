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

@file:JvmName(name = "ContextServiceUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.Manifest
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.KeyguardManager
import android.app.LocaleManager
import android.app.NotificationManager
import android.app.SearchManager
import android.app.UiModeManager
import android.app.WallpaperManager
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.role.RoleManager
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.ShortcutManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.biometrics.BiometricManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaCommunicationManager
import android.media.MediaRouter
import android.media.metrics.MediaMetricsManager
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.media.tv.interactive.TvInteractiveAppManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.VpnManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.BatteryManager
import android.os.Build
import android.os.HardwarePropertiesManager
import android.os.PowerManager
import android.os.UserManager
import android.os.VibratorManager
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.telephony.euicc.EuiccManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.displayhash.DisplayHashManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * Helper class that provides getters for all available, non-deprecated, Android services.
 * Used to not bloat the autocomplete suggestions for context instances too much.
 * ### It is strongly discouraged to store instances of this class in variables.
 * If this is still desired, an ApplicationContext must have been used to obtain the instance.
 */
@JvmInline
value class ServiceProvider @PublishedApi internal constructor(val context: Context) {

    inline val accessibilityManager get() = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    inline val accountManager get() = context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

    inline val activityManager get() = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    inline val alarmManager get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    inline val appOpsManager
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        get() = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

    inline val appWidgetManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager

    inline val audioManager get() = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    inline val batteryManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

    inline val biometricManager
        @RequiresApi(Build.VERSION_CODES.Q)
        get() = context.getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager

    inline val bluetoothManager
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        get() = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    inline val cameraManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    inline val captioningManager
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        get() = context.getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager

    inline val carrierConfigManager
        @RequiresApi(Build.VERSION_CODES.M)
        get() = context.getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager

    inline val clipboardManager
        @MainThread
        get() = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    inline val companionDeviceManager
        @RequiresApi(Build.VERSION_CODES.O)
        get() = context.getSystemService(Context.COMPANION_DEVICE_SERVICE) as CompanionDeviceManager

    inline val connectivityManager get() = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    inline val consumerIrManager
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        get() = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

    inline val crossProfileApps
        @SuppressLint("ServiceCast") @RequiresApi(Build.VERSION_CODES.P)
        get() = context.getSystemService(Context.CROSS_PROFILE_APPS_SERVICE) as CrossProfileApps

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val devicePolicyManager get() = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager

    inline val displayHashManager
        @RequiresApi(Build.VERSION_CODES.S)
        get() = context.getSystemService(Context.DISPLAY_HASH_SERVICE) as DisplayHashManager

    inline val displayManager
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        get() = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

    inline val downloadManager get() = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    inline val euiccManager
        @RequiresApi(Build.VERSION_CODES.P)
        get() = context.getSystemService(Context.EUICC_SERVICE) as EuiccManager

    inline val hardwarePropertiesManager
        @RequiresApi(Build.VERSION_CODES.N)
        get() = context.getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager

    inline val inputManager get() = context.getSystemService(Context.INPUT_SERVICE) as InputManager

    inline val inputMethodManager get() = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    inline val ipSecManager
        @RequiresApi(Build.VERSION_CODES.P)
        get() = context.getSystemService(Context.IPSEC_SERVICE) as IpSecManager

    inline val jobScheduler
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

    inline val keyguardManager get() = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

    inline val launcherApps
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps

    inline val layoutInflater get() = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    inline val localeManager
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        get() = context.getSystemService(Context.LOCALE_SERVICE) as LocaleManager

    inline val locationManager get() = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    inline val mediaCommunicationManager
        @RequiresApi(Build.VERSION_CODES.S)
        get() = context.getSystemService(Context.MEDIA_COMMUNICATION_SERVICE) as MediaCommunicationManager

    inline val mediaMetricsManager
        @RequiresApi(Build.VERSION_CODES.S)
        get() = context.getSystemService(Context.MEDIA_METRICS_SERVICE) as MediaMetricsManager

    inline val mediaProjectionManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

    inline val mediaRouter get() = context.getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter

    inline val mediaSessionManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

    inline val midiManager
        @RequiresApi(Build.VERSION_CODES.M)
        get() = context.getSystemService(Context.MIDI_SERVICE) as MidiManager

    inline val networkStatsManager
        @RequiresApi(Build.VERSION_CODES.M)
        get() = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

    inline val nfcManager get() = context.getSystemService(Context.NFC_SERVICE) as NfcManager

    inline val notificationManager get() = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    inline val nsdManager
        @RequiresPermission(Manifest.permission.INTERNET)
        get() = context.getSystemService(Context.NSD_SERVICE) as NsdManager

    inline val powerManager get() = context.getSystemService(Context.POWER_SERVICE) as PowerManager

    inline val printManager
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        get() = context.getSystemService(Context.PRINT_SERVICE) as PrintManager

    inline val restrictionsManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager

    inline val roleManager
        @RequiresApi(Build.VERSION_CODES.Q)
        get() = context.getSystemService(Context.ROLE_SERVICE) as RoleManager

    inline val searchManager get() = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager

    inline val sensorManager get() = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val shortcutManager
        @RequiresApi(Build.VERSION_CODES.N_MR1)
        get() = context.getSystemService(Context.SHORTCUT_SERVICE) as? ShortcutManager

    inline val storageManager get() = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager

    inline val storageStatsManager
        @RequiresApi(Build.VERSION_CODES.O)
        get() = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

    inline val subscriptionManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
        get() = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

    inline val systemHealthManager
        @RequiresApi(Build.VERSION_CODES.N)
        get() = context.getSystemService(Context.SYSTEM_HEALTH_SERVICE) as SystemHealthManager

    inline val telecomManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager

    inline val telephonyManager get() = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    inline val textClassificationManager
        @RequiresApi(Build.VERSION_CODES.O)
        get() = context.getSystemService(Context.TEXT_CLASSIFICATION_SERVICE) as TextClassificationManager

    inline val textServicesManager get() = context.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager

    inline val tvInputManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = context.getSystemService(Context.TV_INPUT_SERVICE) as? TvInputManager

    inline val tvInteractiveAppManager
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        get() = context.getSystemService(Context.TV_INTERACTIVE_APP_SERVICE) as? TvInteractiveAppManager

    inline val uiModeManager get() = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

    inline val usageStatsManager
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
        get() = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val usbManager get() = context.getSystemService(Context.USB_SERVICE) as? UsbManager

    inline val userManager
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        get() = context.getSystemService(Context.USER_SERVICE) as UserManager

    inline val vibratorManager
        @RequiresApi(Build.VERSION_CODES.S)
        get() = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

    inline val vpnManager
        @RequiresApi(Build.VERSION_CODES.R)
        get() = context.getSystemService(Context.VPN_MANAGEMENT_SERVICE) as VpnManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val wallpaperManager get() = context.getSystemService(Context.WALLPAPER_SERVICE) as? WallpaperManager

    inline val wifiAwareManager
        @RequiresApi(Build.VERSION_CODES.O)
        get() = context.getSystemService(Context.WIFI_AWARE_SERVICE) as? WifiAwareManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val wifiManager get() = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    inline val wifiP2pManager get() = context.getSystemService(Context.WIFI_P2P_SERVICE) as? WifiP2pManager

    inline val wifiRttManager
        @RequiresApi(Build.VERSION_CODES.P)
        get() = context.getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as? WifiRttManager

    inline val windowManager get() = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

}

inline val Context.systemServices get() = ServiceProvider(this)

inline val Context.activityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

inline val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

inline val Context.audioManager get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager

inline val Context.batteryManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager

inline val Context.biometricManager
    @RequiresApi(Build.VERSION_CODES.Q)
    get() = getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager

inline val Context.bluetoothManager
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    get() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

inline val Context.cameraManager
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.CAMERA_SERVICE) as CameraManager

inline val Context.clipboardManager
    @MainThread
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

inline val Context.connectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

inline val Context.downloadManager get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

inline val Context.inputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

inline val Context.jobScheduler
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

inline val Context.keyguardManager get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

inline val Context.layoutInflater get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

inline val Context.locationManager get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

inline val Context.nfcManager get() = getSystemService(Context.NFC_SERVICE) as NfcManager

inline val Context.notificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

inline val Context.powerManager get() = getSystemService(Context.POWER_SERVICE) as PowerManager

inline val Context.storageManager get() = getSystemService(Context.STORAGE_SERVICE) as StorageManager

inline val Context.telephonyManager get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

/**
 * Always `null` for instant apps. Should be non-null otherwise.
 */
inline val Context.wifiManager get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

inline val Context.windowManager get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

