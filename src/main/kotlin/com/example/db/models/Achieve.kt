package com.example.db.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Achieves: IntIdTable("achieves"){
    val description = varchar("description", 50)
    val hubcoinRevard = integer("hubcoinRevard")
    val expirienceRevard = integer("expirienceRevard")
    val employeeId = reference("employeeId", Employees.id)
}

//class Achieve(id: EntityID<Int>): IntEntity(id) {
//    companion object : IntEntityClass<Achieve>(Achieves)
//
//    val employeeId by Employee referrersOn Achieves.employeeId
//}