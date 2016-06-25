[uy.klutter.vertx](../index.md) / [VertxInit](.)


# VertxInit
`object VertxInit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Vertx.kt#L285)

Setup Kovenant do dispatch via Vert.x, and ensure the Vert.x Jackson object mapper is setup for Kotlin and JDK 8 types.

All promise verison of startup methods ensure this object is initialized before continuing.  If you are starting Vert.x
in some other way, you should call [`VertxInit.ensure()`](ensure.md) before doing anything with Vert.x + Kovenant
together.  Calling the method more than once does nothing, and is not harmful (it only causes the class to load if not loaded).





### Properties

|&nbsp;|&nbsp;|
|---|---|
| [fallbackContext](fallback-context.md) | `val fallbackContext: Context` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [ensure](ensure.md) | `fun ensure(): Unit`<p>Be sure that Kovenant and Vert.x are initialized in a way that they cooperate and work together.</p> |
