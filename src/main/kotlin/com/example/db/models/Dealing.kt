package com.example.db.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

//"""
//    {
//      "id": 1169,
//      "created_at": "2024-03-01 20:12:30",
//      "updated_at": "2024-03-01 20:12:30",
//      "name": "Тест",
//      "note": "",
//      "comment": "test",
//      "phones": [
//        "79999999999"
//      ],
//      "email": "test@ya.ru",
//      "manager": {
//        "id": 11,
//        "phone": "79298489777",
//        "email": "nerluck@vk.com",
//        "first_name": "Святослав",
//        "last_name": "Швец"
//      },
//      "stage": {
//        "id": 24,
//        "name": "Объект продан"
//      },
//      "sub_stage": null,
//      "source": {
//        "id": 5,
//        "name": "Собственник"
//      },
//      "amount": 1000000
//    }
//"""

//"""
//    {
//      "id": 1170,
//      "created_at": "2024-03-01 21:04:40",
//      "updated_at": "2024-03-01 21:04:40",
//      "name": "ТЕСТ покупатель",
//      "note": "",
//      "comment": "",
//      "phones": [],
//      "email": "",
//      "manager": {
//        "id": 11,
//        "phone": "79298489777",
//        "email": "nerluck@vk.com",
//        "first_name": "Святослав",
//        "last_name": "Швец"
//      },
//      "stage": {
//        "id": 17,
//        "name": "Сделка состоялась"
//      },
//      "sub_stage": null,
//      "source": {
//        "id": 4,
//        "name": "Исходящий звонок"
//      },
//      "amount": 0
//    }
//"""

// Запрос -  https://faber.yucrm.ru/api/v1/05bf76a430bfaf6214355d8e38784168/clients/list

enum class Stages(val title: String){
    OBJECT_SOLD("Объект продан"),
    CLIENT_BOUGHT("Сделка состоялась")
}

object Dealings: IntIdTable() {
    val employeeId = reference("employeeId", Employees.id)
}

//class Dealing(id: EntityID<Int>): IntEntity(id){
//    companion object : IntEntityClass<Dealing>(Dealings)
//
//    val employeeId by Employee referrersOn Dealings.employeeId
//}