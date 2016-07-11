package uy.klutter.binder

import org.junit.Ignore
import org.junit.Test
import uy.klutter.core.common.maximum
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

            val check = ConstructionBinding.from<TestConstructOnlyDefaultConstructor>(TestConstructOnlyDefaultConstructor::class.primaryConstructor!!,
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

            val check = ConstructionBinding.from<TestConstructOnlyDefaultConstructor>(TestConstructOnlyDefaultConstructor::class.primaryConstructor!!,
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

            val check = ConstructionBinding.from<TestConstructOnlyDefaultConstructorSomeImmutable>(TestConstructOnlyDefaultConstructorSomeImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF")),
                    treatUnusedValuesFromProviderAsErrors = false
            )

            assertEquals(0, check.errorCount) // 3 values are immutable and cannot be set but had values for them
            assertTrue(check.propertyWarnings.all { it.first in setOf("d", "e", "f") && it.second == ConstructionWarning.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY })
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

            val check = ConstructionBinding.from<TestConstructOnlyDefaultConstructorSomeImmutable>(TestConstructOnlyDefaultConstructorSomeImmutable::class.primaryConstructor!!,
                    MapValueProvider(mapOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF")),
                    treatUnusedValuesFromProviderAsErrors = true
            )

            assertEquals(3, check.errorCount) // 3 values are immutable and cannot be set but had values for them
            assertTrue(check.propertyErrors.all { it.first in setOf("d", "e", "f") && it.second == ConstructionError.HAVE_VALUE_FOR_NON_SETTABLE_PROPERTY })
            assertEquals(0, check.warningCount)
            assertEquals(0, check.withParameters.size)
            assertEquals(3, check.thenSetProperties.size) // 3 properties could be set after construction

            try {
                check.execute()
                fail("expected IllegalStateException, cannot execute a plan when there are errors")
            } catch (ex: IllegalStateException) {
                // expected
            }
        }
    }

    @Test fun testPlanBuldingPrimaryConstrutorSetsAll() {
        class TestConstructOnlyConstructorAllImmutable(val a: String, val b: Int, val c: String?, val d: String = "defaulted", val e: Int = 3, var f: String?) {}
        class TestConstructOnlyConstructorMixMutable(val a: String, val b: Int, var c: String?, var d: String = "defaulted", var e: Int = 3, var f: String?) {}

        run {
            // Immutable class, all values specified

            val check = ConstructionBinding.from<TestConstructOnlyConstructorAllImmutable>(TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
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

            val check = ConstructionBinding.from<TestConstructOnlyConstructorAllImmutable>(TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
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

            val check = ConstructionBinding.from<TestConstructOnlyConstructorAllImmutable>(TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "f" to "valueF").withMissingInParameterValuesAsNullable())

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

            val check = ConstructionBinding.from<TestConstructOnlyConstructorAllImmutable>(TestConstructOnlyConstructorAllImmutable::class.primaryConstructor!!,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "f" to "valueF")
            )

            assertEquals(1, check.errorCount)
            assertEquals(1, check.parameterErrors.count { it.first.name == "c" && it.second == CallableError.MISSING_VALUE_FOR_REQUIRED_PARAMETER })
            assertEquals(0, check.warningCount)
            assertEquals(3, check.withParameters.size)
            assertEquals(0, check.thenSetProperties.size)

            try {
                check.execute()
                fail("expected IllegalStateException, cannot execute a plan when there are errors")
            } catch (ex: IllegalStateException) {
                // expected
            }
        }

        run {
            // mutable class, all values specified

            val check = ConstructionBinding.from<TestConstructOnlyConstructorMixMutable>(TestConstructOnlyConstructorMixMutable::class.primaryConstructor!!,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

    internal open class TestConstructWithCompanionCallables protected constructor(val a: String, val b: Int, val c: String?, val d: String = "defaulted", var e: Int, var f: String?) {
        companion object {
            fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
            @JvmStatic fun createStatic(a: String, b: Int, c: String?, d: String = "defaulted", e: Int = 3, f: String?) = TestConstructWithCompanionCallables(a, b, c, d, e, f)
            fun createDescendant(a: String) = TestConstructWithCompanionCallablesDescendant(a)
        }
    }

    internal class TestConstructWithCompanionCallablesDescendant(a: String) : TestConstructWithCompanionCallables(a, 1, "c", "d", 1, "f")

    @Suppress("UNCHECKED_CAST")
    @Test fun testConstructionViaCompanionObjectMethod() {
        run {
            // all values specified

            val check = ConstructionBinding.from<TestConstructWithCompanionCallables>(TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "create" } as KCallable<TestConstructWithCompanionCallables>,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

            val check = ConstructionBinding.from<TestConstructWithCompanionCallables>(TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "create" } as KCallable<TestConstructWithCompanionCallables>,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

            val findStaticJava = TestConstructWithCompanionCallables::class.java.declaredMethods.first { it.name == "createStatic" && !it.isBridge && Modifier.isStatic(it.modifiers) }
            val staticAsCallable = findStaticJava.kotlinFunction as KCallable<TestConstructWithCompanionCallables>

            val check = ConstructionBinding.from<TestConstructWithCompanionCallables>(staticAsCallable,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

            val check = ConstructionBinding.from<TestConstructWithCompanionCallables>(TestConstructWithCompanionCallables::class.companionObject!!.declaredMemberFunctions.first { it.name == "createDescendant" } as KCallable<TestConstructWithCompanionCallables>,
                    mapValueProviderOf("a" to "valueA").withMissingInParameterValuesAsNullable()
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

            val findStaticJava = TestConstructWithCompanionCallables::class.java.declaredMethods.first { it.name == "createStatic" && !it.isBridge && Modifier.isStatic(it.modifiers) }
            val staticAsCallable = findStaticJava.kotlinFunction as KCallable<TestConstructWithCompanionCallables>


            val check = ConstructionBinding.from<TestConstructWithCompanionCallables>(staticAsCallable,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

            val check = ConstructionBinding.from<TestConstructCompound>(TestConstructCompound::class.primaryConstructor!!,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

            val check = ConstructionBinding.from<TestConstructCompound>(TestConstructCompound::class.primaryConstructor!!,
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "f" to "valueF").withMissingInParameterValuesAsNullable()
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

    @Test fun testPickBestPlanWhenObvious() {
        class TestConstructCompoundMoreThanOneOptionWithObviousBest(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
            constructor (a: String, b: Int, c: String?, d: String, e: Int) : this(a, b, c, d) {
                this.e = e
            }

            constructor (a: String, b: Int, c: String?, d: String, e: Int, f: String?) : this(a, b, c, d) {
                this.e = e
                this.f = f
            }

            var e: Int = 0
            var f: String? = null
            var g: String? = null
            var h: String? = null
        }

        run {
            // one with most matching parameters is obvious, 6 this time
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionWithObviousBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "f" to "valueF").withMissingInParameterValuesAsNullable(),
                    considerCompanionObjectFactories = true, // default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionWithObviousBest::class.constructors.filter { it.parameters.size == 6 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 5 this time
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionWithObviousBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456).withMissingInParameterValuesAsNullable(), // value "f" will generate a null when missing
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionWithObviousBest::class.constructors.filter { it.parameters.size == 5 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 5 this time and no nullable missing parms
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionWithObviousBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456), // "f" is missing and will generate an error skipping one constructor
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionWithObviousBest::class.constructors.filter { it.parameters.size == 5 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 4 this time
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionWithObviousBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD"),
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionWithObviousBest::class.constructors.filter { it.parameters.size == 4 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 5 this time plus 2 properties
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionWithObviousBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 456,
                            "g" to "valueG",
                            "h" to "valueH").withMissingInParameterValuesAsNullable(),
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionWithObviousBest::class.constructors.filter { it.parameters.size == 5 }.first(), check.callableBinding.useCallable)
        }

    }

    @Test fun testPickBestPlanWhenThereAreEquals() {
        class TestConstructCompoundMoreThanOneOptionEquallyBest(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
            constructor (a: String, b: Int, c: String?, d: String, e: Int) : this(a, b, c, d) {
                this.e = e
            }

            constructor (a: String, b: Int, c: String?, d: String, f: String?) : this(a, b, c, d) {
                this.f = f
            }

            var e: Int = 0
            var f: String? = null
        }

        run {
            // one with most matching parameters is obvious, 4 this time because only that constructor is possible
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123).withMissingInParameterValuesAsNullable(),
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 4 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 4 this time because only that constructor is possible
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC").withMissingInParameterValuesAsNullable(),
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 4 }.first(), check.callableBinding.useCallable)
        }

        run {
            // one with most matching parameters is obvious, 4 this time because only that constructor is possible
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "d" to "valueD").withMissingInParameterValuesAsNullable(),
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 4 }.first(), check.callableBinding.useCallable)
        }

        run {
            // ok, we still have same constructor because only one can default "d"
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            // d will be defaulted
                            "e" to 3).withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 4 }.first(), check.callableBinding.useCallable)
        }

        run {
            // ok, we now hit a 5 parameter constructor if we have d and e present
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 3).withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 5 && it.parameters.any { it.name == "e" } }.first(), check.callableBinding.useCallable)
        }

        run {
            // ok, we now hit a 5 parameter constructor if we have d and f present
            val check = ConstructionBinding.findBestBinding<TestConstructCompoundMoreThanOneOptionEquallyBest>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "f" to "valueF").withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructCompoundMoreThanOneOptionEquallyBest::class.constructors.filter { it.parameters.size == 5 && it.parameters.any { it.name == "f" } }.first(), check.callableBinding.useCallable)
        }

    }

    internal class TestConstructDifferentOptions(val a: String, val b: Int, val c: String?, val d: String = "defaulted") {
        var e: Int = 0
        var f: String? = null

        constructor (a: String, b: Int, c: String?, d: String = "defaulted", e: Int) : this(a, b, c, d) {
            this.e = e
        }

        companion object {
            fun create(a: String, b: Int, c: String?, d: String = "defaulted", e: Int, f: String?): TestConstructDifferentOptions {
                val temp = TestConstructDifferentOptions(a, b, c, d)
                temp.e = e
                temp.f = f
                return temp
            }
        }
    }

    @Test fun testPickBestPlanWhenThereAreCreatorVersusConstructors() {
        run {
            // ok, we should find the companion creator
            val check = ConstructionBinding.findBestBinding<TestConstructDifferentOptions>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 0,
                            "f" to "valueF").withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertEquals(TestConstructDifferentOptions::class.companionObject!!.declaredMemberFunctions.first { it.parameters.size == 7 } as KCallable<*>, check.callableBinding.useCallable)
            check.execute()
        }

        run {
            // ok, without allowing companion functions, we should find a constructor
            val check = ConstructionBinding.findBestBinding<TestConstructDifferentOptions>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 0,
                            "f" to "valueF").withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = false, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertTrue(check.callableBinding.useCallable in TestConstructDifferentOptions::class.constructors)
            check.execute()
        }

        run {
            // ok, we should find the constructor with "f" parameter missing because the warning for missing treated as nullable downgrades the creator
            val check = ConstructionBinding.findBestBinding<TestConstructDifferentOptions>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 0).withMissingInParameterValuesAsNullable(), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertTrue(check.callableBinding.useCallable in TestConstructDifferentOptions::class.constructors)
            check.execute()
        }

        run {
            // ok, we should find the constructor with "f" parameter missing is now an invalid parameter
            val check = ConstructionBinding.findBestBinding<TestConstructDifferentOptions>(
                    mapValueProviderOf("a" to "valueA",
                            "b" to 123,
                            "c" to "valueC",
                            "d" to "valueD",
                            "e" to 0), // constructor or property
                    considerCompanionObjectFactories = true, // same as default
                    treatUnusedValuesFromProviderAsErrors = true // default
            )!!

            assertTrue(check.callableBinding.useCallable in TestConstructDifferentOptions::class.constructors)
            check.execute()
        }
    }

    @Test fun testEmptyConstructor() {
        class TestDefaultConstructor() {
            val a: String = "hello"
        }

        class TestDefaultConstructorIgnoringOthers() {
            var a: String = "hello"

            constructor (a: String) : this() {
                this.a = a
            }
        }

        val check1 = constructFromValues<TestDefaultConstructor>(emptyValueProvider())
        assertEquals("hello", check1.a)

        val check2 = constructFromValues<TestDefaultConstructorIgnoringOthers>(emptyValueProvider())
        assertEquals("hello", check2.a)

        val check3 = constructFromValues<TestDefaultConstructorIgnoringOthers>(MapValueProvider(mapOf("a" to "goodbye")))
        assertEquals("goodbye", check3.a)

    }

    @Test fun testNestedConstruction() {
        // if the value provider has dotted/nested properties it can bind sub object construction
        class TestSubClass(val a: Int, val b: String)
        class TestOuterClass(val x: Int, val sub: TestSubClass)

        run {
            val check = ConstructionBinding.findBestBinding<TestOuterClass>(mapValueProviderOf("x" to 123, "sub.a" to 10, "sub.b" to "bbb"))!!
            val obj = check.execute()

            assertEquals(123, obj.x)
            assertEquals(10, obj.sub.a)
            assertEquals("bbb", obj.sub.b)
        }

        run {
            val check = ConstructionBinding.findBestBinding<TestOuterClass>(mapValueProviderOf("x" to 123, "sub" to mapValueProviderOf("a" to 10, "b" to "bbb")))!!
            val obj = check.execute()

            assertEquals(123, obj.x)
            assertEquals(10, obj.sub.a)
            assertEquals("bbb", obj.sub.b)
        }

        class Breed(val type: String, val lifespan: Int)
        class Dog(val legs: Int, val name: String, val volume: Int, val breed: Breed, val hairlen: Int)
        class Thing(val a: Int, val b: String, val dog: Dog, val f: Int)
        run {
            val prov = MapValueProvider(mapOf("a" to 123, "b" to "cat", "dog.legs" to 4, "dog.volume" to 34, "dog.name" to "frank", "dog.breed.type" to "longhair", "dog.breed.lifespan" to 12, "dog.hairlen" to 33, "f" to 999))
            val check = ConstructionBinding.findBestBinding<Thing>(prov)!!
            val obj = check.execute()
            assertEquals(123, obj.a)
            assertEquals("cat", obj.b)
            assertEquals(999,obj.f)

            assertEquals(4, obj.dog.legs)
            assertEquals(34, obj.dog.volume)
            assertEquals("frank", obj.dog.name)
            assertEquals(33, obj.dog.hairlen)

            assertEquals("longhair", obj.dog.breed.type)
            assertEquals(12, obj.dog.breed.lifespan)
        }
    }

    // TODO: test for non public setters, non public constructors, non public creators

    @Test fun testDottedConversion() {
        val prov = MapValueProvider(mapOf("a" to 123, "b" to "cat", "dog.legs" to 4, "dog.volume" to 34, "dog.name" to "frank", "dog.breed.type" to "longhair", "dog.breed.lifespan" to 12, "dog.hairlen" to 33, "f" to 999))

        // top level
        assertEquals(123, prov.valueByName("a", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)
        assertEquals("cat", prov.valueByName("b", String::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as String)
        assertEquals(999, prov.valueByName("f", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)

        // dog level
        val dog = (prov.valueByName("dog", Any::class.defaultType, ValueProviderTargetScope.UNKNOWN) as ProvidedValue.Nested).value
        assertEquals(4, dog.valueByName("legs", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)
        assertEquals(34, dog.valueByName("volume", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)
        assertEquals("frank", dog.valueByName("name", String::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as String)
        assertEquals(33, dog.valueByName("hairlen", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)

        // breed level
        val breed = (dog.valueByName("breed", Any::class.defaultType, ValueProviderTargetScope.UNKNOWN) as ProvidedValue.Nested).value
        assertEquals("longhair", breed.valueByName("type", String::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as String)
        assertEquals(12, breed.valueByName("lifespan", Int::class.defaultType, ValueProviderTargetScope.UNKNOWN).value as Int)

    }

    @Test fun testMapAsParameterConstruction() {
        class Something(val mappy: Map<String, String>)

        val prov = MapValueProvider(mapOf("mappy.a" to "a", "mappy.b" to "b", "mappy.c" to "c"))
        val check = ConstructionBinding.findBestBinding<Something>(prov)!!
        val obj = check.execute()

        assertEquals(mapOf("a" to "a", "b" to "b", "c" to "c"), obj.mappy)
    }

    @Test fun testMapConstruction() {
        val prov = MapValueProvider(mapOf("a" to "a", "b" to "b", "c" to "c"))
        val check = ConstructionBinding.findBestBinding<Map<String, String>>(prov)!!
        val obj = check.execute()

        assertEquals(mapOf("a" to "a", "b" to "b", "c" to "c"), obj)
    }

}