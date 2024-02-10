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

package com.conena.nanokt.jvm.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class UUIDExtensionsKtTest {

    private val validUUIDs = listOf(
        "00000000-0000-0000-0000-000000000000",
        "f0000000-0000-0000-0000-000000000000",
        "89827354-424a-3a7d-b5cd-b61c1b419e5f",
        "2bb5f02a-b71a-37de-b651-2ab2ce78d377",
        "325546be-f678-3ff0-97e0-d4ebe84e2f3a",
        "244a87a9-2d8d-4a71-9f96-1596f6eed3a4",
        "e27509d5-83ad-4a34-91d9-65218493788d",
        "4f0e78c0-8529-44da-895f-7e4f1b33f4e2",
        "9d2bc936-2190-5b79-b0d8-d6fde91cc75f",
        "6801202e-693c-55aa-85cd-521e67b2ee6c",
        "c6ae3af6-357b-53fa-9d93-2be29322d12c",
    )

    private val invalidUUIDs = listOf(
        "",
        "1",
        "0-1",
        "0-0-0-0-0",
        "g0000000-0000-0000-0000-000000000000",
    )

    @Test
    fun `isUUID - valid UUIDs`() {
        validUUIDs.forEach { uuid ->
            assertTrue(uuid.isUUID())
        }
    }

    @Test
    fun `isUUID - invalid UUIDs`() {
        invalidUUIDs.forEach { uuid ->
            assertFalse(uuid.isUUID())
        }
    }

    @Test
    fun `toUUID - valid UUIDs`() {
        validUUIDs.forEach { uuid ->
            assertEquals(
                uuid,
                uuid.toUUID().toString()
            )
        }
    }

    @Test
    fun `toUUID - invalid UUIDs`() {
        invalidUUIDs.forEach { uuid ->
            assertThrows(IllegalArgumentException::class.java) {
                uuid.toUUID()
            }
        }
    }

    @Test
    fun `toUUIDorNull - valid UUIDs`() {
        validUUIDs.forEach { uuid ->
            assertEquals(
                uuid,
                uuid.toUUIDorNull().toString()
            )
        }
    }

    @Test
    fun `toUUIDorNull - invalid UUIDs`() {
        invalidUUIDs.forEach { uuid ->
            assertNull(uuid.toUUIDorNull())
        }
    }

}