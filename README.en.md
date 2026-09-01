# 🚀 Employee Management API - `Projedata`

🇧🇷 Leia isto em [Português](README.md)

Enterprise RESTful API developed in **Java 21** and **Spring Boot 3**, featuring data persistence with **Spring Data JPA**, in-memory **H2 Database**, interactive documentation via **Swagger UI (OpenAPI 3)**, payload validation using **Bean Validation**, automated unit testing with **JUnit 5 / Mockito**, and containerization via **Docker & Docker Compose**.

## 🌐 Live Swagger Demo

Access the live application in production:
👉 **[Swagger](https://teste-pratico-projedata-4kpw.onrender.com)**

## 📝 About the Project

This project was originally developed as a solution to the **Projedata** practical technical assessment (for a Software Developer position) and subsequently evolved into a professional layered architecture (*Controller*, *Service*, *Repository*, *Model*, *DTO*).

The application manages employee records and implements core business rules, including batch percentage salary increases, age calculations, dynamic grouping by roles, birthday filtering, and payroll statistical analysis based on multiples of the minimum wage.

## 🖼️ Preview

<img src="./image/projeto.gif" alt="App Demo" />

## 🌐 API Endpoints

The API follows RESTful standards with structured JSON responses and semantic HTTP status codes:

| Method | Endpoint | Description | Parameters / Query Params |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/funcionarios` | List all employees | `ordenarPorNome=true/false` *(default: false)* |
| `GET` | `/api/v1/funcionarios/{id}` | Find employee by ID | `id` *(path variable)* |
| `POST` | `/api/v1/funcionarios` | Register a new employee | JSON Body (`FuncionarioRequestDTO`) |
| `DELETE` | `/api/v1/funcionarios/{id}` | Delete employee by ID | `id` *(path variable)* |
| `DELETE` | `/api/v1/funcionarios/nome/{nome}` | Delete employee by Name | `nome` *(path variable)* |
| `PATCH` | `/api/v1/funcionarios/reajuste` | Apply batch salary raise (%) | `percentual=10.0` *(default: 10.0)* |
| `GET` | `/api/v1/funcionarios/agrupados-por-funcao` | Group employees by role/job title | — |
| `GET` | `/api/v1/funcionarios/aniversariantes` | Filter birthdays by month | `meses=10,12` *(default: 10,12)* |
| `GET` | `/api/v1/funcionarios/mais-velho` | Get the oldest employee and their age | — |
| `GET` | `/api/v1/funcionarios/estatisticas/folha` | Total payroll & minimum wage multiples | `salarioMinimo=1212.00` *(default: 1212.00)* |

## ✨ Features

- [x] **Automatic Data Seeding (Requirement 3.1):** Idempotent database population with initial 10 employee records on application startup via `DataInitializer`.
- [x] **Employee Removal (Requirement 3.2):** Delete by employee name (e.g., "João") or primary key ID.
- [x] **Data Formatting & Display (Requirement 3.3):** Dates formatted cleanly and monetary values formatted with decimal precision.
- [x] **Dynamic Salary Increase (Requirement 3.4):** Batch percentage adjustment (e.g., 10%) updating records in the database.
- [x] **Role Grouping (Requirements 3.5 & 3.6):** Dynamic mapping grouping lists of employees by their job title.
- [x] **Birthday Filtering (Requirement 3.8):** Flexible query for specific months (default: October and December).
- [x] **Oldest Employee Calculation (Requirement 3.9):** Calculation of the oldest employee computing full years with `java.time.Period`.
- [x] **Alphabetical Ordering (Requirement 3.10):** Query with ascending sort by name.
- [x] **Payroll Totals & Wage Multiples (Requirements 3.11 & 3.12):** Total payroll summation and calculation of how many minimum wages each employee earns.

## 📁 Repository Structure

```text
teste-pratico-projedata/
├── src/
│   ├── main/
│   │   ├── java/com/projedata/
│   │   │   ├── config/              # OpenAPI Swagger & Data Seeding Configurations
│   │   │   │   ├── DataInitializer.java
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/          # REST Controllers with OpenAPI Annotations
│   │   │   │   ├── FuncionarioController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── dto/                 # Request, Response & Statistics DTO Records
│   │   │   │   ├── FolhaEstatisticaResponseDTO.java
│   │   │   │   ├── FuncionarioMaisVelhoResponseDTO.java
│   │   │   │   ├── FuncionarioRequestDTO.java
│   │   │   │   └── FuncionarioResponseDTO.java
│   │   │   ├── exception/           # Global Error Handling (RFC 7807 ProblemDetail)
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── model/               # JPA Domain Entities
│   │   │   │   ├── Funcionario.java
│   │   │   │   └── Pessoa.java
│   │   │   ├── repository/          # Spring Data JPA Repositories
│   │   │   │   └── FuncionarioRepository.java
│   │   │   ├── service/             # Business Logic & Financial Calculations
│   │   │   │   └── FuncionarioService.java
│   │   │   └── ProjedataApplication.java  # Main Application Entry Point
│   │   └── resources/
│   │       └── application.yml      # Port, H2 & Swagger UI Configurations
│   └── test/
│       └── java/com/projedata/
│           └── service/
│               └── FuncionarioServiceTest.java # Unit Tests with JUnit 5 & Mockito
├── Dockerfile                       # Multi-Stage Build with Eclipse Temurin JDK 21
├── docker-compose.yml               # Container Orchestration
├── pom.xml                          # Maven Dependency Manager
├── README.md                        # Documentation (Portuguese)
└── README.en.md                     # Documentation (English)
```

## 🛠️ Technologies Used

| Category | Technology |
| :--- | :--- |
| **Language** | [![Java][Java-logo]][Java-url] |
| **Backend & Framework** | [![Spring Boot][Spring-Boot-logo]][Spring-Boot-url] [![Spring Data JPA][Spring-JPA-logo]][Spring-JPA-url] |
| **Database** | [![H2 Database][H2-logo]][H2-url] |
| **API Documentation** | [![Swagger][Swagger-logo]][Swagger-url] [![OpenAPI][OpenAPI-logo]][OpenAPI-url] |
| **Testing** | [![JUnit 5][JUnit5-logo]][JUnit5-url] [![Mockito][Mockito-logo]][Mockito-url] |
| **DevOps & Containers** | [![Docker][Docker-logo]][Docker-url] [![Docker Compose][Docker-Compose-logo]][Docker-Compose-url] [![Maven][Maven-logo]][Maven-url] |
| **Version Control** | [![Git][Git-logo]][Git-url] |

[Java-logo]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.oracle.com/java/technologies/downloads/#java21
[Spring-Boot-logo]: https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white
[Spring-Boot-url]: https://spring.io/projects/spring-boot
[Spring-JPA-logo]: https://img.shields.io/badge/spring_data_jpa-%236DB33F.svg?style=for-the-badge&logo=hibernate&logoColor=white
[Spring-JPA-url]: https://spring.io/projects/spring-data-jpa
[H2-logo]: https://img.shields.io/badge/H2_Database-%23003B57.svg?style=for-the-badge&logo=h2&logoColor=white
[H2-url]: https://www.h2database.com/
[Swagger-logo]: https://img.shields.io/badge/Swagger-%2385EA2D.svg?style=for-the-badge&logo=swagger&logoColor=black
[Swagger-url]: https://swagger.io/
[OpenAPI-logo]: https://img.shields.io/badge/OpenAPI-%236BA539.svg?style=for-the-badge&logo=openapiinitiative&logoColor=white
[OpenAPI-url]: https://www.openapis.org/
[JUnit5-logo]: https://img.shields.io/badge/JUnit5-%2325A162.svg?style=for-the-badge&logo=junit5&logoColor=white
[JUnit5-url]: https://junit.org/junit5/
[Mockito-logo]: https://img.shields.io/badge/Mockito-%23C5E063.svg?style=for-the-badge
[Mockito-url]: https://site.mockito.org/
[Docker-logo]: https://img.shields.io/badge/docker-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com/
[Docker-Compose-logo]: https://img.shields.io/badge/Docker_Compose-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white
[Docker-Compose-url]: https://docs.docker.com/compose/
[Maven-logo]: https://img.shields.io/badge/Apache_Maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white
[Maven-url]: https://maven.apache.org/
[Git-logo]: https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white
[Git-url]: https://git-scm.com

## 💡 Technical Decisions

1. **Java 21 LTS + Records:** Used `record` for all input and output DTOs, ensuring immutability, concise code, and eliminating boilerplate (*getters*, *equals*, *hashCode*, *toString*).
2. **Financial Precision (`BigDecimal`):** Rigorous use of `BigDecimal` with `RoundingMode.HALF_UP` and 2 decimal places across all salary calculations and wage ratio divisions, preventing floating-point rounding errors.
3. **RFC 7807 Standard (`ProblemDetail`):** Implemented global exception handling via `@RestControllerAdvice`, delivering detailed and standardized JSON error responses for validations and not-found resources.
4. **Optimized Multi-Stage Docker Build:** Separated the Maven compilation stage from the lightweight JRE Alpine runtime image, drastically reducing the final container footprint and enhancing security.
5. **Spring Data JPA with MappedSuperclass:** Clean entity inheritance between `Funcionario` and abstract `Pessoa` using `@MappedSuperclass`, keeping object-oriented concepts aligned with relational database mapping.

## 🚀 How to Run the Project

### Prerequisites
- **Docker & Docker Compose** installed OR **Java JDK 21** and **Maven**.

### Option 1: Via Docker Compose (Recommended)
```bash
# 1. Clone the repository
git clone https://github.com/ludson96/teste-pratico-projedata.git

# 2. Navigate to the project directory
cd teste-pratico-projedata

# 3. Build and start containers
docker compose up --build
```
> To run in background (detached mode), use `docker compose up -d`.

### Option 2: Via IDE (IntelliJ IDEA / VS Code / Eclipse)
1. Import the directory as a **Maven** project.
2. Set the Project SDK/JDK to **Java 21**.
3. Run the `main` method in `com.projedata.ProjedataApplication`.

### Option 3: Via Maven CLI
```bash
# Run the application
mvn spring-boot:run

# Run the automated test suite
mvn clean test
```

## 🔍 Useful Links (While App is Running)

- 📄 **Swagger UI (Interactive Docs):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- 📊 **OpenAPI JSON Spec:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- 🗄️ **H2 Web Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - **JDBC URL:** `jdbc:h2:mem:projedatadb`
  - **User:** `sa`
  - **Password:** *(leave blank)*

## 📄 License

This project is licensed under the [MIT License](LICENSE).
