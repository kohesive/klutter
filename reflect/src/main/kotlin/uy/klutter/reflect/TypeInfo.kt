package uy.klutter.reflect

import java.lang.reflect.*
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType

inline fun <reified T> reifiedType(): Type = object : TypeReference<T>() {}.type
inline fun <reified T> reifiedKType(): KType = object : KTypeReference<T>(isNullable<T>()) {}.ktype

inline fun <reified T : Any?> isNullable(): Boolean {
    return null is T
}

interface TypeToken {
    val type: Type
}

abstract class TypeReference<T> protected constructor() : TypeToken {
    override val type: Type by lazy {
        javaClass.getGenericSuperclass().let { superClass ->
            if (superClass is Class<*>) {
                throw IllegalArgumentException("Internal error: TypeReference constructed without actual type information")
            }
            (superClass as ParameterizedType).getActualTypeArguments().single()
        }
    }
}

abstract class KTypeReference<T> protected constructor(val nullable: Boolean): TypeReference<T>() {
    val ktype: KType = type.toKType(nullable)

    fun Type.toKTypeProjection(nullable: Boolean = false): KTypeProjection = when (this) {
        is Class<*> -> this.kotlin.toInvariantFlexibleProjection(nullable = nullable)
        is ParameterizedType -> {
            val erasure = (rawType as Class<*>).kotlin
            erasure.toInvariantFlexibleProjection((erasure.typeParameters.zip(actualTypeArguments).map { (parameter, argument) ->
                val projection = argument.toKTypeProjection()
                projection.takeIf {
                    // Get rid of use-site projections on arguments, where the corresponding parameters already have a declaration-site projection
                    parameter.variance == KVariance.INVARIANT || parameter.variance != projection.variance
                } ?: KTypeProjection.invariant(projection.type!!)
            }), nullable = nullable)
        }
        is WildcardType -> when {
            lowerBounds.isNotEmpty() -> KTypeProjection.contravariant(lowerBounds.single().toKType())
            upperBounds.isNotEmpty() -> KTypeProjection.covariant(upperBounds.single().toKType())
        // This looks impossible to obtain through Java reflection API, but someone may construct and pass such an instance here anyway
            else -> KTypeProjection.STAR
        }
        is GenericArrayType -> Array<Any>::class.toInvariantFlexibleProjection(listOf(genericComponentType.toKTypeProjection()), nullable = nullable)
        is TypeVariable<*> -> TODO() // TODO
        else -> throw IllegalArgumentException("Unsupported type: $this")
    }

    fun Type.toKType(nullable: Boolean = false): KType = toKTypeProjection(nullable).type!!

    fun KClass<*>.toInvariantFlexibleProjection(arguments: List<KTypeProjection> = emptyList(), nullable: Boolean): KTypeProjection {
        return KTypeProjection.invariant(createType(arguments, nullable = nullable))
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

