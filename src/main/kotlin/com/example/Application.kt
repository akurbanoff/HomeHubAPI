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

    val debug = true
    var db_host = ""
    var jdbcUrl = ""
    var host = ""

     if(debug){
        db_host = "172.17.0.1"//"localhost"//"172.17.0.1"
        jdbcUrl = "jdbc:postgresql://${db_host}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}" //"jdbc:postgresql:${dotenv["DB_NAME"]}?port=${dotenv["DB_PORT"]}"
        host = "0.0.0.0"
    } else {
        db_host = "45.130.42.144"
         jdbcUrl = "jdbc:postgresql://${db_host}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"
        host = "0.0.0.0"//"45.130.42.144"
    }

    val dbConnection = Database.connect(
        url = jdbcUrl,
        driver = driverClassName,
        password = dotenv["DB_PASS"],
        user = dotenv["DB_USER"]
    )
    transaction(dbConnection) {
        SchemaUtils.create(Clients, Achieves, Employees,
            Objects, Dealings, Company, ClientDescriptions,
            News
        )
    }
    dbConnection.transactionManager.newTransaction().exec("ALTER TABLE employees ALTER COLUMN password TYPE VARCHAR(200);")
    dbConnection.transactionManager.newTransaction().exec("ALTER TABLE news ALTER COLUMN photos TYPE text[];")
    embeddedServer(
        Netty,
        port = dotenv["SERVER_PORT"].toInt(),
        module = Application::module,
        host = host
    )
        .start(wait = true)
}

fun Application.module() {
    //configureDatabase()
    //configureSecurity()
    configureSerialization()
    //configureHTTP()
    configureRouting()
}
