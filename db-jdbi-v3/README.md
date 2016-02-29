## klutter/db-jdbi-v3

For JDBI v3.x adding both automatic parameter binding by name for Kotlin methods in SqlObjects.  And
ResultSet mapping to Kotlin classes anywhere a ResultSet is used.

Parameter binding supports individual primitive types, and also Kotlin or JavaBean style objects as a parameter (referenced in binding as `:paramName.propertyName`).  No annotations are needed.

ResultSet mapping supports data classes where all fields are present in the constructor, if you have a default constructor and desire fields to be set after, use a normal JavaBean style ResultSet mapper.
The mapper supports nullable types.  It also uses default parameter values in the constructor if the parameter type is not nullable and the value absent in the result set.

**NOTE:  JDBI v3 and this module are experimental**, in development, and only supports JDK 8.  It is however pretty stable due to extensive test coverage.  But the integration with JDBI is not yet in an official release ([pull request in progress](https://github.com/jdbi/jdbi/pull/293)).
Until it has a formal JDBI release supporting our plugin extension point, we use a [custom build from a branch on my fork](https://github.com/apatrida/jdbi/tree/jdbi3-binder-registry) hosted in this repo:

```
maven {
    url 'http://dl.bintray.com/jaysonminard/kohesive'
}
```

**See also:** [JDBI 2 version](../db-jdbi-v2)

### Usage:

Once attached to a DBI or Handle instance, the plugin is automatic.  If you load all DBI plugins via `DBI.installPlugins()` this plugin will be discovered and registered automatically.
Otherwise, you can attach the plugin via the extension methods:  `DBI.attachKotlinPlugin()` and `Handle.attachKotlinPlugin()`.

An example from the test class:

```
    data class Thing(val id: Int, val name: String,
                     val nullable: String?,
                     val nullableDefaultedNull: String? = null,
                     val nullableDefaultedNotNull: String? = "not null",
                     val defaulted: String = "default value")

    interface ThingDao {
        @SqlUpdate("insert into something (id, name) values (:something.id, :something.name)")
        fun insert(something: Thing)

        @SqlQuery("select id, name from something")
        fun list(): List<Thing>

        @SqlQuery("select id, name, null as nullable, null as nullableDefaultedNull, null as nullableDefaultedNotNull, 'test' as defaulted from something")
        fun listWithNulls(): List<Thing>

        @SqlQuery("select id, name from something where id=:id")
        fun findById(id: Int): Thing
    }

    @Test fun testDao() {
      dbi.loadPlugins() // only once when creating DBI instance

      val dao = dbi.open(ThingDao::class)

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
```

And of course all other normal usage of JDBI is valid including fluent query building.  There are a few extensions to help:

* `map(KClass<T>)`
* `useSequence(block: (Sequence<T>)->Unit)` and `useSequence(mapTo: KClass<T>, block: (Sequence<T>)->Unit)`

Allowing code like:

```
val qry = handle.createQuery("select id, name from something where id = :id")
val things = qry.bind("id", brian.id).map(Thing::class).list()
```

and for using a Sequence that is auto closed:

```
qryAll.map(Thing::class).useSequence {
    it.forEach(::println)
}
```

or:

```
qryAll.useSequence(Thing::class) {
    it.forEach(::println)
}
```

**NOTE:** If you bind a Kotlin class (`MyClass::class`) you use the Kotlin ResultSetMapper.
If you bind a class as the Java class (`MyClass::class.java`), you use the JavaBean mapper.

### Coming soon:

* support a mixed model of constructor + properties set after construction (best fit for longest constructor that matches, set the rest after)
