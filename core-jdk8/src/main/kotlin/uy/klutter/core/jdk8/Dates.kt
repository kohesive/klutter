package uy.klutter.core.jdk8

import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal

/**
 * Current time UTC
 *
 * @return now as Instant in timezone UTC
 */
public fun utcNow(): Instant = Instant.now()

/**
 * Make a date formatter for ISO Date Time 'yyyy-MM-dd`T`hh:mm:ss.SSSZ'
 *
 * @return DateTimeFormatter configured for ISO Date Time format
 */
public fun isoDateFormat(): DateTimeFormatter = DateTimeFormatter.ISO_INSTANT

/**
 * Convert the Instant into a String in the ISO Date Time format 'yyyy-MM-dd`T`hh:mm:ss.SSSZ'
 *
 * @return String representing the Instant in ISO Date Time format
 */
public fun Temporal.toIsoString(): String = isoDateFormat().format(this)

