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

package com.conena.nanokt.android.util

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows

import org.junit.Test
import java.nio.charset.Charset

class Base64UtilsTest {

    private data class TestParameters(
        val decoded: String,
        val encoded: String,
        val charset: Charset = Charsets.UTF_8,
        val urlSafe: Boolean = false,
        val wrap: Boolean = false,
        val padding: Boolean = true,
        val crlf: Boolean = false
    )

    @Suppress("SpellCheckingInspection")
    private val testData = arrayOf(
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw==",
            charset = Charsets.UTF_8
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw",
            charset = Charsets.UTF_8,
            padding = false
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw==\n",
            charset = Charsets.UTF_8,
            wrap = true
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw==\r\n",
            charset = Charsets.UTF_8,
            wrap = true,
            crlf = true
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw==",
            charset = Charsets.US_ASCII
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VGVzdFN0cmluZw==",
            charset = Charsets.ISO_8859_1
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VABlAHMAdABTAHQAcgBpAG4AZwA=",
            charset = Charsets.UTF_16LE
        ),
        TestParameters(
            decoded = "TestString",
            encoded = "VAAAAGUAAABzAAAAdAAAAFMAAAB0AAAAcgAAAGkAAABuAAAAZwAAAA==",
            charset = Charsets.UTF_32LE
        ),
        TestParameters(
            decoded = "úûüýþÿ",
            encoded = "w7rDu8O8w73DvsO/",
            charset = Charsets.UTF_8
        ),
        TestParameters(
            decoded = "úûüýþÿ",
            encoded = "+gD7APwA/QD+AP8A",
            charset = Charsets.UTF_16LE
        ),
        TestParameters(
            decoded = "úûüýþÿ",
            encoded = "+gAAAPsAAAD8AAAA/QAAAP4AAAD/AAAA",
            charset = Charsets.UTF_32LE
        ),
        TestParameters(
            decoded = "\uD83D\uDE00\uD83D\uDE05",
            encoded = "8J+YgPCfmIU=",
            charset = Charsets.UTF_8
        ),
        TestParameters(
            decoded = "\uD83D\uDE00\uD83D\uDE05",
            encoded = "PdgA3j3YBd4=",
            charset = Charsets.UTF_16LE
        ),
        TestParameters(
            decoded = "\uD83D\uDE00\uD83D\uDE05",
            encoded = "APYBAAX2AQA=",
            charset = Charsets.UTF_32LE
        ),
        TestParameters(
            decoded = "�ýþÿ",
            encoded = "77+9w73DvsO/",
            charset = Charsets.UTF_8
        ),
        TestParameters(
            decoded = "�ýþÿ",
            encoded = "77-9w73DvsO_",
            charset = Charsets.UTF_8,
            urlSafe = true
        )
    )



    @Test
    fun encodeBase64() {
        for (testDataEntry in testData) {
            println(testDataEntry)
            val result = testDataEntry.decoded.encodeBase64(
                charset = testDataEntry.charset,
                urlSafe = testDataEntry.urlSafe,
                wrap = testDataEntry.wrap,
                padding = testDataEntry.padding,
                crlf = testDataEntry.crlf
            )
            assertThat(result).isEqualTo(testDataEntry.encoded)
        }
    }

    @Test
    fun decodeBase64() {
        for (testDataEntry in testData) {
            println(testDataEntry)
            val result = testDataEntry.encoded.decodeBase64(
                charset = testDataEntry.charset,
                urlSafe = testDataEntry.urlSafe,
            )
            assertThat(result).isEqualTo(testDataEntry.decoded)
        }
    }

    @Test
    fun decodeBase64_invalid_input() {
        // Invalid padding
        assertThrows(IllegalArgumentException::class.java) {
            "VGVzdFN0cmluZw===".decodeBase64()
        }

        // Invalid characters
        assertThat("äöü�VGVzdFN0cmluZw==".decodeBase64()).isEqualTo("TestString")
    }

}