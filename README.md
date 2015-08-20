[![Kotlin M12](https://img.shields.io/badge/Kotlin-M12%20%40%200.12.1230-blue.svg)](http://kotlinlang.org) [![Maven Version](https://img.shields.io/maven-central/v/uy.klutter/klutter-all-jdk8.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter%22) [![CircleCI branch](https://img.shields.io/circleci/project/klutter/klutter/master.svg)](https://circleci.com/gh/klutter/klutter/tree/master) [![Issues](https://img.shields.io/github/issues/klutter/klutter.svg)](https://github.com/klutter/klutter/issues?q=is%3Aopen) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/klutter/klutter/blob/master/LICENSE) 

# klutter
Random small libraries, usually extensions making other libraries happier.  

Please send contributions, fork the repo, issue a pull request and write a comment in the pull request granting the rights for the code to be used in Klutter.

## Maven Dependnecy

Each module has its own dependency.  And for any module, note if it has a version specific to your JDK version (use highest at or below), then include the dependency in your Gradle / Maven projects, ones that have Kotlin configured for Kotlin M12 versions `0.12.1218` or `0.12.1230`

For example, for the whole package (one of `klutter-all-jdk6`, `klutter-all-jdk7`, or `klutter-all-jdk8`) and using an open-ended dependency number while we are in development:

**Gradle:**

```
compile "uy.klutter:klutter-all-jdk8:0.+"
```

**Maven:**
```
<dependency>
    <groupId>uy.klutter</groupId>
    <artifactId>klutter-all-jdk8</artifactId>
    <version>[0.1.0,1.0.0)</version>
</dependency>
```
 
See all modules and current versions on [Maven Central search](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter%22%20) (or the [raw repo](https://repo1.maven.org/maven2/uy/klutter/))

## Modules

|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven&#8209;Artifact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Size|Topic|Injekt|
|------|------|------|------|
|[aws-s3](https://github.com/klutter/klutter/tree/master/aws-s3)|tiny|Amazon AWS SDK S3 helper extensions|Yes|
|[aws-core](https://github.com/klutter/klutter/tree/master/aws)|tiny|Amazon AWS SDK helper extensions|No|
|[config-typesafe-jdk7](https://github.com/klutter/klutter/tree/master/config-typesafe-jdk7)|medium|Typesafe Config easier loader and access to configuration|No|
|[config-typesafe-jdk8](https://github.com/klutter/klutter/tree/master/config-typesafe-jdk8)|medium|Same as above, but with newer version of Typesafe Config that is JDK 8 only|No|
|[core](https://github.com/klutter/klutter/tree/master/core)|small|Core extension methods on strings, numbers, dates, URI Builder, better URL Encoding/Decoding|No|
|[core-jdk7](https://github.com/klutter/klutter/tree/master/core-jdk7)|tiny|Same as above with more extensions based on classes added in JDK-7, includes core|No|
|[core-jdk8](https://github.com/klutter/klutter/tree/master/core-jdk8)|tiny|Same as above with more extensions based on classes added in JDK-8, includes core-JDK-7 & core|No|
|[core-jodatime](https://github.com/klutter/klutter/tree/master/core-jodatime)|tiny|Joda time extension methods, includes core|No|
|[json-jackson](https://github.com/klutter/klutter/tree/master/json-jackson)|tiny|Jackson JSON Data Binding extensions + loading of Kotlin module|Yes|
|[json-jackson-jdk8](https://github.com/klutter/klutter/tree/master/json-jackson-jdk8)|tiny|Same as above + loading of JDK 8 datatypes, JDK 8 date/time, and JDK 8 parameter names modules|Yes|
|[netflix-graph](https://github.com/klutter/klutter/tree/master/netflix-graph)|medium|In memory graph building and compression/serialization.  A wrapper adding schema, ordinal tracking, serialization with ordinals, and is much easier to use API|No|

Modules marked with "yes" for Injekt, are modules that have intergration with [Kohesive/Injekt](http://github.com/kohesive/injekt) and provide prebuild injectable modules that you can easily import providing factories or singletons for dependency injection.

Some of these modules are "tiny" and may not be overly useful yet, but they carry no extra weight, only have required dependencies and can be expanded over time by anyone that wants to send pull requests.  Submitted modules or changes to existing module consist of things not conflicting with Kotlin runtime libraries, and things useful to most Kotlin developers.

## Recommended libraries:

Other libraries that we recommend a building blocks for Kotlin applications:

* [Injekt](https://github.com/kohesive/injekt/blob/master/README.md) - Injekt is a crazyily easy Dependency Injection for Kotlin. 
* [Kovenant](http://kovenant.komponents.nl) - promises for Kotlin, easy, fun, and async! (JVM / Android)

## With help from...

[![Collokia Logo](https://www.collokia.com/images/collokia-logo-210x75.png)](https://www.collokia.com)


