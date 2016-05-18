[uy.klutter.vertx](../index.md) / [VertxInjektModule](.)


# VertxInjektModule

`abstract class VertxInjektModule&nbsp;:&nbsp;InjektModule` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/Injektable.kt#L33)



### Constructors


| [&lt;init&gt;](-init-.md) | `VertxInjektModule()` |


### Functions


| [common](common.md) | `fun InjektRegistrar.common(): Unit` |


### Inheritors


| [VertxInjektables](../-vertx-injektables/index.md) | `object VertxInjektables&nbsp;:&nbsp;VertxInjektModule`
This Injekt Module does a few things:

 |
| [VertxWithSlf4jInjektables](../-vertx-with-slf4j-injektables/index.md) | `object VertxWithSlf4jInjektables&nbsp;:&nbsp;VertxInjektModule`
Same as VertxInjektables but routes Vert.x and Hazelcast logging facades to use Slf4j, and app directly to Slf4j

 |

