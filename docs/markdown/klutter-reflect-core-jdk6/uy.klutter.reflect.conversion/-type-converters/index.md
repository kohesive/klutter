[uy.klutter.reflect.conversion](../index.md) / [TypeConverters](.)


# TypeConverters
<code>class TypeConverters</code> [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L34)<br/>


### Types

|&nbsp;|&nbsp;|
|---|---|
| [AskToConverter](-ask-to-converter/index.md) | <code>data class AskToConverter</code><br/> |
| [ExactConverter](-exact-converter/index.md) | <code>data class ExactConverter</code><br/> |

### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>TypeConverters(parent: TypeConverters? = null)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [parent](parent.md) | <code>val parent: TypeConverters?</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [convertValue](convert-value.md) | <code>fun <T : Any, R : Any> convertValue(fromType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), value: T): R</code><br/><code>fun <T : Any, R : Any> convertValue(fromType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, value: T): R</code><br/><code>fun <T : Any, R : Any> convertValue(value: T): R</code><br/> |
| [findConverter](find-converter.md) | <code>fun findConverter(fromType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): [ExactConverter](-exact-converter/index.md)?</code><br/><code>fun <T : Any, R : Any> findConverter(fromType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>): [ExactConverter](-exact-converter/index.md)?</code><br/> |
| [hasConverter](has-converter.md) | <code>fun hasConverter(fromType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): Boolean</code><br/><code>fun <T : Any, R : Any> hasConverter(fromType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>): Boolean</code><br/> |
| [register](register.md) | <code>fun <T : Any, R : Any> register(fromType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType: [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), convertFunc: [ExactConverter](-exact-converter/index.md).(T) -> R): Unit</code><br/><code>fun <T : X, X : Any, R : Any> register(fromType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<T>, toType: [TypeReference](../../uy.klutter.reflect/-type-reference/index.md)<R>, convertFunc: [ExactConverter](-exact-converter/index.md).(X) -> R): Unit</code><br/><code>fun <T : Any, R : Any> register(converter: [ExactConverter](-exact-converter/index.md).(T) -> R): Unit</code><br/><code>fun register(askFunc: ([Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), [Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)) -> Boolean, convertFunc: [ExactConverter](-exact-converter/index.md).(Any) -> Any): Unit</code><br/> |
