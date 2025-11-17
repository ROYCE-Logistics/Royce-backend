# a1-logistics-backend

Enterprise Trucking & Logistics Management System built with Spring Boot 3, Java 17, PostgreSQL, Spring Security JWT and Flyway.

## Getting started

```bash
cd a1-logistics-backend
./mvnw spring-boot:run
```

Configure the database connection in `src/main/resources/application.yml`.

## Features

- User, Driver, Truck, Trailer, Load, Alert, Document, Settlement, Statistic, Import/Export domain models
- JWT authentication & refresh tokens
- Role-based authorization with CORS for Vue/Flutter frontends
- REST APIs covering admin, fleet, finance and analytics workflows
- Placeholder AI analytics endpoints
- File upload for documents plus import/export job tracking
- Flyway baseline migration and unit/integration tests
