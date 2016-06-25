[uy.klutter.config.typesafe](.)


## Package uy.klutter.config.typesafe

### Types

|&nbsp;|&nbsp;|
|---|---|
| [ApplicationConfig](-application-config/index.md) | <code>class ApplicationConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ClassResourceConfig](-class-resource-config/index.md) | <code>class ClassResourceConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ConfigLoader](-config-loader/index.md) | <code>abstract class ConfigLoader</code><br/> |
| [ConfiguredValue](-configured-value/index.md) | <code>class ConfiguredValue</code><br/>Intermediate object for providing additional functionality on a configured item |
| [EnvironmentVariablesConfig](-environment-variables-config/index.md) | <code>class EnvironmentVariablesConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [FileConfig](-file-config/index.md) | <code>open class FileConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [KonfigAndInjektMain](-konfig-and-injekt-main/index.md) | <code>abstract class KonfigAndInjektMain : [KonfigAndInjektScopedMain](-konfig-and-injekt-scoped-main/index.md)</code><br/>A class that startups up an system using Injekt + TypesafeConfig, using the default global scope, and default object binder |
| [KonfigAndInjektScopedMain](-konfig-and-injekt-scoped-main/index.md) | <code>abstract class KonfigAndInjektScopedMain : InjektModule, [KonfigModule](-konfig-module/index.md)</code><br/>A startup module that registers and uses singletons/object factories from a specific scope,<br/>and an ObjectMapper to bind configuration properties into class instances. |
| [KonfigModule](-konfig-module/index.md) | <code>interface KonfigModule</code><br/>A package of configuration bound items that can be included into a scope of someone else |
| [KonfigRegistrar](-konfig-registrar/index.md) | <code>interface KonfigRegistrar : InjektRegistrar</code><br/> |
| [MapAsConfig](-map-as-config/index.md) | <code>class MapAsConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [PropertiesAsConfig](-properties-as-config/index.md) | <code>class PropertiesAsConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ReaderConfig](-reader-config/index.md) | <code>class ReaderConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ReferenceConfig](-reference-config/index.md) | <code>class ReferenceConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ResolveConfig](-resolve-config/index.md) | <code>class ResolveConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [ResourceConfig](-resource-config/index.md) | <code>class ResourceConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [StringAsConfig](-string-as-config/index.md) | <code>class StringAsConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [SystemPropertiesConfig](-system-properties-config/index.md) | <code>class SystemPropertiesConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |
| [UrlConfig](-url-config/index.md) | <code>class UrlConfig : [ConfigLoader](-config-loader/index.md)</code><br/> |

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [com.typesafe.config.Config](com.typesafe.config.-config/index.md) |  |
| [com.typesafe.config.ConfigObject](com.typesafe.config.-config-object/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bindClassAtConfigPath](bind-class-at-config-path.md) | <code>fun <T : Any> [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigPath(configPath: String): Unit</code><br/><code>fun <T : Any> [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigPath(configPath: String, klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit</code><br/>bind a class bindings its values from a configuration path immediately |
| [bindClassAtConfigRoot](bind-class-at-config-root.md) | <code>fun <T : Any> [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigRoot(): Unit</code><br/><code>fun <T : Any> [KonfigRegistrar](-konfig-registrar/index.md).bindClassAtConfigRoot(klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit</code><br/>bind a class bindings its values from the root of the current configuration path immediately |
| [loadApplicationConfig](load-application-config.md) | <code>fun loadApplicationConfig(): Config</code><br/>Mimics the behavior of ConfigFactory.load() or ConfigFactory.defaultApplication() |
| [loadConfig](load-config.md) | <code>fun loadConfig(vararg loaders: [ConfigLoader](-config-loader/index.md)): Config</code><br/>Load a configuration chain, in the order of priority with NO default behavior (i.e. no environment vars, reference conf loading, nor system props) |
