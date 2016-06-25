[uy.klutter.vertx](../index.md) / [VertxInjektModule](.)


# VertxInjektModule
<code>abstract class VertxInjektModule : InjektModule</code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Injektable.kt#L33)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>VertxInjektModule()</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [common](common.md) | <code>fun InjektRegistrar.common(): Unit</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [VertxInjektables](../-vertx-injektables/index.md) | <code>object VertxInjektables : VertxInjektModule</code><br/>This Injekt Module does a few things: |
| [VertxWithSlf4jInjektables](../-vertx-with-slf4j-injektables/index.md) | <code>object VertxWithSlf4jInjektables : VertxInjektModule</code><br/>Same as VertxInjektables but routes Vert.x and Hazelcast logging facades to use Slf4j, and app directly to Slf4j |
