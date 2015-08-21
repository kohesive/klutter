package uy.klutter.core.uri

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

public class TestUriBuilder {
    @Test public fun testBasicUrls() {
        val original = "http://www.klutter.uy/some/path/to/happiness?level=42&type=supreme#holidays"
        val builder = buildUri(original)
        val built = builder.build()
        assertEquals(original, builder.toString())
        assertEquals(original, built.toString())

        assertEquals("http", builder.scheme)
        assertEquals("http", built.scheme)

        assertEquals("www.klutter.uy", builder.host)
        assertEquals("www.klutter.uy", built.host)
        assertTrue(builder.hasHost())
        assertTrue(built.hasHost())

        assertNull(builder.port)
        assertNull(built.port)
        assertFalse(builder.hasPort())
        assertFalse(built.hasPort())

        assertEquals("/some/path/to/happiness", builder.encodedPath)
        assertEquals("/some/path/to/happiness", built.encodedPath)
        assertEquals(listOf("some", "path", "to", "happiness"), builder.decodedPath)
        assertEquals(listOf("some", "path", "to", "happiness"), built.decodedPath)
        assertTrue(builder.hasPath())
        assertTrue(built.hasPath())

        assertEquals("level=42&type=supreme", builder.encodedQuery)
        assertEquals("level=42&type=supreme", built.encodedQuery)
        assertEquals(mapOf("level" to "42", "type" to "supreme"), builder.decodedQueryDeduped)
        assertEquals(mapOf("level" to "42", "type" to "supreme"), built.decodedQueryDeduped)
        assertEquals(mapOf("level" to listOf("42"), "type" to listOf("supreme")), builder.decodedQuery)
        assertEquals(mapOf("level" to listOf("42"), "type" to listOf("supreme")), built.decodedQuery)

        assertEquals("holidays", builder.encodedFragment)
        assertEquals("holidays", built.encodedFragment)
        assertTrue(builder.hasFragment())
        assertTrue(built.hasFragment())
    }

    @Test public fun testOtherThingsThereOrAbsent() {
        val original = "http://www.klutter.uy:8080"
        val builder = buildUri(original)
        val built = builder.build()

        assertEquals(original, builder.toString())
        assertEquals(original, built.toString())

        assertTrue(builder.hasPort())
        assertTrue(built.hasPort())
        assertEquals(8080, builder.port)
        assertEquals(8080, built.port)

        assertFalse(builder.hasPath())
        assertFalse(built.hasPath())

        assertFalse(builder.hasQuery())
        assertFalse(built.hasQuery())

        assertFalse(builder.hasFragment())
        assertFalse(built.hasFragment())
    }

    @Test public fun testPathDifferences() {
        val original = "http://www.klutter.uy"
        val builder1 = buildUri(original).encodedPath("without/leading/slash")
        assertEquals("http://www.klutter.uy/without/leading/slash", builder1.toString())
        assertEquals("/without/leading/slash", builder1.encodedPath)
        val builder2 = buildUri(original).encodedPath("/with/leading/slash")
        assertEquals("http://www.klutter.uy/with/leading/slash", builder2.toString())
        assertEquals("/with/leading/slash", builder2.encodedPath)
    }

    @Test public fun testDifferentQuerySetters() {
        val original = "http://www.klutter.uy"
        val builder1 = buildUri(original).addQueryParams("one" to "1", "two" to "2", "three" to "3", "two" to "2again")
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&three=3", builder1.toString())
        val builder2 = buildUri(original)
        builder2.decodedQuery = mapOf("one" to listOf("1"), "two" to listOf("2", "2again"), "three" to listOf("3"))
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&three=3", builder2.toString())
        val builder3 = buildUri(original)
        builder3.decodedQueryDeduped = mapOf("one" to "1", "two" to "2", "two" to "2again", "three" to "3")
        assertEquals("http://www.klutter.uy?one=1&two=2again&three=3", builder3.toString())

        val builder4 = buildUri("http://www.klutter.uy?one=1&two=2&two=2again&three=3").addQueryParams("two" to "2mas")
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&two=2mas&three=3", builder4.toString())
        builder4.replaceQueryParams("two" to "2replace")
        assertEquals("http://www.klutter.uy?one=1&two=2replace&three=3", builder4.toString())
        builder4.removeQueryParams("two", "three")
        assertEquals("http://www.klutter.uy?one=1", builder4.toString())
    }

}
