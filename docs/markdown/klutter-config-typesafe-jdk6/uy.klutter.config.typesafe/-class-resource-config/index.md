[uy.klutter.config.typesafe](../index.md) / [ClassResourceConfig](.)


# ClassResourceConfig
<code>class ClassResourceConfig : [ConfigLoader](../-config-loader/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L129)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ClassResourceConfig(resouceName: String, klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<*>, failIfMissing: Boolean = true)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [failIfMissing](fail-if-missing.md) | <code>val failIfMissing: Boolean</code><br/> |
| [klass](klass.md) | <code>val klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<*></code><br/> |
| [resouceName](resouce-name.md) | <code>val resouceName: String</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [load](load.md) | <code>fun load(): Config</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [afterAttached](../-config-loader/after-attached.md) | <code>open fun afterAttached(fullConfig: Config): Unit</code><br/> |
