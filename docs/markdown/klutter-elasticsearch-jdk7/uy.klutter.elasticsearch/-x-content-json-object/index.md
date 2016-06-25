[uy.klutter.elasticsearch](../index.md) / [XContentJsonObject](.)


# XContentJsonObject
<code>open class XContentJsonObject</code> [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L74)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>XContentJsonObject(x: XContentBuilder)</code><br/> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [x](x.md) | <code>val x: XContentBuilder</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](-array.md) | <code>fun Array(name: String, init: [XContentJsonArray](../-x-content-json-array/index.md).() -> Unit): Unit</code><br/> |
| [Object](-object.md) | <code>fun Object(name: String, init: XContentJsonObject.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldClass](-object-with-field-class.md) | <code>fun <R : Any> ObjectWithFieldClass(name: String, init: [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md)<R>.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | <code>fun <R : Enum<R>> ObjectWithFieldEnum(name: String, init: [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.() -> Unit): Unit</code><br/> |
| [setValue](set-value.md) | <code>fun setValue(name: String, value: String): Unit</code><br/><code>fun setValue(name: String, value: Long): Unit</code><br/><code>fun setValue(name: String, value: Int): Unit</code><br/><code>fun setValue(name: String, value: Short): Unit</code><br/><code>fun setValue(name: String, value: Byte): Unit</code><br/><code>fun setValue(name: String, value: Double): Unit</code><br/><code>fun setValue(name: String, value: Float): Unit</code><br/><code>fun setValue(name: String, value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun setValue(name: String, value: Boolean): Unit</code><br/> |
| [setValueNull](set-value-null.md) | <code>fun setValueNull(name: String): Unit</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | <code>fun XContentJsonObject.booleanFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: XContentJsonObject.() -> Unit = {}): Unit</code><br/> |
| [dateFieldMapping](../date-field-mapping.md) | <code>fun XContentJsonObject.dateFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: XContentJsonObject.() -> Unit = {}): Unit</code><br/> |
| [ignoreFieldMapping](../ignore-field-mapping.md) | <code>fun XContentJsonObject.ignoreFieldMapping(field: String): Unit</code><br/> |
| [integerFieldMapping](../integer-field-mapping.md) | <code>fun XContentJsonObject.integerFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: XContentJsonObject.() -> Unit): Unit</code><br/> |
| [longFieldMapping](../long-field-mapping.md) | <code>fun XContentJsonObject.longFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: XContentJsonObject.() -> Unit): Unit</code><br/> |
| [stringFieldMapping](../string-field-mapping.md) | <code>fun XContentJsonObject.stringFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: XContentJsonObject.() -> Unit = {}): Unit</code><br/> |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md) | <code>class XContentJsonObjectWithClass<T : Any> : XContentJsonObject</code><br/> |
| [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md) | <code>class XContentJsonObjectWithEnum<T : Enum<T>> : XContentJsonObject</code><br/> |
