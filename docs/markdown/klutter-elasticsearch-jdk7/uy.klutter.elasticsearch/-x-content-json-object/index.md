[uy.klutter.elasticsearch](../index.md) / [XContentJsonObject](.)


# XContentJsonObject
`open class XContentJsonObject` [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L74)



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `XContentJsonObject(x:&nbsp;XContentBuilder)` |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [x](x.md) | `val x: XContentBuilder` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](-array.md) | `fun Array(name:&nbsp;String, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;->&nbsp;Unit): Unit` |
| [Object](-object.md) | `fun Object(name:&nbsp;String, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](-object-with-field-class.md) | `fun <R&nbsp;:&nbsp;Any> ObjectWithFieldClass(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md)<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | `fun <R&nbsp;:&nbsp;Enum<R>> ObjectWithFieldEnum(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)<R>.()&nbsp;->&nbsp;Unit): Unit` |
| [setValue](set-value.md) | `fun setValue(name:&nbsp;String, value:&nbsp;String): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Long): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Int): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Short): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Byte): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Double): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Float): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`
`fun setValue(name:&nbsp;String, value:&nbsp;Boolean): Unit` |
| [setValueNull](set-value-null.md) | `fun setValueNull(name:&nbsp;String): Unit` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | `fun XContentJsonObject.booleanFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [dateFieldMapping](../date-field-mapping.md) | `fun XContentJsonObject.dateFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [ignoreFieldMapping](../ignore-field-mapping.md) | `fun XContentJsonObject.ignoreFieldMapping(field:&nbsp;String): Unit` |
| [integerFieldMapping](../integer-field-mapping.md) | `fun XContentJsonObject.integerFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit): Unit` |
| [longFieldMapping](../long-field-mapping.md) | `fun XContentJsonObject.longFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit): Unit` |
| [stringFieldMapping](../string-field-mapping.md) | `fun XContentJsonObject.stringFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;->&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md) | `class XContentJsonObjectWithClass<T&nbsp;:&nbsp;Any>&nbsp;:&nbsp;XContentJsonObject` |
| [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md) | `class XContentJsonObjectWithEnum<T&nbsp;:&nbsp;Enum<T>>&nbsp;:&nbsp;XContentJsonObject` |
