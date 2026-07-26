# Spring Data JPA with Hibernate - Answers


---

## 1. Spring Data JPA Setup & Configuration

### `application.properties`
```properties
# Server and Database Configuration
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ormlearn
spring.datasource.username=root
spring.datasource.password=root

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging Settings
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql=TRACE
```

---

## 2. Country Entity & Repository Implementation

### Database Table Creation Script
```sql
CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50) NOT NULL
);

INSERT INTO country VALUES ('IN', 'India');
INSERT INTO country VALUES ('US', 'United States of America');
```

### Country Entity (`Country.java`)
```java
package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {

    @Id
    @Column(name="co_code")
    private String code;

    @Column(name="co_name")
    private String name;

    public Country() {}

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
```

### Country Repository (`CountryRepository.java`)
```java
package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Country;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    // Custom finder query method
    List<Country> findByNameContainingIgnoreCase(String name);
    List<Country> findByNameStartingWith(String prefix);
}
```

### Country Service (`CountryService.java`)
```java
package com.cognizant.ormlearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country getCountryByCode(String code) {
        return countryRepository.findById(code).orElse(null);
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }
}
```

---

## 3. Comparison: JPA vs Hibernate vs Spring Data JPA

| Aspect | JPA (Java Persistence API) | Hibernate | Spring Data JPA |
|---|---|---|---|
| **What is it?** | Specification (JSR 338) defining standard ORM annotations & interfaces | Concrete ORM Framework implementing JPA specification | Abstraction layer on top of JPA providers (like Hibernate) |
| **Boilerplate Code** | High (requires EntityManager setup) | Moderate (Session, SessionFactory management) | Very Low (Repositories handle CRUD automatically) |
| **Transaction Control** | Manual (`EntityTransaction`) | Manual (`session.beginTransaction()`) | Declarative (`@Transactional`) |
| **Query Mechanism** | JPQL, Criteria API | HQL, Criteria API, Native SQL | Query Methods (e.g. `findByName`), `@Query` |

---

## 4. Entity Relationships (One-to-Many & Many-to-Many)

### Employee & Department (@ManyToOne)
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
```

### Department & Employees (@OneToMany)
```java
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
```
