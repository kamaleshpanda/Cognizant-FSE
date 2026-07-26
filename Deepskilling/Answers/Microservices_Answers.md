# Microservices Architecture & Spring Boot 3 - Answers


---

## 1. Service Discovery Server (Eureka Server)

### `application.yml` (Eureka Server)
```yaml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

### Main Class (`EurekaServerApplication.java`)
```java
package com.cognizant.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

---

## 2. Microservice 1: Account Service

### `application.yml` (Account Service)
```yaml
server:
  port: 8081

spring:
  application:
    name: account-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Account REST Controller (`AccountController.java`)
```java
package com.cognizant.account;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{number}")
    public Map<String, Object> getAccount(@PathVariable String number) {
        Map<String, Object> account = new HashMap<>();
        account.put("number", number);
        account.put("type", "Savings");
        account.put("balance", 25000);
        return account;
    }
}
```

---

## 3. Microservice 2: Loan Service

### `application.yml` (Loan Service)
```yaml
server:
  port: 8082

spring:
  application:
    name: loan-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

### Loan REST Controller (`LoanController.java`)
```java
package com.cognizant.loan;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @GetMapping("/{number}")
    public Map<String, Object> getLoan(@PathVariable String number) {
        Map<String, Object> loan = new HashMap<>();
        loan.put("number", number);
        loan.put("type", "Car Loan");
        loan.put("amount", 400000);
        return loan;
    }
}
```

---

## 4. API Gateway Configuration (Spring Cloud Gateway)

### `application.yml` (API Gateway)
```yaml
server:
  port: 8090

spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: account-service
          uri: lb://ACCOUNT-SERVICE
          predicates:
            - Path=/accounts/**
        - id: loan-service
          uri: lb://LOAN-SERVICE
          predicates:
            - Path=/loans/**

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

## 5. Feign Client & Composite Microservice (Customer Composite Service)

### Feign Client Interface (`AccountFeignClient.java`)
```java
package com.cognizant.composite.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountFeignClient {

    @GetMapping("/accounts/{number}")
    Map<String, Object> getAccountDetails(@PathVariable("number") String number);
}
```

### Composite Controller with Fallback (`CustomerCompositeController.java`)
```java
package com.cognizant.composite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cognizant.composite.client.AccountFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer-dashboard")
public class CustomerCompositeController {

    @Autowired
    private AccountFeignClient accountFeignClient;

    @GetMapping("/{accNumber}")
    @CircuitBreaker(name = "accountServiceBreaker", fallbackMethod = "getAccountFallback")
    public Map<String, Object> getCustomerDashboard(@PathVariable String accNumber) {
        return accountFeignClient.getAccountDetails(accNumber);
    }

    public Map<String, Object> getAccountFallback(String accNumber, Throwable t) {
        Map<String, Object> fallback = new HashMap<>();
        fallback.put("number", accNumber);
        fallback.put("status", "Account service currently unavailable. Displaying cached data.");
        return fallback;
    }
}
```
