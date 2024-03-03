package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    routing {
        openAPI(path = "docs")
    }
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
}
