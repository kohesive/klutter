package uy.klutter.reflect.full.tests

import org.junit.Test
import uy.klutter.reflect.KCallableFuncRefOrLambda
import kotlin.reflect.memberExtensionFunctions
import kotlin.reflect.memberProperties
import kotlin.test.assertEquals

@Suppress("NOTHING_TO_INLINE") class TestKt9005 {
    @Test fun testNormalKCallable() {
        val method = TestKt9005::testNormalCallable
        assertEquals(22, method.call(this, 3, 4, 5))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        assertEquals(116, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4)))
    }

    @Test fun testNormalKCallableWithNullable() {
        val method = TestKt9005::testNormalCallableWithNullable
        assertEquals(28, method.call(this, 3, 4, 5, 6))
        assertEquals(17, method.call(this, 3, 4, null, null))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        assertEquals(28, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 5, pmap.get("d")!! to 6)))
        assertEquals(22, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 5, pmap.get("d")!! to null)))
        assertEquals(22, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 5)))
        assertEquals(17, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to null)))
    }

    @Test fun testInlineNormalKCallable() {
        val method = TestKt9005::testInlineNormalCallable
        assertEquals(22, method.call(this, 3, 4, 5))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        assertEquals(116, method.callBy(mapOf(inst to this, pmap.get("a")!! to 3, pmap.get("b")!! to 4)))
    }

    @Test fun testExtensionCallable() {
        val method = TestKt9005::class.memberExtensionFunctions.first { it.name == "testExtensionCallable" }
        assertEquals(32, method.call(this, Nothing(10), 3, 4, 5))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        val recv = method.parameters.drop(1).first()
        assertEquals(126, method.callBy(mapOf(inst to this, recv to Nothing(10), pmap.get("a")!! to 3, pmap.get("b")!! to 4)))
    }

    @Test fun testReferenceCallable() {
        val member = TestKt9005::class.memberProperties.first { it.name == "referenceCallable" }

        val funcInstance = member.get(this) as Function<*>
        val method = KCallableFuncRefOrLambda.Companion.fromInstance(funcInstance, member.name, member.annotations)
        assertEquals(22, method.call(funcInstance, 3, 4, 5))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        assertEquals(116, method.callBy(mapOf(inst to funcInstance, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 99)))
    }

    @Test fun testReferenceCallableWithNullable() {
        val member = TestKt9005::class.memberProperties.first { it.name == "referenceCallableWithNullable" }

        val funcInstance = member.get(this) as Function<*>
        val method = KCallableFuncRefOrLambda.Companion.fromInstance(funcInstance, member.name, member.annotations)
        assertEquals(28, method.call(funcInstance, 3, 4, 5, 6))
        assertEquals(17, method.call(funcInstance, 3, 4, null, null))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        assertEquals(28, method.callBy(mapOf(inst to funcInstance, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 5, pmap.get("d")!! to 6)))
        assertEquals(22, method.callBy(mapOf(inst to funcInstance, pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 5, pmap.get("d")!! to null)))
    }

    @Test fun testReferenceExtCallable() {
        val member = TestKt9005::class.memberProperties.first { it.name == "referenceExtCallable" }

        val funcInstance = member.get(this) as Function<*>
        val method = KCallableFuncRefOrLambda.Companion.fromInstance(funcInstance, member.name, member.annotations)
        assertEquals(32, method.call(funcInstance, Nothing(10), 3, 4, 5))

        val pmap = method.parameters.map { it.name to it }.toMap()
        val inst = method.parameters.first()
        val recv = method.parameters.drop(1).first()
        assertEquals(126, method.callBy(mapOf(inst to funcInstance, recv to Nothing(10), pmap.get("a")!! to 3, pmap.get("b")!! to 4, pmap.get("c")!! to 99)))
    }

    val base = 10

    fun testNormalCallable(a: Int, b: Int, c: Int = 99): Int {
        return base + a + b + c
    }

    inline fun testInlineNormalCallable(a: Int, b: Int, c: Int = 99): Int {
        return base + a + b + c
    }

    fun Nothing.testExtensionCallable(a: Int, b: Int, c: Int = 99): Int {
        return n + base + a + b + c
    }

    val referenceCallable = fun(a: Int, b: Int, c: Int): Int {
        return base + a + b + c
    }

    val referenceExtCallable = fun Nothing.(a: Int, b: Int, c: Int): Int {
        return n + base + a + b + c
    }

    fun testNormalCallableWithNullable(a: Int, b: Int, c: Int?, d: Int? = null): Int {
        return base + a + b + (c ?: 0) + (d ?: 0)
    }

    val referenceCallableWithNullable = fun(a: Int, b: Int, c: Int?, d: Int?): Int {
        return base + a + b + (c ?: 0) + (d ?: 0)
    }
}

data class Nothing(val n: Int)

