package uy.klutter.binder.kodein

import com.github.salomonbrys.kodein.Kodein
import uy.klutter.binder.NamedValueProvider
import java.lang.reflect.Type

class KodeinValueProvider(private val kodein: Kodein, private val delegate: NamedValueProvider) : NamedValueProvider {
    override val supportsDottedNames: Boolean = delegate.supportsDottedNames
    override val knowsEntries: Boolean = delegate.knowsEntries

    override fun valueByName(name: String, targetType: Type): Any? {
        return if (delegate.existsByName(name, targetType)) {
            delegate.valueByName(name, targetType)
        } else {
            kodein.container.providerOrNull(Kodein.Bind(targetType, null))?.invoke() ?: throw IllegalStateException("not found")
        }
    }

    override fun existsByName(name: String, targetType: Type): Boolean {
        return delegate.existsByName(name, targetType) || kodein.container.providerOrNull(Kodein.Bind(targetType, null)) != null
    }

    override fun entries(): List<Pair<String, Any?>> {
        return delegate.entries()
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                other is KodeinValueProvider &&
                other.kodein === this.kodein && // identity compare
                other.delegate == this.delegate
    }

    override fun hashCode(): Int {
        return kodein.hashCode() * delegate.hashCode()
    }

}
