package uy.klutter.reflect

import java.lang.reflect.*
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

inline fun <reified T: Any> reifiedType(): Type = object:TypeReference<T>(){}.type

interface TypeToken {
    val type: Type
}

abstract class TypeReference<T> protected constructor(): TypeToken {
    override val type: Type by lazy {
        javaClass.getGenericSuperclass().let { superClass ->
            if (superClass is Class<*>) {
                throw IllegalArgumentException("Internal error: TypeReference constructed without actual type information")
            }
            (superClass as ParameterizedType).getActualTypeArguments()[0]
        }
    }
}

@Suppress("UNCHECKED_CAST") fun Type.erasedType(): Class<Any> {
    return when (this) {
        is Class<*> -> this as Class<Any>
        is ParameterizedType -> this.getRawType().erasedType()
        is GenericArrayType -> {
            // getting the array type is a bit trickier
            val elementType = this.getGenericComponentType().erasedType()
            val testArray = java.lang.reflect.Array.newInstance(elementType, 0)
            testArray.javaClass
        }
        is TypeVariable<*> -> {
            // not sure yet
            throw IllegalStateException("Not sure what to do here yet")
        }
        is WildcardType -> {
            this.getUpperBounds()[0].erasedType()
        }
        else -> throw IllegalStateException("Should not get here.")
    }
}

