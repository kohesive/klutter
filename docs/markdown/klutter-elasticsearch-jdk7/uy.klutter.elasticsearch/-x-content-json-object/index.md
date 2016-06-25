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
| [Array](-array.md) | `fun Array(name:&nbsp;String, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [Object](-object.md) | `fun Object(name:&nbsp;String, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](-object-with-field-class.md) | `fun &lt;R&nbsp;:&nbsp;Any&gt; ObjectWithFieldClass(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md)&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | `fun &lt;R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; ObjectWithFieldEnum(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [setValue](set-value.md) | `fun setValue(name:&nbsp;String, value:&nbsp;String): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Long): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Int): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Short): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Byte): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Double): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Float): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Boolean): Unit` |
| [setValueNull](set-value-null.md) | `fun setValueNull(name:&nbsp;String): Unit` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | `fun XContentJsonObject.booleanFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [dateFieldMapping](../date-field-mapping.md) | `fun XContentJsonObject.dateFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [ignoreFieldMapping](../ignore-field-mapping.md) | `fun XContentJsonObject.ignoreFieldMapping(field:&nbsp;String): Unit` |
| [integerFieldMapping](../integer-field-mapping.md) | `fun XContentJsonObject.integerFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [longFieldMapping](../long-field-mapping.md) | `fun XContentJsonObject.longFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [stringFieldMapping](../string-field-mapping.md) | `fun XContentJsonObject.stringFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;XContentJsonObject.()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |

### Inheritors

|&nbsp;|&nbsp;|
|---|---|
| [XContentJsonObjectWithClass](../-x-content-json-object-with-class/index.md) | `class XContentJsonObjectWithClass&lt;T&nbsp;:&nbsp;Any&gt;&nbsp;:&nbsp;XContentJsonObject` |
| [XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md) | `class XContentJsonObjectWithEnum&lt;T&nbsp;:&nbsp;Enum&lt;T&gt;&gt;&nbsp;:&nbsp;XContentJsonObject` |
