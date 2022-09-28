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
    for (i in 0 until backStackEntryCount) {
        if (getBackStackEntryAt(i).name == name) {
            return true
        }
    }
    return false
}

