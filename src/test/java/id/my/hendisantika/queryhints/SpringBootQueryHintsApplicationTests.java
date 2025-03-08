package id.my.hendisantika.queryhints;

import id.my.hendisantika.queryhints.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:mysql:9.2.0:///employeeDB"
        },
        webEnvironment = RANDOM_PORT
)
class SpringBootQueryHintsApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void deleteAll() {
        employeeRepository.deleteAll();
    }
}
