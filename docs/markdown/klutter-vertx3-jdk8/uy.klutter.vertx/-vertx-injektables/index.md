[uy.klutter.vertx](../index.md) / [VertxInjektables](.)


# VertxInjektables
<code>object VertxInjektables : [VertxInjektModule](../-vertx-injekt-module/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Injektable.kt#L15)<br/>
This Injekt Module does a few things:

Make sure Vertx is setup to work with Kovenant for promises
Add a singleton for Jackson object mapper sharing it with the Vertx singleton
Setup logger to use the Vertx logging so logging is consistent with vertx





### Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerInjectables](register-injectables.md) | <code>fun InjektRegistrar.registerInjectables(): Unit</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [common](../-vertx-injekt-module/common.md) | <code>fun InjektRegistrar.common(): Unit</code><br/> |
