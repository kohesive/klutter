[uy.klutter.config.typesafe](index.md) / [loadConfig](.)


# loadConfig

`fun loadConfig(vararg loaders:&nbsp;[ConfigLoader](-config-loader/index.md)): Config` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/ConfigLoading.kt#L40)

Load a configuration chain, in the order of priority with NO default behavior (i.e. no environment vars, reference conf loading, nor system props)


loadConfig(a, b, c) would have values from "a" override values from "b" overriding values from "c"


To resolve at some intermediate step you can use the ResolveConfig() loader class at the point you want configs to be resolved:


loadConfig(a, ResolveConfig(), b, c) would load "c", then "b", then resolve variables in "b" and "c" together, then continue
with loading "a", with a final resolve at the end -- creating the full fallback chain a-&gt;b-&gt;c


Prebuilt config loaders include:


EnvironmentVariablesConfig
SystemPropertiesConfig (resolved separately against environment if exists)
ReferenceConfig  (reference.conf from classpath)
ApplicationConfig  (application.conf from classpath, or config.resource, or config.file, or config.url)
ResourceConfig (load resource name from classpath, optionally specify classpath and if it should fail when resource is missing)
ClassResourceConfig (load resource relative to a given class, optionally specify if it should fail when resouce is missing)
FileConfig (load config from a file/path, optionally specify if it should fail when resouce is missing)
ReaderConfig (load config from reader, it should be HOCON or JSON formatted content to be parsed)
UrlConfig (load config from a URL)
MapAsConfig (use a Map&lt;String, Any&gt; as a config)
StringAsConfig (use a String containing the HOCON or JSON to be parsed into config)
PropertiesAsConfig (use a java.util.Properties object as config)


To mimic the default load() behavior of TypeSafe config, the loadDefaultConfig() method is provided.





