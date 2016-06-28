## klutter/config-typesafe-jdk6-kodein

Related artifacts and modules:

* [uy.kohesive.klutter:klutter-config-typesafe](../config-typesafe)
* uy.kohesive.klutter:klutter-config-typesafe-kodein -- injecting configuration as objects via Kodein

## Injecting Configuration into Objects with Kodein

Klutter/config-typesafe plus [Kodein](https://github.com/SalomonBrys/Kodein) support injection from [Typesafe Config](https://github.com/typesafehub/config) configuration via bound objects placed into the Kodein registry, then you may inject your configuration objects as you would any singleton.

The configuration system allows you to define within modules: the configuration objects and a section of the configuration
tree that is to be used.  Then at the main module of your application you import the module's configurables and injectables
delegating the registation to the submodules.

A few other notes:  Jackson Data Binding is used to bind configuration into objects.  (later we will replace with a lighterweight binder).

**This modules is best shown by a quick example:**

Using a configuration file, `myConfig.conf`
```json
{
    "http": {
        "httpPort": 8080,
        "workerThreads": 16
    },
    "data": {
        "bucket": "com.test.bucket",
        "region": "us-east"
    },
    "other": {
        "name": "frisbee"
    }
}
```

We then have a sub module that wants the `http` configuration bound into this data class:

```kotlin
 data class HttpConfig(val httpPort: Int, val workerThreads: Int)
```

The module creates an importable module that registers a configuration class, and any injectables, and code that uses the configuration object later via injection:

```kotlin
object KodeinServerInjectables  {
    // bind configuration objects from root of configuration we inherit
    val configModule = KodeinTypesafeConfig.Module {
        bind<HttpConfig>() fromConfig()
    }

    val module = Kodein {
        // Create MyHttpServer singleton which uses an HttpConfig object in its constructor
        bind() from singleton { MyHttpServer(instance()) }
    }
}

// Our server will receive the configuration via injection
class MyHttpServer(cfg: HttpConfig) { ... }
```

Now the main controlling class, the Application creates a Kodein module and imports other modules:

```kotlin
class MyApp {
    companion object {
        val kodein = {
            // import the application.conf file and bind everything under "http.*" to an HttpConfig object
            importConfig(loadConfig(ApplicationConfig()) {
                // bind my configuration classes
                bind<S3Config>() fromConfig "data"
                bind<OtherConfig>() fromConfig "other"

                // import another module starting at "http.*" in the configuration
                import("http", KodeinServerInjectables.configModule)
            }

            // register my own factories/singletons, or import other modules
            import(KodeinJacksonWithKotlin.module)
            import(KodeinServerInjectables.module)
        }

        // my app starts here with a static main()
        platformStatic public fun main(args: Array<String>) {
            MyApp().run()
        }
    }

    data class DataConfig(val bucket: String, val region: String)
    data class OtherConfig(val name: String)

    // rest of class ...
}
```

Using this system, you have a modularized way to define configuration and services (injectables) that can be managed by Kodein.  Each module defines what it needs, and what it can provide.  Then the main control point of the app decides where in its overall configuration each module resides, and which modules are included.

See module documentation in [klutter/config-typesafe](../config-typesafe)

