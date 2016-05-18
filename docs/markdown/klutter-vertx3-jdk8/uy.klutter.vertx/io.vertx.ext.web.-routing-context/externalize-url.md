[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.RoutingContext](index.md) / [externalizeUrl](.)


# externalizeUrl

`fun RoutingContext.externalizeUrl(): String` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L49)

Return the current routed URL as fully qualified externally accessible URL.  This takes into account proxy and
load balancer headers.
X-Forwarded-Proto
X-Forwarded-Host
X-Forwarded-Port



`fun RoutingContext.externalizeUrl(url:&nbsp;String): String` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L68)

Return the specified URL as fully qualified external accessible URL.  This will substitute the values of proxy/load
balancer using headers:
X-Forwarded-Proto
X-Forwarded-Host
X-Forwarded-Port


This works with URLs that are:

* 
partial without host, for example "/something/here" or "under/me"  resolving to this servers values

* 
partial with host/port, for example "//somehost.com:8983/solr" would add the same scheme (http/https) as this servers to match

* 
full, URLs that are fully qualified are retured untouched, so they are safe to pass to this function ("http://...", "https://...")



Urls that will cause unknown results, those of the form "somehost.com:8983/solr" might be treated as relative paths on current server





