package com.example.responseSerializables

import kotlinx.serialization.Serializable

@Serializable
data class NewsInsertResponse(
    val status: Int
)
