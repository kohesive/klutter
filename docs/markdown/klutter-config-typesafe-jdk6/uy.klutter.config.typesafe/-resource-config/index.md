[uy.klutter.config.typesafe](../index.md) / [ResourceConfig](.)


# ResourceConfig
<code>class ResourceConfig : [ConfigLoader](../-config-loader/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L118)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ResourceConfig(resouceName: String, optionalClassLoader: [ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)? = null, failIfMissing: Boolean = true)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [failIfMissing](fail-if-missing.md) | <code>val failIfMissing: Boolean</code><br/> |
| [optionalClassLoader](optional-class-loader.md) | <code>val optionalClassLoader: [ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)?</code><br/> |
| [resouceName](resouce-name.md) | <code>val resouceName: String</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [load](load.md) | <code>fun load(): Config</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](../-config-loader/after-attached.md) | <code>open fun afterAttached(fullConfig: Config): Unit</code><br/> |
