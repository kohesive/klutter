package uy.klutter.elasticsearch

import org.elasticsearch.common.xcontent.XContentBuilder


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

public fun mappingsForTypeWithEnum<T: Enum<T>>(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithFields<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObjectWithFields<T>.()->Unit): IndexTypeMapping {
     val mappings = xsonObject {
         ObjectWithFields<EsSystemFields>(type) {
             setValue("dynamic", if (allowDynamic) "true" else "strict")
             initTopLevel()
             ObjectWithFields<T>("properties") {
                 initProperties()
             }
         }
    }
    return IndexTypeMapping(type, mappings)
}

public fun mappingsForType(type: String, allowDynamic: Boolean = false, initTopLevel: XContentJsonObjectWithFields<EsSystemFields>.()->Unit = {}, initProperties:  XContentJsonObject.()->Unit): IndexTypeMapping {
    val mappings = xsonObject {
        ObjectWithFields<EsSystemFields>(type) {
            setValue("dyanmic", if (allowDynamic) "true" else "strict")
            initTopLevel()
            Object("properties") {
                initProperties()
            }
        }
    }
    return IndexTypeMapping(type, mappings)
}

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.stringField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
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

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.ignoreField(field: T) {
    Object(field) {
        setValue("type", "string")
        setValue("store", false)
        setValue("index", "no")
    }
}

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.dateField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "date")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.booleanField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "boolean")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.integerField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "integer")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}

public fun <T: Enum<T>> XContentJsonObjectWithFields<T>.longField(field: T, indexed: EsIndexedField = EsIndexedField.NOT_ANALYZED, stored: EsStoredField = EsStoredField.NOT_STORED, init: XContentJsonObject.()->Unit) {
    Object(field) {
        setValue("type", "long")
        setValue("store", stored == EsStoredField.STORED)
        if (indexed == EsIndexedField.NOT_INDEXED) {
            setValue("index", "no")
        }
        init()
    }
}