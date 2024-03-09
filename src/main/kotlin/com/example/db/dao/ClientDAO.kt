package com.example.db.dao

import com.example.db.models.Clients
import com.example.db.models.dto.ClientDTO
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

//class ClientDAO {
//    fun createClient(client: ClientDTO){
//        transaction {
//            Clients.insert {
//                it[id] = client.id
//                it[created_at] = client.created_at
//
//            }
//        }
//    }
//}