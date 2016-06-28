package uy.klutter.db.jdbi.v3

import org.jdbi.v3.ResultSetMapperFactory
import org.jdbi.v3.StatementContext
import org.jdbi.v3.Types
import org.jdbi.v3.sqlobject.*
import org.jdbi.v3.tweak.ResultSetMapper
import java.lang.reflect.Method
import java.lang.reflect.Type
import java.util.*

class KotlinBinderFactory : ParameterBinderFactory {
    override fun binderFor(forClass: Class<*>, whichMethod: Method, whichParameter: Int): Binder<*, *>? {
        return KotlinBinder(whichMethod, whichParameter)
    }

    override fun accepts(forClass: Class<*>, whichMethod: Method, whichParameter: Int): Boolean {
        return forClass.isKotlinClass()
    }
}

class KotlinMapperFactory : ResultSetMapperFactory {
    override fun build(type: Type, ctx: StatementContext): Optional<ResultSetMapper<*>> {
        @Suppress("UNCHECKED_CAST")
        val clazz = Types.getErasedType(type) as Class<Any>

        return if (clazz.isKotlinClass())
            Optional.of<ResultSetMapper<*>>(KotlinMapper<Any>(clazz))
        else
            Optional.empty<ResultSetMapper<*>>()
    }
}