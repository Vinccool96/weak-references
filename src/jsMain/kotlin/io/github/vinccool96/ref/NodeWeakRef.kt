package io.github.vinccool96.ref

@JsModule("node-weak-ref")
@JsNonModule
internal external object NodeWeakRef {

    class WeakRef<T>

    fun <T> create(obj: T): WeakRef<T>

    fun <T> get(ref: WeakRef<T>): T?

}
