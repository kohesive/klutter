[uy.klutter.config.typesafe](../index.md) / [ClassResourceConfig](.)


# ClassResourceConfig

`class ClassResourceConfig&nbsp;:&nbsp;[ConfigLoader](../-config-loader/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L129)



### Constructors


| [&lt;init&gt;](-init-.md) | `ClassResourceConfig(resouceName:&nbsp;String, klass:&nbsp;[Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;*&gt;, failIfMissing:&nbsp;Boolean&nbsp;=&nbsp;true)` |


### Properties


| [failIfMissing](fail-if-missing.md) | `val failIfMissing: Boolean` |
| [klass](klass.md) | `val klass: [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;*&gt;` |
| [resouceName](resouce-name.md) | `val resouceName: String` |


### Functions


| [load](load.md) | `fun load(): Config` |


### Inherited Functions


| [afterAttached](../-config-loader/after-attached.md) | `open fun afterAttached(fullConfig:&nbsp;Config): Unit` |

