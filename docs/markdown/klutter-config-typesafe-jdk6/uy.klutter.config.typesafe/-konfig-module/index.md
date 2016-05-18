[uy.klutter.config.typesafe](../index.md) / [KonfigModule](.)


# KonfigModule

`interface KonfigModule` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L109)

A package of configuration bound items that can be included into a scope of someone else




### Functions


| [registerConfigurables](register-configurables.md) | `abstract fun [KonfigRegistrar](../-konfig-registrar/index.md).registerConfigurables(): Unit` |
| [registerWith](register-with.md) | `open fun registerWith(intoModule:&nbsp;[KonfigRegistrar](../-konfig-registrar/index.md)): Unit` |


### Inheritors


| [KonfigAndInjektScopedMain](../-konfig-and-injekt-scoped-main/index.md) | `abstract class KonfigAndInjektScopedMain&nbsp;:&nbsp;InjektModule, KonfigModule`
A startup module that registers and uses singletons/object factories from a specific scope,
and an ObjectMapper to bind configuration properties into class instances.

 |

