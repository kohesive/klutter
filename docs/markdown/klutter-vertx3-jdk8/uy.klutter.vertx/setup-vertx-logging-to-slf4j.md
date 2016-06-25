[uy.klutter.vertx](index.md) / [setupVertxLoggingToSlf4j](.)


# setupVertxLoggingToSlf4j
<code>fun setupVertxLoggingToSlf4j(): Unit</code> [(source)](https://github.com/kohesive/klutter/blob/master/vertx3-jdk8/src/main/kotlin/uy/klutter/vertx/VertxUtil.kt#L10)<br/>
Tell the Vert.x and Hazelcast logging facades to log through SLF4j, this must be called very early in your application
before the logging systems are activated.


