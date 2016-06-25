[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Session](.)


### Extensions for io.vertx.ext.web.Session

|&nbsp;|&nbsp;|
|---|---|
| [putSafely](put-safely.md) | <code>fun Session.putSafely(key: String, value: Any?): Unit</code><br/>Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls) |
| [removeSafely](remove-safely.md) | <code>fun Session.removeSafely(key: String): Any?</code><br/>Here for balance with putSafely |
