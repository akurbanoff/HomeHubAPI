package com.example.plugins

import com.example.db.dao.EmployeeDAO
import com.example.db.repositories.Repository
import com.example.requestSerializables.NewsRequest
import com.example.requestSerializables.PasswordExistRequest
import com.example.requestSerializables.PasswordRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
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
        post("/insert_password"){
            val request = PasswordRequest(
                id = call.parameters["id"]?.toInt() ?: 999,
                password = call.parameters["password"] ?: "")
            call.respond(repository.insertPassword(request))
        }
        get("/check_password") {
            val request = PasswordRequest(
                id = call.parameters["id"]?.toInt() ?: 999,
                password = call.parameters["password"] ?: "")
            //println(request.password)
            call.respond(repository.checkPassword(request = request))
        }
        get("/check_password_exist"){
            val request = PasswordExistRequest(call.parameters["id"]?.toInt() ?: 999)
            call.respond(repository.checkExistPassword(request.id))
        }
        get("/get_news"){
            val skip = call.parameters["skip"]?.toInt() ?: 0
            val limit = call.parameters["limit"]?.toInt() ?: 5
            call.respond(repository.getNews(skip = skip, limit = limit))
        }

        post("/insert_news"){
            val request = call.receive<NewsRequest>()
            call.respond(repository.insertNews(request))
        }
    }
}
