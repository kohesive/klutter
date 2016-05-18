package uy.klutter.vertx

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.Session
import uy.klutter.core.jdk.mustStartWith
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
private fun pathPlusParmsOfUrl(original: URI): String {
    val path = original.getRawPath().let { if (it.isNullOrBlank()) "" else it.mustStartWith('/') }
    val query = original.getRawQuery().let { if (it.isNullOrBlank()) "" else it.mustStartWith('?') }
    val fragment = original.getRawFragment().let { if (it.isNullOrBlank()) "" else it.mustStartWith('#')}
    return "$path$query$fragment"
}

/**
 * Return the current routed URL as fully qualified externally accessible URL.  This takes into account proxy and
 * load balancer headers.
 *     X-Forwarded-Proto
 *     X-Forwarded-Host
 *     X-Forwarded-Port
 */
fun RoutingContext.externalizeUrl(): String {
    return externalizeUrl(pathPlusParmsOfUrl(URI(request().absoluteURI())))
}

/**
 * Return the specified URL as fully qualified external accessible URL.  This will substitute the values of proxy/load
 * balancer using headers:
 *     X-Forwarded-Proto
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
fun RoutingContext.externalizeUrl(url: String): String {
    val requestUri: URI = URI(request().absoluteURI()) // fallback values for scheme/host/port  ... and for relative paths

    val requestScheme: String = run {
        return@run request().getHeader("X-Forwarded-Proto").let { scheme: String? ->
            if (scheme == null || scheme.isEmpty()) {
                requestUri.getScheme()
            } else {
                scheme
            }
        }
    }

    val requestHost: String = run {
        return@run request().getHeader("X-Forwarded-Host").let inner@ { host: String? ->
            val hostWithPossiblePort = if (host == null || host.isEmpty()) {
                requestUri.getHost()
            } else {
                host
            }

            return@inner hostWithPossiblePort.substringBefore(':')
        }
    }


    val requestPort = run {
        val defaultPort: Int = requestUri.getPort().let inner@ { explicitPort ->
            return@inner if (explicitPort == 0) {
                if ("https" == requestScheme) 443 else 80
            } else {
                explicitPort
            }
        }

        return@run request().getHeader("X-Forwarded-Port").let inner@ { proxyOrLoadBalancerPort ->
            val finalPort: Int = if (proxyOrLoadBalancerPort.isNullOrBlank()) {
                defaultPort
            } else {
                proxyOrLoadBalancerPort.toInt()
            }

            return@inner if (requestScheme == "https" && finalPort == 443) {
                ""
            } else if (requestScheme == "http" && finalPort == 80) {
                ""
            } else {
                ":$finalPort"
            }
        }
    }

    val finalUrl = when {
        url.startsWith("http://") || url.startsWith("https://") -> url
        url.startsWith("//") -> "$requestScheme://$url"
        url.startsWith("/") ->"$requestScheme://$requestHost$requestPort$url"
        else -> {
            val newUri = requestUri.resolve(url)
            val restOfUrl = pathPlusParmsOfUrl(newUri)
            "$requestScheme://$requestHost$requestPort$restOfUrl"
        }
    }
    return finalUrl
}

/**
 * If a request is not HTTPS, reroute it to the same request as HTTPS
 *
 * @param httpsPort optional port for https, defaults to 443
 * @param redirectCode optional redirect HTTP status code, defauts to 302
 */
fun Route.redirectToHttpsHandler(httpsPort: Int = 443, redirectCode: Int = 302) {
    handler { context ->
        if (context.request().isSSL) {
            // if not behind load balancer, we would see this, otherwise won't.
            context.next()
        } else {
            if (context.request().getHeader("X-Forwarded-Proto") == "https") {
                context.next()
            } else {
                try {
                    val myOriginalUrl = context.externalizeUrl()
                    val myNewUrl = uy.klutter.core.uri.buildUri(myOriginalUrl).scheme("https").port(httpsPort).toString()
                    context.response().putHeader("location", myNewUrl).setStatusCode(redirectCode).end()
                } catch (ex: Throwable) {
                    context.next()
                }
            }
        }
    }
}

