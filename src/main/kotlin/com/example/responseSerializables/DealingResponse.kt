package com.example.responseSerializables

import com.example.db.models.dto.Dealing
import kotlinx.serialization.Serializable

@Serializable
data class DealingResponse(
    val list: List<Dealing>
)
