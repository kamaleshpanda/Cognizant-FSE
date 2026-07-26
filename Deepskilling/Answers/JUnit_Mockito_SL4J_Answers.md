# JUnit, Mockito and SLF4J - Answers


---

## Part 1: Basic & Advanced JUnit 5 Exercises

### Target Class (`Calculator.java`)
```java
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divide by zero");
        return a / b;
    }
}
```

### Basic JUnit 5 Test Class (`CalculatorTest.java`)
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Test Addition")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    @DisplayName("Test Division Exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }
}
```

### Advanced JUnit 5 Tests: Parameterized & Nested Tests

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = { "radar", "level", "madam" })
    void testIsPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }

    private boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equalsIgnoreCase(str);
    }
}
```

---

## Part 2: Mockito & Advanced Mocking Exercises

### Service & Dependency Classes

```java
// UserRepository.java
public interface UserRepository {
    User findById(int id);
    User save(User user);
}

// UserService.java
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(int id) {
        User user = userRepository.findById(id);
        return (user != null) ? user.getName() : "Unknown";
    }
}
```

### Mockito Unit Test (`UserServiceTest.java`)

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserName() {
        // Arrange
        User mockUser = new User(101, "Alice");
        when(userRepository.findById(101)).thenReturn(mockUser);

        // Act
        String name = userService.getUserName(101);

        // Assert
        assertEquals("Alice", name);
        verify(userRepository, times(1)).findById(101);
    }
}
```

---

## Part 3: Spring Boot Testing (`@SpringBootTest` & `@WebMvcTest`)

### Controller Test (`CountryControllerTest.java`)

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void testGetCountry() throws Exception {
        mockMvc.perform(get("/countries/IN"))
               .andExpect(status().isOk());
    }
}
```

---

## Part 4: SLF4J & Logback Logging Exercises

### Logging Example (`LoggingDemo.java`)

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

    public void processOrder(String orderId) {
        logger.info("Processing order: {}", orderId);
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("Error processing order {}: {}", orderId, e.getMessage(), e);
        }
    }
}
```

### `logback.xml` Configuration (src/main/resources)

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
