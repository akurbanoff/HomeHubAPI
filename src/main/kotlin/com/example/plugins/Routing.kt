package com.example.plugins

import com.example.db.dao.EmployeeDAO
import com.example.db.repositories.Repository
import com.example.requestSerializables.NewsRequest
import com.example.requestSerializables.PasswordExistRequest
import com.example.requestSerializables.PasswordRequest
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.cio.*
import io.ktor.utils.io.core.*
import java.io.File
import java.util.*

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
            val multipartData = call.receiveMultipart()
            var title: String = ""
            var description: String = ""
            var images = mutableListOf<String>()

            multipartData.forEachPart { part ->
                when(part){
                    is PartData.BinaryItem -> {
                        val bytes = part.provider().readBytes()
                        val file = File("/photos/${Random().nextInt()}.jpeg")
                        if(!file.exists()) file.createNewFile()
                        file.writeBytes(bytes)
                        images.add(file.name)
                    }
                    is PartData.FormItem -> {
                        if(part.name == "title") title = part.value
                        if(part.name == "description") description = part.value
                    }
                    is PartData.BinaryChannelItem -> {
                        //images.add(part.provider().toByteArray())
                    }
                    is PartData.FileItem -> {
                        val bytes = part.streamProvider().readBytes()
                        val file = File("/photos/${Random().nextInt()}.jpeg")
                        if(!file.exists()) file.createNewFile()
                        file.writeBytes(bytes)
                        println(file.name)
                        images.add(file.name)
                    }
                }
                part.dispose()
            }

            println(images.toString())

            call.respond(repository.insertNews(
                NewsRequest(
                    title = title,
                    description = description,
                    photos = images.toList()
                )
            ))
        }
//        post("/upload_news_photos") {
//            val imageName = call.parameters["name"] ?: UUID.randomUUID().toString()
//            val image: ByteArray =
//        }
    }
}
