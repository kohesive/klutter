[uy.klutter.config.typesafe](../index.md) / [ConfigLoader](.)


# ConfigLoader
`abstract class ConfigLoader` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L59)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ConfigLoader()` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](after-attached.md) | `open fun afterAttached(fullConfig:&nbsp;Config): Unit` |
| [load](load.md) | `abstract fun load(): Config` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [ApplicationConfig](../-application-config/index.md) | `class ApplicationConfig&nbsp;:&nbsp;ConfigLoader` |
| [ClassResourceConfig](../-class-resource-config/index.md) | `class ClassResourceConfig&nbsp;:&nbsp;ConfigLoader` |
| [EnvironmentVariablesConfig](../-environment-variables-config/index.md) | `class EnvironmentVariablesConfig&nbsp;:&nbsp;ConfigLoader` |
| [FileConfig](../-file-config/index.md) | `open class FileConfig&nbsp;:&nbsp;ConfigLoader` |
| [MapAsConfig](../-map-as-config/index.md) | `class MapAsConfig&nbsp;:&nbsp;ConfigLoader` |
| [PropertiesAsConfig](../-properties-as-config/index.md) | `class PropertiesAsConfig&nbsp;:&nbsp;ConfigLoader` |
| [ReaderConfig](../-reader-config/index.md) | `class ReaderConfig&nbsp;:&nbsp;ConfigLoader` |
| [ReferenceConfig](../-reference-config/index.md) | `class ReferenceConfig&nbsp;:&nbsp;ConfigLoader` |
| [ResolveConfig](../-resolve-config/index.md) | `class ResolveConfig&nbsp;:&nbsp;ConfigLoader` |
| [ResourceConfig](../-resource-config/index.md) | `class ResourceConfig&nbsp;:&nbsp;ConfigLoader` |
| [StringAsConfig](../-string-as-config/index.md) | `class StringAsConfig&nbsp;:&nbsp;ConfigLoader` |
| [SystemPropertiesConfig](../-system-properties-config/index.md) | `class SystemPropertiesConfig&nbsp;:&nbsp;ConfigLoader` |
| [UrlConfig](../-url-config/index.md) | `class UrlConfig&nbsp;:&nbsp;ConfigLoader` |
