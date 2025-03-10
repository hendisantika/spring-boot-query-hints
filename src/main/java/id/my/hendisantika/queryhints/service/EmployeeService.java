package id.my.hendisantika.queryhints.service;

import id.my.hendisantika.queryhints.entity.Department;
import id.my.hendisantika.queryhints.entity.Employee;
import id.my.hendisantika.queryhints.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-query-hints
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 08/03/25
 * Time: 06.26
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Long fetchEmployees() {
        return employeeRepository.count();
    }

    public List<Employee> getEmployeesBySalary(double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
        Department department = Department.valueOf(departmentName.toUpperCase());
        return employeeRepository.findEmployeesWithDepartment(department);
    }

}
