[uy.klutter.config.typesafe](../index.md) / [KonfigAndInjektMain](.)


# KonfigAndInjektMain
`abstract class KonfigAndInjektMain&nbsp;:&nbsp;[KonfigAndInjektScopedMain](../-konfig-and-injekt-scoped-main/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L15)

A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `KonfigAndInjektMain()`<p>A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder</p> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [mapper](../-konfig-and-injekt-scoped-main/mapper.md) | `val mapper: ObjectMapper` |
| [resolvedConfig](../-konfig-and-injekt-scoped-main/resolved-config.md) | `var resolvedConfig: Config` |
| [scope](../-konfig-and-injekt-scoped-main/scope.md) | `val scope: InjektScope` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [configFactory](../-konfig-and-injekt-scoped-main/config-factory.md) | `abstract fun configFactory(): Config` |
