package io.jadu

import io.jadu.data.db.local.TransactionRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = TransactionRepository()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}
