[uy.klutter.config.typesafe](../index.md) / [ConfiguredValue](.)


# ConfiguredValue

`class ConfiguredValue` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/TypesafeConfig_Ext.kt#L38)

Intermediate object for providing additional functionality on a configured item




### Constructors


| [&lt;init&gt;](-init-.md) | `ConfiguredValue(cfg:&nbsp;Config, key:&nbsp;String)`
Intermediate object for providing additional functionality on a configured item

 |


### Properties


| [cfg](cfg.md) | `val cfg: Config` |
| [key](key.md) | `val key: String` |


### Functions


| [asBoolean](as-boolean.md) | `fun asBoolean(): Boolean`
`fun asBoolean(defaultValue:&nbsp;Boolean): Boolean` |
| [asBooleanOrNull](as-boolean-or-null.md) | `fun asBooleanOrNull(): Boolean?` |
| [asBytesList](as-bytes-list.md) | `fun asBytesList(): List&lt;Long&gt;`
`fun asBytesList(defaultValue:&nbsp;List&lt;Long&gt;): List&lt;Long&gt;` |
| [asBytesListOrEmpty](as-bytes-list-or-empty.md) | `fun asBytesListOrEmpty(): List&lt;Long&gt;` |
| [asBytesListOrNull](as-bytes-list-or-null.md) | `fun asBytesListOrNull(): List&lt;Long&gt;?` |
| [asDouble](as-double.md) | `fun asDouble(): Double`
`fun asDouble(defaultValue:&nbsp;Double): Double` |
| [asDoubleList](as-double-list.md) | `fun asDoubleList(): List&lt;Double&gt;`
`fun asDoubleList(defaultValue:&nbsp;List&lt;Double&gt;): List&lt;Double&gt;` |
| [asDoubleListOrEmpty](as-double-list-or-empty.md) | `fun asDoubleListOrEmpty(): List&lt;Double&gt;` |
| [asDoubleListOrNull](as-double-list-or-null.md) | `fun asDoubleListOrNull(): List&lt;Double&gt;?` |
| [asDoubleOrNull](as-double-or-null.md) | `fun asDoubleOrNull(): Double?` |
| [asFile](as-file.md) | `fun asFile(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileOrNull](as-file-or-null.md) | `fun asFileOrNull(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asFileRelative](as-file-relative.md) | `fun asFileRelative(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileRelativeOrNull](as-file-relative-or-null.md) | `fun asFileRelativeOrNull(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asFileSibling](as-file-sibling.md) | `fun asFileSibling(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileSiblingOrNull](as-file-sibling-or-null.md) | `fun asFileSiblingOrNull(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asInt](as-int.md) | `fun asInt(): Int`
`fun asInt(defaultValue:&nbsp;Int): Int` |
| [asIntArray](as-int-array.md) | `fun asIntArray(): Array&lt;Int&gt;`
`fun asIntArray(defaultValue:&nbsp;Array&lt;Int&gt;): Array&lt;Int&gt;` |
| [asIntArrayOrEmpty](as-int-array-or-empty.md) | `fun asIntArrayOrEmpty(): Array&lt;Int&gt;` |
| [asIntArrayOrNull](as-int-array-or-null.md) | `fun asIntArrayOrNull(): Array&lt;Int&gt;?` |
| [asIntList](as-int-list.md) | `fun asIntList(): List&lt;Int&gt;`
`fun asIntList(defaultValue:&nbsp;List&lt;Int&gt;): List&lt;Int&gt;` |
| [asIntListOrEmpty](as-int-list-or-empty.md) | `fun asIntListOrEmpty(): List&lt;Int&gt;` |
| [asIntListOrNull](as-int-list-or-null.md) | `fun asIntListOrNull(): List&lt;Int&gt;?` |
| [asIntOrNull](as-int-or-null.md) | `fun asIntOrNull(): Int?` |
| [asLong](as-long.md) | `fun asLong(): Long`
`fun asLong(defaultValue:&nbsp;Long): Long` |
| [asLongList](as-long-list.md) | `fun asLongList(): List&lt;Long&gt;`
`fun asLongList(defaultValue:&nbsp;List&lt;Long&gt;): List&lt;Long&gt;` |
| [asLongListOrEmpty](as-long-list-or-empty.md) | `fun asLongListOrEmpty(): List&lt;Long&gt;` |
| [asLongListOrNull](as-long-list-or-null.md) | `fun asLongListOrNull(): List&lt;Long&gt;?` |
| [asLongOrNull](as-long-or-null.md) | `fun asLongOrNull(): Long?` |
| [asObject](as-object.md) | `fun asObject(): ConfigObject` |
| [asObjectList](as-object-list.md) | `fun asObjectList(): List&lt;ConfigObject&gt;` |
| [asObjectListOrEmpty](as-object-list-or-empty.md) | `fun asObjectListOrEmpty(): List&lt;ConfigObject&gt;` |
| [asObjectListOrNull](as-object-list-or-null.md) | `fun asObjectListOrNull(): List&lt;ConfigObject&gt;?` |
| [asObjectOrNull](as-object-or-null.md) | `fun asObjectOrNull(): ConfigObject?` |
| [asString](as-string.md) | `fun asString(): String`
`fun asString(defaultValue:&nbsp;String): String` |
| [asStringArray](as-string-array.md) | `fun asStringArray(): Array&lt;String&gt;`
`fun asStringArray(defaultValue:&nbsp;Array&lt;String&gt;): Array&lt;String&gt;` |
| [asStringArrayOrEmpty](as-string-array-or-empty.md) | `fun asStringArrayOrEmpty(): Array&lt;String&gt;` |
| [asStringArrayOrNull](as-string-array-or-null.md) | `fun asStringArrayOrNull(): Array&lt;String&gt;?` |
| [asStringList](as-string-list.md) | `fun asStringList(): List&lt;String&gt;`
`fun asStringList(defaultValue:&nbsp;List&lt;String&gt;): List&lt;String&gt;` |
| [asStringListOrEmpty](as-string-list-or-empty.md) | `fun asStringListOrEmpty(): List&lt;String&gt;` |
| [asStringListOrNull](as-string-list-or-null.md) | `fun asStringListOrNull(): List&lt;String&gt;?` |
| [asStringOrNull](as-string-or-null.md) | `fun asStringOrNull(): String?` |
| [exists](exists.md) | `fun exists(): Boolean` |
| [isBlankString](is-blank-string.md) | `fun isBlankString(): Boolean` |
| [isEmptyString](is-empty-string.md) | `fun isEmptyString(): Boolean` |
| [isNotBlankString](is-not-blank-string.md) | `fun isNotBlankString(): Boolean` |
| [isNotEmptyString](is-not-empty-string.md) | `fun isNotEmptyString(): Boolean` |
| [notExists](not-exists.md) | `fun notExists(): Boolean` |
| [parseBytes](parse-bytes.md) | `fun parseBytes(): Long`
Parse config string such as 4000 (4000 bytes), 4K, 4M, 4G representing a size in bytes resulting in a Long

`fun parseBytes(defaultValue:&nbsp;Long): Long` |
| [parseBytesOrNull](parse-bytes-or-null.md) | `fun parseBytesOrNull(): Long?` |
| [parseDuration](parse-duration.md) | `fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long`
Parse config string such as "30s", "30 seconds", "4m" representing a time duration resulting in a Long of desired TimeUnit

`fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue:&nbsp;Long): Long` |
| [parseDurationList](parse-duration-list.md) | `fun parseDurationList(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List&lt;Long&gt;`
`fun parseDurationList(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue:&nbsp;List&lt;Long&gt;): List&lt;Long&gt;` |
| [parseDurationListOrEmpty](parse-duration-list-or-empty.md) | `fun parseDurationListOrEmpty(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List&lt;Long&gt;` |
| [parseDurationListOrNull](parse-duration-list-or-null.md) | `fun parseDurationListOrNull(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List&lt;Long&gt;?` |
| [parseDurationOrNull](parse-duration-or-null.md) | `fun parseDurationOrNull(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long?` |

