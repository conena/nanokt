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

package com.conena.nanokt.android.os

import android.content.ClipData
import android.content.ClipDescription
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.test.filters.SdkSuppress
import androidx.test.services.events.TimeStamp
import com.conena.nanokt.android.net.MimeType
import com.conena.nanokt.android.util.sparseArrayOf
import com.conena.nanokt.android.util.toHashMap
import com.conena.nanokt.annotations.ExperimentalNanoKtApi
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.io.Serializable

class BundleUtilsTest {

    @Test
    fun getOrNull_primitive_values() {
        val bundle = Bundle()
        bundle.putByte("ByteKey", 10)
        bundle.putBoolean("BooleanKey", true)
        bundle.putChar("CharKey", 'A')
        bundle.putDouble("DoubleKey", 1000.1)
        bundle.putFloat("FloatKey", 0.5f)
        bundle.putInt("IntKey", 12)
        bundle.putLong("LongKey", 9L)
        bundle.putShort("ShortKey", 100)

        assertThat(bundle.getOrNull<Byte>("ByteKey")).isEqualTo(10)
        assertThat(bundle.getOrNull<Boolean>("BooleanKey")).isTrue()
        assertThat(bundle.getOrNull<Char>("CharKey")).isEqualTo('A')
        assertThat(bundle.getOrNull<Double>("DoubleKey")).isEqualTo(1000.1)
        assertThat(bundle.getOrNull<Float>("FloatKey")).isEqualTo(0.5f)
        assertThat(bundle.getOrNull<Int>("IntKey")).isEqualTo(12)
        assertThat(bundle.getOrNull<Long>("LongKey")).isEqualTo(9L)
        assertThat(bundle.getOrNull<Short>("ShortKey")).isEqualTo(100)
    }

    @Test
    fun getOrNull_objects() {
        val bundle = Bundle()
        val bundleValue = Bundle().apply {
            putString("TestKey", "TestValue")
        }
        val charSequence: CharSequence = "TestCharSequence"
        val parcelable: Parcelable = Uri.parse("https://conena.com")
        val serializable: Serializable = arrayListOf("Test1", "Test2")
        val string = "TestString"

        bundle.putBundle("BundleKey", bundleValue)
        bundle.putCharSequence("CharSequenceKey", charSequence)
        bundle.putParcelable("ParcelableKey", parcelable)
        bundle.putSerializable("SerializableKey", serializable)
        bundle.putString("StringKey", string)

        assertThat(bundle.getOrNull<Bundle>("BundleKey")).isEqualTo(bundleValue)
        assertThat(bundle.getOrNull<CharSequence>("CharSequenceKey")).isEqualTo(charSequence)
        assertThat(bundle.getOrNull<Parcelable>("ParcelableKey")).isEqualTo(parcelable)
        assertThat(bundle.getOrNull<Serializable>("SerializableKey")).isEqualTo(serializable)
        assertThat(bundle.getOrNull<String>("StringKey")).isEqualTo(string)
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    fun getOrNull_objects_api_18() {
        val bundle = Bundle()
        val binder: IBinder = Binder()
        bundle.putBinder("BinderKey", binder)

        assertThat(bundle.getOrNull<IBinder>("BinderKey")).isEqualTo(binder)
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun getOrNull_objects_api_21() {
        val bundle = Bundle()
        val size = Size(10, 15)
        val sizeF = SizeF(5f, 6f)
        bundle.putSize("SizeKey", size)
        bundle.putSizeF("SizeFKey", sizeF)
        assertThat(bundle.getOrNull<Size>("SizeKey")).isEqualTo(size)
        assertThat(bundle.getOrNull<SizeF>("SizeFKey")).isEqualTo(sizeF)
    }

    @Test
    fun getOrNull_arrays() {
        val bundle = Bundle()
        val byteArray: ByteArray = byteArrayOf(-10, 5, 8)
        val booleanArray: BooleanArray = booleanArrayOf(false, false, true, false)
        val charArray: CharArray = charArrayOf('A', 'B', 'C', 'D')
        val charSequenceArray: Array<CharSequence> = arrayOf("ABC", "BCD", "CDE")
        val doubleArray: DoubleArray = doubleArrayOf(-1.0, 0.0, 1.2, 5.0)
        val floatArray: FloatArray = floatArrayOf(-1.0f, 0.0f, 1.2f, 5.0f)
        val intArray: IntArray = intArrayOf(-1, 0, 1, 5)
        val longArray: LongArray = longArrayOf(-1L, 0L, 1L, 5L)
        val shortArray: ShortArray = shortArrayOf(-1, 0, 1, 5)
        val stringArray: Array<String> = arrayOf("ABC", "BCD", "CDE")
        val parcelableArray: Array<Parcelable> = arrayOf(
            ClipData(ClipDescription("Test", arrayOf(MimeType.PLAIN_TEXT)), ClipData.Item("Test")),
            Uri.parse("https://conena.com"),
            TimeStamp(1000L, 100)
        )
        val sparseParcelableArray: SparseArray<Parcelable> = sparseArrayOf(
            ClipData(ClipDescription("Test", arrayOf(MimeType.PLAIN_TEXT)), ClipData.Item("Test")),
            Uri.parse("https://conena.com"),
            TimeStamp(1000L, 100)
        )

        bundle.putByteArray("ByteArrayKey", byteArray)
        bundle.putBooleanArray("BooleanArrayKey", booleanArray)
        bundle.putCharArray("CharArrayKey", charArray)
        bundle.putCharSequenceArray("CharSequenceArrayKey", charSequenceArray)
        bundle.putDoubleArray("DoubleArrayKey", doubleArray)
        bundle.putFloatArray("FloatArrayKey", floatArray)
        bundle.putIntArray("IntArrayKey", intArray)
        bundle.putLongArray("LongArrayKey", longArray)
        bundle.putShortArray("ShortArrayKey", shortArray)
        bundle.putStringArray("StringArrayKey", stringArray)
        bundle.putParcelableArray("ParcelableArrayKey", parcelableArray)
        bundle.putSparseParcelableArray("SparseParcelableArrayKey", sparseParcelableArray)

        assertThat(bundle.getOrNull<ByteArray>("ByteArrayKey")?.asList()).containsExactlyElementsIn(byteArray.asList()).inOrder()
        assertThat(bundle.getOrNull<BooleanArray>("BooleanArrayKey")?.asList()).containsExactlyElementsIn(booleanArray.asList()).inOrder()
        assertThat(bundle.getOrNull<CharArray>("CharArrayKey")?.asList()).containsExactlyElementsIn(charArray.asList()).inOrder()
        assertThat(bundle.getOrNull<Array<out CharSequence>>("CharSequenceArrayKey")?.asList()).containsExactlyElementsIn(charSequenceArray.asList()).inOrder()
        assertThat(bundle.getOrNull<DoubleArray>("DoubleArrayKey")?.asList()).containsExactlyElementsIn(doubleArray.asList()).inOrder()
        assertThat(bundle.getOrNull<FloatArray>("FloatArrayKey")?.asList()).containsExactlyElementsIn(floatArray.asList()).inOrder()
        assertThat(bundle.getOrNull<IntArray>("IntArrayKey")?.asList()).containsExactlyElementsIn(intArray.asList()).inOrder()
        assertThat(bundle.getOrNull<ShortArray>("ShortArrayKey")?.asList()).containsExactlyElementsIn(shortArray.asList()).inOrder()
        assertThat(bundle.getOrNull<Array<String>>("StringArrayKey")?.asList()).containsExactlyElementsIn(stringArray.asList()).inOrder()
        assertThat(bundle.getOrNull<Array<Parcelable>>("ParcelableArrayKey")?.asList()).containsExactlyElementsIn(parcelableArray.asList()).inOrder()
        assertThat(bundle.getOrNull<SparseArray<Parcelable>>("SparseParcelableArrayKey")?.toHashMap()).containsExactlyEntriesIn(sparseParcelableArray.toHashMap()).inOrder()
    }

    @Test
    fun getOrNull_null_values() {
        val bundle = Bundle()

        bundle.putBundle("BundleKey", null)
        bundle.putCharSequence("CharSequenceKey", null)
        bundle.putParcelable("ParcelableKey", null)
        bundle.putSerializable("SerializableKey", null)
        bundle.putString("StringKey", null)

        bundle.putByteArray("ByteArrayKey", null)
        bundle.putBooleanArray("BooleanArrayKey", null)
        bundle.putCharArray("CharArrayKey", null)
        bundle.putCharSequenceArray("CharSequenceArrayKey", null)
        bundle.putDoubleArray("DoubleArrayKey", null)
        bundle.putFloatArray("FloatArrayKey", null)
        bundle.putIntArray("IntArrayKey", null)
        bundle.putLongArray("LongArrayKey", null)
        bundle.putShortArray("ShortArrayKey", null)
        bundle.putStringArray("StringArrayKey", null)

        bundle.putParcelableArray("ParcelableArrayKey", null)
        bundle.putSparseParcelableArray("SparseParcelableArrayKey", null)

        bundle.putCharSequenceArrayList("CharSequenceArrayListKey", null)
        bundle.putIntegerArrayList("IntegerArrayListKey", null)
        bundle.putParcelableArrayList("ParcelableArrayListKey", null)
        bundle.putStringArrayList("StringArrayListKey", null)


        assertThat(bundle.getOrNull<Bundle>("BundleKey")).isNull()
        assertThat(bundle.getOrNull<CharSequence>("CharSequenceKey")).isNull()
        assertThat(bundle.getOrNull<Serializable>("SerializableKey")).isNull()
        assertThat(bundle.getOrNull<String>("StringKey")).isNull()

        assertThat(bundle.getOrNull<ByteArray>("ByteArrayKey")).isNull()
        assertThat(bundle.getOrNull<BooleanArray>("BooleanArrayKey")).isNull()
        assertThat(bundle.getOrNull<CharArray>("CharArrayKey")).isNull()
        assertThat(bundle.getOrNull<Array<out CharSequence>>("CharSequenceArrayKey")).isNull()
        assertThat(bundle.getOrNull<DoubleArray>("DoubleArrayKey")).isNull()
        assertThat(bundle.getOrNull<FloatArray>("FloatArrayKey")).isNull()
        assertThat(bundle.getOrNull<IntArray>("IntArrayKey")).isNull()
        assertThat(bundle.getOrNull<LongArray>("LongArrayKey")).isNull()
        assertThat(bundle.getOrNull<ShortArray>("ShortArrayKey")).isNull()
        assertThat(bundle.getOrNull<Array<String>>("StringArrayKey")).isNull()

        assertThat(bundle.getOrNull<Array<out Parcelable>>("ParcelableArrayKey")).isNull()
        assertThat(bundle.getOrNull<SparseArray<out Parcelable>>("SparseParcelableArrayKey")).isNull()

        assertThat(bundle.getOrNull<ArrayList<out CharSequence>>("CharSequenceArrayListKey")).isNull()
        assertThat(bundle.getOrNull<ArrayList<Int>>("IntegerArrayListKey")).isNull()
        assertThat(bundle.getOrNull<ArrayList<out Parcelable>>("ParcelableArrayListKey")).isNull()
        assertThat(bundle.getOrNull<ArrayList<String>>("StringArrayListKey")).isNull()

    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Test
    fun getOrNull_null_values_api_18() {
        val bundle = Bundle()
        bundle.putBinder("BinderKey", null)
        assertThat(bundle.getOrNull<IBinder>("BinderKey")).isNull()
    }

    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    @Test
    fun getOrNull_null_values_api_21() {
        val bundle = Bundle()
        bundle.putSize("SizeKey", null)
        bundle.putSizeF("SizeFKey", null)
        assertThat(bundle.getOrNull<Size>("SizeKey")).isNull()
        assertThat(bundle.getOrNull<SizeF>("SizeFKey")).isNull()
    }

    @Test
    fun getOrNull_non_existing_key() {
        val bundle = Bundle()
        assertThat(bundle.getOrNull<Any>("Key1")).isNull()
        assertThat(bundle.getOrNull<String>("Key2")).isNull()
        assertThat(bundle.getOrNull<Uri>("Key3")).isNull()
    }

    @OptIn(ExperimentalNanoKtApi::class)
    @Test
    fun get() {
        var bundle: Bundle? = null
        val uri = Uri.parse("https://conena.com")
        val uri2 = Uri.parse("https://www.conena.com")
        assertThat(uri).isNotEqualTo(uri2)

        // Bundle is null
        assertThat(bundle.get("Key0", uri2)).isEqualTo(uri2)

        bundle = Bundle()

        // Normal get
        bundle.putParcelable("Key1", uri)
        assertThat(bundle.get("Key1", uri2)).isEqualTo(uri)

        // Key does not exist
        assertThat(bundle.get("Key2", uri2)).isEqualTo(uri2)

        // Value is null
        bundle.putParcelable("Key3", null)
        assertThat(bundle.get("Key3", uri2)).isEqualTo(uri2)

        // Value has wrong type
        bundle.putParcelable("Key4", TimeStamp(100, 100))
        assertThat(bundle.get("Key4", uri2)).isEqualTo(uri2)

    }

}