package com.example.plugins

import com.example.db.dao.EmployeeDAO
import com.example.db.repositories.Repository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = Repository()
    routing {
        get("/get_employees") {
            call.respond(repository.getEmployees())
        }
        get("/get_dealings"){
            call.respond(repository.getDealings())
        }
        get("/get_clients"){
            call.respond(repository.getClients())
        }
    }
}
