package uy.klutter.db.jdbi.v3

import org.jdbi.v3.H2DatabaseRule
import org.jdbi.v3.sqlobject.SqlQuery
import org.jdbi.v3.sqlobject.SqlUpdate
import org.junit.Rule
import org.junit.Test
import uy.klutter.db.jdbi.v2.map
import uy.klutter.db.jdbi.v2.open
import uy.klutter.db.jdbi.v2.useSequence
import kotlin.test.assertEquals

class KotlinPluginTest {
    @Rule @JvmField
    val db = H2DatabaseRule().withPlugins()

    data class Thing(val id: Int, val name: String,
                     val nullable: String?,
                     val nullableDefaultedNull: String? = null,
                     val nullableDefaultedNotNull: String? = "not null",
                     val defaulted: String = "default value")

    private interface ThingDao {
        @SqlUpdate("insert into something (id, name) values (:something.id, :something.name)")
        fun insert(something: Thing)

        @SqlQuery("select id, name from something")
        fun list(): List<Thing>

        @SqlQuery("select id, name, null as nullable, null as nullableDefaultedNull, null as nullableDefaultedNotNull, 'test' as defaulted from something")
        fun listWithNulls(): List<Thing>

        @SqlQuery("select id, name from something where id=:id")
        fun findById(id: Int): Thing
    }

    private fun commonTest(dao: ThingDao) {
        val brian = Thing(1, "Brian", null)
        val keith = Thing(2, "Keith", null)

        dao.insert(brian)
        dao.insert(keith)

        val rs = dao.list()

        assertEquals(2, rs.size.toLong())
        assertEquals(brian, rs[0])
        assertEquals(keith, rs[1])

        val foundThing = dao.findById(2)
        assertEquals(keith, foundThing)

        val rs2 = dao.listWithNulls()
        assertEquals(2, rs2.size.toLong())
        assertEquals(brian.copy(nullable = null, nullableDefaultedNull = null, nullableDefaultedNotNull = null, defaulted = "test"), rs2[0])
        assertEquals(keith.copy(nullable = null, nullableDefaultedNull = null, nullableDefaultedNotNull = null, defaulted = "test"), rs2[1])
    }

    @Test
    fun testDaoAttachSqlObject() {
        commonTest(attachSqlObject(db.sharedHandle, ThingDao::class))
    }

    @Test
    fun testDaoCanAttachViaDbiOpen() {
        commonTest(db.dbi.open(ThingDao::class))
    }

    @Test
    fun testDaoCanAttachViaDbiOnDemand() {
        commonTest(db.dbi.onDemand(ThingDao::class))
    }

    @Test
    fun testDaoCanAttachViaHandleAttach() {
        commonTest(db.sharedHandle.attach(ThingDao::class))
    }

    @Test
    fun testFluentQuery() {
        val brian = Thing(1, "Brian", null)
        val keith = Thing(2, "Keith", null)

        val dao = db.dbi.open(ThingDao::class)
        dao.insert(brian)
        dao.insert(keith)

        val qry = db.sharedHandle.createQuery("select id, name from something where id = :id")
        val things: List<Thing> = qry.bind("id", brian.id).map(Thing::class).list()
        assertEquals(1, things.size)
        assertEquals(brian, things[0])

        val qryAll = db.sharedHandle.createQuery("select id, name from something")
        qryAll.map(Thing::class).useSequence {
            assertEquals(keith, it.drop(1).first())
        }

        val qryAll2 = db.sharedHandle.createQuery("select id, name from something")
        qryAll2.useSequence(Thing::class) {
            assertEquals(keith, it.drop(1).first())
        }
    }
}

