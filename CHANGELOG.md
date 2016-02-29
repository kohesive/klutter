=========================================================
2016-02-29 v1.16.0 release
=========================================================

* JDBI 2.x and 3.x (experimental) support for Kotlin parameter binding and ResultSet mapping
* dropped dependencies `klutter-all-*`, they are too noisy now with many different things

=========================================================
2016-02-20 v1.15.1 release
=========================================================

* Jackson to 2.7.1-2
* Injekt to 1.14.1

=========================================================
2016-02-15 v0.15.0 release
=========================================================

* Kotlin to 1.0.0
* Injekt to 1.14.0
* Kovenant to 3.0.0

=========================================================
2016-02-10 v0.14-RC-1050 release
=========================================================

Mainly for new Injekt release, possible small breaking change on scopes

* Injekt to 1.13.0-rc-1050
* SLF4j API to 1.7.14

=========================================================
2016-02-09 v0.13-RC-1050 release
=========================================================

Mainly for the Kotlin 1.0 public release candidate

* Kotlin to 1.0.0-rc-1050
* Injekt to 1.12.0-rc-1050
* Jackson-Kotlin module to 2.6.5-rc-1050
* Kovenant to 3.0.0-rc.1050

=========================================================
2016-02-04 v0.13-RC-1036 release
=========================================================

Mainly for the Kotlin 1.0 public release candidate

* Kotlin to 1.0.0-rc-1036
* Injekt to 1.12.0-rc-1036
* Jackson-Kotlin module to 2.6.5-rc-1036
* Kovenant to 3.0.0-rc.1036

=========================================================
2016-02-02 v0.13-RC-1025 release
=========================================================

Mainly for the Kotlin 1.0 release candidate binary compatibility

* Kotlin to 1.0.0-rc-1025
* Injekt to 1.12-rc-1025

=========================================================
2016-01-20 v0.12 release
=========================================================

* Kotlin to 1.0.0-beta-4589
* Injekt to 1.11
* added batching for sequences and iterable (generate a sequence of max sized lists or sequences)
* added function `withNotNull` (to go with `whenNotNull`)
* added docs for `initializedWith`, `verifiedWith`
* AWS SDK to 1.10.47
* SLF4j to 1.7.13

=========================================================
2015-12-30 v0.11.3 release
=========================================================

* added `initializedWith` function to go along with `initializedBy`
* added `verifiedWith` function to go along with `verifiedBy`
* function `with` now allows infix usage

=========================================================
2015-12-30 v0.11.2 release
=========================================================

* Kotlin to 1.0.0-beta-4584
* Injekt to 1.10.1
* Fix UriBuilder to output encoded URL without using URI class, prevents double encoding or undefined results (URI is not a great class)

Fixed artifacts that acted as groups of included submodules but included them as `runtime` instead of `compile`

=========================================================
2015-12-08 v0.11.1 release
=========================================================

* Kotlin to 1.0.0-beta-4583
* Injekt to 1.10.0
* Kovenant to 3.0.0-beta.4
* AWS SDK to 1.10.43
* ElasticSearch to 1.7.4
* Jackson to 2.6.4-1

=========================================================
2015-12-08 v0.11.0 release
=========================================================

* Kotlin to 1.0.0-beta-4583
* Injekt to 1.10.0
* Kovenant to 3.0.0-beta.4
* AWS SDK to 1.10.43
* ElasticSearch to 1.7.4

This needs updated Jackson but the new release isn't ready yet, technically all is ok.

=========================================================
2015-12-08 v0.10.0 release
=========================================================

* Vertx to 3.2.0
* Injekt to 1.9.0

=========================================================
2015-12-08 v0.9.0 release
=========================================================

* Kotlin to BETA 3 1.0.0-beta-3595
* Injekt to 1.8.4
* AWS SDK to 1.10.39
* Jackson to 2.6.4
* JodaTime to 2.9.1
* Kovenant to 3.0.0-beta.3

=========================================================
2015-11-16 v0.8.4 release
=========================================================

* Kotlin to 1.0.0-beta-2423
* Injekt to 1.8.3

=========================================================
2015-11-16 v0.8.2 release
=========================================================

* Kotlin to 1.0.0-beta-2422
* Injekt to 1.8.2
* Jackson Kotlin module to 2.6.3-3

=========================================================
2015-11-09 v0.8.1 release
=========================================================

* Kotlin to 1.0.0-beta-1103
* Injekt to 1.8.1
* AWS SDK to 1.10.32
* Jackson Kotlin module to 2.6.3-2
* Vert.x to 3.1.0
* ElasticSearch to 1.7.3

=========================================================
2015-10-06 v0.8.0 release
=========================================================

* Kotlin to 1.0.0-beta-1038
* Jackson Kotlin module to 2.6.3-1
* Injekt to 1.8.0
* Kovenant to 2.9.0

=========================================================
2015-10-06 v0.7.2 release
=========================================================

* more identity conversion tests in Converters (reflect module)
* more boolean conversions in Converters (reflect module)

=========================================================
2015-10-06 v0.7.1 release
=========================================================

* Kotlin to 0.14.451
* Jackson Kotlin module to 2.6.2-3
* Injekt to 1.7.1
* Amazon AWS SDK to 1.10.22

=========================================================
2015-10-01 v0.7.0 release
=========================================================

* Kotlin to 0.14.449
* Updated to Kovenant 2.7.0
* Updated to Injekt 1.7.0

reflect module is incubating, do not use without risk.

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

