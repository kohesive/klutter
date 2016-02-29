package uy.klutter.db.jdbi.v2

import org.skife.jdbi.v2.ResultSetMapperFactory
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.sqlobject.Binder
import org.skife.jdbi.v2.sqlobject.ParameterBinderFactory
import org.skife.jdbi.v2.tweak.ResultSetMapper
import uy.klutter.reflect.erasedType
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
    override fun mapperFor(forClass: Class<*>, ctx: StatementContext): ResultSetMapper<*>? {
        return KotlinMapper(forClass)
    }

    override fun accepts(forClass: Class<*>, ctx: StatementContext): Boolean {
        return forClass.isKotlinClass()
    }
}