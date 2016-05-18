[uy.klutter.config.typesafe](../index.md) / [ResourceConfig](.)


# ResourceConfig

`class ResourceConfig&nbsp;:&nbsp;[ConfigLoader](../-config-loader/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L118)



### Constructors


| [&lt;init&gt;](-init-.md) | `ResourceConfig(resouceName:&nbsp;String, optionalClassLoader:&nbsp;[ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)?&nbsp;=&nbsp;null, failIfMissing:&nbsp;Boolean&nbsp;=&nbsp;true)` |


### Properties


| [failIfMissing](fail-if-missing.md) | `val failIfMissing: Boolean` |
| [optionalClassLoader](optional-class-loader.md) | `val optionalClassLoader: [ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)?` |
| [resouceName](resouce-name.md) | `val resouceName: String` |


### Functions


| [load](load.md) | `fun load(): Config` |


### Inherited Functions


| [afterAttached](../-config-loader/after-attached.md) | `open fun afterAttached(fullConfig:&nbsp;Config): Unit` |

