package uy.klutter.json.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory

/**
 * Add an ObjectMapper singleton factory to Injekt registry that is enabled for Kotlin classes
 */
public object JacksonWithKotlinInjektables : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory { jacksonObjectMapper() }
    }
}

/**
 * Add an ObjectMapper singleton factory to Injekt registry that auto finds and registers all Jackson modules found on
 * the classpath using Java service provider interface.  This is slower than direct registration of modules, but only
 * done once.  Also, by adding modules directly you are ensured your dependencies are correct for the application at
 * compile-time, which is safer.
 */
public object JacksonWithAutofindModulesInjektables : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory { ObjectMapper().findAndRegisterModules() }
    }
}

