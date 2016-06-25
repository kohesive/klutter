[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.RoutingContext](.)


### Extensions for io.vertx.ext.web.RoutingContext

|&nbsp;|&nbsp;|
|---|---|
| [externalizeUrl](externalize-url.md) | <code>fun RoutingContext.externalizeUrl(): String</code><br/>Return the current routed URL as fully qualified externally accessible URL.  This takes into account proxy and<br/>load balancer headers.<br/>X-Forwarded-Proto<br/>X-Forwarded-Host<br/>X-Forwarded-Port<code>fun RoutingContext.externalizeUrl(url: String): String</code><br/>Return the specified URL as fully qualified external accessible URL.  This will substitute the values of proxy/load<br/>balancer using headers:<br/>X-Forwarded-Proto<br/>X-Forwarded-Host<br/>X-Forwarded-Port |
