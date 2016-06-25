[uy.klutter.config.typesafe](../index.md) / [com.typesafe.config.Config](.)


### Extensions for com.typesafe.config.Config

|&nbsp;|&nbsp;|
|---|---|
| [nested](nested.md) | `fun Config.nested(key:&nbsp;String): Config`<p>Return a nested configuration</p> |
| [plus](plus.md) | `fun Config.plus(fallback:&nbsp;Config): Config`<p>Merge configurations by having the first fallback to the second</p> |
| [render](render.md) | `fun Config.render(): String`<p>Render the configuration as a string, typically for logging</p> |
| [value](value.md) | `fun Config.value(key:&nbsp;String): [ConfiguredValue](../-configured-value/index.md)`<p>Return a value from configuration as a more uniform temporary object that can be checked for existance, and has<br/>additional methods for retrieving values, including with defaults.</p> |
