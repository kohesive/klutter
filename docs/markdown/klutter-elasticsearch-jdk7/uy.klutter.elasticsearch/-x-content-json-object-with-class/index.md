[uy.klutter.elasticsearch](../index.md) / [XContentJsonObjectWithClass](.)


# XContentJsonObjectWithClass
`class XContentJsonObjectWithClass<T&nbsp;:&nbsp;Any>&nbsp;:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L41)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `XContentJsonObjectWithClass(x:&nbsp;XContentBuilder)` |

### Inherited Properties

|&nbsp;|&nbsp;|
|---|---|
| [x](../-x-content-json-object/x.md) | `val x: XContentBuilder` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](-array.md) | `fun Array(field:&nbsp;KProperty1<T,&nbsp;*>, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [Object](-object.md) | `fun Object(field:&nbsp;KProperty1<T,&nbsp;*>, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](-object-with-field-class.md) | `fun <R&nbsp;:&nbsp;Any> ObjectWithFieldClass(field:&nbsp;KProperty1<T,&nbsp;*>, init:&nbsp;XContentJsonObjectWithClass<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | `fun <R&nbsp;:&nbsp;Enum<R>> ObjectWithFieldEnum(field:&nbsp;KProperty1<T,&nbsp;*>, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [setValue](set-value.md) | `fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;String): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Long): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Int): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Short): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Byte): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Double): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Float): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`
`fun setValue(field:&nbsp;KProperty1<T,&nbsp;*>, value:&nbsp;Boolean): Unit` |
| [setValueNull](set-value-null.md) | `fun setValueNull(field:&nbsp;KProperty1<T,&nbsp;*>): Unit` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](../-x-content-json-object/-array.md) | `fun Array(name:&nbsp;String, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [Object](../-x-content-json-object/-object.md) | `fun Object(name:&nbsp;String, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](../-x-content-json-object/-object-with-field-class.md) | `fun <R&nbsp;:&nbsp;Any> ObjectWithFieldClass(name:&nbsp;String, init:&nbsp;XContentJsonObjectWithClass<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](../-x-content-json-object/-object-with-field-enum.md) | `fun <R&nbsp;:&nbsp;Enum<R>> ObjectWithFieldEnum(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [setValue](../-x-content-json-object/set-value.md) | `fun setValue(name:&nbsp;String, value:&nbsp;String): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Long): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Int): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Short): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Byte): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Double): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Float): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Boolean): Unit` |
| [setValueNull](../-x-content-json-object/set-value-null.md) | `fun setValueNull(name:&nbsp;String): Unit` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.booleanFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).booleanFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [dateFieldMapping](../date-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.dateFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).dateFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [ignoreFieldMapping](../ignore-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.ignoreFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).ignoreFieldMapping(field:&nbsp;String): Unit` |
| [integerFieldMapping](../integer-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.integerFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).integerFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [longFieldMapping](../long-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.longFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).longFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [stringFieldMapping](../string-field-mapping.md) | `fun <T&nbsp;:&nbsp;Any> XContentJsonObjectWithClass<T>.stringFieldMapping(field:&nbsp;KProperty1<T,&nbsp;*>, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit`
`fun [XContentJsonObject](../-x-content-json-object/index.md).stringFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
