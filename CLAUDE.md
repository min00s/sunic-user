# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java Spring Boot microservice called `user-service` that manages user authentication and workspace membership. The application follows a multi-module Gradle structure with hexagonal architecture principles.

## Development Commands

### Build and Run
- `./gradlew build` - Build all modules
- `./gradlew :user-boot:bootRun` - Run the application (starts on default Spring Boot port)
- `./gradlew clean build` - Clean build all modules

### Testing
- `./gradlew test` - Run all tests across modules
- `./gradlew :module-name:test` - Run tests for specific module

### Database Setup
- Local development uses MySQL on `localhost:3306/sunic` with credentials `mysuni/mysuni`
- Production uses environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

## Architecture

### Module Structure
```
user-spec/     - Domain entities, exceptions, facades (interfaces) - Minimal dependencies
user-aggregate/- Business logic implementations, stores, JPO entities
user-rest/     - REST controllers and configuration, global exception handling
user-boot/     - Spring Boot application entry point
```

### Module Dependencies
- **user-spec**: Only essential annotations (Spring Context, Jakarta Validation, Jackson, OpenAPI)
- **user-aggregate**: Depends on user-spec + Spring Data JPA + Spring Security crypto
- **user-rest**: Depends on user-spec + user-aggregate + Spring Web + Spring Security  
- **user-boot**: Depends on user-rest (transitively includes full stack) + Redis + dev tools

### Key Patterns
- **Hexagonal Architecture**: Clear separation between domain (spec), application logic (aggregate), and adapters (rest)
- **Immutable Domain Entities**: All entities use builder pattern and return new instances for modifications
- **Entity-JPO Dual Model**: Domain entities (`User`) separate from JPA entities (`UserJpo`) with explicit conversion
- **Store Pattern**: Store classes (`UserStore`, `UserWorkspaceStore`) abstract data access with clear entity boundaries
- **CDO/UDO/RDO Pattern**: Command Data Objects for creation, Update Data Objects for modification, Response Data Objects for output
- **Facade Pattern**: REST controllers implement Swagger-documented facade interfaces with proper HTTP status mapping
- **Global Exception Handling**: Centralized exception handling with domain-specific HTTP status codes

### Core Components
- **UserLogic** (`user-aggregate/src/main/java/com/sunic/user/aggregate/user/logic/UserLogic.java`): Implements user operations using entity factory methods and business logic
- **UserWorkspaceLogic** (`user-aggregate/src/main/java/com/sunic/user/aggregate/userworkspace/logic/UserWorkspaceLogic.java`): Manages workspace operations using entity creation and state management methods
- **UserStore** (`user-aggregate/src/main/java/com/sunic/user/aggregate/user/store/UserStore.java`): Data access for User entities with JPO conversion
- **UserWorkspaceStore** (`user-aggregate/src/main/java/com/sunic/user/aggregate/userworkspace/store/UserWorkspaceStore.java`): Data access for UserWorkspace entities with JPO conversion
- **GlobalExceptionHandler** (`user-rest/src/main/java/com/sunic/user/rest/config/GlobalExceptionHandler.java`): Centralized exception handling with proper HTTP status mapping

### Domain Entity Logic
Entities in the `user-spec` module contain their own creation and modification logic:
- **User**: `create()`, `modify()`, `changePassword()`, `updateLoginFailCount()`, `resetLoginFailCount()`
- **UserWorkspace**: `create()`, `modify()`, `changeState()`, `toRdo()` conversion method
- **DeactivatedUser**: `fromUser()` factory method

### Data Transfer Object Structure
- **CDO (Command Data Objects)**: Used for entity creation operations (e.g., `UserWorkspaceCdo`)
- **UDO (Update Data Objects)**: Used for entity modification operations (e.g., `UserWorkspaceUdo`)  
- **SDO (Service Data Objects)**: Used for service layer operations (e.g., `UserRegisterSdo`, `UserLoginSdo`)
- **RDO (Response Data Objects)**: Used for API responses (e.g., `UserLoginRdo`, `UserWorkspaceRdo`)
- **BaseResponse/ErrorResponse**: Standardized response wrapper with success flag and data

### Technology Stack
- Java 17
- Spring Boot 3.5.4-SNAPSHOT
- Spring Data JPA with MySQL
- Spring Security with JWT
- Lombok for boilerplate reduction
- Swagger/OpenAPI for API documentation (available at `/api-test`)

## Configuration Profiles
- `local`: Development profile with hardcoded MySQL credentials and SQL logging enabled
- `prod`: Production profile using environment variables with optimized JPA settings

## Important Architecture Notes

### Entity Organization
- **Domain entities** are located in `user-spec/src/main/java/com/sunic/user/spec/facade/*/entity/`
- **JPA entities (JPO)** are located in `user-aggregate/src/main/java/com/sunic/user/aggregate/*/store/jpo/`
- **Data Transfer Objects** are located in `user-spec/src/main/java/com/sunic/user/spec/facade/*/vo/`

### Logic Layer Implementation
- Logic classes do NOT implement facade interfaces directly (facades are for REST layer only)
- Logic classes provide business methods that are called by REST controllers
- REST controllers implement facade interfaces and delegate to logic classes

### Store Pattern Implementation
- Each aggregate has its own store class with clear entity boundaries
- Store classes handle conversion between domain entities and JPO entities
- Repositories are injected into stores, not directly into logic classes

## Security Notes
- JWT secret is configured in `application.yml` (consider moving to environment variables for production)
- Password encoding handled by Spring Security's PasswordEncoder  
- Login failure tracking implemented with automatic user lockout logic