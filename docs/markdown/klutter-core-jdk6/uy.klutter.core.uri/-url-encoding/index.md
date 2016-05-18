[uy.klutter.core.uri](../index.md) / [UrlEncoding](.)


# UrlEncoding

`object UrlEncoding` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L23)

Converted to Kotlin from https://github.com/resteasy/Resteasy/blob/master/jaxrs/resteasy-jaxrs/src/main/java/org/jboss/resteasy/util/URLUtils.java
Then with extra things added including a ported decoder from same library, query to maps, and more


URL-encoding utility for each URL part according to the RFC specs


based on work by stephane@epardaud.fr




**See Also**

[http](#)






### Functions


| [decode](decode.md) | `fun decode(s:&nbsp;String, charset:&nbsp;[Charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)&nbsp;=&nbsp;Charsets.UTF_8): String`
Decodes URL encoded string including newly introduced JavaScript encoding with %uxxxx chars

 |
| [decodePathToSegments](decode-path-to-segments.md) | `fun decodePathToSegments(encodedPath:&nbsp;String): List&lt;String&gt;` |
| [decodeQueryStringToMultiMap](decode-query-string-to-multi-map.md) | `fun decodeQueryStringToMultiMap(encodedQuery:&nbsp;String): Map&lt;String,&nbsp;List&lt;String&gt;&gt;` |
| [decodeQueryToMap](decode-query-to-map.md) | `fun decodeQueryToMap(encodedQuery:&nbsp;String): Map&lt;String,&nbsp;String&gt;` |
| [dedupeQueryFromMultiMapToMap](dedupe-query-from-multi-map-to-map.md) | `fun dedupeQueryFromMultiMapToMap(decodedQuery:&nbsp;Map&lt;String,&nbsp;List&lt;String&gt;&gt;): Map&lt;String,&nbsp;String&gt;` |
| [encodeFragmentString](encode-fragment-string.md) | `fun encodeFragmentString(fragment:&nbsp;String): String`
Encodes the fragment part of a URI, it can contain PCHAR* with / and ?

 |
| [encodePathParamName](encode-path-param-name.md) | `fun encodePathParamName(pathParamName:&nbsp;String): String`
Encodes a string to be a valid path parameter name, which means it can contain PCHAR* without "=" or ";". Uses
UTF-8.

 |
| [encodePathParamValue](encode-path-param-value.md) | `fun encodePathParamValue(pathParamValue:&nbsp;String): String`
Encodes a string to be a valid path parameter value, which means it can contain PCHAR* without ";". Uses UTF-8.

 |
| [encodePathSegment](encode-path-segment.md) | `fun encodePathSegment(pathSegment:&nbsp;String): String`
Encodes a string to be a valid path segment, which means it can contain PCHAR* only (do not put path parameters or
they will be escaped. Uses UTF-8.

 |
| [encodePathStringFromSegments](encode-path-string-from-segments.md) | `fun encodePathStringFromSegments(decodedPath:&nbsp;List&lt;String&gt;): String` |
| [encodeQueryMapToString](encode-query-map-to-string.md) | `fun encodeQueryMapToString(decodedQuery:&nbsp;Map&lt;String,&nbsp;String&gt;): String` |
| [encodeQueryMultiMapToString](encode-query-multi-map-to-string.md) | `fun encodeQueryMultiMapToString(decodedQuery:&nbsp;Map&lt;String,&nbsp;List&lt;String&gt;&gt;): String` |
| [encodeQueryNameOrValue](encode-query-name-or-value.md) | `fun encodeQueryNameOrValue(queryNameOrValue:&nbsp;String): String`
Encodes a string to be a valid query, which means it can contain PCHAR* | "?" | "/" without "=" | "&amp;" | "+". Uses
UTF-8.

 |
| [encodeQueryNameOrValueNoParen](encode-query-name-or-value-no-paren.md) | `fun encodeQueryNameOrValueNoParen(queryNameOrValueNoParen:&nbsp;String): String`
Encodes a string to be a valid query with no parenthesis, which means it can contain PCHAR* | "?" | "/" without
"=" | "&amp;" | "+" | "(" | ")". It strips parenthesis. Uses UTF-8.

 |
| [encodeUserInfo](encode-user-info.md) | `fun encodeUserInfo(userInfo:&nbsp;String): String`
Encodes the fragment part of a URI, it can contain PCHAR* with / and ?

 |

