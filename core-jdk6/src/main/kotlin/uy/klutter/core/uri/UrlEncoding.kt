package uy.klutter.core.uri


import uy.klutter.core.common.initializedBy
import uy.klutter.core.common.with
import uy.klutter.core.jdk.mustNotEndWith
import uy.klutter.core.jdk.mustNotStartWith
import uy.klutter.core.jdk.mustStartWith
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.BitSet
import kotlin.with

/**
 * Converted to Kotlin from https://github.com/resteasy/Resteasy/blob/master/jaxrs/resteasy-jaxrs/src/main/java/org/jboss/resteasy/util/URLUtils.java
 * Then with extra things added including a ported decoder from same library, query to maps, and more
 *
 * URL-encoding utility for each URL part according to the RFC specs
 *
 * based on work by stephane@epardaud.fr
 *
 * @see http://www.ietf.org/rfc/rfc3986.txt
 */
public object UrlEncoding {
    private fun BitSet.set(c: Char) = set(c.toInt())
    private fun BitSet.clear(c: Char) = clear(c.toInt())

    private fun bitsetFrom(vararg chars: Char): BitSet {
        val temp = BitSet()
        chars.forEach { temp.set(it) }
        return temp
    }

    private fun bitsetFrom(chars: CharRange): BitSet {
        val temp = BitSet()
        chars.forEach { temp.set(it) }
        return temp
    }

    private fun bitsetFrom(vararg bitsets: BitSet): BitSet {
        val temp = BitSet()
        bitsets.forEach { temp.or(it) }
        return temp
    }

    private operator  fun BitSet.plus(other: BitSet): BitSet {
        val temp = BitSet()
        temp.or(this)
        temp.or(other)
        return temp
    }


    /**
     * gen-delims = ":" / "/" / "?" / "#" / "[" / "]" / "@"
     */
    private val GEN_DELIMS = bitsetFrom(':', '/', '?', '#', '[', ']', '@')

    /**
     * sub-delims = "!" / "$" / "&" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
     */
    private val SUB_DELIMS = bitsetFrom('!', '$', '&', '\'', '(', ')', '*', '+', ',', ';', '=')

    /**
     * reserved = gen-delims | sub-delims
     */
    private val RESERVED = GEN_DELIMS + SUB_DELIMS

    /**
     * lowalpha = "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" |
     * "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z"
     */
    private val LOW_ALPHA = bitsetFrom('a'..'z')

    /**
     * upalpha = "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" |
     * "R" | "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z"
     */
    private val UP_ALPHA = bitsetFrom('A'..'Z')

    /**
     * alpha = lowalpha | upalpha
     */
    private val ALPHA = LOW_ALPHA + UP_ALPHA

    /**
     * digit = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
     */
    private val DIGIT = bitsetFrom('0'..'9')

    /**
     * alphanum = alpha | digit
     */
    private val ALPHANUM = ALPHA + DIGIT

    /**
     * unreserved = ALPHA / DIGIT / "-" / "." / "_" / "~"
     */
    private val UNRESERVED = (ALPHA + DIGIT) with {
        set('-')
        set('.')
        set('_')
        set('~')
    }

    /**
     * pchar = unreserved | escaped | sub-delims | ":" | "@"
     *
     *
     * Note: we don't allow escaped here since we will escape it ourselves, so we don't want to allow them in the
     * unescaped sequences
     */
    private val PCHAR = (UNRESERVED + SUB_DELIMS) with {
        set(':')
        set('@')
    }

    /**
     * user_info = unreserved | escaped | sub-delims | ":"
     *
     *
     * Note: we don't allow escaped here since we will escape it ourselves, so we don't want to allow them in the
     * unescaped sequences
     */
    private val USER_INFO = (UNRESERVED + SUB_DELIMS) with {
        set(':')
    }


    /**
     * path_segment = pchar  ";"
     */
    private val PATH_SEGMENT = BitSet() initializedBy { newSet ->
        newSet.or(PCHAR)
        // deviate from the RFC in order to disallow the path param separator
        newSet.clear(';')
    }

    /**
     * path_param_name = pchar  ";" | "="
     */
    private val PATH_PARAM_NAME = BitSet() initializedBy { newSet ->
        newSet.or(PCHAR)
        // deviate from the RFC in order to disallow the path param separators
        newSet.clear(';')
        newSet.clear('=')
    }

    /**
     * path_param_value = pchar  ";"
     */
    private val PATH_PARAM_VALUE = BitSet() initializedBy { newSet ->
        newSet.or(PCHAR)
        // deviate from the RFC in order to disallow the path param separator
        newSet.clear(';')
    }

    /**
     * query = pchar / "/" / "?"
     */
    private val QUERY = BitSet() initializedBy { newSet ->
        newSet.or(PCHAR)
        newSet.set('/')
        newSet.set('?')
        // deviate from the RFC to disallow separators such as "=", "@" and the famous "+" which is treated as a space
        // when decoding
        newSet.clear('=')
        newSet.clear('&')
        newSet.clear('+')
    }

    /**
     * fragment = pchar / "/" / "?"
     */
    private val FRAGMENT = BitSet() initializedBy { newSet ->
        newSet.or(PCHAR)
        newSet.set('/')
        newSet.set('?')
    }

    /**
     * Encodes the fragment part of a URI, it can contain PCHAR* with '/' and '?'
     */
    public fun encodeFragmentString(fragment: String): String {
        try {
            return encodePart(fragment, Charsets.UTF_8, FRAGMENT)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes the fragment part of a URI, it can contain PCHAR* with '/' and '?'
     */
    public fun encodeUserInfo(userInfo: String): String {
        try {
            return encodePart(userInfo, Charsets.UTF_8, USER_INFO)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }


    /**
     * Encodes a string to be a valid path parameter name, which means it can contain PCHAR* without "=" or ";". Uses
     * UTF-8.
     */
    public fun encodePathParamName(pathParamName: String): String {
        try {
            return encodePart(pathParamName, Charsets.UTF_8, PATH_PARAM_NAME)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes a string to be a valid path parameter value, which means it can contain PCHAR* without ";". Uses UTF-8.
     */
    public fun encodePathParamValue(pathParamValue: String): String {
        try {
            return encodePart(pathParamValue, Charsets.UTF_8, PATH_PARAM_VALUE)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes a string to be a valid query, which means it can contain PCHAR* | "?" | "/" without "=" | "&" | "+". Uses
     * UTF-8.
     */
    public fun encodeQueryNameOrValue(queryNameOrValue: String): String {
        try {
            return encodePart(queryNameOrValue, Charsets.UTF_8, QUERY)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes a string to be a valid query with no parenthesis, which means it can contain PCHAR* | "?" | "/" without
     * "=" | "&" | "+" | "(" | ")". It strips parenthesis. Uses UTF-8.
     */
    public fun encodeQueryNameOrValueNoParen(queryNameOrValueNoParen: String): String {
        try {
            var query: String = encodePart(queryNameOrValueNoParen, Charsets.UTF_8, QUERY)
            query = query.replace("(", "")
            return query.replace(")", "")
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes a string to be a valid path segment, which means it can contain PCHAR* only (do not put path parameters or
     * they will be escaped. Uses UTF-8.
     */
    public fun encodePathSegment(pathSegment: String): String {
        try {
            return encodePart(pathSegment, Charsets.UTF_8, PATH_SEGMENT)
        } catch (e: UnsupportedEncodingException) {
            // should not happen
            throw RuntimeException(e)
        }

    }

    /**
     * Encodes a string to be a valid URI part, with the given characters allowed. The rest will be encoded.
     */
    private fun encodePart(part: String, charset: Charset, allowed: BitSet): String {
        // start at *3 for the worst case when everything is %encoded on one byte
        val encoded = StringBuffer(part.length() * 3)
        val toEncode = part.toCharArray()
        for (c in toEncode) {
            if (allowed.get(c.toInt())) {
                encoded.append(c)
            } else {
                val bytes = c.toString().toByteArray(charset)
                for (b in bytes) {
                    // make it unsigned
                    val u8 = b.toInt() and 255
                    encoded.append(java.lang.String.format("%%%1$02X", u8))
                }
            }
        }
        return encoded.toString()
    }

    /**
     * Decodes URL encoded string including newly introduced JavaScript encoding with %uxxxx chars
     */
    public fun decode(s: String, charset: Charset = Charsets.UTF_8): String {
        var decoded = false
        val l = s.length()
        val sb = StringBuffer(if (l > 1024) l / 3 else l)

        var state: ParseState = ParseState.sText
        var i = 0
        var code = 0
        var c: Char
        var pos = 0
        var ofs = 0
        var buf: ByteArray? = null
        var processDig = false
        while (i < l) {
            c = s.charAt(i)
            when (c) {
                '+' -> {
                    decoded = true
                    when (state) {
                        ParseState.sText -> {
                            sb.append(' ')
                        }
                        ParseState.s2Dig -> {
                            sb.append(String(buf!!, 0, pos + 1, charset))
                            state = ParseState.sText
                            sb.append(' ')
                        } else -> throw IllegalArgumentException("decode: unexpected + at pos: " + i + ", of : " + s)
                    }
                }
                in '0'..'9' -> {
                    ofs = '0'.toInt()
                    processDig = true
                }
                in 'a'..'f' -> {
                    ofs = 'a'.toInt() - 10
                    processDig = true
                }
                in 'A'..'F' -> {
                    ofs = 'A'.toInt() - 10
                    processDig = true
                }
                '%' -> {
                    decoded = true
                    when (state) {
                        ParseState.sText -> {
                            state = ParseState.sEscape
                            if (buf == null)
                                buf = ByteArray((l - i) / 3)
                            pos = 0
                        }
                        ParseState.s2Dig -> {
                            state = ParseState.sEscape
                            pos++
                        } else -> throw IllegalArgumentException("decode: unexpected escape % at pos: " + i + ", of : " + s)
                    }
                }
                'u' -> when (state) {
                    ParseState.sEscape -> {
                        if (pos > 0) {
                            sb.append(String(buf!!, 0, pos, charset))
                            pos = 0
                        }
                        state = ParseState.sU1
                    }
                    ParseState.sText -> {
                        sb.append(c)
                    }
                    ParseState.s2Dig -> {
                        sb.append(String(buf!!, 0, pos + 1, charset))
                        state = ParseState.sText
                        sb.append(c)
                    } else ->  throw IllegalArgumentException("decode: unexpected char in hex at pos: " + i + ", of : " + s)
                }
                else -> when (state) {
                    ParseState.sText -> {
                        sb.append(c)
                    }
                    ParseState.s2Dig -> {
                        sb.append(String(buf!!, 0, pos + 1, charset))
                        state = ParseState.sText
                        sb.append(c)
                    } else -> throw IllegalArgumentException("decode: unexpected char in hex at pos: " + i + ", of : " + s)
                }
            }
            i++
            if (processDig) {
                when (state) {
                    ParseState.sEscape -> {
                        code = c.toInt() - ofs
                        state = ParseState.s1Dig
                    }
                    ParseState.s1Dig -> {
                        buf!![pos] = (code * 16 + (c.toInt() - ofs)).toByte()
                        state = ParseState.s2Dig
                    }
                    ParseState.s2Dig -> {
                        // escape finished
                        sb.append(String(buf!!, 0, pos + 1, charset))
                        state = ParseState.sText
                        sb.append(c)
                    }
                    ParseState.sU1 -> {
                        code = c.toInt() - ofs
                        state = ParseState.sU2
                    }
                    ParseState.sU2 -> {
                        code = code * 16 + c.toInt() - ofs
                        state = ParseState.sU3
                    }
                    ParseState.sU3 -> {
                        code = code * 16 + c.toInt() - ofs
                        state = ParseState.sU4
                    }
                    ParseState.sU4 -> {
                        sb.append((code * 16 + c.toInt() - ofs).toChar())
                        state = ParseState.sText
                    }
                    else -> {
                        sb.append(c)
                    }
                }
                processDig = false
            }
        }
        if (state == ParseState.s2Dig) {
            sb.append(String(buf!!, 0, pos + 1, charset))
        }
        return if (decoded) sb.toString() else s
    }

    private enum class ParseState { sText, s1Dig, s2Dig, sEscape, sU1, sU2, sU3, sU4 }

    public fun decodePathToSegments(encodedPath: String): List<String> = encodedPath.mustNotStartWith('/').mustNotEndWith('/').split('/').map { UrlEncoding.decode(it) }
    public fun encodePathStringFromSegments(decodedPath: List<String>): String {
        return if (decodedPath.isEmpty()) ""
        else decodedPath.map { UrlEncoding.encodePathSegment(it) }.joinToString("/").mustStartWith('/')
    }


    public fun decodeQueryStringToMultiMap(encodedQuery: String): Map<String, List<String>> {
        return encodedQuery
                .splitToSequence('&')
                .filter { it.isNotBlank() }
                .map {
                    val parts = it.splitToSequence('=', limit = 2)
                    Pair(UrlEncoding.decode(parts.first()).trim(), UrlEncoding.decode(parts.drop(1).firstOrNull() ?: "").trim())
                }
                .filter { it.first.isNotEmpty() }
                .groupBy { it.first }
                .map { groupPair -> groupPair.getKey() to groupPair.getValue().map { it.second } }
                .toMap()
    }
    public fun decodeQueryToMap(encodedQuery: String): Map<String, String> {
        return encodedQuery
                .splitToSequence('&')
                .filter { it.isNotBlank() }
                .map {
                    val parts = it.splitToSequence('=', limit = 2)
                    Pair(UrlEncoding.decode(parts.first()).trim(), UrlEncoding.decode(parts.drop(1).firstOrNull() ?: "").trim())
                }
                .filter { it.first.isNotEmpty() }
                .toList() // until M13
                .toMap()
    }
    public fun dedupeQueryFromMultiMapToMap(decodedQuery: Map<String, List<String>>): Map<String, String> {
        return decodedQuery
                .asSequence()
                .filter { it.getKey().isNotBlank() }
                .flatMap { pair -> pair.getValue().asSequence().map { pair.getKey() to it } }
                .toList() // until M13
                .toMap()
    }
    public fun encodeQueryMultiMapToString(decodedQuery: Map<String, List<String>>): String {
        return decodedQuery
                .asSequence()
                .filter { it.getKey().isNotBlank() }
                .flatMap { pair -> pair.getValue().asSequence().map { pair.getKey() to it } }
                .map { UrlEncoding.encodeQueryNameOrValueNoParen(it.first.trim()) + "=" +  UrlEncoding.encodeQueryNameOrValue(it.second.trim()) }
                .joinToString("&")
    }
    public fun encodeQueryMapToString(decodedQuery: Map<String, String>): String {
        return decodedQuery
                .asSequence()
                .filter { it.getKey().isNotBlank() }
                .map { UrlEncoding.encodeQueryNameOrValueNoParen(it.getKey().trim()) + "=" +  UrlEncoding.encodeQueryNameOrValue(it.getValue().trim()) }
                .joinToString("&")
    }
}
