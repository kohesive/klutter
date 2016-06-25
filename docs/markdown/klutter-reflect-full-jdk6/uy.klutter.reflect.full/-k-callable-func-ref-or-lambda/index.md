[uy.klutter.reflect.full](../index.md) / [KCallableFuncRefOrLambda](.)


# KCallableFuncRefOrLambda
<code>class KCallableFuncRefOrLambda<T : Function<R>, out R> : KCallable<R></code> [(source)](https://github.com/kohesive/klutter/blob/master/reflect-full-jdk6/src/main/kotlin/uy/klutter/reflect/full/KT-9005.kt#L9)<br/>


### Properties

|&nbsp;|&nbsp;|
|---|---|
| [annotations](annotations.md) | <code>val annotations: List<Annotation></code><br/> |
| [name](name.md) | <code>val name: String</code><br/> |
| [parameters](parameters.md) | <code>val parameters: List<KParameter></code><br/> |
| [returnType](return-type.md) | <code>val returnType: KType</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [call](call.md) | <code>fun call(vararg args: Any?): R</code><br/> |
| [callBy](call-by.md) | <code>fun callBy(args: Map<KParameter, Any?>): R</code><br/> |

### Companion Object Functions

|&nbsp;|&nbsp;|
|---|---|
| [fromInstance](from-instance.md) | <code>fun <T : Function<R>, R> fromInstance(functionInstance: T): KCallableFuncRefOrLambda<T, R></code><br/><code>fun <T : Function<R>, R> fromInstance(functionInstance: T, name: String, annotations: List<Annotation>): KCallableFuncRefOrLambda<T, R></code><br/> |
