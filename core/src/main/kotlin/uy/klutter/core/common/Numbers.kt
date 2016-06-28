package uy.klutter.core.common


import java.text.DecimalFormat

fun Int.minimum(minVal: Int): Int = Math.max(this, minVal)
fun Int.maximum(maxVal: Int): Int = Math.min(this, maxVal)
fun Int.coerce(minVal: Int, maxVal: Int): Int = this.minimum(minVal).maximum(maxVal)
fun Int.coerce(range: IntRange): Int = this.minimum(range.start).maximum(range.endInclusive)

fun Long.minimum(minVal: Long): Long = Math.max(this, minVal)
fun Long.maximum(maxVal: Long): Long = Math.min(this, maxVal)
fun Long.coerce(minVal: Long, maxVal: Long): Long = this.minimum(minVal).maximum(maxVal)
fun Long.coerce(range: LongRange): Long = this.minimum(range.start).maximum(range.endInclusive)

fun Byte.minimum(minVal: Byte): Byte = if (this < minVal) minVal else this
fun Byte.maximum(maxVal: Byte): Byte = if (this > maxVal) maxVal else this
fun Byte.coerce(minVal: Byte, maxVal: Byte): Byte = this.minimum(minVal).maximum(maxVal)
fun Byte.coerce(range: IntRange): Byte = this.minimum(range.start.toByte()).maximum(range.endInclusive.toByte())

fun Short.minimum(minVal: Short): Short = if (this < minVal) minVal else this
fun Short.maximum(maxVal: Short): Short = if (this > maxVal) maxVal else this
fun Short.coerce(minVal: Short, maxVal: Short): Short = this.minimum(minVal).maximum(maxVal)
fun Short.coerce(range: IntRange): Short = this.minimum(range.start.toShort()).maximum(range.endInclusive.toShort())

fun Double.minimum(minVal: Double): Double = Math.max(this, minVal)
fun Double.maximum(maxVal: Double): Double = Math.min(this, maxVal)
fun Double.coerce(minVal: Double, maxVal: Double): Double = this.minimum(minVal).maximum(maxVal)

fun Long.humanReadable(): String {
    if (this <= 0) return "0"
    val units = arrayOf("B", "KB", "MB", "GB", "TB", "EB")
    val digitGroups = (Math.log10(this.toDouble())/Math.log10(1024.0)).toInt();
    return DecimalFormat("#,##0.#").format(this/Math.pow(1024.0, digitGroups.toDouble())) + " " + units[digitGroups];
}

fun Int.humanReadable(): String {
    return this.toLong().humanReadable()
}

