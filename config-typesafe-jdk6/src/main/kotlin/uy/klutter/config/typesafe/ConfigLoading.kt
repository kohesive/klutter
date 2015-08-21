package uy.klutter.config.typesafe

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigParseOptions
import com.typesafe.config.ConfigResolveOptions
import java.io.File
import java.io.Reader
import java.net.URL
import java.util.*


/**
 * Load a configuration chain, in the order of priority with NO default behavior (i.e. no environment vars, reference conf loading, nor system props)
 *
 * loadConfig(a, b, c) would have values from "a" override values from "b" overriding values from "c"
 *
 * To resolve at some intermediate step you can use the ResolveConfig() loader class at the point you want configs to be resolved:
 *
 * loadConfig(a, ResolveConfig(), b, c) would load "c", then "b", then resolve variables in "b" and "c" together, then continue
 * with loading "a", with a final resolve at the end -- creating the full fallback chain a->b->c
 *
 * Prebuilt config loaders include:
 *
 *     EnvironmentVariablesConfig
 *     SystemPropertiesConfig (resolved separately against environment if exists)
 *     ReferenceConfig  (reference.conf from classpath)
 *     ApplicationConfig  (application.conf from classpath, or config.resource, or config.file, or config.url)
 *     ResourceConfig (load resource name from classpath, optionally specify classpath and if it should fail when resource is missing)
 *     ClassResourceConfig (load resource relative to a given class, optionally specify if it should fail when resouce is missing)
 *     FileConfig (load config from a file/path, optionally specify if it should fail when resouce is missing)
 *     ReaderConfig (load config from reader, it should be HOCON or JSON formatted content to be parsed)
 *     UrlConfig (load config from a URL)
 *     MapAsConfig (use a Map<String, Any> as a config)
 *     StringAsConfig (use a String containing the HOCON or JSON to be parsed into config)
 *     PropertiesAsConfig (use a java.util.Properties object as config)
 *
 * To mimic the default load() behavior of TypeSafe config, the loadDefaultConfig() method is provided.
 */
public fun loadConfig(vararg loaders: ConfigLoader): Config {
    return loaders.foldRight(ConfigFactory.empty(), { loader, accum ->
        val current = loader.load().withFallback(accum)
        loader.afterAttached(current)
        current
    }).resolve(ConfigResolveOptions.noSystem())
}

// System properties included twice in total applicationConfChain so that resolve of reference config is the same as normal behavior of ConfigFactory.load() method
private val referenceConfChain = listOf(ResolveConfig(), SystemPropertiesConfig(), ReferenceConfig(), EnvironmentVariablesConfig())
private val applicationConfChain = listOf(SystemPropertiesConfig(), ApplicationConfig()) + referenceConfChain

/**
 * Mimics the behavior of ConfigFactory.load() or ConfigFactory.defaultApplication()
 */
public fun loadApplicationConfig(): Config {
    return loadConfig(*applicationConfChain.toTypedArray())
}

public abstract class ConfigLoader() {
    public abstract fun load(): Config

    public open fun afterAttached(fullConfig: Config): Unit {
    }
}

public class ResolveConfig() : ConfigLoader() {
    public override fun load(): Config = ConfigFactory.empty()
    public override fun afterAttached(fullConfig: Config): Unit {
        fullConfig.resolve(ConfigResolveOptions.noSystem())
    }
}

public class EnvironmentVariablesConfig : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.systemEnvironment()
    }
}

public class SystemPropertiesConfig : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.systemProperties().resolve()
    }
}

public class ReferenceConfig(val optionalClassLoader: ClassLoader? = null) : ConfigLoader() {
    public override fun load(): Config {
        return if (optionalClassLoader != null) {
            ConfigFactory.parseResources(optionalClassLoader, "reference.conf")
        } else {
            ConfigFactory.parseResources("reference.conf")
        }
    }
}

public class ApplicationConfig(val optionalClassLoader: ClassLoader? = null) : ConfigLoader() {
    public override fun load(): Config {
        val specifiedOverrideOptions = ConfigParseOptions.defaults().setAllowMissing(false)
        if (System.getProperty("config.resource") != null) {
            if (optionalClassLoader != null) {
                return ConfigFactory.parseResources(optionalClassLoader, System.getProperty("config.resource"), specifiedOverrideOptions)
            } else {
                return ConfigFactory.parseResources(System.getProperty("config.resource"), specifiedOverrideOptions)
            }
        } else if (System.getProperty("config.file") != null) {
            return ConfigFactory.parseFile(File(System.getProperty("config.file")), specifiedOverrideOptions)
        } else if (System.getProperty("config.url") != null) {
            return ConfigFactory.parseURL(URL(System.getProperty("config.url")), specifiedOverrideOptions)
        } else {
            if (optionalClassLoader != null) {
                return ConfigFactory.parseResourcesAnySyntax(optionalClassLoader, "application")
            } else {
                return  ConfigFactory.parseResourcesAnySyntax("application")
            }
        }
    }
}

public class ResourceConfig(val resouceName: String, val optionalClassLoader: ClassLoader? = null, val failIfMissing: Boolean = true) : ConfigLoader() {
    public override fun load(): Config {
        val options = ConfigParseOptions.defaults().setAllowMissing(failIfMissing)
        return if (optionalClassLoader != null) {
            ConfigFactory.parseResourcesAnySyntax(optionalClassLoader, resouceName, options)
        } else {
            ConfigFactory.parseResourcesAnySyntax(resouceName, options)
        }
    }
}

public class ClassResourceConfig(val resouceName: String, val klass: Class<*>, val failIfMissing: Boolean = true) : ConfigLoader() {
    public override fun load(): Config {
        val options = ConfigParseOptions.defaults().setAllowMissing(failIfMissing)
        return ConfigFactory.parseResourcesAnySyntax(klass, resouceName, options)
    }
}

open public class FileConfig(val file: File, val failIfMissing: Boolean = true) : ConfigLoader() {
    public override fun load(): Config {
        val options = ConfigParseOptions.defaults().setAllowMissing(failIfMissing)
        return ConfigFactory.parseFileAnySyntax(file, options)
    }
}

public class UrlConfig(val url: URL, val failIfMissing: Boolean = true) : ConfigLoader() {
    public override fun load(): Config {
        val options = ConfigParseOptions.defaults().setAllowMissing(failIfMissing)
        return ConfigFactory.parseURL(url, options)
    }
}

public class ReaderConfig(val reader: Reader) : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.parseReader(reader)
    }
}

public class MapAsConfig(val configMap: Map<String, Any>) : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.parseMap(configMap)
    }
}

public class PropertiesAsConfig(val configProps: Properties) : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.parseProperties(configProps)
    }
}

public class StringAsConfig(val configString: String) : ConfigLoader() {
    public override fun load(): Config {
        return ConfigFactory.parseString(configString)
    }
}



