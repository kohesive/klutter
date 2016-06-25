[uy.klutter.core.uri](../index.md) / [ImmutableUri](.)


# ImmutableUri
`interface ImmutableUri` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L28)



### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | `abstract val decodedFragment: String?` |
| [decodedPath](decoded-path.md) | `abstract val decodedPath: List&lt;String&gt;?` |
| [decodedQuery](decoded-query.md) | `abstract val decodedQuery: Map&lt;String,&nbsp;List&lt;String&gt;&gt;?` |
| [decodedQueryDeduped](decoded-query-deduped.md) | `abstract val decodedQueryDeduped: Map&lt;String,&nbsp;String&gt;?` |
| [decodedUserInfo](decoded-user-info.md) | `abstract val decodedUserInfo: String?` |
| [encodedFragment](encoded-fragment.md) | `abstract val encodedFragment: String?` |
| [encodedPath](encoded-path.md) | `abstract val encodedPath: String?` |
| [encodedQuery](encoded-query.md) | `abstract val encodedQuery: String?` |
| [encodedUserInfo](encoded-user-info.md) | `abstract val encodedUserInfo: String?` |
| [host](host.md) | `abstract val host: String?` |
| [port](port.md) | `abstract val port: Int?` |
| [scheme](scheme.md) | `abstract val scheme: String?` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](as-string.md) | `open fun asString(): String` |
| [fragmentAsDecodedPath](fragment-as-decoded-path.md) | `open fun fragmentAsDecodedPath(): List&lt;String&gt;?` |
| [fragmentAsDecodedQuery](fragment-as-decoded-query.md) | `open fun fragmentAsDecodedQuery(): Map&lt;String,&nbsp;List&lt;String&gt;&gt;?` |
| [fragmentAsDecodedQueryDeduped](fragment-as-decoded-query-deduped.md) | `open fun fragmentAsDecodedQueryDeduped(): Map&lt;String,&nbsp;String&gt;?` |
| [hasFragment](has-fragment.md) | `open fun hasFragment(): Boolean` |
| [hasHost](has-host.md) | `open fun hasHost(): Boolean` |
| [hasPath](has-path.md) | `open fun hasPath(): Boolean` |
| [hasPort](has-port.md) | `open fun hasPort(): Boolean` |
| [hasQuery](has-query.md) | `open fun hasQuery(): Boolean` |
| [hasScheme](has-scheme.md) | `open fun hasScheme(): Boolean` |
| [hasUserInfo](has-user-info.md) | `open fun hasUserInfo(): Boolean` |
| [toURI](to-u-r-i.md) | `open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [BuiltUri](../-uri-builder/-built-uri/index.md) | `data class BuiltUri&nbsp;:&nbsp;ImmutableUri` |
| [UriBuilder](../-uri-builder/index.md) | `class UriBuilder&nbsp;:&nbsp;ImmutableUri` |
