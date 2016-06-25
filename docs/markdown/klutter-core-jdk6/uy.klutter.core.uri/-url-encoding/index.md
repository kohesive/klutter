[uy.klutter.core.uri](../index.md) / [UrlEncoding](.)


# UrlEncoding
<code>object UrlEncoding</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L23)<br/>
Converted to Kotlin from https://github.com/resteasy/Resteasy/blob/master/jaxrs/resteasy-jaxrs/src/main/java/org/jboss/resteasy/util/URLUtils.java
Then with extra things added including a ported decoder from same library, query to maps, and more

URL-encoding utility for each URL part according to the RFC specs

based on work by stephane@epardaud.fr



**See Also**

[http](#)




### Functions

|&nbsp;|&nbsp;|
|---|---|
| [decode](decode.md) | <code>fun decode(s: String, charset: [Charset](http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html) = Charsets.UTF_8): String</code><br/>Decodes URL encoded string including newly introduced JavaScript encoding with %uxxxx chars |
| [decodePathToSegments](decode-path-to-segments.md) | <code>fun decodePathToSegments(encodedPath: String): List<String></code><br/> |
| [decodeQueryStringToMultiMap](decode-query-string-to-multi-map.md) | <code>fun decodeQueryStringToMultiMap(encodedQuery: String): Map<String, List<String>></code><br/> |
| [decodeQueryToMap](decode-query-to-map.md) | <code>fun decodeQueryToMap(encodedQuery: String): Map<String, String></code><br/> |
| [dedupeQueryFromMultiMapToMap](dedupe-query-from-multi-map-to-map.md) | <code>fun dedupeQueryFromMultiMapToMap(decodedQuery: Map<String, List<String>>): Map<String, String></code><br/> |
| [encodeFragmentString](encode-fragment-string.md) | <code>fun encodeFragmentString(fragment: String): String</code><br/>Encodes the fragment part of a URI, it can contain PCHAR* with / and ? |
| [encodePathParamName](encode-path-param-name.md) | <code>fun encodePathParamName(pathParamName: String): String</code><br/>Encodes a string to be a valid path parameter name, which means it can contain PCHAR* without "=" or ";". Uses<br/>UTF-8. |
| [encodePathParamValue](encode-path-param-value.md) | <code>fun encodePathParamValue(pathParamValue: String): String</code><br/>Encodes a string to be a valid path parameter value, which means it can contain PCHAR* without ";". Uses UTF-8. |
| [encodePathSegment](encode-path-segment.md) | <code>fun encodePathSegment(pathSegment: String): String</code><br/>Encodes a string to be a valid path segment, which means it can contain PCHAR* only (do not put path parameters or<br/>they will be escaped. Uses UTF-8. |
| [encodePathStringFromSegments](encode-path-string-from-segments.md) | <code>fun encodePathStringFromSegments(decodedPath: List<String>): String</code><br/> |
| [encodeQueryMapToString](encode-query-map-to-string.md) | <code>fun encodeQueryMapToString(decodedQuery: Map<String, String>): String</code><br/> |
| [encodeQueryMultiMapToString](encode-query-multi-map-to-string.md) | <code>fun encodeQueryMultiMapToString(decodedQuery: Map<String, List<String>>): String</code><br/> |
| [encodeQueryNameOrValue](encode-query-name-or-value.md) | <code>fun encodeQueryNameOrValue(queryNameOrValue: String): String</code><br/>Encodes a string to be a valid query, which means it can contain PCHAR* | "?" | "/" without "=" | "&amp;" | "+". Uses<br/>UTF-8. |
| [encodeQueryNameOrValueNoParen](encode-query-name-or-value-no-paren.md) | <code>fun encodeQueryNameOrValueNoParen(queryNameOrValueNoParen: String): String</code><br/>Encodes a string to be a valid query with no parenthesis, which means it can contain PCHAR* | "?" | "/" without<br/>"=" | "&amp;" | "+" | "(" | ")". It strips parenthesis. Uses UTF-8. |
| [encodeUserInfo](encode-user-info.md) | <code>fun encodeUserInfo(userInfo: String): String</code><br/>Encodes the fragment part of a URI, it can contain PCHAR* with / and ? |
