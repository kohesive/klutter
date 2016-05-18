[uy.klutter.config.typesafe](.)


## Package uy.klutter.config.typesafe


### Types


| [ApplicationConfig](-application-config/index.md) | `class ApplicationConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ClassResourceConfig](-class-resource-config/index.md) | `class ClassResourceConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ConfigLoader](-config-loader/index.md) | `abstract class ConfigLoader` |
| [ConfiguredValue](-configured-value/index.md) | `class ConfiguredValue`
Intermediate object for providing additional functionality on a configured item

 |
| [EnvironmentVariablesConfig](-environment-variables-config/index.md) | `class EnvironmentVariablesConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [FileConfig](-file-config/index.md) | `open class FileConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [KonfigAndInjektMain](-konfig-and-injekt-main/index.md) | `abstract class KonfigAndInjektMain&nbsp;:&nbsp;[KonfigAndInjektScopedMain](-konfig-and-injekt-scoped-main/index.md)`
A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder

 |
| [KonfigAndInjektScopedMain](-konfig-and-injekt-scoped-main/index.md) | `abstract class KonfigAndInjektScopedMain&nbsp;:&nbsp;InjektModule, [KonfigModule](-konfig-module/index.md)`
A startup module that registers and uses singletons/object factories from a specific scope,
and an ObjectMapper to bind configuration properties into class instances.

 |
| [KonfigModule](-konfig-module/index.md) | `interface KonfigModule`
A package of configuration bound items that can be included into a scope of someone else

 |
| [KonfigRegistrar](-konfig-registrar/index.md) | `interface KonfigRegistrar&nbsp;:&nbsp;InjektRegistrar` |
| [MapAsConfig](-map-as-config/index.md) | `class MapAsConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [PropertiesAsConfig](-properties-as-config/index.md) | `class PropertiesAsConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ReaderConfig](-reader-config/index.md) | `class ReaderConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ReferenceConfig](-reference-config/index.md) | `class ReferenceConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ResolveConfig](-resolve-config/index.md) | `class ResolveConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [ResourceConfig](-resource-config/index.md) | `class ResourceConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [StringAsConfig](-string-as-config/index.md) | `class StringAsConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [SystemPropertiesConfig](-system-properties-config/index.md) | `class SystemPropertiesConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |
| [UrlConfig](-url-config/index.md) | `class UrlConfig&nbsp;:&nbsp;[ConfigLoader](-config-loader/index.md)` |


### Extensions for External Classes


| [com.typesafe.config.Config](com.typesafe.config.-config/index.md) |  |
| [com.typesafe.config.ConfigObject](com.typesafe.config.-config-object/index.md) |  |


### Functions


| [bindClassAtConfigPath](bind-class-at-config-path.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigPath(configPath:&nbsp;String): Unit`
`fun &lt;T&nbsp;:&nbsp;Any&gt; [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigPath(configPath:&nbsp;String, klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit`
bind a class bindings its values from a configuration path immediately

 |
| [bindClassAtConfigRoot](bind-class-at-config-root.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigRoot(): Unit`
`fun &lt;T&nbsp;:&nbsp;Any&gt; [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigRoot(klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit`
bind a class bindings its values from the root of the current configuration path immediately

 |
| [loadApplicationConfig](load-application-config.md) | `fun loadApplicationConfig(): Config`
Mimics the behavior of ConfigFactory.load() or ConfigFactory.defaultApplication()

 |
| [loadConfig](load-config.md) | `fun loadConfig(vararg loaders:&nbsp;[ConfigLoader](-config-loader/index.md)): Config`
Load a configuration chain, in the order of priority with NO default behavior (i.e. no environment vars, reference conf loading, nor system props)

 |

