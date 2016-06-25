[uy.klutter.elasticsearch](../index.md) / [XContentJsonArray](.)


# XContentJsonArray
<code>class XContentJsonArray</code> [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L107)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>XContentJsonArray(x: XContentBuilder)</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [addArray](add-array.md) | <code>fun addArray(init: XContentJsonArray.() -> Unit): Unit</code><br/> |
| [addObject](add-object.md) | <code>fun addObject(init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [addValue](add-value.md) | <code>fun addValue(value: String): Unit</code><br/><code>fun addValue(value: Long): Unit</code><br/><code>fun addValue(value: Int): Unit</code><br/><code>fun addValue(value: Short): Unit</code><br/><code>fun addValue(value: Byte): Unit</code><br/><code>fun addValue(value: Double): Unit</code><br/><code>fun addValue(value: Float): Unit</code><br/><code>fun addValue(value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun addValue(value: Boolean): Unit</code><br/> |
| [addValueNull](add-value-null.md) | <code>fun addValueNull(): Unit</code><br/> |
