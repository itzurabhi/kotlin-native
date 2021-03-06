package codegen.bridges.test10

import kotlin.test.*

open class A<T> {
    open fun foo(x: T) {
        println(x.toString())
    }
}

interface I {
    fun foo(x: Int)
}

class B : A<Int>(), I {
    var z: Int = 5
    override fun foo(x: Int) {
        z = x
    }
}

fun zzz(a: A<Int>) {
    a.foo(42)
}

@Test fun runTest() {
    val b = B()
    zzz(b)
    val a = A<Int>()
    zzz(a)
    println(b.z)
}