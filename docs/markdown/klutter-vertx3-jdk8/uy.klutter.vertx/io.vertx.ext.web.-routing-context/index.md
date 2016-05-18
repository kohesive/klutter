[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.RoutingContext](.)


### Extensions for io.vertx.ext.web.RoutingContext


| [externalizeUrl](externalize-url.md) | `fun RoutingContext.externalizeUrl(): String`
Return the current routed URL as fully qualified externally accessible URL.  This takes into account proxy and
load balancer headers.
X-Forwarded-Proto
X-Forwarded-Host
X-Forwarded-Port

`fun RoutingContext.externalizeUrl(url:&nbsp;String): String`
Return the specified URL as fully qualified external accessible URL.  This will substitute the values of proxy/load
balancer using headers:
X-Forwarded-Proto
X-Forwarded-Host
X-Forwarded-Port

 |

