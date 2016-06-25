[uy.klutter.config.typesafe](../index.md) / [KonfigModule](.)


# KonfigModule
<code>interface KonfigModule</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L109)<br/>
A package of configuration bound items that can be included into a scope of someone else



### Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerConfigurables](register-configurables.md) | <code>abstract fun [KonfigRegistrar](../-konfig-registrar/index.md).registerConfigurables(): Unit</code><br/> |
| [registerWith](register-with.md) | <code>open fun registerWith(intoModule: [KonfigRegistrar](../-konfig-registrar/index.md)): Unit</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [KonfigAndInjektScopedMain](../-konfig-and-injekt-scoped-main/index.md) | <code>abstract class KonfigAndInjektScopedMain : InjektModule, KonfigModule</code><br/>A startup module that registers and uses singletons/object factories from a specific scope,<br/>and an ObjectMapper to bind configuration properties into class instances. |
