# NanoKt

Introducing NanoKt – an elegant and lightweight solution for streamlined Android development that offers an extensive collection of up-to-date extension functions covering many areas of the Android framework and the Java and Kotlin standard libraries. NanoKt will make your code more elegant, efficient and save you time in the process. Moreover, this is achieved without compromising performance, as almost all provided functions and properties are inlined at compile time.

**Why yet another extension library?**

NanoKt stands out by providing well-documented functions with recommended annotations for an AndroidX-like experience. The thoughtful structure and method naming, inspired by the Android framework, aims to eliminate redundant code without enforcing a one-size-fits-all framework. It is designed to integrate seamlessly into various Android projects. I've been using it for the entire - more than a year - beta phase in production for several of my apps (including [Logcat Reader Professional][LRP link]) and plan to actively develop it further and integrate it into all my projects.

The library is divided into modules for pure Kotlin, Kotlin-JVM, and Kotlin-Android, making it compatible with a wide range of projects. I plan to release a compatible version for Kotlin Multiplatform at a later date.

# Table of contents

- [Getting started](#getting-started)
- [Examples](#examples)
    - [Copying to the clipboard](#copying-to-the-clipboard)
    - [Accessing system services](#accessing-system-services)
    - [Reading configuration (e.g. night mode)](#reading-configuration-eg-night-mode)
    - [Reading system settings (e.g. airplane mode)](#reading-system-settings-eg-airplane-mode))
    - [Starting activities](#starting-activities)
    - [Starting the Play Store](#starting-the-play-store)
    - [Starting other apps (e.g. settings, mail client, etc.)](#starting-other-apps-eg-settings-mail-client-etc)
    - [Conversion of complex units](#conversion-of-complex-units)
    - [Working with SharedPreferences](#working-with-sharedpreferences)
    - [Accessing theme attributes](#accessing-theme-attributes)
    - [Working with bundles](#working-with-bundles)
    - [Working with services](#working-with-services)
    - [Working with views](#working-with-views)
    - [Working with bitmaps](#working-with-bitmaps)
    - [Encoding/Decoding Base64](#encodingdecoding-base64)
    - [Debug logging](#debug-logging)
    - [Other examples](#other-examples)
- [Experimental parts of the library](#experimental-parts-of-the-library)
- [Versioning](#versioning)
- [FAQ](#faq)
    - [Is Kotlin multiplatform support planned?](#is-kotlin-multiplatform-support-planned)
    - [Will there be a version for Kotlin coroutines?](#will-there-be-a-version-for-kotlin-coroutines)
    - [Will there be a version for Jetpack Compose?](#will-there-be-a-version-for-jetpack-compose)
    - [Will there be a version for Library xy?](#will-there-be-a-version-for-library-xy)
- [Contribution](#contribution)
- [License](#license)

# Getting started
[![Maven Central](https://img.shields.io/maven-central/v/com.conena.nanokt/nanokt)](https://mvnrepository.com/artifact/com.conena.nanokt/nanokt-android)
[![API](https://img.shields.io/badge/Min%20API-16-orange)](https://developer.android.com/about/versions/jelly-bean#android-4.1)
[![API](https://img.shields.io/badge/Compiled%20API-33-orange)](https://developer.android.com/about/versions/13)
[![GitHub followers](https://img.shields.io/github/followers/SageDroid.svg?style=social&label=Follow&maxAge=2592000)](https://github.com/SageDroid)
[![GitHub stars](https://img.shields.io/github/stars/conena/nanokt?style=social&label=Star&maxAge=2592000)](https://github.com/conena/nanokt/)

To start using NanoKt in your project, add the appropriate dependencies to your build.gradle file.

```groovy
repositories {
    mavenCentral()
}

dependencies {
    // Extensions for Android
    implementation 'com.conena.nanokt:nanokt-android:1.1.0'
    // Pure Kotlin Extensions
    implementation 'com.conena.nanokt:nanokt:1.1.0'
    // Extensions for the Java standard library
    implementation 'com.conena.nanokt:nanokt-jvm:1.1.0'
    // Extensions for threads in Android. Not needed if you use coroutines
    implementation 'com.conena.nanokt:nanokt-android-threads:1.1.0'
}
```

# Examples
Below are some examples of how NanoKt takes the pain out of writing boilerplate code and follows current Android documentation recommendations. Examples of the Android-independent NanoKt-JVM and NanoKt (Kotlin) are not listed here yet. You can browse the repository and look at the extensions offered.

### Copying to the clipboard
It is requested to copy plain text to the clipboard and notify the user about the copying, but without displaying the text, e.g. because it is an access code.

Solution without NanoKt
```kotlin
val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
val clipData = ClipData.newPlainText(null, textToCopy)
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    clipData.description.extras = PersistableBundle().apply {
        putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
    }
}
clipboardManager.setPrimaryClip(clipData)
if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
    Toast.makeText(this, "Access code copied.", Toast.LENGTH_SHORT).show()
}
```

Solution with NanoKt
```kotlin
clipboardManager.setPrimaryClip(text = textToCopy, isSensitive = true) {
    toastShort("Access code copied.")
}
```
The example takes into account the [current recommendations on user feedback when copying text to the clipboard][clipboard feedback], as well as the changes in Android 13. The trailing lambda with the toast is only invoked on devices with Android versions below Android 13.

### Accessing system services
You may wonder where the clipboard manager comes from in the first example. A brief look at the source code provides the answer.
```kotlin
@get:CheckResult
@get:MainThread
inline val Context.clipboardManager get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
```
NanoKt makes all available services available as context extension properties. Frequently used services are available directly in the context, less frequently used services have to be called via a systemServices extension. Since the ClipboardManager can only be obtained on the MainThread, this is indicated with an annotation so that you receive a warning in your IDE if you use it incorrectly. Additionally, you don't have to worry about nullability because NanoKt takes care of that.

```kotlin
// Examples for commonly used services
context.clipboardManager
context.layoutInflater
context.notificationManager

// All services available via systemServices
context.systemServices.midiManager
context.systemServices.printManager

// Nullability is respected. DevicePolicyManager is null in instant apps
context.systemServices.devicePolicyManager?.storageEncryptionStatus
```

### Reading configuration (e.g. night mode)
Solution without NanoKt
```kotlin
val nightMode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
    resources.configuration.isNightModeActive
} else {
    val flag = resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)
    flag == Configuration.UI_MODE_NIGHT_YES
}
```

Solution with NanoKt
```kotlin
// configuration is an extension on Context
val nightMode = configuration.isNightModeActiveCompat
```

Further examples of available configuration extensions:
```kotlin
val isTablet = configuration.isTablet()
val isLargeTablet = configuration.isTablet(requireXLarge = true)
val isCar = configuration.isCar
val isTelevision = configuration.isTelevision
val isWatch = configuration.isWatch
val isVrHeadset = configuration.isVrHeadset
val isLongScreen = configuration.isLongScreen
val isLandscape = configuration.isLandscape
val isPortrait = configuration.isPortrait
val isLtrLayout = configuration.isLtrLayout
val isRtlLayout = configuration.isRtlLayout
```

### Reading system settings (e.g. airplane mode)
Solution without NanoKt
```kotlin
val isAirplaneModeEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    try {
        Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON)
    } catch (_: Throwable) {
        0
    } == 1
} else {
    @Suppress("DEPRECATION")
    try {
        Settings.System.getInt(contentResolver, Settings.System.AIRPLANE_MODE_ON)
    } catch (_: Throwable) {
        0
    } == 1
}
```

Solution with NanoKt
```kotlin
val isAirplaneModeEnabled = settings.isAirplaneModeEnabled
```

Via the Context extension property "settings" NanoKt provides a variety of system settings to read and convenient functions to easily load additional settings as needed.

```kotlin
val isUSBDebuggingEnabled = settings.isAdbEnabled
val isBluetoothEnabled = settings.isBluetoothEnabled
val isDataRoamingEnabled = settings.isDataRoamingEnabled
val areDeveloperOptionsEnabled = settings.isDeveloperOptionsEnabled
val deviceName = settings.deviceName
val isMobileDataEnabled = settings.isMobileDataEnabled
val isWifiEnabled = settings.isWifiEnabled
val currentBrightness = settings.screenBrightness

settings.getGlobalIntOrNull(name = settingName)
settings.getSystemIntOrNull(name = settingName)
settings.getSecureIntOrNull(name = settingName)
...
```

### Starting activities
Tired of the same old routine, rewriting activity-starting code and worrying about forgetting essential flags like FLAG_ACTIVITY_NEW_TASK? With NanoKt, this problem is a thing of the past because the new task flag is added automatically when called from a non-activity context.

```kotlin
// start an activity
context.startActivity<MyActivity>()
// start an activity with options
context.startActivity<MyActivity>(options = startOptions)
// start an activity with a custom intent
startActivity<MyActivity>() {
    putExtra(SOME_BUNDLE_EXTRA, "extra")
    // Set flags with extension properties
    isNewTask = true
    isClearTop = true
}
// Only a flag necessary?
context.startActivity<MyActivity>(intentEditor = Intent::setClearTop)
```

### Starting the Play Store
When opening the Play Store (e.g. for reviews, paid version), many developers make the mistake of not specifying the package in the intent. As a result, the user may end up in another app (e.g. third-party store) where your app does not even exist. NanoKt makes it as easy as possible for you.

Solution without NanoKt
```kotlin
val intent = Intent(
    Intent.ACTION_VIEW,
    Uri.Builder()
        .scheme("https")
        .authority("play.google.com")
        .path("store/apps/details")
        .appendQueryParameter("id", "com.conena.logcat.reader")
        .build()
).setPackage("com.android.vending")
try {
    try {
        startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        startActivity(intent.setPackage(null))
    }
} catch (_: ActivityNotFoundException) {
    Toast.makeText(this, "Neither Google Play nor browser installed.", Toast.LENGTH_SHORT).show()
}
```

Solution with NanoKt
```kotlin
startPlayStoreForApp(packageName = "com.conena.logcat.reader").onFailure {
    toastShort("Neither Google Play nor browser installed.")
}
```
Other examples
```
// Start Play Store for the current app, ignore errors
startPlayStoreForApp()
// Start Play Store with referrer
startPlayStoreForApp(
    packageName = "com.conena.logcat.reader.ultra",
    referrer = "utm_source=nanoktIsEpic"
)
// Show all of your apps
startPlayStoreForDeveloper(developerName = "Conena")

```
NanoKt offers various functions that significantly simplify the opening of other activities. Another feature that becomes clear here is the use of the Kotlin Result API which allows for very elegant error handling.

### Starting other apps (e.g. settings, mail client, etc.)
I'm leaving out the non-NanoKt version for space reasons. However, the pain should be known to every Android developer.
```kotlin
// Show the details page of your app in the system settings
startAppSettings()
// Show the notification settings of your app in the system settings
startAppSettings(action = Settings.ACTION_APP_NOTIFICATION_SETTINGS)
// Start various system settings
startSettings(action = Settings.ACTION_WIFI_SETTINGS)
// You can always modify the intent
startSettings(action = Settings.ACTION_WIFI_SETTINGS) {
    putExtra(SOME_BUNDLE_EXTRA, "extra")
    // Set flags with extension properties
    isNewTask = true
}
// Open a website
startBrowser(url = "https://github.com/conena/nanokt").onFailure {
    toastShort("Go get a browser!")
}
// Share text
startSendActivityChooser(text = "12345679")
// More complex share operation
startSendActivityChooser(subject = "subject", text = "text", attachment = attachmentUri)
// Start mail client
startSendMailActivity(subject = "subject", body = "text", attachment = attachmentUri).onFailure {
    toastShort("No mail application installed")
}
```

### Conversion of complex units
```kotlin
val eightDp = 8.dpInPx
val eightDpAlternative = 8.dpToPx()
val eightDpAlternative2 = 8.toPx(TypedValue.COMPLEX_UNIT_DIP)
val eightSp = 8.spInPx
val eightSpAlternative = 8.spToPx()
val eightSpAlternative2 = 8.toPx(TypedValue.COMPLEX_UNIT_SP)
```

### Working with SharedPreferences
The standard solution for accessing the default SharedPreferences is often to include the AndroidX Preference Library, even though you don't need it for anything else. From now on, you can do it with NanoKt, as the default SharedPreferences are available via an Context extension property - without any additional dependency.
```kotlin
val sharedPreferences = context.defaultSharedPreferences
```
NanoKt also provides an elegant solution for quickly writing and reading single values.
```kotlin
// Write a single value and apply
sharedPreferences.put("key", "Value")
sharedPreferences.put("key", true)
sharedPreferences.put("key", 42)
// Remove a single key and apply
sharedPreferences.remove("key")
// Get a value or null if not present
sharedPreferences.getStringOrNull("key")
sharedPreferences.getBooleanOrNull("key")
sharedPreferences.getIntOrNull("key")
```
Other helpful functions for advanced use cases complete the spectrum of provided functions.
```kotlin
// Get a mutable string set that you can modify safely
sharedPreferences.getMutableStringSetOrNull("key")
// Invert a boolean value
sharedPreferences.invertBoolean("key")
// Invert a boolean value, set it to true if it does not yet exist.
sharedPreferences.invertBoolean(key = "key", defaultValue = true)
// The number of entries
sharedPreferences.size
// Delete all entries
sharedPreferences.clear()
```

### Accessing theme attributes
NanoKt provides simple functions to load colors, ColorStateLists, Integers, Strings, Booleans or TypedValue objects from the current theme.

Solution without NanoKt
```kotlin
var color: Int? = null
val typedValue = TypedValue()
if (theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)) {
    val isColorType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        typedValue.isColorType
    } else {
        typedValue.type >= TypedValue.TYPE_FIRST_COLOR_INT && typedValue.type <= TypedValue.TYPE_LAST_COLOR_INT
    }
    color = if (isColorType) {
        typedValue.data
    } else {
        try {
            ResourcesCompat.getColor(resources, typedValue.resourceId, theme)
        } catch (_: Throwable) {
            null
        }
    }
}
```

Solution with NanoKt
```kotlin
val color = theme.getColorOrNull(R.attr.colorPrimary)
// Alternatively with possible Resources.NotFoundException
val color = theme.getColor(R.attr.colorPrimary)
```

### Working with bundles
Even simply reading a parcelable from a bundle has become quite annoying since Android 13. The previous methods were marked as deprecated and the new methods [contain bugs that will not be fixed until Android 14][bundle bug]. NanoKt provides a solution to this problem as well.

Solution without NanoKt
```kotlin
val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    bundle.getParcelable("key", Uri::class.java)
} else {
    @Suppress("DEPRECATION")
    bundle.getParcelable("key") as? Uri?
}
```
Solution with NanoKt
```kotlin
val uri = bundle.getOrNull<Uri>("key")
```

### Working with services
A lot has changed in recent years, especially when working with foreground services. Some methods are deprecated or still too new to be used at all API levels. Wouldn't it be nice to have some handy functions that take the boiler plate code off your hands?

```kotlin
// Start a service
context.startService<MyService>()
// Start a foreground service
context.startForegroundServiceCompat(intent = serviceIntent)
context.startForegroundServiceCompat<MyService>()
// Bind a service
context.bindService<MyService>(connection = myConnection)

// Stop a foreground service
service.stopForegroundCompat(notificationBehavior = Service.STOP_FOREGROUND_REMOVE)
service.stopForegroundAndRemoveNotification()
service.stopForegroundAndDetachNotification()
```

### Working with views
There are various view extensions for frequently occurring tasks.

```kotlin
// Set visibility
view.setVisible()
View.setInvisible()
view.setGone()
// Visible based on condition or gone
view.setVisibleIf(condition = someCondition)
// Visible based on condition or invisible
view.setVisibleIf(condition = someCondition, otherwise = View.INVISIBLE)

// Set padding
view.setHorizontalPadding(value = 14.dpInPx)
view.setVerticalPadding(value = 14.dpInPx)

// Request focus
view.hasFocusCompat = true

// Handle input
val inputActive = view.isInputActive
view.setSoftInputVisibility(visible = true)

// Start drag and drop
view.startDragAndDropCompat()

// Set tooltips
view.setTooltipTextCompat(resId = R.string.my_tooltip)
view.setTooltipTextCompat(tooltipText = "tooltip")
```
If you are currently making your app compliant with the European Accessibility Act, you may have noticed that some methods related to accessibility features do not accept string resources. NanoKt fixes this.
```kotlin
view.setContentDescription(resId = R.string.desc)
view.setStateDescription(resId = R.string.desc)
view.setAccessibilityPaneTitle(resId = R.string.title)
```
Further examples for TextViews:
```kotlin
// Get/Set the text style
textView.textStyle = Typeface.NORMAL
// Clear the text
textView.clear()
// Clear an error
textView.clearError()
// Set an error with resources
textView.setError(resId = R.string.error, iconResId = R.drawable.error)
```

### Working with bitmaps
```kotlin
// Never forget recycling a bitmap again
someBitmap.use { bitmap ->
    // Do something with the bitmap
}
// The bitmap is recycled!

// Decode a bitmap
val bitmap = byteArray.decodeToBitmap()
// Decode a bitmap with options
val bitmap = byteArray.decodeToBitmap(
    offset = someOffset,
    length = someLength,
    options: BitmapFactory.Options? = someOptions
)
```

### Encoding/Decoding Base64
```kotlin
// Base64-encode
val base64Encoded = "NanoKt is cool!".encodeBase64()
// Base64-encode with options
val base64Encoded = "NanoKt is cool!".encodeBase64(
    charset = Charsets.UTF_8,
    urlSafe = false,
    wrap = false,
    padding = true,
    crlf = false
)
// Base64-decode
val base64Decoded = "TmFub0t0IGlzIGNvb2wh".decodeBase64()
// Base64-decode with options
val base64Decoded = "TmFub0t0IGlzIGNvb2wh".decodeBase64(
    charset = Charsets.UTF_8,
    urlSafe = false
)
```
### Debug logging
NanoKt offers handy logging functions that automatically create a tag based on the file and line number.

```kotlin
logDebug("I love NanoKt")
logWarn("I love NanoKt")
logError("I love NanoKt")
// Example Output:
// MainActivity.kt:17 D I love NanoKt
// MainActivity.kt:18 W I love NanoKt
// MainActivity.kt:19 E I love NanoKt
```

### Other examples
The examples show only a small selection of the available functions. Some more examples are listed here and you can explore a large number of other functions by browsing the repository.

```kotlin
// Create a DocumentFile from a single uri
val file = documentUri.toDocumentFileOrNull(context)
// Create a DocumentFile from a tree uri
val folder = documentUri.toDocumentTreeOrNull(context)

// Check if a string is valid android/linux file name
val valid = "someFile.txt".isValidFileName()

// SparseArray utils
val sparseArray = sparseArrayOf(0 to "First", 1 to "Second")
val asMap = sparseArray.toHashMap()
val asList = sparseArray.toArrayList()

// ... and many more
```
# Experimental parts of the library

Some parts of the library (not seen in the examples) are marked as experimental because they are likely to change or be removed in the next releases. To be able to use them without warning, you can opt-in in the module level build.gradle.
```groovy
kotlin.sourceSets.all {
    languageSettings.optIn("com.conena.nanokt.annotations.ExperimentalNanoKtApi")
}
```

# Versioning

The version numbering follows a structured approach based on the nature of changes introduced:

**Major Versions (1.0.0):**

- Published for significant new features or extensive breaking changes.
- Mandatory to review the changelog thoroughly before updating to ensure seamless transition.

**Minor Versions (1.1.0):**

- Released for new functionalities or minor breaking changes, typically easy to address or affecting a limited user base.
- This includes expected and necessary adjustments in Android development (e.g., higher Kotlin/AndroidX/SDK versions).
- It is recommended to consult the changelog prior to updating for a smoother experience.

**Fix Versions (1.0.1):**

- Deployed when addressing bug fixes.
- Upgrading to fix versions is straightforward and does not necessitate a detailed review of the changelog.

**Please note that alpha and beta versions operate under different rules. Due to their developmental nature, breaking changes of any magnitude can occur at any time. Therefore, it is imperative to always refer to the changelog before updating.**

# FAQ

### Is Kotlin multiplatform support planned?
Absolutely! While I can't provide a specific date, adding Kotlin multiplatform support is a goal I'm actively pursuing.

### Will there be a version for Kotlin coroutines?
Possibly. I haven't made a concrete decision yet, and development in this direction hasn't begun.

### Will there be a version for Jetpack Compose?
It's a possibility, but not something on my immediate to-do list. I'm regularly evaluating options, and if Jetpack Compose integration is in the cards, I'll share updates accordingly.

### Will there be a version for Library xy?
Most likely not. Maintaining quality and structure becomes challenging with an extensive array of modules for various independent libraries. As of now, I believe there are sufficient alternatives available.

# Contribution

Please feel free to open an issue or submit a pull request if you have any suggestions for improvement. I recommend that you contact me before making major changes, so that your work is not in vain. When submitting a pull request, please confirm that you wrote the code yourself, waive any copyright rights, and agree that the code will be placed under the original license of the library.

# License
```
Copyright (C) 2023 Fabian Andera

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[LRP link]:https://play.google.com/store/apps/details?id=com.conena.logcat.reader
[clipboard feedback]:https://developer.android.com/develop/ui/views/touch-and-input/copy-paste#Feedback
[bundle bug]:https://issuetracker.google.com/issues/240585930#comment6
