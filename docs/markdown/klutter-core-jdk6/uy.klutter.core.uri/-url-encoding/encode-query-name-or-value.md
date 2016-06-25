[uy.klutter.core.uri](../index.md) / [UrlEncoding](index.md) / [encodeQueryNameOrValue](.)


# encodeQueryNameOrValue
`fun encodeQueryNameOrValue(queryNameOrValue:&nbsp;String): String` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L238)

Encodes a string to be a valid query, which means it can contain PCHAR* | "?" | "/" without "=" | "&amp;" | "+". Uses
UTF-8.


