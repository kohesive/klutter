[uy.klutter.core.uri](../index.md) / [UriBuilder](.)


# UriBuilder
`class UriBuilder&nbsp;:&nbsp;[ImmutableUri](../-immutable-uri/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L72)



### Types

|&nbsp;|&nbsp;|
|---|---|
| [BuiltUri](-built-uri/index.md) | `data class BuiltUri&nbsp;:&nbsp;[ImmutableUri](../-immutable-uri/index.md)` |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `UriBuilder(scheme:&nbsp;String?&nbsp;=&nbsp;null, encodedUserInfo:&nbsp;String?&nbsp;=&nbsp;null, host:&nbsp;String?&nbsp;=&nbsp;null, port:&nbsp;Int?&nbsp;=&nbsp;null, encodedPath:&nbsp;String?&nbsp;=&nbsp;null, encodedQuery:&nbsp;String?&nbsp;=&nbsp;null, encodedFragment:&nbsp;String?&nbsp;=&nbsp;null)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | `var decodedFragment: String?` |
| [decodedPath](decoded-path.md) | `var decodedPath: List<String>?` |
| [decodedQuery](decoded-query.md) | `var decodedQuery: Map<String,&nbsp;List<String>>?` |
| [decodedQueryDeduped](decoded-query-deduped.md) | `var decodedQueryDeduped: Map<String,&nbsp;String>?` |
| [decodedUserInfo](decoded-user-info.md) | `var decodedUserInfo: String?` |
| [encodedFragment](encoded-fragment.md) | `var encodedFragment: String?` |
| [encodedPath](encoded-path.md) | `var encodedPath: String?` |
| [encodedQuery](encoded-query.md) | `var encodedQuery: String?` |
| [encodedUserInfo](encoded-user-info.md) | `var encodedUserInfo: String?` |
| [host](host.md) | `var host: String?` |
| [port](port.md) | `var port: Int?` |
| [scheme](scheme.md) | `var scheme: String?` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [addQueryParams](add-query-params.md) | `fun addQueryParams(vararg params:&nbsp;<ERROR CLASS><String,&nbsp;String?>): UriBuilder` |
| [build](build.md) | `fun build(): [ImmutableUri](../-immutable-uri/index.md)` |
| [clearFragment](clear-fragment.md) | `fun clearFragment(): UriBuilder` |
| [clearPath](clear-path.md) | `fun clearPath(): UriBuilder` |
| [clearQuery](clear-query.md) | `fun clearQuery(): UriBuilder` |
| [clearQueryExcept](clear-query-except.md) | `fun clearQueryExcept(vararg keepParm:&nbsp;String): UriBuilder`
`fun clearQueryExcept(keepParms:&nbsp;Collection<String>): UriBuilder` |
| [decodedFragment](decoded-fragment.md) | `fun decodedFragment(newFragment:&nbsp;String?): UriBuilder` |
| [decodedPath](decoded-path.md) | `fun decodedPath(newPath:&nbsp;List<String>?): UriBuilder` |
| [decodedUserInfo](decoded-user-info.md) | `fun decodedUserInfo(newUserInfo:&nbsp;String?): UriBuilder` |
| [encodedFragment](encoded-fragment.md) | `fun encodedFragment(newFragment:&nbsp;String?): UriBuilder` |
| [encodedPath](encoded-path.md) | `fun encodedPath(newPath:&nbsp;String?): UriBuilder` |
| [encodedQuery](encoded-query.md) | `fun encodedQuery(newQuery:&nbsp;String?): UriBuilder` |
| [encodedUserInfo](encoded-user-info.md) | `fun encodedUserInfo(newUserInfo:&nbsp;String?): UriBuilder` |
| [host](host.md) | `fun host(newHost:&nbsp;String?): UriBuilder` |
| [port](port.md) | `fun port(newPort:&nbsp;Int?): UriBuilder` |
| [removeQueryParams](remove-query-params.md) | `fun removeQueryParams(vararg params:&nbsp;String): UriBuilder` |
| [replaceQueryParams](replace-query-params.md) | `fun replaceQueryParams(vararg params:&nbsp;<ERROR CLASS><String,&nbsp;String?>): UriBuilder` |
| [scheme](scheme.md) | `fun scheme(newScheme:&nbsp;String?): UriBuilder` |
| [toString](to-string.md) | `fun toString(): String` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](../-immutable-uri/as-string.md) | `open fun asString(): String` |
| [fragmentAsDecodedPath](../-immutable-uri/fragment-as-decoded-path.md) | `open fun fragmentAsDecodedPath(): List<String>?` |
| [fragmentAsDecodedQuery](../-immutable-uri/fragment-as-decoded-query.md) | `open fun fragmentAsDecodedQuery(): Map<String,&nbsp;List<String>>?` |
| [fragmentAsDecodedQueryDeduped](../-immutable-uri/fragment-as-decoded-query-deduped.md) | `open fun fragmentAsDecodedQueryDeduped(): Map<String,&nbsp;String>?` |
| [hasFragment](../-immutable-uri/has-fragment.md) | `open fun hasFragment(): Boolean` |
| [hasHost](../-immutable-uri/has-host.md) | `open fun hasHost(): Boolean` |
| [hasPath](../-immutable-uri/has-path.md) | `open fun hasPath(): Boolean` |
| [hasPort](../-immutable-uri/has-port.md) | `open fun hasPort(): Boolean` |
| [hasQuery](../-immutable-uri/has-query.md) | `open fun hasQuery(): Boolean` |
| [hasScheme](../-immutable-uri/has-scheme.md) | `open fun hasScheme(): Boolean` |
| [hasUserInfo](../-immutable-uri/has-user-info.md) | `open fun hasUserInfo(): Boolean` |
| [toURI](../-immutable-uri/to-u-r-i.md) | `open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)` |
