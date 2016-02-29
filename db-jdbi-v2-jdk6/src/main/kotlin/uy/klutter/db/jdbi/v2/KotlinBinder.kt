package uy.klutter.db.jdbi.v2

import org.skife.jdbi.v2.*
import org.skife.jdbi.v2.SQLStatement
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.*
import uy.klutter.reflect.erasedType
import java.lang.reflect.Method
import java.lang.reflect.Parameter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.declaredMemberProperties
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.kotlinFunction

class KotlinBinder(val method: Method, val paramIdx: Int) : Binder<Bind, Any> {
    val parameter = method.parameters[paramIdx]

    override fun bind(q: SQLStatement<*>, bind: Bind?, arg: Any?) {
        bind(q, parameter, bind, arg)
    }

    private fun Type.findGenericParameter(ofSuper: Class<*>): Type {
        val genericSuperFirstType = erasedType().getGenericSuperclass().let { superClass ->
            if (superClass !is ParameterizedType) {
                throw IllegalArgumentException("Internal error: TypeReference constructed without actual type information")
            }
            superClass.getActualTypeArguments()[0]
        }
        return genericSuperFirstType
    }

    private fun bind(q: SQLStatement<*>, parameter: Parameter, bind: Bind?, arg: Any?) {
        val paramType = parameter.getParameterizedType()

        val bindName = if (parameter.isNamePresent()) {
            parameter.getName()
        } else {
            method.kotlinFunction?.parameters?.dropWhile { it.kind != KParameter.Kind.VALUE }?.toList()?.get(paramIdx)?.name
        } ?: throw UnsupportedOperationException("A parameter was not given a name, "
                + "and parameter name data is not present in the class file, for: "
                + parameter.getDeclaringExecutable() + " :: " + parameter)

        fun bind(q: SQLStatement<*>, bindToParm: String?, bindAsType: Type, value: Any?, prefix: String = "") {
            val type = if (q is PreparedBatchPart) {
                // FIXME BatchHandler should extract the iterable/iterator element type and pass it to the binder
                val erasedType = bindAsType.erasedType()
                if (Iterable::class.java.isAssignableFrom(erasedType)) {
                    bindAsType.findGenericParameter(Iterable::class.java)
                } else if (Iterator::class.java.isAssignableFrom(erasedType)) {
                    bindAsType.findGenericParameter(Iterator::class.java)
                } else {
                    bindAsType
                }
            } else {
                bindAsType
            }

            val erasedType = type.erasedType()
            if (erasedType.isKotlinClass()) {
                @Suppress("UNCHECKED_CAST")
                (erasedType.kotlin as KClass<Any>).declaredMemberProperties.forEach { subProp ->
                    bind(q, subProp.name, subProp.returnType.javaType, if (value == null) null else subProp.get(value), "${prefix}${bindToParm}.")
                }
            } else {
                if (bindToParm != null) {
                    q.bind("${prefix}${bindToParm}", value)
                } else if (prefix.isNullOrBlank()) {
                    // we can't really bind sub items by order
                    q.bind(paramIdx, value)
                }
            }
        }

        bind(q, bindName, paramType, arg)
    }
}