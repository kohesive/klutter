package uy.klutter.db.jdbi.v2

import org.skife.jdbi.v2.*
import org.skife.jdbi.v2.sqlobject.*

class KotlinPlugin {
    companion object {
        init {
            SqlObjectBuilder.registerBinderFactory(KotlinBinderFactory())
        }
    }

    fun customizeHandle(handle: Handle): Handle {
        handle.registerMapper(KotlinMapperFactory())
        return handle
    }

    fun customizeDBI(dbi: DBI): DBI {
        dbi.registerMapper(KotlinMapperFactory())
        return dbi
    }
}

fun Handle.attachKotlinPlugin(): Handle {
    return KotlinPlugin().customizeHandle(this)
}

fun DBI.attachKotlinPlugin(): DBI {
    return KotlinPlugin().customizeDBI(this)
}
