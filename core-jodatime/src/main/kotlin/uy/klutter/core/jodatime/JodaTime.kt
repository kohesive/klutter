package uy.klutter.core.jodatime

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

/**
 * Current time UTC
 *
 * @return now as DateTime in timezone UTC
 */
fun utcNow(): DateTime = DateTime.now(DateTimeZone.UTC)

/**
 * Make a date formatter for ISO Date Time 'yyyy-MM-dd`T`hh:mm:ss.SSSZ'
 *
 * @return DateTimeFormatter configured for ISO Date Time format
 */
fun isoDateFormat(): DateTimeFormatter = ISODateTimeFormat.dateTime()

/**
 * Convert the date into a String in the ISO Date Time format 'yyyy-MM-dd`T`hh:mm:ss.SSSZ'
 *
 * @return String representing the DateTime in ISO Date Time format
 */
fun DateTime.toIsoString(): String = isoDateFormat().print(this)

