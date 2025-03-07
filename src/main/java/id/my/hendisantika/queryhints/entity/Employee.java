package id.my.hendisantika.queryhints.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-query-hints
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 08/03/25
 * Time: 06.23
 * To change this template use File | Settings | File Templates.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dept;
    private String email;
    private String gender;
    private double salary;

    public Employee(String name, String dept, String email, String gender, double salary) {
        this.name = name;
        this.dept = dept;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
    }
}
