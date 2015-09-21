package uy.klutter.json.jackson.jdk8

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addSingletonFactory

/**
 * Add an ObjectMapper singleton factory to Injekt registry that is enabled for Kotlin + JDK8 classes (core, temporal, parameter names)
 */
public object JacksonWithKotlinAndJdk8Injektables : InjektModule {
    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory { addJacksonJdk8ModulesToMapper(jacksonObjectMapper()) }
    }
}

public fun addJacksonJdk8ModulesToMapper(mapper: ObjectMapper): ObjectMapper {
    return mapper.registerKotlinModule()
            .registerModule(JavaTimeModule())
            .registerModule(Jdk8Module())
            .registerModule(ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
}



