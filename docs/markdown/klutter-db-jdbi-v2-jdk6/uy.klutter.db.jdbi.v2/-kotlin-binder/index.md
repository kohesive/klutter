[uy.klutter.db.jdbi.v2](../index.md) / [KotlinBinder](.)


# KotlinBinder
<code>class KotlinBinder : Binder<Bind, Any></code> [(source)](https://github.com/kohesive/klutter/blob/master/db-jdbi-v2-jdk6/src/main/kotlin/uy/klutter/db/jdbi/v2/KotlinBinder.kt#L18)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>KotlinBinder(method: [Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html), paramIdx: Int)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [method](method.md) | <code>val method: [Method](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html)</code><br/> |
| [paramIdx](param-idx.md) | <code>val paramIdx: Int</code><br/> |
| [parameter](parameter.md) | <code>val parameter: [Parameter](http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Parameter.html)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [bind](bind.md) | <code>fun bind(q: SQLStatement<*>, bind: Bind?, arg: Any?): Unit</code><br/> |
