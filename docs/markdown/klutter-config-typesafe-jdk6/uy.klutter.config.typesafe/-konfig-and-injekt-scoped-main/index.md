[uy.klutter.config.typesafe](../index.md) / [KonfigAndInjektScopedMain](.)


# KonfigAndInjektScopedMain
<code>abstract class KonfigAndInjektScopedMain : InjektModule, [KonfigModule](../-konfig-module/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L21)<br/>
A startup module that registers and uses singletons/object factories from a specific scope,
and an ObjectMapper to bind configuration properties into class instances.



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>KonfigAndInjektScopedMain(scope: InjektScope, mapper: ObjectMapper = jacksonObjectMapper())</code><br/>A startup module that registers and uses singletons/object factories from a specific scope,<br/>and an ObjectMapper to bind configuration properties into class instances. |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [mapper](mapper.md) | <code>val mapper: ObjectMapper</code><br/> |
| [resolvedConfig](resolved-config.md) | <code>var resolvedConfig: Config</code><br/> |
| [scope](scope.md) | <code>val scope: InjektScope</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [configFactory](config-factory.md) | <code>abstract fun configFactory(): Config</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerConfigurables](../-konfig-module/register-configurables.md) | <code>abstract fun [KonfigRegistrar](../-konfig-registrar/index.md).registerConfigurables(): Unit</code><br/> |
| [registerWith](../-konfig-module/register-with.md) | <code>open fun registerWith(intoModule: [KonfigRegistrar](../-konfig-registrar/index.md)): Unit</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [KonfigAndInjektMain](../-konfig-and-injekt-main/index.md) | <code>abstract class KonfigAndInjektMain : KonfigAndInjektScopedMain</code><br/>A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder |
