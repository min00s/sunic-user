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
user-spec/     - Domain entities, exceptions, facades (interfaces)
user-aggregate/- Business logic implementations 
user-rest/     - REST controllers and configuration
user-boot/     - Spring Boot application entry point
```

### Key Patterns
- **Hexagonal Architecture**: Clear separation between domain (spec), application logic (aggregate), and adapters (rest)
- **Immutable Domain Entities**: All entities use builder pattern and return new instances for modifications
- **Entity-JPO Dual Model**: Domain entities (`User`) separate from JPA entities (`UserJpo`) with explicit conversion
- **Store Pattern**: Store classes (`UserStore`, `UserWorkspaceStore`) abstract data access with clear entity boundaries
- **SDO/RDO Pattern**: Service Data Objects for input, Response Data Objects for output
- **Facade Pattern**: Separate facades for different business capabilities

### Core Components
- **UserLogic** (`user-aggregate/src/main/java/com/sunic/user/aggregate/user/logic/UserLogic.java`): Coordinates user operations by delegating to specialized services
- **UserWorkspaceLogic** (`user-aggregate/src/main/java/com/sunic/user/aggregate/userworkspace/logic/UserWorkspaceLogic.java`): Manages workspace operations
- **UserStore** (`user-aggregate/src/main/java/com/sunic/user/aggregate/user/store/UserStore.java`): Data access for User entities only
- **UserWorkspaceStore** (`user-aggregate/src/main/java/com/sunic/user/aggregate/userworkspace/store/UserWorkspaceStore.java`): Data access for UserWorkspace entities only

### Domain Entity Logic
Entities in the `user-spec` module contain their own creation and modification logic:
- **User**: `create()`, `modify()`, `changePassword()`, `updateLoginFailCount()`, `resetLoginFailCount()`
- **UserWorkspace**: `create()`, `modify()`, `changeState()`
- **DeactivatedUser**: `fromUser()` factory method

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

## Security Notes
- JWT secret is configured in `application.yml` (consider moving to environment variables for production)
- Password encoding handled by Spring Security's PasswordEncoder  
- Login failure tracking implemented with automatic user lockout logic