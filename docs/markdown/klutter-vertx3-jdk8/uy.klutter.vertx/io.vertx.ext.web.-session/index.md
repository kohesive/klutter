[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Session](.)


### Extensions for io.vertx.ext.web.Session

|&nbsp;|&nbsp;|
|---|---|
| [putSafely](put-safely.md) | `fun Session.putSafely(key:&nbsp;String, value:&nbsp;Any?): Unit`<p>Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls)</p> |
| [removeSafely](remove-safely.md) | `fun Session.removeSafely(key:&nbsp;String): Any?`<p>Here for balance with putSafely</p> |
