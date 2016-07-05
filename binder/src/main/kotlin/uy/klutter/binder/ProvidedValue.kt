package uy.klutter.binder


sealed class ProvidedValue<T: Any>() {
    companion object {
        fun <T: Any> of(value: T?): ProvidedValue<T> = Present.of(value)
        fun <O: Any, T: Any> coerced(original: ProvidedValue<O>, value: T?): ProvidedValue<T> = Coerced.of(original, value)
        fun absent(): ProvidedValue<Any> = Absent.of()
    }

    fun isAbsent(): Boolean = this is Absent
    fun isPresent(): Boolean = this is Present
    fun isCoerced(): Boolean = this is Coerced<*, *>

    open class Present<T: Any> protected constructor (val value: T?): ProvidedValue<T>() {
        companion object {
            fun <T: Any> of(value: T?, warnings: List<String> = arrayListOf()): Present<T> = Present(value)
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

    class Coerced<O: Any, T: Any> private constructor (val original: ProvidedValue<O>, value: T?): Present<T>(value) {
        companion object {
            fun <O: Any, T: Any> of(original: ProvidedValue<O>, value: T?): Coerced<O, T> = Coerced(original, value)
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

    class Absent private constructor (): ProvidedValue<Any>() {
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