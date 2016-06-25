[uy.klutter.core.uri](.)


## Package uy.klutter.core.uri

### Types

|&nbsp;|&nbsp;|
|---|---|
| [ImmutableUri](-immutable-uri/index.md) | <code>interface ImmutableUri</code><br/> |
| [UriBuilder](-uri-builder/index.md) | <code>class UriBuilder : [ImmutableUri](-immutable-uri/index.md)</code><br/> |
| [UrlEncoding](-url-encoding/index.md) | <code>object UrlEncoding</code><br/>Converted to Kotlin from https://github.com/resteasy/Resteasy/blob/master/jaxrs/resteasy-jaxrs/src/main/java/org/jboss/resteasy/util/URLUtils.java<br/>Then with extra things added including a ported decoder from same library, query to maps, and more |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [buildUri](build-uri.md) | <code>fun buildUri(uri: [URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)): [UriBuilder](-uri-builder/index.md)</code><br/><code>fun buildUri(uriString: String): [UriBuilder](-uri-builder/index.md)</code><br/><code>fun buildUri(url: [URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)): [UriBuilder](-uri-builder/index.md)</code><br/><code>fun buildUri(uri: [ImmutableUri](-immutable-uri/index.md)): [UriBuilder](-uri-builder/index.md)</code><br/> |
