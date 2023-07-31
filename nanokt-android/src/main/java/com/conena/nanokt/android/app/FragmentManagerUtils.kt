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

@file:JvmName(name = "FragmentManagerUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.app

import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Same as [FragmentManager.findFragmentById] but returns `null` if a fragment was found but is not of
 * type [T].
 * @param T The top of the fragment to find.
 * @param id Either the id from XML or the container id when added in a transaction.
 */
@CheckResult
inline fun <reified T : Fragment> FragmentManager.findFragment(id: Int) = findFragmentById(id) as? T

/**
 * Same as [FragmentManager.findFragmentByTag] but returns `null` if a fragment was found but is not of
 * type [T].
 * @param T The top of the fragment to find.
 * @param tag The tag of the fragment.
 */
@CheckResult
inline fun <reified T : Fragment> FragmentManager.findFragment(tag: String) = findFragmentByTag(tag) as? T

/**
 * @param name The name that was supplied to [FragmentTransaction.addToBackStack]
 * @return `true` if the backstack contains an entry with the given [name].
 */
@CheckResult
inline fun FragmentManager.backStackContains(name: String): Boolean {
    for (i in 0 ..< backStackEntryCount) {
        if (getBackStackEntryAt(i).name == name) {
            return true
        }
    }
    return false
}

