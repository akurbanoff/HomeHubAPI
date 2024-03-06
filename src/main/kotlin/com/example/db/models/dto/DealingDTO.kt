package com.example.db.models.dto

import com.example.parser.Stages
import kotlinx.serialization.Serializable


//"""
//    {"id":1,
//    "created_at":"2023-11-10 12:53:14",
//    "updated_at":"2024-02-20 14:18:21",
//    "name":"тест",
//    "note":"",
//    "comment":"",
//    "phones":["98767876567656765"],
//    "email":"",
//    "manager":{"id":3,"phone":"79015212121","email":"r.shvets21@yandex.ru","first_name":"Ростислав","last_name":"Швец"},
//    "stage":{"id":2,"name":"Нецелевой контакт"},"sub_stage":{"id":255,"name":"Спам\/реклама"},"source":null,"amount":0}
//"""

@Serializable
data class DealingDTO(
    val code: Int,
    val result: DealingResult,
)

@Serializable
data class DealingResult(
    val execution_time: Double,
    val limit: Int,
    val list: List<Dealing>,
    val order_by: String,
    val order_dir: String,
    val page: Int,
    val pages: Int,
    val total: Int
)

@Serializable
data class Dealing(
    val amount: Int,
    val comment: String?,
    val created_at: String,
    val email: String?,
    val id: Int,
    val manager: Manager?,
    val name: String?,
    val note: String?,
    val phones: List<String>,
    val source: Source?,
    val stage: Stage?,
    val sub_stage: SubStage?,
    val updated_at: String
)

@Serializable
data class Stage(
    val id: Int,
    val name: String
)

@Serializable
data class SubStage(
    val id: Int,
    val name: String
)