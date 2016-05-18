[uy.klutter.core.jdk](../index.md) / [ChildFirstClassloader](.)


# ChildFirstClassloader

`class ChildFirstClassloader&nbsp;:&nbsp;[ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/jdk/ChildFirstClassloader.kt#L17)

A parent-last classloader that will try the child classloader first and then the parent.




### Types


| [UrlEnumeration](-url-enumeration/index.md) | `class UrlEnumeration&nbsp;:&nbsp;[Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)&lt;[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)&gt;` |


### Constructors


| [&lt;init&gt;](-init-.md) | `ChildFirstClassloader(classpath:&nbsp;List&lt;[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)&gt;, parentClassLoader:&nbsp;[ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)?)`
A parent-last classloader that will try the child classloader first and then the parent.

 |


### Functions


| [clearAssertionStatus](clear-assertion-status.md) | `fun clearAssertionStatus(): Unit` |
| [findClass](find-class.md) | `fun findClass(name:&nbsp;String): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;*&gt;` |
| [findResource](find-resource.md) | `fun findResource(name:&nbsp;String): [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)?` |
| [findResources](find-resources.md) | `fun findResources(name:&nbsp;String): [Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)&lt;[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)&gt;` |
| [getResource](get-resource.md) | `fun getResource(name:&nbsp;String): [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)?` |
| [getResourceAsStream](get-resource-as-stream.md) | `fun getResourceAsStream(name:&nbsp;String): [InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html)?` |
| [getResources](get-resources.md) | `fun getResources(name:&nbsp;String): [Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)&lt;[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)&gt;` |
| [loadClass](load-class.md) | `fun loadClass(name:&nbsp;String, resolve:&nbsp;Boolean): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;*&gt;`
`fun loadClass(name:&nbsp;String): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)&lt;*&gt;` |
| [setClassAssertionStatus](set-class-assertion-status.md) | `fun setClassAssertionStatus(className:&nbsp;String?, enabled:&nbsp;Boolean): Unit` |
| [setDefaultAssertionStatus](set-default-assertion-status.md) | `fun setDefaultAssertionStatus(enabled:&nbsp;Boolean): Unit` |
| [setPackageAssertionStatus](set-package-assertion-status.md) | `fun setPackageAssertionStatus(packageName:&nbsp;String, enabled:&nbsp;Boolean): Unit` |

