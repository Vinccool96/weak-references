package io.github.vinccool96.ref

expect object WeakReferenceFactory {

    fun <T> createWeakRef(referent: T): WeakReference<T>

}