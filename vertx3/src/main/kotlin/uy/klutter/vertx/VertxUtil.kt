package uy.klutter.vertx

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import io.vertx.core.json.Json

/**
 * Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application
 * before the logging systems are activated.
 */
fun setupVertxLoggingToSlf4j() {
    // must be called before anything in Vertx
    System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory")
    System.setProperty("hazelcast.logging.type", "slf4j")
}

/**
 * Setup the Vert.x singleton for Jackson ObjectMapper to support Kotlin and JDK 8 types.  This should be called very early
 * in your application lifecycle.
 */
fun setupVertxJsonForKotlin() {
    addJacksonJdk8ModulesToMapper(Json.mapper)
    addJacksonJdk8ModulesToMapper(Json.prettyMapper)
}

fun addJacksonJdk8ModulesToMapper(mapper: ObjectMapper): ObjectMapper {
    return mapper.registerKotlinModule()
            .registerModule(JavaTimeModule())
            .registerModule(Jdk8Module())
            .registerModule(ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
}


