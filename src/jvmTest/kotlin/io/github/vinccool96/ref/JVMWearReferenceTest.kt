package io.github.vinccool96.ref

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@Suppress("UNUSED_VALUE")
class JVMWearReferenceTest {

    @Test
    fun testGetGC() {
        var obj: Any? = Any()
        val ref = WeakReferenceFactory.createWeakRef(obj)
        assertNotNull(ref.get())
        obj = null
        System.gc()
        assertNull(ref.get())
    }

}