package io.jadu

import io.jadu.data.db.local.TransactionRepository
import io.jadu.data.db.local.UserRepository
import io.jadu.utils.TokenManager
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = TransactionRepository()
    val tokenManager = TokenManager(environment.config)
    val userRepository = UserRepository()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting(userRepository, tokenManager, repository)
}
