# HelpDesk API

REST API for managing users, categories, and support tickets, developed with Java and Spring Boot.

This project was created as a practical backend application to apply concepts such as REST APIs, object-oriented programming, Spring Boot, JPA, DTOs, exception handling, pagination, API documentation, and automated testing.

## 🚀 Project Overview

The HelpDesk API provides a backend structure for managing a technical support environment.

The API allows the management of:

- Users
- Categories
- Support tickets

The project was developed incrementally, with each version introducing new backend concepts and improvements.

## 🛠️ Technologies

- Java 21
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jakarta Validation
- Maven
- JUnit 5
- Mockito
- Springdoc OpenAPI
- Swagger UI

## 🏗️ Architecture

The project follows a layered architecture:

```text
Controller
    ↓
DTO
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
Database
```

For API responses, entities are converted into DTOs through dedicated mappers:

```text
Entity
   ↓
Mapper
   ↓
DTO
   ↓
JSON Response
```

The main packages are organized as follows:

```text
src/main/java/com/gustavo/helpdeskapi

├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
└── service
```

## 📌 Main Features

### Users

The API supports:

- Creating users
- Listing users
- Finding users by ID
- Updating users
- Deleting users

User responses are handled through DTOs to prevent sensitive information, such as passwords, from being exposed by the API.

### Categories

The API currently supports:

- Creating categories
- Listing categories

### Tickets

The API supports:

- Creating tickets
- Listing tickets
- Finding tickets by ID
- Updating tickets
- Deleting tickets
- Associating tickets with users
- Associating tickets with categories
- Defining ticket status
- Defining ticket priority

## 📦 DTOs

The project uses Data Transfer Objects to control the data exchanged through the API.

Examples include:

```text
UserDTO
UserCreateDTO

CategoryDTO
CategoryCreateDTO

TicketDTO
TicketCreateDTO

ErrorResponseDTO
```

Using DTOs helps separate the API contract from the persistence entities and prevents sensitive entity fields from being unnecessarily exposed.

## 🔄 Mappers

Dedicated mapper classes are responsible for converting between DTOs and entities.

Examples:

```text
UserMapper
CategoryMapper
TicketMapper
```

This keeps conversion logic outside Controllers and Services.

## ✅ Validation

The API uses Jakarta Bean Validation to validate incoming data.

Examples include:

```java
@NotBlank
@NotNull
```

Controllers use:

```java
@Valid
```

to trigger validation before the request reaches the service layer.

Example validation flow:

```text
HTTP Request
     ↓
CreateDTO
     ↓
@Valid
     ↓
Validation
     ↓
Service
```

Invalid requests return an HTTP `400 Bad Request`.

## ⚠️ Exception Handling

The project implements centralized exception handling using:

```text
ResourceNotFoundException
GlobalExceptionHandler
ErrorResponseDTO
```

This prevents exception-handling logic from being duplicated across Controllers.

Example:

```text
ResourceNotFoundException
          ↓
GlobalExceptionHandler
          ↓
HTTP 404
          ↓
ErrorResponseDTO
```

Validation errors are also handled centrally and return structured responses.

Example:

```json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "...",
  "errors": {
    "name": "Nome é obrigatório"
  }
}
```

## 📄 Pagination and Sorting

Ticket listing supports pagination and sorting through Spring Data's `Pageable`.

Example:

```http
GET /api/tickets?page=0&size=10&sort=id,desc
```

The API returns pagination metadata such as:

- Current page
- Page size
- Total elements
- Total pages
- Sorting information

Example response structure:

```json
{
  "content": [],
  "number": 0,
  "size": 10,
  "totalElements": 10,
  "totalPages": 1
}
```

## 📚 API Documentation

The API is documented using OpenAPI and Swagger UI.

After starting the application, the documentation is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

The OpenAPI specification is available at:

```text
http://localhost:8080/v3/api-docs
```

The API documentation is configured with:

```text
Name: HelpDesk API
Version: 2.0.0
```

## 🧪 Automated Tests

The project includes automated tests using JUnit 5 and Mockito.

The tests focus mainly on the service layer and use mocks to isolate business logic from the database.

Current test structure:

```text
UserServiceTest
├── shouldFindUserById
├── shouldThrowExceptionWhenUserDoesNotExist
└── shouldCreateUser

TicketServiceTest
├── shouldCreateTicket
├── shouldThrowExceptionWhenUserDoesNotExist
├── shouldThrowExceptionWhenCategoryDoesNotExist
├── shouldFindTicketById
├── shouldThrowExceptionWhenTicketDoesNotExist
├── shouldUpdateTicket
└── shouldDeleteTicket

CategoryServiceTest
├── shouldCreateCategory
└── shouldFindAllCategories
```

The project currently contains:

```text
13 automated tests
```

The tests cover successful operations, missing resources, repository interactions, and exception scenarios.

## 🗄️ Database

The application uses PostgreSQL as its relational database.

JPA and Hibernate are used to map Java entities to database tables.

Main entities include:

```text
User
Category
Ticket
```

Relationships include:

```text
User
  │
  └── Ticket

Category
  │
  └── Ticket
```

## 🔌 API Endpoints

### Users

```http
POST   /api/users
GET    /api/users
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}
```

### Categories

```http
POST /api/categories
GET  /api/categories
```

### Tickets

```http
POST   /api/tickets
GET    /api/tickets
GET    /api/tickets/{id}
PUT    /api/tickets/{id}
DELETE /api/tickets/{id}
```

## ▶️ Running the Project

### Prerequisites

Make sure you have:

- Java 21
- PostgreSQL
- IntelliJ IDEA or another Java IDE

The project includes the Maven Wrapper, so Maven does not need to be installed globally.

### 1. Clone the repository

```bash
git clone https://github.com/gustavoconce/helpdesk-api.git
```

### 2. Configure the database

Create a PostgreSQL database for the application.

Then configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/helpdesk
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 3. Run the application

Using the Maven Wrapper on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Or run the main Spring Boot class directly through your IDE.

### 4. Access the API

The API will be available at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## 🧪 Running Tests

To run the complete test suite using the Maven Wrapper on Windows:

```powershell
.\mvnw.cmd test
```

The project currently contains 13 automated tests.

## 📈 Project Versions

### V1.0

Initial implementation of the HelpDesk API, including:

- User management
- Category management
- Ticket management
- REST endpoints
- PostgreSQL integration
- JPA/Hibernate
- Basic exception handling

### V2.0

The second version focused on improving the API architecture, validation, documentation, and reliability.

Implemented:

- DTOs and CreateDTOs
- Entity/DTO mappers
- Request validation
- Centralized exception handling
- Structured error responses
- Pagination
- Sorting
- OpenAPI documentation
- Swagger UI
- Automated unit tests with JUnit 5 and Mockito

## 🎯 Project Goals

The main goal of this project is to demonstrate practical backend development skills using Java and Spring Boot.

The project focuses on concepts commonly used in real-world backend applications, including:

- REST API development
- Layered architecture
- Object-oriented programming
- Data persistence
- DTO pattern
- Mapper pattern
- Input validation
- Exception handling
- Pagination
- API documentation
- Automated testing
- Clean and maintainable code

## 🔮 Future Improvements

Possible improvements for future versions include:

- Authentication and authorization with Spring Security
- Password hashing
- Role-based access control
- More complete CRUD operations for categories
- Dedicated UpdateDTOs
- Integration tests
- Improved API error standardization
- Docker support
- CI/CD pipeline
- Production environment configuration

## 👨‍💻 Author

Developed by Gustavo Conceição.

Bachelor's degree in Information Systems with a focus on backend development, APIs, software engineering, and data-driven solutions.
