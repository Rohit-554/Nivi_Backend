# Nivi Backend

A Ktor-based REST API backend application with PostgreSQL database integration, JWT authentication, and Docker containerization.

## Technology Stack

- Kotlin
- Ktor 3.0.0
- PostgreSQL 15
- Exposed ORM
- HikariCP connection pooling
- JWT authentication
- Docker and Docker Compose
- Gradle build system

## Project Structure

```
src/main/kotlin/
├── Application.kt      # Application entry point and module configuration
├── Databases.kt        # Database connection configuration using HikariCP
├── Security.kt         # JWT authentication setup
├── Routing.kt          # API routes and status page configuration
├── Monitoring.kt       # Request logging configuration
├── Serialization.kt    # JSON serialization setup
├── UsersSchema.kt      # User entity and CRUD operations using Exposed ORM
└── CitySchema.kt       # City entity and CRUD operations using JDBC
```

## Features

- PostgreSQL database integration with HikariCP connection pooling
- Exposed ORM for database operations
- JWT-based authentication
- Request/response logging
- Exception handling with status pages
- JSON content negotiation with kotlinx.serialization
- Docker containerization for database
- pgAdmin for database management

## Database Configuration

The application uses PostgreSQL running in a Docker container with the following configuration:

- Host: localhost
- Port: 5433
- Database: nivi_db
- Username: myuser
- Password: mypassword

Database connection is managed through HikariCP with the following settings:
- Maximum pool size: 3
- Transaction isolation: REPEATABLE_READ
- Auto-commit: disabled

## Prerequisites

- JDK 11 or higher
- Docker and Docker Compose
- Gradle (included via wrapper)

## Setup and Installation

1. Clone the repository

2. Start the PostgreSQL database using Docker Compose:
```bash
docker-compose up -d
```

This will start:
- PostgreSQL database on port 5433
- pgAdmin web interface on port 5050

3. Access pgAdmin (optional):
- URL: http://localhost:5050
- Email: admin@admin.com
- Password: admin

4. Build the application:
```bash
./gradlew build
```

5. Run the application:
```bash
./gradlew run
```

The server will start on http://localhost:8080

## Available Gradle Tasks

| Task | Description |
|------|-------------|
| `./gradlew build` | Build the application |
| `./gradlew test` | Run tests |
| `./gradlew run` | Run the application |
| `./gradlew buildFatJar` | Build executable JAR with all dependencies |

## Database Schema

### Users Table
- id: Integer (auto-increment primary key)
- name: Varchar(50)
- age: Integer

Uses Exposed ORM for CRUD operations.

### Cities Table
- id: Serial (auto-increment primary key)
- name: Varchar(255)
- population: Integer

Uses JDBC for CRUD operations.

## API Endpoints

### Health Check
```
GET /
Response: "Hello guys kaise ho aplog!"
```

### JSON Test Endpoint
```
GET /json/kotlinx-serialization
Response: {"hello": "world"}
```

## Authentication

The application includes JWT authentication configuration with the following default settings:
- Algorithm: HMAC256
- Audience: jwt-audience
- Domain: https://jwt-provider-domain/
- Realm: ktor sample app

## Configuration Files

### application.yaml
Contains application configuration including:
- Server port (8080)
- Database connection settings
- JWT configuration

### docker-compose.yml
Defines services for:
- PostgreSQL database
- pgAdmin interface

## Error Handling

The application includes global exception handling that returns 500 status codes with error details for unhandled exceptions.

## Logging

Request logging is enabled for all endpoints using Ktor's CallLogging plugin with INFO level.

## Development Notes

- The application uses Ktor's EngineMain for server initialization
- Database tables are automatically created on application startup
- All database operations are executed asynchronously using coroutines
- Connection pooling ensures efficient database resource management

## Troubleshooting

If you encounter database connection errors:

1. Ensure Docker containers are running:
```bash
docker ps
```

2. Check container logs:
```bash
docker logs nivi_db
```

3. Verify database connection settings in application.yaml match docker-compose.yml

4. Ensure port 5433 is not in use by another application

## License

This project was created using the Ktor Project Generator.

## Additional Resources

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Exposed Framework](https://github.com/JetBrains/Exposed)
- [HikariCP](https://github.com/brettwooldridge/HikariCP)
