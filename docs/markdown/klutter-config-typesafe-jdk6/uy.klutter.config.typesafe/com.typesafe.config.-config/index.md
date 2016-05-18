[uy.klutter.config.typesafe](../index.md) / [com.typesafe.config.Config](.)


### Extensions for com.typesafe.config.Config


| [nested](nested.md) | `fun Config.nested(key:&nbsp;String): Config`
Return a nested configuration

 |
| [plus](plus.md) | `fun Config.plus(fallback:&nbsp;Config): Config`
Merge configurations by having the first fallback to the second

 |
| [render](render.md) | `fun Config.render(): String`
Render the configuration as a string, typically for logging

 |
| [value](value.md) | `fun Config.value(key:&nbsp;String): [ConfiguredValue](../-configured-value/index.md)`
Return a value from configuration as a more uniform temporary object that can be checked for existance, and has
additional methods for retrieving values, including with defaults.

 |

