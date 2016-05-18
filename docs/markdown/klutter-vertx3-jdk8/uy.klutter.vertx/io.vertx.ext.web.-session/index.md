[uy.klutter.vertx](../index.md) / [io.vertx.ext.web.Session](.)


### Extensions for io.vertx.ext.web.Session


| [putSafely](put-safely.md) | `fun Session.putSafely(key:&nbsp;String, value:&nbsp;Any?): Unit`
Put values into session, but nulls act as removes (in Vert.x clustered, sometimes this causes a failure to put nulls)

 |
| [removeSafely](remove-safely.md) | `fun Session.removeSafely(key:&nbsp;String): Any?`
Here for balance with putSafely

 |

