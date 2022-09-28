@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.conena.nanokt

/**
 * @return `null` if the collection is empty or `null`. Otherwise the collection is returned.
 */
inline fun <T> Collection<T>?.emptyAsNull(): Collection<T>? = if (isNullOrEmpty()) null else this

/**
 * @return A list of all entries in [elements] that are of type [T].
 */
inline fun <reified T : Any> listOfInstanceOf(vararg elements: Any?): List<T> {
    return elements.filterIsInstance(T::class.java)
}

/**
 * @return A list containing [element] it it is of type [T]. Otherwise an empty list is returned.
 */
inline fun <reified T : Any> listOfInstanceOf(element: Any?): List<T> {
    return if (element is T) listOf(element) else emptyList()
}

/**
 * Same as [MutableCollection.removeIf] but does also work below Java 8.
 * Remove entries from the collection which match the [predicate].
 * @param predicate Predicate that returns `true` for entries that should be removed.
 * @return `true` if at least one entry was removed.
 */
inline fun <T> MutableCollection<T>.removeIfCompat(predicate: (T) -> Boolean): Boolean {
    var removed = false
    val each = iterator()
    while (each.hasNext()) {
        if (predicate.invoke(each.next())) {
            each.remove()
            removed = true
        }
    }
    return removed
}

/**
 * Remove or add a value based on the value of [add].
 * @param add `true` to add the value, `false` to remove the value.
 * @param value The value.
 * @return `true` if the collection was changed, `false` if the collection is unchanged.
 */
inline fun <T> MutableCollection<T>.addOrRemove(add: Boolean, value: T): Boolean {
    return if (add) add(value) else remove(value)
}
