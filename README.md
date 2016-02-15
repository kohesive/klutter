[![Kotlin](https://img.shields.io/badge/kotlin-1.0.0-blue.svg)](http://kotlinlang.org) [![Maven Version](https://img.shields.io/maven-central/v/uy.klutter/klutter-all-jdk8.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter%22) [![CircleCI branch](https://img.shields.io/circleci/project/kohesive/klutter/master.svg)](https://circleci.com/gh/kohesive/klutter/tree/master) [![Issues](https://img.shields.io/github/issues/kohesive/klutter.svg)](https://github.com/kohesive/klutter/issues?q=is%3Aopen) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/kohesive/klutter/blob/master/LICENSE) [![Kotlin Slack](https://img.shields.io/badge/chat-kotlin%20slack-orange.svg)](http://kotlinslackin.herokuapp.com)

# klutter
Random small libraries, usually extensions making other libraries happier.  

Please send contributions, fork the repo, issue a pull request and write a comment in the pull request granting the
rights for the code to be used in Klutter.

## Maven Dependency

Each module has its own set of dependencies.  There is a main dependency which is always the most current JDK
version of the module, and also a version suffixed by the JDK version for which it is compatible (that version or newer).
Include the dependency in your Gradle / Maven projects, ones that already have Kotlin configured.

For example, for the whole package (one of `klutter-all` (lastest JDK), `klutter-all-jdk6`, `klutter-all-jdk7`, or `klutter-all-jdk8`) and using an
open-ended dependency number while Klutter is in early active development:

**Gradle:**

```
compile "uy.klutter:klutter-all:0.14.+"
```

**Maven:**
```
<dependency>
    <groupId>uy.klutter</groupId>
    <artifactId>klutter-all</artifactId>
    <version>[0.14.0,0.15.0)</version>
</dependency>
```
 
See all modules and current versions on [Maven Central search](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter%22%20) (or the [raw repo](https://repo1.maven.org/maven2/uy/klutter/))

## Modules

|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven&#8209;Artifact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Size|Topic|Injekt|
|------|------|------|------|
|all|*|Everything available for a given JDK version|*|
|[aws-s3](aws-s3/)|tiny|Amazon AWS SDK S3 helper extensions|Yes|
|[aws-core](aws-core/)|tiny|Amazon AWS SDK helper extensions|No|
|[config-typesafe](config-typesafe/)|medium|Typesafe Config easier loader and access to configuration|No|
|[core](core/)|small|Core extension methods on strings, numbers, dates, URI Builder, better URL Encoding/Decoding|No|
|[elasticsearch](elasticsearch/)|medium|Extensions to ElasticSearch client library|No|
|[json-jackson](json-jackson/)|tiny|Jackson JSON Data Binding extensions + loading of Kotlin module|Yes|
|[netflix-graph](netflix-graph/)|medium|In memory graph building and compression/serialization.  A wrapper adding schema, ordinal tracking, serialization with ordinals, and is much easier to use API|No|
|[vertx3](vertx3/)|medium|Vert.x-3 helpers and integration with Kovenant Promises|Yes|

Modules marked with "yes" for Injekt, are modules that have intergration with [Kohesive/Injekt](http://github.com/kohesive/injekt) and provide prebuild injectable modules that you can easily import providing factories or singletons for dependency injection.  Using an Injekt module looks something like:

```kotlin
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
            importModule(AmazonS3Injektables)  
            importModule(JacksonWithKotlinAndJdk8Injektables)
        }
    }

    ...
    // later, use them in properties in any class
    val s3: AmazonS3Client by Delegates.injectLazy()
    // or use them anywhere in code
    val myObject: CoolObject = Inject.get<ObjectMapper>().readValue(jsonString)
    // or another form of the same
    val mapper: ObjectMapper = Inject.get()
    // or within constructor or method definitions as default values
    public fun doSomethingWithS3(s3: AmazonS3Client = Inject.get()) { ... }
}
```

Some of these modules are "tiny" and may not be overly useful yet, but they carry no extra weight, only have required dependencies and can be expanded over time by anyone that wants to send pull requests.  Submitted modules or changes to existing module consist of things not conflicting with Kotlin runtime libraries, and things useful to most Kotlin developers.

## Recommended libraries:

Other libraries that we recommend a building blocks for Kotlin applications:

* [Injekt](https://github.com/kohesive/injekt/blob/master/README.md) - Injekt is a crazyily easy Dependency Injection for Kotlin. 
* [Kovenant](http://kovenant.komponents.nl) - promises for Kotlin, easy, fun, and async! (JVM / Android)
* [Kovert](https://github.com/kohesive/kovert) - invisible REST framework for Kotlin + Vert.x

## With help from...

[![Collokia Logo](https://www.collokia.com/images/collokia-logo-210x75.png)](https://www.collokia.com)


