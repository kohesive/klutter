[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Route](index.md) / [redirectToHttpsHandler](.)


# redirectToHttpsHandler
<code>fun Route.redirectToHttpsHandler(httpsPort: Int = 443, redirectCode: Int = 302): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L139)<br/>
If a request is not HTTPS, reroute it to the same request as HTTPS

### Parameters
`httpsPort` - optional port for https, defaults to 443

`redirectCode` - optional redirect HTTP status code, defauts to 302


