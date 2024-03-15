package com.example

import com.example.db.configureDatabase
import com.example.db.models.*
import com.example.plugins.*
import com.zaxxer.hikari.HikariConfig
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import java.util.Properties

fun main() {
    val dotenv = dotenv()
    val driverClassName = "org.postgresql.Driver"

    val debug = false

    val db_host = if(debug){
        "localhost"
    } else {
        //"172.17.0.1"
        "45.130.42.144"
    }

    val jdbcURL = if(debug)
        "jdbc:postgresql:${dotenv["DB_NAME"]}?port=${dotenv["DB_PORT"]}"
    else
        "jdbc:postgresql://${db_host}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"

    val dbConnection = Database.connect(
        url = jdbcURL,
        driver = driverClassName,
        password = dotenv["DB_PASS"],
        user = dotenv["DB_USER"]
    )
    dbConnection.transactionManager.newTransaction().exec("ALTER TABLE employees ALTER COLUMN password TYPE VARCHAR(200);")
    transaction(dbConnection) {
        SchemaUtils.create(Clients, Achieves, Employees, Objects, Dealings, Company, ClientDescriptions)
    }
    embeddedServer(
        Netty,
        port = dotenv["SERVER_PORT"].toInt(),
        module = Application::module,
        host = if(debug) "0.0.0.0" else "45.130.42.144"
    )
        .start(wait = true)
}

fun Application.module() {
    //configureDatabase()
    //configureSecurity()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
