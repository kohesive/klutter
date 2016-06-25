[uy.klutter.config.typesafe](../index.md) / [UrlConfig](.)


# UrlConfig
<code>class UrlConfig : [ConfigLoader](../-config-loader/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L143)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>UrlConfig(url: [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html), failIfMissing: Boolean = true)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [failIfMissing](fail-if-missing.md) | <code>val failIfMissing: Boolean</code><br/> |
| [url](url.md) | <code>val url: [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [load](load.md) | <code>fun load(): Config</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](../-config-loader/after-attached.md) | <code>open fun afterAttached(fullConfig: Config): Unit</code><br/> |
