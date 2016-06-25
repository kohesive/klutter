[uy.klutter.reflect.conversion](../index.md) / [TypeConverters](.)


# TypeConverters
`class TypeConverters` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L34)



### Types

|&nbsp;|&nbsp;|
|---|---|
| [AskToConverter](-ask-to-converter/index.md) | `data class AskToConverter` |
| [ExactConverter](-exact-converter/index.md) | `data class ExactConverter` |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `TypeConverters(parent:&nbsp;TypeConverters?&nbsp;=&nbsp;null)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [parent](parent.md) | `val parent: TypeConverters?` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [convertValue](convert-value.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> convertValue(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), value:&nbsp;T): R`
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> convertValue(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, value:&nbsp;T): R`
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> convertValue(value:&nbsp;T): R` |
| [findConverter](find-converter.md) | `fun findConverter(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): [ExactConverter](-exact-converter/index.md)?`
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> findConverter(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>): [ExactConverter](-exact-converter/index.md)?` |
| [hasConverter](has-converter.md) | `fun hasConverter(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): Boolean`
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> hasConverter(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>): Boolean` |
| [register](register.md) | `fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> register(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;->&nbsp;R): Unit`
`fun <T&nbsp;:&nbsp;X, X&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> register(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(X)&nbsp;->&nbsp;R): Unit`
`fun <T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any> register(converter:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;->&nbsp;R): Unit`
`fun register(askFunc:&nbsp;([Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html),&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html))&nbsp;->&nbsp;Boolean, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(Any)&nbsp;->&nbsp;Any): Unit` |
