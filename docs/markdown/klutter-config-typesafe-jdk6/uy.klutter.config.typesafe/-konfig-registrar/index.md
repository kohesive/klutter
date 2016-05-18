[uy.klutter.config.typesafe](../index.md) / [KonfigRegistrar](.)


# KonfigRegistrar

`interface KonfigRegistrar&nbsp;:&nbsp;InjektRegistrar` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/InjektConfig.kt#L60)



### Functions


| [bindClassAtConfigPath](bind-class-at-config-path.md) | `abstract fun &lt;T&nbsp;:&nbsp;Any&gt; bindClassAtConfigPath(configPath:&nbsp;String, klass:&nbsp;TypeReference&lt;T&gt;): Unit`
bind a class bindings its values from a configuration path immediately

 |
| [bindClassAtConfigRoot](bind-class-at-config-root.md) | `abstract fun &lt;T&nbsp;:&nbsp;Any&gt; bindClassAtConfigRoot(klass:&nbsp;TypeReference&lt;T&gt;): Unit`
bind a class bindings its values from the root of the current configuration path immediately

 |
| [importModule](import-module.md) | `abstract fun importModule(atPath:&nbsp;String, module:&nbsp;[KonfigModule](../-konfig-module/index.md)): Unit`
import a module loading it and any submodules immediately

 |


### Extension Functions


| [bindClassAtConfigPath](../bind-class-at-config-path.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; KonfigRegistrar.bindClassAtConfigPath(configPath:&nbsp;String): Unit`
`fun &lt;T&nbsp;:&nbsp;Any&gt; KonfigRegistrar.bindClassAtConfigPath(configPath:&nbsp;String, klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit`
bind a class bindings its values from a configuration path immediately

 |
| [bindClassAtConfigRoot](../bind-class-at-config-root.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; KonfigRegistrar.bindClassAtConfigRoot(): Unit`
`fun &lt;T&nbsp;:&nbsp;Any&gt; KonfigRegistrar.bindClassAtConfigRoot(klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;T&gt;): Unit`
bind a class bindings its values from the root of the current configuration path immediately

 |
| [registerConfigurables](../-konfig-module/register-configurables.md) | `abstract fun KonfigRegistrar.registerConfigurables(): Unit` |

