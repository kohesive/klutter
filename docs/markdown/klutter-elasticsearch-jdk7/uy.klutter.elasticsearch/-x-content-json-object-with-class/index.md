[uy.klutter.elasticsearch](../index.md) / [XContentJsonObjectWithClass](.)


# XContentJsonObjectWithClass
`class XContentJsonObjectWithClass&lt;T&nbsp;:&nbsp;Any&gt;&nbsp;:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md)` [(source)](https://github.com/kohesive/klutter/blob/master/elasticsearch-jdk7/src/main/kotlin/uy/klutter/elasticsearch/XContent.kt#L41)



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
| [Array](-array.md) | `fun Array(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [Object](-object.md) | `fun Object(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](-object-with-field-class.md) | `fun &lt;R&nbsp;:&nbsp;Any&gt; ObjectWithFieldClass(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, init:&nbsp;XContentJsonObjectWithClass&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](-object-with-field-enum.md) | `fun &lt;R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; ObjectWithFieldEnum(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [setValue](set-value.md) | `fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;String): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Long): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Int): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Short): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Byte): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Double): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Float): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`<br/>`fun setValue(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, value:&nbsp;Boolean): Unit` |
| [setValueNull](set-value-null.md) | `fun setValueNull(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;): Unit` |

### Inherited Functions

|&nbsp;|&nbsp;|
|---|---|
| [Array](../-x-content-json-object/-array.md) | `fun Array(name:&nbsp;String, init:&nbsp;[XContentJsonArray](../-x-content-json-array/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [Object](../-x-content-json-object/-object.md) | `fun Object(name:&nbsp;String, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldClass](../-x-content-json-object/-object-with-field-class.md) | `fun &lt;R&nbsp;:&nbsp;Any&gt; ObjectWithFieldClass(name:&nbsp;String, init:&nbsp;XContentJsonObjectWithClass&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [ObjectWithFieldEnum](../-x-content-json-object/-object-with-field-enum.md) | `fun &lt;R&nbsp;:&nbsp;Enum&lt;R&gt;&gt; ObjectWithFieldEnum(name:&nbsp;String, init:&nbsp;[XContentJsonObjectWithEnum](../-x-content-json-object-with-enum/index.md)&lt;R&gt;.()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [setValue](../-x-content-json-object/set-value.md) | `fun setValue(name:&nbsp;String, value:&nbsp;String): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Long): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Int): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Short): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Byte): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Double): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Float): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;[BigDecimal](http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html)): Unit`<br/>`fun setValue(name:&nbsp;String, value:&nbsp;Boolean): Unit` |
| [setValueNull](../-x-content-json-object/set-value-null.md) | `fun setValueNull(name:&nbsp;String): Unit` |

### Extension Functions

|&nbsp;|&nbsp;|
|---|---|
| [booleanFieldMapping](../boolean-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.booleanFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).booleanFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [dateFieldMapping](../date-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.dateFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).dateFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
| [ignoreFieldMapping](../ignore-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.ignoreFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).ignoreFieldMapping(field:&nbsp;String): Unit` |
| [integerFieldMapping](../integer-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.integerFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).integerFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [longFieldMapping](../long-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.longFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).longFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit): Unit` |
| [stringFieldMapping](../string-field-mapping.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; XContentJsonObjectWithClass&lt;T&gt;.stringFieldMapping(field:&nbsp;KProperty1&lt;T,&nbsp;*&gt;, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit`<br/>`fun [XContentJsonObject](../-x-content-json-object/index.md).stringFieldMapping(field:&nbsp;String, indexed:&nbsp;[EsIndexedField](../-es-indexed-field/index.md)&nbsp;=&nbsp;EsIndexedField.NOT_ANALYZED, stored:&nbsp;[EsStoredField](../-es-stored-field/index.md)&nbsp;=&nbsp;EsStoredField.NOT_STORED, init:&nbsp;[XContentJsonObject](../-x-content-json-object/index.md).()&nbsp;-&gt;&nbsp;Unit&nbsp;=&nbsp;{}): Unit` |
