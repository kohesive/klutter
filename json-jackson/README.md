## klutter/json-jackson

Jackson JSON (and other format) parsing helpers and prebuilt Injekt modules.

Module is available in artifacts:

* uy.klutter:klutter-json-jackson (latest JDK, currently JDK 8)
* [uy.klutter:klutter-json-jackson-jdk6](../json-jackson-jdk6) (JDK 6 and 7)
* [uy.klutter:klutter-json-jackson-jdk8](../json-jackson-jdk8)

A top level function is available in the JDK 8 module to add appropriate JDK 8 modules to Jackson given an existing mapper:

* `addJacksonJdk8ModulesToMapper(mapper)`

## Injekt

An Injekt module is included to provide a singleton ObjectMapper that uses the Kotlin module plugin fro Jackson.  The Injekt Modules available:

* `JacksonWithKotlinInjektables` - basic Jackson + Kotlin module
* `JacksonWithAutofindModulesInjektables` - basic Jackson, and register any modules found on classpath
* `JacksonWithKotlinAndJdk8Injektables` - in JDK 8 module, Jackson configured with Kotlin + JDK 8 classes (core, temporal) + JDK 8 Parameter Names support

Include this module into your InjektMain using something like:

```
class MyApp {
    companion object : InjektMain() {
        // my app starts here with a static main()
        platformStatic public fun main(args: Array<String>) {
            MyApp().run()
        }

        // the Injekt system will call me back here on a method I override.  And all my functions for registration are
        // easy to find on the receiver class
        override fun InjektRegistrar.registerInjectables() {
            // add my singletons, factories, keyed factories, per-thread factories, ...
            ...

            // import prebuilt Injekt modules
            importModule(JacksonWithKotlinInjektables)  // pick only 1 module from above
        }
    }

    ...
    // later, use them in properties in any other class
    val json: ObjectMapper by Delegates.injectLazy()
    // or use them anywhere in code 
    val json: ObjectMapper = Inject.get()
    // or another form of the same 
    val myObject: MyDataClass = Inject<ObjectMapper>.get().readValue(jsonString)
    // or in constructors or method definitions
    public fun doSomethingWithJson(mapper: ObjectMapper = Inject.get()) { ... }
}
```
