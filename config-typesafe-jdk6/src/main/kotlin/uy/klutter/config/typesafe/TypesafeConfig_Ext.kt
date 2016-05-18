package uy.klutter.config.typesafe

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigObject
import com.typesafe.config.ConfigResolveOptions
import java.io.File
import java.net.URI
import java.util.concurrent.TimeUnit


/**
 * Merge configurations by having the first fallback to the second
 */
fun Config.plus(fallback: Config): Config = this.withFallback(fallback)

/**
 * Return a value from configuration as a more uniform temporary object that can be checked for existance, and has
 * additional methods for retrieving values, including with defaults.
 */
fun Config.value(key: String): ConfiguredValue = ConfiguredValue(this, key)
fun ConfigObject.value(key: String): ConfiguredValue = ConfiguredValue(this.toConfig(), key)

/**
 * Return a nested configuration
 */
fun Config.nested(key: String): Config = this.getConfig(key)
fun ConfigObject.nested(key: String): ConfigObject = this.toConfig().getObject(key)

/**
 * Render the configuration as a string, typically for logging
 */
fun Config.render(): String = this.root().render()

/**
 * Intermediate object for providing additional functionality on a configured item
 */
class ConfiguredValue(val cfg: Config, val key: String) {
    fun asFile(): File = File(asString())
    fun asFileOrNull(): File? = if (exists()) asFile() else null

    fun asFileRelative(relativeTo: File): File = relativeTo.resolve(asString())
    fun asFileRelativeOrNull(relativeTo: File): File? = if (exists()) asFileRelative(relativeTo) else null

    fun asFileSibling(relativeTo: File): File = relativeTo.resolveSibling(asString())
    fun asFileSiblingOrNull(relativeTo: File): File? = if (exists()) asFileSibling(relativeTo) else null

    fun asString(): String = cfg.getString(key).trim()
    fun asString(defaultValue: String): String = if (exists()) cfg.getString(key).trim() else defaultValue
    fun asStringOrNull(): String? = if (exists()) asString() else null

    fun asBoolean(): Boolean = cfg.getBoolean(key)
    fun asBoolean(defaultValue: Boolean): Boolean = if (exists()) cfg.getBoolean(key) else defaultValue
    fun asBooleanOrNull(): Boolean? = if (exists()) asBoolean() else null

    fun asInt(): Int = cfg.getInt(key)
    fun asInt(defaultValue: Int): Int = if (exists()) cfg.getInt(key) else defaultValue
    fun asIntOrNull(): Int? = if (exists()) asInt() else null

    fun asLong(): Long = cfg.getLong(key)
    fun asLong(defaultValue: Long): Long = if (exists()) cfg.getLong(key) else defaultValue
    fun asLongOrNull(): Long? = if (exists()) asLong() else null

    fun asDouble(): Double = cfg.getDouble(key)
    fun asDouble(defaultValue: Double): Double = if (exists()) cfg.getDouble(key) else defaultValue
    fun asDoubleOrNull(): Double? = if (exists()) asDouble() else null

    /**
     * Parse config string such as 4000 (4000 bytes), 4K, 4M, 4G representing a size in bytes resulting in a Long
     */
    fun parseBytes(): Long = cfg.getBytes(key)
    fun parseBytes(defaultValue: Long): Long = if (exists()) cfg.getBytes(key) else defaultValue
    fun parseBytesOrNull(): Long? = if (exists()) asLong() else null

    /**
     * Parse config string such as "30s", "30 seconds", "4m" representing a time duration resulting in a Long of desired TimeUnit
     */
    fun parseDuration(desiredUnits: TimeUnit): Long = cfg.getDuration(key, desiredUnits)
    fun parseDuration(desiredUnits: TimeUnit, defaultValue: Long): Long =  if (exists()) cfg.getDuration(key, desiredUnits) else defaultValue
    fun parseDurationOrNull(desiredUnits: TimeUnit): Long? = if (exists()) parseDuration(desiredUnits) else null

    fun asStringList(): List<String> =  cfg.getStringList(key)
    fun asStringList(defaultValue: List<String>): List<String> = if (exists()) cfg.getStringList(key) else defaultValue
    fun asStringListOrNull(): List<String>? = if (exists()) asStringList() else null
    fun asStringListOrEmpty(): List<String> = asStringList(emptyList())

    fun asIntList(): List<Int> = cfg.getIntList(key)
    fun asIntList(defaultValue: List<Int>): List<Int> = if (exists()) cfg.getIntList(key) else defaultValue
    fun asIntListOrNull(): List<Int>? = if (exists()) asIntList() else null
    fun asIntListOrEmpty(): List<Int> = asIntList(emptyList())

    fun asLongList(): List<Long> = cfg.getLongList(key)
    fun asLongList(defaultValue: List<Long>): List<Long> = if (exists()) cfg.getLongList(key) else defaultValue
    fun asLongListOrNull(): List<Long>? = if (exists()) asLongList() else null
    fun asLongListOrEmpty(): List<Long> = asLongList(emptyList())

    fun asDoubleList(): List<Double> = cfg.getDoubleList(key)
    fun asDoubleList(defaultValue: List<Double>): List<Double> = if (exists()) cfg.getDoubleList(key) else defaultValue
    fun asDoubleListOrNull(): List<Double>? = if (exists()) asDoubleList() else null
    fun asDoubleListOrEmpty(): List<Double> = asDoubleList(emptyList())

    fun asBytesList(): List<Long> = cfg.getBytesList(key)
    fun asBytesList(defaultValue: List<Long>): List<Long> = if (exists()) cfg.getBytesList(key) else defaultValue
    fun asBytesListOrNull(): List<Long>? = if (exists()) asBytesList() else null
    fun asBytesListOrEmpty(): List<Long> = asBytesList(emptyList())

    fun parseDurationList(desiredUnits: TimeUnit): List<Long> = cfg.getDurationList(key, desiredUnits)
    fun parseDurationList(desiredUnits: TimeUnit, defaultValue: List<Long>): List<Long> = if (exists()) cfg.getDurationList(key, desiredUnits) else defaultValue
    fun parseDurationListOrNull(desiredUnits: TimeUnit): List<Long>? = if (exists()) parseDurationList(desiredUnits) else null
    fun parseDurationListOrEmpty(desiredUnits: TimeUnit): List<Long> = parseDurationList(desiredUnits, emptyList())

    fun asStringArray(): Array<String> = cfg.getStringList(key).toTypedArray()
    fun asStringArray(defaultValue: Array<String>): Array<String> = if (exists()) cfg.getStringList(key).toTypedArray() else defaultValue
    fun asStringArrayOrNull(): Array<String>? = if (exists()) asStringArray() else null
    fun asStringArrayOrEmpty(): Array<String> = asStringArray(emptyArray())

    fun asIntArray(): Array<Int> = cfg.getIntList(key).toTypedArray()
    fun asIntArray(defaultValue: Array<Int>): Array<Int> = if (exists()) cfg.getIntList(key).toTypedArray() else defaultValue
    fun asIntArrayOrNull(): Array<Int>? = if (exists()) asIntArray() else null
    fun asIntArrayOrEmpty(): Array<Int> = asIntArray(emptyArray())

    fun isEmptyString(): Boolean = asStringOrNull().isNullOrEmpty()
    fun isNotEmptyString(): Boolean = exists() && asString().isNotEmpty()
    fun isBlankString(): Boolean = asStringOrNull().isNullOrBlank()
    fun isNotBlankString(): Boolean = exists() || asString().isNotBlank()

    fun exists(): Boolean = cfg.hasPath(key)
    fun notExists(): Boolean = !cfg.hasPath(key)

    fun asObject(): ConfigObject = cfg.getObject(key)
    fun asObjectOrNull(): ConfigObject? = if (exists()) cfg.getObject(key) else null

    fun asObjectList(): List<ConfigObject> = cfg.getObjectList(key)
    fun asObjectListOrNull(): List<ConfigObject>? = if (exists()) cfg.getObjectList(key) else null
    fun asObjectListOrEmpty(): List<ConfigObject> = if (exists()) cfg.getObjectList(key) else emptyList()
}
