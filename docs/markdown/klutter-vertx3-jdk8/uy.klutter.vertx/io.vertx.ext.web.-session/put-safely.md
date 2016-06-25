[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Session](index.md) / [putSafely](.)


# putSafely
<code>fun Session.putSafely(key: String, value: Any?): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L13)<br/>
Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls)


