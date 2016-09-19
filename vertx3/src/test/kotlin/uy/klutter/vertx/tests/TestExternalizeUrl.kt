package uy.klutter.vertx.tests

import org.junit.Test
import uy.klutter.vertx.dividePort
import uy.klutter.vertx.externalizeURI
import uy.klutter.vertx.pathPlusParmsOfUrl
import java.net.URI
import kotlin.test.assertEquals

class TestExternalizeUrl {
    private fun checkNoHeaders(requestUrl: String, resolveUrl: String) =
            externalizeURI(URI(requestUrl), resolveUrl, emptyMap()).toString()

    private fun checkWithHeaders(requestUrl: String, resolveUrl: String, headers: Map<String, String>) =
            externalizeURI(URI(requestUrl), resolveUrl, headers).toString()


    @Test
    fun testUrlsWithNoForwardHeaders() {
        assertEquals("http://www.jetbrains.com/kotlin",
                checkNoHeaders("https://something.com/nowhere", "http://www.jetbrains.com/kotlin"))

        assertEquals("http://www.jetbrains.com/kotlin",
                checkNoHeaders("http://www.jetbrains.com/csharp", "/kotlin"))

        assertEquals("http://www.jetbrains.com/kotlin",
                checkNoHeaders("http://www.jetbrains.com/csharp?q=searchy", "/kotlin"))

        assertEquals("http://www.jetbrains.com/kotlin",
                checkNoHeaders("http://www.jetbrains.com/csharp?q=searchy#fraggy", "/kotlin"))

        assertEquals("http://www.jetbrains.com/kotlin#someHash",
                checkNoHeaders("http://www.jetbrains.com/kotlin", "#someHash"))

        assertEquals("http://www.jetbrains.com/kotlin#someHash",
                checkNoHeaders("http://www.jetbrains.com/kotlin#oldHash", "#someHash"))

        assertEquals("http://www.jetbrains.com/kotlin/rocks",
                checkNoHeaders("http://www.jetbrains.com/kotlin", "/kotlin/rocks"))

        assertEquals("http://www.jetbrains.com/kotlin/rocks",
                checkNoHeaders("http://www.jetbrains.com/kotlin/explore", "rocks"))

        assertEquals("https://blog.jetbrains.com/kotlin",
                checkNoHeaders("https://www.jetbrains.com/csharp", "//blog.jetbrains.com/kotlin"))
    }

    @Test
    fun testRestOfUrl() {
        assertEquals("/path1/path2?parm=123#fraggy", URI("http://www.klutter.uy/path1/path2?parm=123#fraggy").pathPlusParmsOfUrl())
    }

    @Test
    fun testDivideHostAndPortIpv4() {
        assertEquals(Pair("www.ibm.com", null), dividePort("www.ibm.com"))
        assertEquals(Pair("www.ibm.com", "443"), dividePort("www.ibm.com:443"))
    }

    @Test
    fun testDivideHostAndPortIpv6() {
        assertEquals(Pair("[::1]", null), dividePort("[::1]"))
        assertEquals(Pair("[::1]", "80"), dividePort("[::1]:80"))
    }

    @Test
    fun testUrlsWithForwardHeaders() {
        assertEquals("https://www.jetbrains.com/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/nowhere", "kotlin",
                        mapOf("X-Forwarded-Proto" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com",
                                "X-Forwarded-Port" to "443")))

        assertEquals("https://www.jetbrains.com/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "/kotlin",
                        mapOf("X-Forwarded-Proto" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com",
                                "X-Forwarded-Port" to "443")))

        assertEquals("https://www.jetbrains.com/kotlin/stuff",
                checkWithHeaders("http://10.0.0.1:8080/kotlin/stuff/and/more/stuff", "/kotlin/stuff",
                        mapOf("X-Forwarded-Scheme" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com",
                                "X-Forwarded-Port" to "443")))

        assertEquals("https://www.jetbrains.com/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Proto" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com:443",
                                "X-Forwarded-Port" to "443")))

        assertEquals("https://www.jetbrains.com:8443/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Proto" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com",
                                "X-Forwarded-Port" to "8443")))

        assertEquals("https://www.jetbrains.com:8443/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Proto" to "https",
                                "X-Forwarded-Host" to "www.jetbrains.com:8443",
                                "X-Forwarded-Port" to "8443")))

        assertEquals("http://www.jetbrains.com/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Proto" to "http",
                                "X-Forwarded-Host" to "www.jetbrains.com",
                                "X-Forwarded-Port" to "80")))

        assertEquals("http://www.jetbrains.com:8080/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Proto" to "http",
                                "X-Forwarded-Host" to "www.jetbrains.com")))

        assertEquals("http://www.jetbrains.com:8080/kotlin",
                checkWithHeaders("http://10.0.0.1:8080/kotlin", "kotlin",
                        mapOf("X-Forwarded-Host" to "www.jetbrains.com")))

    }
}