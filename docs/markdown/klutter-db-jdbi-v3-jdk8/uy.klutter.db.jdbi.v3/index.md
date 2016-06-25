[uy.klutter.db.jdbi.v3](.)


## Package uy.klutter.db.jdbi.v3

### Types

|&nbsp;|&nbsp;|
|---|---|
| [KotlinBinder](-kotlin-binder/index.md) | `class KotlinBinder&nbsp;:&nbsp;Binder<Bind,&nbsp;Any>` |
| [KotlinBinderFactory](-kotlin-binder-factory/index.md) | `class KotlinBinderFactory&nbsp;:&nbsp;ParameterBinderFactory` |
| [KotlinMapper](-kotlin-mapper/index.md) | `class KotlinMapper<C&nbsp;:&nbsp;Any>&nbsp;:&nbsp;ResultSetMapper<C>` |
| [KotlinMapperFactory](-kotlin-mapper-factory/index.md) | `class KotlinMapperFactory&nbsp;:&nbsp;ResultSetMapperFactory` |
| [KotlinPlugin](-kotlin-plugin/index.md) | `class KotlinPlugin&nbsp;:&nbsp;JdbiPlugin` |

### Exceptions

|&nbsp;|&nbsp;|
|---|---|
| [KotlinMemberAccessException](-kotlin-member-access-exception/index.md) | `class KotlinMemberAccessException&nbsp;:&nbsp;DBIException` |
| [NoSuchColumnMapperException](-no-such-column-mapper-exception/index.md) | `class NoSuchColumnMapperException&nbsp;:&nbsp;DBIException` |

### Extensions for External Classes

|&nbsp;|&nbsp;|
|---|---|
| [java.lang.Class](java.lang.-class/index.md) |  |
| [org.jdbi.v3.DBI](org.jdbi.v3.-d-b-i/index.md) |  |
| [org.jdbi.v3.Handle](org.jdbi.v3.-handle/index.md) |  |
| [org.jdbi.v3.Query](org.jdbi.v3.-query/index.md) |  |

### Functions

|&nbsp;|&nbsp;|
|---|---|
| [attachSqlObject](attach-sql-object.md) | `fun <T&nbsp;:&nbsp;Any> attachSqlObject(handle:&nbsp;Handle, sqlObjectType:&nbsp;KClass<T>): T` |
