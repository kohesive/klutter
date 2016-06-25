[uy.klutter.reflect.conversion](../index.md) / [TypeConverters](index.md) / [register](.)


# register
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> register(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;->&nbsp;R): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L39)
`fun <T&nbsp;:&nbsp;X, X&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> register(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(X)&nbsp;->&nbsp;R): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L44)
`inline fun <reified&nbsp;T&nbsp;:&nbsp;Any, reified&nbsp;R&nbsp;:&nbsp;Any> register(noinline converter:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;->&nbsp;R): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L49)
`fun register(askFunc:&nbsp;([Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html),&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html))&nbsp;->&nbsp;Boolean, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(Any)&nbsp;->&nbsp;Any): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L53)


