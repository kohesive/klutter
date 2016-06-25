[uy.klutter.db.jdbi.v2](../index.md) / [org.skife.jdbi.v2.Query](.)


### Extensions for org.skife.jdbi.v2.Query

|&nbsp;|&nbsp;|
|---|---|
| [map](map.md) | `fun <O&nbsp;:&nbsp;Map<String,&nbsp;Any?>, T&nbsp;:&nbsp;Any> Query<O>.map(toClass:&nbsp;KClass<T>): Query<T>` |
| [useSequence](use-sequence.md) | `fun <O&nbsp;:&nbsp;Any> Query<O>.useSequence(block:&nbsp;(Sequence<O>)&nbsp;->&nbsp;Unit): Unit`
`fun <O&nbsp;:&nbsp;Map<String,&nbsp;Any?>, T&nbsp;:&nbsp;Any> Query<O>.useSequence(toClass:&nbsp;KClass<T>, block:&nbsp;(Sequence<T>)&nbsp;->&nbsp;Unit): Unit` |
