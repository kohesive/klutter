[uy.klutter.core.collections](../index.md) / [kotlin.collections.List](.)


### Extensions for kotlin.collections.List

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](as-read-only.md) | `fun &lt;T&gt; List&lt;T&gt;.asReadOnly(): List&lt;T&gt;`<p>Wraps the List with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally</p> |
| [asReadOnlyCollection](as-read-only-collection.md) | `fun &lt;T&gt; List&lt;T&gt;.asReadOnlyCollection(): Collection&lt;T&gt;`<p>Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [toImmutable](to-immutable.md) | `fun &lt;T&gt; List&lt;T&gt;.toImmutable(): List&lt;T&gt;`<p>Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally</p> |
