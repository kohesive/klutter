package uy.klutter.binder

import org.junit.Test
import kotlin.reflect.*
import kotlin.reflect.jvm.kotlinFunction
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class TestConstruction {

    @Test fun testPlanBuldingDefaultConstructorSettingPropertiesAfter() {
        class TestConstructOnlyDefaultConstructor() {
            var a: String = ""
            var b: Int = 0
            var c: String? = null
            var d: String = "defaulted"
            var e: Int = 0
            var f: String? = null
        }

        class TestConstructOnlyDefaultConstructorSomeImmutable() {
            var a: String = ""
            var b: Int = 0
            var c: String? = null
            val d: String = "defaulted"
            val e: Int = 0
            val f: String? = null
        }

        run {
            // nothing set in constructor,
            // all values settable after

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
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // nothing set in constructor
            // all but 2 values set after
            // one extra value left dangling in the ValueProvider

            val check = ConstructionPlan.from(TestConstructOnlyDefaultConstructor::class,
                    TestConstructOnlyDefaultConstructor::class.java,
                    TestConstructOnlyDefaultConstructor::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "e" to 456,
                            "unused" to "bla"
                            ))
            )

            assertEquals(0, check.errorCount)
            assertEquals(2, check.warningCount) // 2 values not set after construction
            assertEquals(0, check.withParameters.size)
            assertEquals(4, check.thenSetProperties.size) // 4 values can be set after construction
            assertEquals(setOf("unused"), check.nonmatchingProviderEntries)   // one extra value left dangling in the ValueProvider
        }

        run {
            // nothing set in constructor,
            // all values settable after
            // but 3 values are `val` not `var` and cannot be set

            val check = ConstructionPlan.from(TestConstructOnlyDefaultConstructorSomeImmutable::class,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.java,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(3, check.errorCount) // 3 values are immutable and cannot be set but had values for them
            assertTrue(check.propertyErrors.all { it.first.name in setOf("d","e","f") && it.second == ConstructionError.NON_SETTABLE_PROPERTY })
            assertEquals(0, check.warningCount)
            assertEquals(0, check.withParameters.size)
            assertEquals(3, check.thenSetProperties.size) // 3 properties could be set after construction
            assertEquals(emptySet(), check.nonmatchingProviderEntries)  // even though 3 values can't be set, they did match up
        }
    }

    @Test fun testPlanBuldingPrimaryConstrutorSetsAll() {
        class TestConstructOnlyConstructorAllImmutable(val a: String, val b: Int, val c: String?, val d: String = "defaulted", val e: Int = 3, var f: String?) {}
        class TestConstructOnlyConstructorMixMutable(val a: String, val b: Int, var c: String?, var d: String = "defaulted", var e: Int = 3, var f: String?) {}

        run {
            // all values specified

            val check = ConstructionPlan.from(TestConstructOnlyConstructorAllImmutable::class,
                    TestConstructOnlyConstructorAllImmutable::class.java,
                    TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(6, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // none with default values specified

            val check = ConstructionPlan.from(TestConstructOnlyConstructorAllImmutable::class,
                    TestConstructOnlyConstructorAllImmutable::class.java,
                    TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(4, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // all values specified

            val check = ConstructionPlan.from(TestConstructOnlyConstructorMixMutable::class,
                    TestConstructOnlyConstructorMixMutable::class.java,
                    TestConstructOnlyConstructorMixMutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(6, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

    }

    class TestConstructWithCompanionCallables private constructor(val a: String, val b: Int, val c: String?, val d: String = "defaulted", var e: Int, var f: String?) {
        companion object {
            fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
            @JvmStatic fun createStatic(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @Test fun testConstructionViaCompanionObjectMethod() {
        run {
            // all values specified

            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "create" } as KCallable<TestConstructWithCompanionCallables>,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(7, check.withParameters.size) // is param count + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // ones with defaults not specified

            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "create" } as KCallable<TestConstructWithCompanionCallables>,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(5, check.withParameters.size)  // is param count 4 + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // check that static doesn't interfere
            // all values specified

            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "createStatic" } as KCallable<TestConstructWithCompanionCallables>,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(7, check.withParameters.size) // is param count + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }

        run {
            // check that static doesn't interfere, calling from viewpoint of static instead of companion
            // ones with defaults not specified

            val findStatic = TestConstructWithCompanionCallables::class.java.declaredMethods.first { it.name == "createStatic" && !it.isBridge }.kotlinFunction  as KCallable<TestConstructWithCompanionCallables>
            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    findStatic,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(5, check.withParameters.size)  // is param count 4 + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)
            assertEquals(emptySet(), check.nonmatchingProviderEntries)
        }


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