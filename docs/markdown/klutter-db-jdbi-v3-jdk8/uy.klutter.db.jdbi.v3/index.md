[uy.klutter.db.jdbi.v3](.)


## Package uy.klutter.db.jdbi.v3


### Types


| [KotlinBinder](-kotlin-binder/index.md) | `class KotlinBinder&nbsp;:&nbsp;Binder&lt;Bind,&nbsp;Any&gt;` |
| [KotlinBinderFactory](-kotlin-binder-factory/index.md) | `class KotlinBinderFactory&nbsp;:&nbsp;ParameterBinderFactory` |
| [KotlinMapper](-kotlin-mapper/index.md) | `class KotlinMapper&lt;C&nbsp;:&nbsp;Any&gt;&nbsp;:&nbsp;ResultSetMapper&lt;C&gt;` |
| [KotlinMapperFactory](-kotlin-mapper-factory/index.md) | `class KotlinMapperFactory&nbsp;:&nbsp;ResultSetMapperFactory` |
| [KotlinPlugin](-kotlin-plugin/index.md) | `class KotlinPlugin&nbsp;:&nbsp;JdbiPlugin` |


### Exceptions


| [KotlinMemberAccessException](-kotlin-member-access-exception/index.md) | `class KotlinMemberAccessException&nbsp;:&nbsp;DBIException` |
| [NoSuchColumnMapperException](-no-such-column-mapper-exception/index.md) | `class NoSuchColumnMapperException&nbsp;:&nbsp;DBIException` |


### Extensions for External Classes


| [java.lang.Class](java.lang.-class/index.md) |  |
| [org.jdbi.v3.DBI](org.jdbi.v3.-d-b-i/index.md) |  |
| [org.jdbi.v3.Handle](org.jdbi.v3.-handle/index.md) |  |
| [org.jdbi.v3.Query](org.jdbi.v3.-query/index.md) |  |


### Functions


| [attachSqlObject](attach-sql-object.md) | `fun &lt;T&nbsp;:&nbsp;Any&gt; attachSqlObject(handle:&nbsp;Handle, sqlObjectType:&nbsp;KClass&lt;T&gt;): T` |

