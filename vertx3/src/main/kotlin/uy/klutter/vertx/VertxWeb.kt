package uy.klutter.vertx

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.Session
import uy.klutter.core.common.mustStartWith
import uy.klutter.core.uri.buildUri
import java.net.URI


/**
 * Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls)
 */
fun Session.putSafely(key: String, value: Any?) {
    if (value == null) {
        remove(key)
    } else {
        put(key, value)
    }
}

/**
 * Here for balance with putSafely
 */
fun Session.removeSafely(key: String): Any? {
    return remove<Any?>(key)
}

/**
 * Extract unencoded path?query#hash from URL and return as a string.
 *
 * for example `http://www.klutter.uy/path1/path2?parm=123#fraggy`
 * would return only `path1/path2?parm=123#fraggy`
 *
 */
internal fun URI.pathPlusParmsOfUrl(): String {
    val path = this.getRawPath().let { if (it.isNullOrBlank()) "" else it.mustStartWith('/') }
    val query = this.getRawQuery().let { if (it.isNullOrBlank()) "" else it.mustStartWith('?') }
    val fragment = this.getRawFragment().let { if (it.isNullOrBlank()) "" else it.mustStartWith('#') }
    return "$path$query$fragment"
}

/**
 * Divide a host and port, both for ipv4 and ipv6
 * return null for absent port
 */
internal fun dividePort(hostWithOptionalPort: String): Pair<String, String?> {
    val parts = if (hostWithOptionalPort.startsWith('[')) { // ipv6
        Pair(hostWithOptionalPort.substringBefore(']') + ']', hostWithOptionalPort.substringAfter("]:", ""))
    } else { // ipv4
        Pair(hostWithOptionalPort.substringBefore(':'), hostWithOptionalPort.substringAfter(':', ""))
    }
    return Pair(parts.first, if (parts.second.isNullOrBlank()) null else parts.second)
}

/**
 * Return the current routed URL as fully qualified externally accessible URL.  This takes into account proxy and
 * load balancer headers.
 *     X-Forwarded-Proto (or X-Forwarded-Scheme: https)
 *     X-Forwarded-Host
 *     X-Forwarded-Port
 */
fun RoutingContext.externalizeUrl(): String {
    return externalizeUrl(URI(request().absoluteURI()).pathPlusParmsOfUrl())
}

fun RoutingContext.externalizeUrlToUri(): URI {
    return externalizeUrlToUri(URI(request().absoluteURI()).pathPlusParmsOfUrl())
}

/**
 * Return the specified URL as fully qualified external accessible URL.  This will substitute the values of proxy/load
 * balancer using headers:
 *     X-Forwarded-Proto (or X-Forwarded-Scheme: https)
 *     X-Forwarded-Host
 *     X-Forwarded-Port
 *
 * This works with URL's that are:
 *
 * - partial without host, for example "/something/here" or "under/me"  resolving to this server's values
 * - partial with host/port, for example "//somehost.com:8983/solr" would add the same scheme (http/https) as this server's to match
 * - full, URL's that are fully qualified are retured untouched, so they are safe to pass to this function ("http://...", "https://...")
 *
 * Url's that will cause unknown results, those of the form "somehost.com:8983/solr" might be treated as relative paths on current server
 */
fun RoutingContext.externalizeUrl(resolveUrl: String): String {
    return externalizeUrlToUri(resolveUrl).toString()
}

fun RoutingContext.externalizeUrlToUri(resolveUrl: String): URI {
    val cleanHeaders = request().headers().filter { it.value.isNullOrBlank() }
            .map { it.key to it.value }.toMap()
    return externalizeURI(URI(request().absoluteURI()), resolveUrl, cleanHeaders)
}

/**
 * Internal externalizer, easier to test without mocking RoutingContext and the headers map is already cleaned up
 */
internal fun externalizeURI(requestUri: URI, resolveUrl: String, headers: Map<String, String>): URI {
    // special case of not touching fully qualified resolve URL's
    if (resolveUrl.startsWith("http://") || resolveUrl.startsWith("https://")) return URI(resolveUrl)

    val forwardedScheme = headers.get("X-Forwarded-Proto")
            ?: headers.get("X-Forwarded-Scheme")
            ?: requestUri.getScheme()

    // special case of //host/something URL's
    if (resolveUrl.startsWith("//")) return URI("$forwardedScheme:$resolveUrl")

    val (forwardedHost, forwardedHostOptionalPort) =
            dividePort(headers.get("X-Forwarded-Host") ?: requestUri.getHost())

    val fallbackPort = requestUri.getPort().let { explicitPort ->
        if (explicitPort <= 0) {
            if ("https" == forwardedScheme) 443 else 80
        } else {
            explicitPort
        }
    }
    val requestPort = headers.get("X-Forwarded-Port")?.toInt()
            ?: forwardedHostOptionalPort
            ?: fallbackPort
    val finalPort = when {
        forwardedScheme == "https" && requestPort == 443 -> ""
        forwardedScheme == "http" && requestPort == 80 -> ""
        else -> ":$requestPort"
    }

    val restOfUrl = requestUri.pathPlusParmsOfUrl()
    return URI("$forwardedScheme://$forwardedHost$finalPort$restOfUrl").resolve(resolveUrl)
}

/**
 * If a request is not HTTPS, reroute it to the same request as HTTPS
 *
 * @param httpsPort optional port for https, defaults to 443
 * @param redirectCode optional redirect HTTP status code, defauts to 302
 * @param failOnUrlBuilding if there are errors for URI class parsing the URL, should it continue handling or fail the request
 */
fun Route.redirectToHttpsHandler(httpsPort: Int = 443, redirectCode: Int = 302, failOnUrlBuilding: Boolean = true) {
    handler { context ->
        val proto = context.request().getHeader("X-Forwarded-Proto")
                ?: context.request().getHeader("X-Forwarded-Scheme")
        if (proto == "https") {
            context.next()
        } else if (proto.isNullOrBlank() && context.request().isSSL) {
            context.next()
        } else {
            try {
                val myPublicUri = context.externalizeUrl()
                val myHttpsPublicUri = buildUri(myPublicUri).scheme("https").port(httpsPort).toString()
                context.response().putHeader("location", myHttpsPublicUri).setStatusCode(redirectCode).end()
            } catch (ex: Throwable) {
                if (failOnUrlBuilding) context.fail(ex)
                else context.next()
            }
        }
    }
}
