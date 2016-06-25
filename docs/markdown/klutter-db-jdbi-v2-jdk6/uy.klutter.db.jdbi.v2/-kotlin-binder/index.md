[uy.klutter.db.jdbi.v2](../index.md) / [KotlinBinder](.)


# KotlinBinder
`class KotlinBinder&nbsp;:&nbsp;Binder<Bind,&nbsp;Any>` [(source)](https://github.com/kohesive/klutter/blob/master/db-jdbi-v2-jdk6/src/main/kotlin/uy/klutter/db/jdbi/v2/KotlinBinder.kt#L18)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `KotlinBinder(method:&nbsp;[Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html), paramIdx:&nbsp;Int)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [method](method.md) | `val method: [Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html)` |
| [paramIdx](param-idx.md) | `val paramIdx: Int` |
| [parameter](parameter.md) | `val parameter: [Parameter](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Parameter.html)` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bind](bind.md) | `fun bind(q:&nbsp;SQLStatement<*>, bind:&nbsp;Bind?, arg:&nbsp;Any?): Unit` |
