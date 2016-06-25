[uy.klutter.json.jackson](.)


## Package uy.klutter.json.jackson

### Types

|&nbsp;|&nbsp;|
|---|---|
| [JacksonWithAutofindModulesInjektables](-jackson-with-autofind-modules-injektables/index.md) | <code>object JacksonWithAutofindModulesInjektables : InjektModule</code><br/>Add an ObjectMapper singleton factory to Injekt registry that auto finds and registers all Jackson modules found on<br/>the classpath using Java service provider interface.  This is slower than direct registration of modules, but only<br/>done once.  Also, by adding modules directly you are ensured your dependencies are correct for the application at<br/>compile-time, which is safer. |
| [JacksonWithKotlinInjektables](-jackson-with-kotlin-injektables/index.md) | <code>object JacksonWithKotlinInjektables : InjektModule</code><br/>Add an ObjectMapper singleton factory to Injekt registry that is enabled for Kotlin classes |
