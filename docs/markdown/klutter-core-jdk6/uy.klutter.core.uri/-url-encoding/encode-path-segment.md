[uy.klutter.core.uri](../index.md) / [UrlEncoding](index.md) / [encodePathSegment](.)


# encodePathSegment
<code>fun encodePathSegment(pathSegment: String): String</code> [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L268)<br/>
Encodes a string to be a valid path segment, which means it can contain PCHAR* only (do not put path parameters or
they will be escaped. Uses UTF-8.


