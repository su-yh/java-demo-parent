Spring Data JPA 查询方法支持的关键字（可参考：https://docs.spring.io/spring-data/jpa/docs/2.2.x/reference/html/#repositories.query-methods）

**Table 2.2. Supported keywords inside method names**

| Keyword             | Sample                                | JPQL snippet                                                 |
| ------------------- | ------------------------------------- | ------------------------------------------------------------ |
| `And`               | `findByLastnameAndFirstname`          | `… where x.lastname = ?1 and x.firstname = ?2`               |
| `Or`                | `findByLastnameOrFirstname`           | `… where x.lastname = ?1 or x.firstname = ?2`                |
| `Between`           | `findByStartDateBetween`              | `… where x.startDate between 1? and ?2`                      |
| `LessThan`          | `findByAgeLessThan`                   | `… where x.age < ?1`                                         |
| `GreaterThan`       | `findByAgeGreaterThan`                | `… where x.age > ?1`                                         |
| `After`             | `findByStartDateAfter`                | `… where x.startDate > ?1`                                   |
| `Before`            | `findByStartDateBefore`               | `… where x.startDate < ?1`                                   |
| `IsNull`            | `findByAgeIsNull`                     | `… where x.age is null`                                      |
| `IsNotNull,NotNull` | `findByAge(Is)NotNull`                | `… where x.age not null`                                     |
| `Like`              | `findByFirstnameLike`                 | `… where x.firstname like ?1`                                |
| `NotLike`           | `findByFirstnameNotLike`              | `… where x.firstname not like ?1`                            |
| `StartingWith`      | `findByFirstnameStartingWith`         | `… where x.firstname like ?1` (parameter bound with appended `%`) |
| `EndingWith`        | `findByFirstnameEndingWith`           | `… where x.firstname like ?1` (parameter bound with prepended `%`) |
| `Containing`        | `findByFirstnameContaining`           | `… where x.firstname like ?1` (parameter bound wrapped in `%`) |
| `OrderBy`           | `findByAgeOrderByLastnameDesc`        | `… where x.age = ?1 order by x.lastname desc`                |
| `Not`               | `findByLastnameNot`                   | `… where x.lastname <> ?1`                                   |
| `In`                | `findByAgeIn(Collection<Age> ages)`   | `… where x.age in ?1`                                        |
| `NotIn`             | `findByAgeNotIn(Collection<Age> age)` | `… where x.age not in ?1`                                    |
| `True`              | `findByActiveTrue()`                  | `… where x.active = true`                                    |
| `False`             | `findByActiveFalse()`                 | `… where x.active = false`                                   |

**Matching 生成的语句**
- DEFAULT (case-sensitive) firstname = ?0 默认（大小写敏感）
- DEFAULT (case-insensitive) LOWER(firstname) = LOWER(?0) 默认（忽略大小写）
- EXACT (case-sensitive) firstname = ?0 精确匹配（大小写敏感）
- EXACT (case-insensitive) LOWER(firstname) = LOWER(?0) 精确匹配（忽略大小写）
- STARTING (case-sensitive) firstname like ?0 + ‘%’ 前缀匹配（大小写敏感）
- STARTING (case-insensitive) LOWER(firstname) like LOWER(?0) + ‘%’ 前缀匹配（忽略大小写）
- ENDING (case-sensitive) firstname like ‘%’ + ?0 后缀匹配（大小写敏感）
- ENDING (case-insensitive) LOWER(firstname) like ‘%’ + LOWER(?0) 后缀匹配（忽略大小写）
- CONTAINING (case-sensitive) firstname like ‘%’ + ?0 + ‘%’ 模糊查询（大小写敏感）
- CONTAINING (case-insensitive) LOWER(firstname) like ‘%’ + LOWER(?0) + ‘%’ 模糊查询（忽略大小写） 


