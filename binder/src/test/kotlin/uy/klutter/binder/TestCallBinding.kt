package uy.klutter.binder

import org.junit.Test
import kotlin.reflect.declaredMemberExtensionFunctions
import kotlin.test.assertEquals

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
    }

    enum class Thingies {
        ONE, TWO, THREE
    }

    class Session(val context: String)

    @Test fun testCallingInstanceMethods() {
        val t = constructFromValues<TestVariousMethods>(emptyValueProvider())

        assertEquals("Test: hello", MethodCallBinding.from(TestVariousMethods::foo, t, null, MapValueProvider(mapOf("a" to "hello"))).execute())
        assertEquals("Test: hello 123 defaulted null true", MethodCallBinding.from(TestVariousMethods::longer, t, null, MapValueProvider(mapOf("a" to "hello", "b" to 123, "isValid" to true))).execute())
        assertEquals("Test: ONE TWO THREE", MethodCallBinding.from(TestVariousMethods::thing, t, null, MapValueProvider(mapOf("one" to Thingies.ONE, "two" to Thingies.TWO, "more" to Thingies.THREE))).execute())
        assertEquals(5, MethodCallBinding.from(TestVariousMethods::withNum, t, null, MapValueProvider(mapOf("x" to 4))).execute())
        assertEquals(Thingies.TWO, MethodCallBinding.from(TestVariousMethods::thingFromNum, t, null, MapValueProvider(mapOf("x" to 1))).execute())
    }

    @Test fun testCallingExtensionMethods() {
        val t = constructFromValues<TestVariousMethods>(MapValueProvider(mapOf("base" to "<:BASE")))
        val s = constructFromValues<Session>(MapValueProvider(mapOf("context" to "CONTEXT:>")))

        val findFoo = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "foo" }
        val findLonger = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "longer" }
        val findThing = TestVariousMethods::class.declaredMemberExtensionFunctions.first { it.name == "thing" }
        assertEquals("CONTEXT:> hello <:BASE", MethodCallBinding.from(findFoo, t, s, MapValueProvider(mapOf("a" to "hello"))).execute())
        assertEquals("CONTEXT:> hello 123 defaulted null true <:BASE", MethodCallBinding.from(findLonger, t, s, MapValueProvider(mapOf("a" to "hello", "b" to 123, "isValid" to true))).execute())
        assertEquals("CONTEXT:> ONE TWO THREE <:BASE", MethodCallBinding.from(findThing, t, s, MapValueProvider(mapOf("one" to Thingies.ONE, "two" to Thingies.TWO, "more" to Thingies.THREE))).execute())
    }
}