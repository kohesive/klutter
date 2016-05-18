[uy.klutter.config.typesafe](../index.md) / [ConfiguredValue](index.md) / [parseDuration](.)


# parseDuration

`fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/TypesafeConfig_Ext.kt#L78)

Parse config string such as "30s", "30 seconds", "4m" representing a time duration resulting in a Long of desired TimeUnit



`fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue:&nbsp;Long): Long` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/TypesafeConfig_Ext.kt#L79)


