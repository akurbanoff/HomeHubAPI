package com.example.responseSerializables

import com.example.db.models.dto.Client
import kotlinx.serialization.Serializable

@Serializable
data class ClientResponse(
    val list: List<Client>
)
