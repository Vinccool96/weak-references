package io.github.vinccool96.ref

actual object WeakReferenceFactory {

    actual fun <T> createWeakRef(referent: T): WeakReference<T> {
        return JVMCoreWeakRef(referent)
    }

    private class JVMCoreWeakRef<T>(referent: T) : WeakReference<T> {

        private var ref: java.lang.ref.WeakReference<T> = java.lang.ref.WeakReference(referent)

        override val value: T?
            get() = this.get()

        override fun get(): T? {
            return this.ref.get()
        }

        override fun clear() {
            this.ref.clear()
        }

    }

}