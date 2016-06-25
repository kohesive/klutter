[uy.klutter.core.common](.)


## Package uy.klutter.core.common

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [kotlin.collections.Collection](kotlin.collections.-collection/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [initializedBy](initialized-by.md) | `infix fun &lt;T&gt; T.initializedBy(initWith:&nbsp;(T)&nbsp;-&gt;&nbsp;Unit): T`<p>Make initialization of a parameter more readable with its initialization work done inline with the declaration<br/>The instance is passed as a variable to the initialization lambda</p> |
| [initializedWith](initialized-with.md) | `infix fun &lt;T&gt; T.initializedWith(initWith:&nbsp;T.()&nbsp;-&gt;&nbsp;Unit): T`<p>Make initialization of a parameter more readable with its initialization work done inline with the declaration<br/>The receiver of the lambda is the variable to be initialized</p> |
| [verifiedBy](verified-by.md) | `infix fun &lt;T&gt; T.verifiedBy(verifyWith:&nbsp;(T)&nbsp;-&gt;&nbsp;Unit): T`<p>Make initialization of a parameter more readable with its verification work done inline with the declaration<br/>The instance is passed as a variable to the initialization lambda</p> |
| [verifiedWith](verified-with.md) | `infix fun &lt;T&gt; T.verifiedWith(verifyWith:&nbsp;T.()&nbsp;-&gt;&nbsp;Unit): T`<p>Make initialization of a parameter more readable with its verification work done inline with the declaration<br/>The receiver of the lambda is the variable to be initialized</p> |
| [whenNotNull](when-not-null.md) | `infix fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; T?.whenNotNull(thenDo:&nbsp;(T)&nbsp;-&gt;&nbsp;R?): R?`<p>When something isnt null do something, kinda the opposite of ?:</p> |
| [with](with.md) | `infix fun &lt;T&gt; T.with(initWith:&nbsp;T.()&nbsp;-&gt;&nbsp;Unit): T`<p>Make initialization of a parameter more readable with its initialization work done inline with the declaration</p> |
| [withNotNull](with-not-null.md) | `infix fun &lt;T&nbsp;:&nbsp;Any, R&nbsp;:&nbsp;Any&gt; T?.withNotNull(thenDo:&nbsp;T.()&nbsp;-&gt;&nbsp;R?): R?`<p>When something isnt null do something, kinda the opposite of ?:</p> |
