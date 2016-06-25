[uy.klutter.core.jdk](../index.md) / [ChildFirstClassloader](.)


# ChildFirstClassloader
<code>class ChildFirstClassloader : [ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/jdk/ChildFirstClassloader.kt#L17)<br/>
A parent-last classloader that will try the child classloader first and then the parent.



### Types

|&nbsp;|&nbsp;|
|---|---|
| [UrlEnumeration](-url-enumeration/index.md) | <code>class UrlEnumeration : [Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)<[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)></code><br/> |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ChildFirstClassloader(classpath: List<[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)>, parentClassLoader: [ClassLoader](http://docs.oracle.com/javase/6/docs/api/java/lang/ClassLoader.html)?)</code><br/>A parent-last classloader that will try the child classloader first and then the parent. |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [clearAssertionStatus](clear-assertion-status.md) | <code>fun clearAssertionStatus(): Unit</code><br/> |
| [findClass](find-class.md) | <code>fun findClass(name: String): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<*></code><br/> |
| [findResource](find-resource.md) | <code>fun findResource(name: String): [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)?</code><br/> |
| [findResources](find-resources.md) | <code>fun findResources(name: String): [Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)<[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)></code><br/> |
| [getResource](get-resource.md) | <code>fun getResource(name: String): [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)?</code><br/> |
| [getResourceAsStream](get-resource-as-stream.md) | <code>fun getResourceAsStream(name: String): [InputStream](http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html)?</code><br/> |
| [getResources](get-resources.md) | <code>fun getResources(name: String): [Enumeration](http://docs.oracle.com/javase/6/docs/api/java/util/Enumeration.html)<[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)></code><br/> |
| [loadClass](load-class.md) | <code>fun loadClass(name: String, resolve: Boolean): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<*></code><br/><code>fun loadClass(name: String): [Class](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)<*></code><br/> |
| [setClassAssertionStatus](set-class-assertion-status.md) | <code>fun setClassAssertionStatus(className: String?, enabled: Boolean): Unit</code><br/> |
| [setDefaultAssertionStatus](set-default-assertion-status.md) | <code>fun setDefaultAssertionStatus(enabled: Boolean): Unit</code><br/> |
| [setPackageAssertionStatus](set-package-assertion-status.md) | <code>fun setPackageAssertionStatus(packageName: String, enabled: Boolean): Unit</code><br/> |
