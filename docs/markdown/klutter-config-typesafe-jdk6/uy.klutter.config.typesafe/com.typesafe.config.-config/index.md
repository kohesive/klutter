[uy.klutter.config.typesafe](../index.md) / [com.typesafe.config.Config](.)


### Extensions for com.typesafe.config.Config

|&nbsp;|&nbsp;|
|---|---|
| [nested](nested.md) | <code>fun Config.nested(key: String): Config</code><br/>Return a nested configuration |
| [plus](plus.md) | <code>fun Config.plus(fallback: Config): Config</code><br/>Merge configurations by having the first fallback to the second |
| [render](render.md) | <code>fun Config.render(): String</code><br/>Render the configuration as a string, typically for logging |
| [value](value.md) | <code>fun Config.value(key: String): [ConfiguredValue](../-configured-value/index.md)</code><br/>Return a value from configuration as a more uniform temporary object that can be checked for existance, and has<br/>additional methods for retrieving values, including with defaults. |
