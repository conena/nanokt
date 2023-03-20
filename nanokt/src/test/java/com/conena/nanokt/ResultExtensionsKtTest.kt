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

package com.conena.nanokt

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class ResultExtensionsKtTest {

    @Test
    fun mapSuccess() {
        val successValue = "15"
        val failureValue = UninitializedPropertyAccessException()
        val success = Result.success(value = successValue)
        val failure = Result.failure<String>(exception = failureValue)
        val transformedSuccess = success.mapSuccess(String::toInt)
        val transformedFailure = failure.mapSuccess(String::toInt)
        assertTrue(transformedSuccess.isSuccess)
        assertTrue(transformedFailure.isFailure)
        assertEquals(successValue.toInt(), transformedSuccess.getOrNull())
        assertSame(failureValue, transformedFailure.exceptionOrNull())
        assertNotEquals(success, transformedSuccess)
        assertEquals(failure, transformedFailure)
    }

    @Test
    fun mapFailure() {
        val successValue = "15"
        val failureValue = UninitializedPropertyAccessException()
        val success = Result.success(value = successValue)
        val failure = Result.failure<String>(exception = failureValue)
        val transformedSuccess = success.mapFailure(::UnsupportedOperationException)
        val transformedFailure = failure.mapFailure(::UnsupportedOperationException)
        assertTrue(transformedSuccess.isSuccess)
        assertTrue(transformedFailure.isFailure)
        assertSame(successValue, transformedSuccess.getOrNull())
        assertSame(failureValue, transformedFailure.exceptionOrNull()?.cause)
        assertTrue(transformedFailure.exceptionOrNull() is UnsupportedOperationException)
        assertEquals(success, transformedSuccess)
    }

    @Test
    operator fun component1() {
        val successValue = "15"
        val success = Result.success(value = successValue)
        assertSame(successValue, success.component1())
        assertNull(success.component2())
    }

    @Test
    operator fun component2() {
        val failureValue = UninitializedPropertyAccessException()
        val failure = Result.failure<String>(exception = failureValue)
        assertNull(failure.component1())
        assertSame(failureValue, failure.component2())
    }

}