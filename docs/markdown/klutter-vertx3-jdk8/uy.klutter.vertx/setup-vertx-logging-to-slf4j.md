[uy.klutter.vertx](index.md) / [setupVertxLoggingToSlf4j](.)


# setupVertxLoggingToSlf4j

`fun setupVertxLoggingToSlf4j(): Unit` [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxUtil.kt#L10)

Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application
before the logging systems are activated.



