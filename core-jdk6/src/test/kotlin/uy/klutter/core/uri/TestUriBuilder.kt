package uy.klutter.core.uri.tests

import org.junit.Test
import uy.klutter.core.uri.UriBuilder
import uy.klutter.core.uri.UrlEncoding
import uy.klutter.core.uri.buildUri
import java.net.URI
import java.net.URLDecoder
import kotlin.test.*

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
        assertEquals(builder1.toString(), builder1.toURI().toString())
        assertEquals("/without/leading/slash", builder1.encodedPath)
        val builder2 = buildUri(original).encodedPath("/with/leading/slash")
        assertEquals("http://www.klutter.uy/with/leading/slash", builder2.toString())
        assertEquals(builder2.toString(), builder2.toURI().toString())
        assertEquals("/with/leading/slash", builder2.encodedPath)
    }

    @Test public fun testClearingThings() {
        val original = "http://www.klutter.uy/path?a=1&b=2&c=3#fraggy"
        val builder1 = buildUri(original).clearQuery()
        assertEquals("http://www.klutter.uy/path#fraggy", builder1.toString())
        val builder2 = buildUri(original).clearFragment()
        assertEquals("http://www.klutter.uy/path?a=1&b=2&c=3", builder2.toString())
        val builder3 = buildUri(original).clearPath()
        assertEquals("http://www.klutter.uy?a=1&b=2&c=3#fraggy", builder3.toString())
        val builder4 = buildUri(original).clearQuery().clearFragment().clearPath()
        assertEquals("http://www.klutter.uy", builder4.toString())
        val builder5 = buildUri(original).clearQueryExcept("b")
        assertEquals("http://www.klutter.uy/path?b=2#fraggy", builder5.toString())
        val builder6 = buildUri(original).clearQueryExcept("a", "c")
        assertEquals("http://www.klutter.uy/path?a=1&c=3#fraggy", builder6.toString())
    }

    @Test public fun testDifferentQuerySetters() {
        val original = "http://www.klutter.uy"
        val builder1 = buildUri(original).addQueryParams("one" to "1", "two" to "2", "three" to "3", "two" to "2again", "four" to "a b")
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&three=3&four=a+b", builder1.toString())
        assertEquals(builder1.toString(), builder1.toURI().toString())
        val builder2 = buildUri(original)
        builder2.decodedQuery = mapOf("one" to listOf("1"), "two" to listOf("2", "2again"), "three" to listOf("3"), "four" to listOf("a b"))
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&three=3&four=a+b", builder2.toString())
        assertEquals(builder2.toString(), builder2.toURI().toString())
        val builder3 = buildUri(original)
        builder3.decodedQueryDeduped = mapOf("one" to "1", "two" to "2", "two" to "2again", "three" to "3", "four" to "a b")
        assertEquals("http://www.klutter.uy?one=1&two=2again&three=3&four=a+b", builder3.toString())
        assertEquals(builder3.toString(), builder3.toURI().toString())

        // added %20 in the query key and value for one
        // added empty parameter as well
        // added parameter without value
        val builder4 = buildUri("http://www.klutter.uy?one=1&&%20two%20=%202%20&two=2again&three=3&four=").addQueryParams("two" to "2mas")
        assertEquals("http://www.klutter.uy?one=1&two=2&two=2again&two=2mas&three=3&four=", builder4.toString())
        builder4.replaceQueryParams("two" to "2replace")
        assertEquals("http://www.klutter.uy?one=1&two=2replace&three=3&four=", builder4.toString())
        assertEquals(builder4.toString(), builder4.toURI().toString())
        builder4.removeQueryParams("two", "three", "four")
        assertEquals("http://www.klutter.uy?one=1", builder4.toString())
        assertEquals(builder4.toString(), builder4.toURI().toString())
        builder4.addQueryParams("four" to "a b")
        assertEquals("http://www.klutter.uy?one=1&four=a+b", builder4.toString())
        assertEquals(builder4.toString(), builder4.toURI().toString())
    }

    @Test
    public fun testSpaceSpecialInQuery() {
        assertEquals("a+b+c", UrlEncoding.encodeQueryNameOrValue("a b c"))
        assertEquals("a b c", UrlEncoding.decode("a+b+c"))
    }

    @Test
    public fun testToString() {
        "http://www.klutter.uy".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080/".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080/path/to/somewhere".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://www.klutter.uy:8080?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy/".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy/path/to/somewhere".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080/".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080/path/to/somewhere".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy:8080?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy/".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy/path/to/somewhere".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy#fraggy".let { assertEquals(it, buildUri(it).toString()) }
        "http://user:pass@www.klutter.uy?a=1#fraggy".let { assertEquals(it, buildUri(it).toString()) }

        "https://www.klutter.uy".let { assertEquals(it, buildUri(it).toString()) }

        "file:/something/on/my/drive".let { assertEquals(it, buildUri(it).toString()) }

        // TODO: is this ok?  because we render "file:/something/on/my/drive"
        // "file:///something/on/my/drive".let { assertEquals(it, buildUri(it).toString()) }
    }

    @Test public fun testDollar() {
        val dollar = '$'

        val x1 = "\$100.00"
        val x2 = "${"$"}100.00"
        val x3 = """${"$"}100.00"""
        val x4 = "${dollar}100.00"
        val x5 = """${dollar}100.00"""

        assertEquals(x5, x1)
        assertEquals(x5, x2)
        assertEquals(x5, x3)
        assertEquals(x5, x4)

        // you cannot escape in """ strings, therefore:

        val odd = """\$100.00""" // creates "\$100.00" instead of "$100.00"
        // assertEquals(x5, odd) would fail
    }
}


