package com.example.db.models.dto

import com.example.parser.Stages
import kotlinx.serialization.Serializable

@Serializable
data class DealingDTO(
    val code: Int,
    val result: DealingResult,
)

@Serializable
data class DealingResult(
    val execution_time: Double,
    val limit: Int,
    val list: ArrayList<Dealing>,
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
    val manager: Manager,
    val name: String?,
    val note: String?,
    val phones: List<String>,
    val source: Source?,
    val stage: Stage,
    val sub_stage: SubStage,
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