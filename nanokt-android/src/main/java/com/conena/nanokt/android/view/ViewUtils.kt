@file:JvmName(name = "ViewUtils")
@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt.android.view

import android.content.ClipData
import android.os.Build
import android.view.DragEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import androidx.annotation.Px
import com.conena.nanokt.android.content.inputMethodManager

/**
 * Set the view's visibility to [View.VISIBLE].
 */
inline fun View.setVisible() {
    visibility = View.VISIBLE
}

/**
 * Set the view's visibility to [View.INVISIBLE].
 */
inline fun View.setInvisible() {
    visibility = View.INVISIBLE
}

/**
 * Set the view's visibility to [View.GONE].
 */
inline fun View.setGone() {
    visibility = View.GONE
}

/**
 * Set the view's visibility to [View.VISIBLE] if the [condition] is `true`.
 * @param condition `true` to make the view visible. `false` to make the view [otherwise].
 * @param otherwise The visibility applied if [condition] is `false`. If `null` is passed the
 * visibility is not touched if [condition] is `false`.
 */
inline fun View.visibleIf(condition: Boolean, otherwise: Int? = View.GONE) {
    visibility = if (condition) View.VISIBLE else otherwise ?: return
}

/**
 * Set the top and bottom padding to [value].
 * @param T The type of the view.
 * @param value The padding value in pixel.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View> T.setHorizontalPadding(@Px value: Int): T {
    setPadding(value, paddingTop, value, paddingBottom)
    return this
}

/**
 * Set the left and right padding to [value].
 * @param T The type of the view.
 * @param value The padding value in pixel.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View> T.setVerticalPadding(@Px value: Int): T {
    setPadding(paddingLeft, value, paddingRight, value)
    return this
}

/**
 * `true` if this view has focus itself, or is the ancestor of the view that has focus.
 * Setting this to `true` does no guarantee that the view gets focus. See [View.requestFocus] for details.
 * @see View.hasFocus
 * @see View.requestFocus
 * @see View.clearFocus
 */
inline var View.focus: Boolean
    get() = hasFocus()
    set(value) { if (value) requestFocus() else clearFocus() }

/**
 * `true` if the current view is the currently active view for the input method.
 */
inline val View.inputActive get() = context.inputMethodManager.isActive(this)

/**
 * @param show `true` to show the soft input method for the current view. `false` to hide it if it
 * is currently active for the current view.
 */
inline fun View.showSoftInputMethod(show: Boolean = true) {
    if (show) {
        context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    } else if (context.inputMethodManager.isActive(this)) {
        context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}

/**
 * Calls [View.startDragAndDrop] starting from [Build.VERSION_CODES.N] and
 * [View.startDrag] on older versions.
 * @param data Optional [ClipData] with data to be transferred by the drag and drop operation.
 * @param shadowBuilder Used for building the drag shadow.
 * @param myLocalState Will later be available in [DragEvent.getLocalState].
 * @param flags See possible flags in [View.startDragAndDrop].
 * @return `true` if the method completes successfully, or `false` if it fails anywhere.
 */
inline fun View.startDragAndDropCompat(
    data: ClipData? = null,
    shadowBuilder: DragShadowBuilder = DragShadowBuilder(this),
    myLocalState: Any? = this,
    flags: Int = 0
): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        startDragAndDrop(data, shadowBuilder, myLocalState, flags)
    } else {
        @Suppress("DEPRECATION")
        startDrag(data, shadowBuilder, myLocalState, flags)
    }
}

/**
 * Sets the current instance as [View.OnClickListener] for the [view].
 * @param T The Type of the [View.OnClickListener].
 * @param view The view to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View.OnClickListener> T.addAsOnClickListenerFor(view: View): T {
    view.setOnClickListener(this)
    return this
}

/**
 * Sets the current instance as [View.OnClickListener] for all of the given [views].
 * @param T The Type of the [View.OnClickListener].
 * @param views The views to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View.OnClickListener> T.addAsOnClickListenerFor(vararg views: View): T {
    for (v in views) {
        v.setOnClickListener(this)
    }
    return this
}

/**
 * Sets the current instance as [View.OnLongClickListener] for the [view].
 * @param T The Type of the [View.OnLongClickListener].
 * @param view The view to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View.OnLongClickListener> T.addAsLongClickListenerFor(view: View): T {
    view.setOnLongClickListener(this)
    return this
}

/**
 * Sets the current instance as [View.OnLongClickListener] for all of the given [views].
 * @param T The Type of the [View.OnLongClickListener].
 * @param views The views to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : View.OnLongClickListener> T.addAsLongClickListenerFor(vararg views: View): T {
    for (v in views) {
        v.setOnLongClickListener(this)
    }
    return this
}

/**
 * Sets the current instance as [View.OnLongClickListener] for the [view].
 * @param T The Type of the [CompoundButton.OnCheckedChangeListener].
 * @param view The view to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : CompoundButton.OnCheckedChangeListener> T.addAsCheckedChangeListenerFor(
    view: CompoundButton
): T {
    view.setOnCheckedChangeListener(this)
    return this
}

/**
 * Sets the current instance as [CompoundButton.OnCheckedChangeListener] for all of the given [views].
 * @param T The Type of the [CompoundButton.OnCheckedChangeListener].
 * @param views The views to add the listener to.
 * @return The receiver object, for chaining multiple calls.
 */
inline fun <T : CompoundButton.OnCheckedChangeListener> T.addAsCheckedChangeListenerFor(
    vararg views: CompoundButton
): T {
    for (v in views) {
        v.setOnCheckedChangeListener(this)
    }
    return this
}