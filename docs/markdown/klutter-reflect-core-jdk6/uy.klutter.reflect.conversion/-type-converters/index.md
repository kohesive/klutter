[uy.klutter.reflect.conversion](../index.md) / [TypeConverters](.)


# TypeConverters

`class TypeConverters` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-core-jdk6/src/main/kotlin/uy/klutter/reflect/conversion/Converters.kt#L34)



### Types


| [AskToConverter](-ask-to-converter/index.md) | `data class AskToConverter` |
| [ExactConverter](-exact-converter/index.md) | `data class ExactConverter` |


### Constructors


| [&lt;init&gt;](-init-.md) | `TypeConverters(parent:&nbsp;TypeConverters?&nbsp;=&nbsp;null)` |


### Properties


| [parent](parent.md) | `val parent: TypeConverters?` |


### Functions


| [convertValue](convert-value.md) | `fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; convertValue(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), value:&nbsp;T): R`
`fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; convertValue(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;T&gt;, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;R&gt;, value:&nbsp;T): R`
`fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; convertValue(value:&nbsp;T): R` |
| [findConverter](find-converter.md) | `fun findConverter(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): [ExactConverter](-exact-converter/index.md)?`
`fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; findConverter(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;T&gt;, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;R&gt;): [ExactConverter](-exact-converter/index.md)?` |
| [hasConverter](has-converter.md) | `fun hasConverter(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html)): Boolean`
`fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; hasConverter(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;T&gt;, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;R&gt;): Boolean` |
| [register](register.md) | `fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; register(fromType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), toType:&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html), convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;-&gt;&nbsp;R): Unit`
`fun &lt;T&nbsp;:&nbsp;X, X&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; register(fromType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;T&gt;, toType:&nbsp;[TypeReference](../../uy.klutter.reflect/-type-reference/index.md)&lt;R&gt;, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(X)&nbsp;-&gt;&nbsp;R): Unit`
`fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; register(converter:&nbsp;[ExactConverter](-exact-converter/index.md).(T)&nbsp;-&gt;&nbsp;R): Unit`
`fun register(askFunc:&nbsp;([Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html),&nbsp;[Type](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Type.html))&nbsp;-&gt;&nbsp;Boolean, convertFunc:&nbsp;[ExactConverter](-exact-converter/index.md).(Any)&nbsp;-&gt;&nbsp;Any): Unit` |

