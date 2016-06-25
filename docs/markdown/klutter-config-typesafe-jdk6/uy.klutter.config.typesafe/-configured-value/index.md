[uy.klutter.config.typesafe](../index.md) / [ConfiguredValue](.)


# ConfiguredValue
<code>class ConfiguredValue</code> [(source)](https://github.com/kohesive/klutter/blob/master/config-typesafe-jdk6/src/main/kotlin/uy/klutter/config/typesafe/TypesafeConfig_Ext.kt#L38)<br/>
Intermediate object for providing additional functionality on a configured item



### Constructors

|&nbsp;|&nbsp;|
|---|---|
| [&lt;init&gt;](-init-.md) | <code>ConfiguredValue(cfg: Config, key: String)</code><br/>Intermediate object for providing additional functionality on a configured item |

### Properties

|&nbsp;|&nbsp;|
|---|---|
| [cfg](cfg.md) | <code>val cfg: Config</code><br/> |
| [key](key.md) | <code>val key: String</code><br/> |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [asBoolean](as-boolean.md) | <code>fun asBoolean(): Boolean</code><br/><code>fun asBoolean(defaultValue: Boolean): Boolean</code><br/> |
| [asBooleanOrNull](as-boolean-or-null.md) | <code>fun asBooleanOrNull(): Boolean?</code><br/> |
| [asBytesList](as-bytes-list.md) | <code>fun asBytesList(): List<Long></code><br/><code>fun asBytesList(defaultValue: List<Long>): List<Long></code><br/> |
| [asBytesListOrEmpty](as-bytes-list-or-empty.md) | <code>fun asBytesListOrEmpty(): List<Long></code><br/> |
| [asBytesListOrNull](as-bytes-list-or-null.md) | <code>fun asBytesListOrNull(): List<Long>?</code><br/> |
| [asDouble](as-double.md) | <code>fun asDouble(): Double</code><br/><code>fun asDouble(defaultValue: Double): Double</code><br/> |
| [asDoubleList](as-double-list.md) | <code>fun asDoubleList(): List<Double></code><br/><code>fun asDoubleList(defaultValue: List<Double>): List<Double></code><br/> |
| [asDoubleListOrEmpty](as-double-list-or-empty.md) | <code>fun asDoubleListOrEmpty(): List<Double></code><br/> |
| [asDoubleListOrNull](as-double-list-or-null.md) | <code>fun asDoubleListOrNull(): List<Double>?</code><br/> |
| [asDoubleOrNull](as-double-or-null.md) | <code>fun asDoubleOrNull(): Double?</code><br/> |
| [asFile](as-file.md) | <code>fun asFile(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)</code><br/> |
| [asFileOrNull](as-file-or-null.md) | <code>fun asFileOrNull(): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?</code><br/> |
| [asFileRelative](as-file-relative.md) | <code>fun asFileRelative(relativeTo: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)</code><br/> |
| [asFileRelativeOrNull](as-file-relative-or-null.md) | <code>fun asFileRelativeOrNull(relativeTo: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?</code><br/> |
| [asFileSibling](as-file-sibling.md) | <code>fun asFileSibling(relativeTo: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)</code><br/> |
| [asFileSiblingOrNull](as-file-sibling-or-null.md) | <code>fun asFileSiblingOrNull(relativeTo: [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)): [File](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)?</code><br/> |
| [asInt](as-int.md) | <code>fun asInt(): Int</code><br/><code>fun asInt(defaultValue: Int): Int</code><br/> |
| [asIntArray](as-int-array.md) | <code>fun asIntArray(): Array<Int></code><br/><code>fun asIntArray(defaultValue: Array<Int>): Array<Int></code><br/> |
| [asIntArrayOrEmpty](as-int-array-or-empty.md) | <code>fun asIntArrayOrEmpty(): Array<Int></code><br/> |
| [asIntArrayOrNull](as-int-array-or-null.md) | <code>fun asIntArrayOrNull(): Array<Int>?</code><br/> |
| [asIntList](as-int-list.md) | <code>fun asIntList(): List<Int></code><br/><code>fun asIntList(defaultValue: List<Int>): List<Int></code><br/> |
| [asIntListOrEmpty](as-int-list-or-empty.md) | <code>fun asIntListOrEmpty(): List<Int></code><br/> |
| [asIntListOrNull](as-int-list-or-null.md) | <code>fun asIntListOrNull(): List<Int>?</code><br/> |
| [asIntOrNull](as-int-or-null.md) | <code>fun asIntOrNull(): Int?</code><br/> |
| [asLong](as-long.md) | <code>fun asLong(): Long</code><br/><code>fun asLong(defaultValue: Long): Long</code><br/> |
| [asLongList](as-long-list.md) | <code>fun asLongList(): List<Long></code><br/><code>fun asLongList(defaultValue: List<Long>): List<Long></code><br/> |
| [asLongListOrEmpty](as-long-list-or-empty.md) | <code>fun asLongListOrEmpty(): List<Long></code><br/> |
| [asLongListOrNull](as-long-list-or-null.md) | <code>fun asLongListOrNull(): List<Long>?</code><br/> |
| [asLongOrNull](as-long-or-null.md) | <code>fun asLongOrNull(): Long?</code><br/> |
| [asObject](as-object.md) | <code>fun asObject(): ConfigObject</code><br/> |
| [asObjectList](as-object-list.md) | <code>fun asObjectList(): List<ConfigObject></code><br/> |
| [asObjectListOrEmpty](as-object-list-or-empty.md) | <code>fun asObjectListOrEmpty(): List<ConfigObject></code><br/> |
| [asObjectListOrNull](as-object-list-or-null.md) | <code>fun asObjectListOrNull(): List<ConfigObject>?</code><br/> |
| [asObjectOrNull](as-object-or-null.md) | <code>fun asObjectOrNull(): ConfigObject?</code><br/> |
| [asString](as-string.md) | <code>fun asString(): String</code><br/><code>fun asString(defaultValue: String): String</code><br/> |
| [asStringArray](as-string-array.md) | <code>fun asStringArray(): Array<String></code><br/><code>fun asStringArray(defaultValue: Array<String>): Array<String></code><br/> |
| [asStringArrayOrEmpty](as-string-array-or-empty.md) | <code>fun asStringArrayOrEmpty(): Array<String></code><br/> |
| [asStringArrayOrNull](as-string-array-or-null.md) | <code>fun asStringArrayOrNull(): Array<String>?</code><br/> |
| [asStringList](as-string-list.md) | <code>fun asStringList(): List<String></code><br/><code>fun asStringList(defaultValue: List<String>): List<String></code><br/> |
| [asStringListOrEmpty](as-string-list-or-empty.md) | <code>fun asStringListOrEmpty(): List<String></code><br/> |
| [asStringListOrNull](as-string-list-or-null.md) | <code>fun asStringListOrNull(): List<String>?</code><br/> |
| [asStringOrNull](as-string-or-null.md) | <code>fun asStringOrNull(): String?</code><br/> |
| [exists](exists.md) | <code>fun exists(): Boolean</code><br/> |
| [isBlankString](is-blank-string.md) | <code>fun isBlankString(): Boolean</code><br/> |
| [isEmptyString](is-empty-string.md) | <code>fun isEmptyString(): Boolean</code><br/> |
| [isNotBlankString](is-not-blank-string.md) | <code>fun isNotBlankString(): Boolean</code><br/> |
| [isNotEmptyString](is-not-empty-string.md) | <code>fun isNotEmptyString(): Boolean</code><br/> |
| [notExists](not-exists.md) | <code>fun notExists(): Boolean</code><br/> |
| [parseBytes](parse-bytes.md) | <code>fun parseBytes(): Long</code><br/>Parse config string such as 4000 (4000 bytes), 4K, 4M, 4G representing a size in bytes resulting in a Long<code>fun parseBytes(defaultValue: Long): Long</code><br/> |
| [parseBytesOrNull](parse-bytes-or-null.md) | <code>fun parseBytesOrNull(): Long?</code><br/> |
| [parseDuration](parse-duration.md) | <code>fun parseDuration(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long</code><br/>Parse config string such as "30s", "30 seconds", "4m" representing a time duration resulting in a Long of desired TimeUnit<code>fun parseDuration(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue: Long): Long</code><br/> |
| [parseDurationList](parse-duration-list.md) | <code>fun parseDurationList(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long></code><br/><code>fun parseDurationList(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html), defaultValue: List<Long>): List<Long></code><br/> |
| [parseDurationListOrEmpty](parse-duration-list-or-empty.md) | <code>fun parseDurationListOrEmpty(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long></code><br/> |
| [parseDurationListOrNull](parse-duration-list-or-null.md) | <code>fun parseDurationListOrNull(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): List<Long>?</code><br/> |
| [parseDurationOrNull](parse-duration-or-null.md) | <code>fun parseDurationOrNull(desiredUnits: [TimeUnit](http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/TimeUnit.html)): Long?</code><br/> |
