[uy.klutter.core.uri](.)


## Package uy.klutter.core.uri


### Types


| [ImmutableUri](-immutable-uri/index.md) | `interface ImmutableUri` |
| [UriBuilder](-uri-builder/index.md) | `class UriBuilder&nbsp;:&nbsp;[ImmutableUri](-immutable-uri/index.md)` |
| [UrlEncoding](-url-encoding/index.md) | `object UrlEncoding`
Converted to Kotlin from https://github.com/resteasy/Resteasy/blob/master/jaxrs/resteasy-jaxrs/src/main/java/org/jboss/resteasy/util/URLUtils.java
Then with extra things added including a ported decoder from same library, query to maps, and more

 |


### Functions


| [buildUri](build-uri.md) | `fun buildUri(uri:&nbsp;[URI](http://docs.oracle.com/javase/6/docs/api/java/net/URI.html)): [UriBuilder](-uri-builder/index.md)`
`fun buildUri(uriString:&nbsp;String): [UriBuilder](-uri-builder/index.md)`
`fun buildUri(url:&nbsp;[URL](http://docs.oracle.com/javase/6/docs/api/java/net/URL.html)): [UriBuilder](-uri-builder/index.md)`
`fun buildUri(uri:&nbsp;[ImmutableUri](-immutable-uri/index.md)): [UriBuilder](-uri-builder/index.md)` |

