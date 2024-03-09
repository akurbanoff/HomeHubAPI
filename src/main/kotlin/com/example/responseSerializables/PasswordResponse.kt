package com.example.responseSerializables

import kotlinx.serialization.Serializable

@Serializable
data class PasswordResponse(
    val status: Int
)
