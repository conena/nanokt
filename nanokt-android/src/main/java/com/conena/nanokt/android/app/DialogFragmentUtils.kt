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

@file:JvmName(name = "DialogFragmentUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.app

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Same as [DialogFragment.show] but null is used as tag.
 * @param manager The FragmentManager this fragment will be added to.
 */
inline fun DialogFragment.show(manager: FragmentManager) {
    show(manager, null)
}

/**
 * Same as [DialogFragment.show] but null is used as tag.
 * @param transaction The transaction to add the fragment to.
 */
inline fun DialogFragment.show(transaction: FragmentTransaction) {
    show(transaction, null)
}

/**
 * Same as [DialogFragment.show] but checks if the state is saved before showing the dialog.
 * If [DialogFragment.isStateSaved] is `true`, the dialog is not shown and `false` is returned.
 * @param manager The FragmentManager this fragment will be added to.
 * @param tag The optional tag for this fragment.
 * @return `true` if the dialog was shown. `false` otherwise.
 */
inline fun DialogFragment.showIfStateIsNotSaved(
    manager: FragmentManager,
    tag: String? = null
): Boolean {
    if (manager.isStateSaved) {
        return false
    }
    show(manager, tag)
    return true
}