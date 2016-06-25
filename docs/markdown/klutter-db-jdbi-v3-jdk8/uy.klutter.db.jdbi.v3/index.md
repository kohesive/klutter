[uy.klutter.db.jdbi.v3](.)


## Package uy.klutter.db.jdbi.v3

### Types

|&nbsp;|&nbsp;|
|---|---|
| [KotlinBinder](-kotlin-binder/index.md) | <code>class KotlinBinder : Binder<Bind, Any></code><br/> |
| [KotlinBinderFactory](-kotlin-binder-factory/index.md) | <code>class KotlinBinderFactory : ParameterBinderFactory</code><br/> |
| [KotlinMapper](-kotlin-mapper/index.md) | <code>class KotlinMapper<C : Any> : ResultSetMapper<C></code><br/> |
| [KotlinMapperFactory](-kotlin-mapper-factory/index.md) | <code>class KotlinMapperFactory : ResultSetMapperFactory</code><br/> |
| [KotlinPlugin](-kotlin-plugin/index.md) | <code>class KotlinPlugin : JdbiPlugin</code><br/> |

### Exceptions

|&nbsp;|&nbsp;|
|---|---|
| [KotlinMemberAccessException](-kotlin-member-access-exception/index.md) | <code>class KotlinMemberAccessException : DBIException</code><br/> |
| [NoSuchColumnMapperException](-no-such-column-mapper-exception/index.md) | <code>class NoSuchColumnMapperException : DBIException</code><br/> |

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
| [attachSqlObject](attach-sql-object.md) | <code>fun <T : Any> attachSqlObject(handle: Handle, sqlObjectType: KClass<T>): T</code><br/> |
