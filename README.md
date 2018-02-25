[![Kotlin](https://img.shields.io/badge/kotlin-1.2.21-blue.svg)](http://kotlinlang.org) [![Maven Central](https://img.shields.io/maven-central/v/uy.kohesive.klutter/klutter-core.svg)](https://mvnrepository.com/artifact/uy.kohesive.klutter)  [![CircleCI branch](https://img.shields.io/circleci/project/kohesive/klutter/master.svg)](https://circleci.com/gh/kohesive/klutter/tree/master) [![Issues](https://img.shields.io/github/issues/klutter/kovert.svg)](https://github.com/kohesive/klutter/issues?q=is%3Aopen) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/kohesive/klutter/blob/master/LICENSE) [![Kotlin Slack #kohesive](https://img.shields.io/badge/chat-kotlin%20slack%20%23kohesive-orange.svg)](http://kotlinslackin.herokuapp.com)

# klutter

Random small libraries, usually extensions making other libraries happier. Versions later than 2.x are for JDK 8 and newer only.  Use 1.x for older JDK's.

## Maven Dependency

Each module has its own set of dependencies.  The basic pattern is:

**Gradle:**

```
compile "uy.kohesive.klutter:klutter-moduleName:2.3.+"
```

**Maven:**
```
<dependency>
    <groupId>uy.kohesive.klutter</groupId>
    <artifactId>klutter-moduleName</artifactId>
    <version>[2.3.0,2.4.0)</version>
</dependency>
```
 
See all modules and current versions on [mvenrepository.com](https://mvnrepository.com/artifact/uy.kohesive.klutter)

## Modules

|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven&#8209;Artifact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Topic|
|------|------|
|[config-typesafe](config-typesafe/)|Typesafe Config easier loader and access to configuration|
|[config-typesafe-kodein](config-typesafe/)|Typesafe Config injection into Kodein modules|
|[conversion](conversion/)|Type converters for primitive and common JDK types|
|[core](core/)|Core extension methods on strings, numbers, dates, URI Builder, better URL Encoding/Decoding|
|[db-jdbi-v2](db-jdbi-v2/)|Adds Kotlin parameter binding and RestulSet mapping to JDBI versions 2.x|
|db-jdbi-v3|Merged into JDBI official, 3.x snapshot and releases after March 7, 2017|
|[elasticsearch-1.7.x](elasticsearch-1.7.x/)|Extensions and kovenant Promises for ElasticSearch client library for ES 1.7.x|
|[elasticsearch-2.4.x](elasticsearch-2.4.x/)|Extensions and kovenant Promises for ElasticSearch client library for ES 2.4.x|
|[elasticsearch-5.x](elasticsearch-5.x/)|Extensions and kovenant Promises for ElasticSearch client library for ES 5.x|
|[json-jackson-kodein](json-jackson/)|Jackson JSON w/Kotlin module Kodein module|
|[netflix-graph](netflix-graph/)|In memory graph building and compression/serialization.  A wrapper adding schema, ordinal tracking, serialization with ordinals, and is much easier to use API|
|[reflect](reflect-full/)|Helpers for Kotlin reflection extending the kotlin-reflect dependency|
|[vertx3](vertx3/)|Vert.x-3 helpers and integration with Kovenant Promises|
|[vertx3-kodein](vertx3-kodein/)|Vert.x-3 Kodein modules|

## Recommended libraries:

Other libraries that we recommend a building blocks for Kotlin applications:

* [Kovenant](http://kovenant.komponents.nl) - promises for Kotlin, easy, fun, and async! (JVM / Android)
* [Kovert](https://github.com/kohesive/kovert) - invisible REST framework for Kotlin + Vert.x
* [Kodein](https://github.com/SalomonBrys/Kodein) - very easy yet powerful dependency injection in Kotlin

