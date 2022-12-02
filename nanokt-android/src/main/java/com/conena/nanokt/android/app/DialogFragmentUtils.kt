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
    if (isStateSaved) {
        return false
    }
    show(manager, tag)
    return true
}

/**
 * Same as [DialogFragment.show] but checks if the state is saved before showing the dialog.
 * If [DialogFragment.isStateSaved] is `true`, the dialog is not shown and `false` is returned.
 * @param transaction The transaction to add the fragment to.
 * @param tag The optional tag for this fragment.
 * @return `true` if the dialog was shown. `false` otherwise.
 */
inline fun DialogFragment.showIfStateIsNotSaved(
    transaction: FragmentTransaction,
    tag: String? = null
): Boolean {
    if (isStateSaved) {
        return false
    }
    show(transaction, tag)
    return true
}