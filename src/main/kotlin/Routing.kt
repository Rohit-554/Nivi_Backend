package io.jadu

import io.jadu.data.db.local.UserRepository
import io.jadu.routing.authRoute
import io.jadu.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(repository: UserRepository, tokenManager: TokenManager) {

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        authRoute(repository, tokenManager)
        get("/") {
            call.respondText("Hello guys kaise ho aplog!")
        }
    }
}
