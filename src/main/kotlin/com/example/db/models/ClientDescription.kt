package com.example.db.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ClientDescriptions: IntIdTable() {
    val descriptionId = reference("descriptionId", Clients.id)
}

//class ClientDescription(id: EntityID<Int>): IntEntity(id){
//    companion object : IntEntityClass<ClientDescription>(ClientDescriptions)
//
//    val declarationId by Clients referrersOn ClientDescriptions.descriptionId
//}