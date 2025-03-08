# spring-boot-query-hints

Query Hints in Spring Data JPA: Optimizing Database Queries

### Introduction

When working with Spring Data JPA, database performance plays a crucial role in ensuring that applications run
efficiently, especially when dealing with large datasets. **Unoptimized queries, excessive caching and inefficient
fetching strategies** can lead to increased memory usage, slow response times and unnecessary database load.

This is where `@QueryHints` comes into play. It allows developers to pass vendor-specific hints to the JPA provider (
e.g., Hibernate) to optimize query execution. By leveraging @QueryHints, we can fine-tune database interactions for
better performance, reduced memory consumption, and improved query execution speed.

In this article, we’ll explore how to use `@QueryHints` effectively, covering common optimizations such as read-only
queries, fetch size tuning, query timeouts and caching strategies. We’ll also discuss when to use @QueryHints and when
to avoid them, ensuring you make informed decisions while optimizing your Spring Data JPA queries.

In Spring Data JPA, the `@QueryHints` annotation allows us to provide vendor-specific hints to the JPA provider, helping
fine-tune query execution. These hints do not modify the query itself but influence how it is executed internally by
Hibernate (or another JPA provider).

### How @QueryHints Works

The `@QueryHints` annotation is used within Spring Data JPA repositories to pass a list of hints, each represented as a
@QueryHint annotation. The `@QueryHint` takes two parameters:

* name — The name of the hint (JPA or Hibernate-specific).
* value — The value assigned to the hint.

```java

@Query("SELECT e FROM Employee e WHERE e.salary > :salary")
@QueryHints({
        @QueryHint(name = "org.hibernate.readOnly", value = "true"),
        @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
        @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
        @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE"),
        @QueryHint(name = "jakarta.persistence.query.timeout", value = "2000")
})
List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary);
```

a) Read-Only Queries

Marking queries as read-only prevents Hibernate from tracking changes to the retrieved entities. This avoids unnecessary
persistence overhead, reducing memory consumption and improving query performance.

### Why Use Read-Only Queries?

* Hibernate tracks entity changes to detect modifications for persistence.
* For read-only operations, this tracking is unnecessary and consumes extra memory.
* Marking queries as read-only skips this tracking, improving performance.

Example: Marking Queries as Read-Only

```java

@QueryHints({@QueryHint(name = "org.hibernate.readOnly", value = "true")})
Employee findByEmail(String email);
```

💡 Performance Benefit:

* Hibernate does not keep the retrieved entity in the persistence context.
* Saves CPU and memory by skipping unnecessary dirty checks.
* Ideal for queries fetching reports, logs, or analytics data where updates are not needed.

b) Fetch Size Optimization
When fetching large result sets, retrieving all records at once can cause high memory consumption and slow performance.
The fetchSize hint allows controlling how many rows are retrieved at a time, improving query efficiency.

### Why Set Fetch Size?

Without a fetch size, JPA loads all data at once, leading to high memory usage.
Setting a fetch size retrieves data in chunks, reducing memory pressure and optimizing JDBC communication.
Example: Controlling Fetch Size

```java

@QueryHints({@QueryHint(name = "org.hibernate.fetchSize", value = "50")})
List<Employee> findAllEmployees();
```

💡 Performance Benefit:

* Efficient memory usage: Loads only 50 records at a time instead of everything at once.
* Reduces database load: Fetching in chunks minimizes the impact on database resources.
* Useful for large tables: When processing millions of records, helps prevent OutOfMemoryErrors.

c) JDBC Query Timeout
Long-running queries can block resources, leading to slow application performance. Setting a query timeout ensures that
if a query exceeds the given time, it is automatically terminated.

### Why Set a Query Timeout?

* Prevents queries from blocking connections indefinitely.
* Ensures faster application response times.
* Helps avoid deadlocks and performance bottlenecks.

Example: Setting a Query Timeout (5 seconds)

```java

@QueryHints({@QueryHint(name = "javax.persistence.query.timeout", value = "5000")})
    // 5 sec timeout
List<Employee> findByLastName(String lastName);
```

💡 Performance Benefit:

* If the query takes more than 5 seconds, it is canceled.
* Avoids thread starvation by freeing up resources quickly.
* Useful for applications dealing with high database load.

d) Cache Strategy Optimization
Caching improves performance by reducing database hits for frequently accessed data. JPA provides two levels of caching:

* First-Level Cache (default) — Session-level cache (local to a transaction).
* Second-Level Cache — Application-wide cache that persists across transactions.

The cacheable hint allows queries to use second-level caching.

Example: Enabling Query Caching

```java

@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
List<Employee> findAllCached();
```

💡 Performance Benefit:

* Avoids redundant database queries by retrieving data from cache.
* Speeds up repeated queries, improving overall response times.
* Ideal for frequently accessed but rarely updated data (e.g., product catalogs, configuration settings).

When to Use and When to Avoid @QueryHints

✅ Use @QueryHints When:

✔ Optimizing read-heavy operations.

✔ Working with large datasets that need efficient fetching.

✔ Avoiding unnecessary dirty checks for read-only queries.

✔ Controlling query execution time to prevent long-running operations.

✔ Improving performance through caching.

❌ Avoid @QueryHints When:

✖ Queries are already optimized through indexing and proper joins.

✖ Using dynamic queries, where hints may not be flexible.

✖ Needing a vendor-agnostic solution (as some hints are Hibernate-specific).

Conclusion
Using @QueryHints in Spring Data JPA can significantly improve performance by optimizing query execution. Key takeaways:

✅ Read-only queries reduce memory overhead by skipping persistence checks.

✅ Fetch size optimization improves large dataset retrieval efficiency.

✅ Query timeouts prevent long-running queries from blocking system resources.

✅ Caching strategies speed up repeated queries and reduce database load.

Before applying @QueryHints, always profile your queries using tools like Hibernate Statistics, Spring Boot Actuator, or
database query analyzers to identify actual performance bottlenecks.

http://localhost:8080/employees/count

http://localhost:8080/employees/department/IT

http://localhost:8080/employees/salary/50000
