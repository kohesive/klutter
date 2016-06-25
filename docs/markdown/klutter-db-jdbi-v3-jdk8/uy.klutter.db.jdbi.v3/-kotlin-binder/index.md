[uy.klutter.db.jdbi.v3](../index.md) / [KotlinBinder](.)


# KotlinBinder
<code>class KotlinBinder : Binder<Bind, Any></code> [(source)](https://github.com/kohesive/klutter/blob/master/db-jdbi-v3-jdk8/src/main/kotlin/uy/klutter/db/jdbi/v3/KotlinBinder.kt#L17)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>KotlinBinder(method: [Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html), paramIdx: Int)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [method](method.md) | <code>val method: [Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html)</code><br/> |
| [paramIdx](param-idx.md) | <code>val paramIdx: Int</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bind](bind.md) | <code>fun bind(q: SQLStatement<*>, parameter: [Parameter](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Parameter.html), bind: Bind?, arg: Any?): Unit</code><br/> |
