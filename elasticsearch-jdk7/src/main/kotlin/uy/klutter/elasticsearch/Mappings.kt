package uy.klutter.elasticsearch

import org.elasticsearch.common.xcontent.XContentBuilder
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1


public data class IndexTypeMapping(val type: String, val json: XContentBuilder)

public enum class EsSystemFields {
    _uid, _id, _type, _source, _all, _analyzer, _boost, _parent, _field_names, _routing, _index, _size, _timestamp, _ttl
}

public enum class EsStoredField {
    STORED, NOT_STORED
}

public enum class EsIndexedField {
    NOT_ANALYZED, ANALYZED, NOT_INDEXED
}

public fun mappingsForTypeWithEnum<T: Enum<T>>(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithEnum<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObjectWithEnum<T>.()->Unit): IndexTypeMapping {
     val mappings = xsonObject {
         ObjectWithFieldEnum<EsSystemFields>(type) {
             setValue("dynamic", if (allowDynamic) "true" else "strict")
             initTopLevel()
             ObjectWithFieldEnum<T>("properties") {
                 initProperties()
             }
         }
    }
    return IndexTypeMapping(type, mappings)
}

@Deprecated("use mappingsForTypeWithClass()")
public fun mappingsForTypeForClass<T: Any>(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithEnum<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObjectWithClass<T>.()->Unit): IndexTypeMapping {
    return mappingsForTypeWithClass(type, allowDynamic, initTopLevel, initProperties)
}

public fun mappingsForTypeWithClass<T: Any>(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithEnum<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObjectWithClass<T>.()->Unit): IndexTypeMapping {
    val mappings = xsonObject {
        ObjectWithFieldEnum<EsSystemFields>(type) {
            setValue("dynamic", if (allowDynamic) "true" else "strict")
            initTopLevel()
            ObjectWithFieldClass<T>("properties") {
                initProperties()
            }
        }
    }
    return IndexTypeMapping(type, mappings)
}

public fun mappingsForType(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithEnum<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObject.()->Unit): IndexTypeMapping {
    val mappings = xsonObject {
        ObjectWithFieldEnum<EsSystemFields>(type) {
            setValue("dyanmic", if (allowDynamic) "true" else "strict")
            initTopLevel()
            Object("properties") {
                initProperties()
            }
        }
    }
    return IndexTypeMapping(type, mappings)
}

@Deprecated("use stringFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.stringField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    stringFieldMapping(field.name(), indexed, stored, init)
}

@Deprecated("use ignoreFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.ignoreField(field: T) {
    ignoreFieldMapping(field.name())
}

@Deprecated("use dateFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.dateField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    dateFieldMapping(field.name(), indexed, stored, init)
}

@Deprecated("use booleanFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.booleanField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    booleanFieldMapping(field.name(), indexed, stored, init)
}

@Deprecated("use integerFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.integerField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    integerFieldMapping(field.name(), indexed, stored, init)
}

@Deprecated("use longFieldMapping")
public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.longField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    longFieldMapping(field.name(), indexed, stored, init)
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.stringFieldMapping(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    stringFieldMapping(field.name(), indexed, stored, init)
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.ignoreFieldMapping(field: T) {
    ignoreFieldMapping(field.name())
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.dateFieldMapping(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    dateFieldMapping(field.name(), indexed, stored, init)
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.booleanFieldMapping(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    booleanFieldMapping(field.name(), indexed, stored, init)
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.integerFieldMapping(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    integerFieldMapping(field.name(), indexed, stored, init)
}

public fun <T: Enum<T>> XContentJsonObjectWithEnum<T>.longFieldMapping(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    longFieldMapping(field.name(), indexed, stored, init)
}

// ===


public fun <T: Any> XContentJsonObjectWithClass<T>.stringFieldMapping(field: KProperty1<T, *>, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    stringFieldMapping(field.name, indexed, stored, init)
}

public fun <T: Any> XContentJsonObjectWithClass<T>.ignoreFieldMapping(field:  KProperty1<T, *>) {
    ignoreFieldMapping(field.name)
}

public fun <T: Any> XContentJsonObjectWithClass<T>.dateFieldMapping(field:  KProperty1<T, *>, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    dateFieldMapping(field.name, indexed, stored, init)
}

public fun <T: Any> XContentJsonObjectWithClass<T>.booleanFieldMapping(field:  KProperty1<T, *>, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    booleanFieldMapping(field.name, indexed, stored, init)
}

public fun <T: Any> XContentJsonObjectWithClass<T>.integerFieldMapping(field:  KProperty1<T, *>, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    integerFieldMapping(field.name, indexed, stored, init)
}

public fun <T: Any> XContentJsonObjectWithClass<T>.longFieldMapping(field:  KProperty1<T, *>, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    longFieldMapping(field.name, indexed, stored, init)
}

// ===

public fun XContentJsonObject.stringFieldMapping(field: String, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    Object(field) {
        setValue("type", "string")
        setValue("store", stored == EsStoredField.STORED)
        setValue("index", when(indexed) {
            EsIndexedField.ANALYZED -> "analyzed"
            EsIndexedField.NOT_ANALYZED -> "not_analyzed"
            else -> "no"
        })
        init()
    }
}

public fun XContentJsonObject.ignoreFieldMapping(field: String) {
    Object(field) {
        setValue("type", "string")
        setValue("store", false)
        setValue("index", "no")
    }
}

public fun XContentJsonObject.dateFieldMapping(field: String, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    Object(field) {
        setValue("type", "date")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun XContentJsonObject.booleanFieldMapping(field: String, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit = {}) {
    Object(field) {
        setValue("type", "boolean")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun XContentJsonObject.integerFieldMapping(field: String, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "integer")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun XContentJsonObject.longFieldMapping(field: String, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "long")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}