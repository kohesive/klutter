[uy.klutter.reflect.full](../index.md) / [KCallableFuncRefOrLambda](.)


# KCallableFuncRefOrLambda
`class KCallableFuncRefOrLambda<T&nbsp;:&nbsp;Function<R>, out&nbsp;R>&nbsp;:&nbsp;KCallable<R>` [(source)](https://github.com/kohesive/klutter/blob/master/reflect-full-jdk6/src/main/kotlin/uy/klutter/reflect/full/KT-9005.kt#L9)



### Properties

|&nbsp;|&nbsp;|
|---|---|
| [annotations](annotations.md) | `val annotations: List<Annotation>` |
| [name](name.md) | `val name: String` |
| [parameters](parameters.md) | `val parameters: List<KParameter>` |
| [returnType](return-type.md) | `val returnType: KType` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [call](call.md) | `fun call(vararg args:&nbsp;Any?): R` |
| [callBy](call-by.md) | `fun callBy(args:&nbsp;Map<KParameter,&nbsp;Any?>): R` |

### Companion Object Functions

|&nbsp;|&nbsp;|
|---|---|
| [fromInstance](from-instance.md) | `fun <T&nbsp;:&nbsp;Function<R>, R> fromInstance(functionInstance:&nbsp;T): KCallableFuncRefOrLambda<T,&nbsp;R>`
`fun <T&nbsp;:&nbsp;Function<R>, R> fromInstance(functionInstance:&nbsp;T, name:&nbsp;String, annotations:&nbsp;List<Annotation>): KCallableFuncRefOrLambda<T,&nbsp;R>` |
