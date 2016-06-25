[uy.klutter.elasticsearch](../index.md) / [XContentJsonObjectWithEnum](.)


# XContentJsonObjectWithEnum
<code>class XContentJsonObjectWithEnum<T : Enum<T>> : [XContentJsonObject](../-x-content-json-object/index.md)</code> [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L8)<br/>


### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>XContentJsonObjectWithEnum(x: XContentBuilder)</code><br/> |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [x](../-x-content-json-object/x.md) | <code>val x: XContentBuilder</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](-array.md) | <code>fun Array(field: T, init: [XContentJsonArray](../-x-content-json-array/index.md).() -> Unit): Unit</code><br/> |
| [Object](-object.md) | <code>fun Object(field: T, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [ObjectWithFieldClass](-object-with-field-class.md) | <code>fun <R : Any> ObjectWithFieldClass(field: T, init: [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md)<R>.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | <code>fun <R : Enum<R>> ObjectWithFieldEnum(field: T, init: XContentJsonObjectWithEnum<R>.() -> Unit): Unit</code><br/> |
| [setValue](set-value.md) | <code>fun setValue(field: T, value: String): Unit</code><br/><code>fun setValue(field: T, value: Long): Unit</code><br/><code>fun setValue(field: T, value: Int): Unit</code><br/><code>fun setValue(field: T, value: Short): Unit</code><br/><code>fun setValue(field: T, value: Byte): Unit</code><br/><code>fun setValue(field: T, value: Double): Unit</code><br/><code>fun setValue(field: T, value: Float): Unit</code><br/><code>fun setValue(field: T, value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun setValue(field: T, value: Boolean): Unit</code><br/> |
| [setValueNull](set-value-null.md) | <code>fun setValueNull(field: T): Unit</code><br/> |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](../-x-content-json-object/-array.md) | <code>fun Array(name: String, init: [XContentJsonArray](../-x-content-json-array/index.md).() -> Unit): Unit</code><br/> |
| [Object](../-x-content-json-object/-object.md) | <code>fun Object(name: String, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [ObjectWithFieldClass](../-x-content-json-object/-object-with-field-class.md) | <code>fun <R : Any> ObjectWithFieldClass(name: String, init: [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md)<R>.() -> Unit): Unit</code><br/> |
| [ObjectWithFieldEnum](../-x-content-json-object/-object-with-field-enum.md) | <code>fun <R : Enum<R>> ObjectWithFieldEnum(name: String, init: XContentJsonObjectWithEnum<R>.() -> Unit): Unit</code><br/> |
| [setValue](../-x-content-json-object/set-value.md) | <code>fun setValue(name: String, value: String): Unit</code><br/><code>fun setValue(name: String, value: Long): Unit</code><br/><code>fun setValue(name: String, value: Int): Unit</code><br/><code>fun setValue(name: String, value: Short): Unit</code><br/><code>fun setValue(name: String, value: Byte): Unit</code><br/><code>fun setValue(name: String, value: Double): Unit</code><br/><code>fun setValue(name: String, value: Float): Unit</code><br/><code>fun setValue(name: String, value: [BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit</code><br/><code>fun setValue(name: String, value: Boolean): Unit</code><br/> |
| [setValueNull](../-x-content-json-object/set-value-null.md) | <code>fun setValueNull(name: String): Unit</code><br/> |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanField](../boolean-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~booleanField~~(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [booleanFieldMapping](../boolean-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.booleanFieldMapping(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).booleanFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [dateField](../date-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~dateField~~(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [dateFieldMapping](../date-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.dateFieldMapping(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).dateFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [ignoreField](../ignore-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~ignoreField~~(field: T): Unit</code><br/> |
| [ignoreFieldMapping](../ignore-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.ignoreFieldMapping(field: T): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).ignoreFieldMapping(field: String): Unit</code><br/> |
| [integerField](../integer-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~integerField~~(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [integerFieldMapping](../integer-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.integerFieldMapping(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).integerFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [longField](../long-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~longField~~(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [longFieldMapping](../long-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.longFieldMapping(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).longFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit): Unit</code><br/> |
| [stringField](../string-field.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.~~stringField~~(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
| [stringFieldMapping](../string-field-mapping.md) | <code>fun <T : Enum<T>> XContentJsonObjectWithEnum<T>.stringFieldMapping(field: T, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/><code>fun [XContentJsonObject](../-x-content-json-object/index.md).stringFieldMapping(field: String, indexed: [EsIndexedField](../-es-indexed-field/index.md) = EsIndexedField.NOT_ANALYZED, stored: [EsStoredField](../-es-stored-field/index.md) = EsStoredField.NOT_STORED, init: [XContentJsonObject](../-x-content-json-object/index.md).() -> Unit = {}): Unit</code><br/> |
