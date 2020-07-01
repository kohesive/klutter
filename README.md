[![Kotlin](https://img.shields.io/badge/kotlin-1.3.72-blue.svg)](http://kotlinlang.org) [![Maven Central](https://img.shields.io/maven-central/v/uy.kohesive.klutter/klutter-core.svg)](https://mvnrepository.com/artifact/uy.kohesive.klutter)  [![CircleCI branch](https://img.shields.io/circleci/project/kohesive/klutter/master.svg)](https://circleci.com/gh/kohesive/klutter/tree/master) [![Issues](https://img.shields.io/github/issues/kohesive/klutter.svg)](https://github.com/kohesive/klutter/issues?q=is%3Aopen) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/kohesive/klutter/blob/master/LICENSE) [![Kotlin Slack #kohesive](https://img.shields.io/badge/chat-kotlin%20slack%20%23kohesive-orange.svg)](http://kotlinslackin.herokuapp.com)

# klutter

Random small libraries, usually extensions making other libraries happier. Versions later than 2.x are for JDK 8 and newer only.  

## Maven Dependency

Each module has its own set of dependencies.  The basic pattern is:

**Gradle:**

```
compile "uy.kohesive.klutter:klutter-moduleName:3.0.+"
```

**Maven:**
```
<dependency>
    <groupId>uy.kohesive.klutter</groupId>
    <artifactId>klutter-moduleName</artifactId>
    <version>[3.0.0,3.1.0)</version>
</dependency>
```
 
See all modules and current versions on [mvenrepository.com](https://mvnrepository.com/artifact/uy.kohesive.klutter)

## Modules

|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Maven&#8209;Artifact&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|Topic|
|------|------|
|[config-typesafe](config-typesafe/)|Typesafe Config easier loader and access to configuration|
|[config-typesafe-kodein](config-typesafe/)|Typesafe Config injection into Kodein 6.5 modules|
|[conversion](conversion/)|Type converters for primitive and common JDK types|
|[core](core/)|Core extension methods on strings, numbers, dates, URI Builder, better URL Encoding/Decoding|
[db-jdbi-v2|See JDBI official support in 3.x|
|db-jdbi-v3|Merged into JDBI official, 3.x snapshot and releases after March 7, 2017|
|elasticsearch-1.7.x|REMOVED in Klutter 3.x|
|elasticsearch-2.4.x|REMOVED in Klutter 3.x|
|elasticsearch-5.x|REMOVED in Klutter 3.x|
|elasticsearch-6.x|REMOVED in Klutter 3.x|
|[json-jackson-kodein](json-jackson/)|Jackson JSON w/Kotlin module Kodein 6.5 module|
|[netflix-graph](netflix-graph/)|In memory graph building and compression/serialization.  A wrapper adding schema, ordinal tracking, serialization with ordinals, and is much easier to use API|
|[reflect](reflect-full/)|Helpers for Kotlin reflection extending the kotlin-reflect dependency|
|vertx3|REMOVED in Klutter 3.x, Vertx has native Kotlin Coroutine support now|
|vertx3-kodein|REMOVED in Klutter 3.x, Vertx has native Kotlin Coroutine support now|

## Recommended libraries:

Other libraries that we recommend a building blocks for Kotlin applications:

* [Kovert](https://github.com/kohesive/kovert) - invisible REST framework for Kotlin + Vert.x
* [Kodein](https://github.com/Kodein-Framework/Kodein-DI) - very easy yet powerful dependency injection in Kotlin, now supporting version 5.x

