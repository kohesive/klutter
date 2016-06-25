[uy.klutter.reflect.conversion](../index.md) / [TypeConverters](index.md) / [convertValue](.)


# convertValue
<code>fun <T : Any, R : Any> convertValue(fromType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), value: T): R</code> [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L83)<br/><code>fun <T : Any, R : Any> convertValue(fromType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, value: T): R</code> [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L88)<br/><code>inline fun <reified T : Any, reified R : Any> convertValue(value: T): R</code> [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L92)<br/>

