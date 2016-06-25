[uy.klutter.config.typesafe](../index.md) / [KonfigAndInjektScopedMain](.)


# KonfigAndInjektScopedMain
`abstract class KonfigAndInjektScopedMain&nbsp;:&nbsp;InjektModule, [KonfigModule](../-konfig-module/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L21)

A startup module that registers and uses singletons/object factories from a specific scope,
and an ObjectMapper to bind configuration properties into class instances.



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `KonfigAndInjektScopedMain(scope:&nbsp;InjektScope, mapper:&nbsp;ObjectMapper&nbsp;=&nbsp;jacksonObjectMapper())`<p>A startup module that registers and uses singletons/object factories from a specific scope,<br/>and an ObjectMapper to bind configuration properties into class instances.</p> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [mapper](mapper.md) | `val mapper: ObjectMapper` |
| [resolvedConfig](resolved-config.md) | `var resolvedConfig: Config` |
| [scope](scope.md) | `val scope: InjektScope` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [configFactory](config-factory.md) | `abstract fun configFactory(): Config` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerConfigurables](../-konfig-module/register-configurables.md) | `abstract fun [KonfigRegistrar](../-konfig-registrar/index.md).registerConfigurables(): Unit` |
| [registerWith](../-konfig-module/register-with.md) | `open fun registerWith(intoModule:&nbsp;[KonfigRegistrar](../-konfig-registrar/index.md)): Unit` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [KonfigAndInjektMain](../-konfig-and-injekt-main/index.md) | `abstract class KonfigAndInjektMain&nbsp;:&nbsp;KonfigAndInjektScopedMain`<p>A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder</p> |
