package io.github.vinccool96.ref

actual object WeakReferenceFactory {

    actual fun <T> createWeakRef(referent: T): WeakReference<T> {
        return JSCoreWeakRef(referent)
    }

    private class JSCoreWeakRef<T>(referent: T) : WeakReference<T> {

        private var ref: NodeWeakRef.WeakRef<T>? = NodeWeakRef.create(referent)

        override val value: T?
            get() = this.get()

        override fun get(): T? {
            val r = this.ref
            return if (r != null) NodeWeakRef.get(r) else null
        }

        override fun clear() {
            this.ref = null
        }

    }

}