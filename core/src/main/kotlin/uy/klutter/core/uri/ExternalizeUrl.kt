package uy.klutter.core.uri

import uy.klutter.core.common.mustStartWith
import java.net.URI

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
fun externalizeURI(requestUri: URI, resolveUrl: String, headers: Map<String, String>): URI {
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
 * Extract unencoded path?query#hash from URL and return as a string.
 *
 * for example `http://www.klutter.uy/path1/path2?parm=123#fraggy`
 * would return only `path1/path2?parm=123#fraggy`
 *
 */
fun URI.pathPlusParmsOfUrl(): String {
    val path = this.getRawPath().let { if (it.isNullOrBlank()) "" else it.mustStartWith('/') }
    val query = this.getRawQuery().let { if (it.isNullOrBlank()) "" else it.mustStartWith('?') }
    val fragment = this.getRawFragment().let { if (it.isNullOrBlank()) "" else it.mustStartWith('#') }
    return "$path$query$fragment"
}

/**
 * Divide a host and port, both for ipv4 and ipv6
 * return null for absent port
 */
fun dividePort(hostWithOptionalPort: String): Pair<String, String?> {
    val parts = if (hostWithOptionalPort.startsWith('[')) { // ipv6
        Pair(hostWithOptionalPort.substringBefore(']') + ']', hostWithOptionalPort.substringAfter("]:", ""))
    } else { // ipv4
        Pair(hostWithOptionalPort.substringBefore(':'), hostWithOptionalPort.substringAfter(':', ""))
    }
    return Pair(parts.first, if (parts.second.isNullOrBlank()) null else parts.second)
}