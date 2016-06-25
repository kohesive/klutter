[uy.klutter.config.typesafe](../index.md) / [KonfigRegistrar](.)


# KonfigRegistrar
`interface KonfigRegistrar&nbsp;:&nbsp;InjektRegistrar` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L60)



### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bindClassAtConfigPath](bind-class-at-config-path.md) | `abstract fun <T&nbsp;:&nbsp;Any> bindClassAtConfigPath(configPath:&nbsp;String, klass:&nbsp;TypeReference<T>): Unit`<p>bind a class bindings its values from a configuration path immediately</p> |
| [bindClassAtConfigRoot](bind-class-at-config-root.md) | `abstract fun <T&nbsp;:&nbsp;Any> bindClassAtConfigRoot(klass:&nbsp;TypeReference<T>): Unit`<p>bind a class bindings its values from the root of the current configuration path immediately</p> |
| [importModule](import-module.md) | `abstract fun importModule(atPath:&nbsp;String, module:&nbsp;[KonfigModule](../-konfig-module/index.md)): Unit`<p>import a module loading it and any submodules immediately</p> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [bindClassAtConfigPath](../bind-class-at-config-path.md) | `fun <T&nbsp;:&nbsp;Any> KonfigRegistrar.bindClassAtConfigPath(configPath:&nbsp;String): Unit`
`fun <T&nbsp;:&nbsp;Any> KonfigRegistrar.bindClassAtConfigPath(configPath:&nbsp;String, klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit`<p>bind a class bindings its values from a configuration path immediately</p> |
| [bindClassAtConfigRoot](../bind-class-at-config-root.md) | `fun <T&nbsp;:&nbsp;Any> KonfigRegistrar.bindClassAtConfigRoot(): Unit`
`fun <T&nbsp;:&nbsp;Any> KonfigRegistrar.bindClassAtConfigRoot(klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<T>): Unit`<p>bind a class bindings its values from the root of the current configuration path immediately</p> |
| [registerConfigurables](../-konfig-module/register-configurables.md) | `abstract fun KonfigRegistrar.registerConfigurables(): Unit` |
