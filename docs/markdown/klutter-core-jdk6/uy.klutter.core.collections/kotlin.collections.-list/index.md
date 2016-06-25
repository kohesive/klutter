[uy.klutter.core.collections](../index.md) / [kotlin.collections.List](.)


### Extensions for kotlin.collections.List

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](as-read-only.md) | <code>fun <T> List<T>.asReadOnly(): List<T></code><br/>Wraps the List with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally |
| [asReadOnlyCollection](as-read-only-collection.md) | <code>fun <T> List<T>.asReadOnlyCollection(): Collection<T></code><br/>Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type |
| [toImmutable](to-immutable.md) | <code>fun <T> List<T>.toImmutable(): List<T></code><br/>Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally |
