package com.example.db.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

//"""
//    {
//      "id": 1,
//      "created_at": "2023-11-10 12:53:14",
//      "updated_at": "2024-02-20 14:18:21",
//      "is_picked": true,
//      "picked_at": "2024-02-20 14:18:21",
//      "name": "тест",
//      "comment": null,
//      "phone": "98767876567656765",
//      "email": "",
//      "manager": {
//        "id": 3,
//        "phone": "79015212121",
//        "email": "r.shvets21@yandex.ru",
//        "first_name": "Ростислав",
//        "last_name": "Швец"
//      },
//      "department": {
//        "id": 34,
//        "name": "Руководство"
//      },
//      "source": null,
//      "type": {
//        "id": 10,
//        "name": "Создан менеджером"
//      },
//      "direction": {
//        "id": 1,
//        "name": "Входящий"
//      },
//      "quality": {
//        "id": 2,
//        "name": "Нецелевой"
//      },
//      "amount": 0
//    }
//"""


object Clients: IntIdTable("clients") {
    val created_at = varchar("created_at", 60)
    val department_id = integer("department_id").nullable()
    val department_name = varchar("department_name", 30).nullable()
    val direction_id = integer("direction_id")
    val direction_name = varchar("direction_name", 30)
    val is_picked = bool("is_picked")
    val type_id = integer("type_id")
    val type_name = varchar("type_name", 30)
    val phone_number = integer("phone_number")
    val name = varchar("name", 100).nullable()
    val email = varchar("email", 50).nullable()
    val region = varchar("region", 100)
    val employeeId = reference("employeeId", Employees.id)
}

//class Client(id: EntityID<Int>): IntEntity(id){
//    companion object : IntEntityClass<Client>(Clients)
//
//    var createdAt by Clients.created_at
//    val employeeId by Employee referrersOn Clients.employeeId
//}