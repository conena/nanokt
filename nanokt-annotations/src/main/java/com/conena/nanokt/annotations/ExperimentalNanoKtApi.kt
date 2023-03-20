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

package com.conena.nanokt.annotations

/**
 * The annotation marks parts of the library that are likely to be changed or even removed in the future.
 *
 * Any usage must be accepted either by annotating that usage with the [OptIn] annotation,
 * e.g. `@OptIn(ExperimentalNanoKtApi::class)`, or by using the compiler argument
 * `com.conena.nanokt.annotations.ExperimentalNanoKtApi`.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "This part of the library will probably be changed or even removed in the future." +
            " Any usage must be accepted either by annotating that usage with the OptIn annotation,\n" +
            " e.g. `@OptIn(ExperimentalNanoKtApi::class)`, or by using the compiler argument\n" +
            " `-opt-in=com.conena.nanokt.annotations.ExperimentalNanoKtApi`"
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.TYPEALIAS
)
@MustBeDocumented
annotation class ExperimentalNanoKtApi