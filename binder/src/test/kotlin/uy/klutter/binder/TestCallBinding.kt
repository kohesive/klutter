package uy.klutter.binder

import org.junit.Test
import kotlin.reflect.declaredMemberExtensionFunctions
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestCallBinding {
    class TestVariousMethods(val base: String = "Test:") {
        fun foo(a: String): String { return "$base $a" }
        fun longer(a: String, b: Int, c: String = "defaulted", e: String?, isValid: Boolean): String { return "$base $a $b $c $e $isValid"}
        fun thing(one: Thingies, two: Thingies, more: Thingies): String { return "$base $one $two $more"}
        fun withNum(x: Int) = x+1
        fun thingFromNum(x: Int): Thingies = Thingies.values()[x]

        fun Session.foo(a: String): String { return "$context $a $base" }
        fun Session.longer(a: String, b: Int, c: String = "defaulted", e: String?, isValid: Boolean): String { return "$context $a $b $c $e $isValid $base"}
        fun Session.thing(one: Thingies, two: Thingies, more: Thingies): String { return "$context $one $two $more $base"}

        fun petDog(howMany: Int, specification: Dog): List<Dog> = (1..howMany).map { specification }
    }

    data class Breed(val type: String, val lifespan: Int)
    data class Dog(val legs: Int, val name: String, val volume: Int, val breed: Breed, val hairlen: Int)


    enum class Thingies {
        ONE, TWO, THREE
    }

    class Session(val context: String)

    @Test fun testCallingInstanceMethods() {
        val t = constructFromValues<TestVariousMethods>(emptyValueProvider())

        assertEquals("Test: hello", MethodCallBinding.from(TestVariousMethods::foo, t, null, mapValueProviderOf("a" to "hello").withMissingInParameterValuesAsNullable()).executor())
        assertEquals("Test: hello 123 defaulted null true", MethodCallBinding.from(TestVariousMethods::longer, t, null, mapValueProviderOf("a" to "hello", "b" to 123, "isValid" to true).withMissingInParameterValuesAsNullable()).executor())
        assertEquals("Test: ONE TWO THREE", MethodCallBinding.from(TestVariousMethods::thing, t, null, mapValueProviderOf("one" to Thingies.ONE, "two" to Thingies.TWO, "more" to Thingies.THREE).withMissingInParameterValuesAsNullable()).executor())
        assertEquals(5, MethodCallBinding.from(TestVariousMethods::withNum, t, null, mapValueProviderOf("x" to 4).withMissingInParameterValuesAsNullable()).executor())
        assertEquals(Thingies.TWO, MethodCallBinding.from(TestVariousMethods::thingFromNum, t, null, mapValueProviderOf("x" to 1).withMissingInParameterValuesAsNullable()).executor())
    }

    @Test fun testCallingExtensionMethods() {
        val t = constructFromValues<TestVariousMethods>(mapValueProviderOf("base" to "<:BASE"))
        val s = constructFromValues<Session>(mapValueProviderOf("context" to "CONTEXT:>"))

        val findFoo = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "foo" }
        val findLonger = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "longer" }
        val findThing = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "thing" }
        assertEquals("CONTEXT:> hello <:BASE", MethodCallBinding.from(findFoo, t, s, mapValueProviderOf("a" to "hello").withMissingInParameterValuesAsNullable()).executor())
        assertEquals("CONTEXT:> hello 123 defaulted null true <:BASE", MethodCallBinding.from(findLonger, t, s, mapValueProviderOf("a" to "hello", "b" to 123, "isValid" to true).withMissingInParameterValuesAsNullable()).executor())
        assertEquals("CONTEXT:> ONE TWO THREE <:BASE", MethodCallBinding.from(findThing, t, s, mapValueProviderOf("one" to Thingies.ONE, "two" to Thingies.TWO, "more" to Thingies.THREE).withMissingInParameterValuesAsNullable()).executor())
    }

    @Test fun testTypeConversionOnMethodCalls() {
        val t = constructFromValues<TestVariousMethods>(emptyValueProvider())

        assertEquals("Test: hello 123 defaulted null true", MethodCallBinding.from(TestVariousMethods::longer, t, null, mapValueProviderOf("a" to "hello", "b" to "123", "isValid" to "true").withDefaults()).executor())
        assertEquals("Test: ONE TWO THREE", MethodCallBinding.from(TestVariousMethods::thing, t, null, mapValueProviderOf("one" to "ONE", "two" to "TWO", "more" to 2).withDefaults()).executor())
        assertEquals(5, MethodCallBinding.from(TestVariousMethods::withNum, t, null, mapValueProviderOf("x" to "4").withDefaults()).executor())
        assertEquals(Thingies.TWO, MethodCallBinding.from(TestVariousMethods::thingFromNum, t, null, mapValueProviderOf("x" to "1").withDefaults()).executor())

        val badBinding = MethodCallBinding.from(TestVariousMethods::thing, t, null, mapValueProviderOf("one" to "ONE", "two" to "TWO", "more" to 2))
        assertTrue(badBinding.hasErrors)
    }

    @Test fun testParameterIsComplexObject() {
        val t = constructFromValues<TestVariousMethods>(emptyValueProvider())
        val prov = MapValueProvider(mapOf("howMany" to 1, "specification.legs" to 4, "specification.volume" to 34, "specification.name" to "frank", "specification.breed.type" to "longhair", "specification.breed.lifespan" to 12, "specification.hairlen" to 33))
        val check = MethodCallBinding.from(TestVariousMethods::petDog, t, null, prov).executor()
        assertEquals(1, check.size)
        val firstDog = check.first()
        assertEquals(Dog(4, "frank", 34, Breed("longhair", 12), 33), firstDog)
    }
}