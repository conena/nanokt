@file:JvmName(name = "ContextServiceUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.content

import android.Manifest
import android.accounts.AccountManager
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.GrammaticalInflectionManager
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
import android.companion.virtual.VirtualDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.om.OverlayManager
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.ShortcutManager
import android.credentials.CredentialManager
import android.devicelock.DeviceLockManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.biometrics.BiometricManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.health.connect.HealthConnectManager
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
import androidx.annotation.CheckResult
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

    @get:CheckResult
    inline val accessibilityManager get() = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    @get:CheckResult
    inline val accountManager get() = context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

    @get:CheckResult
    inline val activityManager get() = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    @get:CheckResult
    inline val alarmManager get() = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.KITKAT)
    inline val appOpsManager get() = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val appWidgetManager get() = context.getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager

    @get:CheckResult
    inline val audioManager get() = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val batteryManager get() = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.Q)
    inline val biometricManager get() = context.getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    inline val bluetoothManager get() = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val cameraManager get() = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.KITKAT)
    inline val captioningManager get() = context.getSystemService(Context.CAPTIONING_SERVICE) as CaptioningManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.M)
    inline val carrierConfigManager get() = context.getSystemService(Context.CARRIER_CONFIG_SERVICE) as CarrierConfigManager

    @get:CheckResult
    @get:MainThread
    inline val clipboardManager get() = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.O)
    inline val companionDeviceManager get() = context.getSystemService(Context.COMPANION_DEVICE_SERVICE) as CompanionDeviceManager

    @get:CheckResult
    inline val connectivityManager get() = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.KITKAT)
    inline val consumerIrManager get() = context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val credentialManager get() = context.getSystemService(Context.CREDENTIAL_SERVICE) as CredentialManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.P)
    inline val crossProfileApps get() = context.getSystemService(Context.CROSS_PROFILE_APPS_SERVICE) as CrossProfileApps

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val deviceLockManager get() = context.getSystemService(Context.DEVICE_LOCK_SERVICE) as DeviceLockManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    inline val devicePolicyManager get() = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.S)
    inline val displayHashManager get() = context.getSystemService(Context.DISPLAY_HASH_SERVICE) as DisplayHashManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline val displayManager get() = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

    @get:CheckResult
    inline val downloadManager get() = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.P)
    inline val euiccManager get() = context.getSystemService(Context.EUICC_SERVICE) as EuiccManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val grammaticalInflectionManager get() = context.getSystemService(Context.GRAMMATICAL_INFLECTION_SERVICE) as GrammaticalInflectionManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.N)
    inline val hardwarePropertiesManager get() = context.getSystemService(Context.HARDWARE_PROPERTIES_SERVICE) as HardwarePropertiesManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val healthConnectManager get() = context.getSystemService(Context.HEALTHCONNECT_SERVICE) as HealthConnectManager

    @get:CheckResult
    inline val inputManager get() = context.getSystemService(Context.INPUT_SERVICE) as InputManager

    @get:CheckResult
    inline val inputMethodManager get() = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.P)
    inline val ipSecManager get() = context.getSystemService(Context.IPSEC_SERVICE) as IpSecManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val jobScheduler get() = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

    @get:CheckResult
    inline val keyguardManager get() = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val launcherApps get() = context.getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps

    @get:CheckResult
    inline val layoutInflater get() = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.TIRAMISU)
    inline val localeManager get() = context.getSystemService(Context.LOCALE_SERVICE) as LocaleManager

    @get:CheckResult
    inline val locationManager get() = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.S)
    inline val mediaCommunicationManager get() = context.getSystemService(Context.MEDIA_COMMUNICATION_SERVICE) as MediaCommunicationManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.S)
    inline val mediaMetricsManager get() = context.getSystemService(Context.MEDIA_METRICS_SERVICE) as MediaMetricsManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val mediaProjectionManager get() = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

    @get:CheckResult
    inline val mediaRouter get() = context.getSystemService(Context.MEDIA_ROUTER_SERVICE) as MediaRouter

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val mediaSessionManager get() = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.M)
    inline val midiManager get() = context.getSystemService(Context.MIDI_SERVICE) as MidiManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.M)
    inline val networkStatsManager get() = context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

    @get:CheckResult
    inline val nfcManager get() = context.getSystemService(Context.NFC_SERVICE) as NfcManager

    @get:CheckResult
    inline val notificationManager get() = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @get:CheckResult
    @get:RequiresPermission(Manifest.permission.INTERNET)
    inline val nsdManager get() = context.getSystemService(Context.NSD_SERVICE) as NsdManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val overlayManager get() = context.getSystemService(Context.OVERLAY_SERVICE) as OverlayManager

    @get:CheckResult
    inline val powerManager get() = context.getSystemService(Context.POWER_SERVICE) as PowerManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.KITKAT)
    inline val printManager get() = context.getSystemService(Context.PRINT_SERVICE) as PrintManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val restrictionsManager get() = context.getSystemService(Context.RESTRICTIONS_SERVICE) as RestrictionsManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.Q)
    inline val roleManager get() = context.getSystemService(Context.ROLE_SERVICE) as RoleManager

    @get:CheckResult
    inline val searchManager get() = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager

    @get:CheckResult
    inline val sensorManager get() = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.N_MR1)
    inline val shortcutManager get() = context.getSystemService(Context.SHORTCUT_SERVICE) as? ShortcutManager

    @get:CheckResult
    inline val storageManager get() = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.O)
    inline val storageStatsManager get() = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    inline val subscriptionManager get() = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.N)
    inline val systemHealthManager get() = context.getSystemService(Context.SYSTEM_HEALTH_SERVICE) as SystemHealthManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val telecomManager get() = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager

    @get:CheckResult
    inline val telephonyManager get() = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.O)
    inline val textClassificationManager get() = context.getSystemService(Context.TEXT_CLASSIFICATION_SERVICE) as TextClassificationManager

    @get:CheckResult
    inline val textServicesManager get() = context.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline val tvInputManager get() = context.getSystemService(Context.TV_INPUT_SERVICE) as? TvInputManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.TIRAMISU)
    inline val tvInteractiveAppManager get() = context.getSystemService(Context.TV_INTERACTIVE_APP_SERVICE) as? TvInteractiveAppManager

    @get:CheckResult
    inline val uiModeManager get() = context.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    inline val usageStatsManager get() = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    inline val usbManager get() = context.getSystemService(Context.USB_SERVICE) as? UsbManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    inline val userManager get() = context.getSystemService(Context.USER_SERVICE) as UserManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.S)
    inline val vibratorManager get() = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    inline val virtualDeviceManager get() = context.getSystemService(Context.VIRTUAL_DEVICE_SERVICE) as? VirtualDeviceManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.R)
    inline val vpnManager get() = context.getSystemService(Context.VPN_MANAGEMENT_SERVICE) as VpnManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    inline val wallpaperManager get() = context.getSystemService(Context.WALLPAPER_SERVICE) as? WallpaperManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.O)
    inline val wifiAwareManager get() = context.getSystemService(Context.WIFI_AWARE_SERVICE) as? WifiAwareManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    inline val wifiManager get() = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

    /**
     * Always `null` for instant apps. Should be non-null otherwise.
     */
    @get:CheckResult
    inline val wifiP2pManager get() = context.getSystemService(Context.WIFI_P2P_SERVICE) as? WifiP2pManager

    @get:CheckResult
    @get:RequiresApi(Build.VERSION_CODES.P)
    inline val wifiRttManager get() = context.getSystemService(Context.WIFI_RTT_RANGING_SERVICE) as? WifiRttManager

    @get:CheckResult
    inline val windowManager get() = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

}

inline val Context.systemServices get() = ServiceProvider(this)

@get:CheckResult
inline val Context.activityManager get() = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

@get:CheckResult
inline val Context.alarmManager get() = getSystemService(Context.ALARM_SERVICE) as AlarmManager

@get:CheckResult
inline val Context.audioManager get() = getSystemService(Context.AUDIO_SERVICE) as AudioManager

@get:CheckResult
@get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline val Context.batteryManager get() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager

@get:CheckResult
@get:RequiresApi(Build.VERSION_CODES.Q)
inline val Context.biometricManager get() = getSystemService(Context.BIOMETRIC_SERVICE) as BiometricManager

@get:CheckResult
@get:RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
inline val Context.bluetoothManager get() = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

@get:CheckResult
@get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline val Context.cameraManager get() = getSystemService(Context.CAMERA_SERVICE) as CameraManager

@get:CheckResult
@get:MainThread
inline val Context.clipboardManager get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

@get:CheckResult
inline val Context.connectivityManager get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

@get:CheckResult
inline val Context.downloadManager get() = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

@get:CheckResult
inline val Context.inputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

@get:CheckResult
@get:RequiresApi(Build.VERSION_CODES.LOLLIPOP)
inline val Context.jobScheduler get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

@get:CheckResult
inline val Context.keyguardManager get() = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

@get:CheckResult
inline val Context.layoutInflater get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

@get:CheckResult
inline val Context.locationManager get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

@get:CheckResult
inline val Context.nfcManager get() = getSystemService(Context.NFC_SERVICE) as NfcManager

@get:CheckResult
inline val Context.notificationManager get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

@get:CheckResult
inline val Context.powerManager get() = getSystemService(Context.POWER_SERVICE) as PowerManager

@get:CheckResult
inline val Context.storageManager get() = getSystemService(Context.STORAGE_SERVICE) as StorageManager

@get:CheckResult
inline val Context.telephonyManager get() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

/**
 * Always `null` for instant apps. Should be non-null otherwise.
 */
@get:CheckResult
inline val Context.wifiManager get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager

@get:CheckResult
inline val Context.windowManager get() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

