[uy.klutter.core.uri](../index.md) / [UriBuilder](.)


# UriBuilder
<code>class UriBuilder : [ImmutableUri](../-immutable-uri/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UriBuilder.kt#L72)<br/>


### Types

|&nbsp;|&nbsp;|
|---|---|
| [BuiltUri](-built-uri/index.md) | <code>data class BuiltUri : [ImmutableUri](../-immutable-uri/index.md)</code><br/> |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>UriBuilder(scheme: String? = null, encodedUserInfo: String? = null, host: String? = null, port: Int? = null, encodedPath: String? = null, encodedQuery: String? = null, encodedFragment: String? = null)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [decodedFragment](decoded-fragment.md) | <code>var decodedFragment: String?</code><br/> |
| [decodedPath](decoded-path.md) | <code>var decodedPath: List<String>?</code><br/> |
| [decodedQuery](decoded-query.md) | <code>var decodedQuery: Map<String, List<String>>?</code><br/> |
| [decodedQueryDeduped](decoded-query-deduped.md) | <code>var decodedQueryDeduped: Map<String, String>?</code><br/> |
| [decodedUserInfo](decoded-user-info.md) | <code>var decodedUserInfo: String?</code><br/> |
| [encodedFragment](encoded-fragment.md) | <code>var encodedFragment: String?</code><br/> |
| [encodedPath](encoded-path.md) | <code>var encodedPath: String?</code><br/> |
| [encodedQuery](encoded-query.md) | <code>var encodedQuery: String?</code><br/> |
| [encodedUserInfo](encoded-user-info.md) | <code>var encodedUserInfo: String?</code><br/> |
| [host](host.md) | <code>var host: String?</code><br/> |
| [port](port.md) | <code>var port: Int?</code><br/> |
| [scheme](scheme.md) | <code>var scheme: String?</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [addQueryParams](add-query-params.md) | <code>fun addQueryParams(vararg params: <ERROR CLASS><String, String?>): UriBuilder</code><br/> |
| [build](build.md) | <code>fun build(): [ImmutableUri](../-immutable-uri/index.md)</code><br/> |
| [clearFragment](clear-fragment.md) | <code>fun clearFragment(): UriBuilder</code><br/> |
| [clearPath](clear-path.md) | <code>fun clearPath(): UriBuilder</code><br/> |
| [clearQuery](clear-query.md) | <code>fun clearQuery(): UriBuilder</code><br/> |
| [clearQueryExcept](clear-query-except.md) | <code>fun clearQueryExcept(vararg keepParm: String): UriBuilder</code><br/><code>fun clearQueryExcept(keepParms: Collection<String>): UriBuilder</code><br/> |
| [decodedFragment](decoded-fragment.md) | <code>fun decodedFragment(newFragment: String?): UriBuilder</code><br/> |
| [decodedPath](decoded-path.md) | <code>fun decodedPath(newPath: List<String>?): UriBuilder</code><br/> |
| [decodedUserInfo](decoded-user-info.md) | <code>fun decodedUserInfo(newUserInfo: String?): UriBuilder</code><br/> |
| [encodedFragment](encoded-fragment.md) | <code>fun encodedFragment(newFragment: String?): UriBuilder</code><br/> |
| [encodedPath](encoded-path.md) | <code>fun encodedPath(newPath: String?): UriBuilder</code><br/> |
| [encodedQuery](encoded-query.md) | <code>fun encodedQuery(newQuery: String?): UriBuilder</code><br/> |
| [encodedUserInfo](encoded-user-info.md) | <code>fun encodedUserInfo(newUserInfo: String?): UriBuilder</code><br/> |
| [host](host.md) | <code>fun host(newHost: String?): UriBuilder</code><br/> |
| [port](port.md) | <code>fun port(newPort: Int?): UriBuilder</code><br/> |
| [removeQueryParams](remove-query-params.md) | <code>fun removeQueryParams(vararg params: String): UriBuilder</code><br/> |
| [replaceQueryParams](replace-query-params.md) | <code>fun replaceQueryParams(vararg params: <ERROR CLASS><String, String?>): UriBuilder</code><br/> |
| [scheme](scheme.md) | <code>fun scheme(newScheme: String?): UriBuilder</code><br/> |
| [toString](to-string.md) | <code>fun toString(): String</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [asString](../-immutable-uri/as-string.md) | <code>open fun asString(): String</code><br/> |
| [fragmentAsDecodedPath](../-immutable-uri/fragment-as-decoded-path.md) | <code>open fun fragmentAsDecodedPath(): List<String>?</code><br/> |
| [fragmentAsDecodedQuery](../-immutable-uri/fragment-as-decoded-query.md) | <code>open fun fragmentAsDecodedQuery(): Map<String, List<String>>?</code><br/> |
| [fragmentAsDecodedQueryDeduped](../-immutable-uri/fragment-as-decoded-query-deduped.md) | <code>open fun fragmentAsDecodedQueryDeduped(): Map<String, String>?</code><br/> |
| [hasFragment](../-immutable-uri/has-fragment.md) | <code>open fun hasFragment(): Boolean</code><br/> |
| [hasHost](../-immutable-uri/has-host.md) | <code>open fun hasHost(): Boolean</code><br/> |
| [hasPath](../-immutable-uri/has-path.md) | <code>open fun hasPath(): Boolean</code><br/> |
| [hasPort](../-immutable-uri/has-port.md) | <code>open fun hasPort(): Boolean</code><br/> |
| [hasQuery](../-immutable-uri/has-query.md) | <code>open fun hasQuery(): Boolean</code><br/> |
| [hasScheme](../-immutable-uri/has-scheme.md) | <code>open fun hasScheme(): Boolean</code><br/> |
| [hasUserInfo](../-immutable-uri/has-user-info.md) | <code>open fun hasUserInfo(): Boolean</code><br/> |
| [toURI](../-immutable-uri/to-u-r-i.md) | <code>open fun toURI(): [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)</code><br/> |
