[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Session](index.md) / [putSafely](.)


# putSafely
`fun Session.putSafely(key:&nbsp;String, value:&nbsp;Any?): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxWeb.kt#L13)

Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls)


