[uy.klutter.core.uri](../index.md) / [UrlEncoding](index.md) / [encodeQueryNameOrValueNoParen](.)


# encodeQueryNameOrValueNoParen

`fun encodeQueryNameOrValueNoParen(queryNameOrValueNoParen:&nbsp;String): String` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L252)

Encodes a string to be a valid query with no parenthesis, which means it can contain PCHAR* | "?" | "/" without
"=" | "&amp;" | "+" | "(" | ")". It strips parenthesis. Uses UTF-8.



