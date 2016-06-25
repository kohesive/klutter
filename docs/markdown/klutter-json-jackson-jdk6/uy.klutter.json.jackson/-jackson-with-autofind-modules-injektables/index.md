[uy.klutter.json.jackson](../index.md) / [JacksonWithAutofindModulesInjektables](.)


# JacksonWithAutofindModulesInjektables
<code>object JacksonWithAutofindModulesInjektables : InjektModule</code> [(source)](https://github.com/kohesive/klutter/blob/master/json-jackson-jdk6/src/main/kotlin/uy/klutter/json/jackson/Injektable.kt#L24)<br/>
Add an ObjectMapper singleton factory to Injekt registry that auto finds and registers all Jackson modules found on
the classpath using Java service provider interface.  This is slower than direct registration of modules, but only
done once.  Also, by adding modules directly you are ensured your dependencies are correct for the application at
compile-time, which is safer.



### Functions

|&nbsp;|&nbsp;|
|---|---|
| [registerInjectables](register-injectables.md) | <code>fun InjektRegistrar.registerInjectables(): Unit</code><br/> |
