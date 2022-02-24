package io.github.vinccool96.ref

actual object WeakReferenceFactory {

    actual fun <T> createWeakRef(referent: T): WeakReference<T> {
        return JSCoreWeakRef(referent)
    }

    private class JSCoreWeakRef<T>(referent: T) : WeakReference<T> {

        private var ref: T? = referent

        override val value: T?
            get() = this.get()

        override fun get(): T? {
            return this.ref
        }

        override fun clear() {
            this.ref = null
        }

    }

}