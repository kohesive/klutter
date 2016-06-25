[uy.klutter.vertx](../index.md) / [VertxInjektables](.)


# VertxInjektables
`object VertxInjektables&nbsp;:&nbsp;[VertxInjektModule](../-vertx-injekt-module/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Injektable.kt#L15)

This Injekt Module does a few things:

Make sure Vertx is setup to work with Kovenant for promises
Add a singleton for Jackson object mapper sharing it with the Vertx singleton
Setup logger to use the Vertx logging so logging is consistent with vertx





### Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerInjectables](register-injectables.md) | `fun InjektRegistrar.registerInjectables(): Unit` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [common](../-vertx-injekt-module/common.md) | `fun InjektRegistrar.common(): Unit` |
