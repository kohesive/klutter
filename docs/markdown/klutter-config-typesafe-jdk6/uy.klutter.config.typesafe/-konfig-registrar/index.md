[uy.klutter.config.typesafe](../index.md) / [KonfigRegistrar](.)


# KonfigRegistrar
<code>interface KonfigRegistrar : InjektRegistrar</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L60)<br/>


### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bindClassAtConfigPath](bind-class-at-config-path.md) | <code>abstract fun <T : Any> bindClassAtConfigPath(configPath: String, klass: TypeReference<T>): Unit</code><br/>bind a class bindings its values from a configuration path immediately |
| [bindClassAtConfigRoot](bind-class-at-config-root.md) | <code>abstract fun <T : Any> bindClassAtConfigRoot(klass: TypeReference<T>): Unit</code><br/>bind a class bindings its values from the root of the current configuration path immediately |
| [importModule](import-module.md) | <code>abstract fun importModule(atPath: String, module: [KonfigModule](../-konfig-module/index.md)): Unit</code><br/>import a module loading it and any submodules immediately |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [bindClassAtConfigPath](../bind-class-at-config-path.md) | <code>fun <T : Any> KonfigRegistrar.bindClassAtConfigPath(configPath: String): Unit</code><br/><code>fun <T : Any> KonfigRegistrar.bindClassAtConfigPath(configPath: String, klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit</code><br/>bind a class bindings its values from a configuration path immediately |
| [bindClassAtConfigRoot](../bind-class-at-config-root.md) | <code>fun <T : Any> KonfigRegistrar.bindClassAtConfigRoot(): Unit</code><br/><code>fun <T : Any> KonfigRegistrar.bindClassAtConfigRoot(klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit</code><br/>bind a class bindings its values from the root of the current configuration path immediately |
| [registerConfigurables](../-konfig-module/register-configurables.md) | <code>abstract fun KonfigRegistrar.registerConfigurables(): Unit</code><br/> |
