package uy.klutter.elasticsearch

import org.elasticsearch.common.xcontent.XContentBuilder
import org.elasticsearch.common.xcontent.XContentFactory
import java.math.BigDecimal
import kotlin.reflect.KProperty1

public class XContentJsonObjectWithEnum<T: Enum<T>>(x: XContentBuilder): XContentJsonObject(x)  {
    public fun setValue(field: T, value: String): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Long): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Int): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Short): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Byte): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Double): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Float): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: BigDecimal): Unit { x.field(field.name, value) }
    public fun setValue(field: T, value: Boolean): Unit { x.field(field.name, value) }
    public fun setValueNull(field: T): Unit { x.nullField(field.name) }
    public fun Object(field: T, init: XContentJsonObject.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObject(x).init()
        x.endObject()
    }
    public fun <R: Enum<R>> ObjectWithFieldEnum(field: T, init: XContentJsonObjectWithEnum<R>.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObjectWithEnum<R>(x).init()
        x.endObject()
    }
    public fun <R: Any> ObjectWithFieldClass(field: T, init: XContentJsonObjectWithClass<R>.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObjectWithClass<R>(x).init()
        x.endObject()
    }
    public fun Array(field: T, init: XContentJsonArray.()->Unit): Unit {
        x.startArray(field.name)
        XContentJsonArray(x).init()
        x.endArray()
    }
}

public class XContentJsonObjectWithClass<T: Any>(x: XContentBuilder): XContentJsonObject(x)  {
    public fun setValue(field: KProperty1<T, *>, value: String): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Long): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Int): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Short): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Byte): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Double): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Float): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: BigDecimal): Unit { x.field(field.name, value) }
    public fun setValue(field: KProperty1<T, *>, value: Boolean): Unit { x.field(field.name, value) }
    public fun setValueNull(field: KProperty1<T, *>): Unit { x.nullField(field.name) }
    public fun Object(field: KProperty1<T, *>, init: XContentJsonObject.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObject(x).init()
        x.endObject()
    }
    public fun <R: Enum<R>> ObjectWithFieldEnum(field:  KProperty1<T, *>, init: XContentJsonObjectWithEnum<R>.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObjectWithEnum<R>(x).init()
        x.endObject()
    }
    public fun <R: Any> ObjectWithFieldClass(field:  KProperty1<T, *>, init: XContentJsonObjectWithClass<R>.()->Unit): Unit {
        x.startObject(field.name)
        XContentJsonObjectWithClass<R>(x).init()
        x.endObject()
    }
    public fun Array(field: KProperty1<T, *>, init: XContentJsonArray.()->Unit): Unit {
        x.startArray(field.name)
        XContentJsonArray(x).init()
        x.endArray()
    }
}

public open class XContentJsonObject(protected val x: XContentBuilder) {
    public fun setValue(name: String, value: String): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Long): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Int): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Short): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Byte): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Double): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Float): Unit { x.field(name, value) }
    public fun setValue(name: String, value: BigDecimal): Unit { x.field(name, value) }
    public fun setValue(name: String, value: Boolean): Unit { x.field(name, value) }
    public fun setValueNull(name: String): Unit { x.nullField(name) }
    public fun Object(name: String, init: XContentJsonObject.()->Unit): Unit {
        x.startObject(name)
        XContentJsonObject(x).init()
        x.endObject()
    }
    public fun <R: Enum<R>> ObjectWithFieldEnum(name: String, init: XContentJsonObjectWithEnum<R>.()->Unit): Unit {
        x.startObject(name)
        XContentJsonObjectWithEnum<R>(x).init()
        x.endObject()
    }
    public fun <R: Any> ObjectWithFieldClass(name: String, init: XContentJsonObjectWithClass<R>.()->Unit): Unit {
        x.startObject(name)
        XContentJsonObjectWithClass<R>(x).init()
        x.endObject()
    }
    public fun Array(name: String, init: XContentJsonArray.()->Unit): Unit {
        x.startArray(name)
        XContentJsonArray(x).init()
        x.endArray()
    }
}

public class XContentJsonArray(private val x: XContentBuilder) {
    public fun addValue(value: String): Unit { x.value(value) }
    public fun addValue(value: Long): Unit { x.value(value) }
    public fun addValue(value: Int): Unit { x.value(value) }
    public fun addValue(value: Short): Unit { x.value(value) }
    public fun addValue(value: Byte): Unit { x.value(value) }
    public fun addValue(value: Double): Unit { x.value(value) }
    public fun addValue(value: Float): Unit { x.value(value) }
    public fun addValue(value: BigDecimal): Unit { x.value(value) }
    public fun addValue(value: Boolean): Unit { x.value(value) }
    public fun addValueNull(): Unit { x.nullValue() }
    public fun addObject(init: XContentJsonObject.()->Unit): Unit {
        x.startObject()
        XContentJsonObject(x).init()
        x.endObject()
    }
    public fun addArray(init: XContentJsonArray.()->Unit): Unit {
        x.startArray()
        XContentJsonArray(x).init()
        x.endArray()
    }
}

public fun <T: Enum<T>> xsonObjectWithFieldEnum(init: XContentJsonObjectWithEnum<T>.()->Unit): XContentBuilder {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject()
    XContentJsonObjectWithEnum<T>(builder).init()
    builder.endObject()
    return builder
}

public fun <T: Any> xsonObjectWithFieldClass(init: XContentJsonObjectWithClass<T>.()->Unit): XContentBuilder {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject()
    XContentJsonObjectWithClass<T>(builder).init()
    builder.endObject()
    return builder
}

public fun xsonObject(init: XContentJsonObject.()->Unit): XContentBuilder {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject()
    XContentJsonObject(builder).init()
    builder.endObject()
    return builder
}

public fun xsonArray(init: XContentJsonArray.()->Unit): XContentBuilder {
    val builder = XContentFactory.jsonBuilder()
    builder.startArray()
    XContentJsonArray(builder).init()
    builder.endArray()
    return builder
}
