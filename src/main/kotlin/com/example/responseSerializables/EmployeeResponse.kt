package com.example.responseSerializables

import com.example.db.models.dto.EmployeeSerializable
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeResponse(
    val list: List<EmployeeSerializable>
)
