package uy.klutter.db.jdbi.v3

import org.jdbi.v3.DBI
import org.jdbi.v3.Handle
import org.jdbi.v3.spi.JdbiPlugin
import org.jdbi.v3.sqlobject.SqlObjectBuilder

class KotlinPlugin : JdbiPlugin {
    companion object {
        init {
            SqlObjectBuilder.registerBinderFactory(KotlinBinderFactory())
        }
    }

    override fun customizeHandle(handle: Handle): Handle {
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
