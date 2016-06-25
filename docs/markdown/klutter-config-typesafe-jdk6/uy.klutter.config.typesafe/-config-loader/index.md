[uy.klutter.config.typesafe](../index.md) / [ConfigLoader](.)


# ConfigLoader
<code>abstract class ConfigLoader</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L59)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ConfigLoader()</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](after-attached.md) | <code>open fun afterAttached(fullConfig: Config): Unit</code><br/> |
| [load](load.md) | <code>abstract fun load(): Config</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [ApplicationConfig](../-application-config/index.md) | <code>class ApplicationConfig : ConfigLoader</code><br/> |
| [ClassResourceConfig](../-class-resource-config/index.md) | <code>class ClassResourceConfig : ConfigLoader</code><br/> |
| [EnvironmentVariablesConfig](../-environment-variables-config/index.md) | <code>class EnvironmentVariablesConfig : ConfigLoader</code><br/> |
| [FileConfig](../-file-config/index.md) | <code>open class FileConfig : ConfigLoader</code><br/> |
| [MapAsConfig](../-map-as-config/index.md) | <code>class MapAsConfig : ConfigLoader</code><br/> |
| [PropertiesAsConfig](../-properties-as-config/index.md) | <code>class PropertiesAsConfig : ConfigLoader</code><br/> |
| [ReaderConfig](../-reader-config/index.md) | <code>class ReaderConfig : ConfigLoader</code><br/> |
| [ReferenceConfig](../-reference-config/index.md) | <code>class ReferenceConfig : ConfigLoader</code><br/> |
| [ResolveConfig](../-resolve-config/index.md) | <code>class ResolveConfig : ConfigLoader</code><br/> |
| [ResourceConfig](../-resource-config/index.md) | <code>class ResourceConfig : ConfigLoader</code><br/> |
| [StringAsConfig](../-string-as-config/index.md) | <code>class StringAsConfig : ConfigLoader</code><br/> |
| [SystemPropertiesConfig](../-system-properties-config/index.md) | <code>class SystemPropertiesConfig : ConfigLoader</code><br/> |
| [UrlConfig](../-url-config/index.md) | <code>class UrlConfig : ConfigLoader</code><br/> |
