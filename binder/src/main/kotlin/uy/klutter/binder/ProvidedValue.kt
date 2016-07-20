package uy.klutter.binder


sealed class ProvidedValue<out T>(val value: T) {
    companion object {
        fun <T> of(value: T): ProvidedValue<T> = Present.of(value)
        fun nested(subprovider: NamedValueProvider): NestedNamedValueProvider = NestedNamedValueProvider.of(subprovider)
        fun nested(subprovider: OrderedValueProvider): NestedOrderedValueProvider = NestedOrderedValueProvider.of(subprovider)
        fun <O, T> coerced(original: ProvidedValue<O>, value: T): ProvidedValue<T> = Coerced.of(original, value)
        fun absent(): ProvidedValue<Unit> = Absent.of()
    }

    open class Present<out T> protected constructor (value: T): ProvidedValue<T>(value) {
        companion object {
            fun <T> of(value: T): Present<T> = Present(value)
        }

        override fun toString(): String = "[Present ${value}]"
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Present<*>) return false
            return value == other.value
        }

        override fun hashCode(): Int {
            return value?.hashCode() ?: 4314
        }
    }

    class Coerced<out O, out T> private constructor (val original: ProvidedValue<O>, value: T): Present<T>(value) {
        companion object {
            fun <O, T> of(original: ProvidedValue<O>, value: T): Coerced<O, T> = Coerced(original, value)
        }

        override fun toString(): String = "[Coerced ${value} from ${original}]"
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Coerced<*, *>) return false
            return original == other.original && value == other.value
        }

        override fun hashCode(): Int {
            return value?.hashCode() ?: 9911
        }
    }

    class NestedNamedValueProvider private constructor (subprovider: NamedValueProvider): ProvidedValue<NamedValueProvider>(subprovider)  {
        companion object {
            fun of(subprovider: NamedValueProvider): NestedNamedValueProvider = NestedNamedValueProvider(subprovider)
        }

        override fun toString(): String = "[NestedNamedValueProvider ${value}]"
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is NestedNamedValueProvider) return false
            return value == other.value
        }

        override fun hashCode(): Int {
            return value.hashCode() * 49
        }
    }

    class NestedOrderedValueProvider private constructor (subprovider: OrderedValueProvider): ProvidedValue<OrderedValueProvider>(subprovider)  {
        companion object {
            fun of(subprovider: OrderedValueProvider): NestedOrderedValueProvider = NestedOrderedValueProvider(subprovider)
        }

        override fun toString(): String = "[NestedOrderedValueProvider ${value}]"
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is NestedOrderedValueProvider) return false
            return value == other.value
        }

        override fun hashCode(): Int {
            return value.hashCode() * 49
        }
    }

    class Absent private constructor (): ProvidedValue<Unit>(Unit) {
        companion object {
            private val instance = Absent()

            @Suppress("UNCHECKED_CAST")
            fun of(): Absent = instance
        }

        override fun toString(): String = "[Absent]"
        override fun equals(other: Any?): Boolean = this === other
        override fun hashCode(): Int = 999
    }
}