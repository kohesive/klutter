package uy.klutter.core.uri


import uy.klutter.core.jdk.mustStartWith
import java.net.URI
import java.net.URL
import java.net.URLEncoder
import java.util.*

public fun buildUri(uri: URI): UriBuilder {
    return UriBuilder(scheme = uri.getScheme(), encodedUserInfo = uri.getRawUserInfo(), host = uri.getHost(), port = if (uri.getPort() < 0) null else uri.getPort(),
            encodedPath = uri.getRawPath(), encodedQuery = uri.getRawQuery(), encodedFragment = uri.getRawFragment())
}

public fun buildUri(uriString: String): UriBuilder {
    return buildUri(URI(uriString))
}

public fun buildUri(url: URL): UriBuilder {
    return buildUri(url.toURI())
}

public fun buildUri(uri: ImmutableUri): UriBuilder {
    return UriBuilder(scheme = uri.scheme, encodedUserInfo = uri.encodedUserInfo, host = uri.host, port = uri.port,
            encodedPath = uri.encodedPath, encodedQuery = uri.encodedQuery, encodedFragment = uri.encodedFragment)
}

public interface ImmutableUri {
    val scheme: String?
    val encodedUserInfo: String?
    val decodedUserInfo: String?
    val host: String?
    val port: Int?
    val encodedPath: String?
    val encodedQuery: String?
    val encodedFragment: String?
    val decodedFragment: String?
    val decodedPath: List<String>?
    val decodedQuery: Map<String, List<String>>?
    val decodedQueryDeduped: Map<String, String>?

    fun hasScheme(): Boolean = scheme?.isNotBlank() ?: false
    fun hasPath(): Boolean = encodedPath?.isNotBlank() ?: false
    fun hasQuery(): Boolean = encodedQuery?.isNotBlank() ?: false
    fun hasFragment(): Boolean = encodedFragment?.isNotBlank() ?: false
    fun hasHost(): Boolean = host?.isNotBlank() ?: false
    fun hasPort(): Boolean = (port != null) && (port!! > 0)
    fun hasUserInfo(): Boolean = encodedUserInfo?.isNotBlank() ?: false

    fun toURI(): URI {
        return URI(asString())
    }

    fun asString(): String {
        val sb = StringBuffer()
        if (hasScheme()) sb.append("$scheme:")
        if (hasUserInfo() || hasHost() || hasPort()) sb.append("//")
        if (hasUserInfo()) sb.append("$encodedUserInfo@")
        if (hasHost()) sb.append("$host")
        if (hasPort()) sb.append(":$port")
        if (hasPath()) sb.append("$encodedPath")
        if (hasQuery()) sb.append("?$encodedQuery")
        if (hasFragment()) sb.append("#$encodedFragment")
        return sb.toString()
    }

    fun fragmentAsDecodedPath(): List<String>? = if (encodedFragment == null) null else UrlEncoding.decodePathToSegments(encodedFragment!!)
    fun fragmentAsDecodedQuery(): Map<String, List<String>>? = if (encodedFragment == null) null else UrlEncoding.decodeQueryStringToMultiMap(encodedFragment!!)
    fun fragmentAsDecodedQueryDeduped(): Map<String, String>? = if (encodedFragment == null) null else UrlEncoding.decodeQueryToMap(encodedFragment!!)
}

public class UriBuilder(scheme: String? = null, encodedUserInfo: String? = null, host: String? = null, port: Int? = null, encodedPath: String? = null, encodedQuery: String? = null, encodedFragment: String? = null): ImmutableUri {
    override var scheme: String? = scheme
    override var host: String? = host
    override var port: Int? = port

    private fun String?.urlDecode(): String? = if (this == null) null else UrlEncoding.decode(this)

    private var _encodedUserInfo: String? = encodedUserInfo
    private var _decodedUserInfo: String? = encodedUserInfo.urlDecode()

    private var _encodedPath = encodedPath
    private var _decodedPath = if (encodedPath == null) null else UrlEncoding.decodePathToSegments(encodedPath)

    private var _encodedQuery = encodedQuery
    private var _decodedQuery = if (encodedQuery == null) null else UrlEncoding.decodeQueryStringToMultiMap(encodedQuery)
    private var _decodedQueryDeduped = if (encodedQuery == null) null else UrlEncoding.decodeQueryToMap(encodedQuery)

    private var _encodedFragment = encodedFragment
    private var _decodedFragment = encodedFragment.urlDecode()

    override var encodedFragment: String?
        set(value) {
            _encodedFragment = value
            _decodedFragment = value.urlDecode()
        }
        get() = _encodedFragment

    override var decodedFragment: String?
        set(value) {
            _encodedFragment = if (value == null) null else UrlEncoding.encodeFragmentString(value)
            _decodedFragment = value
        }
        get() = _decodedFragment

    override var encodedUserInfo: String?
        set(value) {
            _encodedUserInfo = value
            _decodedUserInfo = value.urlDecode()
        }
        get() = _encodedUserInfo

    override var decodedUserInfo: String?
        set(value) {
            _encodedUserInfo = if (value == null) null else UrlEncoding.encodeUserInfo(value)
            _decodedUserInfo = value
        }
        get() = _decodedUserInfo

    override var encodedPath: String?
        set(value) {
            _encodedPath = value?.mustStartWith('/')
            _decodedPath = if (value == null) null else UrlEncoding.decodePathToSegments(value)
        }
        get() = _encodedPath

    override var decodedPath: List<String>?
        set(value) {
            _decodedPath = value
            _encodedPath = if (value == null) null else UrlEncoding.encodePathStringFromSegments(value)
        }
        get() = _decodedPath

    override var encodedQuery: String?
        set(value) {
            _encodedQuery = value
            _decodedQuery = if (value == null) null else UrlEncoding.decodeQueryStringToMultiMap(value)
            _decodedQueryDeduped = if (_decodedQuery == null) null else UrlEncoding.dedupeQueryFromMultiMapToMap(_decodedQuery!!)
        }
        get() = _encodedQuery

    override var decodedQuery: Map<String, List<String>>?
        set(value) {
            _encodedQuery = if (value == null) null else UrlEncoding.encodeQueryMultiMapToString(value)
            _decodedQuery = value
            _decodedQueryDeduped = if (value == null) null else UrlEncoding.dedupeQueryFromMultiMapToMap(value)
        }
        get() = _decodedQuery

    override var decodedQueryDeduped: Map<String, String>?
        set(value) {
            _encodedQuery = if (value == null) null else UrlEncoding.encodeQueryMapToString(value)
            _decodedQuery = value?.map { it.key to listOf(it.value) }?.toMap()
            _decodedQueryDeduped = value
        }
        get() = _decodedQueryDeduped

    fun scheme(newScheme: String?): UriBuilder {
        scheme = newScheme
        return this
    }

    fun encodedUserInfo(newUserInfo: String?): UriBuilder {
        encodedUserInfo = newUserInfo
        return this
    }

    fun decodedUserInfo(newUserInfo: String?): UriBuilder {
        decodedUserInfo = newUserInfo
        return this
    }

    fun host(newHost: String?): UriBuilder {
        host = newHost
        return this
    }

    fun port(newPort: Int?): UriBuilder {
        port = newPort
        return this
    }

    fun clearQuery(): UriBuilder {
        encodedQuery = null
        return this
    }

    fun clearQueryExcept(vararg keepParm: String): UriBuilder {
        return clearQueryExcept(keepParm.toSet())
    }

    fun clearQueryExcept(keepParms: Collection<String>): UriBuilder {
        val existing = decodedQuery
        val tempMap: HashMap<String, List<String>> = if (existing is LinkedHashMap) existing else LinkedHashMap(existing ?: mapOf())
        val killParms = tempMap.keys - keepParms
        for (param in killParms) {
            tempMap.remove(param)
        }
        decodedQuery = if (tempMap.isEmpty()) null else tempMap
        return this
    }

    fun clearFragment(): UriBuilder {
        encodedFragment = null
        return this
    }

    fun clearPath(): UriBuilder {
        encodedPath = null
        return this
    }

    fun encodedPath(newPath: String?): UriBuilder {
        encodedPath = newPath
        return this
    }

    fun decodedPath(newPath: List<String>?): UriBuilder {
        decodedPath = newPath
        return this
    }

    fun encodedQuery(newQuery: String?): UriBuilder {
        encodedQuery = newQuery
        return this
    }

    fun encodedFragment(newFragment: String?): UriBuilder {
        encodedFragment = newFragment
        return this
    }

    fun decodedFragment(newFragment: String?): UriBuilder {
        decodedFragment = newFragment
        return this
    }

    fun replaceQueryParams(vararg params: Pair<String, String?>): UriBuilder {
        val existing = decodedQuery
        val tempMap: HashMap<String, List<String>> = if (existing is LinkedHashMap) existing else LinkedHashMap<String, List<String>>(existing ?: mapOf())
        for (param in params) {
            tempMap.put(param.first, listOf(param.second ?: ""))
        }
        decodedQuery = tempMap
        return this
    }

    fun addQueryParams(vararg params: Pair<String, String?>): UriBuilder {
        val existing = decodedQuery
        val tempMap: HashMap<String, List<String>> = if (existing is LinkedHashMap) existing else LinkedHashMap(existing ?: mapOf())
        for (param in params) {
            tempMap.put(param.first, (tempMap.get(param.first) ?: emptyList()) + listOf(param.second ?: ""))
        }
        decodedQuery = tempMap
        return this
    }

    fun removeQueryParams(vararg params: String): UriBuilder {
        val existing = decodedQuery
        val tempMap: HashMap<String, List<String>> = if (existing is LinkedHashMap) existing else LinkedHashMap(existing ?: mapOf())
        for (param in params) {
            tempMap.remove(param)
        }
        decodedQuery = if (tempMap.isEmpty()) null else tempMap
        return this
    }

    fun build(): ImmutableUri {
        // create a version that is a copy, readonly, and a data class so is comparable, hashable, etc.
        return BuiltUri(scheme, encodedUserInfo, decodedUserInfo,
                host, port,
                encodedPath, decodedPath?.toList(),
                encodedQuery,
                if (decodedQuery != null) LinkedHashMap(decodedQuery) else null, // copying the map, but not the list because the builder always makes new lists when adding/removing values
                if (decodedQueryDeduped != null) LinkedHashMap(decodedQueryDeduped) else null,
                encodedFragment, decodedFragment)
    }

    override public fun toString(): String {
        return asString()
    }

    data class BuiltUri(override val scheme: String?, override val encodedUserInfo: String?, override val decodedUserInfo: String?,
                        override val host: String?, override val port: Int?,
                        override val encodedPath: String?, override val decodedPath: List<String>?,
                        override val encodedQuery: String?, override val decodedQuery: Map<String, List<String>>?, override val decodedQueryDeduped: Map<String, String>?,
                        override val encodedFragment: String?, override val decodedFragment: String?) : ImmutableUri  {
       override public fun toString(): String {
            return toURI().toString()
        }
    }

}

