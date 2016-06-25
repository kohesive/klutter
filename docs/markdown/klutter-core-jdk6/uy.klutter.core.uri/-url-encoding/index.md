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

|&nbsp;|&nbsp;|
|---|---|
| [decode](decode.md) | `fun decode(s:&nbsp;String, charset:&nbsp;[Charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html)&nbsp;=&nbsp;Charsets.UTF_8): String`<p>Decodes URL encoded string including newly introduced JavaScript encoding with %uxxxx chars</p> |
| [decodePathToSegments](decode-path-to-segments.md) | `fun decodePathToSegments(encodedPath:&nbsp;String): List<String>` |
| [decodeQueryStringToMultiMap](decode-query-string-to-multi-map.md) | `fun decodeQueryStringToMultiMap(encodedQuery:&nbsp;String): Map<String,&nbsp;List<String>>` |
| [decodeQueryToMap](decode-query-to-map.md) | `fun decodeQueryToMap(encodedQuery:&nbsp;String): Map<String,&nbsp;String>` |
| [dedupeQueryFromMultiMapToMap](dedupe-query-from-multi-map-to-map.md) | `fun dedupeQueryFromMultiMapToMap(decodedQuery:&nbsp;Map<String,&nbsp;List<String>>): Map<String,&nbsp;String>` |
| [encodeFragmentString](encode-fragment-string.md) | `fun encodeFragmentString(fragment:&nbsp;String): String`<p>Encodes the fragment part of a URI, it can contain PCHAR* with / and ?</p> |
| [encodePathParamName](encode-path-param-name.md) | `fun encodePathParamName(pathParamName:&nbsp;String): String`<p>Encodes a string to be a valid path parameter name, which means it can contain PCHAR* without "=" or ";". Uses<br/>UTF-8.</p> |
| [encodePathParamValue](encode-path-param-value.md) | `fun encodePathParamValue(pathParamValue:&nbsp;String): String`<p>Encodes a string to be a valid path parameter value, which means it can contain PCHAR* without ";". Uses UTF-8.</p> |
| [encodePathSegment](encode-path-segment.md) | `fun encodePathSegment(pathSegment:&nbsp;String): String`<p>Encodes a string to be a valid path segment, which means it can contain PCHAR* only (do not put path parameters or<br/>they will be escaped. Uses UTF-8.</p> |
| [encodePathStringFromSegments](encode-path-string-from-segments.md) | `fun encodePathStringFromSegments(decodedPath:&nbsp;List<String>): String` |
| [encodeQueryMapToString](encode-query-map-to-string.md) | `fun encodeQueryMapToString(decodedQuery:&nbsp;Map<String,&nbsp;String>): String` |
| [encodeQueryMultiMapToString](encode-query-multi-map-to-string.md) | `fun encodeQueryMultiMapToString(decodedQuery:&nbsp;Map<String,&nbsp;List<String>>): String` |
| [encodeQueryNameOrValue](encode-query-name-or-value.md) | `fun encodeQueryNameOrValue(queryNameOrValue:&nbsp;String): String`<p>Encodes a string to be a valid query, which means it can contain PCHAR* | "?" | "/" without "=" | "&amp;" | "+". Uses<br/>UTF-8.</p> |
| [encodeQueryNameOrValueNoParen](encode-query-name-or-value-no-paren.md) | `fun encodeQueryNameOrValueNoParen(queryNameOrValueNoParen:&nbsp;String): String`<p>Encodes a string to be a valid query with no parenthesis, which means it can contain PCHAR* | "?" | "/" without<br/>"=" | "&amp;" | "+" | "(" | ")". It strips parenthesis. Uses UTF-8.</p> |
| [encodeUserInfo](encode-user-info.md) | `fun encodeUserInfo(userInfo:&nbsp;String): String`<p>Encodes the fragment part of a URI, it can contain PCHAR* with / and ?</p> |
