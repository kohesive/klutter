[uy.klutter.core.common](.)


## Package uy.klutter.core.common

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [kotlin.collections.Collection](kotlin.collections.-collection/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [initializedBy](initialized-by.md) | <code>infix fun <T> T.initializedBy(initWith: (T) -> Unit): T</code><br/>Make initialization of a parameter more readable with its initialization work done inline with the declaration<br/>The instance is passed as a variable to the initialization lambda |
| [initializedWith](initialized-with.md) | <code>infix fun <T> T.initializedWith(initWith: T.() -> Unit): T</code><br/>Make initialization of a parameter more readable with its initialization work done inline with the declaration<br/>The receiver of the lambda is the variable to be initialized |
| [verifiedBy](verified-by.md) | <code>infix fun <T> T.verifiedBy(verifyWith: (T) -> Unit): T</code><br/>Make initialization of a parameter more readable with its verification work done inline with the declaration<br/>The instance is passed as a variable to the initialization lambda |
| [verifiedWith](verified-with.md) | <code>infix fun <T> T.verifiedWith(verifyWith: T.() -> Unit): T</code><br/>Make initialization of a parameter more readable with its verification work done inline with the declaration<br/>The receiver of the lambda is the variable to be initialized |
| [whenNotNull](when-not-null.md) | <code>infix fun <T : Any, R : Any> T?.whenNotNull(thenDo: (T) -> R?): R?</code><br/>When something isnt null do something, kinda the opposite of ?: |
| [with](with.md) | <code>infix fun <T> T.with(initWith: T.() -> Unit): T</code><br/>Make initialization of a parameter more readable with its initialization work done inline with the declaration |
| [withNotNull](with-not-null.md) | <code>infix fun <T : Any, R : Any> T?.withNotNull(thenDo: T.() -> R?): R?</code><br/>When something isnt null do something, kinda the opposite of ?: |
