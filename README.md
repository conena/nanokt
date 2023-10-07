# NanoKt

Introducing NanoKt – an elegant and lightweight solution for streamlined Android development that offers an extensive collection of up-to-date extension functions covering many areas of the Android framework and the Java and Kotlin standard libraries. NanoKt will make your code more elegant, efficient and save you time in the process. Moreover, this is achieved without compromising performance, as almost all provided functions and properties are inlined at compile time.

The library is also well-documented with KDoc and includes meaningful annotations in appropriate spots, making it very user-friendly and easy to get started with.

API breaking changes can still happen within the beta phase. From the first stable version on, NanoKt will follow the principle of semantic versioning. I plan to actively develop the library and gradually integrate it into all my apps. I'm already using it for several months in production for my app [Logcat Reader Professional][LRP link].

The library is divided into modules for pure Kotlin, Kotlin-JVM, and Kotlin-Android, making it compatible with a wide range of projects. I may even release a compatible version for Kotlin Multiplatform at a later date.

# Getting started

To start using NanoKt in your project, add the appropriate dependencies to your build.gradle file.

```groovy
repositories {
    mavenCentral()
}

dependencies {
    // Extensions for Android
    implementation 'com.conena.nanokt:nanokt-android:1.0.0-beta06'
    // Pure Kotlin Extensions
    implementation 'com.conena.nanokt:nanokt:1.0.0-beta06'
    // Extensions for the Java standard library
    implementation 'com.conena.nanokt:nanokt-jvm:1.0.0-beta06'
    // Extensions for threads in Android. Not needed if you use coroutines
    implementation 'com.conena.nanokt:nanokt-android-threads:1.0.0-beta06'
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

### System services
You may wonder where the clipboard manager comes from in the first example. NanoKt makes all available services available as context extension properties. Frequently used services are available directly in the context, less frequently used services have to be called via a systemServices extension. Additionally, you don't have to worry about nullability because NanoKt takes care of that.

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

### Check for Night mode
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
val nightMode = resources.configuration.isNightModeActiveCompat
```

### Check if airplane mode is enabled
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
val isAirplaneModeEnabled = settings.airplaneModeEnabled
```
Via the extension property "settings" NanoKt provides a variety of system settings to read and convenient methods to easily load additional settings as needed.

### Open another app in the Playstore
You want to display the Playstore page of another app (e.g. paid version, plugin, etc.). If the Playstore cannot be opened, the Playstore should be displayed in the browser.

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
showAppInPlayStore(packageName = "com.conena.logcat.reader").onFailure {
    toastShort("Neither Google Play nor browser installed.")
}
```
NanoKt offers various methods that significantly simplify the opening of other activities. Another feature that becomes clear here is the use of the Kotlin Result API which allows for very elegant error handling. Interesting to note that the above code would not work if executed in a context other than an Activity. The NanoKt example still works because the Intent.FLAG_ACTIVITY_NEW_TASK flag is automatically set if the method is not called from an Activity. This applies to most NanoKt methods that start activities.

### Other activity-related examples
I'm leaving out the non-NanoKt version for space reasons. However, the pain should be known to every Android developer.
```kotlin
// Start an activity of your app
startActivity<MainActivity>()
// Show the details page of your app in the system settings
startAppSettings()
// Show the notification settings of your app in the system settings
startAppSettings(action = Settings.ACTION_APP_NOTIFICATION_SETTINGS)
// Direct a user to the beta test of your app
startPlayStoreForTestTrack(packageName = "con.conena.navigation.gesture.control")
// Open a website
startBrowser(url = "https://github.com/conena/nanokt").onFailure {
    toastShort("Go get a browser!")
}
// Share text
startSendActivityChooser(text = "12345679")
// More complex share operation
startSendActivityChooser(subject = "subject", text = "text", attachment = attachmentUri)
// Share with mail
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

### Read and write with SharedPreferences
```kotlin
val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
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
// Get a mutable string set that you can modify safely
sharedPreferences.getMutableStringSetOrNull("key")
// Invert a boolean value
sharedPreferences.invertBoolean("key")
// The number of entries
sharedPreferences.size
```

### Load resources from the current theme
NanoKt provides simple methods to load colors, ColorStateLists, Integers, Strings, Booleans or TypedValue objects from the current theme.

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
### Other examples
The examples shown were only a small selection of the available methods. Some more examples are listed here and you can explore a large number of other methods by browsing the repository.

```kotlin
// Create a DocumentFile from a single uri
val file = documentUri.toDocumentFileOrNull(context)
// Create a DocumentFile from a tree uri
val folder = documentUri.toDocumentTreeOrNull(context)

// Check if a string is valid android/linux file name
val valid = "someFile.txt".isValidFileName()

// Base64-encode strings
val base64Encoded = "NanoKt is cool!".encodeBase64()
// Base64-decode strings
val base64Decoded = "TmFub0t0IGlzIGNvb2wh".decodeBase64()

// SparseArray utils
val sparseArray = sparseArrayOf(0 to "First", 1 to "Second")
val asMap = sparseArray.toHashMap()
val asList = sparseArray.toArrayList()

// View utils
view.setVisibleIf(condition = true)
view.setVisibleIf(condition = true, otherwise = View.INVISIBLE)
view.startDragAndDropCompat()

// ... and many more
```
# Experimental parts of the library

Some parts of the library (not seen in the examples) are marked as experimental because they are likely to change or be removed in the next releases. To be able to use them without warning, you can opt-in in the module level build.gradle.
```groovy
kotlin.sourceSets.all {
    languageSettings.optIn("com.conena.nanokt.annotations.ExperimentalNanoKtApi")
}
```

# Contribution

Please feel free to open an issue or submit a pull request if you have any suggestions for improvement. When submitting a pull request, please confirm that you wrote the code yourself, waive any copyright rights, and agree that the code will be placed under the original license of the library.

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
