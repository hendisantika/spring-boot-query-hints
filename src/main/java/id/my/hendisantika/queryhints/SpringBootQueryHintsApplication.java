package id.my.hendisantika.queryhints;

import id.my.hendisantika.queryhints.entity.Employee;
import id.my.hendisantika.queryhints.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static id.my.hendisantika.queryhints.entity.Department.randomLetter;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootQueryHintsApplication {
    private final EmployeeRepository employeeRepository;

    public static double generateSalary(double min, double max) {
        // Generate a random double in the range 0.0 to 1.0
        Random random = new Random();
        double randomValue = random.nextDouble();

        // Scale it to the range [min, max]
        return min + (randomValue * (max - min));
    }

    @PostConstruct
    public void initDB() {
        employeeRepository.deleteAll();
        List<Employee> employees = IntStream.rangeClosed(3001, 100_000)
                .mapToObj(i -> new Employee("emp" + i, randomLetter(), "employee" + i + "@gmail.com", getGender(), generateSalary(50000, 100000))).collect(Collectors.toList());

        employeeRepository.saveAll(employees);
    }

    private String getGender() {
        return new Random().nextBoolean() ? "Male" : "Female";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQueryHintsApplication.class, args);
    }

}
