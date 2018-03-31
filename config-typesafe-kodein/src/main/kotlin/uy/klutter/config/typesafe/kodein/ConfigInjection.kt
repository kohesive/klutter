package uy.klutter.config.typesafe.kodein

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.singleton
import com.typesafe.config.Config
import com.typesafe.config.ConfigRenderOptions
import com.typesafe.config.ConfigValue
import org.kodein.di.TypeToken
import org.kodein.di.bindings.InstanceBinding
import org.kodein.di.generic
import org.kodein.di.jvmType

fun Kodein.Builder.importConfig(config: Config, configExpression: String? = null, allowOverride: Boolean = false, mapper: ObjectMapper = jacksonObjectMapper(), init: KodeinTypesafeConfig.Module.Builder.(Config) -> Unit) {
    val root = if (configExpression != null && configExpression.isNotBlank()) config.getConfig(configExpression) else config
    import(KodeinTypesafeConfig.Module(init).Builder(root, mapper).kodeinModule, allowOverride)
}

fun Kodein.Companion.ConfigModule(init: KodeinTypesafeConfig.Module.Builder.(Config)->Unit) = KodeinTypesafeConfig.Module { init(it) }

class KodeinTypesafeConfig private constructor() {
    class Module internal constructor (private val init: Builder.(Config) -> Unit) {
        inner class Builder(val config: Config, val mapper: ObjectMapper = jacksonObjectMapper()) {
            internal val kodeinModule = Kodein.Module() {
                // call init which builds a list of things to do
                init(config)
                // execute those things
                exec()
            }

            private val actions: MutableList<BindActions> = arrayListOf()

            inner class BindActions(val action: Kodein.Builder.() -> Unit) {
                operator fun invoke(context: Kodein.Builder): Unit {
                    context.action()
                }
            }

            private fun Kodein.Builder.exec() {
                actions.forEach { it(this) }
            }

            fun import(configExpression: String, module: Module, allowOverride: Boolean = false) {
                actions.add(BindActions {
                    if (configExpression.isBlank()) throw IllegalArgumentException("configExpression must be non blank and resolve to a nested configuration")
                    val root = config.getConfig(configExpression) ?: throw IllegalArgumentException("configExpression must be non blank and resolve to a nested configuration")
                    import(module.Builder(root, mapper).kodeinModule, allowOverride)
                })
            }

            inner class ConfigBinder<in T : Any>(private val _bindType: TypeToken<T>, private val _bindTag: Any?, private val overrides: Boolean?) {
                infix fun fromConfig(targetConfig: Config) {
                    addAction(targetConfig)
                }

                infix fun fromConfig(configExpression: String) {
                    addAction(config.getConfig(configExpression))
                }

                private fun addAction(targetConfig: Config) {
                    actions.add(BindActions {
                        val asJson = targetConfig.root().render(ConfigRenderOptions.concise().setJson(true))
                        val value: T = mapper.readValue(asJson, TypeFactory.defaultInstance().constructType(_bindType.jvmType))!!
                        Bind(_bindType, _bindTag, overrides) with InstanceBinding(_bindType, value)
                       })
                }
            }

            inner class ConstantBinder<in T : Any>(private val _bindType: TypeToken<T>, private val _bindTag: Any?, private val overrides: Boolean?) {
                infix fun fromConfig(configExpression: String) {
                    addAction(config.getValue(configExpression))
                }

                infix fun fromConfig(configValue: ConfigValue) {
                    addAction(configValue)
                }

                @Suppress("UNCHECKED_CAST")
                private fun addAction(configValue: ConfigValue) {
                    actions.add(BindActions {
                        val value = configValue.unwrapped() as T
                        Bind(_bindType, _bindTag, overrides) with InstanceBinding(_bindType, value)
                    })
                }
            }

            inline fun <reified T : Any> bind(tag: Any? = null, overrides: Boolean? = null): ConfigBinder<T> = ConfigBinder(generic(), tag, overrides)
            inline fun <reified T : Any> constant(tag: Any, overrides: Boolean? = null): ConstantBinder<T> = ConstantBinder(generic(), tag, overrides)
        }
    }
}


