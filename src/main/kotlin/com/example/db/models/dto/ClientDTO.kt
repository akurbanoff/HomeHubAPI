package com.example.db.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class ClientDTO(
    val code: Int,
    val result: Result,
)


@Serializable
data class Result(
    val execution_time: Double,
    val limit: Int,
    val list: List<Client>,
    val order_by: String,
    val order_dir: String,
    val page: Int,
    val pages: Int,
    val total: Int
)

@Serializable
data class Client (
    val amount: Int,
    val comment: String?,
    val created_at: String,
    val department: Department?,
    val direction: Direction,
    val email: String?,
    val id: Int,
    val is_picked: Boolean,
    val manager: Manager?,
    val name: String?,
    val phone: String,
    val picked_at: String,
    val quality: Quality,
    val source: Source?,
    val type: Type,
    val updated_at: String
)

@Serializable
data class Department(
    val id: Int,
    val name: String
)

@Serializable
data class Direction(
    val id: Int,
    val name: String
)

@Serializable
data class Manager(
    val id: Int,
    val phone: String,
    val email: String,
    val first_name: String,
    val last_name: String,
)

@Serializable
data class Quality(
    val id: Int,
    val name: String
)

@Serializable
data class Source(
    val id: Int,
    val name: String
)

@Serializable
data class Type(
    val id: Int,
    val name: String
)