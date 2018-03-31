package uy.klutter.json.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


object KodeinJacksonWithKotlin {
    /**
     * Add an ObjectMapper singleton factory to Kodein registry that is enabled for Kotlin classes
     */

    val module = Kodein.Module {
        bind<ObjectMapper>() with singleton {
            jacksonObjectMapper()
                    .registerModule(JavaTimeModule())
                    .registerModule(Jdk8Module())
                    .registerModule(ParameterNamesModule(JsonCreator.Mode.PROPERTIES))

        }
    }

    /**
     * Add an ObjectMapper singleton factory to Kodein registry that auto finds and registers all Jackson modules found on
     * the classpath using Java service provider interface.  This is slower than direct registration of modules, but only
     * done once.  Also, by adding modules directly you are ensured your dependencies are correct for the application at
     * compile-time, which is safer.
     */

    val moduleWithAutoFindJacksonModules = Kodein.Module {
        bind<ObjectMapper>() with singleton { jacksonObjectMapper().findAndRegisterModules() }
    }
}


