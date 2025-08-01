# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java Spring Boot microservice called `user-service` that manages user authentication and workspace membership. The application follows a **4-layer hexagonal architecture** with strict domain-driven design principles and modular separation.

## Development Commands

### Build and Run
- `./gradlew build` - Build all modules
- `./gradlew :user-boot:bootRun` - Run the application (starts on default Spring Boot port)
- `./gradlew clean build` - Clean build all modules

### Testing
- `./gradlew test` - Run all tests across modules
- `./gradlew :module-name:test` - Run tests for specific module (e.g., `:user-spec:test`)

### Database Setup
- **Local development**: MySQL on `localhost:3306/sunic` with credentials `mysuni/mysuni`
- **Production**: Uses environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

## Architecture Overview

### 4-Layer Modular Architecture
The project follows a unidirectional dependency flow:
```
user-boot → user-rest → user-aggregate → user-spec
```

### Module Responsibilities

#### user-spec (Domain Contracts)
- **Purpose**: Defines pure domain entities, facades, exceptions, and data transfer objects
- **Key Pattern**: Domain-driven design with clean separation from framework dependencies
- **Dependencies**: Minimal - only Jakarta Validation, Jackson annotations, SpringDoc OpenAPI

**Package Structure**:
```
com.sunic.user.spec/
├── common/              # Shared response wrappers (ApiResponse, ErrorResponse)
├── user/                # User domain aggregate
│   ├── entity/          # Pure domain entities (User, Role, DeactivatedUser)
│   ├── exception/       # User-specific exceptions
│   └── facade/          # Interface contracts
│       └── sdo/         # Service Data Objects (CQRS pattern)
└── userworkspace/       # UserWorkspace domain aggregate
    ├── entity/          # Pure domain entities (UserWorkspace, UserWorkspaceState, UserWorkspaceType)
    ├── exception/       # Workspace-specific exceptions
    └── facade/          # Interface contracts
        └── sdo/         # Service Data Objects (CQRS pattern)
```

#### user-aggregate (Business Logic & Data Access)
- **Purpose**: Implements business logic and provides data persistence abstraction
- **Key Patterns**: Logic classes for business rules, Store classes for data access facade, JPO entities for JPA

**Package Structure**:
```
com.sunic.user.aggregate/
├── user/
│   ├── logic/           # UserLogic - business operations
│   └── store/           # Data access facade
│       ├── jpo/         # JPA Persistence Objects (UserJpo, DeactivatedUserJpo)
│       └── repository/  # Spring Data repositories
└── userworkspace/
    ├── logic/           # UserWorkspaceLogic - business operations  
    └── store/           # Data access facade
        ├── jpo/         # JPA Persistence Objects (UserWorkspaceJpo)
        └── repository/  # Spring Data repositories
```

#### user-rest (Web Layer)
- **Purpose**: Exposes REST APIs and handles HTTP concerns
- **Key Patterns**: Resource controllers implement facade interfaces, global exception handling

**Package Structure**:
```
com.sunic.user.rest/
├── config/              # Web configurations (Security, Swagger, GlobalExceptionHandler)
└── rest/                # REST controllers
    ├── user/            # UserResource
    └── userworkspace/   # UserWorkspaceResource
```

#### user-boot (Application Bootstrap)
- **Purpose**: Application entry point and global configuration
- **Dependencies**: Includes full stack transitively, plus Redis and dev tools

## Key Architecture Patterns

### Domain Entity Logic
Domain entities contain their own business methods and factory patterns:
- **User**: `create()`, `modify()`, `changePassword()`, `updateLoginFailCount()`, `resetLoginFailCount()`, `toLoginRdo()`
- **UserWorkspace**: `create()`, `modify()`, `deleteState()`, `toRdo()`
- **DeactivatedUser**: `fromUser()` factory method

### Entity-JPO Dual Model
- **Domain entities** (User, UserWorkspace) are pure business objects in `user-spec`
- **JPA entities** (UserJpo, UserWorkspaceJpo) handle persistence concerns in `user-aggregate`
- **Store classes** handle conversion between domain and JPA entities

### CQRS Data Transfer Pattern
- **CDO (Command Data Objects)**: For entity creation operations
- **UDO (Update Data Objects)**: For entity modification operations  
- **SDO (Service Data Objects)**: For service layer operations
- **RDO (Response Data Objects)**: For API responses
- **ApiResponse/ErrorResponse**: Standardized response wrappers

### Store Pattern
- **UserStore**: Abstracts user data access with methods like `save()`, `findByEmail()`, `existsByEmail()`
- **UserWorkspaceStore**: Abstracts workspace data access with methods like `save()`, `findById()`, `existsByName()`
- Store classes encapsulate JPO conversion and repository operations

### Global Exception Handling
- **GlobalExceptionHandler**: Centralized exception handling with domain-specific HTTP status codes
- Domain exceptions map to appropriate HTTP status codes (404, 409, 401, 403, 400)

## Configuration Profiles
- **local**: Development profile with hardcoded MySQL credentials and SQL logging enabled
- **prod**: Production profile using environment variables with optimized JPA settings

## Important Development Notes

### Entity Modification Patterns
- Domain entities are immutable by design - modification methods return new instances
- Use entity factory methods (e.g., `User.create()`) for entity creation
- Business logic resides in domain entities, not in service classes

### Logic Layer Implementation
- Logic classes (UserLogic, UserWorkspaceLogic) orchestrate business operations
- Logic classes use Store classes for data access, never repositories directly
- REST controllers implement facade interfaces and delegate to logic classes

### Security Considerations
- JWT secret configured in `application.yml` (consider environment variables for production)
- Password encoding handled by Spring Security's PasswordEncoder
- Login failure tracking with automatic user lockout logic

### Testing Strategy
- Each module can be tested in isolation due to clean architecture boundaries
- Domain logic is testable without Spring context
- Use `./gradlew :module-name:test` for focused testing

## Technology Stack
- Java 17
- Spring Boot 3.5.4-SNAPSHOT
- Spring Data JPA with MySQL
- Spring Security with JWT
- Lombok for boilerplate reduction
- Swagger/OpenAPI for API documentation (available at `/api-test`)
- Redis for caching/session management