package id.my.hendisantika.queryhints.repository;

import id.my.hendisantika.queryhints.entity.Employee;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-query-hints
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 08/03/25
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    @QueryHints({
            @QueryHint(name = "org.hibernate.readOnly", value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.query.timeout", value = "2000")
    })
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    @QueryHints({
            @QueryHint(name = "org.hibernate.readOnly", value = "true"),
            @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
            @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE"),
            @QueryHint(name = "jakarta.persistence.query.timeout", value = "2000")
    })
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);
}
