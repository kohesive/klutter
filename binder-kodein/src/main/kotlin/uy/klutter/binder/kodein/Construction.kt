package uy.klutter.binder.kodein

import com.github.salomonbrys.kodein.Kodein
import uy.klutter.binder.NamedValueProvider
import uy.klutter.binder.ProvidedValue
import uy.klutter.binder.ValueProviderTargetScope
import kotlin.reflect.KType
import kotlin.reflect.jvm.javaType

class KodeinValueProvider(private val kodein: Kodein, private val delegate: NamedValueProvider) : NamedValueProvider by delegate {
    override fun valueByName(name: String, targetType: KType, scope: ValueProviderTargetScope): ProvidedValue<Any?> {
        val maybe = delegate.valueByName(name, targetType, scope)
        return when (maybe) {
            is ProvidedValue.Present -> maybe
            is ProvidedValue.Nested -> maybe // TODO: should we return this, or check if we have a full object first?
            is ProvidedValue.Absent -> kodein.container.providerOrNull(Kodein.Bind(targetType.javaType, null))?.invoke()
                    ?.let { ProvidedValue.of(it) } ?: ProvidedValue.absent()
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null &&
                this.javaClass == other.javaClass &&
                other is KodeinValueProvider &&
                other.kodein === this.kodein && // identity compare
                other.delegate == this.delegate
    }

    override fun hashCode(): Int {
        return kodein.hashCode() * delegate.hashCode()
    }

}
