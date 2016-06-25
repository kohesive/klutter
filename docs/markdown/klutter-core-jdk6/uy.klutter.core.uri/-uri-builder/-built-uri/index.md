[uy.klutter.core.uri](../../index.md) / [UriBuilder](../index.md) / [BuiltUri](.)


# BuiltUri
`data class BuiltUri&nbsp;:&nbsp;[ImmutableUri](../../-immutable-uri/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L283)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `BuiltUri(scheme:&nbsp;String?, encodedUserInfo:&nbsp;String?, decodedUserInfo:&nbsp;String?, host:&nbsp;String?, port:&nbsp;Int?, encodedPath:&nbsp;String?, decodedPath:&nbsp;List&lt;String&gt;?, encodedQuery:&nbsp;String?, decodedQuery:&nbsp;Map&lt;String,&nbsp;List&lt;String&gt;&gt;?, decodedQueryDeduped:&nbsp;Map&lt;String,&nbsp;String&gt;?, encodedFragment:&nbsp;String?, decodedFragment:&nbsp;String?)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | `val decodedFragment: String?` |
| [decodedPath](decoded-path.md) | `val decodedPath: List&lt;String&gt;?` |
| [decodedQuery](decoded-query.md) | `val decodedQuery: Map&lt;String,&nbsp;List&lt;String&gt;&gt;?` |
| [decodedQueryDeduped](decoded-query-deduped.md) | `val decodedQueryDeduped: Map&lt;String,&nbsp;String&gt;?` |
| [decodedUserInfo](decoded-user-info.md) | `val decodedUserInfo: String?` |
| [encodedFragment](encoded-fragment.md) | `val encodedFragment: String?` |
| [encodedPath](encoded-path.md) | `val encodedPath: String?` |
| [encodedQuery](encoded-query.md) | `val encodedQuery: String?` |
| [encodedUserInfo](encoded-user-info.md) | `val encodedUserInfo: String?` |
| [host](host.md) | `val host: String?` |
| [port](port.md) | `val port: Int?` |
| [scheme](scheme.md) | `val scheme: String?` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [toString](to-string.md) | `fun toString(): String` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](../../-immutable-uri/as-string.md) | `open fun asString(): String` |
| [fragmentAsDecodedPath](../../-immutable-uri/fragment-as-decoded-path.md) | `open fun fragmentAsDecodedPath(): List&lt;String&gt;?` |
| [fragmentAsDecodedQuery](../../-immutable-uri/fragment-as-decoded-query.md) | `open fun fragmentAsDecodedQuery(): Map&lt;String,&nbsp;List&lt;String&gt;&gt;?` |
| [fragmentAsDecodedQueryDeduped](../../-immutable-uri/fragment-as-decoded-query-deduped.md) | `open fun fragmentAsDecodedQueryDeduped(): Map&lt;String,&nbsp;String&gt;?` |
| [hasFragment](../../-immutable-uri/has-fragment.md) | `open fun hasFragment(): Boolean` |
| [hasHost](../../-immutable-uri/has-host.md) | `open fun hasHost(): Boolean` |
| [hasPath](../../-immutable-uri/has-path.md) | `open fun hasPath(): Boolean` |
| [hasPort](../../-immutable-uri/has-port.md) | `open fun hasPort(): Boolean` |
| [hasQuery](../../-immutable-uri/has-query.md) | `open fun hasQuery(): Boolean` |
| [hasScheme](../../-immutable-uri/has-scheme.md) | `open fun hasScheme(): Boolean` |
| [hasUserInfo](../../-immutable-uri/has-user-info.md) | `open fun hasUserInfo(): Boolean` |
| [toURI](../../-immutable-uri/to-u-r-i.md) | `open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)` |
