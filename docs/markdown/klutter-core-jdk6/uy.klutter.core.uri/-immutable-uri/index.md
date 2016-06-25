[uy.klutter.core.uri](../index.md) / [ImmutableUri](.)


# ImmutableUri
<code>interface ImmutableUri</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L28)<br/>


### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | <code>abstract val decodedFragment: String?</code><br/> |
| [decodedPath](decoded-path.md) | <code>abstract val decodedPath: List<String>?</code><br/> |
| [decodedQuery](decoded-query.md) | <code>abstract val decodedQuery: Map<String, List<String>>?</code><br/> |
| [decodedQueryDeduped](decoded-query-deduped.md) | <code>abstract val decodedQueryDeduped: Map<String, String>?</code><br/> |
| [decodedUserInfo](decoded-user-info.md) | <code>abstract val decodedUserInfo: String?</code><br/> |
| [encodedFragment](encoded-fragment.md) | <code>abstract val encodedFragment: String?</code><br/> |
| [encodedPath](encoded-path.md) | <code>abstract val encodedPath: String?</code><br/> |
| [encodedQuery](encoded-query.md) | <code>abstract val encodedQuery: String?</code><br/> |
| [encodedUserInfo](encoded-user-info.md) | <code>abstract val encodedUserInfo: String?</code><br/> |
| [host](host.md) | <code>abstract val host: String?</code><br/> |
| [port](port.md) | <code>abstract val port: Int?</code><br/> |
| [scheme](scheme.md) | <code>abstract val scheme: String?</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](as-string.md) | <code>open fun asString(): String</code><br/> |
| [fragmentAsDecodedPath](fragment-as-decoded-path.md) | <code>open fun fragmentAsDecodedPath(): List<String>?</code><br/> |
| [fragmentAsDecodedQuery](fragment-as-decoded-query.md) | <code>open fun fragmentAsDecodedQuery(): Map<String, List<String>>?</code><br/> |
| [fragmentAsDecodedQueryDeduped](fragment-as-decoded-query-deduped.md) | <code>open fun fragmentAsDecodedQueryDeduped(): Map<String, String>?</code><br/> |
| [hasFragment](has-fragment.md) | <code>open fun hasFragment(): Boolean</code><br/> |
| [hasHost](has-host.md) | <code>open fun hasHost(): Boolean</code><br/> |
| [hasPath](has-path.md) | <code>open fun hasPath(): Boolean</code><br/> |
| [hasPort](has-port.md) | <code>open fun hasPort(): Boolean</code><br/> |
| [hasQuery](has-query.md) | <code>open fun hasQuery(): Boolean</code><br/> |
| [hasScheme](has-scheme.md) | <code>open fun hasScheme(): Boolean</code><br/> |
| [hasUserInfo](has-user-info.md) | <code>open fun hasUserInfo(): Boolean</code><br/> |
| [toURI](to-u-r-i.md) | <code>open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [BuiltUri](../-uri-builder/-built-uri/index.md) | <code>data class BuiltUri : ImmutableUri</code><br/> |
| [UriBuilder](../-uri-builder/index.md) | <code>class UriBuilder : ImmutableUri</code><br/> |
