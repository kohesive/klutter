## klutter/json-jackson-kodein

Jackson JSON (and other format) parsing helpers and prebuilt Kodein injection modules.

Module is available in artifacts:

* uy.klutter.v2:klutter-json-jackson

A top level function is available to add appropriate JDK 8 modules to Jackson given an existing mapper:

* `addJacksonJdk8ModulesToMapper(mapper)`

## Kodein

Kodein modules are included to provide a singleton ObjectMapper that uses the Kotlin module plugin from Jackson:

* `KodeinJacksonWithKotlin.module` - basic Jackson + JDK 8 modules + Kotlin module
* `KodeinJacksonWithKotlin.moduleWithAutoFindJacksonModules` - Jackson + Kotlin module + register any modules found on classpath

Include this module into your Kodein using something like:

```
class MyApp {
    companion object {
        // my app starts here with a static main()
        platformStatic public fun main(args: Array<String>) {
            MyApp().run()
        }

        val kodein = Kodein {
           import(KodeinJacksonWithKotlin.module)

           // register your other bindings
        }
    }

    ...
    // later, use them in properties in any other class
    val json: ObjectMapper = kodein.instance()

    // or another form of the same
    val myObject: MyDataClass = kodein.instance<ObjectMapper>().readValue(jsonString)
    // or in constructors or method definitions
    public fun doSomethingWithJson(mapper: ObjectMapper = kodein.instance()) { ... }
}
```
