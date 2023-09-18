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

package com.conena.nanokt.android.net


import androidx.core.net.toUri
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import java.net.URI
import java.net.URISyntaxException
import java.net.URL

class UriUtilsTest {

    private val referrerDecoded = "utm_source=test_source&utm_medium=test_medium&utm_term=test_term&utm_content=test_content&utm_campaign=test_name"
    @Suppress("SpellCheckingInspection")
    private val referrerEncoded = "utm_source%3Dtest_source%26utm_medium%3Dtest_medium%26utm_term%3Dtest_term%26utm_content%3Dtest_content%26utm_campaign%3Dtest_name"

    @Test
    fun getPlayStoreUriForApp_web() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control"
        val result = UriCompanion.getPlayStoreUriForApp(
            packageName = "com.conena.navigation.gesture.control",
            webLink = true
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getPlayStoreUriForApp_web_ref() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        val result = UriCompanion.getPlayStoreUriForApp(
            packageName = "com.conena.navigation.gesture.control",
            referrer = referrerDecoded,
            webLink = true
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getPlayStoreUriForApp_market() {
        val expected = "market://details?id=com.conena.navigation.gesture.control"
        val result = UriCompanion.getPlayStoreUriForApp(
            packageName = "com.conena.navigation.gesture.control"
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getPlayStoreUriForApp_market_ref() {
        val expected = "market://details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        val result = UriCompanion.getPlayStoreUriForApp(
            packageName = "com.conena.navigation.gesture.control",
            referrer = referrerDecoded
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getTestTrackWebsiteUriForApp() {
        val expected = "https://play.google.com/apps/testing/com.conena.navigation.gesture.control"
        val result = UriCompanion.getTestTrackWebsiteUriForApp(packageName = "com.conena.navigation.gesture.control")
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getPlayStoreUriForDeveloper_web() {
        val expected = "https://play.google.com/store/apps/developer?id=Conena"
        val result = UriCompanion.getPlayStoreUriForDeveloper(
            developerName = "Conena",
            webLink = true
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun getPlayStoreUriForDeveloper_market() {
        val expected = "market://developer?id=Conena"
        val result = UriCompanion.getPlayStoreUriForDeveloper(
            developerName = "Conena"
        )
        assertThat(result.toString()).isEqualTo(expected)
    }

    @Test
    fun toJavaUri() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        assertThat(expected.toUri().toJavaUri().toString()).isEqualTo(expected)
    }

    @Test
    fun toJavaUri_invalid() {
        val invalidUri = " /".toUri()
        assertThrows(URISyntaxException::class.java) {
            invalidUri.toJavaUri()
        }
    }

    @Test
    fun toJavaUriOrNull() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        assertThat(expected.toUri().toJavaUriOrNull().toString()).isEqualTo(expected)
    }

    @Test
    fun toJavaUriOrNull_invalid() {
        assertThat(" /".toUri().toJavaUriOrNull()).isNull()
    }

    @Test
    fun toJavaUrlOrNull() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        assertThat(expected.toUri().toJavaUrlOrNull().toString()).isEqualTo(expected)
    }

    @Test
    fun toJavaUrlOrNull_invalid() {
        assertThat(" /".toUri().toJavaUrlOrNull()).isNull()
    }

    @Test
    fun javaUriToAndroidUri() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        assertThat(URI(expected).toAndroidUri().toString()).isEqualTo(expected)
    }


    @Test
    fun javaUrlToAndroidUri() {
        val expected = "https://play.google.com/store/apps/details?id=com.conena.navigation.gesture.control&referrer=$referrerEncoded"
        assertThat(URL(expected).toAndroidUri().toString()).isEqualTo(expected)
    }

}