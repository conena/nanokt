package com.conena.nanokt.android

/**
 * The annotation marks parts of the library that are likely to be changed or even removed in the future.
 *
 * Any usage must be accepted either by annotating that usage with the [OptIn] annotation,
 * e.g. `@OptIn(ExperimentalNanoKtAndroidApi::class)`, or by using the compiler argument
 * `-opt-in=com.conena.nanokt.android.ExperimentalNanoKtAndroidApi`.
 */
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "This part of the library will probably be changed or even removed in the future." +
            " Any usage must be accepted either by annotating that usage with the OptIn annotation,\n" +
            " e.g. `@OptIn(ExperimentalNanoKtAndroidApi::class)`, or by using the compiler argument\n" +
            " `-opt-in=com.conena.nanokt.android.ExperimentalNanoKtAndroidApi`"
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
annotation class ExperimentalNanoKtAndroidApi
