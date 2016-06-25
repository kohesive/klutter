[uy.klutter.config.typesafe](../index.md) / [ConfiguredValue](.)


# ConfiguredValue
`class ConfiguredValue` [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/TypesafeConfig_Ext.kt#L38)

Intermediate object for providing additional functionality on a configured item



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | `ConfiguredValue(cfg:&nbsp;Config, key:&nbsp;String)`<p>Intermediate object for providing additional functionality on a configured item</p> |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [cfg](cfg.md) | `val cfg: Config` |
| [key](key.md) | `val key: String` |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [asBoolean](as-boolean.md) | `fun asBoolean(): Boolean`
`fun asBoolean(defaultValue:&nbsp;Boolean): Boolean` |
| [asBooleanOrNull](as-boolean-or-null.md) | `fun asBooleanOrNull(): Boolean?` |
| [asBytesList](as-bytes-list.md) | `fun asBytesList(): List<Long>`
`fun asBytesList(defaultValue:&nbsp;List<Long>): List<Long>` |
| [asBytesListOrEmpty](as-bytes-list-or-empty.md) | `fun asBytesListOrEmpty(): List<Long>` |
| [asBytesListOrNull](as-bytes-list-or-null.md) | `fun asBytesListOrNull(): List<Long>?` |
| [asDouble](as-double.md) | `fun asDouble(): Double`
`fun asDouble(defaultValue:&nbsp;Double): Double` |
| [asDoubleList](as-double-list.md) | `fun asDoubleList(): List<Double>`
`fun asDoubleList(defaultValue:&nbsp;List<Double>): List<Double>` |
| [asDoubleListOrEmpty](as-double-list-or-empty.md) | `fun asDoubleListOrEmpty(): List<Double>` |
| [asDoubleListOrNull](as-double-list-or-null.md) | `fun asDoubleListOrNull(): List<Double>?` |
| [asDoubleOrNull](as-double-or-null.md) | `fun asDoubleOrNull(): Double?` |
| [asFile](as-file.md) | `fun asFile(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileOrNull](as-file-or-null.md) | `fun asFileOrNull(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asFileRelative](as-file-relative.md) | `fun asFileRelative(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileRelativeOrNull](as-file-relative-or-null.md) | `fun asFileRelativeOrNull(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asFileSibling](as-file-sibling.md) | `fun asFileSibling(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)` |
| [asFileSiblingOrNull](as-file-sibling-or-null.md) | `fun asFileSiblingOrNull(relativeTo:&nbsp;[File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?` |
| [asInt](as-int.md) | `fun asInt(): Int`
`fun asInt(defaultValue:&nbsp;Int): Int` |
| [asIntArray](as-int-array.md) | `fun asIntArray(): Array<Int>`
`fun asIntArray(defaultValue:&nbsp;Array<Int>): Array<Int>` |
| [asIntArrayOrEmpty](as-int-array-or-empty.md) | `fun asIntArrayOrEmpty(): Array<Int>` |
| [asIntArrayOrNull](as-int-array-or-null.md) | `fun asIntArrayOrNull(): Array<Int>?` |
| [asIntList](as-int-list.md) | `fun asIntList(): List<Int>`
`fun asIntList(defaultValue:&nbsp;List<Int>): List<Int>` |
| [asIntListOrEmpty](as-int-list-or-empty.md) | `fun asIntListOrEmpty(): List<Int>` |
| [asIntListOrNull](as-int-list-or-null.md) | `fun asIntListOrNull(): List<Int>?` |
| [asIntOrNull](as-int-or-null.md) | `fun asIntOrNull(): Int?` |
| [asLong](as-long.md) | `fun asLong(): Long`
`fun asLong(defaultValue:&nbsp;Long): Long` |
| [asLongList](as-long-list.md) | `fun asLongList(): List<Long>`
`fun asLongList(defaultValue:&nbsp;List<Long>): List<Long>` |
| [asLongListOrEmpty](as-long-list-or-empty.md) | `fun asLongListOrEmpty(): List<Long>` |
| [asLongListOrNull](as-long-list-or-null.md) | `fun asLongListOrNull(): List<Long>?` |
| [asLongOrNull](as-long-or-null.md) | `fun asLongOrNull(): Long?` |
| [asObject](as-object.md) | `fun asObject(): ConfigObject` |
| [asObjectList](as-object-list.md) | `fun asObjectList(): List<ConfigObject>` |
| [asObjectListOrEmpty](as-object-list-or-empty.md) | `fun asObjectListOrEmpty(): List<ConfigObject>` |
| [asObjectListOrNull](as-object-list-or-null.md) | `fun asObjectListOrNull(): List<ConfigObject>?` |
| [asObjectOrNull](as-object-or-null.md) | `fun asObjectOrNull(): ConfigObject?` |
| [asString](as-string.md) | `fun asString(): String`
`fun asString(defaultValue:&nbsp;String): String` |
| [asStringArray](as-string-array.md) | `fun asStringArray(): Array<String>`
`fun asStringArray(defaultValue:&nbsp;Array<String>): Array<String>` |
| [asStringArrayOrEmpty](as-string-array-or-empty.md) | `fun asStringArrayOrEmpty(): Array<String>` |
| [asStringArrayOrNull](as-string-array-or-null.md) | `fun asStringArrayOrNull(): Array<String>?` |
| [asStringList](as-string-list.md) | `fun asStringList(): List<String>`
`fun asStringList(defaultValue:&nbsp;List<String>): List<String>` |
| [asStringListOrEmpty](as-string-list-or-empty.md) | `fun asStringListOrEmpty(): List<String>` |
| [asStringListOrNull](as-string-list-or-null.md) | `fun asStringListOrNull(): List<String>?` |
| [asStringOrNull](as-string-or-null.md) | `fun asStringOrNull(): String?` |
| [exists](exists.md) | `fun exists(): Boolean` |
| [isBlankString](is-blank-string.md) | `fun isBlankString(): Boolean` |
| [isEmptyString](is-empty-string.md) | `fun isEmptyString(): Boolean` |
| [isNotBlankString](is-not-blank-string.md) | `fun isNotBlankString(): Boolean` |
| [isNotEmptyString](is-not-empty-string.md) | `fun isNotEmptyString(): Boolean` |
| [notExists](not-exists.md) | `fun notExists(): Boolean` |
| [parseBytes](parse-bytes.md) | `fun parseBytes(): Long`<p>Parse config string such as 4000 (4000 bytes), 4K, 4M, 4G representing a size in bytes resulting in a Long</p>`fun parseBytes(defaultValue:&nbsp;Long): Long` |
| [parseBytesOrNull](parse-bytes-or-null.md) | `fun parseBytesOrNull(): Long?` |
| [parseDuration](parse-duration.md) | `fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long`<p>Parse config string such as "30s", "30 seconds", "4m" representing a time duration resulting in a Long of desired TimeUnit</p>`fun parseDuration(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue:&nbsp;Long): Long` |
| [parseDurationList](parse-duration-list.md) | `fun parseDurationList(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long>`
`fun parseDurationList(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue:&nbsp;List<Long>): List<Long>` |
| [parseDurationListOrEmpty](parse-duration-list-or-empty.md) | `fun parseDurationListOrEmpty(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long>` |
| [parseDurationListOrNull](parse-duration-list-or-null.md) | `fun parseDurationListOrNull(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long>?` |
| [parseDurationOrNull](parse-duration-or-null.md) | `fun parseDurationOrNull(desiredUnits:&nbsp;[TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long?` |
