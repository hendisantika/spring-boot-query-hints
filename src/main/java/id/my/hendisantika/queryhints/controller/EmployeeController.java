package id.my.hendisantika.queryhints.controller;

import id.my.hendisantika.queryhints.entity.Employee;
import id.my.hendisantika.queryhints.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-query-hints
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 08/03/25
 * Time: 06.30
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/count")
    public ResponseEntity<String> getTotalEmployeeCount() {
        return ResponseEntity.ok("Total employees record counts =" + employeeService.fetchEmployees());
    }

    @GetMapping("/salary/{amount}")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(@PathVariable double amount) {
        return ResponseEntity.ok(employeeService.getEmployeesBySalary(amount));
    }
}
