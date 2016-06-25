[uy.klutter.core.uri](../../index.md) / [UriBuilder](../index.md) / [BuiltUri](.)


# BuiltUri
<code>data class BuiltUri : [ImmutableUri](../../-immutable-uri/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L283)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>BuiltUri(scheme: String?, encodedUserInfo: String?, decodedUserInfo: String?, host: String?, port: Int?, encodedPath: String?, decodedPath: List<String>?, encodedQuery: String?, decodedQuery: Map<String, List<String>>?, decodedQueryDeduped: Map<String, String>?, encodedFragment: String?, decodedFragment: String?)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | <code>val decodedFragment: String?</code><br/> |
| [decodedPath](decoded-path.md) | <code>val decodedPath: List<String>?</code><br/> |
| [decodedQuery](decoded-query.md) | <code>val decodedQuery: Map<String, List<String>>?</code><br/> |
| [decodedQueryDeduped](decoded-query-deduped.md) | <code>val decodedQueryDeduped: Map<String, String>?</code><br/> |
| [decodedUserInfo](decoded-user-info.md) | <code>val decodedUserInfo: String?</code><br/> |
| [encodedFragment](encoded-fragment.md) | <code>val encodedFragment: String?</code><br/> |
| [encodedPath](encoded-path.md) | <code>val encodedPath: String?</code><br/> |
| [encodedQuery](encoded-query.md) | <code>val encodedQuery: String?</code><br/> |
| [encodedUserInfo](encoded-user-info.md) | <code>val encodedUserInfo: String?</code><br/> |
| [host](host.md) | <code>val host: String?</code><br/> |
| [port](port.md) | <code>val port: Int?</code><br/> |
| [scheme](scheme.md) | <code>val scheme: String?</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [toString](to-string.md) | <code>fun toString(): String</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](../../-immutable-uri/as-string.md) | <code>open fun asString(): String</code><br/> |
| [fragmentAsDecodedPath](../../-immutable-uri/fragment-as-decoded-path.md) | <code>open fun fragmentAsDecodedPath(): List<String>?</code><br/> |
| [fragmentAsDecodedQuery](../../-immutable-uri/fragment-as-decoded-query.md) | <code>open fun fragmentAsDecodedQuery(): Map<String, List<String>>?</code><br/> |
| [fragmentAsDecodedQueryDeduped](../../-immutable-uri/fragment-as-decoded-query-deduped.md) | <code>open fun fragmentAsDecodedQueryDeduped(): Map<String, String>?</code><br/> |
| [hasFragment](../../-immutable-uri/has-fragment.md) | <code>open fun hasFragment(): Boolean</code><br/> |
| [hasHost](../../-immutable-uri/has-host.md) | <code>open fun hasHost(): Boolean</code><br/> |
| [hasPath](../../-immutable-uri/has-path.md) | <code>open fun hasPath(): Boolean</code><br/> |
| [hasPort](../../-immutable-uri/has-port.md) | <code>open fun hasPort(): Boolean</code><br/> |
| [hasQuery](../../-immutable-uri/has-query.md) | <code>open fun hasQuery(): Boolean</code><br/> |
| [hasScheme](../../-immutable-uri/has-scheme.md) | <code>open fun hasScheme(): Boolean</code><br/> |
| [hasUserInfo](../../-immutable-uri/has-user-info.md) | <code>open fun hasUserInfo(): Boolean</code><br/> |
| [toURI](../../-immutable-uri/to-u-r-i.md) | <code>open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)</code><br/> |
