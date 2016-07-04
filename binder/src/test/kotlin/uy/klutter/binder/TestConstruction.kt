package uy.klutter.binder

import org.junit.Ignore
import org.junit.Test
import java.lang.reflect.Modifier
import kotlin.reflect.*
import kotlin.reflect.jvm.kotlinFunction
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail


class TestConstruction {

    @Test fun testPlanBuldingDefaultConstructorSettingPropertiesAfter() {
        class TestConstructOnlyDefaultConstructor() {
            var a: String = ""
            var b: Int = 0
            var c: String? = null
            var d: String = "defaulted"
            var e: Int = 3
            var f: String? = null
        }

        class TestConstructOnlyDefaultConstructorSomeImmutable() {
            var a: String = ""
            var b: Int = 0
            var c: String? = null
            val d: String = "defaulted"
            val e: Int = 3
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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
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
                            )),
                    treatUnusedValuesFromProviderAsErrors = false
            )

            assertEquals(0, check.errorCount)

            assertEquals(3, check.warningCount) // 2 values not set after construction, 1 unused value from provider
            assertEquals(1, check.propertyWarnings.count { it.first == "unused" && it.second == ConstructionWarning.HAVE_VALUE_NOT_USED })
            assertEquals(1, check.propertyWarnings.count { it.first == "d" && it.second == ConstructionWarning.MISSING_VALUE_FOR_SETTABLE_PROPERTY })
            assertEquals(1, check.propertyWarnings.count { it.first == "f" && it.second == ConstructionWarning.MISSING_VALUE_FOR_SETTABLE_PROPERTY })

            assertEquals(0, check.withParameters.size)
            assertEquals(4, check.thenSetProperties.size) // 4 values can be set after construction

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d) // not set, defaulted in constructor
            assertEquals(456, inst.e)
            assertEquals(null, inst.f) // not set, defaulted in property declaration
        }

        run {
            // nothing set in constructor,
            // all values settable after
            // but 3 values are `val` not `var` and cannot be set, which become warnings by default

            val check = ConstructionPlan.from(TestConstructOnlyDefaultConstructorSomeImmutable::class,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.java,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF")),
                    treatUnusedValuesFromProviderAsErrors = false
            )

            assertEquals(0, check.errorCount) // 3 values are immutable and cannot be set but had values for them
            assertTrue(check.propertyWarnings.all { it.first in setOf("d","e","f") && it.second == ConstructionWarning.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY })
            assertEquals(3, check.warningCount)
            assertEquals(0, check.withParameters.size)
            assertEquals(3, check.thenSetProperties.size) // 3 properties could be set after construction

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d) // value was present but property wasn't settable
            assertEquals(3, inst.e)           // value was present but property wasn't settable
            assertEquals(null, inst.f)        // value was present but property wasn't settable
        }

        run {
            // nothing set in constructor,
            // all values settable after
            // but 3 values are `val` not `var` and cannot be set, which we now want as errors

            val check = ConstructionPlan.from(TestConstructOnlyDefaultConstructorSomeImmutable::class,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.java,
                    TestConstructOnlyDefaultConstructorSomeImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF")),
                    treatUnusedValuesFromProviderAsErrors = true
            )

            assertEquals(3, check.errorCount) // 3 values are immutable and cannot be set but had values for them
            assertTrue(check.propertyErrors.all { it.first in setOf("d","e","f") && it.second == ConstructionError.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY })
            assertEquals(0, check.warningCount)
            assertEquals(0, check.withParameters.size)
            assertEquals(3, check.thenSetProperties.size) // 3 properties could be set after construction

            try {
                check.execute()
                fail("expected IllegalStateException, cannot execute a plan when there are errors")
            }  catch (ex: IllegalStateException) {
                // expected
            }
        }
    }

    @Test fun testPlanBuldingPrimaryConstrutorSetsAll() {
        class TestConstructOnlyConstructorAllImmutable(val a: String, val b: Int, val c: String?, val d: String = "defaulted", val e: Int = 3, var f: String?) {}
        class TestConstructOnlyConstructorMixMutable(val a: String, val b: Int, var c: String?, var d: String = "defaulted", var e: Int = 3, var f: String?) {}

        run {
            // Immutable class, all values specified

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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // Immutable class, none of the default values specified

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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d)
            assertEquals(3, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // Immutable class, none of the default values specified and one nullable not specified

            val check = ConstructionPlan.from(TestConstructOnlyConstructorAllImmutable::class,
                    TestConstructOnlyConstructorAllImmutable::class.java,
                    TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "f" to "valueF")),
                    treatMissingAsNullForNullableConstructorParameters = true // already default but to make test clear
            )

            assertEquals(0, check.errorCount)
            assertEquals(1, check.warningCount) // one missing value became null for a nullable type
            assertEquals(4, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals(null, inst.c)
            assertEquals("defaulted", inst.d)
            assertEquals(3, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // Immutable class, none of the default values specified and one nullable not specified while that is made illegal

            val check = ConstructionPlan.from(TestConstructOnlyConstructorAllImmutable::class,
                    TestConstructOnlyConstructorAllImmutable::class.java,
                    TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "f" to "valueF")),
                    treatMissingAsNullForNullableConstructorParameters = false
            )

            assertEquals(1, check.errorCount)
            assertEquals(1, check.parameterErrors.count { it.first.name == "c" && it.second == CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER})
            assertEquals(0, check.warningCount)
            assertEquals(3, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)

            try {
                check.execute()
                fail("expected IllegalStateException, cannot execute a plan when there are errors")
            }  catch (ex: IllegalStateException) {
                // expected
            }
        }

        run {
            // mutable class, all values specified

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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
        }

    }

    open class TestConstructWithCompanionCallables protected constructor(val a: String, val b: Int, val c: String?, val d: String = "defaulted", var e: Int, var f: String?) {
        companion object {
            fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
            @JvmStatic fun createStatic(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
            fun createDescendant(a: String) = TestConstructWithCompanionCallablesDescendant(a)
        }
    }

    class TestConstructWithCompanionCallablesDescendant(a: String): TestConstructWithCompanionCallables(a, 1, "c", "d", 1, "f")

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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d)
            assertEquals(3, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // check that static doesn't interfere
            // all values specified

            val findStaticJava = TestConstructWithCompanionCallables::class.java.declaredMethods.first { it.name == "createStatic" && !it.isBridge && Modifier.isStatic(it.modifiers)}
            val staticAsCallable = findStaticJava.kotlinFunction  as KCallable<TestConstructWithCompanionCallables>

            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    staticAsCallable,
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

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // a creator that makes a descendant class is ok too

            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "createDescendant" } as KCallable<TestConstructWithCompanionCallables>,
                    MapValueProvider(mapOf("a" to "valueA"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(2, check.warningCount) // 2 properties exist that are settable but we don't have values for them and they aren't obviously set in constructor
            assertEquals(2, check.withParameters.size) // is param count + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(1, inst.b)
            assertEquals("c", inst.c)
            assertEquals("d", inst.d)
            assertEquals(1, inst.e)
            assertEquals("f", inst.f)
        }
   }

    @Suppress("UNCHECKED_CAST")
    @Ignore("callBy on static method will fail as of Kotlin 1.0.2, see https://youtrack.jetbrains.com/issue/KT-12915")
    @Test fun testConstructionViaCompanionObjectMethodThatIsStaticWithMissingParameters() {
        run {
            // check that static doesn't interfere, calling from viewpoint of static instead of companion
            // ones with defaults not specified

            val findStaticJava = TestConstructWithCompanionCallables::class.java.declaredMethods.first { it.name == "createStatic" && !it.isBridge && Modifier.isStatic(it.modifiers)}
            val staticAsCallable = findStaticJava.kotlinFunction  as KCallable<TestConstructWithCompanionCallables>


            val check = ConstructionPlan.from(TestConstructWithCompanionCallables::class,
                    TestConstructWithCompanionCallables::class.java,
                    staticAsCallable,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(5, check.withParameters.size)  // is param count 4 + 1 because of Receiver being the companion object instance
            assertEquals(0, check.thenSetProperties.size)

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d)
            assertEquals(3, inst.e)
            assertEquals("valueF", inst.f)
        }
    }

    @Test fun testConstructionViaMixedModel() {
        class TestConstructCompound(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
            var e: Int = 3
            var f: String? = null
        }

        run {
            // mixed constructor and setters to be used

            val check = ConstructionPlan.from(TestConstructCompound::class,
                    TestConstructCompound::class.java,
                    TestConstructCompound::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(0, check.warningCount)
            assertEquals(4, check.withParameters.size)
            assertEquals(2, check.thenSetProperties.size)

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("valueD", inst.d)
            assertEquals(456, inst.e)
            assertEquals("valueF", inst.f)
        }

        run {
            // mixed constructor and setters to be used
            // drop a few that have defaults, 1 param and 1 property

            val check = ConstructionPlan.from(TestConstructCompound::class,
                    TestConstructCompound::class.java,
                    TestConstructCompound::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF"))
            )

            assertEquals(0, check.errorCount)
            assertEquals(1, check.warningCount) // warning about not setting the setter of 'e' since default value can't be seen
            assertEquals(3, check.withParameters.size)
            assertEquals(1, check.thenSetProperties.size)

            val inst = check.execute()
            assertEquals("valueA", inst.a)
            assertEquals(123, inst.b)
            assertEquals("valueC", inst.c)
            assertEquals("defaulted", inst.d)
            assertEquals(3, inst.e)
            assertEquals("valueF", inst.f)
        }
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