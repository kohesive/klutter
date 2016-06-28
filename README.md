[![Kotlin](https://img.shields.io/badge/kotlin-1.0.2-blue.svg)](http://kotlinlang.org) [![Maven Version](https://img.shields.io/maven-central/v/uy.klutter.v2/klutter-all-jdk8.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter.v2%22) [![CircleCI branch](https://img.shields.io/circleci/project/kohesive/klutter/master.svg)](https://circleci.com/gh/kohesive/klutter/tree/master) [![Issues](https://img.shields.io/github/issues/kohesive/klutter.svg)](https://github.com/kohesive/klutter/issues?q=is%3Aopen) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/kohesive/klutter/blob/master/LICENSE) [![Kotlin Slack](https://img.shields.io/badge/chat-kotlin%20slack-orange.svg)](http://kotlinslackin.herokuapp.com)

# klutter

Random small libraries, usually extensions making other libraries happier. Versions later than 2.x are for JDK 8 and newer only.  Use 1.x for older JDK's.

## Maven Dependency

Each module has its own set of dependencies.  The basic pattern is:

**Gradle:**

```
compile "uy.kohesive.klutter:klutter-moduleName:2.0.+"
```

**Maven:**
```
<dependency>
    <groupId>uy.kohesive.klutter</groupId>
    <artifactId>klutter-moduleName</artifactId>
    <version>[2.0.0,2.1.0)</version>
</dependency>
```
 
See all modules and current versions on [Maven Central search](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22uy.klutter.v2%22%20) (or the [raw repo](https://repo1.maven.org/maven2/uy/klutter/v2/))

## Modules

|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven&#8209;Artifact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Size|Topic|Kodein module|
|------|------|------|------|
|[aws-s3](aws-s3/)|tiny|Amazon AWS SDK S3 helper extensions|Yes|
|[aws-core](aws-core/)|tiny|Amazon AWS SDK helper extensions|No|
|[config-typesafe](config-typesafe/)|medium|Typesafe Config easier loader and access to configuration|No|
|[core](core/)|small|Core extension methods on strings, numbers, dates, URI Builder, better URL Encoding/Decoding|No|
|[db-jdbi-v2](db-jdbi-v2/)|small|Adds Kotlin parameter binding and RestulSet mapping to JDBI versions 2.x|No|
|[db-jdbi-v3](db-jdbi-v3/)|small|Adds Kotlin parameter binding and RestulSet mapping to JDBI versions 3.x|No|
|[elasticsearch-1.7x](elasticsearch-1.7x/)|medium|Extensions to ElasticSearch client library|No|
|[json-jackson](json-jackson/)|tiny|Jackson JSON Data Binding extensions + loading of Kotlin module|Yes|
|[netflix-graph](netflix-graph/)|medium|In memory graph building and compression/serialization.  A wrapper adding schema, ordinal tracking, serialization with ordinals, and is much easier to use API|No|
|[reflect-core](reflect-core/)|small|Helpers for Kotlin reflection that do not require kotlin-reflect dependency|No|
|[reflect-full](reflect-full/)|small|Helpers for Kotlin reflection extending the kotlin-reflect dependency|No|
|[vertx3](vertx3/)|medium|Vert.x-3 helpers and integration with Kovenant Promises|Yes|

Modules marked with "yes" for Kodein, are modules that have prebuilt modules for injecting with [Kodein](https://github.com/SalomonBrys/Kodein).

Some of these modules are "tiny" and may not be overly useful yet, but they carry no extra weight, only have required dependencies and can be expanded over time by anyone that wants to send pull requests.  Submitted modules or changes to existing module consist of things not conflicting with Kotlin runtime libraries, and things useful to most Kotlin developers.

## Recommended libraries:

Other libraries that we recommend a building blocks for Kotlin applications:

* [Kovenant](http://kovenant.komponents.nl) - promises for Kotlin, easy, fun, and async! (JVM / Android)
* [Kovert](https://github.com/kohesive/kovert) - invisible REST framework for Kotlin + Vert.x
* [Kodein](https://github.com/SalomonBrys/Kodein) - very easy yet powerful dependency injection in Kotlin

## With help from...

[![Collokia Logo](https://www.collokia.com/images/collokia-logo-210x75.png)](https://www.collokia.com)


