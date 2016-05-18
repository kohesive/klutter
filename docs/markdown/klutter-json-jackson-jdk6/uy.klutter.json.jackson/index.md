[uy.klutter.json.jackson](.)


## Package uy.klutter.json.jackson


### Types


| [JacksonWithAutofindModulesInjektables](-jackson-with-autofind-modules-injektables/index.md) | `object JacksonWithAutofindModulesInjektables&nbsp;:&nbsp;InjektModule`
Add an ObjectMapper singleton factory to Injekt registry that auto finds and registers all Jackson modules found on
the classpath using Java service provider interface.  This is slower than direct registration of modules, but only
done once.  Also, by adding modules directly you are ensured your dependencies are correct for the application at
compile-time, which is safer.

 |
| [JacksonWithKotlinInjektables](-jackson-with-kotlin-injektables/index.md) | `object JacksonWithKotlinInjektables&nbsp;:&nbsp;InjektModule`
Add an ObjectMapper singleton factory to Injekt registry that is enabled for Kotlin classes

 |

