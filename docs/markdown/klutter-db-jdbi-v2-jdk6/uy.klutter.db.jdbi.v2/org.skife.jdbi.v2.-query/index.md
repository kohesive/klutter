[uy.klutter.db.jdbi.v2](../index.md) / [org.skife.jdbi.v2.Query](.)


### Extensions for org.skife.jdbi.v2.Query

|&nbsp;|&nbsp;|
|---|---|
| [map](map.md) | <code>fun <O : Map<String, Any?>, T : Any> Query<O>.map(toClass: KClass<T>): Query<T></code><br/> |
| [useSequence](use-sequence.md) | <code>fun <O : Any> Query<O>.useSequence(block: (Sequence<O>) -> Unit): Unit</code><br/><code>fun <O : Map<String, Any?>, T : Any> Query<O>.useSequence(toClass: KClass<T>, block: (Sequence<T>) -> Unit): Unit</code><br/> |
