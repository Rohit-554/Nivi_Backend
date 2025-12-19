package io.jadu

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    // 1. Configure db
    val config = HikariConfig().apply {
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = "jdbc:postgresql://localhost:5433/nivi_db"
        username = "myuser"      // From docker-compose
        password = "mypassword"  // From docker-compose
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }

    // 2. Connect Exposed to this pool
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    log.info("✅ Database connected successfully to Docker Postgres!")
}