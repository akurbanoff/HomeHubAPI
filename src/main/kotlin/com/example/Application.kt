package com.example

import com.example.db.configureDatabase
import com.example.db.models.*
import com.example.plugins.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    val dotenv = dotenv()
    val driverClassName = "org.postgresql.Driver"
    val jdbcURL = if(dotenv["DEBUG"] == "false") "jdbc:postgresql://db:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}" else "jdbc:postgresql:${dotenv["DB_NAME"]}?port=${dotenv["DB_PORT"]}"
    val dbConnection = Database.connect(
        url = jdbcURL,
        driver = driverClassName,
        password = dotenv["DB_PASS"],
        user = dotenv["DB_USER"]
    )
    transaction(dbConnection) {
        SchemaUtils.create(Clients, Achieves, Employees, Objects, Dealings, Company, ClientDescriptions)
    }
    embeddedServer(
        Netty,
        port = dotenv["SERVER_PORT"].toInt(),
        module = Application::module,
        host = if(dotenv["DEBUG"] == "false") "45.130.42.144" else "0.0.0.0"
    )//host = "0.0.0.0"
        .start(wait = true)
}

fun Application.module() {
    //configureDatabase()
    //configureSecurity()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
