[uy.klutter.core.collections](../index.md) / [kotlin.collections.List](.)


### Extensions for kotlin.collections.List

|&nbsp;|&nbsp;|
|---|---|
| [asReadOnly](as-read-only.md) | `fun <T> List<T>.asReadOnly(): List<T>`<p>Wraps the List with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally</p> |
| [asReadOnlyCollection](as-read-only-collection.md) | `fun <T> List<T>.asReadOnlyCollection(): Collection<T>`<p>Wraps the List as a Collection with a lightweight delegating class that prevents casting back to mutable type</p> |
| [toImmutable](to-immutable.md) | `fun <T> List<T>.toImmutable(): List<T>`<p>Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,<br/>specializing for the case of the RandomAccess marker interface being retained if it was there originally</p> |
