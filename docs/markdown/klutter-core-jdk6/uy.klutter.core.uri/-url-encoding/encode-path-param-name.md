[uy.klutter.core.uri](../index.md) / [UrlEncoding](index.md) / [encodePathParamName](.)


# encodePathParamName
`fun encodePathParamName(pathParamName:&nbsp;String): String` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/uri/UrlEncoding.kt#L211)

Encodes a string to be a valid path parameter name, which means it can contain PCHAR* without "=" or ";". Uses
UTF-8.


