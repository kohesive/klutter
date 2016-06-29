package uy.klutter.binder

import org.junit.Test
import kotlin.reflect.primaryConstructor
import kotlin.test.assertEquals


class TestConstruction {

    @Test fun testPlanBulding() {
        run {
            val check = ConstructionPlan.from(TestConstructOnlyDefaultConstructor::class,
                    TestConstructOnlyDefaultConstructor::class.java,
                    TestConstructOnlyDefaultConstructor::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                                           "b" to 123,
                                           "c" to "valueC",
                                           "d" to "valueD",
                                           "e" to 456,
                                           "f" to "valueF"))
                    )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(0, check.withParameters.size)
            assertEquals(6, check.thenSetProperties.size)
            assertEquals(emptySet(), check.unusedProvideEntries)
        }
    }

    // == test classes for constructing different flavours

    class TestConstructOnlyDefaultConstructor() {
        var a: String = ""
        var b: Int = 0
        var c: String? = null
        var d: String = "defaulted"
        var e: Int = 0
        var f: String? = null
    }


    class TestConstructOnlyConstructor(val a: String, val b: Int, val c: String?, val d: String = "defaulted", var e: Int, var f: String?) {
    }

    class TestConstructCompound(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
        var e: Int = 0
        var f: String? = null
    }

    class TestConstructCompoundMoreThanOneOptionWithObviousBest (val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
        constructor (a: String, b: Int, c: String?, d: String, e: Int):this(a,b,c,d) {
            this.e = e
        }
        constructor (a: String, b: Int, c: String?, d: String, e: Int, f: String?):this(a,b,c,d) {
            this.e = e
            this.f = f
        }
        var e: Int = 0
        var f: String? = null
    }

    class TestConstructCompoundMoreThanOneOptionEquallyBest (val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
        constructor (a: String, b: Int, c: String?, d: String, e: Int):this(a,b,c,d) {
            this.e = e
        }
        constructor (a: String, b: Int, c: String?, d: String, f: String?):this(a,b,c,d) {
            this.f = f
        }
        var e: Int = 0
        var f: String? = null
    }

    class TestConstructWithCallable private constructor(val a: String, val b: Int, val c: String?, val d: String = "defaulted", var e: Int, var f: String?) {
        companion object {
            @JvmStatic fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int, f: String?) = TestConstructWithCallable(a, b, c, d, e, f)
        }
    }

    class TestConstructDifferentOptions(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
        var e: Int = 0
        var f: String? = null

        constructor (a: String, b: Int, c: String?, d: String = "defaulted", e: Int) : this(a, b, c, d) {
            this.e = e
        }

        companion object {
            @JvmStatic fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int, f: String?): TestConstructDifferentOptions {
                val temp = TestConstructDifferentOptions(a, b, c, d)
                temp.e = e
                temp.f = f
                return temp
            }
        }
    }

}