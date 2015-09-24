=========================================================
2015-09-21 v0.6.4 / v0.6.5 / v0.6.6 releases
=========================================================

* ElasticSearch mappings now have init builder as optional
* mapping helper methods names change, see deprecated warnings for new names
* added mapping helpers using a class as the guide for field names so you can pass in property references

=========================================================
2015-09-21 v0.6.3 release
=========================================================

* Change Kovenant + Vert.x dispatcher to NOT order serially the async calls, but run them in parallel.  Use `vertx.executeBlocking` methods directly to change this behavior.

=========================================================
2015-09-21 v0.6.2 release
=========================================================

* Update Jackson Kotlin module to 2.6.2-1  (bug fixes for Kotlin M13)

=========================================================
2015-09-21 v0.6.1 release
=========================================================

* Fix elasticsearch transport client helper function, was returning local node
* Update dependency to Injekt 1.6.1

=========================================================
2015-09-14 v0.6.0 release
=========================================================

* BREAKING CHANGE: Moved vertx json functions to subpackage to avoid accidental import and collision with other json builders

=========================================================
2015-09-14 v0.5.0 release
=========================================================

Kotlin M13 support, updated to Kovenant 2.5.0, Jackson 2.6.2, Injekt 1.5.0, ElasticSearch 1.7.2

=========================================================
2015-09-14 v0.4.0 release
=========================================================

Added ElasticSearch helpers, integration with Kovenant promises in klutter/elasticsearch module, see module docs for more information

=========================================================
2015-08-31 v0.3.0 release
=========================================================

Added Vertx3 helpers in the klutter/vertx3 module, see module docs for more information

=========================================================
2015-08-20 v0.2.1 release
=========================================================

Moved JDK7 typesafe config extension methods into .jdk7 subpackage until Kotlin can merge
two package fragments from two jars better.

=========================================================
2015-08-21 v0.2.0 release
=========================================================

Documentation added for every module.

Change naming so that all libraries have the JDK target in the name.  And the default module name
without the JDK version is always "latest JDK" (at this moment, JDK8 or highest available for the module).

Klutter-Config-Typesafe now has JDK6 build.

`Long.humanReadonable()` extension mispelling fixed

`Path.deleteRecursive()` renamed to `deleteRecursively` to match Kotlin `File.deleteRecursively`


=========================================================
2015-08-20 v0.1.1 release
=========================================================

First release to Maven Central

