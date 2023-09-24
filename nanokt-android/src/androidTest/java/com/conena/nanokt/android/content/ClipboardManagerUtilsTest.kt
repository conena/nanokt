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
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ClipboardManagerUtilsTest {

    private lateinit var instrumentation: Instrumentation
    private lateinit var context: Context
    private lateinit var clipboardManager: ClipboardManager

    @Before
    fun setUp() {
        instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.runOnMainSync {
            context = instrumentation.targetContext
            clipboardManager = context.clipboardManager
        }
    }

    // Don't run this test on versions later than P to avoid trouble with the input focus requirement.
    @SdkSuppress(maxSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun setPrimaryClip() {
        val value = "setPrimaryClip"
        clipboardManager.setPrimaryClip(text = value)
        assertThat(clipboardManager.primaryClip?.getItemAt(0)?.coerceToText(context)).isEqualTo(value)
        assertThat(clipboardManager.primaryClip?.description?.label).isNull()

        val value2 = "setPrimaryClip2"
        val label2 = "setPrimaryClipLabel2"
        clipboardManager.setPrimaryClip(text = value2, label = label2)
        assertThat(clipboardManager.primaryClip?.getItemAt(0)?.coerceToText(context)).isEqualTo(value2)
        assertThat(clipboardManager.primaryClip?.description?.label).isEqualTo(label2)
    }

    // Don't run this test on versions later than P to avoid trouble with the input focus requirement.
    @SdkSuppress(maxSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun getPrimaryClipDataItem() {
        val value = "getPrimaryClipDataItem"
        clipboardManager.setPrimaryClip(text = value)
        assertThat(clipboardManager.getPrimaryClipDataItem()?.coerceToText(context)).isEqualTo(value)
    }

    // Don't run this test on versions later than P to avoid trouble with the input focus requirement.
    @SdkSuppress(maxSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun isPrimaryClipContentPlainText() {
        // Plain text
        clipboardManager.setPrimaryClip(text = "Plain Text")
        assertThat(clipboardManager.isPrimaryClipContentPlainText()).isTrue()

        clipboardManager.setPrimaryClip(ClipData.newHtmlText("", "", ""))
        assertThat(clipboardManager.isPrimaryClipContentPlainText()).isFalse()
    }

    // Starting with Android P calls to this function are delegated to clearPrimaryClip
    @SdkSuppress(maxSdkVersion = Build.VERSION_CODES.P)
    @Test
    fun clearPrimaryClipCompat() {
        val value = "clearPrimaryClipCompat"
        clipboardManager.setPrimaryClip(text = value)
        assertThat(clipboardManager.getPrimaryClipDataItem()?.coerceToText(context)).isEqualTo(value)

        clipboardManager.clearPrimaryClipCompat()
        assertThat(clipboardManager.getPrimaryClipDataItem()?.coerceToText(context)).isEqualTo("")
    }

}