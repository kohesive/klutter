[uy.klutter.reflect.full](../index.md) / [KCallableFuncRefOrLambda](.)


# KCallableFuncRefOrLambda
`class KCallableFuncRefOrLambda&lt;T&nbsp;:&nbsp;Function&lt;R&gt;, out&nbsp;R&gt;&nbsp;:&nbsp;KCallable&lt;R&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-full-jdk6/src/main/kotlin/uy/klutter/reflect/full/KT-9005.kt#L9)



### Properties

|&nbsp;|&nbsp;|
|---|---|
| [annotations](annotations.md) | `val annotations: List&lt;Annotation&gt;` |
| [name](name.md) | `val name: String` |
| [parameters](parameters.md) | `val parameters: List&lt;KParameter&gt;` |
| [returnType](return-type.md) | `val returnType: KType` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [call](call.md) | `fun call(vararg args:&nbsp;Any?): R` |
| [callBy](call-by.md) | `fun callBy(args:&nbsp;Map&lt;KParameter,&nbsp;Any?&gt;): R` |

### Companion Object Functions

|&nbsp;|&nbsp;|
|---|---|
| [fromInstance](from-instance.md) | `fun &lt;T&nbsp;:&nbsp;Function&lt;R&gt;, R&gt; fromInstance(functionInstance:&nbsp;T): KCallableFuncRefOrLambda&lt;T,&nbsp;R&gt;`<br/>`fun &lt;T&nbsp;:&nbsp;Function&lt;R&gt;, R&gt; fromInstance(functionInstance:&nbsp;T, name:&nbsp;String, annotations:&nbsp;List&lt;Annotation&gt;): KCallableFuncRefOrLambda&lt;T,&nbsp;R&gt;` |
