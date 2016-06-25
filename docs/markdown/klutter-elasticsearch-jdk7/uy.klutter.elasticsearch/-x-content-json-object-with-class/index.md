[uy.klutter.elasticsearch](../index.md) / [XContentJsonObjectWithClass](.)


# XContentJsonObjectWithClass
<code>class XContentJsonObjectWithClass<T : Any> : [XContentJsonObject](../-x-content-json-object/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L41)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>XContentJsonObjectWithClass(x: XContentBuilder)</code><br/> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [x](../-x-content-json-object/x.md) | <code>val x: XContentBuilder</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](-array.md) | <code>fun Array(field: KProperty1<T, *>, init: [XContentJsonArray](../-x-content-json-array/index.md).() -> Unit): Unit</code><br/> |
| [Object](-object.md) | <code>fun Object(field: KProperty1<T, *>, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [ObjectWithFieldClass](-object-with-field-class.md) | <code>fun <R : Any> ObjectWithFieldClass(field: KProperty1<T, *>, init: XContentJsonObjectWithClass<R>.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | <code>fun <R : Enum<R>> ObjectWithFieldEnum(field: KProperty1<T, *>, init: [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.() -> Unit): Unit</code><br/> |
| [setValue](set-value.md) | <code>fun setValue(field: KProperty1<T, *>, value: String): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Long): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Int): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Short): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Byte): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Double): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Float): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun setValue(field: KProperty1<T, *>, value: Boolean): Unit</code><br/> |
| [setValueNull](set-value-null.md) | <code>fun setValueNull(field: KProperty1<T, *>): Unit</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](../-x-content-json-object/-array.md) | <code>fun Array(name: String, init: [XContentJsonArray](../-x-content-json-array/index.md).() -> Unit): Unit</code><br/> |
| [Object](../-x-content-json-object/-object.md) | <code>fun Object(name: String, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [ObjectWithFieldClass](../-x-content-json-object/-object-with-field-class.md) | <code>fun <R : Any> ObjectWithFieldClass(name: String, init: XContentJsonObjectWithClass<R>.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldEnum](../-x-content-json-object/-object-with-field-enum.md) | <code>fun <R : Enum<R>> ObjectWithFieldEnum(name: String, init: [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.() -> Unit): Unit</code><br/> |
| [setValue](../-x-content-json-object/set-value.md) | <code>fun setValue(name: String, value: String): Unit</code><br/><code>fun setValue(name: String, value: Long): Unit</code><br/><code>fun setValue(name: String, value: Int): Unit</code><br/><code>fun setValue(name: String, value: Short): Unit</code><br/><code>fun setValue(name: String, value: Byte): Unit</code><br/><code>fun setValue(name: String, value: Double): Unit</code><br/><code>fun setValue(name: String, value: Float): Unit</code><br/><code>fun setValue(name: String, value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun setValue(name: String, value: Boolean): Unit</code><br/> |
| [setValueNull](../-x-content-json-object/set-value-null.md) | <code>fun setValueNull(name: String): Unit</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.booleanFieldMapping(field: KProperty1<T, *>, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).booleanFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [dateFieldMapping](../date-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.dateFieldMapping(field: KProperty1<T, *>, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).dateFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [ignoreFieldMapping](../ignore-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.ignoreFieldMapping(field: KProperty1<T, *>): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).ignoreFieldMapping(field: String): Unit</code><br/> |
| [integerFieldMapping](../integer-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.integerFieldMapping(field: KProperty1<T, *>, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).integerFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [longFieldMapping](../long-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.longFieldMapping(field: KProperty1<T, *>, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).longFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [stringFieldMapping](../string-field-mapping.md) | <code>fun <T : Any> XContentJsonObjectWithClass<T>.stringFieldMapping(field: KProperty1<T, *>, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).stringFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
