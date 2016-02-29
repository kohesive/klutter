package uy.klutter.db.jdbi.v3

import org.jdbi.v3.DBI
import org.jdbi.v3.Handle
import org.jdbi.v3.Query
import org.jdbi.v3.sqlobject.SqlObjectBuilder
import uy.klutter.db.jdbi.v2.map
import kotlin.reflect.KClass

private val metadataFqName = "kotlin.Metadata"

fun Class<*>.isKotlinClass(): Boolean {
    return this.annotations.singleOrNull { it.annotationClass.java.name == metadataFqName } != null
}


fun <T: Any> attachSqlObject(handle: Handle, sqlObjectType: KClass<T>): T {
    return SqlObjectBuilder.attach(handle, sqlObjectType.java)
}

// inline fun <reified T: Any> attachSqlObject(handle: Handle): T {
//    return attachSqlObject(handle, T::class)
// }

fun <T: Any> DBI.open(sqlObjectType: KClass<T>): T {
    return SqlObjectBuilder.open(this, sqlObjectType.java)
}

// inline fun <reified T: Any> DBI.open(): T {
//     return this.open(T::class)
// }

fun <T: Any> DBI.onDemand(sqlObjectType: KClass<T>): T {
    return SqlObjectBuilder.onDemand(this, sqlObjectType.java)
}

// inline fun <reified T: Any> DBI.onDemand(): T {
//    return this.onDemand(T::class)
// }

fun <T: Any> Handle.attach(sqlObjectType: KClass<T>): T {
    return SqlObjectBuilder.attach(this, sqlObjectType.java)
}

// inline fun <reified T: Any> Handle.attach(): T {
//    return this.attach(T::class)
// }

fun <O: Map<String, Any?>, T: Any> Query<O>.map(toClass: KClass<T>): Query<T> {
    return this.map(KotlinMapper(toClass.java))
}


fun <O: Any> Query<O>.useSequence(block: (Sequence<O>)->Unit): Unit {
    this.iterator().use {
        block(it.asSequence())
    }
}

fun <O: Map<String, Any?>, T: Any> Query<O>.useSequence(toClass: KClass<T>, block: (Sequence<T>)->Unit): Unit {
    this.map(toClass).iterator().use {
        block(it.asSequence())
    }
}
