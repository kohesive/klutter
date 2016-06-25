[uy.klutter.config.typesafe](../index.md) / [KonfigAndInjektMain](.)


# KonfigAndInjektMain
<code>abstract class KonfigAndInjektMain : [KonfigAndInjektScopedMain](../-konfig-and-injekt-scoped-main/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L15)<br/>
A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>KonfigAndInjektMain()</code><br/>A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [mapper](../-konfig-and-injekt-scoped-main/mapper.md) | <code>val mapper: ObjectMapper</code><br/> |
| [resolvedConfig](../-konfig-and-injekt-scoped-main/resolved-config.md) | <code>var resolvedConfig: Config</code><br/> |
| [scope](../-konfig-and-injekt-scoped-main/scope.md) | <code>val scope: InjektScope</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [configFactory](../-konfig-and-injekt-scoped-main/config-factory.md) | <code>abstract fun configFactory(): Config</code><br/> |
