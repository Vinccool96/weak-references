package io.github.vinccool96.ref

/**
 * Used to get weak references.
 *
 * Note: Kotlin/JS doesn't have weak references for now, so [get] doesn't return null, unless [T] is nullable.
 *
 * @param T the type of the weak reference
 */
interface WeakReference<T> {

    /**
     * Returns either reference to an object or null, if it was collected.
     *
     * @see get
     */
    val value: T?

    /**
     * Returns this reference object's referent. If this reference object has been cleared, either by the program or by
     * the garbage collector, then this method returns `null`.
     *
     * @return The object to which this reference refers, or `null` if this reference object has been cleared
     */
    fun get(): T?

    /**
     * Clears reference to an object.
     */
    fun clear()

}