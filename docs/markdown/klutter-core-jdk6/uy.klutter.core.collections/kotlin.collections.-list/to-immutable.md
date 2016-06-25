[uy.klutter.core.collections](../index.md) / [kotlin.collections.List](index.md) / [toImmutable](.)


# toImmutable
`fun &lt;T&gt; List&lt;T&gt;.toImmutable(): List&lt;T&gt;` [(source)](https://github.com/kohesive/klutter/blob/master/core-jdk6/src/main/kotlin/uy/klutter/core/common/Immutable.kt#L222)

Copies the List and then wraps with a lightweight delegating class that prevents casting back to mutable type,
specializing for the case of the RandomAccess marker interface being retained if it was there originally


