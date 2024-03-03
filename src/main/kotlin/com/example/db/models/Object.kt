package com.example.db.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Objects: IntIdTable() {
    val employeeId = reference("employeeId", Employees)
}

class Object(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<Object>(Objects)

    val employeeId by Employee referrersOn Objects.employeeId
}