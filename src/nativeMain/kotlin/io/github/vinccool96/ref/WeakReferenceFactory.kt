package io.github.vinccool96.ref

actual object WeakReferenceFactory {

    actual fun <T> createWeakRef(referent: T): WeakReference<T> {
        return NativeCoreWeakRef(referent)
    }

    class NativeCoreWeakRef<T>(referent: T) : WeakReference<T> {

        private val ref = kotlin.native.ref.WeakReference(referent as Any)

        override val value: T?
            get() = this.get()

        @Suppress("UNCHECKED_CAST")
        override fun get(): T? {
            this.ref.value
            return this.ref.get() as T?
        }

        override fun clear() {
            this.ref.clear()
        }

    }

}