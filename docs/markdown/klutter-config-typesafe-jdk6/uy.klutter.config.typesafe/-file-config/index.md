[uy.klutter.config.typesafe](../index.md) / [FileConfig](.)


# FileConfig
<code>open class FileConfig : [ConfigLoader](../-config-loader/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L136)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>FileConfig(file: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html), failIfMissing: Boolean = true)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [failIfMissing](fail-if-missing.md) | <code>val failIfMissing: Boolean</code><br/> |
| [file](file.md) | <code>val file: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [load](load.md) | <code>open fun load(): Config</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](../-config-loader/after-attached.md) | <code>open fun afterAttached(fullConfig: Config): Unit</code><br/> |
