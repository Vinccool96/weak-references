package io.github.vinccool96.ref

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertSame

class WeakReferenceTest {

    @Test
    fun testValue() {
        val obj = Any()
        val ref = WeakReferenceFactory.createWeakRef(obj)
        assertSame(obj, ref.value)
    }

    @Test
    fun testGet() {
        val obj = Any()
        val ref = WeakReferenceFactory.createWeakRef(obj)
        assertSame(obj, ref.get())
    }

    @Test
    fun testClear() {
        val obj = Any()
        val ref = WeakReferenceFactory.createWeakRef(obj)
        assertNotNull(ref.get())
        ref.clear()
        assertNull(ref.get())
    }

}