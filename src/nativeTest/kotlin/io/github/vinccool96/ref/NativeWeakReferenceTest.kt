package io.github.vinccool96.ref

import kotlin.native.internal.GC
import kotlin.test.Test
import kotlin.test.assertNull

@Suppress("UNUSED_VALUE")
class NativeWeakReferenceTest {

    private lateinit var ref: WeakReference<Any?>

    @Test
    fun testGetGC() {
        doRef()
        GC.collect()
        assertNull(this.ref.get())
    }

    private fun doRef() {
        var obj: Any? = Any()
        ref = WeakReferenceFactory.createWeakRef(obj)
        obj = null
    }

}