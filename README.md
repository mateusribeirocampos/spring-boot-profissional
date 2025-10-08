# Spring Boot Professional - Java Study Repository

A comprehensive learning repository documenting the journey from fundamental Java concepts to professional Spring Boot development, covering dependency injection, JDBC operations, DAO pattern implementation, and ORM/JPA.

## 📚 Table of Contents

- [About](#about)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Modules Overview](#modules-overview)
- [Getting Started](#getting-started)
- [Database Configuration](#database-configuration)
- [Learning Path](#learning-path)
- [Author](#author)

## 🎯 About

This repository serves as a complete study guide for mastering Spring Boot and related Java technologies. The project is organized into progressive modules, each focusing on specific concepts and design patterns essential for professional Java development.

## 📁 Project Structure

```
spring-boot-profissional/
├── aula1/                              # Introduction to Spring Boot
├── example-components/                 # Manual dependency injection
├── componentes-injecao-dependencias/   # Spring dependency injection
├── jdbc/                               # JDBC fundamentals
│   ├── jdbc.get.postgres/             # SELECT operations
│   ├── jdbc.insertion.postgres/       # INSERT operations
│   ├── jdbc.update.postgres/          # UPDATE operations
│   ├── jdbc.delete.postgres/          # DELETE operations
│   └── jdbc.transaction.postgres/     # Transaction management
└── DAO/                                # Data Access Object pattern
    └── jdbc-dao/                      # Complete DAO implementation
```

## 🛠️ Technologies

- **Java**: 21
- **Spring Boot**: 3.5.6
- **Maven**: Build automation
- **PostgreSQL**: Relational database
- **JDBC**: Database connectivity

## 📖 Modules Overview

### 1. Aula1 - Spring Boot Basics
Introduction to Spring Boot framework with basic dependency injection using `@Autowired` annotation.

**Key Concepts:**
- Spring Boot application structure
- `CommandLineRunner` interface
- Basic dependency injection
- Service layer implementation

**Main Class:** `com.campos.aula1.Aula1Application`

---

### 2. Example Components - Manual Dependency Injection
Demonstrates dependency injection without Spring framework, using manual instantiation and constructor injection.

**Key Concepts:**
- Manual dependency management
- Service composition
- Upcasting and polymorphism
- Tax and pension service implementation

**Main Class:** `org.example.Main`

---

### 3. Componentes Injeção Dependências - Spring DI
Proper implementation of Spring's dependency injection container with `@Autowired` and `@Component` annotations.

**Key Concepts:**
- Spring IoC container
- Component scanning
- Automatic dependency resolution
- Order processing with shipping services

**Main Class:** `com.campos.Main`

---

### 4. JDBC - Database Operations
Comprehensive coverage of Java Database Connectivity (JDBC) with PostgreSQL.

#### 4.1 JDBC Get
- SELECT queries
- ResultSet handling
- JOIN operations
- Database connection management

#### 4.2 JDBC Insertion
- INSERT operations
- Statement execution
- Auto-generated keys retrieval

#### 4.3 JDBC Update
- UPDATE operations
- PreparedStatement usage
- Row modification

#### 4.4 JDBC Delete
- DELETE operations
- Referential integrity handling
- Exception management

#### 4.5 JDBC Transaction
- Transaction management
- COMMIT and ROLLBACK operations
- Data consistency

**Key Components:**
- `DB`: Database connection factory
- `DbException`: Custom exception handling
- `DbIntegrityException`: Referential integrity violations

---

### 5. DAO - Data Access Object Pattern
Complete implementation of the DAO design pattern with full CRUD operations for Seller and Department entities.

**Key Features:**
- Factory pattern implementation (`DaoFactory`)
- Interface-based design (`SellerDao`, `DepartmentDao`)
- JDBC implementation (`SellerDaoJDBC`, `DepartmentDaoJDBC`)
- PreparedStatement for SQL injection prevention
- ResultSet optimization and reuse
- Custom query methods (e.g., `findByDepartment`, `findByBaseSalaryGreaterThan`)

**Entities:**
- `Seller`: Employee entity with department relationship
- `Department`: Department entity

**DAO Operations:**
- `findById(Integer id)`: Retrieve entity by ID
- `findAll()`: Retrieve all entities
- `insert(Entity obj)`: Create new entity
- `update(Entity obj)`: Update existing entity
- `deleteById(Integer id)`: Delete entity by ID
- Custom queries specific to business logic

**Main Class:** `application.Program`

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- Maven 3.6+
- PostgreSQL database
- IDE (Eclipse, IntelliJ IDEA, or VS Code)

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/yourusername/spring-boot-profissional.git
cd spring-boot-profissional
```

2. **Configure the database:**
Create a PostgreSQL database and update the `db.properties` file in each JDBC module:
```properties
user=your_username
password=your_password
dburl=jdbc:postgresql://localhost:5432/your_database
useSSL=false
```

3. **Build Spring Boot modules:**
```bash
cd aula1
mvn clean install
mvn spring-boot:run
```

4. **Run JDBC projects:**
```bash
cd jdbc/jdbc.get.postgres
javac -cp .:lib/* src/application/Program.java
java -cp .:lib/* application.Program
```

5. **Run DAO project:**
```bash
cd DAO/jdbc-dao
# Compile and run using your IDE or command line
```

## 🗄️ Database Configuration

The project uses PostgreSQL with the following schema:

```sql
CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60)
);

CREATE TABLE seller (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    base_salary DOUBLE PRECISION NOT NULL,
    department_id INTEGER,
    FOREIGN KEY (department_id) REFERENCES department(id)
);
```

## 📈 Learning Path

This repository follows a progressive learning approach:

1. **Foundations**: Understanding Spring Boot basics and application structure
2. **Dependency Injection**: Manual vs. Spring-managed dependency injection
3. **Database Basics**: Raw JDBC operations (CRUD)
4. **Design Patterns**: DAO pattern implementation
5. **Advanced Topics**: Transaction management and data consistency
6. **Next Steps**: ORM/JPA and Hibernate (coming soon)

## 🎓 Key Takeaways

- **Spring Boot** simplifies Java application development with auto-configuration
- **Dependency Injection** promotes loose coupling and testability
- **JDBC** provides low-level database access and control
- **DAO Pattern** separates business logic from data access logic
- **PreparedStatement** prevents SQL injection and improves performance
- **Factory Pattern** centralizes object creation and dependency management

## 📝 Recent Updates

- ✅ Connection management improvements in `Program.java`
- ✅ Corrections in `SellerDaoJDBC` and `DepartmentDaoJDBC`
- ✅ Refactored with `PreparedStatement` and `ResultSet`
- ✅ Implemented `findByBaseSalaryGreaterThan` query method
- ✅ Complete CRUD operations for both Seller and Department entities

## 👤 Author

**Mateus Campos**

This repository documents my journey in learning professional Spring Boot development, from fundamentals to advanced enterprise patterns.

## 📄 License

This project is for educational purposes.

---

**Note:** This is an active learning repository. New modules and improvements are added regularly as the study progresses toward professional Spring Boot development with JPA/Hibernate and RESTful APIs.
