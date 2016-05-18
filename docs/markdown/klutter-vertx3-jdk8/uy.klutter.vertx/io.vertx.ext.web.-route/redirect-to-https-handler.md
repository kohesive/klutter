[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Route](index.md) / [redirectToHttpsHandler](.)


# redirectToHttpsHandler

`fun Route.redirectToHttpsHandler(httpsPort:&nbsp;Int&nbsp;=&nbsp;443, redirectCode:&nbsp;Int&nbsp;=&nbsp;302): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L139)

If a request is not HTTPS, reroute it to the same request as HTTPS


### Parameters

`httpsPort` - optional port for https, defaults to 443

`redirectCode` - optional redirect HTTP status code, defauts to 302


